/*
[백준]
1167, 트리의 지름

[문제파악]
- 트리의 지름이란, 트리에서 임의의 두 점 사이의 거리 중 가장 긴 것을 말한다.
- 트리의 지름을 구하는 프로그램을 작성하시오.

[입력]
- 트리가 입력으로 주어진다.
- 먼저 첫 번째 줄에서는 트리의 정점의 개수 V가 주어지고 (2 ≤ V ≤ 100,000)둘째 줄부터 V개의 줄에 걸쳐 간선의 정보가 다음과 같이 주어진다.
- 정점 번호는 1부터 V까지 매겨져 있다.
- 먼저 정점 번호가 주어지고, 이어서 연결된 간선의 정보를 의미하는 정수가 두 개씩 주어지는데, 하나는 정점번호, 다른 하나는 그 정점까지의 거리이다.
- 예를 들어 네 번째 줄의 경우 정점 3은 정점 1과 거리가 2인 간선으로 연결되어 있고, 정점 4와는 거리가 3인 간선으로 연결되어 있는 것을 보여준다.
- 각 줄의 마지막에는 -1이 입력으로 주어진다.
- 주어지는 거리는 모두 10,000 이하의 자연수이다.

[출력]
- 첫째 줄에 트리의 지름을 출력한다.

[구현방법]
- 저번에 풀어봤던 문제와 똑같다
- 임의의 한 Node에서 가장 먼 노드를 DFS로 찾고, 그렇게 찾은 노드에서 가장 먼 지점을 찾으면 그것이 트리의 지름이 됨
- 내가 맨날 DFS에서 실수하는게 dist를 재는 경우, 그것을 전역 변수로 지정해서 실수하곤 하는데 아예 그냥 DFS의 변수로 할당해버렸다

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int V, farNodeNum, farTempdist;
    static boolean[] isVisited;
    static ArrayList<ArrayList<Node>> tree;

    static class Node {
        int end, dist;

        Node (int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }

    static void DFS(int node, int dist) {
        // 1. 일단 방문한 적 있는지 체크하고, 방문했으면 return / 없으면 방문처리
        if (isVisited[node]) return;
        isVisited[node] = true;

        // 2. 현재 노드의 dist가 최대 거리보다 멀면 갱신하기
        if (farTempdist < dist) {
            farTempdist = dist;
            farNodeNum = node;
        }

        // 3. 현재 노드에서 갈 수 있는 모든 노드 순차적으로 DFS 처리 시작
        for (Node temp : tree.get(node)) {
            if (isVisited[temp.end]) continue;  // 방문한 적 있는 곳이면 패스
            DFS(temp.end, dist + temp.dist);  // 방문한 적 없는 곳이면 진행
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(br.readLine());
        tree = new ArrayList<ArrayList<Node>>();

        // 트리 구조 입력 받기 (최대 10만개)
        for (int i = 0; i < 100_000 + 1; i++) {
            tree.add(new ArrayList<>());
        }

        // 연결된 내용 입력 받기
        StringTokenizer st;
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());

            int nodeNum = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int end = Integer.parseInt(st.nextToken());
                if (end == -1) break;  // -1 입력 받으면 쫑
                int dist = Integer.parseInt(st.nextToken());

                // 단방향이란 말 없으니, 양방향으로 추가
                tree.get(nodeNum).add(new Node(end, dist));
                tree.get(end).add(new Node(nodeNum, dist));
            }
        }


        // 1. 임의 지점에서 DFS 시작 - 가장 멀리 있는 노드 찾기
        isVisited = new boolean[100_000 + 1];
        farTempdist = 0;
        DFS(1, 0);  // DFS 임의의 지점에서 시작

        // 2. 가장 멀리 있는 노드에서 DFS 시작 - 이제 여기서 구한 값이 트리의 지름
        isVisited = new boolean[100_000 + 1];
        farTempdist = 0;
        DFS(farNodeNum, 0);  // DFS 임의의 지점에서 시작

        System.out.println(farTempdist);
    }
}