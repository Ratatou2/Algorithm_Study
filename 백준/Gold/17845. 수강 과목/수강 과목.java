/*
[백준]
17845, 수강과목

[문제파악]
- 유니스트 컴퓨터공학과에 다니는 서윤이는 이번에 어떤 과목을 들을지 고민중이다.
- 학점을 잘 받을 수 있으면서도 중요한 과목을 듣고 싶은 서윤이는 모든 과목의 중요도와, 일정 이상의 학점을 받기 위해 필요한 공부시간을 다 적었다.
- 처음에는 모든 과목을 들으려고 했던 서윤이는 자신의 공부 시간에 한계가 있다는 것을 깨달았다.
- 그래서, 공부 시간의 한계를 초과하지 않으면서 과목의 중요도 합이 최대가 되도록 몇 개만 선택하여 수강하기로 마음먹었다.
- 중요도가 최대가 되도록 과목을 선택했을 때, 최대가 되는 중요도를 출력하는 프로그램을 작성하시오.

[입력]
- 첫줄에 서윤이의 최대 공부시간 N (1 ≤ N ≤ 10,000), 과목 수 K (1 ≤ K ≤ 1,000)이 공백을 사이에 두고 주어진다.
- 이후 K개의 줄에 중요도 I (1 ≤ I ≤ 100,000), 필요한 공부시간 (1 ≤ T ≤ 10,000)이 공백을 사이에 두고 주어진다.

[출력]
- 얻을 수 있는 최대 중요도를 출력한다.

[구현방법]
- 이거 저번에 풀었던 문제랑 비슷하다..!! 복습하기 최적!!!

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[N + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int I = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            // 가장 위에서부터 쌓아 내려오는 방식
            // 최대 가용 시간부터 하나씩 차감하여 현재 최소 가용시간인 T까지 반복문 진행
            // 원래 있는 값과 현재 I + 이전 DP 기록 중 [반복문 - T]의 값을 더한 것과 max값 비교
            // 둘 중 더 높은 값을 채용하는 방식
            for (int j = N; T <= j; j--) {
                dp[j] = Math.max(dp[j], dp[j-T] + I);
            }
        }

        System.out.println(dp[N]);
    }
}