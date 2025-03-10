/*
[백준]
11404, 플로이드

[문제파악]
- n(2 ≤ n ≤ 100)개의 도시가 있다.
- 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000)개의 버스가 있다.
- 각 버스는 한 번 사용할 때 필요한 비용이 있다.
- 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 도시의 개수 n이 주어지고 둘째 줄에는 버스의 개수 m이 주어진다.
- 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다.
- 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다.
- 버스의 정보는 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c로 이루어져 있다.
- 시작 도시와 도착 도시가 같은 경우는 없다. 비용은 100,000보다 작거나 같은 자연수이다.
- 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.

[출력]
//- n개의 줄을 출력해야 한다.
- i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용이다.
- 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.

[구현방법]
- 플로이드 워셜 문제
- 최소비용을 구하는 문제니까 자기 자신으로 회귀하는 것은 0 (비용 최소화), 그외 모든 지점은 INF로 지정하고 진행한다
- Integer.MAX_VALUE로 하면 오버 플로가 날 수 있기 때문에 한 노드에서 비용의 최댓값은 얼추 999,999로 잡는다
- 이는 기본적으로 input으로 받을 수 있는 단일 비용의 최댓값이 10만이기 때문이다 (문제의 조건)

[보완점]
- 플로이드 워셜을 어설프게 알고 있어서 헷갈리고 시간이 걸렸던 문제 (이건 외우면 된다)
- 외워
- 그리고 문제를 잘 읽자
- 경로가 여러 개일 수 있다는 의미는 입력을 받을 때 노선 역시 최솟값의 비용의 노선으로 갱신해야한다는 의미이다
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int INF = Integer.MAX_VALUE / 2;
    static int[][] map;


    static void printMap() {
        StringBuilder sb = new StringBuilder();

        for (int row = 1; row <= N; row++)  {
            for (int col = 1; col <= N; col++)  {
                if (INF <= map[row][col]) sb.append(0 + " ");
                else sb.append(map[row][col] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        // map 입력 받기
        for (int row = 1; row <= N; row++)  {
            for (int col = 1; col <= N; col++)  {
                if (row == col) map[row][col] = 0;  // 자기 자신을 가는 경우, 최솟값은 0 (= 비용 없음)
                else map[row][col] = INF;  // 비용은 최대 10만까지
            }
        }

        // 버스 정보 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int city_a = Integer.parseInt(st.nextToken());  // 시작 도시
            int city_b = Integer.parseInt(st.nextToken());  // 도착 도시
            int cost = Integer.parseInt(st.nextToken());

            // 경로가 여러 개일 수 있기 때문에 최솟값으로 갱신해줘야 한다
            map[city_a][city_b] = Math.min(map[city_a][city_b], cost);
        }

        // 플로이드-워셜 알고리즘 (3중 for문)
        for (int mid = 1; mid <= N; mid++) {
            for (int start = 1; start <= N; start++) {
                for (int end = 1; end <= N; end++) {
                    // (시작-중간) 또는 (중간-도착) 지점이 못가는 곳이면 더 볼 것 없이 패스
                    if (map[start][mid] == INF || map[mid][end] == INF) continue;

                    // start~end는 start~mid + mid+end와 기존 값을 비교해서 갱신한다
                    map[start][end] = Math.min(map[start][end], map[start][mid] + map[mid][end]);
                }
            }
        }

        printMap();
    }
}
