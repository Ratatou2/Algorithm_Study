/*
[백준]
7576, 토마토

[문제파악]
- 철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다.
- 토마토는 아래의 그림과 같이 격자 모양 상자의 칸에 하나씩 넣어서 창고에 보관한다.
- 창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다.
- 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다.
- 하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 방향에 있는 토마토를 의미한다.
- 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다.
- 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지, 그 최소 일수를 알고 싶어 한다.

- 토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라.
- 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.


[입력]
- 첫 줄에는 상자의 크기를 나타내는 두 정수 M,N이 주어진다.
    - M은 상자의 가로 칸의 수,
    - N은 상자의 세로 칸의 수를 나타낸다.
    - 단, 2 ≤ M,N ≤ 1,000 이다.
- 둘째 줄부터는 하나의 상자에 저장된 토마토들의 정보가 주어진다.
- 즉, 둘째 줄부터 N개의 줄에는 상자에 담긴 토마토의 정보가 주어진다.
- 하나의 줄에는 상자 가로줄에 들어있는 토마토의 상태가 M개의 정수로 주어진다.
- 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
- 토마토가 하나 이상 있는 경우만 입력으로 주어진다.

[출력]
- 여러분은 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다.
- 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.

[구현방법]
- 이거는 그냥 BFS + 사방탐색으로 풀면 됨!
- 입력 받을 때 안익은 토마토 갯수 전부 count 해두고 q가 비었는데도 안익은게 남아있다면, 못 익는 구조니까 -1 출력하면 될듯하다
- 근데 이거 level별 관리 어떻게 하더라..? (ex. 1일차에는 1일차에 추가된 애들만 넣고 빼고, 2일차에는 2일차에 추가된 애들만 넣고 빼고)
    - 그냥 level을 담당해줄 친구를 하나 만들면 되겄는디
- 아 그리고 방문처리도 해야함
- 개인적으로는 덜 익은 토마토를 미리 카운트 해놨다가 다 끝났을 떄 갯수를 확인해서 덜익은게 있으면 -1을 출력하려고 했음
- 근데 이게 계산이 잘못되는 지점도 있고 해가지고 흠...

[보완점]

[입력값]
6 4
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 1


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};
    static int[][] farm;
    static boolean[][] isVisited;

    static class field {
        int x, y, count;

        field (int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public String toString() {
            return "[(" + x + ", " + y + ") : " + count + "]";
        }
    }

    static void farmToString() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                sb.append(farm[row][col]).append(" ");
            }
            sb.append("\n");
        }
        sb.append("==========================");

        System.out.println(sb);

    }

    static void printQueue(Queue<field> input) {
        StringBuilder sb = new StringBuilder();
        for (field temp : input) sb.append(temp.toString());

        System.out.println(sb);
    }

    static boolean isOutOfBound(int x, int y) {
        return x < 0 || N <= x || y < 0 || M <= y;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        farm = new int[N][M];
        isVisited = new boolean[N][M];
        Queue<field> whereIsTomato = new LinkedList<>();
        int maxDays = 0;
        int totalTomato = 0;

        // Map 입력받고, 토마토 위치 Queue에 저장
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < M; col++) {
                farm[row][col] = Integer.parseInt(st.nextToken());
                if (farm[row][col] == 1) {
                    whereIsTomato.add(new field(row, col, 0));
                    isVisited[row][col] = true;
                } else if (farm[row][col] == 0) totalTomato++;
            }
        }

//        farmToString();
//        printQueue(whereIsTomato);
//        System.out.println(totalTomato);

        // Queue 돌면서 토마토 위치에서 번져 나가기 시작
        while (!whereIsTomato.isEmpty()) {
            field cur = whereIsTomato.poll();
            maxDays = Math.max(maxDays, cur.count);

            // 익은 토마토 있는 곳에서부터 사방 탐색 시작
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + moveX[i];
                int nextY = cur.y + moveY[i];

                // 맵 밖으로 넘쳤으면, 다음 탐색
                if (isOutOfBound(nextX, nextY) || isVisited[nextX][nextY]) continue;

                // 설익은 토마토가 있으면 익을테니까 Queue에 넣음
                if (farm[nextX][nextY] == 0) {
                    // count 값을 + 1하고 나서 넣어야 함을 잊지말자 (= cur.count + 1)
                    // 초기엔 ++cur.count를 해버렸는데 이렇게 해서 level 관리가 안됐던 것;;
                    // 다른 놈들도 영향을 받으니 +1을 해서 집어넣는 방법으로 해야한다
                    whereIsTomato.add(new field(nextX, nextY, cur.count + 1));
                    isVisited[nextX][nextY] = true;
                    farm[nextX][nextY] = 1;  // 익음 처리
                    totalTomato--;
                }
            }
        }

        // 덜 익은 토마토가 있다는 것은 익힐 수 없는 곳이 있다는 의미니까 -1 출력
        System.out.println(0 == totalTomato ? maxDays : -1);
    }
}