import java.io.*;
import java.util.*;

public class Main{
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
            indegree[B]++; // B의 진입 차수 증가
        }

        // 최소 힙 (우선순위 큐) 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 진입 차수가 0인 문제들을 PQ에 추가
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                pq.add(i);
            }
        }

        // 위상 정렬 수행
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int now = pq.poll(); // 가장 쉬운 문제(번호 작은 것)를 꺼냄
            sb.append(now).append(" ");

            // 현재 문제를 푼 후 영향을 받는 문제들 처리
            for (int next : adj[now]) {
                indegree[next]--; // 선행 문제가 해결됐으므로 진입 차수 감소
                if (indegree[next] == 0) { // 진입 차수가 0이 되면 PQ에 삽입
                    pq.add(next);
                }
            }
        }

        // 결과 출력
        System.out.println(sb);
    }
}
