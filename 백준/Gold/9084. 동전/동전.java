/*
[백준]
9084, 동전

[문제파악]
- 우리나라 화폐단위, 특히 동전에는 1원, 5원, 10원, 50원, 100원, 500원이 있다.
- 이 동전들로는 정수의 금액을 만들 수 있으며 그 방법도 여러 가지가 있을 수 있다.
- 예를 들어, 30원을 만들기 위해서는 1원짜리 30개 또는 10원짜리 2개와 5원짜리 2개 등의 방법이 가능하다.
- 동전의 종류가 주어질 때에 주어진 금액을 만드는 모든 방법을 세는 프로그램을 작성하시오.

[입력]
- 입력의 첫 줄에는 테스트 케이스의 개수 T(1 ≤ T ≤ 10)가 주어진다.
- 각 테스트 케이스의 첫 번째 줄에는 동전의 가지 수 N(1 ≤ N ≤ 20)이 주어지고 두 번째 줄에는 N가지 동전의 각 금액이 오름차순으로 정렬되어 주어진다.
- 각 금액은 정수로서 1원부터 10000원까지 있을 수 있으며 공백으로 구분된다.
- 세 번째 줄에는 주어진 N가지 동전으로 만들어야 할 금액 M(1 ≤ M ≤ 10000)이 주어진다.
- 편의를 위해 방법의 수는 231 - 1 보다 작고, 같은 동전이 여러 번 주어지는 경우는 없다.

[출력]
- 각 테스트 케이스에 대해 입력으로 주어지는 N가지 동전으로 금액 M을 만드는 모든 방법의 수를 한 줄에 하나씩 출력한다.

[구현방법]
- DP인거 알겠고 20이면 10 2개로 쪼개서 하면 되지...않나??
- 하나씩 다 그려가며 해봤는데 이게 아니었다 10으로 두개 쪼개면 경우의 수가 총 8개이지만, 사실 20의 총 경우의 수는 9개이다
- 이 문제의 관건은 코인이 추가될 수록 쓸 수 있는 경우의 수(패)가 늘어나는 것을 감안해야 한다.
- 즉, 이전에 계산해뒀던 값을 갖다 쓰더라도 dp[구해야하는 순서 - 현재 코인]으로 현재 코인 조건을 추가할만큼의 여분을 두며 최적의 값을 살펴야한다

[보완점]
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N];

            // 화폐 종류 입력받기
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());  // 만들어야 할 금액
            int[] dp = new int[M + 1];  // 0부터 시작해야하니까
            dp[0] = 1;  // 0을 만들 수 있는 가짓수는 1가지이다

            for (int currCoin : coins) {
                for (int i = currCoin; i <= M; i++) {
                    dp[i] += dp[i - currCoin];
                }
            }

            sb.append(dp[M]).append("\n");
        }

        System.out.println(sb);
    }
}