/*
[백준]
14728, 벼락치기

[문제파악]
- ChAOS(Chung-ang Algorithm Organization and Study) 회장이 되어 일이 많아진 준석이는 시험기간에도 일 때문에 공부를 하지 못하다가 시험 전 날이 되어버리고 말았다.
- 다행히도 친절하신 교수님께서 아래와 같은 힌트를 시험 전에 공지해 주셨다.
- 내용은 아래와 같다.
    1) 여러 단원을 융합한 문제는 출제하지 않는다.
    2) 한 단원에 한 문제를 출제한다.
    3) 단, 그 단원에 모든 내용을 알고 있어야 풀 수 있는 문제를 낼 것이다.
- 이런 두가지 힌트와 함께 각 단원 별 배점을 적어 놓으셨다.
- 어떤 단원의 문제를 맞추기 위해서는 그 단원의 예상 공부 시간만큼, 혹은 그보다 더 많이 공부하면 맞출 수 있다고 가정하자.
- 이때, ChAOS 회장 일로 인해 힘든 준석이를 위하여 남은 시간 동안 공부해서 얻을 수 있는 최대 점수를 구하는 프로그램을 만들어 주도록 하자.

[입력]
- 첫째 줄에는 이번 시험의 단원 개수 N(1 ≤ N ≤ 100)과 시험까지 공부 할 수 있는 총 시간 T(1 ≤ T ≤ 10000)가 공백을 사이에 두고 주어진다.
- 둘째 줄부터 N 줄에 걸쳐서 각 단원 별 예상 공부 시간 K(1 ≤ K ≤ 1000)와 그 단원 문제의 배점 S(1 ≤ S ≤ 1000)가 공백을 사이에 두고 주어진다.

[출력]
- 첫째 줄에 준석이가 얻을 수 있는 최대 점수를 출력한다.

[구현방법]
- 냅색 문제
- DP[N] = Math.max( DP[N-200] + DP[200], DP[N-100] + DP[100], DP[N-50] + DP[50]) 이 셋 중에 하나 아닐까?
- DP는 매번 풀어도 어렵다... 익숙해지는 타이밍이 올런지 ㅠ

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[] dp = new int[T + 1];  // 매 시간마다 가장 효율적인 것을 계산하기 위해서 T + 1까지 계산

        for (int test = 0; test < N; test++) {  // n번 반복
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            // 최대 시간에서부터 거꾸로 계산 (동일한 단원을 중복해서 선택하는 이슈 제거)
            for (int i = T; K <= i; i--) {
                dp[i] = Math.max(dp[i], dp[i-K] + S);
            }
        }

        System.out.println(dp[T]);
    }
}