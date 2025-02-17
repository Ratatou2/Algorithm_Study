/*
[백준]
18352, 특정 거리의 도시 찾기

[문제파악]
- 어떤 나라에는 1번부터 N번까지의 도시와 M개의 단방향 도로가 존재한다. 모든 도로의 거리는 1이다.
- 이 때 특정한 도시 X로부터 출발하여 도달할 수 있는 모든 도시 중에서, 최단 거리가 정확히 K인 모든 도시들의 번호를 출력하는 프로그램을 작성하시오.
- 또한 출발 도시 X에서 출발 도시 X로 가는 최단 거리는 항상 0이라고 가정한다.
- 예를 들어 N=4, K=2, X=1일 때 다음과 같이 그래프가 구성되어 있다고 가정하자.
- 이 때 1번 도시에서 출발하여 도달할 수 있는 도시 중에서, 최단 거리가 2인 도시는 4번 도시 뿐이다.
- 2번과 3번 도시의 경우, 최단 거리가 1이기 때문에 출력하지 않는다.

[입력]
- 첫째 줄에 도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X가 주어진다. (2 ≤ N ≤ 300,000, 1 ≤ M ≤ 1,000,000, 1 ≤ K ≤ 300,000, 1 ≤ X ≤ N)
- 둘째 줄부터 M개의 줄에 걸쳐서 두 개의 자연수 A, B가 공백을 기준으로 구분되어 주어진다.
- 이는 A번 도시에서 B번 도시로 이동하는 단방향 도로가 존재한다는 의미다. (1 ≤ A, B ≤ N)
- 단, A와 B는 서로 다른 자연수이다.

[출력]
- X로부터 출발하여 도달할 수 있는 도시 중에서, 최단 거리가 K인 모든 도시의 번호를 한 줄에 하나씩 오름차순으로 출력한다.
- 이 때 도달할 수 있는 도시 중에서, 최단 거리가 K인 도시가 하나도 존재하지 않으면 -1을 출력한다.

[구현방법]
- 다익스트라 알고리즘이며 DP 문제
- 다익스트라에 익숙해지고자 풀기 시작 했음
- 원리는 이해하고 있지만 어떻게 구현하는지는 잘 모름
- 공부해보니 다익스트라 알고리즘은 기본적으로 아래 규칙을 따른다
    1) 음의 가중치 없는 경우에만 사용 가능 
        - 벨만-포드 알고리즘이 음의 가중치가 있을 때 사용, 시간 복잡도는 더 높음
    2) 방문하지 않은 노드 중에서 가장 비용이 적은 노드를 선택 
        - PQ를 사용하는 이유이자, 그리디 알고리즘의 개념이 사용되는 부분
        - 따라서 현재 상태에서 가장 좋은 선택을 하며, 최단 경로가 확정된 노드가 됨
        - 한번 선택된 노드는 다시 방문 X
    3) 해당 노드로부터 갈 수 있는 노드들의 비용을 갱신
        - cost를 계산하여 최소 비용으로 갱신 함
        - 갱신조건 : 현재 노드까지 비용 + 인접 노드까지 비용 < 기존 인접 노드 비용

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K, X;
    static List<List<Node>> graph;
    static int[] distance;
    static final int INF = Integer.MAX_VALUE;

    // Node 클래스 (도시, 거리)
    static class Node implements Comparable<Node> {
        int city;
        int cost;

        Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost; // cost 오름차순 정렬
        }
    }

    // 다익스트라 알고리즘
    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));  // PQ에 시작지점 넣기, 자기 자신은 cost가 0

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentCity = current.city;
            int currentCost = current.cost;

            // 현재 노드가 이미 처리된 노드라면 무시
            if (distance[currentCity] < currentCost) continue;

            // 인접 노드 순차적으로 확인
            for (Node next : graph.get(currentCity)) {
                int nextCity = next.city;
                int nextCost = currentCost + next.cost;

                // 기록된 경로보다, 더 짧은 경로를 발견한 경우
                if (nextCost < distance[nextCity]) {
                    distance[nextCity] = nextCost;
                    pq.add(new Node(nextCity, nextCost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 입력 받기
        N = Integer.parseInt(st.nextToken()); // 도시 개수
        M = Integer.parseInt(st.nextToken()); // 도로 개수
        K = Integer.parseInt(st.nextToken()); // 목표 거리
        X = Integer.parseInt(st.nextToken()); // 출발 도시

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 도로 정보 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, 1)); // 거리 1 고정
        }

        // 거리 배열 초기화
        distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[X] = 0; // 출발 도시까지의 거리는 0

        // 다익스트라 알고리즘 시작
        dijkstra(X);

        // 결과 출력 세팅
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            // 구하는 거리와 같은 거리의 노드는 결과에 추가
            if (distance[i] == K) result.add(i);
        }

        // 비어 있으면 -1 출력, 그게 아니라면 오름차순으로 출력
        if (result.isEmpty()) sb.append("-1");
        else {
            Collections.sort(result); // 오름차순 정렬

            for (int city : result) sb.append(city + "\n");
        }

        System.out.println(sb);
    }
}