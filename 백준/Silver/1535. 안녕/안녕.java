/*
[백준]
1535, 안녕

[문제파악]
- 세준이는 성형수술을 한 후에 병원에 너무 오래 입원해 있었다.
- 이제 세준이가 병원에 입원한 동안 자기를 생각해준 사람들에게 감사하다고 말할 차례이다.
- 세준이를 생각해준 사람은 총 N명이 있다. 사람의 번호는 1번부터 N번까지 있다.
- 세준이가 i번 사람에게 인사를 하면 L[i]만큼의 체력을 잃고, J[i]만큼의 기쁨을 얻는다.
- 세준이는 각각의 사람에게 최대 1번만 말할 수 있다.
- 세준이의 목표는 주어진 체력내에서 최대한의 기쁨을 느끼는 것이다.
- 세준이의 체력은 100이고, 기쁨은 0이다.
- 만약 세준이의 체력이 0이나 음수가 되면, 죽어서 아무런 기쁨을 못 느낀 것이 된다.
- 세준이가 얻을 수 있는 최대 기쁨을 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 사람의 수 N(≤ 20)이 들어온다.
- 둘째 줄에는 각각의 사람에게 인사를 할 때, 잃는 체력이 1번 사람부터 순서대로 들어오고,
- 셋째 줄에는 각각의 사람에게 인사를 할 때, 얻는 기쁨이 1번 사람부터 순서대로 들어온다.
- 체력과 기쁨은 100보다 작거나 같은 자연수 또는 0이다.

[출력]
- 첫째 줄에 세준이가 얻을 수 있는 최대 기쁨을 출력한다.

[구현방법]
- 1번 부터 순서대로 들어온다
- 2초에 N 최대값이 20이니까 따로 자료구조나 그런걸 신경 쓸 필욘 없어보임
- 이건 할지 말지의 최댓값을 구해야하는거니까 Top-Down 방식 쓰면 된다. (피 100일때부터 1일 때까지)
    - 그리고 계속 더해가면 되는게 아니라 최댓값을 구해야함을 잊지말자 (= Math.max(dp[i - curr_lose] + curr_joy, dp[i]))
- 위에도 적어놨지만 0까지 도달하면 안된다
- 이 말인 즉, 피는 늘 최소한 1은 남아있어야하고, 그럼 DP에서 우리가 구해야하는 값은 dp[99]인 셈이다

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer input_Lose = new StringTokenizer(br.readLine());
        StringTokenizer input_Joy = new StringTokenizer(br.readLine());

        int[] dp = new int[100];  // 99까지만 구해야하니까 100이면 충분 (= 피 1은 무조건 남아 있어야해서 그렇다)

        // 테스트 케이스 하나마다 1~99까지의 피통에서 계산할 수 있는 것들을 구하는 것이다
        for (int test = 0; test < N; test++) {
            int curr_lose = Integer.parseInt(input_Lose.nextToken());
            int curr_joy = Integer.parseInt(input_Joy.nextToken());

            // 문제 예시보고 추가한 것
            // 필요 cost(=lose)가 100이면 피통이 바로 0이되니까 계산할 필요도 없다
            if (100 <= curr_lose) continue;

            // 입력 받은 lost와 joy로 1~100까지의 dp를 갱신한다
            for (int i = 99; curr_lose <= i; i--) {
                dp[i] = Math.max(dp[i], dp[i - curr_lose] + curr_joy);  // 기존에 구해뒀던 값이랑 지금 새로 구하고 있는 값이랑 비교
            }

            // System.out.println(Arrays.toString(dp));
        }

        System.out.println(dp[99]);
    }
}