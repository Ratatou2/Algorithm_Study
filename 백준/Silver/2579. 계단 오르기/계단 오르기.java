/*
[백준]
2579, 계단오르기

[문제파악]
- 계단 오르기 게임은 계단 아래 지점부터 꼭대기에 위치한 도착점까지 가는 게임
- 계단에는 일정한 점수가 쓰여있고, 계단을 밟으면 그 계단에 쓰여있는 점수를 얻게 된다
- 계단 오르는데는 아래와 같은 규칙이 있다.
    1. 계단은 한번에 1개 or 2개씩 오를 수 있다.
    2. 연속된 세계의 계단은 밟아서는 안된다. (단, 시작점에 계단에 포함되지 않는다)
    2. 마지막 도착 계단은 반드시 밟아야 한다.
- 게임에서 얻을 수 있는 총 점수의 최댓값을 구하라.

[입력]
- 첫째줄에 계단의 갯수
- 둘째줄에 한줄에 하나씩 제일 아래에 놓인 계단부터 ㅆ ㅡ인 점수가 주어짐
    - 계단의 갯수는 300이하의 자연수
    - 계단의 점수는 10,000이하의 자연수

[출력]
- 얻을 수 있는 최댓값을 출력

[구현방법]
- DP는 으레 그렇듯 항상 규칙을 찾아야한다
- (개인적으론) 예를 들 때도 i보단, 명확한 숫자로 비교하는게 더 나았다
- 예를 들면 5번째 계단을 밟을건데 이 계단을 밟게 되는 경우의 수는 총 3가지가 있다
    - 5, 4, 2 인 경우 (연속된 세개는 밟을수 없기에)
    - 5, 3, 2
    - 5, 3, 1
    - 문제가 되는 지점은 연속된 세개를 밟을 수 없다는 것
    - 즉 규칙은 dp[5] + dp[5-1] + dp[5-3] / dp[5] + dp[5-2] + dp[5-3]

[보완점]
- 아이디어가 근접했는데 틀린 이유
- 현재 계단에서의 최댓값을 dp[i] + Math.max(dp[i-1] + dp[i-3], dp[i-2] + dp[i-3])으로 지정했음
    - dp[i-1] + dp[i-3] => 5를 밟았으면 바로 직전 계단인 4와 전전전 계단인 2를 밟은 경우 (연속된 3계단은 밟을 수 없으니 3번 계단은 밟을 수 없다)
    - dp[i-2] + dp[i-3] => 5를 밟았으면 바로 전전 계단인 3과 전전전 게단인 2를 밟은 경우 (4를 건너뛰고 연속된 계단을 밟은 케이스)
- 이렇게 해서 틀린거다
- 사실은 dp[i] + Math.max(nums[i-1] + dp[i-3], dp[i-2]) 로 구해야함
- 가만 생각해보면 dp[i-2] 계단이란건 그 계단까지의 최대 득점이 기록되어있다는 의미다.
- 그 말인 즉, dp[i-2]가 바로 직전(nums[i-3]) 계단을 밟았는지 밟지 않았는지 우리는 알수가 없다는 의미다
- 그러면 dp[i-3]을 더해주면 안된다. 우리 그 값이 만들어진 조건을 모르니까. 우리가 필요한건 dp[i-2]가 전부니까
    - dp[i-2]에는 이미 그 계단에 도달하기까지의 모든 경우의 수를 따진 최댓값이 저장되어있다
- 그러니까 dp[i-2]과 nums[i-1] + dp[i-3]한 값을 비교해야한다
- nums[i-1] + dp[i-3]는 왜 두개냐면 직전 계단(nums[i-1])을 밟았으면, 그 전 계단인 dp[i-2]는 당연히 배제하니까 그럼 그 전전 계단인 dp[i-3]을 합산해줘야한다
- 어렵다 어려워... 오래 걸리더라도 한동안 DP만 풀어야할듯
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[Math.max(N + 1, 3)];
        int[] dp = new int[Math.max(N + 1, 3)];

        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = nums[1];
        dp[2] = nums[2] + dp[1];  // dp니까 1, 2 계단 둘 다 밟았을 때가 최댓값이니 둘을 더해줘야 함

        for (int i = 3; i <= N; i++) {
            dp[i] = nums[i] + Math.max(nums[i-1] + dp[i-3], dp[i-2]);
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[N]);
    }
}