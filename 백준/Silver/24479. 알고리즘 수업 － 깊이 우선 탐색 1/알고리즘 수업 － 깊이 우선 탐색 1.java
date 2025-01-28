/*
[백준]
24479, 알고리즘 수업 - 깊이 우선 탐색 1

[문제파악]
- 오늘도 서준이는 깊이 우선 탐색(DFS) 수업 조교를 하고 있다.
- 아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.
- N개의 정점과 M개의 간선으로 구성된 무방향 그래프(undirected graph)가 주어진다.
- 정점 번호는 1번부터 N번이고 모든 간선의 가중치는 1이다.
- 정점 R에서 시작하여 깊이 우선 탐색으로 노드를 방문할 경우 노드의 방문 순서를 출력하자.
- 깊이 우선 탐색 의사 코드는 다음과 같다. 인접 정점은 오름차순으로 방문한다.

[입력]
- 첫째 줄에 정점의 수 N (5 ≤ N ≤ 100,000), 간선의 수 M (1 ≤ M ≤ 200,000), 시작 정점 R (1 ≤ R ≤ N)이 주어진다.
- 다음 M개 줄에 간선 정보 u v가 주어지며 정점 u와 정점 v의 가중치 1인 양방향 간선을 나타낸다. (1 ≤ u < v ≤ N, u ≠ v)
- 모든 간선의 (u, v) 쌍의 값은 서로 다르다.

[출력]
- 첫째 줄부터 N개의 줄에 정수를 한 개씩 출력한다.
- i번째 줄에는 정점 i의 방문 순서를 출력한다.
- 시작 정점의 방문 순서는 1이다.
- 시작 정점에서 방문할 수 없는 경우 0을 출력한다.

[구현방법]
- DFS를 구현한다
- 주의해야할 점은 양방향 간선이라는 점과 방문처리이다
- 맨날 헷갈리는데 방문을 결정하게 되면, 즉 다음 DFS로 가게 되면 그때 방문처리하고 넘어가도 된다
    - DFS 도착하자마자 방문처리 안해도 된다는 의미. 위 방식이 더 확실하기도 하고

[보완점]
- 메모리 초과 -> 인접리스트 사용 필요
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int[] visitSequence;
    static boolean[] isVisited;
    static List<Integer>[] adjList;
    static int order = 1;

    static void DFS(int start) {
        visitSequence[start] = order++;  // 방문 순서 기록

        for (int next : adjList[start]) {
            if (!isVisited[next]) {
                isVisited[next] = true;  // 방문 처리
                DFS(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 정점의 수
        M = Integer.parseInt(st.nextToken());  // 간선의 수
        R = Integer.parseInt(st.nextToken());  // 시작 정점

        // 초기화
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        visitSequence = new int[N + 1];
        isVisited = new boolean[N + 1];

        // 간선 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adjList[start].add(end);
            adjList[end].add(start);
        }

        // 인접 리스트 정렬 (오름차순 방문)
        for (int i = 1; i <= N; i++) {
            Collections.sort(adjList[i]);
        }

        // DFS 시작
        isVisited[R] = true;
        DFS(R);

        // 결과 출력
        for (int i = 1; i <= N; i++) {
            sb.append(visitSequence[i]).append("\n");
        }
        System.out.println(sb);
    }
}