/*
[백준]
2662, 기업투자

[문제파악]
- 어떤 투자가가 여러 기업들에게 돈을 투자해서 최대의 이익을 얻고자 한다.
- 단, 투자는 만원 단위로 할 수 있으며 각 기업은 많이 투자할수록 많은 이익을 투자가에게 돌려준다.
- 돈을 투자하지 않은 경우는 당연히 얻게 되는 이익도 없다.
- 예를 들어서, 한 투자가가 4만원을 갖고 두 개의 기업들에 각각 만원 단위로 투자했을 경우 얻을 수 있는 이익은 다음과 같다.

        투자 액수 (만원)	기업 A	기업 B
               1	       5	  1
               2	       6	  5
               3	       7	  9
               4	       8	  15

- 위의 경우 만일, 기업 A에 1만원, 기업 B에 3만원을 투자하는 경우 투자가가 얻는 이익은 14만원(5만원+9만원)이다.
- 4만원을 투자해서 가장 많은 이익을 얻을 경우 기업 B에만 4만원을 투자하는 경우로서 이때의 이익은 15만원이다.
- 여기서 투자가는 한 기업에 돈을 나누어 투자할 수는 없다.
- 투자액이 정해져 있고, 기업의 개수와 각 기업에 투자했을 경우에 얻게 되는 이익이 주어졌을 때 가장 많은 이익을 얻을 수 있는 투자방식과 이때의 이익금을 구하는 프로그램을 작성하라.

[입력]
- 첫째 줄에 투자 금액 N과 투자 가능한 기업들의 개수 M이 주어진다. (1 ≤ N ≤ 300, 1 ≤ M ≤ 20)
- 둘째 줄부터 N개의 줄에 투자액수와 각 기업이 투자가에게 주는 이익이 주어진다.
- 투자 금액은 항상 1보다 크거나 같고, N보다 작거나 같고, 같은 투자 금액이 두 번 이상 주어지는 경우는 없다.
- 즉, i번 줄에 주어지는 투자 금액은 i-1만원이다.

[출력]
- 첫 줄에 얻을 수 있는 최대 이익을 출력하고, 둘째 줄에는 각 기업에 투자한 액수를 출력한다.
- 최대 이익은 231보다 작다.

[구현방법]
- 기업은 여러 개 일 수 있다 여러 기업에 투자할 수도 있음
- 그러니까 4원이 있으면 A(1), B(1), C(1), D(1)이 가능하다는 것임
- 비교해야할게 비단 Math.max (DP[3] + [1], DP[4]) 뿐만이 아님..
    - 아닌가?
        - DP[2] = Math.max(DP[2], DP[1] + DP[1])
        - DP[3] = Math.max(DP[3], DP[2] + DP[1])
        - 위에 보면 이미 고려된 상황 아닌가? DP[2]에서 선택된 max 값이 DP[1] + DP[1] 이라면 이미 (DP[1] + DP[1]) + DP[1]가 되는거잖어
        - 이미 고려된 상황인데?
        - 그럼 재귀로 구해야하나 난이도 높은 이유가 분명 있을텐데
        - 한 기업에 여러번 투자는 안됨
- ㅇㅋ 그럼 일단 DP는 Down-Top 방식으로 진행될 예정이고, 자료 구조는 2차원 배열
- 각 배열(투자액수)마다 Max 값을 구해서 그 기업을 DP 비교로 써야할듯
- 투자 금액이 순차적(1,2,3,4)이지 않을 수도 있겠네? 이런식이 아닌 1,4,5,6

[보완점]
- 일단 원리는 이해했다. 핵심은 DP를 구할 때, 아래와 같은 조건을 따른다
    1) 모든 기업부터 하나씩 DP에서 체크한다 (최대 이익이 무엇인지)
    2) 범위값으로 조절한다
    3) 순차적으로 투자금을 늘려가며 갱신한다
    
- 그냥 for문이 세개인 이유는 기업을 순서대로 하나씩 체크, 현재 투자금을 기준으로, 0부터 현재 투자금까지 현 기업에 모든 경우의 수를 체크한다
- 어렵다 어려워 이건 필히 다시 풀어보자
- 여기서 끝나지 않고, 기업에 투자한 곳들은 어디인지까지 체크해야함...
- 어렵다 어려웡...
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());  // 투자 금액
        int M = Integer.parseInt(st.nextToken());  // 투자 가능 기업의 수
        int[] dp = new int[N + 1];
        ArrayList<ArrayList<Integer>> path = new ArrayList<>();
        int[][] profit = new int[M][N + 1];  // i번째 기업에 j 원을 투자했을 때 얻는 금액

        // 각 기업별 수익 input 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken(); // 첫 번째 값은 투자 금액으로 생략
            for (int j = 1; j <= M; j++) {
                profit[j - 1][i + 1] = Integer.parseInt(st.nextToken());
            }
        }

        // 경로 List 초기화
        for (int i = 0; i <= N; i++) path.add(new ArrayList<>());

        // DP 초기값 넣고, 경로도 셋팅
        for (int i = 0; i <= N; i++) {
            dp[i] = profit[0][i];
            path.get(i).add(i);
        }

        for (int i = 1; i < M; i++) { // 2번째 기업부터 모든 기업에 대해 반복
            for (int j = N; j >= 0; j--) { // 해당 기업의 바로 전단계까지의 누적 투자 금액에 대해 거꾸로 반복
                int add = 0;

                for (int k = 1; k <= j; k++) { // 해당 누적 투자 금액에서 이 기업에 투자해서 얻는 이득 갱신
                    if (dp[j - k] + profit[i][k] > dp[j]) {
                        dp[j] = dp[j - k] + profit[i][k];
                        path.set(j, new ArrayList<>(path.get(j - k)));
                        add = k;
                    }
                }
                path.get(j).add(add);
            }
        }

        sb.append(dp[N]).append("\n");
        for (int value : path.get(N)) {
            sb.append(value).append(" ");
        }

        System.out.println(sb);
    }
}