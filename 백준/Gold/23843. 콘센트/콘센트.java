/*
[백준]
23843, 콘센트

[문제파악]
- 광재는 전자기기 대여사업을 시작했다.
- 퇴근하기 전에 다음날 손님들에게 빌려줄 N개의 전자기기를 충전하려 한다.
- 사용 가능한 콘센트는 M개가 있고, 성능은 모두 동일하다.
- 전자기기들은 한 번에 하나의 콘센트에서만 충전이 가능하고, 충전에 필요한 시간은 2k(0 ≤ k ≤ 15, k는 정수) 형태이다.
- 광재의 빠른 퇴근을 위해 모든 전자기기를 충전하기 위한 최소 시간이 얼마인지 알려주자.

[입력]
- 첫째 줄에 전자기기의 개수 N과 콘센트의 개수 M이 주어진다. (1 ≤ N ≤ 10,000, 1 ≤ M ≤ 10)
- 둘째 줄에 충전에 필요한 시간 ti를 나타내는 N개의 정수가 주어진다. (20 ​≤ ti ≤ 215, ti = 2k (0 ≤ k ≤ 15, k는 정수))

[출력]
- 충전에 필요한 최소 시간을 출력한다.

[구현방법]
- 기기마다 걸리는 시간은 Array에 reverse로 내림차순 정렬하고, PQ에는 콘센트 M개만큼 밀어넣는다
    - 이때 PQ는 오름차순 (정방향 정렬)
- 그러면 PQ에서 poll 하면 제일 작은 값이 나올거고 거기에 Array에서 남은 제일 큰 친구를 더함
- 이렇게 하면 제일 빨리 끝나는 친구에게 제일 오래 걸리는 작업을 할당할 수 있고 결과적으로 전체 소요시간이 줄어듦

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 전자기기 갯수
        int M = Integer.parseInt(st.nextToken());  // 콘센트 갯수

        int[] devices = new int[N];

        // 전자기기 충전시간 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            devices[i] = Integer.parseInt(st.nextToken());
        }

        // 전자기기 정렬
        Arrays.sort(devices);

        // 예외처리!! - 콘센트 갯수가 기기 갯수랑 동일하거나 더 많으면, 가장 오래 걸리는 기기가 전체 소요시간이다
        if (N <= M) {
            System.out.println(devices[N - 1]);
            return;
        }
        
        // 콘센트 갯수만큼, 전자기기 콘센트에 꼽아두기 (시간이 제일 오래 걸리는 것들부터)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int index = N - 1;
        for (int i = 0; i < M; i++) {
            pq.add(devices[index--]);
        }

        // PQ에는 시간이 제일 작은 것부터 순차적으로 정렬되어 있음
        // 그 상태에서 하나를 꺼내고 그 다음 전자기기 충전 시간을 더한 뒤, 다시 PQ에 넣는다
        // 제일 빨리 끝나는 콘센트에, 남아 있는 전자기기들 중 충전이 제일 오래 걸리는 전자기기를 꼽아주는 것이다
        while (0 <= index) {
            pq.add(devices[index--] + pq.poll());
        }

        // 그럼 이제 콘센트에서 하나씩 다 뽑을건데 제일 오래 걸리는건 PQ 제일 마지막에 있으니 다 뽑아버리고 마지막에 뽑은걸 나중에 출력하면 된다
        int latestTime = 0;
        for (int i = 0; i < M; i++) {
            latestTime = pq.poll();
        }

        System.out.println(latestTime);
    }
}