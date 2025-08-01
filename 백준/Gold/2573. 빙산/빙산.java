import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] isVisited;

    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};

    // isVisited 초기화
    static void resetIsVisited() {
        for (boolean[] temp : isVisited) {
            Arrays.fill(temp, false);
        }
    }

    // 맵 바깥으로 나갔는지 체크
    static boolean isOutOfBound(int x, int y) {
        return x < 0 || N <= x || y < 0 || M <= y;
    }

    // 빙산 녹이기
    static void meltIceberg() {
        int[][] melt = new int[N][M];

        // 전체 맵 탐색하며 녹는 양 계산
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (map[x][y] == 0) continue;

                int count = 0;
                for (int i = 0; i < 4; i++) {
                    int nx = x + moveX[i];
                    int ny = y + moveY[i];
                    if (isOutOfBound(nx, ny)) continue;
                    if (map[nx][ny] == 0) count++;
                }
                melt[x][y] = count;
            }
        }

        // 녹이기 적용
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (map[x][y] > 0) {
                    map[x][y] = Math.max(map[x][y] - melt[x][y], 0);
                }
            }
        }
    }

    // 빙산끼리 연결되어있는지 체크
    static boolean isConnected() {
        resetIsVisited();
        int totalIce = 0;
        boolean foundStart = false;
        int startX = 0, startY = 0;

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (map[x][y] > 0) {
                    totalIce++;
                    if (!foundStart) {
                        startX = x;
                        startY = y;
                        foundStart = true;
                    }
                }
            }
        }

        if (totalIce == 0) return true; // 다 녹음

        int connected = BFS(startX, startY);
        return connected == totalIce;
    }

    // BFS로 연결된 빙산 수 세기
    static int BFS(int sx, int sy) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sx, sy});
        isVisited[sx][sy] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + moveX[i];
                int ny = y + moveY[i];

                if (isOutOfBound(nx, ny) || isVisited[nx][ny] || map[nx][ny] == 0) continue;

                isVisited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isVisited = new boolean[N][M];
        int year = 0;

        // map 입력 받기
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            if (!isConnected()) {
                System.out.println(year);
                return;
            }

            meltIceberg();
            year++;

            // 다 녹은 경우
            boolean allMelted = true;
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    if (map[x][y] > 0) {
                        allMelted = false;
                        break;
                    }
                }
                if (!allMelted) break;
            }

            if (allMelted) {
                System.out.println(0);
                return;
            }
        }
    }
}