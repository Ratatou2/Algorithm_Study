/*
[백준]
1520, 내리막 길

[문제파악]
- 여행을 떠난 세준이는 지도를 하나 구하였다.
- 이 지도는 아래 그림과 같이 직사각형 모양이며 여러 칸으로 나뉘어져 있다.
- 한 칸은 한 지점을 나타내는데 각 칸에는 그 지점의 높이가 쓰여 있으며, 각 지점 사이의 이동은 지도에서 상하좌우 이웃한 곳끼리만 가능하다.
- 현재 제일 왼쪽 위 칸이 나타내는 지점에 있는 세준이는 제일 오른쪽 아래 칸이 나타내는 지점으로 가려고 한다.
- 그런데 가능한 힘을 적게 들이고 싶어 항상 높이가 더 낮은 지점으로만 이동하여 목표 지점까지 가고자 한다.
- 위와 같은 지도에서는 다음과 같은 세 가지 경로가 가능하다.
- 지도가 주어질 때 이와 같이 제일 왼쪽 위 지점에서 출발하여 제일 오른쪽 아래 지점까지 항상 내리막길로만 이동하는 경로의 개수를 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에는 지도의 세로의 크기 M과 가로의 크기 N이 빈칸을 사이에 두고 주어진다.
- 이어 다음 M개 줄에 걸쳐 한 줄에 N개씩 위에서부터 차례로 각 지점의 높이가 빈 칸을 사이에 두고 주어진다.
- M과 N은 각각 500이하의 자연수이고, 각 지점의 높이는 10000이하의 자연수이다.

[출력]
- 첫째 줄에 이동 가능한 경로의 수 H를 출력한다.
- 모든 입력에 대하여 H는 10억 이하의 음이 아닌 정수이다.

[구현방법]
- 현재 위치보다 낮은 곳만 가면 되고, 상하좌우 다 가능하다면야 뭐 DFS로 길을 탐색하면 될 것 같다

[보완점]
- 답이 나오긴하는데 시간초과 났음
- 메모이제이션이 필요했다 아래 반례 같은 경우 여러가지 경우의 수가 있겠지만, 결국 요지는 가봤던 길을 첨부터 또 가봐야한다는 것
- 이를 위해서 각 위치에 도달할 수 있는 경우의 수를 저장하고 그 값을 이용하는 것이 베스트 케이스
- 이렇게 하면 시간을 줄일 수 있다.. 어렵넹

- 구현방법은 정답을 좀 참고했는데, 코드 중간에 dp[x][y] += DFS(nx, ny); 이부분을 잘 보면 답이 있다
- 현재 위치(dp[x][y])는 (nx, ny) 위치를 DFS 탐색해서 얻은 경우의 수를 더하는 것이다
- 즉 뭔말이냐면, 이 풀이법은 (0, 0)에서부터 하나씩 이동하며 탐색하는 것이 아니라 계속 타고 내려간다
- 그러다가 목적지에 도달하면 해당 지점을 1로 잡고 거기서부터 이동할 수 있는 지점을 하나씩 탐색하고 기록해서 데이터를 누적하는 꼴이다
- 역방향 탐지인셈 진짜 어렵다잉 사람들 정말 똑똑한 것 같음
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map, dp;
    static int[] move_X = {-1, 0, 1, 0};
    static int[] move_Y = {0, 1, 0, -1};

    static boolean isOutOfBound(int x, int y) {
        return x < 0 || N <= x || y < 0 || M <= y;
    }

//    static void printDP() {
//        StringBuilder sb = new StringBuilder();
//
//        for (int row = 0; row < N; row++) {
//            for (int col = 0; col < M; col++) {
//                sb.append(dp[row][col] == -1 ? "X " : dp[row][col] + " ");
//            }
//            sb.append("\n");
//        }
//
//        System.out.println(sb);
//    }

    static int DFS(int x, int y) {
        // 탐색 종료 조건 달성 시 하나의 경로를 추가 (목적지 도착한 경우)
        if (x == N - 1 && y == M - 1) {
            return 1;
        }

        // 이미 계산된 경로가 있으면 반환
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        // 초기화
        dp[x][y] = 0;

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + move_X[i];
            int ny = y + move_Y[i];

            // map 범위 밖으로 나갔거나, 현재 높이보다 높거나 같은 곳이면 패스 (항상 더 낮은 곳으로 가고 싶다했음)
            if (isOutOfBound(nx, ny) || map[x][y] <= map[nx][ny]) continue;

            // 유효한 경로니 재귀 호출 (목적지에서부터 현 위치로 올 수 있는 경우의 수를 따지는 것이다)
            dp[x][y] += DFS(nx, ny);
        }

        return dp[x][y];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];

        // map 입력받기
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                dp[row][col] = -1;
            }
        }

        System.out.println(DFS(0, 0));
    }
}
