/*
[백준]
9657, 돌게임3

[문제파악]
- 탁자 위에 N개의 돌게임
- 번갈아가며 돈을 가져가며, 돌은 1개 or 3개 or 4개 가져갈 수 있다
- 마지막 돌을 가져가는 사람이 게임을 이기게 된다
- 두 사람이 완벽하게 게임을 했 을 때 이기는 사람을 구하라
- 상근이가 먼저 시작한다

[입력]
- 첫째줄에 N (1 <= N <= 1,000)

[출력]
- 상근이가 게임을 이기면 SK를, 창영이가 게임을 이기면 CY를 출력

[구현방법]
- 앞선 돌게임들과 비슷한데 4개까지 가져갈 수 있는 조건이 추가되었다
- 결과적으로 SK가 유리하게 된 셈 (사용할 수 있게 된 패가 많아졌으니)

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[Math.max(N, 5)];

        // 0 = SK
        // 1 = CY
        dp[0] = 0;  // 1개 일때, SK 승리
        dp[1] = 1;  // 2개 일때, CY 승리
        dp[2] = 0;
        dp[3] = 0;

        for (int i = 5; i < N; i++) {
            int temp = Math.max(Math.max(dp[i-4], dp[i-3]), dp[i-1]) + 1;  // 최대한 max(=1)를 구하는 이유!? (*)
            dp[i] = temp == 2 ? 0 : temp;
        }

        String winner = dp[N-1] == 0 ? "SK" : "CY";
        System.out.println(winner);
    }
}

/*
최대한 max(-1)를 구하는 이유는?
- 두 사람 모두 서로가 이기기 위해 최선을 다한다
- 즉, N이 정해진 시점에서 누가 이길진 이미 정해져있고 최대한 자기에게 유리하게 해야한다
- SK가 먼저 뽑기 때문에 최대한 자기 자신이 마지막에게 뽑게 하는 경우의 수를 사용하게 될 것이다
- 그렇게 되면, 최대한 마지막이 CY(1)인 케이스를 유도하는게 맞기 때문에 max를 구해야한다
- 더군다나 이 조건은 내가 dp 배열에서 SK를 0, CY를 1을 주었기 때문에 성립한다
* */