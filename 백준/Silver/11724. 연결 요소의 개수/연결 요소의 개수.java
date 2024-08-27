/*
[백준]


[문제파악]
- 방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2)
- 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.

[출력]
- 첫째 줄에 연결 요소의 개수를 출력한다.

[구현방법]
- DFS와 BFS로 풀어보자!

[보완점]
- DFS할 때는 들어오자마자 방문처리 필수다!

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[] isVisited;
    static boolean[][] connection;

    static void DFS(int index) {
        if (isVisited[index]) return;

        isVisited[index] = true;
        for (int i = 1; i <= N; i++) {
            if (connection[index][i] && !isVisited[i]) {
                DFS(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 정점의 개수
        M = Integer.parseInt(st.nextToken());  // 간선의 개수
        int count = 0;

        connection = new boolean[N + 1][N + 1];
        isVisited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            connection[node1][node2] = true;
            connection[node2][node1] = true;
        }

        for (int i = 1; i <= N; i++) {
            if (!isVisited[i]) {
                DFS(i);
                count++;
            }
        }

        System.out.println(count);
    }
}