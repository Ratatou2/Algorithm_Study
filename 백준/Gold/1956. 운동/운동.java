/*
[백준]
1956, 운동

[문제파악]
- V개의 마을와 E개의 도로로 구성되어 있는 도시가 있다.
- 도로는 마을과 마을 사이에 놓여 있으며, 일방 통행 도로이다.
- 마을에는 편의상 1번부터 V번까지 번호가 매겨져 있다고 하자.
- 당신은 도로를 따라 운동을 하기 위한 경로를 찾으려고 한다.
- 운동을 한 후에는 다시 시작점으로 돌아오는 것이 좋기 때문에, 우리는 사이클을 찾기를 원한다.
- 단, 당신은 운동을 매우 귀찮아하므로, 사이클을 이루는 도로의 길이의 합이 최소가 되도록 찾으려고 한다.
- 도로의 정보가 주어졌을 때, 도로의 길이의 합이 가장 작은 사이클을 찾는 프로그램을 작성하시오.
- 두 마을을 왕복하는 경우도 사이클에 포함됨에 주의한다.

[입력]
- 첫째 줄에 V와 E가 빈칸을 사이에 두고 주어진다. (2 ≤ V ≤ 400, 0 ≤ E ≤ V(V-1))
- 다음 E개의 줄에는 각각 세 개의 정수 a, b, c가 주어진다.
- a번 마을에서 b번 마을로 가는 거리가 c인 도로가 있다는 의미이다. (a → b임에 주의)
- 거리는 10,000 이하의 자연수이다. (a, b)
- 쌍이 같은 도로가 여러 번 주어지지 않는다.

[출력]
- 첫째 줄에 최소 사이클의 도로 길이의 합을 출력한다.
- 운동 경로를 찾는 것이 불가능한 경우에는 -1을 출력한다.

[구현방법]
- 이것 역시 플루이드 워셜
- 다만 사이클이니까 자기 자신으로 돌아오는 경우의 수를 따져야한다
- 따라서 자기 자신도 INF로 초기화 해두면 됨
- 근데 70%에서 터지는 것을 보아하니 INF 값이 더 커야하나보다
- 어차피 INF이면 계산 안하도록 예외처리해두었으니 최대치까지 키워보자
- 이딴게 문제가 아니었음
- 답이 없을 경우, -1을 출력해야함 (문제 똑바로 안 읽냐?)

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static int INF = Integer.MAX_VALUE / 2;
    static int[][] map;

//    static void printMap() {
//        StringBuilder sb = new StringBuilder();
//
//        for (int row = 1; row <= V; row++) {
//            for (int col = 1; col <= V; col++) {
//                sb.append(map[row][col] == INF ? -1 : map[row][col]).append(" ");
//            }
//            sb.append("\n");
//        }
//
//        System.out.println(sb);
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int result = Integer.MAX_VALUE;

        V = Integer.parseInt(st.nextToken());  // 마을 갯수
        E = Integer.parseInt(st.nextToken());  // 도로 갯수
        map = new int[V + 1][V + 1];

        // 갱신 전 INF로 초기화
        for (int row = 1; row <= V; row++) {
            for (int col = 1; col <= V; col++) {
                map[row][col] = INF;
            }
        }

        // 도로 입력 받기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());  // 마을 A
            int b = Integer.parseInt(st.nextToken());  // 마을 B
            int c = Integer.parseInt(st.nextToken());  // 도로의 거리

            map[a][b] = c;
        }

        // 플로이드 워셜 알고리즘 (3중 for문)
        for (int mid = 1; mid <= V; mid++) {
            for (int start = 1; start <= V; start++) {
                for (int end = 1; end <= V; end++) {
                    if (map[start][mid] == INF || map[mid][end] == INF) continue;  // 갈 방법이 없으면 패스
                    map[start][end] = Math.min(map[start][mid] + map[mid][end], map[start][end]);
                }
            }
        }

        // 가장 적은 거리의 사이클 찾기 (row == col 지점만 체크하면 됨)
        for (int i = 1; i <= V; i++) {
            result = Math.min(result, map[i][i]);
        }

        System.out.println(result == INF ? -1 : result);
    }
}
