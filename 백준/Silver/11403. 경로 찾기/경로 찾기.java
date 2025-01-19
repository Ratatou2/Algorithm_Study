/*
[백준]
11403, 경로찾기

[문제파악]
- 가중치 없는 방향 그래프 G가 주어졌을 때, 모든 정점 (i, j)에 대해서, i에서 j로 가는 길이가 양수인 경로가 있는지 없는지 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 정점의 개수 N (1 ≤ N ≤ 100)이 주어진다.
- 둘째 줄부터 N개 줄에는 그래프의 인접 행렬이 주어진다.
- i번째 줄의 j번째 숫자가 1인 경우에는 i에서 j로 가는 간선이 존재한다는 뜻이고, 0인 경우는 없다는 뜻이다.
- i번째 줄의 i번째 숫자는 항상 0이다.

[출력]
- 총 N개의 줄에 걸쳐서 문제의 정답을 인접행렬 형식으로 출력한다.
- 정점 i에서 j로 가는 길이가 양수인 경로가 있으면 i번째 줄의 j번째 숫자를 1로, 없으면 0으로 출력해야 한다.

[구현방법]
- 직접 그려보니 이해됐던 문제
- 별건 없고 한 노드에서 시작해서 갈 수 있는 모든 방향 그래프를 물고 들어가서 도달할 수 있는 모든 노드를 표기해주면 된다
- 예를들어 3번 노트에서 2번 노드 방향으로 이어진 간선이 있다면, 해당 간선을 타고 들어가면서 2번에서 도달할 수 있는 모든 노드들을 표기하면 된다
- 이전에 갔던 노드가 나온다면 이미 갈 수 있는 모든 경로를 탐색한 경우이니 return을 해줘야만 무한루프에서 빠져나올 수 있다

[보완점]
- 알고보니 플루이드 워셜 문제였다..
- 한마디로 되게 간단하게 해결할 수 있었음... ㅠㅠㅠ
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        // 인접 행렬 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드-와샬 알고리즘
        // mid를 중심으로 start와 end까지의 연결을 구한다 (최단 거리의 경우, 가중치를 비교)
        // 즉 start와 mid가 연결되어 있고, mid가 end랑 연결되어 있으면 start와 end도 연결되어 있다고 볼 수 있다는 의미다
        for (int mid = 0; mid < N; mid++) {
            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    if (map[start][mid] == 1 && map[mid][end] == 1) {
                        map[start][end] = 1;
                    }
                }
            }
        }

        // 결과 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}