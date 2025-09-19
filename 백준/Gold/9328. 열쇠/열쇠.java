

/*
[백준]
9328, 열쇠

[문제파악]
상근이는 1층 빌딩에 침입해 매우 중요한 문서를 훔쳐오려고 한다.
상근이가 가지고 있는 평면도에는 문서의 위치가 모두 나타나 있다.
빌딩의 문은 모두 잠겨있기 때문에, 문을 열려면 열쇠가 필요하다.
상근이는 일부 열쇠를 이미 가지고 있고, 일부 열쇠는 빌딩의 바닥에 놓여져 있다.
상근이는 상하좌우로만 이동할 수 있다.

상근이가 훔칠 수 있는 문서의 최대 개수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 테스트 케이스의 개수가 주어진다.
테스트 케이스의 수는 100개를 넘지 않는다.

각 테스트 케이스의 첫째 줄에는 지도의 높이와 너비 h와 w (2 ≤ h, w ≤ 100)가 주어진다.
다음 h개 줄에는 빌딩을 나타내는 w개의 문자가 주어지며, 각 문자는 다음 중 하나이다.

'.'는 빈 공간을 나타낸다.
'*'는 벽을 나타내며, 상근이는 벽을 통과할 수 없다.
'$'는 상근이가 훔쳐야하는 문서이다.
알파벳 대문자는 문을 나타낸다.
알파벳 소문자는 열쇠를 나타내며, 그 문자의 대문자인 모든 문을 열 수 있다.
마지막 줄에는 상근이가 이미 가지고 있는 열쇠가 공백없이 주어진다.
만약, 열쇠를 하나도 가지고 있지 않는 경우에는 "0"이 주어진다.

상근이는 처음에는 빌딩의 밖에 있으며, 빌딩 가장자리의 벽이 아닌 곳을 통해 빌딩 안팎을 드나들 수 있다.
각각의 문에 대해서, 그 문을 열 수 있는 열쇠의 개수는 0개, 1개, 또는 그 이상이고, 각각의 열쇠에 대해서, 그 열쇠로 열 수 있는 문의 개수도 0개, 1개, 또는 그 이상이다.
열쇠는 여러 번 사용할 수 있다.

[출력]
각 테스트 케이스 마다, 상근이가 훔칠 수 있는 문서의 최대 개수를 출력한다.

[구현방법]
1. BFS 사용할 것이고, Map은 Char[][]
2. 일단 소유한 알파벳 26개 열쇠를 기억하는 boolean[] 배열을 만든다
    - 초기에 소유한 열쇠 & 진행하다 열쇠를 주우면 이 배열을 true로 바꿔서 사용
3. 4면을 for문을 돌며 진입로를 Queue에 등록해둔다
4. 탐색해가며, isVisited(방문배열)과 벽과 열쇠, 문서를 주울 때마다 분기를 탄다
5. 특히 현재 소유한 열쇠와 문을 대조할 땐, isUpper로 체크한다
6. 그렇게 더이상 이동할 수 없는 지점이 왔을 떄 여태껏 주운 문서 갯수를 max값과 비교하며 갱신한다

예외처리로는 진입로가 없는 경우 0으로 출력하는 것과, (밖을 나갈 수 있으니) 하나의 루트를 다 탐색하고 다른 루트로 진입해서 또 찾아보는 경우의 수도 계산해야한다
- 여기까지 생각해두었는데 보완하면 좋은 점이 두가지가 있다
1. 일단 지도를 가로세로 +2 사이즈로 만들어서 '.'로 둘러싸면 외부 탐색을 하며 진입로를 찾을 필요 자체가 없다 (BFS가 알아서 해줄거니까)
    - 외부에서 또 다른 진입로도 시도해보는 생각은 좋았음
2. 이 문제는 최단 경로를 찾는 것이 아닌만큼, 열쇠가 없어 못지나간 문을, 나중에 열쇠를 찾게되면? Queue에 추가해줘야한다는 것이다
    - 즉, 지금 Y를 마주쳤는데 키 y가 없어서 패스
    - 이후에 y키를 찾음
    - 이 시점에 Y로 돌아가서 문을 열고 거기서부터 탐색할 가능성이 열리는 것임
    - 그러니 Queue에 Y 지점을 추가해줘야 한다
    - 즉, 키가 없는 문이라도 좌표를 기억해뒀다가 열쇠를 찾으면 분기 조건으로 해당 문의 좌표를 Queue에 추가해주는 로직 추가가 필요

[보완점]
- computeIfAbsent 제대로 쓸줄 모르더라..? 보완해야 함
*/

import java.io.*;
import java.util.*;

public class Main {
    static int[] moveX = {0, 1, 0, -1};
    static int[] moveY = {-1, 0, 1, 0};

    static int h, w;
    static char[][] map;
    static boolean[][] isVisited;
    static boolean[] keys;

    static int findMap() {
        // 3. 4면을 for문을 돌며 진입로를 Queue에 등록해둔다
        // 이때 시작지점은 (0, 0), 방문처리
        int count = 0;
        Queue<int[]> location = new ArrayDeque<>();
        location.add(new int[] {0, 0});
        isVisited[0][0] = true;
        Map<Character, Queue<int[]>> doors = new HashMap<>();

        while (!location.isEmpty()) {
            int[] curr = location.poll();
            // 4. 탐색해가며, isVisited(방문배열)과 벽과 열쇠, 문서를 주울 때마다 분기를 탄다
            //          - 열쇠(e.g. y키)를 찾은 순간, 이전에 마주한, 키가 없어 못지나간 문(e.g. Y문)이 있다면 해당 문을 Queue에 추가해준다

            for (int i = 0; i < 4; i++) {
                int nextX = curr[0] + moveX[i];
                int nextY = curr[1] + moveY[i];

                // 맵 범위를 나갔거나 방문한적 잆거나 벽이면 패스
                if (nextX < 0 || nextY < 0 || h <= nextX || w <= nextY
                        || isVisited[nextX][nextY]
                        || map[nextX][nextY] == '*') continue;

                char currLocation = map[nextX][nextY];

                // if : 이동 가능한 지점이면 그냥 Q에 추가하고 방문처리
                // else if : 문서면 갯수 count + 1
                // else : 문이거나 열쇠이면
                if (currLocation == '.') {
                    location.add(new int[] {nextX, nextY});
                    isVisited[nextX][nextY] = true;
                } else if (currLocation == '$') {
                    map[nextX][nextY] = '.';
                    location.add(new int[] {nextX, nextY});
                    isVisited[nextX][nextY] = true;
                    count++;
                } else {
                    boolean isUpperCase = Character.isUpperCase(currLocation);

                    // 열쇠(소문자)이면? 해당 지점 빈공간으로 변경하고 Q와 열쇠에 추가
                    // 문(대문자)라면?
                    //  - 열쇠 있으면? 해당 지점 빈공간으로 변경하고 Q에 추가하고 '문'을 기록해둔 Map에 열 수 있는 문이 있는지 확인
                    //  - 열쇠 없으면? 일단 기록해두고, 해당 지점은 벽 취급
                    if (!isUpperCase) {
                        map[nextX][nextY] = '.';
                        location.add(new int[] {nextX, nextY});
                        isVisited[nextX][nextY] = true;
                        keys[currLocation - 'a'] = true;

                        // 키에 해당하는 문을 만난 적 있는지 체크하고, 있으면 Queue에 또 다른 가능성으로 추가함
                        // 5. 특히 현재 소유한 열쇠와 문을 대조할 땐, isUpper로 체크한다
                        Queue<int[]> currDoors = doors.getOrDefault(Character.toUpperCase(currLocation), new ArrayDeque<>());
                        while (!currDoors.isEmpty()) {
                            int[] temp = currDoors.poll();
                            map[temp[0]][temp[1]] = '.';
                            location.add(new int[] {temp[0], temp[1]});
                            isVisited[temp[0]][temp[1]] = true;
                        }
                    } else if (keys[currLocation - 'A']) {
                        map[nextX][nextY] = '.';
                        location.add(new int[] {nextX, nextY});
                        isVisited[nextX][nextY] = true;
                    } else {
                        doors.computeIfAbsent(currLocation, k -> new ArrayDeque<>())
                                .add(new int[] {nextX, nextY});
                    }
                }
            }
        }

        // 6. 그렇게 더이상 이동할 수 없는 지점이 왔을 떄 여태껏 주운 문서 갯수를 출력한다
        return count;
    }


//    static void printWay() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("\n");
//        sb.append("\n");
//
//        sb.append("보유한 키 : ");
//        for (int i = 0; i < 26; i++) {
//            if (keys[i]) {
//                sb.append((char) (i + 'A')).append(" ");
//            }
//        }
//        sb.append("\n");
//
//        for (int row = 0; row < h; row++) {
//            for (int col = 0; col < w; col++) {
//                sb.append(isVisited[row][col] ? "=" : map[row][col]).append(" ");
//            }
//
//            sb.append("\n");
//        }
//
//        System.out.println(sb);
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test = 0 ; test < T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken()) + 2;
            w = Integer.parseInt(st.nextToken()) + 2;

            map = new char[h][w];
            isVisited = new boolean[h][w];

            // 지도 외곽 방문 가능처리
            Arrays.fill(map[0], '.');
            Arrays.fill(map[h - 1], '.');
            for (int i = 1; i < h - 1; i++) {
                map[i][0] = '.';
                map[i][w - 1] = '.';
            }

            // 지도 입력 받기
            for (int row = 0; row < h - 2; row++) {
                String input = br.readLine();
                for (int col = 0; col < w - 2; col++) {
                    map[row + 1][col + 1] = input.charAt(col);
                }
            }

            // 열쇠 기록 (2. 일단 소유한 알파벳 26개 열쇠를 기억하는 boolean[] 배열을 만든다)
            String inputKeys = br.readLine();
            keys = new boolean[26];

            // 키를 가지고 있으면 기록해두기
            if (inputKeys.charAt(0) != '0') {
                for (char temp : inputKeys.toCharArray()) {
                    keys[temp - 'a'] = true;
                }
            }

            // 1. BFS 사용할 것이고, Map은 Char[][]
            sb.append(findMap()).append("\n");
        }

        System.out.println(sb);
    }
}
