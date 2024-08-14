/*
[백준]
11725, 트리의부모찾기

[문제파악]
- 루트 없는 트리가 주어진다.
- 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하시오.

[입력]
- 첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다.
- 둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.

[출력]
- 첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.

[구현방법]
- 트리 문제는 아직도 온전히 이해가 안됐나보다 왤케 손이 안가고 하기가 싫은건지... 어려워서 더 그런가보다 ㅠ 분발하자!

[보완점]
- DFS로 하면 좀 낫다 근데 DFS가 기억이 안난다
- 이럴게 아니라 BFS DFS부터 좀 다시 파야겠다
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
    static ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
    static boolean[] visited;
    static int[] parents;

    static void dfs(int node) {
        visited[node] = true;  // 방문처리

        for (int i : edges.get(node)) {
            // 방문한 적 없으면 뵈러 가야지!
            if (!visited[i]) {
                dfs(i);
                parents[i] = node;  // 각 노드의 부모노드를 저장해둠
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int nodes = Integer.parseInt(br.readLine());

        // 각 간선들이 어디부터 어디까지 이어져있는지 저장해두기 위한 ArrayList이다
        // 그리고 0번은 안쓰고 index 그대로 쓰기 위해 nodes 갯수까지 만들어줌 (i <= nodes)
        for (int i = 0; i <= nodes; i++) {
            edges.add(new ArrayList<>());
        }

        // 서로 연결된 노드들 정보 등록
        for (int i = 0; i < nodes - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            // 방향 없이 '서로' 연결 되어있기 때문에 두번 추가 (각 노드들 입장에서 서로를 추가인 셈이다)
            edges.get(node1).add(node2);
            edges.get(node2).add(node1);
        }

        // 0번 index부터 안쓰니까 두 배열 모두 다 nodes+1 사이즈만큼 만들어준다
        visited = new boolean[nodes + 1];
        parents = new int[nodes + 1];

        dfs(1);  // 루트인 1번 노드부터 탐색한다

        for (int i = 2; i < parents.length; i++) {
            sb.append(parents[i]).append("\n");  // 문제 조건 : 2번 노드부터 부모를 구하면 된다.
        }

        System.out.println(sb);
    }
}