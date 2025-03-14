/*
[백준]
14938, 서강그라운드

[문제파악]
- 예은이는 요즘 가장 인기가 있는 게임 서강그라운드를 즐기고 있다.
- 서강그라운드는 여러 지역중 하나의 지역에 낙하산을 타고 낙하하여, 그 지역에 떨어져 있는 아이템들을 이용해 서바이벌을 하는 게임이다.
- 서강그라운드에서 1등을 하면 보상으로 치킨을 주는데, 예은이는 단 한번도 치킨을 먹을 수가 없었다.
- 자신이 치킨을 못 먹는 이유는 실력 때문이 아니라 아이템 운이 없어서라고 생각한 예은이는 낙하산에서 떨어질 때 각 지역에 아이템 들이 몇 개 있는지 알려주는 프로그램을 개발을 하였지만 어디로 낙하해야 자신의 수색 범위 내에서 가장 많은 아이템을 얻을 수 있는지 알 수 없었다.
- 각 지역은 일정한 길이 l (1 ≤ l ≤ 15)의 길로 다른 지역과 연결되어 있고 이 길은 양방향 통행이 가능하다.
- 예은이는 낙하한 지역을 중심으로 거리가 수색 범위 m (1 ≤ m ≤ 15) 이내의 모든 지역의 아이템을 습득 가능하다고 할 때, 예은이가 얻을 수 있는 아이템의 최대 개수를 알려주자.
- 주어진 필드가 위의 그림과 같고, 예은이의 수색범위가 4라고 하자. ( 원 밖의 숫자는 지역 번호, 안의 숫자는 아이템 수, 선 위의 숫자는 거리를 의미한다)
- 예은이가 2번 지역에 떨어지게 되면 1번,2번(자기 지역), 3번, 5번 지역에 도달할 수 있다. (4번 지역의 경우 가는 거리가 3 + 5 = 8 > 4(수색범위) 이므로 4번 지역의 아이템을 얻을 수 없다.)
- 이렇게 되면 예은이는 23개의 아이템을 얻을 수 있고, 이는 위의 필드에서 예은이가 얻을 수 있는 아이템의 최대 개수이다.

[입력]
- 첫째 줄에는 지역의 개수 n (1 ≤ n ≤ 100)과 예은이의 수색범위 m (1 ≤ m ≤ 15), 길의 개수 r (1 ≤ r ≤ 100)이 주어진다.
- 둘째 줄에는 n개의 숫자가 차례대로 각 구역에 있는 아이템의 수 t (1 ≤ t ≤ 30)를 알려준다.
- 세 번째 줄부터 r+2번째 줄 까지 길 양 끝에 존재하는 지역의 번호 a, b, 그리고 길의 길이 l (1 ≤ l ≤ 15)가 주어진다.
- 지역의 번호는 1이상 n이하의 정수이다.
- 두 지역의 번호가 같은 경우는 없다.

[출력]
- 예은이가 얻을 수 있는 최대 아이템 개수를 출력한다.

[구현방법]
- 양방향 가능
- 현재 노드에 떨어졌을 때, 다른 지점을 공유해서라도 가장 많이 얻을 수 있는 경로 탐색
- 플로이드 워셜이다보니까 일단 최단 해당 지점에서 여러 노드를 거칠 수 있으면 (많이 들를수록 좋기 때문에) 매번 갱신한다
- 한마디로 제일 적은 거리로

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r;
    static int INF = Integer.MAX_VALUE;
    static int[] items;
    static boolean[] isVisited;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // 지역의 갯수
        m = Integer.parseInt(st.nextToken());  // 예은의 수색범위
        r = Integer.parseInt(st.nextToken());  // 길의 갯수

        map = new int[n + 1][n + 1];
        items = new int[n + 1];
        isVisited = new boolean[n + 1];

        // 지역의 아이템 갯수 등록
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        // map 초기화
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (row == col) map[row][col] = 0;  // 자기자신은 길의 길이가 0임
                else map[row][col] = INF;
            }
        }

        // 길 입력 받기
        for (int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            // 양방향 등록
            map[a][b] = l;
            map[b][a] = l;
        }

        // 플로이드 워셜
        for (int mid = 1; mid <= n; mid++) {
            for (int start = 1; start <= n; start++) {
                for (int end = 1; end <= n; end++) {
                    // 가지 못하는 경우 or 예은의 수색 범위를 벗어나면 continue
                    if (map[start][mid] == INF || map[mid][end] == INF || m < map[start][mid] + map[mid][end]) continue;

                    // 해당 노드까지의 최단 거리를 찾는 이유
                    // 당연히 소모되는 거리가 짧으면 짧을수록 많은 노드들을 다닐 수 있고, 그러면 너무 당연하게도 더 많은 item들을 얻을 수 있음
                    map[start][end] = Math.min(map[start][end], map[start][mid] + map[mid][end]);
                }
            }
        }

        // 출력 코드
//        StringBuilder sb = new StringBuilder();
//        for (int row = 1; row <= n; row++) {
//            for (int col = 1; col <= n; col++) {
//                sb.append(map[row][col] == INF ? 0 : map[row][col]).append(" ");
//            }
//            sb.append("\n");
//        }
//
//        System.out.println(sb);


        // 최대 아이템 개수 찾기
        // 최대 아이템 갯수 찾는 로직이 좀 헷갈렸는데 이 부분은 문제를 이해하면 된다
        // 문제에서 표기하기를 현 위치에서 탐색 범위에 있는 곳들은 모두 방문 가능하다 (갔다오는, 이동 가능한 총 거리를 소모하는 개념이 아님)
        // 따라서 A에서 M거리만큼 떨어진 모든 거리를 가도 되니까, sum으로 죄다 더해주는 것이다
        int maxItems = 0;
        for (int start = 1; start <= n; start++) {
            int sum = 0;

            for (int end = 1; end <= n; end++) {
                // 수색 범위 내라면 아이템 획득 가능
                if (map[start][end] <= m) sum += items[end];
            }

            maxItems = Math.max(maxItems, sum);
        }

        // 결과 출력
        System.out.println(maxItems);
    }
}
