/*
[백준]
5582, 공통 부분 문자열

[문제파악]
- 두 문자열이 주어졌을 때, 두 문자열에 모두 포함된 가장 긴 공통 부분 문자열을 찾는 프로그램을 작성하시오.
- 어떤 문자열 s의 부분 문자열 t란, s에 t가 연속으로 나타나는 것을 말한다.
- 예를 들어, 문자열 ABRACADABRA의 부분 문자열은 ABRA, RAC, D, ACADABRA, ABRACADABRA, 빈 문자열 등이다.
- 하지만, ABRC, RAA, BA, K는 부분 문자열이 아니다.
- 두 문자열 ABRACADABRA와 ECADADABRBCRDARA의 공통 부분 문자열은 CA, CADA, ADABR, 빈 문자열 등이 있다.
- 이 중에서 가장 긴 공통 부분 문자열은 ADABR이며, 길이는 5이다.
- 또, 두 문자열이 UPWJCIRUCAXIIRGL와 SBQNYBSBZDFNEV인 경우에는 가장 긴 공통 부분 문자열은 빈 문자열이다.

[입력]
- 첫째 줄과 둘째 줄에 문자열이 주어진다.
- 문자열은 대문자로 구성되어 있으며, 길이는 1 이상 4000 이하이다.

[출력]
- 첫째 줄에 두 문자열에 모두 포함 된 부분 문자열 중 가장 긴 것의 길이를 출력한다.

[구현방법]
- 처음에 이 문제를 마주했을 땐, 하나씩 이동하며 비교하는 것을 상상했더랬다
- 근데 그렇게 해서는 풀어낼 수 없더라 해봐야할 것도 워낙 많기도 하고, 시간내로 끝나지 않을 것..
- 힌트를 봤는데 DP로 풀라고.. 하더라..? 심지어 이런 LCS라는 알고리즘이 존재함;;
- 결국 못풀어서 확인해보니 이렇게 간단할 수가... DP는 구성까지 가는게 힘든 것 같다 나한테만 어렵나 싶기도..

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();
        int[][] dp = new int[str1.length()+1][str2.length()+1];  // DP니까 이전 값도 기록하기 위해 +1 크기로 생성해준다
        int max = 0;

        for (int i = 1 ; i <= str1.length() ; i++){
            for (int j = 1 ; j <= str2.length(); j++){
                // 문자열이 같다면
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    // dp에 같다는 것을 기록해둔다. 이때 이전에 같은 값이 있었으면 그것의 연장선이기 때문에 +1
                    // 뭔 뜻이냐면 이전까지 같은 값이 2개 있는 경우, 현 위치까지 같으면 3개가 같기 때문에 2 + 1 = 3으로 기록하는 것이다
                    dp[i][j] = dp[i-1][j-1] + 1;

                    // 그렇게 저장한 값이 최댓값보다 크면, 최댓값 역시 갱신한다
                    if (max < dp[i][j]) max = dp[i][j];
                } else dp[i][j] = 0;  // 같지 않으면, 0으로 저장한다
            }
        }

        System.out.println(max);
    }
}