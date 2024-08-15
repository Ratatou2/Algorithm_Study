/*
[백준]
1260, DFS와 BFS

[문제파악]
- 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오.
- 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다.
- 정점 번호는 1번부터 N번까지이다.

[입력]
- 첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다.
- 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다.
- 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다.
- 입력으로 주어지는 간선은 양방향이다.

[출력]
- 첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다.
- V부터 방문된 점을 순서대로 출력하면 된다.

[구현방법]
- 처음엔 Map<Integer, LinkedList<Integer>> map = new HashMap<>();과 같은 방식으로 구현할까 했는데 조건에 부합하지 않았다
    - 연결된 노드들 중 작은 숫자의 노드부터 방문한다
- 따라서 입력 순서가 유지되는 LinkedList는 적합하지 않음
- int[][]가 나을듯...
- 진짜 DFS, BFS는 기초라고 불리는데 다 까먹었다 이걸 어째!!!! (다시하면 되지 뭐...)
- 일단 DFS는 재귀, BFS는 Queue. 이 두가지만 기억하면 반절은 먹고 시작하는 것 같다
- 그 외엔 이제 방문처리를 해줘야하고, 한번 물은 길은 끝까지 탐색하냐(DFS), 아니면 레벨단위로 싹 쓸고 내려가냐(BFS) 차이를 잘 구분하면 될듯하다
- 구현하는 것은 그렇게 어려운게 아니지만 정 매번 짱구 굴리는게 힘들다면 기본적인 틀은 외워도 되지 않을까? 싶다

[보완점]
- 주의해야할 점은 DFS는 어차피 재귀라서 방문처리를 함수가 실행되는 초기 부분에 넣으면 되지만, 이와 달리 BFS는 Queue에서 꺼낼 때마다 해야한다는 것이다
- 둘을 같이 공부하니까 초반에 헷갈려서 적어둠
- 아 그리고 DFS, BFS 둘다 따로니까 isVisited도 까먹지 말고 새로 돌릴 때마다 초기화 해줘야한다
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static StringBuilder sb;
    static boolean[] isVisited;
    static boolean[][] map;
    static Queue<Integer> q;


    // DFS = 재귀 사용하면 깔끔
    public static void DFS(int node) {
        isVisited[node] = true;
        sb.append(node).append(" ");

        // index는 1부터 N까지 할 것임 (이러려고 map과 isVisited를 N + 1 사이즈로 만든 것)
        for (int i = 1; i <= N; i++) {
            if (map[node][i] && !isVisited[i]) {
                DFS(i);
            }
        }
    }

    // BFS = Queue 사용해서 레벨 단위로 싹 쓸기
    public static void BFS(int node) {
        q.add(node);  // 시작 노드를 Queue에 넣어주어야 아래 while문 실행 가능

        while (!q.isEmpty()) {
            int pollNode = q.poll();  // Queue의 첫번째꺼 꺼내기
            isVisited[pollNode] = true;  // 꺼낸 것 방문
            sb.append(pollNode).append(" ");

            for (int i = 1; i <= N; i++) {
                // 방문한 적없고, 현재 노드와 연결되어 있는 곳이라면
                if (!isVisited[i] && map[pollNode][i]) {
                    q.add(i);  // 큐에 추가하고
                    isVisited[i] = true;  // 방문 처리한다
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        map = new boolean[N + 1][N + 1];
        q = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            map[node1][node2] = true;
            map[node2][node1] = true;
        }

        isVisited = new boolean[N + 1];
        DFS(V);
        sb.append("\n");

        isVisited = new boolean[N + 1];
        BFS(V);

        System.out.println(sb);
    }
}