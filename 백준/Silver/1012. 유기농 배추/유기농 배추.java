/*
[백준]


[문제파악]
- 차세대 영농인 한나는 강원도 고랭지에서 유기농 배추를 재배하기로 하였다.
- 농약을 쓰지 않고 배추를 재배하려면 배추를 해충으로부터 보호하는 것이 중요하기 때문에, 한나는 해충 방지에 효과적인 배추흰지렁이를 구입하기로 결심한다.
- 이 지렁이는 배추근처에 서식하며 해충을 잡아 먹음으로써 배추를 보호한다.
- 특히, 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면 이 지렁이는 인접한 다른 배추로 이동할 수 있어, 그 배추들 역시 해충으로부터 보호받을 수 있다.
- 한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우에 서로 인접해있는 것이다.
- 한나가 배추를 재배하는 땅은 고르지 못해서 배추를 군데군데 심어 놓았다.
- 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다.
- 예를 들어 배추밭이 아래와 같이 구성되어 있으면 최소 5마리의 배추흰지렁이가 필요하다. 0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.

1	1	0	0	0	0	0	0	0	0
0	1	0	0	0	0	0	0	0	0
0	0	0	0	1	0	0	0	0	0
0	0	0	0	1	0	0	0	0	0
0	0	1	1	0	0	0	1	1	1
0	0	0	0	1	0	0	1	1	1

[입력]
- 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다.
- 그 다음 줄부터 각각의 테스트 케이스에 대해
    - 첫째 줄에는 배추를 심은 배추밭의 가로길이 M(1 ≤ M ≤ 50)과 세로길이 N(1 ≤ N ≤ 50),
    - 그리고 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)이 주어진다.
    - 그 다음 K줄에는 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)가 주어진다.
- 두 배추의 위치가 같은 경우는 없다.

[출력]
- 각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수를 출력한다.

[구현방법]
- 음 일단 입력 받으면서 1인 곳은 죄다 기억해둔다
- Queue로 구현해서 BFS 구현한다
- 하나의 1에서 방문할 수 있는 모든 1을 방문처리한 뒤, 다음 1로 옮긴다
- 방문처리 되어 있다면 패스, 아니면 또 탐색
- 계속 반복한다
- BFS 한번 끝날 때마다, 지렁이 count +1 해주면 됨!

[보완점]
2
10 8 17
0 0
1 0
1 1
4 2
4 3
4 5
2 4
3 4
7 4
8 4
9 4
7 5
8 5
9 5
7 6
8 6
9 6
10 10 1
5 5


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int ROW, COL, cabbageCount;
    static boolean[][] farm, isVisited;
    static final int[] moveX = {-1, 1, 0, 0}; // 상하좌우 이동
    static final int[] moveY = {0, 0, -1, 1};

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    static boolean isOutOfBound(int x, int y) {
        return x < 0 || ROW <= x || y < 0 || COL <= y;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ROW = Integer.parseInt(st.nextToken());
            COL = Integer.parseInt(st.nextToken());
            cabbageCount = Integer.parseInt(st.nextToken());
            farm = new boolean[ROW][COL];
            isVisited = new boolean[ROW][COL];
            Queue<Node> cabbageLocation = new LinkedList<>();
            int count = 0;

            for (int i = 0; i < cabbageCount; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                cabbageLocation.add(new Node(x, y));
                farm[x][y] = true;  // 배추 있는 곳 표기
            }

            // 모든 노드를 탐색해보기 위함 (for문과 다를 바 없음)
            while (!cabbageLocation.isEmpty()) {
                Node currentCabbage = cabbageLocation.poll();
                int currentCabbageX = currentCabbage.x;
                int currentCabbageY = currentCabbage.y;

                // 방문한 적이 있다면 다음 노드
                if (isVisited[currentCabbageX][currentCabbageY]) continue;

                Queue<Node> q = new LinkedList<>();  // 지금 노드(배추)에서 부터 연결된 모든 노드(배추)들을 저장하기 위한 Queue
                q.add(new Node(currentCabbageX, currentCabbageY));

                while (!q.isEmpty()) {
                    Node currentNode = q.poll();
                    int currentX = currentNode.x;
                    int currentY = currentNode.y;

                    // 방문한적 있으면 패스해야한다
                    if (isVisited[currentX][currentY]) continue;
                    // q에서 나온 애들은 죄다 방문처리 해준다
                    isVisited[currentX][currentY] = true;

                    // 4방 탐색
                    for (int i = 0; i < 4; i++) {
                        int nextX = currentX + moveX[i];
                        int nextY = currentY + moveY[i];

                        // 밭 밖으로 나갔거나 배추가 아니면(=false) 다음 탐색 진행
                        if (isOutOfBound(nextX, nextY) || !farm[nextX][nextY]) continue;
                        q.add(new Node(nextX, nextY));
                    }
                }

                count++;
            }

            System.out.println(count);
        }
    }
}