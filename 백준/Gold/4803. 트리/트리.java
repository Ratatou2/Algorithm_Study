

/*
[백준]
4803, 트리

[문제파악]
그래프는 정점과 간선으로 이루어져 있다.
두 정점 사이에 경로가 있다면, 두 정점은 연결되어 있다고 한다.
연결 요소는 모든 정점이 서로 연결되어 있는 정점의 부분집합이다.
그래프는 하나 또는 그 이상의 연결 요소로 이루어져 있다.
트리는 사이클이 없는 연결 요소이다. 트리에는 여러 성질이 있다.
예를 들어, 트리는 정점이 n개, 간선이 n-1개 있다.
또, 임의의 두 정점에 대해서 경로가 유일하다.
그래프가 주어졌을 때, 트리의 개수를 세는 프로그램을 작성하시오.

[입력]
입력은 여러 개의 테스트 케이스로 이루어져 있다.
각 테스트 케이스의 첫째 줄에는 n ≤ 500과 m ≤ n(n-1)/2을 만족하는 정점의 개수 n과 간선의 개수 m이 주어진다.
다음 m개의 줄에는 간선을 나타내는 두 개의 정수가 주어진다.
같은 간선은 여러 번 주어지지 않는다.
정점은 1번부터 n번까지 번호가 매겨져 있다.
입력의 마지막 줄에는 0이 두 개 주어진다.

[출력]
입력으로 주어진 그래프에 트리가 없다면 "No trees."를, 한 개라면 "There is one tree."를, T개(T > 1)라면 "A forest of T trees."를 테스트 케이스 번호와 함께 출력한다.

[구현방법]
- 일단 List로 입력 받으면서 상호 연결로 저장해둔다
- 다 저장하고 하나씩 탐색(BFS)하면서 순환구조가 되는지 확인하면 되는데... 루트를 찾지 못하면 이건 의미가 없지 않나?
- 또한 이어져 있지 않다고 하더라도 '트리의 갯수'를 세야하기 때문에 흠..

[보완점]
- List<List<Integer>>보단 List<Integer>[]가 더 낫다
*/

import java.io.*;
import java.util.*;

public class Main {
    static boolean[] isVisited;
    static List<Integer>[] tree;
    static boolean isCycle;

    static void DFS(int curr, int parent) {
        isVisited[curr] = true;

        for (int i : tree[curr]) {
            if (i == parent) continue;
            if (isVisited[i]) {
                isCycle = true;
                return;
            } else {
                DFS(i, curr);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());  // 정점의 갯수
            int M = Integer.parseInt(st.nextToken());  // 간선의 갯수

            // 중단 조건
            if (N == 0 && M == 0) break;

            isVisited = new boolean[N + 1];

            // 정점의 갯수만큼 List 만들어 집어넣기
            tree = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                tree[i] = new ArrayList<>();
            }

            // 간선 입력 받기
            for (int i = 0; i < M; i++) {
                StringTokenizer nodes = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(nodes.nextToken());
                int B = Integer.parseInt(nodes.nextToken());

                tree[A].add(B);
                tree[B].add(A);
            }

//            StringBuilder sb = new StringBuilder();
//            for (int i = 1; i <= N; i++) {
//                if (tree[i].isEmpty()) continue;
//
//                sb.append("[" + i + "] ");
//                for (int j : tree[i]) {
//                    sb.append(j + " ");
//                }
//
//                sb.append("\n");
//            }
//
//            System.out.println(sb);

            // 하나씩 방문하면서 DFS로 전체 탐색 진행
            int treeCount = 0;
            for (int i = 1; i <= N; i++) {
                if (isVisited[i]) continue;
                isCycle = false;
                DFS(i,0);

                if (!isCycle) treeCount++;  // 내가 실수한 부분인데 이 문제의 목표는 '트리의 갯수를 세는 것이다' 그러니까 사이클을 발견해도 중단할 이유가 없음 (다른 분단된 곳에 트리 구조가 있을 수 으니까)

            }

            sb.append("Case " + T + ": ");
            if (treeCount == 0) sb.append("No trees.");  // 트리 유무는 갯수로 판단해야한다 (isCycle 쓰면 안됨, 사이클이 있건말건 트리 갯수를 결과값으로 쓰기 때문임)
            else if (treeCount == 1) sb.append("There is one tree.");
            else sb.append("A forest of " + treeCount + " trees.");
            sb.append("\n");

            T++;  // 테스트 케이스 변수 +1
        }

        System.out.println(sb);
    }
}
