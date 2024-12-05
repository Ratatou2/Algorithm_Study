/*
[백준]
4963, 섬의 개수

[문제파악]
- 정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다.
- 섬의 개수를 세는 프로그램을 작성하시오.

- 한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다.
- 두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다.
- 지도는 바다로 둘러싸여 있으며, 지도 밖으로 나갈 수 없다.

[입력]
- 입력은 여러 개의 테스트 케이스로 이루어져 있다.
- 각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다.
- w와 h는 50보다 작거나 같은 양의 정수이다.
- 둘째 줄부터 h개 줄에는 지도가 주어진다.
- 1은 땅, 0은 바다이다.
- 입력의 마지막 줄에는 0이 두 개 주어진다.

[출력]
- 각 테스트 케이스에 대해서, 섬의 개수를 출력한다.

[구현방법]
- 일단 BFS로 풀어야겠다
- 방문 처리용 배열도 하나 필요하다
- 전체 for문 돌면서 방문처리된 적 없으면 거기서부터 BFS로 퍼져나가서 섬 하나로 count 해야할듯하다

[보완점]
- 메모리 초과가 났음
- Q에서 중복된걸 처리하는게 없었음 방문처리를 먼저하고 큐에 넣어줘야 큐가 무한히 길어지는 것을 막을 수 있다
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int w, h, IslandCount;

    static int[][] mapOfIsland;
    static boolean[][] isVisited;

    static int[] move_x = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] move_y = {0, -1, -1, -1, 0, 1, 1, 1};

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    // 경계선 밖으로 갔는지 체크
    static boolean isOutOfBound(int x, int y) {
        return x < 0 || w <= x || y < 0 || h <= y;
    }

    // 지도 출력 (인풋 체크용)
    static void toString(int[][] input) {
        StringBuilder sb = new StringBuilder();
        sb.append("==================" + "\n");

        for (int i = 0; i < input.length; i++) {
            sb.append(Arrays.toString(input[i])).append("\n");
        }

        sb.append("==================");
        System.out.println(sb);
    }

    // 방문처리 지도 출력 (아웃풋 체크용)
    static void checkTheMapOfVisited () {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (isVisited[x][y]) sb.append("⬜️");
                else sb.append("⬛️");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void BFS(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        isVisited[y][x] = true;  // 방문 처리 (y는 행, x는 열)

        q.add(new Node(x, y));

        while (!q.isEmpty()) {
            Node curr = q.poll();

            // 8방향 탐색 (대각선도 갈 수 있기 때문임)
            for (int i = 0; i < 8; i++) {
                int nx = curr.x + move_x[i];
                int ny = curr.y + move_y[i];

                // 맵 범위 안이고, 방문한 적 없고, 땅이면 Queue에 추가
                if (!isOutOfBound(nx, ny) && !isVisited[ny][nx] && mapOfIsland[ny][nx] == 1) {
                    isVisited[ny][nx] = true;
                    q.add(new Node(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 반복
        while (true) {
            IslandCount = 0;
            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            // 마지막에 0 0 두개 입력되면 종료
            if (w == 0 && h == 0) break;

            mapOfIsland = new int[h][w];  // 섬 기록할 지도
            isVisited = new boolean[h][w];  // 방문 처리

            // 지도 입력 받기
            for (int y = 0; y < h; y++) {
                st = new StringTokenizer(br.readLine());

                for (int x = 0; x < w; x++) {
                    mapOfIsland[y][x] = Integer.parseInt(st.nextToken()); // y가 행, x가 열
                }
            }

            // toString(mapOfIsland);

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    // 땅이고 방문한 적 없으면 BFS 진행
                    if (mapOfIsland[y][x] == 1 && !isVisited[y][x]) { // y가 행, x가 열
                        BFS(x, y);
                        IslandCount++;  // BFS 탐색을 한번 끝냈다는 것은 현재 땅에서 갈 수 있는 모든 땅을 갔다는 의미임 (그렇기 때문에 +1)
                    }
                }
            }

            sb.append(IslandCount).append("\n");
        }

        System.out.println(sb);
    }
}