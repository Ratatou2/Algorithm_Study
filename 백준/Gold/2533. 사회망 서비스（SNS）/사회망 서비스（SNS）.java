/*
[백준]
2533, 사회망 서비스(SNS)

[문제파악]
- 사회망에서 사람들의 친구 관계는 그래프로 표현할 수 있는데, 이 그래프에서 사람은 정점으로 표현되고, 두 정점을 잇는 에지는 두 정점으로 표현되는 두 사람이 서로 친구 관계임을 표현한다.
- 사회망 서비스에 속한 사람들은 얼리 아답터이거나 얼리 아답터가 아니다.
- 얼리 아답터가 아닌 사람들은 자신의 모든 친구들이 얼리 아답터일 때만 이 아이디어를 받아들인다.
- 어떤 아이디어를 사회망 서비스에서 퍼뜨리고자 할 때, 가능한 한 최소의 수의 얼리 아답터를 확보하여 모든 사람이 이 아이디어를 받아들이게 하는 문제는 매우 중요하다.
- 일반적인 그래프에서 이 문제를 푸는 것이 매우 어렵다는 것이 알려져 있기 때문에, 친구 관계 그래프가 트리인 경우, 즉 모든 두 정점 사이에 이들을 잇는 경로가 존재하면서 사이클이 존재하지 않는 경우만 고려한다.
- 예를 들어, 8명의 사람으로 이루어진 다음 친구 관계 트리를 생각해보자.
- 2, 3, 4번 노드가 표현하는 사람들이 얼리 아답터라면, 얼리 아답터가 아닌 사람들은 자신의 모든 친구가 얼리 아답터이기 때문에 새로운 아이디어를 받아들인다.
- 친구 관계 트리가 주어졌을 때, 모든 개인이 새로운 아이디어를 수용하기 위하여 필요한 최소 얼리어답터의 수를 구하는 프로그램을 작성하시오.

[입력]
- 첫 번째 줄에는 친구 관계 트리의 정점 개수 N이 주어진다.
- 단, 2 ≤ N ≤ 1,000,000이며, 각 정점은 1부터 N까지 일련번호로 표현된다.
- 두 번째 줄부터 N-1개의 줄에는 각 줄마다 친구 관계 트리의 에지 (u, v)를 나타내는 두 정수 u와 v가 하나의 빈칸을 사이에 두고 주어진다.

[출력]
- 주어진 친구 관계 그래프에서 아이디어를 전파하는데 필요한 얼리 아답터의 최소 수를 하나의 정수로 출력한다.

[구현방법]
- 트리에서 타고 내려갈 때 몇 명의 인원이, 정확히는 누가 얼리어답터면 가장 많은 인원들이 전파되는지 계속 기억하면서 타고 내려가야한다
- 이 문제가 DP로 분류된 이유는 그런 문제 + 또한 트리여서 DP + 트리 문제인 것이다

- 이 아이디어의 요지는 자식부터 DP를, DFS 탐색으로 시작한다는 것이다
- 또한 DP를 2가지 경우로 분리한다
    1) DP[node][0] -> node가 얼리어답터가 아닌 경우, 현재 이 서브트리를 커버하기 위한 최소 얼리어답터 수
    2) DP[node][1] -> node가 얼리어답터인 경우, 현재 이 서브트리를 커버하기 위한 최소 얼리어답터 수
- DFS로 서브트리 전체를 돌면서 자식부터 DP값을 계산해서 부모로 올라가는 Bottom-Up 방식
- 다만 이때, 둘의 조건이 살짝 다른게 있다
    1) 현재 node가 얼리어답터인 경우
        - 자식이 얼리어답터인지 아닌지 알바 없다 (어차피 내가 얼리어답터니까 주변인들은 전파할 수 있음)
        - [얼리] or [얼리 아님] 둘 중에 더 작은 값 가져다 쓰면 된다
    2) 현재 node가 얼리어답터가 아닌 경우
        - 이 경우엔 자식은 무조건 얼리어답터여야 한다
        - 얼리어답터여야만 내(=현재 노드)가 전파 받을 수 있기 때문이다

[보완점]
StringTokenizer를 for문 내부에서 매번 새로 만드는 것과 있는 것에 새로 할당하는 것의 효율성 차이
- 어떤게 더 효율적인지 늘 궁금했는데 아래와 같단다
    - 한줄 요약하면 새로 생성하는게 굉장히 작은 리소스를 소모하기 때문에 for문 외부에서 쓰이는지 확인하고 하는 번거로움 배제와 안정성을 위해서라도 새로 생성하는게 나음

    방법           성능     가독성       안정성
    매번 새로 생성	✅ 빠름	✅ 명확함	✅ 안전
    재사용 선언	✅ 동일	❌ 범위 넓어짐	⚠️ 실수 가능

- 문제를 좀 정리하면, 일단 내 자신이 얼리어답터인가 아닌가, 2가지 경우 수를 제일 밑바닥의 자식부터 따지면서 계산해 올라가는 방식으로 풀어야한다
- 트리 구조이지만 방향이 없기 때문에 가능한 방법이다 또한 자식부터 부모까지 올라가는 Bottom-Up 방식이기 때문에, DFS로 계속 재귀로 타고 들어간다
*/

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] tree;
    static int[][] dp;
    static boolean[] visited;

    static void dfs(int node) {
        // 자기 자신에 대한 처리, 자기 자신이 얼리어답터라면 +1을 해줘야하니까 아래처럼 방문처리하고 0, 1로 기록해두는 것이다
        visited[node] = true;
        dp[node][0] = 0;   // node가 얼리어답터가 아닐 경우, 자기 자신은 계산에 안들어가니까 0
        dp[node][1] = 1;   // node가 얼리어답터일 경우, 자기 자신도 계산에 넣어야하니까 1

        // 현 노드에 연결된 모든 노드들을 방문
        for (int child : tree[node]) {
            // 방문한 적 있으면 패스
            if (visited[child]) continue;

            dfs(child);

            // 현재 노드가 얼리어답터이다 아니다 모두 기록해둠 (그래야 계속 누적하고 비교해서 정답을 찾지)
            dp[node][0] += dp[child][1];  // node가 얼리 아답터가 아니면 자식은 반드시 얼리 아답터
            dp[node][1] += Math.min(dp[child][0], dp[child][1]);  // node가 얼리 아답터면 자식은 얼리 아답터일 수도 있고 아닐 수도 있음 (그러니까 둘 중 더 작은 값을 더함)
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N + 1];
        dp = new int[N + 1][2];  // 현재 노드가 얼리어답터가 [맞다], [아니다] 두개를 다 기록하기 위해 배열을 하나 더 만듦
        visited = new boolean[N + 1];

        // tree 노드 초기화
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        // 노드 연결된 구조 입력 받기
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 양방향 추가
            tree[u].add(v);
            tree[v].add(u);
        }

        dfs(1);  // 루트는 1번 노드

        System.out.println(Math.min(dp[1][0], dp[1][1]));  // 최소 얼리어답터 수 출력
    }
}