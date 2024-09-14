/*
[백준]
14940, 쉬운 최단거리

[문제파악]
- 지도가 주어지면 모든 지점에 대해서 목표지점까지의 거리를 구하여라.
- 문제를 쉽게 만들기 위해 오직 가로와 세로로만 움직일 수 있다고 하자.

[입력]
- 지도의 크기 n과 m이 주어진다.
- n은 세로의 크기, m은 가로의 크기다.(2 ≤ n ≤ 1000, 2 ≤ m ≤ 1000)
- 다음 n개의 줄에 m개의 숫자가 주어진다.
- 0은 갈 수 없는 땅이고 1은 갈 수 있는 땅, 2는 목표지점이다.
- 입력에서 2는 단 한개이다.1

[출력]
- 각 지점에서 목표지점까지의 거리를 출력한다.
- 원래 갈 수 없는 땅인 위치는 0을 출력하고, 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1을 출력한다.

[구현방법]
- 이거 그냥 시작 지점 저장해둬싿가 원본 맵, 이동거리 맵, 방문처리 맵 세가지 만들어서 등록하면 되는 것 같다

[보완점]
- 이상하게 요즘엔 X, Y, row, col 두는게 헷갈리네;;;
- 둘이 통일해야하는데 참
- [3%에서 멈춤] 원래 갈 수 있는 땅인 부분 중에서 도착할 수 없는 위치는 -1로 하라는걸 안해서... 틀렸음

15 15
2 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1 0 0 0 0 1
1 1 1 1 1 1 1 1 1 1 0 1 1 1 1
1 1 1 1 1 1 1 1 1 1 0 1 0 0 0
1 1 1 1 1 1 1 1 1 1 0 1 1 1 1


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int X, Y;
    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};
    static int[][] map, recordMap;
    static boolean[][] isVisitied;

    static class Node {
        int x, y, count;

        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static boolean isOutOfBound(int x, int y) {
        return x < 0 || X <= x || y < 0 || Y <= y;
    }

    static void printRecordMap() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < X; row++) {
            for (int col = 0; col < Y; col++) {
                // 땅인데 방문한 적이 없다면 -1로 표기
                if (map[row][col] == 1 && !isVisitied[row][col]) recordMap[row][col] = -1;

                sb.append(recordMap[row][col]).append(" ");
            }
            
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N = 세로(X), M = 가로(Y)
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        map = new int[X][Y];
        recordMap = new int[X][Y];
        isVisitied = new boolean[X][Y];
        Node startPoint = null;

        // Map 입력 저장하기
        for (int row = 0; row < X; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < Y; col++) {
                int input = Integer.parseInt(st.nextToken());
                map[row][col] = input;

                // 시작 지점을 만나면 저장해둔다
                // 방문처리도 하고, 기록용 맵에 기록도 해둠
                if (input == 2) {
                    startPoint = new Node(row, col, 0);
                    isVisitied[row][col] = true;
                    // recordMap[row][col] = 0;  // 사실 기록용 맵 기본값이 0이라 굳이 안해줘도 됨
                }
            }
        }

        // 시작 지점부터 시작
        Queue<Node> q = new LinkedList<>();
        q.add(startPoint);
        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 4방탐색
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + moveX[i];
                int nextY = cur.y + moveY[i];
                int curCount = cur.count + 1;

                // map의 범위를 벗어났거나 방문한적 있거나, 갈 수 없는 곳이라면 다음 체크
                if (isOutOfBound(nextX, nextY) || isVisitied[nextX][nextY] || map[nextX][nextY] == 0) continue;

                // 여기까지 왔다는건 아래 조건을 만족한다는 의미니까 방문처리하고 해당 노드 기준으로 4방탐색 가능하도록 q에 넣어준다
                // 조건 : Map 범위 내에 있음 + 방문한 적없음 + 갈수 있는 곳임
                isVisitied[nextX][nextY] = true;
                recordMap[nextX][nextY] = curCount;
                q.add(new Node(nextX, nextY, curCount));
            }
        }

        printRecordMap();
    }
}