

/*
[백준]
24444, 알고리즘 수업 - 너비 우선 탐색 1

[문제파악]
오늘도 서준이는 너비 우선 탐색(BFS) 수업 조교를 하고 있다.
아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.
N개의 정점과 M개의 간선으로 구성된 무방향 그래프(undirected graph)가 주어진다.
정점 번호는 1번부터 N번이고 모든 간선의 가중치는 1이다.
정점 R에서 시작하여 너비 우선 탐색으로 노드를 방문할 경우 노드의 방문 순서를 출력하자.
너비 우선 탐색 의사 코드는 다음과 같다. 인접 정점은 오름차순으로 방문한다.

    bfs(V, E, R) {  # V : 정점 집합, E : 간선 집합, R : 시작 정점
        for each v ∈ V - {R}
            visited[v] <- NO;
        visited[R] <- YES;  # 시작 정점 R을 방문 했다고 표시한다.
        enqueue(Q, R);  # 큐 맨 뒤에 시작 정점 R을 추가한다.
        while (Q ≠ ∅) {
            u <- dequeue(Q);  # 큐 맨 앞쪽의 요소를 삭제한다.
            for each v ∈ E(u)  # E(u) : 정점 u의 인접 정점 집합.(정점 번호를 오름차순으로 방문한다)
                if (visited[v] = NO) then {
                    visited[v] <- YES;  # 정점 v를 방문 했다고 표시한다.
                    enqueue(Q, v);  # 큐 맨 뒤에 정점 v를 추가한다.
                }
        }
    }

[입력]
첫째 줄에 정점의 수 N (5 ≤ N ≤ 100,000), 간선의 수 M (1 ≤ M ≤ 200,000), 시작 정점 R (1 ≤ R ≤ N)이 주어진다.
다음 M개 줄에 간선 정보 u v가 주어지며 정점 u와 정점 v의 가중치 1인 양방향 간선을 나타낸다. (1 ≤ u < v ≤ N, u ≠ v) 모든 간선의 (u, v) 쌍의 값은 서로 다르다.

[출력]
첫째 줄부터 N개의 줄에 정수를 한 개씩 출력한다. i번째 줄에는 정점 i의 방문 순서를 출력한다. 시작 정점의 방문 순서는 1이다. 시작 정점에서 방문할 수 없는 경우 0을 출력한다.

[구현방법]


[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R, seq;
    static boolean[] isVisited;
    static List<Integer>[] graph;
    static int[] visitSequence;

    // BFS 방문
    static void BFS (int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (isVisited[curr]) continue;  // 방문한 이력이 있으면 그냥 패스 (대신 Q에선 삭제 처리)

            // 방문한적 없으면 방문처리하고, 몇 번째 방문인지 기록 남긴다
            isVisited[curr] = true;
            visitSequence[curr] = seq++;  // 방문처리 하는 순간이 방문을 '인정'하는 순간이기 때문에 이때 순서를 기록한다

            // 그래프와 연결된 모든 지점 탐색
            for (int temp : graph[curr]) {
                if (isVisited[temp]) continue;
                q.add(temp);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 정점 갯수
        M = Integer.parseInt(st.nextToken());  // 간선 갯수
        R = Integer.parseInt(st.nextToken());  // 시작 정점

        // 0번 인덱스는 쓰지 않음
        isVisited = new boolean[N + 1];
        visitSequence = new int[N + 1];
        seq = 1;  // 방문 순서 기록 변수

        // 그래프 초기화
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 입력 받기
        for (int i = 0; i < M; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(input.nextToken());
            int B = Integer.parseInt(input.nextToken());

            graph[A].add(B);
            graph[B].add(A);
        }

        // 각 리스트들에 대해 정렬
        for (List<Integer> temp : graph) {
            if (temp.isEmpty()) continue;
            Collections.sort(temp);
        }

        BFS(R);

        // 정답 출력 조건에 맞춰서 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(visitSequence[i]).append("\n");
        }

        System.out.println(sb);
    }
}
