/*
[백준]
24480, 알고리즘 수업 - 깊이 우선 탐색 2

[문제파악]
- 오늘도 서준이는 깊이 우선 탐색(DFS) 수업 조교를 하고 있다.
- 아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.
- N개의 정점과 M개의 간선으로 구성된 무방향 그래프(undirected graph)가 주어진다.
- 정점 번호는 1번부터 N번이고 모든 간선의 가중치는 1이다.
- 정점 R에서 시작하여 깊이 우선 탐색으로 노드를 방문할 경우 노드의 방문 순서를 출력하자.
- 깊이 우선 탐색 의사 코드는 다음과 같다. 인접 정점은 내림차순으로 방문한다.

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
- 어제 풀었던 내용의 복습문제나 다름없다
- 이번에 내림차순일 뿐 나머지는 똑같다
- 이번 문제 역시 N 최댓값은 100,000이다 이걸 방문 배열로 만들면 100,000^2이니 메모리가 터질 수 있음
- 역시 이번에도 오롯이 혼자 인접리스트로 구현해보자
- 인접리스트 또 까먹는게 배열처럼 길이 먼저 할당해주고나서 각 인덱스에 리스트를 할당해야 들어간다
    - 배열 구조에 리스트를 밀어넣은 것이기 때문에 .add, .put이 아니라는 점 까먹지 말기

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R, index;
    static boolean[] isVisited;
    static int[] visitSequence;
    static List<Integer>[] adjList;

    static void DFS(int start) {
        visitSequence[start] = index++;

        for (int cur : adjList[start]) {
            if (isVisited[cur]) continue;

            isVisited[cur] = true;
            DFS(cur);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 정점의 수
        M = Integer.parseInt(st.nextToken());  // 간선의 수
        R = Integer.parseInt(st.nextToken());  // 시작 정점

        index = 1;
        visitSequence = new int[N + 1];
        isVisited = new boolean[N + 1];
        adjList = new ArrayList[N + 1];  // 먼저 추가할 수 있게 배열의 길이를 선언해준다

        // 선언 해준만큼 새로운 배열을 할당해준다
        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }


        // 연결된 노드들 입력받기
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adjList[start].add(end);
            adjList[end].add(start);
        }

        // 내림차순 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(adjList[i], Collections.reverseOrder());
        }

        // 시작 지점 방문처리 (순번 등록은 DFS 시작지점에서 하고 있으니 없어도 된다)
        isVisited[R] = true;
        DFS(R);

        // 결과 출력 조건에 맞춰 세
        for (int i = 1; i <= N; i++) {
            sb.append(visitSequence[i]).append("\n");
        }

        System.out.println(sb);
    }
}