/*
[백준]
3584, 가장 가까운 공통 조상

[문제파악]
- 루트가 있는 트리(rooted tree)가 주어지고, 그 트리 상의 두 정점이 주어질 때 그들의 가장 가까운 공통 조상(Nearest Common Ancestor)은 다음과 같이 정의됩니다.
- 두 노드의 가장 가까운 공통 조상은, 두 노드를 모두 자손으로 가지면서 깊이가 가장 깊은(즉 두 노드에 가장 가까운) 노드를 말합니다.
- 예를 들어 15와 11를 모두 자손으로 갖는 노드는 4와 8이 있지만, 그 중 깊이가 가장 깊은(15와 11에 가장 가까운) 노드는 4 이므로 가장 가까운 공통 조상은 4가 됩니다.
- 루트가 있는 트리가 주어지고, 두 노드가 주어질 때 그 두 노드의 가장 가까운 공통 조상을 찾는 프로그램을 작성하세요

[입력]
- 첫 줄에 테스트 케이스의 개수 T가 주어집니다.
- 각 테스트 케이스마다, 첫째 줄에 트리를 구성하는 노드의 수 N이 주어집니다. (2 ≤ N ≤ 10,000)
- 그리고 그 다음 N-1개의 줄에 트리를 구성하는 간선 정보가 주어집니다.
- 한 간선 당 한 줄에 두 개의 숫자 A B 가 순서대로 주어지는데, 이는 A가 B의 부모라는 뜻입니다. (당연히 정점이 N개인 트리는 항상 N-1개의 간선으로 이루어집니다!)
- A와 B는 1 이상 N 이하의 정수로 이름 붙여집니다.
- 테스트 케이스의 마지막 줄에 가장 가까운 공통 조상을 구할 두 노드가 주어집니다.

[출력]
- 각 테스트 케이스 별로, 첫 줄에 입력에서 주어진 두 노드의 가장 가까운 공통 조상을 출력합니다.

[구현방법]
- 트리 문제니까, 각가의 노드의 조상을 기록해둔다음, 역 DFS로 조상을 탐색, 가장 가까운 공통 조상을 만나는 순간 return 해버리면 되지 않을까?
- 또한 자기 자신도 부모 노드가 될 수 있음을 잊지 말자
[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static boolean isSecond;
    static int[] whoIsParent;
    static HashSet<Integer> myAncestors;
    static StringBuilder sb;

    static void DFS(int node) {
        // 두번째 노드 탐색이면서, 자기 자신이 조상에 포함된다면 더 볼 것없이 끝내면 된다
        if (isSecond && myAncestors.contains(node)) {
            sb.append(node).append("\n");
            return;
        }

        int myParent = whoIsParent[node];

        // 루트 노드면 자기 자신 추가하고 끝낸다
        if (myParent == 0) {
            myAncestors.add(node);
            return;
        }

        // 첫번째 - node_A의 조상을 기록
        // 두번째 - node_B의 조상을 보며 A와 공통되는 사람이 있는지 확인
        if (!isSecond) myAncestors.add(myParent);
        else {
            if (myAncestors.contains(myParent)) {
                sb.append(myParent).append("\n");
                return;
            }
        }

        // 내 부모 노드의 부모 노드를 찾도록 DFS 재귀
        DFS(myParent);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        sb = new StringBuilder();

        for (int test = 0; test < T; test++) {
            int N = Integer.parseInt(br.readLine());
            whoIsParent = new int[N + 1];
            myAncestors = new HashSet<>();
            isSecond = false;

            // 트리 입력 받기
            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());

                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                whoIsParent[child] = parent;
            }

            // 공통 조상을 구할 두 노드
            st = new StringTokenizer(br.readLine());
            int node_A = Integer.parseInt(st.nextToken());
            int node_B = Integer.parseInt(st.nextToken());

            myAncestors.add(node_A);  // 자기 자신도 루트 노드가 될 수 있음
            DFS(node_A);
            isSecond = true;  // 두번째 노드 탐색은 조상 Set에 추가할 것없이 있는지 여부만 체크하면 된다
            DFS(node_B);
        }

        System.out.println(sb);
    }
}