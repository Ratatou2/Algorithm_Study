/*
[백준]
10159, 저울

[문제파악]
- 무게가 서로 다른 N 개의 물건이 있다.
- 각 물건은 1부터 N 까지 번호가 매겨져 있다.
- 우리는 일부 물건 쌍에 대해서 양팔 저울로 어떤 것이 무거운 것인지를 측정한 결과표를 가지고 있다.
- 이 결과표로부터 직접 측정하지 않은 물건 쌍의 비교 결과를 알아낼 수도 있고 알아내지 못할 수도 있다.
- 예를 들어, 총 6개의 물건이 있고, 다음 5개의 비교 결과가 주어졌다고 가정하자. ([1]은 1번 물건의 무게를 의미한다.)

- [1]>[2], [2]>[3], [3]>[4], [5]>[4], [6]>[5]

- 우리는 [2]>[3], [3]>[4]로부터 [2]>[4]라는 것을 알 수 있다.
- 하지만, 물건 2와 물건 6을 비교하는 경우, 앞서의 결과만으로는 어느 것이 무거운지 알 수 없다.
- 이와 같이, 물건 2는 물건 1, 3, 4와의 비교 결과는 알 수 있지만, 물건 5, 6과의 비교 결과는 알 수 없다.
- 물건 4는 모든 다른 물건과의 비교 결과를 알 수 있다.

- 비교 결과가 모순되는 입력은 없다고 가정한다.
- 위 예제의 기존 측정 결과에 [3]>[1]이 추가되었다고 가정하자.
- 이 경우 [1]>[2], [2]>[3]이므로 우리는 [1]>[3]이라는 것을 예측할 수 있는데, 이는 기존에 측정된 결과 [3]>[1]과 서로 모순이므로 이러한 입력은 가능하지 않다.

- 물건의 개수 N 과 일부 물건 쌍의 비교 결과가 주어졌을 때, 각 물건에 대해서 그 물건과의 비교 결과를 알 수 없는 물건의 개수를 출력하는 프로그램을 작성하시오.

[입력]
- 첫 줄에는 물건의 개수 N 이 주어지고, 둘째 줄에는 미리 측정된 물건 쌍의 개수 M이 주어진다.
- 단, 5 ≤ N ≤ 100 이고, 0 ≤ M ≤ 2,000이다.
- 다음 M개의 줄에 미리 측정된 비교 결과가 한 줄에 하나씩 주어진다.
- 각 줄에는 측정된 물건 번호를 나타내는 두 개의 정수가 공백을 사이에 두고 주어지며, 앞의 물건이 뒤의 물건보다 더 무겁다.

[출력]
- 여러분은 N개의 줄에 결과를 출력해야 한다.
- i 번째 줄에는 물건 i 와 비교 결과를 알 수 없는 물건의 개수를 출력한다.

[구현방법]
- 이것도 결국 플로이드 워셜이고 사실 단방향 간선이라고 생각하면 된다
- 1 > 2 > 3이면 1은 3보다 무겁단걸 알 수 있지만 1 > 3 > 4, 5 > 6 > 4 이면 1과 5의 관계는 알 수 없다
- 즉 단방향 간선이면 4로 이동은 가능하지만 4에서 5로 건너갈 수는 없다는 것
- 그러니 가중치를 계산할 필요도 없고 그저, true/false. 갈 수 있냐 없냐만 따지면 될듯하다

- 이렇게 되면 안된다. 이렇게 하면 크고 작은 것 둘 중 하나만 비교 가능 그러니까 역으로 비교할 수 있어도 비교 가능한 것이니까
- 근데 알고보니 쉬웠다... 그냥 크면 크다고 기록 남기고, 작으면 작다고 기록 남기면 된다... 뭐야

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int INF = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());  // 물건 갯수
        M = Integer.parseInt(br.readLine());  // 미리 측정된 물건 쌍의 갯수
        map = new int[N + 1][N + 1];

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                map[row][col] = INF;
            }
        }

        // 입력 받기
        for (int row = 1; row <= M; row++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;  // 더 큰 경우
            map[b][a] = -1;  // 더 작은 경우
        }

        // 플로이드 워셜
        for (int mid = 1; mid <= N; mid++) {
            for (int start = 1; start <= N; start++) {
                for (int end = 1; end <= N; end++) {
                    // 자기 자신이거나, 경유지(mid)에 도달할 수 없는 경우 더 볼 것 없다
                    if (start == end || map[start][mid] == INF || map[mid][end] == INF ) continue;

                    if (map[start][mid] == -1 && map[mid][end] == -1) map[start][end] = -1;  // 더 작은 경우의 수면 작음 처리
                    else if (map[start][mid] == 1 && map[mid][end] == 1) map[start][end] = 1;  // 더 큰 경우의 수면 큼 처리
                }
            }
        }

        // 갈 수 없는 지점이 몇개인지 순차적으로 출력
        for (int row = 1; row <= N; row++) {
            int count = 0;
            for (int col = 1; col <= N; col++) {
                // 더 큰거나, 작은게 있는지 알 수 없으면 count에 +1 (비교 결과를 모르는 것을 세는 것이다)
                if (map[row][col] == -1 || map[row][col] == 1) count++;
            }

            sb.append(N - count - 1).append("\n");
        }

        System.out.println(sb);
    }
}
