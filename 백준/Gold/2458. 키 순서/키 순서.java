import java.util.Scanner;

public class Main {
    static final int INF = 1000000; // 무한대를 표현하기 위한 큰 값

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 학생 수
        int M = sc.nextInt(); // 키 비교 횟수

        // 플로이드-워셜을 위한 그래프 초기화
        boolean[][] graph = new boolean[N + 1][N + 1];

        // 입력으로 주어진 키 비교 관계를 저장
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a][b] = true; // a < b
        }

        // 플로이드-워셜 알고리즘 수행
        for (int k = 1; k <= N; k++) { // 중간 노드
            for (int i = 1; i <= N; i++) { // 시작 노드
                for (int j = 1; j <= N; j++) { // 끝 노드
                    // i -> j 로 가는 경로가 존재하는지 확인
                    if (graph[i][k] && graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }

        // 키 순위를 정확히 알 수 있는 학생 수 계산
        int result = 0;
        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] || graph[j][i]) { // i가 j보다 크거나 작으면 카운트
                    count++;
                }
            }
            if (count == N - 1) { // 자기 자신 제외하고 모든 노드와 연결되면
                result++;
            }
        }

        // 결과 출력
        System.out.println(result);
        sc.close();
    }
}