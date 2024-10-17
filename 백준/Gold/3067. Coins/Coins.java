/*
[백준]
3067, Coins

[문제파악]
- 우리나라 화폐단위, 특히 동전에는 1원, 5원, 10원, 50원, 100원, 500원이 있다.
- 이 동전들로는 모든 정수의 금액을 만들 수 있으며 그 방법도 여러 가지가 있을 수 있다.
- 예를 들어 30원을 만들기 위해서는 1원짜리 30개 또는 10원짜리 2개와 5원짜리 2개 등의 방법이 가능하다.
- 동전의 종류가 주어질 때에 주어진 금액을 만드는 모든 방법을 세는 프로그램을 작성하시오.

[입력]
- 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다.
- 각 테스트 케이스는 첫 번째 줄에는 동전의 가지 수 N(1 ≤ N ≤ 20)이 주어지고 두 번째 줄에는 N 가지 동전의 각 금액이 오름차순으로 정렬되어 주어진다.
- 각 금액은 정수로서 1원부터 10000원까지 있을 수 있으며 공백으로 구분된다.
- 세 번째 줄에는 주어진 N가지 동전으로 만들어야 할 금액 M(1 ≤ M ≤ 10000)이 주어진다.
- 편의를 위해 방법의 수는 231 - 1 보다 작다고 가정해도 된다.

[출력]
- 각 테스트 케이스에 대해 입력으로 주어지는 N가지 동전으로 금액 M을 만드는 모든 방법의 수를 한 줄에 하나씩 출력한다.

[구현방법]
- 이거 DP, 냅색 문제다 (네가 골랐으니까 그렇겠지)
- 일단 작게 쪼개서 미리 계산해두면 된다 (ㄹㅇ 뻔한 소리)
- 아 이거는 동전에 따라서 그 횟수가 바뀌는 것이라서, 동전 기준으로 계속 누적을 해야하는 것이다 (max 값을 찾는 방식이 아님)
- 글구 나중에 알았는데 [9084, 동전] 문제랑 똑같다

[보완점]
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int coinCount = Integer.parseInt(br.readLine());
            int[] coins = new int[coinCount];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < coinCount; i++) coins[i] = Integer.parseInt(st.nextToken());

            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M + 1];
            dp[0] = 1;  // 아무것도 안 쓰면 0도 만들 수 있음 (1가지의 가짓수)

            for (int coin : coins) {
                for (int i = coin; i <= M; i++) {
                    dp[i] += dp[i - coin];
                }
            }

            sb.append(dp[M]).append("\n");
        }

        System.out.println(sb);
    }
}