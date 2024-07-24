/*
[백준]
14501, 퇴사

[문제파악]
- N+1에 퇴사
- N일 동안 최대한 많은 상담을 진행
- 단, 모든 상담은 불가능
- N+1에는 회사에 없으므로 N열을 넘기는 상담도 불가
- 최대 이익을 구해라

[입력]
- 첫째줄에 N (1 <= N <= 15)
- 둘째줄부터 N개의 줄에 T, P가 공백으로 구분되어 순차적으로 주어짐
	- 1일부터 N일까지 순서대로 주어진다 (1 <= T <= 5, 1 <= P <= 1,000)

[출력]
- 얻을 수 있는 최대 이익 출력

[구현방법]
- 봐도 이해가 안된다

[보완점]
- 결국 하나씩 다 손으로 써봤음
- 일단 현재에서 손님을 받고 끝나는 시점에 가서 업데이트를 해주는 식임
- 지금 값을 미리 갱신하는게 아닌, 끝나는 시점 갱신
- 그렇기 때문에 dp 배열은 처음부터 채워지는게 아니라 순차적으로 진행한다
- 자세한 내용은 주석으로 다 달긴했는데, 며칠 뒤에 다시 풀어봐야할듯 ㅠ
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] time = new int[N + 1];
        int[] cost = new int[N + 1];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            // 현재 작업에서 소요되는 시간이 데드라인 내에 끝나는지 체크
            if (i + time[i] <= N) {
                // 데드라인 내에 끝난다면, 끝나는 지점에 현재 값과 기록된 값을 계산하여 기록
                // 1일에 시작해서 3일뒤에 끝난다면 4일에는 4일째의 기록과 1일의 누적합과 1일이 끝났을 때의 급여를 합산해서 max를 구한다
                dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + cost[i]);
            }

            // 그리고 기본적으로 현재 값을 갱신
            // 비교하는건 다음 값과 현재값
            // 다음 값을 미리 갱신해두는 것은 현재의 최댓값을 미리 기록해두어야 계산할 수 있기 때문임
            // 또한 다음 값은 기록된 다음값을 유지할 것인지 아니면 지금 일을 이어받을 것인지에 대한 갱신임
            // 그렇게 될 경우 내가 i날에 일하는 것보다 이전에 일해서 지금까지 유지하는게 더 큰 이익이기 때문임
            dp[i+1] = Math.max(dp[i], dp[i+1]);
        }

        System.out.println(dp[N]);
    }
}