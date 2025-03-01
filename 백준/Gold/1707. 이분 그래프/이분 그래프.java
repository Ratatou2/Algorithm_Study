/*
[백준]
1707, 이분 그래프

[문제파악]
- 그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때, 그러한 그래프를 특별히 이분 그래프 (Bipartite Graph) 라 부른다.
- 그래프가 입력으로 주어졌을 때, 이 그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오.

[입력]
- 입력은 여러 개의 테스트 케이스로 구성되어 있는데, 첫째 줄에 테스트 케이스의 개수 K가 주어진다.
- 각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V와 간선의 개수 E가 빈 칸을 사이에 두고 순서대로 주어진다.
- 각 정점에는 1부터 V까지 차례로 번호가 붙어 있다.
- 이어서 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데, 각 줄에 인접한 두 정점의 번호 u, v (u ≠ v)가 빈 칸을 사이에 두고 주어진다.

    2 ≤ K ≤ 5
    1 ≤ V ≤ 20,000
    1 ≤ E ≤ 200,000

[출력]
- K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.

[구현방법]
- 음... 이거는 DFS 쓰면 될 것 같은데 중복되는 곳이 나오면, 그러니까 방문했던 곳을 가게되면 그땐 이분 트리가 아닌 것..! 아닐까?
- 정점이 많을 때 메모리 효율을 위해 인접 행렬보다는 인접 리스트를 사용하는 것이 유리하다는 것은 문제를 풀다가 배웠음
- 이 분 그래프에 대한 이해가 미숙했던 것 같다
- 좀 더 공부해보니 서로 다른 두 색으로 칠한 노드들이 있다고 가정할 경우, 같은 색끼리는 간선으로 연결되어 있지 않으면 그것은 이분 그래프라고 할 수 있단다
- 즉 탐색하면서 각 노드에 기록을 해두는 것이 중요할듯!
- 근데 계속 드는 생각인데 한 노드를 지점으로 연결된 모든 노드들을 다른 색으로 물들이는 문제라면 BFS가 훨씬 더 쉬울 것이란 생각이 든다
- 그리고 임의의 노드를 시작할 때 시작 색을 빨간색으로 고정해서 물들여도 되는가에 대한 의문이 있었지만, 어차피 노드들이 연결되어 있다면 연결된 애들끼린 방문을 한 번씩은 다할 것이다
- 이 말인 즉, 시작 노드의 색은 중요하지 않다 어차피 방문할거니까 ㅇㅇ
- 내가 놓친 부분은 시작색을 주지 않았다는 점

[보완점]
- 사실 별도의 Node 클래스를 둘 필요는 없었다.
- 색상을 기록할 int 배열 하나, 그리고 서로 연결된 간선을 기록할 ArrayList하나면 되기 때문임
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static boolean isFalse;
    static int[] nodeColors;
    static ArrayList<ArrayList<Integer>> graph;

    static void DFS(int node, int color){
        // 현재 노드 색칠하기
        nodeColors[node] = color;

        // 칠할 색이랑 같으면 이분 그래프가 아니니까 종료
        int paintColor = color == 1 ? 2 : 1;

        // 연결된 노드들 순차 탐색
        for (int connectNode : graph.get(node)) {
            // 색칠할 노드의 색이 현재 노드와의 색과 같다면 return
            if (nodeColors[connectNode] == color) {
                isFalse = true;
                return;
            }

            // 방문한적 없는 노드여야 가능 (이거 없으면 stackOverFlow 남, 당연하게도 방문했던 곳도 무한정 방문할테니)
            if (nodeColors[connectNode] == 0) {
                DFS(connectNode, paintColor);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 시작
        for (int test = 0; test < T; test++) {
            // 그래프 선언 및 초기화
            graph = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < 200_000 + 1; i++) {
                graph.add(new ArrayList<Integer>());
            }

            st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            isFalse = false;

            // 노드 기록할 ArrayList 선언 및 초기화
            nodeColors = new int[V + 1];

            // 간선 입력 받기 (양방향)
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                graph.get(start).add(end);
                graph.get(end).add(start);
            }

            // 모든 노드에 대해 DFS 실행
            for (int i = 1; i <= V; i++) {
                if (nodeColors[i] == 0) {  // 아직 방문하지 않은 노드라면
                    DFS(i, 1);  // 빨간색(1)으로 시작
                }
                if (isFalse) break;
            }

            sb.append(isFalse ? "NO" : "YES").append("\n");
        }

        System.out.println(sb);
    }
}