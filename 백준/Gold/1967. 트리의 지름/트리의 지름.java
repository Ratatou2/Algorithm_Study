/*
[백준]
1967, 트리의 지름

[문제파악]
- 트리(tree)는 사이클이 없는 무방향 그래프이다.
- 트리에서는 어떤 두 노드를 선택해도 둘 사이에 경로가 항상 하나만 존재하게 된다.
- 트리에서 어떤 두 노드를 선택해서 양쪽으로 쫙 당길 때, 가장 길게 늘어나는 경우가 있을 것이다.
- 이럴 때 트리의 모든 노드들은 이 두 노드를 지름의 끝 점으로 하는 원 안에 들어가게 된다.

- 이런 두 노드 사이의 경로의 길이를 트리의 지름이라고 한다.
- 정확히 정의하자면 트리에 존재하는 모든 경로들 중에서 가장 긴 것의 길이를 말한다.
- 입력으로 루트가 있는 트리를 가중치가 있는 간선들로 줄 때, 트리의 지름을 구해서 출력하는 프로그램을 작성하시오.
- 아래와 같은 트리가 주어진다면 트리의 지름은 45가 된다.

- 트리의 노드는 1부터 n까지 번호가 매겨져 있다.

[입력]
- 파일의 첫 번째 줄은 노드의 개수 n(1 ≤ n ≤ 10,000)이다.
- 둘째 줄부터 n-1개의 줄에 각 간선에 대한 정보가 들어온다.
- 간선에 대한 정보는 세 개의 정수로 이루어져 있다.
- 첫 번째 정수는 간선이 연결하는 두 노드 중 부모 노드의 번호를 나타내고, 두 번째 정수는 자식 노드를, 세 번째 정수는 간선의 가중치를 나타낸다.
- 간선에 대한 정보는 부모 노드의 번호가 작은 것이 먼저 입력되고, 부모 노드의 번호가 같으면 자식 노드의 번호가 작은 것이 먼저 입력된다.
- 루트 노드의 번호는 항상 1이라고 가정하며, 간선의 가중치는 100보다 크지 않은 양의 정수이다.

[출력]
- 첫째 줄에 트리의 지름을 출력한다.

[구현방법]
- 처음엔 서로 가장 먼 지점을 생각했으나 가중치가 있으므로 다익스트라마냥 고려해서 경로를 짜야한다
- DFS로 한 지점에서 다른 지점까지 탐색 가능한 방향을 찾아야할 것 같다
- 특히, 노드가 1만개까지 들어올 수 있으니까 배열로 만들면 터질 것 같고, ArrayList로 만들어야 할듯

[보완점]
- 모든 노드에서 DFS로 찾는 것은 비효율적임
- 랜덤한 한 노드에서 DFS로 현 위치에서 가장 멀리 있는 노드를 찾은 다음, 그 노드에서 가장 먼 곳의 노드를 찾는게 더 효율적이다
- 이런식으로 구성할 경우 두번의 DFS로 답을 찾을 수 있음 (사람들 진짜 천재네..)

[예제]
12
1 2 3
1 3 2
2 4 5
3 5 11
3 6 9
4 7 1
4 8 7
5 9 15
5 10 4
6 11 6
6 12 10

[예제-답]
45
*/


import java.io.*;
import java.util.*;

public class Main {
    static int N, maxDistance, farthestNode;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> tree;

    public static class Node {
        int end, weight;

        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

//        @Override
//        public String toString() {
//            return "end : " + end + "\nweight : " + weight;
//        }
    }

    static void DFS(int node, int distance) {
        if (visited[node]) return;
        visited[node] = true;

        // 누적된 현재 거리가 기록된 기록보다 더 멀면 갱신
        if (maxDistance < distance) {
            maxDistance = distance;
            farthestNode = node;  // 두번쨰 DFS 탐색 때는 필요 없는 값임
        }

        // 연결된 노드들 전부 탐색
        for (Node next : tree.get(node)) {
            DFS(next.end, distance + next.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();

        // 노드 리스트 초기화 (특히 노드 1만개 가능하니까 미리 넣어둔다)
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        // 입력값 받기 (N-1개의 줄이라고 했으니 N-1로 진행)
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 양방향으로 추가
            tree.get(start).add(new Node(end, weight));
            tree.get(end).add(new Node(start, weight));
        }

        // 1) 임의의 노드(1번)에서 가장 먼 노드 찾기
        visited = new boolean[N + 1];
        maxDistance = 0;
        DFS(1, 0);

        // 2) 임의의 노드에서 가장 멀었던 노드에서 다시 DFS 수행하여 트리의 지름 계산
        visited = new boolean[N + 1];
        maxDistance = 0;
        DFS(farthestNode, 0);

        System.out.println(maxDistance);
    }
}