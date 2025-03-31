/*
[백준]
9251, LCS

[문제파악]
- LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
- 예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.

[입력]
- 첫째 줄과 둘째 줄에 두 문자열이 주어진다.
- 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.

[출력]
- 첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를 출력한다.

[구현방법]
- 도저히 어떻게 구해야하는질 모르겠어서, 생각나는거라곤 하나씩 죄다 비교하는건데 제한시간이 0.1초라 설마.. DP? 했더니만 역시 DP...
- DP는 늘 반복되는 부분을 못 잡아내면 그냥 끝까지 모르겠다.. ㅠ

[보완점]
- 예시를 보면 아래와 같다
        C A P C A K
      0 0 0 0 0 0 0
    A 0 0 1 1 1 1 1
    C 0 1 1 1 2 2 2
    A 0 1 2 2 2 3 3
    Y 0 1 2 2 2 3 3
    K 0 1 2 2 2 3 4
    P 0 1 2 3 3 3 4

이런식으로 문자열 하나를 잡고 다른 문자열과 비교하는 구조를 가진다
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int LCS(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // 그러니까 s1에서 첫번쨰 문자열부터 s2의 문자열과 하나씩 비교해 나갈건데, 같은 지점이 있다면, 이전에 누적된 길이의 친구에다가 +1 (길이가 하나 늘어났으므로)해서 저장한다
                // 그게 아니면 그냥 이전에 있었던 것들 중 가장 큰 것을 계속 데려가는 구조이다 (s1 문자를 하나 제외(dp[i-1][j])한 것 또는 s2 문자를 하나 제외(dp[i][j-1] 한 것 중 하나 선택)
                // 즉 dp에서 [i][j]의 의미는 s1에서는 i번째까지, s2에서는 j번째까지 비교했을 때 가장 긴 문자열을, 부분 수열을 의미하는 것이다.
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[len1][len2];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        System.out.println(LCS(str1, str2));
    }
}