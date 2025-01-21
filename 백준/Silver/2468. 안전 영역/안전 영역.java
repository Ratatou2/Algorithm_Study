/*
[백준]
2468, 안전 영역

[문제파악]
- 재난방재청에서는 많은 비가 내리는 장마철에 대비해서 다음과 같은 일을 계획하고 있다.
- 먼저 어떤 지역의 높이 정보를 파악한다.
- 그 다음에 그 지역에 많은 비가 내렸을 때 물에 잠기지 않는 안전한 영역이 최대로 몇 개가 만들어 지는 지를 조사하려고 한다.
- 이때, 문제를 간단하게 하기 위하여, 장마철에 내리는 비의 양에 따라 일정한 높이 이하의 모든 지점은 물에 잠긴다고 가정한다.
- 어떤 지역의 높이 정보는 행과 열의 크기가 각각 N인 2차원 배열 형태로 주어지며 배열의 각 원소는 해당 지점의 높이를 표시하는 자연수이다.
- 어떤 지역의 높이 정보가 주어졌을 때, 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 계산하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에는 어떤 지역을 나타내는 2차원 배열의 행과 열의 개수를 나타내는 수 N이 입력된다.
- N은 2 이상 100 이하의 정수이다.
- 둘째 줄부터 N개의 각 줄에는 2차원 배열의 첫 번째 행부터 N번째 행까지 순서대로 한 행씩 높이 정보가 입력된다.
- 각 줄에는 각 행의 첫 번째 열부터 N번째 열까지 N개의 높이 정보를 나타내는 자연수가 빈 칸을 사이에 두고 입력된다.
- 높이는 1이상 100 이하의 정수이다.

[출력]
- 첫째 줄에 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 출력한다.

[구현방법]
- 일단 강수량보다 작은 지역은 잠기는 것으로 간주한다
- 넓이와는 별개로 안 잡긴 지역의 갯수를 센다
- 즉, 지도를 입력 받으면, for문으로 탐색하면서 강수량보다 높고, 연결된 최대까지 탐색하는 것이 좋겠다 (BFS)
- 근데 또 이제 안전 영역의 최대 갯수를 계산해야 하므로 수위는 [최저 건물 높이 < 높이 < 최고 건물 높이]로 점진적으로 증진 시켜가며 테스트를 해봐야할 것으로 보인다
- 사방탐색을 할 때도 물에 잠긴 곳은 가면 안된다는 것을 잊으면 안된다

[보완점]
- 메모리 초과로 터짐 ㅠ
- 일단 isVisited 매번 만들기 때문에 초기화하는 방향으로 수정
- Queue 대신 재귀적 DFS 사용하면 메모리 최적화가 가능하지만 난 BFS로 풀고 싶은걸...
- Node 클래스 삭제
- isVisited를 재사용하기 위해, 방문 마킹 int를 하나 추가 횟수가 몇번이건 marking을 늘려가며 진행하면 isVisited를 매번 초기화할 필요가 없다
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] visitTracking;
    static int visitMark;
    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};

    static boolean isOutofBound (int x, int y) {
        return x < 0 || N <= x || y < 0 || N <= y;
    }

    // 지도 출력 함수
    static void printMap (int[][] map) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                builder.append(map[i][j] + " ");
            }
            builder.append("\n");
        }

        System.out.println(builder);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int maxHeight = 0;

        visitTracking =  new int[N][N];
        visitMark = 0;

        // 지도 입력 받기
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());

                // 최소 건물 높이, 최대 건물 높이 갱신
                if (maxHeight < map[row][col]) maxHeight = map[row][col];
            }
        }

        int maxCount = 1;  // 모든 지역이 잠기지 않으면 최솟값 1
        Queue<int[]> q = new LinkedList<>();

        // 최소 건물 높이부터 최대 건물 높이까지 1씩 늘려가면서 어느 지점에서 가장 많은 안전 지역이 나오는지 테스트 해본다
        // 근데 최소 높이가 1이니까 1부터 해도됨 (놓친 부분)
        for (int currentHeight = 1; currentHeight <= maxHeight; currentHeight++) {
            visitMark++;
            int count = 0;  // 지역 갯수 셀 함수

            // 지도 탐색
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    // 방문했거나, 잠긴 지역이면 패스
                    if (visitTracking[row][col] == visitMark || map[row][col] <= currentHeight) continue;
                    // 방문하지도 않았고, 잠기지도 않았으면 해당 지역 기준으로 사방탐색 진행
                    // 그러므로 Queue에 넣고, 더 이상 진행하지 못할 때까지 탐색한다 (BFS)
                    q.clear();
                    q.add(new int[]{row, col});
                    visitTracking[row][col] = visitMark;

                    while (!q.isEmpty()) {
                        int[] curr = q.poll();

                        // 사방탐색 진행
                        for (int index = 0; index < 4; index++) {
                            int nextX = curr[0] + moveX[index];
                            int nextY = curr[1] + moveY[index];

                            // 맵 범위 밖이거나, 방문했던 이력이 있거나, 물에 잠긴 곳이면 패스
                            if (isOutofBound(nextX, nextY)
                                    || visitTracking[nextX][nextY] == visitMark
                                    || map[nextX][nextY] <= currentHeight) continue;

                            visitTracking[nextX][nextY] = visitMark;
                            q.add(new int[]{nextX, nextY});
                        }
                    }

                    count++;  // 연결된 모든 곳을 방문하고 왔으니, Count + 1
                }
            }

            maxCount = Math.max(maxCount, count);
        }

        System.out.println(maxCount);
    }
}