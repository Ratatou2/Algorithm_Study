/*
[백준]
2636, 치즈

[문제파악]
- 아래 <그림 1>과 같이 정사각형 칸들로 이루어진 사각형 모양의 판이 있고, 그 위에 얇은 치즈(회색으로 표시된 부분)가 놓여 있다.
- 판의 가장자리(<그림 1>에서 네모 칸에 X친 부분)에는 치즈가 놓여 있지 않으며 치즈에는 하나 이상의 구멍이 있을 수 있다.

- 이 치즈를 공기 중에 놓으면 녹게 되는데 공기와 접촉된 칸은 한 시간이 지나면 녹아 없어진다.
- 치즈의 구멍 속에는 공기가 없지만 구멍을 둘러싼 치즈가 녹아서 구멍이 열리면 구멍 속으로 공기가 들어가게 된다.
- <그림 1>의 경우, 치즈의 구멍을 둘러싼 치즈는 녹지 않고 ‘c’로 표시된 부분만 한 시간 후에 녹아 없어져서 <그림 2>와 같이 된다.

- <그림 3>은 원래 치즈의 두 시간 후 모양을 나타내고 있으며,
- 남은 조각들은 한 시간이 더 지나면 모두 녹아 없어진다.
- 그러므로 처음 치즈가 모두 녹아 없어지는 데는 세 시간이 걸린다.
- <그림 3>과 같이 치즈가 녹는 과정에서 여러 조각으로 나누어 질 수도 있다.

- 입력으로 사각형 모양의 판의 크기와 한 조각의 치즈가 판 위에 주어졌을 때, 공기 중에서 치즈가 모두 녹아 없어지는 데 걸리는 시간과 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에는 사각형 모양 판의 세로와 가로의 길이가 양의 정수로 주어진다.
- 세로와 가로의 길이는 최대 100이다.
- 판의 각 가로줄의 모양이 윗 줄부터 차례로 둘째 줄부터 마지막 줄까지 주어진다.
- 치즈가 없는 칸은 0, 치즈가 있는 칸은 1로 주어지며 각 숫자 사이에는 빈칸이 하나씩 있다.

[출력]
- 첫째 줄에는 치즈가 모두 녹아서 없어지는 데 걸리는 시간을 출력하고, 둘째 줄에는 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 출력한다.

[구현방법]
- 치즈 구멍이 있는걸 도통 어떻게 예외처리해서 답을 구해야 하는지 모르겠어서 힌트를 좀 봤다
- 문제 조건에 따르면 최외곽 지역은, 가장 바깥 라인은 치즈가 없다. 즉, 0,0은 무조건 공기이고 여기서부터 공기를 탐색해 들어가면 되는 것
    - BFS 탐색을 진행하다가 공기를 만나면 Q에 추가하고, 치즈를 만나면 더 진행할 필요 없이 그 부분을 녹이면 된다 (녹은 부분은 그 다음 BFS 때, 공기가 될 것이다)
    - 그렇게 진행하다가 전체 치즈 갯수가 0이면 끝내고 그게 아니라면 BFS를 또 진행한다

[보완점]


[예제]
13 12
0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1 1 0 0 0
0 1 1 1 0 0 0 1 1 0 0 0
0 1 1 1 1 1 1 0 0 0 0 0
0 1 1 1 1 1 0 1 1 0 0 0
0 1 1 1 1 0 0 1 1 0 0 0
0 0 1 1 0 0 0 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 1 1 1 1 1 1 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0

[예제 답안]
3
5
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] move_x = {-1, 0, 1, 0};
    static int[] move_y = {0, 1, 0, -1};

    static int row, col, totalCheese, hour;
    static boolean[][] board, isVisited;

    static boolean outOfBound (int x, int y) {
        return x < 0 || row <= x || y < 0 || col <= y;
    }

    static int countCheese() {
        int count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j]) count++;
            }
        }

        return count;
    }

    static void printBoard() {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        sb.append("\n===============================\n");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sb.append(board[i][j] ? "■ " : "□ ");
                if (board[i][j]) count++;
            }

            sb.append("\n");
        }
        sb.append("\n===============================\n");

        System.out.println(count);
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        board = new boolean[row][col];
        isVisited = new boolean[row][col];
        totalCheese = 0;
        hour = 0;

        // board 입력받기
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                String curr = st.nextToken();
                if (curr.equals("1")) totalCheese++;  // 전체 치즈 갯수 카운트
                board[i][j] = curr.equals("1");
            }
        }

        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> meltingCheese = new LinkedList<>();
        q.add(new int[]{0, 0});

        int recordCheeseCount = totalCheese;
        while (0 < totalCheese) {

            while (!q.isEmpty()) {
                int[] cur = q.poll();

                int x = cur[0];
                int y = cur[1];

                isVisited[x][y] = true;  // 방문처리

                for (int i = 0; i < 4; i++) {
                    int nx = x + move_x[i];
                    int ny = y + move_y[i];

                    // 맵 범위를 벗어났거나, 방문했던 공기면 contiue;
                    if (outOfBound(nx, ny) || isVisited[nx][ny]) continue;

                    // 치즈면 녹을 Q에 추가, 공기면 q에 추가
                    if (board[nx][ny]) meltingCheese.add(new int[]{nx, ny});
                    else {
                        q.add(new int[]{nx, ny});
                        isVisited[nx][ny] = true;  // 방문처리
                    }
                }
            }

            while (!meltingCheese.isEmpty()) {
                int[] cur = meltingCheese.poll();
                int x = cur[0];
                int y = cur[1];

                if (!board[x][y]) continue;
                board[x][y] = false;  // 치즈 녹음 처리
                totalCheese--;  // 녹은 치즈는 갯수 - 처리

                q.add(new int[]{x, y});
            }

            if (totalCheese == 0) {
                hour++;
                break;
            };

            // 이번 loop를 타서 치즈가 전혀 없게 된다면 마지막에 세둔 치즈 갯수가 모두 녹기 전 갯수이다
            recordCheeseCount = countCheese();
            hour++;

        }

        System.out.println(hour);  // 마지막 한개까지 다 녹아버리려면 한시간 더 있어야하기 때문에 hour + 1
        System.out.println(recordCheeseCount);
    }
}