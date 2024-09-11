/*
[백준]
2178, 미로탐색

[문제파악]
- N×M크기의 배열로 표현되는 미로가 있다.
    1	0	1	1	1	1
    1	0	1	0	1	0
    1	0	1	0	1	1
    1	1	1	0	1	1

    - 미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다.
    - 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오.
    - 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.
- 위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

[입력]
- 첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다.
- 다음 N개의 줄에는 M개의 정수로 미로가 주어진다.
- 각각의 수들은 붙어서 입력으로 주어진다.

[출력]
- 첫째 줄에 지나야 하는 최소의 칸 수를 출력한다.
- 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.

[구현방법]
- 최단 거리면 DFS가 적격이다 (땡!!땡!!떙!!)
    - 가중치가 없는, 그래프의 최단 거리 : BFS 사용
    - 가중치가 다르고, 음수간선이 없을 땐 : 다익스트라
- DFS로 풀면 시간 초과... BFS로 해야한다
- 스스로 들었던 의문은, 아니 그럼 교차지점에서 두 경우의 수가 만났을 때, 이미 방문한 케이스가 있다면?
    - 그런 경우는 오히려 상관없는게 하나의 정점에 이미 먼저 도착한 경우의 수가 있다면 그 경우의 수는 나보다 더 빠른 경우의 수이다.
    - 그러니 최단 경로를 찾는 경우에 나보다 이미 방문한 케이스가 있다면 현 케이스는 무시해도 되는 것이다.
- 결국 정점까지 빠르게 도달하는 경우의 수가 최단 거리인 셈이다

[보완점]

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

    static class Node {
        int x, y, count;

        Node (int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    // 범위 밖으로 나갔는지 확인하는 코드
    static boolean isOutOfBound(int x, int y) {
        return x < 0 || N <= x || y < 0 || M <= y;
    }

    // Map 출력 코드
    static void toStringMap(boolean[][] map) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                sb.append(map[i][j] ? '1' : '0');
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[N][M];
        boolean[][] isVisited = new boolean[N][M];

        // Map 입력 받기
        for (int row = 0; row < N; row++) {
            String temp = br.readLine();

            for (int col = 0; col < M; col++) {
                map[row][col] = temp.charAt(col) == '1';
            }
        }

//        toStringMap(map);

        // 시작 지점은 (0, 0), 도착지는 (N-1, N-1)
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1));  // 시작 지점도 카운트해야해서 기본 count 셋팅값은 1이다

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 도착지에 도착했다면, count 출력하고 끝
            if (cur.x == N - 1 && cur.y == M - 1) {
                System.out.println(cur.count);
                return;
            }

            // 4방탐색
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + moveX[i];
                int nextY = cur.y + moveY[i];

                // map 밖으로 나갔으면 더 볼 것 없음
                if (isOutOfBound(nextX, nextY)) continue;

                // 갈 수 있는 곳이고, 방문한 적 없다면 탐색할 수 있게 q에 넣는다
                if (map[nextX][nextY] && !isVisited[nextX][nextY]) {
                    isVisited[nextX][nextY] = true;
                    q.add(new Node(nextX, nextY, cur.count + 1));
                }
            }
        }
    }
}