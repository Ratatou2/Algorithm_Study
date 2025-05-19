/*
[백준]
17182, 우주 탐사선

[문제파악]
- 우주 탐사선 ana호는 어떤 행성계를 탐사하기 위해 발사된다.
- 모든 행성을 탐사하는데 걸리는 최소 시간을 계산하려 한다.
- 입력으로는 ana호가 탐색할 행성의 개수와 ana호가 발사되는 행성의 위치와 ana호가 행성 간 이동을 하는데 걸리는 시간이 2차원 행렬로 주어진다.
- 행성의 위치는 0부터 시작하여 0은 행렬에서 0번째 인덱스에 해당하는 행성을 의미한다.
- 2차원 행렬에서 i, j 번 요소는 i 번째 행성에서 j 번째 행성에 도달하는데 걸리는 시간을 나타낸다.
- i와 j가 같을 때는 항상 0이 주어진다. 모든 행성을 탐사하는데 걸리는 최소 시간을 계산하여라.
- 탐사 후 다시 시작 행성으로 돌아올 필요는 없으며 이미 방문한 행성도 중복해서 갈 수 있다.

[입력]
- 첫 번째 줄에는 행성의 개수 N과 ana호가 발사되는 행성의 위치 K가 주어진다. (2 ≤ N ≤ 10, 0 ≤ K < N)
- 다음 N 줄에 걸쳐 각 행성 간 이동 시간 Tij 가 N 개 씩 띄어쓰기로 구분되어 주어진다. (0 ≤ Tij  ≤ 1000)

[출력]
- 모든 행성을 탐사하기 위한 최소 시간을 출력한다.

[구현방법]
- 비트마스킹으로 풀어보고 싶어서 골랐는데 이거 그냥 탐색 문제 아님?
- 심지어 어딜 들리든 상관없으면 플로이드-워셜 쓰면 될 것 같은데

- 안일한 생각이었음
- 일단 이 문제의 유형은 변형된 TPS임 (traveling Salesman Problem)
- 구현 순서는 아래와 같음
    1) 플로이드-워셜로 이동비용 최소화
    2) DFS + 비트마스킹으로 탐색하면 됨
- ??? 대체 뭐 어떻게 하라고...
- 일단 다른 노드를 경유하더라도 각 노드끼리의 최단 경로를 구해야 함
- 근데 뭐 비트마스킹으로 탐색은 대체...

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] dist;
    static int answer;

    static void DFS(int curr, int isVisited, int totalTime) {
        // 모든 행성을 방문했다면?
        // (1 << N)을 하고 -1을 하는 이유는? (*)
        if (isVisited == (1 << N) - 1) {
            answer = Math.min(answer, totalTime);  // 값 갱신
            return;  // 종료
        }

        // 아직 남아있는 행성들 방문
        for (int next = 0; next < N; next++) {
            // AND 처리했는데 0이다? 방문한적 없다는 소리!
            if ((isVisited & (1 << next)) == 0) {
                // isVisited | (1 << next) : OR 처리를 통해서 next를 방문 처리한 것
                // totalTime + dist[curr][next] : 현재 -> next로 가는 시간 추가
                DFS(next, isVisited | (1 << next), totalTime + dist[curr][next]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 행성 갯수
        K = Integer.parseInt(st.nextToken());  // 시작 위치
        dist = new int[N][N];
        answer = Integer.MAX_VALUE;

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드 워셜써서 경로 최단거리 구하기
        // 어떻게 거쳐가든 최단 경로가 있다면 갱신하는 과정을 거친다
        for (int mid = 0; mid < N; mid++) {
            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    dist[start][end] = Math.min(dist[start][end], dist[start][mid] + dist[mid][end]);
                }
            }
        }

        // 이 상태에서 DFS를 함
        // 근데? 비트마스킹 써서 함...
        // 일단 기본적인 큰 틀은 비트마스킹 = 방문처리와 다를바 없다 그냥 boolean[] isVisited 만들어서 하던거 그냥 자릿값으로 하는거랑 똑같음
        // 비트마스킹으로 하는 이유는? 메모리랑 속도에서 굉장한 이점을 챙겨가니까
        DFS(K, 1 << K, 0);

        System.out.println(answer);
    }
}
/*
## (1 << N)을 하고 -1을 하는 이유는? (*)
- 예를들어 N=4이고 N만큼 1을 비트로 밀면? 10000이다
- 근데? 우리가 사실상 보고 싶은 것은? 1111이죠? 그러면? -1을 해주면 됩니다 (2진수 계산법)
*/