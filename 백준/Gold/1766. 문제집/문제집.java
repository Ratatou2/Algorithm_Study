import java.io.*;
import java.util.*;

/*
[백준]
1766, 문제집

[문제파악]
- 민오는 1번부터 N번까지 총 N개의 문제로 되어 있는 문제집을 풀려고 한다.
- 문제는 난이도 순서로 출제되어 있다.
- 즉 1번 문제가 가장 쉬운 문제이고 N번 문제가 가장 어려운 문제가 된다.
- 어떤 문제부터 풀까 고민하면서 문제를 훑어보던 민오는, 몇몇 문제들 사이에는 '먼저 푸는 것이 좋은 문제'가 있다는 것을 알게 되었다.
- 예를 들어 1번 문제를 풀고 나면 4번 문제가 쉽게 풀린다거나 하는 식이다.
- 민오는 다음의 세 가지 조건에 따라 문제를 풀 순서를 정하기로 하였다.
- N개의 문제는 모두 풀어야 한다.
- 먼저 푸는 것이 좋은 문제가 있는 문제는, 먼저 푸는 것이 좋은 문제를 반드시 먼저 풀어야 한다.
- 가능하면 쉬운 문제부터 풀어야 한다.
- 예를 들어서 네 개의 문제가 있다고 하자. 4번 문제는 2번 문제보다 먼저 푸는 것이 좋고, 3번 문제는 1번 문제보다 먼저 푸는 것이 좋다고 하자.
- 만일 4-3-2-1의 순서로 문제를 풀게 되면 조건 1과 조건 2를 만족한다.
- 하지만 조건 3을 만족하지 않는다. 4보다 3을 충분히 먼저 풀 수 있기 때문이다.
- 따라서 조건 3을 만족하는 문제를 풀 순서는 3-1-4-2가 된다.
- 문제의 개수와 먼저 푸는 것이 좋은 문제에 대한 정보가 주어졌을 때, 주어진 조건을 만족하면서 민오가 풀 문제의 순서를 결정해 주는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 문제의 수 N(1 ≤ N ≤ 32,000)과 먼저 푸는 것이 좋은 문제에 대한 정보의 개수 M(1 ≤ M ≤ 100,000)이 주어진다.
- 둘째 줄부터 M개의 줄에 걸쳐 두 정수의 순서쌍 A,B가 빈칸을 사이에 두고 주어진다.
- 이는 A번 문제는 B번 문제보다 먼저 푸는 것이 좋다는 의미이다.
- 항상 문제를 모두 풀 수 있는 경우만 입력으로 주어진다.

[출력]
- 첫째 줄에 문제 번호를 나타내는 1 이상 N 이하의 정수들을 민오가 풀어야 하는 순서대로 빈칸을 사이에 두고 출력한다.

[구현방법]
- 뭔가 PQ만 구현한다고 되는 것은 아닌 것 같고 탐색이 필연적으로 따라가는 것 같다.
- 그렇다면 서로간의 우열(선행 문제가 있는 것)은 어떻게 기록할 것인지
- 그냥 모든 문제를 다 풀 수 있다니까 풀었는지 확인하는 배열이랑 PQ로 된 배열하나 만들어서 순차적으로 입력하면 될듯
- 그러니까 PQ로 이뤄진 배열을 만들고 각 문제에 선행되어야 하는 문제가 있으면 그 PQ에 밀어넣는 것임
- 근데 이건 또 한문제에 선행되어야하는게 여러개이면... 또 모를 일임
- 일단 틀렸고요 DFS로 탐색하면 풀리려나

[보완점]
- 위상 정렬이 적합한 문제라고 함
- 그게 뭔데...
- 공부 해보니 아래와 같다

위상정렬이란?
- 방향성이 있는 그래프(Directed Acyclic Graph, DAG)에서 정점들을 선형 순서로 정렬하는 알고리즘
- 이때, 정렬된 순서에서는 모든 간섭 (u → v)에서 정점 u가 정점 v보다 앞에 위치해야 함
- 방향 비순환 그래프에서만 가능함
    - 순환이 없어야한다는 의미
- 여러 개의 정렬 결과가 나올 수 있음
    - 순서를 만족하는 여러 가지 정렬 방법이 존재할 수 있음
- 진입 차수를 고려한 알고리즘과 DFS 기반 알고리즘 두가지임
    - 진입차수란? 특정 노드로 들어오는 간선의 개수를 의미함
- 그래서 특히, 컴파일러의 의존성 해결 - 선행되어 컴파일 해야하는 파일 찾을 때 쓴다고..
    - 제법? 진짜 똑똑하다고 생각함;;;

- 어렵다 어려워.. 위상정렬 개념은 처음이니 나중에 다시 풀어보자
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 문제의 수
        int M = Integer.parseInt(st.nextToken()); // 간선의 수

        // 그래프 초기화
        ArrayList<Integer>[] adj = new ArrayList[N + 1]; // 인접 리스트
        int[] indegree = new int[N + 1]; // 진입 차수 배열

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        // 간선 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adj[A].add(B); // A -> B (A를 먼저 풀어야 B를 풀 수 있음)
            indegree[B]++; // B의 진입 차수 증가 (즉, A가 풀려야 B를 풀 수 있으니까 B는 풀 수 있는 순서를 저~ 뒤로 밀어두는 것)
        }

        // 최소 힙 (우선순위 큐) 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 진입 차수가 0인 문제들을 PQ에 추가 (선행되어야할 문제가 없는, 바로 풀 수 있는 친구들)
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) pq.add(i);
        }

        // 위상 정렬 수행
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int now = pq.poll();  // 가장 쉬운 문제(번호 작은 것)를 꺼냄
            sb.append(now).append(" ");

            // 현재 문제를 푼 후 영향을 받는 문제들 처리
            // 배열을 순차 탐색하기 때문에 자연스레 작은 문제들(쉬운 문제들)부터 풀 수 있다
            for (int next : adj[now]) {
                indegree[next]--;  // 선행 문제가 해결됐으므로 진입 차수 감소 (자신의 차례가 하나 가까워진 것으로 이해하면 된다)

                // 진입 차수가 0이 되면 PQ에 삽입
                if (indegree[next] == 0) pq.add(next);
            }
        }

        System.out.println(sb);
    }
}