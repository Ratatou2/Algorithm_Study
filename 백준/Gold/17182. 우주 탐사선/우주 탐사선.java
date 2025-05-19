import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] dist;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 행성 개수
        K = Integer.parseInt(st.nextToken());  // 시작 위치

        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 플로이드-워셜로 모든 경로의 최단 거리 구하기
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 2. DFS + 비트마스킹으로 모든 탐사 순서 탐색
        dfs(K, 1 << K, 0);

        System.out.println(answer);
    }

    // DFS 함수
    static void dfs(int current, int visited, int totalTime) {
        if (visited == (1 << N) - 1) {  // 모든 행성 방문 완료
            answer = Math.min(answer, totalTime);
            return;
        }

        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) == 0) {  // 방문 안 한 행성만
                dfs(next, visited | (1 << next), totalTime + dist[current][next]);
            }
        }
    }
}