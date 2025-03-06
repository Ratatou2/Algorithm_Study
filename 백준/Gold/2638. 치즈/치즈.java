/*
[백준]
2638, 치즈

[문제파악]
- N×M의 모눈종이 위에 아주 얇은 치즈가 <그림 1>과 같이 표시되어 있다.
- 단, N 은 세로 격자의 수이고, M 은 가로 격자의 수이다.
- 이 치즈는 냉동 보관을 해야만 하는데 실내온도에 내어놓으면 공기와 접촉하여 천천히 녹는다.
- 그런데 이러한 모눈종이 모양의 치즈에서 각 치즈 격자(작 은 정사각형 모양)의 4변 중에서 적어도 2변 이상이 실내온도의 공기와 접촉한 것은 정확히 한시간만에 녹아 없어져 버린다.
- 따라서 아래 <그림 1> 모양과 같은 치즈(회색으로 표시된 부분)라면 C로 표시된 모든 치즈 격자는 한 시간 후에 사라진다.

- <그림 2>와 같이 치즈 내부에 있는 공간은 치즈 외부 공기와 접촉하지 않는 것으로 가정한다.
- 그러므 로 이 공간에 접촉한 치즈 격자는 녹지 않고 C로 표시된 치즈 격자만 사라진다.
- 그러나 한 시간 후, 이 공간으로 외부공기가 유입되면 <그림 3>에서와 같이 C로 표시된 치즈 격자들이 사라지게 된다.

- 모눈종이의 맨 가장자리에는 치즈가 놓이지 않는 것으로 가정한다.
- 입력으로 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에는 모눈종이의 크기를 나타내는 두 개의 정수 N, M (5 ≤ N, M ≤ 100)이 주어진다.
- 그 다음 N개의 줄에는 모눈종이 위의 격자에 치즈가 있는 부분은 1로 표시되고, 치즈가 없는 부분은 0으로 표시된다.
- 또한, 각 0과 1은 하나의 공백으로 분리되어 있다.

[출력]
- 출력으로는 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 정수로 첫 줄에 출력한다.

[구현방법]
- 이 문제는 이전 치즈랑 달리 2변이 닿아있어야만 한다 즉, 치즈 입장에서 사방 탐색을 해야할 것 같음
- 사방을 탐색했는데 1개 이상이 공기야? 그러면 이제 Q에 넣고 녹일 준비
- 아 근데 사방이 둘러 쌓여있으면 괜찮다네...? 이러면 또 바깥 공기에서부터 탐색을 진행해야하는디... ㅠ
- 아 그럼 바깥에서 탐색 진행하고 닿은 치즈는 추가, 이후 Q에 들어간 치즈 하나씩 꺼내면서 면이 두개 닿아있는지를 체크하면 되겄네
- 근...데...? 한 치즈가 여러번 탐색될 수 있으니까..? Set으로 만듭시다 아님 뭐 배열에 추가하기 전에 contains 써도 되고
    - ArrayList의 contains는 O(N)이라네 (당연하게도...)
    - Set으로 받읍시다
    - Set은 중복제거를 contains처럼 처리하지만 Hash를 써서 평균적으로 O(1) 시간이 소요된다 함 (끝내준다;;)

[보완점]
- Q를 쓸 때는 LinkedList보단 ArrayDeque를 쓰도록 하자
- Q는 인터페이스고 이것을 구현하는 구현체가 LinkedList & ArrayDeque가 있음
- 근데 ArrayDeque가 메모리 사용 & 속도면에서 더 효율적이라고 함
- LinkedList는 참조 객체 관리 비용 때문에 메모리 사용이 높다고... (참조 객체 관리 비용이 뭔데, 아마 연결되어있는걸 기록하고 관리하는 그 기회비용?)
- LinkedList는?
    (1) FIFO(First In, First Out) 방식
    (2) 양방향 연결 리스트로 삽입 & 삭제가 O(1) (중간 접근은 느림)
- ArrayDeque는?
    (1) 배열 기반의 Deque로, 양쪽에서 삽입 & 삭제가 빠름 (O(1))
    (2) LinkedList보다 메모리 효율 & 속도 면에서 더 좋음

- 추가로 PQ는?
    (1) 우선순위 큐로, 원소가 정렬된 상태로 관리
    (2) 삽입 & 삭제가 O(log N)


- 와 사람들 다들 나랑 비슷하게 생각했구낭 신기...
- 그 하나 놓친게 치즈 녹일 때 내부로 판정된 부분은 공기로 처리로 하면 안되는데 해버리고 있었음...
- 바깥 공기가 탐색한 지역은 전부 마킹하는 별도의 배열이 있어야할지도..
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, totalCheese;
    static int[][] map;
    static boolean[][] isVisited;
    static boolean[][] isAir;
    static int[] move_X = {-1, 0 , 1, 0};
    static int[] move_Y = {0 ,1, 0, -1};
    static Queue<int[]> cheeseInTheAir;

    static boolean isOutOfBound(int x, int y) {
        return x < 0 || N <= x || y < 0 || M <= y;
    }

    static void DFS(int x, int y) {
        // 방문 체크 및 처리 + 공기처리
        if (isVisited[x][y]) return;
        isVisited[x][y] = true;
        isAir[x][y] = true;

        // 사방탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + move_X[i];
            int ny = y + move_Y[i];

            // 맵 범위 밖이거나 방문했던 곳이면 return
            if (isOutOfBound(nx, ny) || isVisited[nx][ny]) continue;

            // 이동할 곳이 공기면, 해당 지점부터 계속 탐색
            // 이동할 곳이 치즈면, 방문 처리 후 Q에 집어넣기
            if (map[nx][ny] == 0) {
                DFS(nx, ny);
            } else if (map[nx][ny] == 1) {
                isVisited[nx][ny] = true;
                cheeseInTheAir.add(new int[]{nx, ny});
            }
        }
    }

    static void cheeseMelting() {
        while(!cheeseInTheAir.isEmpty()) {
            int[] cheese = cheeseInTheAir.poll();

            int x = cheese[0];
            int y = cheese[1];

            if (isVisited[x][y]) continue;  // 이미 확인한 치즈면 패스

            // 2면 이상이 공기와 닿아있는지 체크
            int countAir = 0;
            for (int i = 0; i < 4; i++) {
                int nx = x + move_X[i];
                int ny = y + move_Y[i];

                if (isOutOfBound(nx, ny) || isVisited[nx][ny]) continue;
                if (isAir[nx][ny]) countAir++;  // 공기로 판정된 것이라면 count
            }

            // 현재 치즈 기준 사방 탐색을 했는데, 2면 이상이 공기와 닿아있다?
            // 녹이고 방문처리 (중복 치즈는 같은 작업 안하기 위함)
            if (2 <= countAir) {
                map[x][y] = 0;  // 치즈 녹임
                totalCheese--;  // 전체 치즈 갯수 -1
                isVisited[x][y] = true;
            }
        }
    }

//    static void printMap(String when) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("=========== " + when + " ===========" + "\n");
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                sb.append(map[i][j] == 1 ? "■ " : "□ ");
//            }
//            sb.append("\n");
//        }
//
//        System.out.println(sb);
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // 모눈종이 입력받기
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                int curr = Integer.parseInt(st.nextToken());
                if (curr == 1) totalCheese++;  // 전체 치즈 카운트
                map[row][col] = curr;
            }
        }

        // 공기로부터 탐색하며 치즈를 찾고, 면이 2개 이상 닿은 치즈를 녹이는 과정
        int hour = 0;
        while (0 < totalCheese) {
            cheeseInTheAir = new ArrayDeque<>();
            isVisited = new boolean[N][M];
            isAir = new boolean[N][M];
            DFS(0, 0);
            isVisited = new boolean[N][M];
            //printMap("Before");
            cheeseMelting();
            //printMap("After");
            hour++;
        }

        System.out.println(hour);
    }
}
