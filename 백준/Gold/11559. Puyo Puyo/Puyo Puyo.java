

/*
[백준]
11559, 뿌요 뿌요

[문제파악]
뿌요뿌요의 룰은 다음과 같다.
필드에 여러 가지 색깔의 뿌요를 놓는다.
뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.
뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다. 이때 1연쇄가 시작된다.
뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.
아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어난다.
터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.
남규는 최근 뿌요뿌요 게임에 푹 빠졌다. 이 게임은 1:1로 붙는 대전게임이라 잘 쌓는 것도 중요하지만, 상대방이 터뜨린다면 연쇄가 몇 번이 될지 바로 파악할 수 있는 능력도 필요하다.
하지만 아직 실력이 부족하여 남규는 자기 필드에만 신경 쓰기 바쁘다.
상대방의 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산하여 남규를 도와주자!

[입력]
총 12개의 줄에 필드의 정보가 주어지며, 각 줄에는 6개의 문자가 있다.
이때 .은 빈공간이고 .이 아닌것은 각각의 색깔의 뿌요를 나타낸다.
R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑이다.
입력으로 주어지는 필드는 뿌요들이 전부 아래로 떨어진 뒤의 상태이다.

[출력]
현재 주어진 상황에서 몇연쇄가 되는지 출력한다. 하나도 터지지 않는다면 0을 출력한다.

[구현방법]
- 아래 순서대로 구현해야할듯 하다
    1) 현재 맵에서 (0,0)부터 탐색하며 인접한 같은 색 4개 이상으로 터질 것들 파악
    2) 터뜨릴 조건(상하좌우 같은 색 4개 이상)을 만족하면, 해당 맵은 전부 '.'으로 초기화
    3) 동시 폭발이 조건이므로 현 상태에서 터뜨릴 수 있는 모든 뿌요를 터뜨림
    4) 이제 역순으로 (10, 5)에서 부터 아래 방향으로만 탐색하며 바닥을 만나거나, 뿌요를 만날 때까지 내려감
    5) 이동한 것들은 바닥부분에 갱신, 원래 위치는 '.'으로 대체
    6) 1~5까지를 계속 반복, 위 로직에서 연쇄가 1번도 없는 경우 중단
    7) 최종 연쇄 횟수를 출력하고 종료

[보완점]
- 5번 부분 개선할 수 있는게 Queue를 하나 만들고 열(col)단위로 반복문 돌리면서 '.'이 아닌 것들을 Q에 넣는다
- 그리고는 열 갯수만큼 .을 채우고, 가장 밑바닥에 해당하는 열부터 Q에서 뽑아낸 값으로 갱신하면 쉽고 빠르게 할 수 있음
*/

import java.io.*;
import java.util.*;

public class Main {
    static int[] moveX = {0, 1, 0, -1};
    static int[] moveY = {-1, 0, 1, 0};

    static int boom;
    static Character[][] map;
    static boolean[][] isVisited;

    static boolean findSameColor (int x, int y, char currColor) {
        // 2) 터뜨릴 조건(상하좌우 같은 색 4개 이상)을 만족하면, 해당 맵은 전부 '.'으로 초기화
        Queue<int[]> q = new ArrayDeque<>();
        List<int[]> sameColorLocation = new ArrayList<>();  // 기록용 리스트
        q.add(new int[] {x, y});
        sameColorLocation.add(new int[] {x, y});
        isVisited[x][y] = true;  // 방문처리

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            // 사방 탐색
            for (int i = 0; i < 4; i++) {
                int nextX = curr[0] + moveX[i];
                int nextY = curr[1] + moveY[i];

                // 맵의 범위를 넘어가거나, 아무것도 없거나, 현재 색과 같지 않거나, 방문한적 있으면 으면 패스
                if (nextX < 0 || nextY < 0 || 12 <= nextX || 6 <= nextY
                        || map[nextX][nextY] == '.'
                        || map[nextX][nextY] != currColor
                        || isVisited[nextX][nextY]) continue;

                // 같은 색이면 방문처리, 기록해두고 Q와 기록용 리스트에 삽입
                isVisited[nextX][nextY] = true;
                q.add(new int[] {nextX, nextY});
                sameColorLocation.add(new int[] {nextX, nextY});
            }
        }

        // 모든 탐색이 끝나고, 같은 색상이 4개 미만이면 더 볼 것 없이 그대로 종료
        if (sameColorLocation.size() < 4) {
            // 방문처리 해제 (더 효율적으로 구사하는 방법이 없나 ㅇㅅㅇ...)
            for (int[] temp : sameColorLocation) {
                isVisited[temp[0]][temp[1]] = false;
            }

            return false;
        }
        
        // 3) 동시 폭발이 조건이므로 현 상태에서 터뜨릴 수 있는 모든 뿌요를 터뜨림
        // 4개 이상이면 전부 터뜨리고, 해당 지역 초기화 + 연쇄 카운트
        for (int[] temp : sameColorLocation) {
            map[temp[0]][temp[1]] = '.';
            isVisited[temp[0]][temp[1]] = false;
        }

        return true;
    }

    static void movePuyo() {
        Queue<Character> cols = new ArrayDeque<>();
        
        // 4) 이제 역순으로 아래 방향으로만 탐색하며 바닥을 만나거나, 뿌요를 만날 때까지 내려감
        // 밑에서부터 확인해야하기 때문에 역순 탐지 필요
        for (int col = 6 - 1; 0 <= col; col--) {
            for (int row = 12 - 1; 0 <= row; row--) {
                if (map[row][col] == '.') continue;

                cols.add(map[row][col]);
            }

            // 내려올게 없으면 그대로 종료
            if (cols.isEmpty()) continue;

            // 5) 이동한 것들은 바닥 부분에 갱신, 원래 위치는 '.'으로 대체
            for (int row = 12 - 1; 0 <= row; row--) {
                if (!cols.isEmpty()) map[row][col] = cols.poll();
                else map[row][col] = '.';
            }
        }
    }

    static StringBuilder printMap () {
        StringBuilder sb = new StringBuilder();
        sb.append("===================\n");
        for (int row = 0; row < 12; row++) {
            sb.append(Arrays.toString(map[row])).append("\n");
        }

        return sb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new Character[12][6];
        isVisited = new boolean[12][6];
        boom = 0;  // 연쇄 횟수 카운트

        // map 입력 받기
        for (int row = 0; row < 12; row++) {
            String input = br.readLine();

            for (int col = 0; col < 6; col++) {
                map[row][col] = input.charAt(col);
            }
        }

        // 한번이라도 터진적이 있는지 체크
        boolean isBoom = false;

        // 한번 싹 훑는 것을 '매번' 해야한다 (until 아무것도 바뀌지 않을 때까지
        while (true) {
            isBoom = false;

            // 1) 현재 맵에서 (0,0)부터 탐색하며 인접한 같은 색 4개 이상으로 터질 것들 파악
            for (int row = 0; row < 12; row++) {
                for (int col = 0; col < 6; col++) {
                    if (map[row][col] == '.') continue;  // 아무것도 없으면 패스
                    boolean isExist = findSameColor(row, col, map[row][col]);

                    // 한번이라도 터진 적이 있다면 뿌요들을 움직인 다음 한번 더 탐색해 봐야한다
                    if (isExist) isBoom = true;
                }
            }

            // 지운게 있으면, 사라진 공간만큼 뿌요들이 움직여야 함
            // 지운게 없다면, 그대로 종료
            if (!isBoom) break;
            else {
                movePuyo();
                boom++;
            };
        }

        System.out.println(boom);
    }
}
