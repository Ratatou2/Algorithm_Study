/*
[SWEA]
B형 09 파핑파핑 지뢰찾기

[문제파악]
- 파핑 파핑 지뢰 찾기’라는 유명한 게임이 있다. 이 게임은 RXC 크기의 표를 이용하는 게임인데, 표의 각 칸에는 지뢰가 있을 수도 있고 없을 수도 있다.
- 표의 각 칸을 클릭했을 때, 그 칸이 지뢰가 있는 칸이라면 ‘파핑 파핑!’이라는 소리와 함께 게임은 끝난다.
- 지뢰가 없는 칸이라면 변이 맞닿아 있거나 꼭지점이 맞닿아 있는 최대 8칸에 대해 몇 개의 지뢰가 있는지가 0에서 8사이의 숫자로 클릭한 칸에 표시된다.
- 만약 이 숫자가 0이라면 근처의 8방향에 지뢰가 없다는 것이 확정된 것이기 때문에 그 8방향의 칸도 자동으로 숫자를 표시해 준다.
- 실제 게임에서는 어떤 위치에 지뢰가 있는지 알 수 없지만, 이 문제에서는 특별히 알 수 있다고 하자.
- 지뢰를 ‘*’로, 지뢰가 없는 칸을 ‘.’로, 클릭한 지뢰가 없는 칸을 ‘c’로 나타냈을 때 표가 어떻게 변화되는지 나타낸다.
- 세 번째 예에서는 0으로 표시 될 칸들과 이와 인접한 칸들이 한 번의 클릭에 연쇄적으로 숫자가 표시된 것을 볼 수 있다.
- 파핑 파핑 지뢰 찾기를 할 때 표의 크기와 표가 주어질 때, 지뢰가 있는 칸을 제외한 다른 모든 칸의 숫자들이 표시되려면 최소 몇 번의 클릭을 해야 하는지 구하는 프로그램을 작성하라.

[입력]
- 첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
- 각 테스트 케이스의 첫 번째 줄에 하나의 정수 N(1 ≤ N ≤ 300) 이 주어진다. 이는 지뢰 찾기를 하는 표의 크기가 N*N임을 나타낸다.
- 다음 N개의 줄의 i번째 줄에는 길이가 N인 문자열이 주어진다.
- 이 중 j번째 문자는 표에서 i번째 행 j번째 열에 있는 칸이 지뢰가 있는 칸인지 아닌지를 나타낸다.
- ‘*’이면 지뢰가 있다는 뜻이고, ‘.’이면 지뢰가 없다는 뜻이다. ‘*’와 ‘.’외의 다른 문자는 입력으로 주어지지 않는다.

[출력]
- 각 테스트 케이스마다 ‘#x’(x는 테스트케이스 번호를 의미하며 1부터 시작한다)를 출력하고,
- 최소 몇 번의 클릭을 해야 지뢰가 없는 모든 칸에 숫자가 표시될 것인지 출력한다.

[구현방법]
- 일단 기본적으로, 입력을 받을 때, * 좌표를 다 저장해둔다.
- 그리고 해당 * 좌표들을 하나씩 돌면서, 해당 좌표 기준 8방에 +1을 해준다. (기본 셋팅값은 죄다 0임)
- 그렇게 다 숫자 셋팅 해두고 0인 곳을 누르면 8방 탐색(BFS)을 하면서 0을 다 뒤집는 느낌으로 진행
- 근데 사실 이러면 칸 주변에 지뢰가 몇개나 있냐는 중요한게 아니다. 어차피 지뢰 있으면 못 가니까
- 재귀로 푸니까 뭔가 꼬였는지 답이 제대로 안나옴.. BFS로 해봅시다
- 최소횟수 구하려면 0부터 클릭하게 한다음 방문 안한 8(지뢰근처) 1개당 +1 해서 click 수 count해야할 것 같다

[보완점]


[입력]
2
3
..*
..*
**.
5
..*..
..*..
.*..*
.*...
.*...


[출력]
#1 2
#2 8
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] gameMap;
    static boolean[][] isVisited;
    static Queue<Node> mines;
    static Queue<Node> zero;

    // 8방 탐색
    static int[] moveX = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] moveY = new int[]{0, 1, 1, 1, 0, -1, -1, -1};

    // 지뢰 저장하기 위한 클래스
    static class Node {
        int x, y;

        public Node (int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }
    }


    // 주변 0인 것들까지 다 뒤집기
    static void flipEveryConnectedZero(int x, int y) {
        isVisited[x][y] = true;

        Queue<Node> nodes = new ArrayDeque<>();
        nodes.add(new Node(x, y));

        while (!nodes.isEmpty()) {
            Node currNode = nodes.poll();
            int curX = currNode.x;
            int curY = currNode.y;

            for (int dir = 0; dir < moveX.length; dir++) {
                int nextX = curX + moveX[dir];
                int nextY = curY + moveY[dir];

                // 맵 밖을 벗어났거나, 방문했거나, 지뢰면 패스
                if (outOfBound(nextX, nextY) || isVisited[nextX][nextY] || gameMap[nextX][nextY] == 1) continue;

                isVisited[nextX][nextY] = true;
                if (gameMap[nextX][nextY] == 0) {
                    nodes.add(new Node(nextX, nextY));
                }
            }
        }
    }

    // 지뢰 주변에 마킹하는 메서드
    static void marking8Way(int x, int y) {
        for (int dir = 0; dir < moveX.length; dir++) {
            int nextX = x + moveX[dir];
            int nextY = y + moveY[dir];

            // gameMap의 범위를 벗어나느지 체크
            // 내부에 있고, 0이면 (일반 지역이면) 마킹하기
            if (!outOfBound(nextX, nextY) && gameMap[nextX][nextY] == 0) {
                gameMap[nextX][nextY] = 8;
            }
        }
    }

    // Map 범위 밖을 벗어났는지 확인하는 메서드
    static boolean outOfBound (int x, int y) {
        return x < 0 || N <= x || y < 0 || N <= y;
    }

    // Map 출력용 메서드
    static void printMap (int[][] map) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < map.length; i++) {
            sb.append(Arrays.toString(map[i])).append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            gameMap = new int[N][N];
            isVisited = new boolean[N][N];
            int clicks = 0;

            mines = new ArrayDeque<>();
            zero = new ArrayDeque<>();

            for (int row = 0; row < N; row++) {
                String input = br.readLine();

                for (int column = 0; column < N; column++) {
                    // 지뢰 발견하면
                    if (input.charAt(column) == '*') {
                        gameMap[row][column] = 1;  // 맵에 지뢰표기
                        isVisited[row][column] = true;  // 지뢰는 미리 방문처리
                        mines.add(new Node(row, column));  // 지뢰 Q에 추가
                    } else zero.add(new Node(row, column));
                }
            }


            // 1) 지뢰 기준으로 주변 셀들 탐색해서 "8" 표기해두기 (int[][] 여서 8로 했을 뿐, 숫자 8에 큰 의미는 없다)
            for (Node currentMine : mines) {
                marking8Way(currentMine.x, currentMine.y);
            }

            // 2) 아직 0인 지점부터 탐색 시작해서 click 수를 최소화 해야한다
            for (Node currentZeor : zero) {
                int zeroX = currentZeor.x;
                int zeroY = currentZeor.y;

                // 평범한 곳이면서, 아직 방문하지 않은 곳이면 뒤집기 시작
                if (gameMap[zeroX][zeroY] == 0 && !isVisited[zeroX][zeroY]) {
                    flipEveryConnectedZero(zeroX, zeroY);
                    clicks++;
                }
            }

            // 3) 그 뒤로 맵 순차 탐색하면서 0을 만나면 8방 탐색하는 기능으로 isVisited 할 수 있는 곳 까지 다 해두기
            for (int row = 0; row < N; row++) {
                for (int column = 0; column < N; column++) {
                    int currentTile = gameMap[row][column];

                    // 방문한적 있으면 패스
                    if (isVisited[row][column]) continue;

                    // 방문하지 않은 지뢰 근처면 뒤집기 시전
                    if (currentTile == 8 && !isVisited[row][column]) {
                        isVisited[row][column] = true;
                        clicks++;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(clicks).append("\n");
        }

        System.out.println(sb);
    }
}