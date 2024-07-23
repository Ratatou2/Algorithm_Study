/*
[백준]
9656, 돌게임

[문제파악]
- 탁자 위에 N개의 돌게임
- 번갈아가며 돈을 가져가며, 돌은 1개 or 3개 가져갈 수 있다
- 마지막 돌을 가져가는 사람이 게임을 지게 된다
- 두 사람이 완벽하게 게임을 했 을 때 이기는 사람을 구하라
- 상근이가 먼저 시작한다

[입력]
- 첫째줄에 N (1 <= N <= 1,000)

[출력]
- 상근이가 게임을 이기면 SK를, 창영이가 게임을 이기면 CY를 출력

[구현방법]
- 이거는 돌게임 1이랑 그냥 조건이 반대...
- 이번엔 인덱스로 계산해보지 뭐 (N+1 안하겠단 소리)
- 또한 좀 더 빠르게 해봅시당

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[Math.max(N, 3)];

        // 이기는 사람 표기
        // 0 = CY
        // 1 = SK
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < N; i++) {
            dp[i] = dp[i - 1] == 0 ? 1 : 0;  // 이전 값이 0(CY)이면, 이번엔 1(SK)가 이긴다
        }

        String result = dp[N-1] == 0 ? "CY" : "SK";

        System.out.println(result);
    }
}