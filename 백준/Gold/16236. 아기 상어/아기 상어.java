/*
[백준]
16236, 아기상어

[문제파악]
- N×N 크기의 공간에 물고기 M마리와 아기 상어 1마리가 있다.
- 공간은 1×1 크기의 정사각형 칸으로 나누어져 있다.
- 한 칸에는 물고기가 최대 1마리 존재한다.

- 아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다.
- 가장 처음에 아기 상어의 크기는 2이고, 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다.
- 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다.
- 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다.
- 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.
- 아기 상어가 어디로 이동할지 결정하는 방법은 아래와 같다.

- 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
- 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
- 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
- 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
- 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
- 아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다.
- 즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 이동과 동시에 물고기를 먹는다.
- 물고기를 먹으면, 그 칸은 빈 칸이 된다.

- 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.
- 공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 공간의 크기 N(2 ≤ N ≤ 20)이 주어진다.
- 둘째 줄부터 N개의 줄에 공간의 상태가 주어진다.
- 공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있고, 아래와 같은 의미를 가진다.
    0: 빈 칸
    1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
    9: 아기 상어의 위치

아기 상어는 공간에 한 마리 있다.

[출력]
- 첫째 줄에 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력한다.

[구현방법]
- 흠.. 처음엔 물고기를 Queue에 넣을까 생각했는데, Queue에 넣으면 아기상어와의 거리를 매번 재야하나..?
- 더군다나 거리를 재서 가까운 물고기부터 먹어야하는데 마냥 이동하기엔 덩치 큰 물고기가 막고 있으면 또 돌아가야한단 말이지??
- 고려할게 꽤 많군
- 일단 드는 생각은 PriorityQueue를 구현해서 우선순위는 size로 둔다 그러면 항상 작은 것부터 오게되니까 현재 아기상어 크기보다 작은게 나올 때까지 for문 돌리는거지
- 그런 식으로 진행하고 작은 놈들 리스트업이 되면, 개중에 가장 거리가 적은 놈으로 이동해서 잡아먹는다
- 이동할 때는 4방 탐색을 통해서 거리가 제일 제일 가까워지는 쪽으로 이동하는 방식으로 진행하는게... 베스트 아이디어인데 이게 실현 가능성이 있나?
- 아 이동할 때는 그냥 BFS 쓰면 되는구나 ㅋㅋㅋㅋㅋㅋㅋㅋ
- 우선순위 큐는 단순히 그 다음 먹이를 정할 때만 쓰면 되는거고! 납득
- 그럼 로직을 짜보면

// 1) map 입력 받기 (입력 받다가 물고기를 입력 받으면 Fish 클래스로 받아서 우선순위 큐에 넣는다)
// 2) 아기상어 좌표에서부터 가장 가까우면서 아기상어보다 작은 물고기를 찾아간다
    // 2-1) 조건을 충족하는 물고기가 없으면, return
    // 2-2) 조건을 충족하는 물고기가 있으면, 가장 위에 있으면서, 가장 왼쪽에 있는 물고기를 먹는다
// 3) 더 먹을 물고기가 없다면 종료

- 이게 처음엔 단순히 x, y 좌표로 거리를 구하려고 했는데 중간에 큰 물고기가 가로막고 있거나하면 돌아가게 되니까 PQ를 써서, 나보다 작온 놈 중에 가장 가까운 것 찾는게 나을듯

[보완점]
- PQ에 Comparable<Fish> 걸어두고 compareTo를 size로 걸어뒀는데 안돼;;
- 뭔가 방향이 잘못된걸까?
- X, Y의 세부 조건까지 넣어주니 sort 잘됐다

- 아 그리고 나는 무조건 DFS로만 가능한걸로 생각했다 (초기에는)
- 왜냐면 조건에 맞춰서 구해야하니까, 근데 생각해보니 가장 빨리 닿는 물고기가 제일 가까운 놈이다
- 그리고 그런 놈을 발견하면, Q에 있는 모든 물고기를 소진해서 그 다음 이동에서 물고기를 찾아보고 있으면 그떄 가까운 물고기가 여럿일 때의 조건을 따지면 된다
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] sea;
    static boolean[][] visited;
    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};

    static class Shark {
        int x, y, size, eatCount;

        Shark(int x, int y) {
            this.x = x;
            this.y = y;
            this.size = 2;
            this.eatCount = 0;
        }
    }

    static class Fish implements Comparable<Fish> {
        int x, y, distance;

        Fish(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        // 거리, 가장 위 (x축), 가장 왼쪽 (y축) 순으로 정렬
        @Override
        public int compareTo(Fish o) {
            if (this.distance != o.distance) return Integer.compare(this.distance, o.distance);
            if (this.x != o.x) return Integer.compare(this.x, o.x);
            return Integer.compare(this.y, o.y);
        }
    }

    // BFS로 가장 가까운 물고기 찾기
    static Fish findNearestFish(Shark shark) {
        Queue<Fish> queue = new LinkedList<>();
        PriorityQueue<Fish> fishes = new PriorityQueue<>();
        queue.add(new Fish (shark.x, shark.y, 0));  // Queue에 상어 위치 입력

        visited = new boolean[N][N];  // BFS 할 때마다 방문 배열은 초기화 (당연하게도)
        visited[shark.x][shark.y] = true;  // 시작지점 방문 처리

        while (!queue.isEmpty()) {
            Fish current = queue.poll();  // 아기 상어의 위치 꺼내기

            int curX = current.x;
            int curY = current.y;
            int curDist = current.distance;

            // 4방향 탐색 시작
            for (int i = 0; i < 4; i++) {
                int nextX = curX + moveX[i];
                int nextY = curY + moveY[i];

                // 맵 밖으로 나갔거나, 방문했던 곳이거나, 아기 상어보다 큰 물고기라면 못 지나간다
                if (isOutOfBound(nextX, nextY) || visited[nextX][nextY] || shark.size < sea[nextX][nextY])
                    continue;

                // 그런 장소가 아니라면 우선 방문 처리
                visited[nextX][nextY] = true;

                // 물고기이면서 아기 상어보다 사이즈가 작다면 PQ에 넣는다
                if (0 < sea[nextX][nextY] && sea[nextX][nextY] < shark.size) {
                    fishes.add(new Fish(nextX, nextY, curDist + 1));
                }

                queue.add(new Fish (nextX, nextY, curDist + 1));
            }
        }

        return fishes.isEmpty() ? null : fishes.poll();  // 가장 앞에 있는 것을 꺼내서 return한다 (*)
    }

    static boolean isOutOfBound(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sea = new int[N][N];
        Shark babyShark = null;

        // 초기 맵 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());

                // 아기 상어 위치 기록 후, 빈칸으로 초기화
                if (sea[i][j] == 9) {
                    babyShark = new Shark(i, j);
                    sea[i][j] = 0;
                }
            }
        }

        // 전체적으로 이동에 소요한 시간 체크
        int totalTime = 0;

        // BFS를 통해 물고기를 찾고, 먹을 수 있을 때까지 반복
        while (true) {
            Fish targetFish = findNearestFish(babyShark);

            // 먹을 물고기가 없다면 끝
            if (targetFish == null) break;

            // 물고기 먹기
            totalTime += targetFish.distance;  // 이동하는데 소요한 시간을 전체 소요시간에 더함

            // 아기 상어 이동한 좌표 기록
            babyShark.x = targetFish.x;
            babyShark.y = targetFish.y;
            babyShark.eatCount++;
            sea[targetFish.x][targetFish.y] = 0;  // 물고기 잡아먹었으니까 해당 칸은 0으로 초기화 (아무것도 없음)

            // 아기 상어의 크기 증가 조건 체크
            if (babyShark.eatCount == babyShark.size) {
                babyShark.size++;
                babyShark.eatCount = 0;
            }
        }

        System.out.println(totalTime);
    }
}

/*
(*) 가장 앞에 있는 물고기 값이 가장 가까운 물고기일 수 있는 이유는?
- PQ이기 때문이다
- 우리는 위에서 Fish 클래스의 정렬 기준을 compareTo로 정의해주었다
    - 그 정의는 거리, 가장 위 (x축), 가장 왼쪽 (y축) 순이다
- 그렇기 떄문에 물고기는 거리가 가까우면서 그중에서 가장 위에 있고 가장 왼쪽에 있는 순으로 정렬될 것이다
- PQ이기 때문에 입력 순서와도 상관없이!
- 그렇게 구현해두었기 때문에 매번 현 상어 위치에서 가장 가까운 물고기를 찾을 수 있는 것이다
- BFS를 통해 방사형으로 퍼져 나가기 때문에 가장 먼저 닿을 수 있는 물고기를 찾는 것에도 적합하다
- 그렇기 때문에 내가 초기에 생각했던 DFS 보다 훨씬 더 효율적인 것이다
*/