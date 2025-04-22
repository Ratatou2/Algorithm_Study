/*
[백준]
14267, 회사문화1

[문제파악]
- 영선회사에는 매우 좋은 문화가 있는데, 바로 상사가 직속 부하를 칭찬하면 그 부하가 부하의 직속 부하를 연쇄적으로 칭찬하는 내리 칭찬이 있다.
- 즉, 상사가 한 직속 부하를 칭찬하면 그 부하의 모든 부하들이 칭찬을 받는다.
- 모든 칭찬에는 칭찬의 정도를 의미하는 수치가 있는데, 이 수치 또한 부하들에게 똑같이 칭찬 받는다.
- 직속 상사와 직속 부하관계에 대해 주어지고, 칭찬에 대한 정보가 주어질 때, 각자 얼마의 칭찬을 받았는지 출력하시오,

[입력]
- 첫째 줄에는 회사의 직원 수 n명, 최초의 칭찬의 횟수 m이 주어진다.
- 직원은 1번부터 n번까지 번호가 매겨져 있다. (2 ≤ n, m ≤ 100,000)
- 둘째 줄에는 직원 n명의 직속 상사의 번호가 주어진다.
- 직속 상사의 번호는 자신의 번호보다 작으며, 최종적으로 1번이 사장이다. 1번의 경우, 상사가 없으므로 -1이 입력된다.
- 다음 m줄에는 직속 상사로부터 칭찬을 받은 직원 번호 i, 칭찬의 수치 w가 주어진다. (2 ≤ i ≤ n, 1 ≤ w ≤ 1,000)
- 사장은 상사가 없으므로 칭찬을 받지 않는다.

[출력]
- 1번부터 n번의 직원까지 칭찬을 받은 정도를 출력하시오.

[구현방법]
- 트리에서 DP는 일반 DP보다 점화식이 좀 더 뚜렷한 편이라 '비교적' 쉬운 것 같다(는 망언을 했는데 사과드린다)
- 그래도 트리에서의 DP를 자주 풀어서 좀 익숙해지고자 했음
- 이 문제는 top-down 방식으로 해야할 것 같다. 직속 상관이 누군지 다 아니까 그 상사로 부터 연결된 모든 친구들에게 칭찬만큼 값을 더해주어야한다

- DP문제라는 것을 그새 놓치고 그냥 반복 DFS로 풀려고했다...
- 하.. DFS는 딱 한번만 돌고 DP로 누적된거 가지고 계속 내려가면 된다

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] whoIsTheBoss, DP;
    static boolean[] isVisited;
    static ArrayList<Integer>[] tree;

    // Top-Down으로 내려가면서 칭찬들 전부 누적 진행
    static void DFS(int employee) {
        // 현재 상사에게 누적된 칭찬
        int praise = DP[employee];

        // 나랑 연결된 내 부하들 모두에게 나의 누적 칭찬량을 더해준다 (이것을 반복)
        for (int minion : tree[employee]) {
            DP[minion] += praise;
            DFS(minion);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        DP = new int[N + 1];

        // 트리 초기화
        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        // 직속 상관 기록
        st = new StringTokenizer(br.readLine());
        whoIsTheBoss = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int boss = Integer.parseInt(st.nextToken());
            whoIsTheBoss[i] = boss;

            if (i == 1) continue;  // 사장은 직속상관이 없음
            tree[boss].add(i);
        }

        // 내리 칭찬 시작
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int employee = Integer.parseInt(st.nextToken());
            int praise = Integer.parseInt(st.nextToken());

            DP[employee] += praise;  // 칭찬 받은 사원부터 밑으로 쭉 칭찬 받아야하니까, 칭찬 받은 사원 지점에서 칭찬을 누적해둔다
        }

        // 사장님에서부터 시작~
        DFS(1);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(DP[i]).append(" ");
        }

        System.out.println(sb);
    }
}