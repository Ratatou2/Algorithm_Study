/*
[백준]
1926, 그림

[문제파악]
- 어떤 큰 도화지에 그림이 그려져 있을 때, 그 그림의 개수와, 그 그림 중 넓이가 가장 넓은 것의 넓이를 출력하여라.
- 단, 그림이라는 것은 1로 연결된 것을 한 그림이라고 정의하자.
- 가로나 세로로 연결된 것은 연결이 된 것이고 대각선으로 연결이 된 것은 떨어진 그림이다.
- 그림의 넓이란 그림에 포함된 1의 개수이다.

[입력]
- 첫째 줄에 도화지의 세로 크기 n(1 ≤ n ≤ 500)과 가로 크기 m(1 ≤ m ≤ 500)이 차례로 주어진다.
- 두 번째 줄부터 n+1 줄 까지 그림의 정보가 주어진다. (단 그림의 정보는 0과 1이 공백을 두고 주어지며, 0은 색칠이 안된 부분, 1은 색칠이 된 부분을 의미한다)

[출력]
- 첫째 줄에는 그림의 개수, 둘째 줄에는 그 중 가장 넓은 그림의 넓이를 출력하여라.
- 단, 그림이 하나도 없는 경우에는 가장 넓은 그림의 넓이는 0이다.

[구현방법]
- 입력받으면서 1이면, Q에 넣어두고, Q 돌면서 연결되어있는 곳은 죄다 탐색하고
- 탐색한 값(그림의 넓이) max값 갱신해가면서 돌면 될듯하다
- BFS 문제

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
        private int x, y;

        Node (int x , int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node : (" + x + ", " + y + ")";
        }
    }

    static boolean isOutOfBound(int x, int y) {
        return x < 0 || N <= x || y < 0 || M <= y;
    }

    static void printMap(boolean[][] input) {
        StringBuilder sb = new StringBuilder();

        // 맵 출력하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append((input[i][j] ? 1 : 0)).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[][] paper = new boolean[N][M];
        boolean[][] isVisited = new boolean[N][M];
        Queue<Node> pic = new LinkedList<>();
        int picCount = 0;
        int maxCount = 0;


        // 그림 입력 받기
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < M; col++) {
                paper[row][col] = Integer.parseInt(st.nextToken()) == 1;

                // 그림이면 일단 추가
                if (paper[row][col]) pic.add(new Node(row, col));
            }
        }

        // BFS 탐색 시작
        // 1) 우선은 시작지점이 될 수 있는 모든 것을 확인해봐야한다
        for (Node temp : pic) {
            // 그렇기 때문에 연결되어 있으면 방문처리가 되어있을테니 사전에 체크해주기
            if (isVisited[temp.x][temp.y]) continue;
            Queue<Node> q = new LinkedList<>();
            q.add(new Node(temp.x, temp.y));

            int count = 1;  // 자기자신부터 세면 1부터 시작임

            // 2) 연결된 곳 탐색하기
            while (!q.isEmpty()) {
                Node cur = q.poll();
                isVisited[cur.x][cur.y] = true;

                for (int i = 0; i < 4; i++) {
                    int nextX = cur.x + moveX[i];
                    int nextY = cur.y + moveY[i];

                    if (isOutOfBound(nextX, nextY) || isVisited[nextX][nextY] || !paper[nextX][nextY]) continue;

                    q.add(new Node(nextX, nextY));
                    isVisited[nextX][nextY] = true;
                    count++;  // 이동할 때마다 면적 +1
                }
            }

            maxCount = Math.max(maxCount, count);  // 다 세고 난 뒤에 밖에서 maxCount 갱신해주면 된다.
            picCount++;
        }

        if (picCount == 0) maxCount = 0;

        sb.append(picCount).append("\n").append(maxCount);
        System.out.println(sb);
    }
}