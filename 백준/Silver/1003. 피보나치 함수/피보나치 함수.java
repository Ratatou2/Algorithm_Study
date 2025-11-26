

/*
[백준]
1003, 피보나치 함수

[문제파악]
다음 소스는 N번째 피보나치 수를 구하는 C++ 함수이다.

int fibonacci(int n) {
    if (n == 0) {
        printf("0");
        return 0;
    } else if (n == 1) {
        printf("1");
        return 1;
    } else {
        return fibonacci(n‐1) + fibonacci(n‐2);
    }
}
fibonacci(3)을 호출하면 다음과 같은 일이 일어난다.
fibonacci(3)은 fibonacci(2)와 fibonacci(1) (첫 번째 호출)을 호출한다.
fibonacci(2)는 fibonacci(1) (두 번째 호출)과 fibonacci(0)을 호출한다.

두 번째 호출한 fibonacci(1)은 1을 출력하고 1을 리턴한다.
fibonacci(0)은 0을 출력하고, 0을 리턴한다.
fibonacci(2)는 fibonacci(1)과 fibonacci(0)의 결과를 얻고, 1을 리턴한다.

첫 번째 호출한 fibonacci(1)은 1을 출력하고, 1을 리턴한다.
fibonacci(3)은 fibonacci(2)와 fibonacci(1)의 결과를 얻고, 2를 리턴한다.

1은 2번 출력되고, 0은 1번 출력된다. N이 주어졌을 때, fibonacci(N)을 호출했을 때, 0과 1이 각각 몇 번 출력되는지 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 테스트 케이스의 개수 T가 주어진다.

[출력]
각 테스트 케이스는 한 줄로 이루어져 있고, N이 주어진다.
N은 40보다 작거나 같은 자연수 또는 0이다.
각 테스트 케이스마다 0이 출력되는 횟수와 1이 출력되는 횟수를 공백으로 구분해서 출력한다.

[구현방법]
- 재귀로 타고 내려가면서 직접 계산하기엔 0.25초라서 불가능할 것 같다 (N은 40보다 작긴함)
- 그래서 아마 DP를 써야겠지?
- 근데 결국 재귀로 내려가면서 이전 값 구하고 다시 올라오면서 DP에 기록해둔 값 써야하는 구조 아닌가?
- 아... 문제의 본질을 잊지말자 피보나치 구하는게 아니고 결국 0과 1을 사용한 '횟수'를 구하는 것이다
- 점화식은 피보나치랑 똑같지만 구하는 아이디어는 다른 것임 (0과 1의 호출 횟수)
    DP[n][0] = DP[n-1][0] + DP[n-2][0]
    DP[n][1] = DP[n-1][1] + DP[n-2][1]
- 그럼 결국 DP를 40까지 미리 구해두고 그냥 T에 맞춰 호출하는 방식이 되겠다
- 역시 DP는 점화식까지 도달하는게 젤 어려운듯..

[보완점]

*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 40까지 DP 미리 구해놓기
        int[][] DP = new int[40 + 1][2];

        // 초기값 세팅
        DP[0][0] = 1;  // 피보나치(0) -> 0을 1번 출력한 것이라서 1
        DP[0][1] = 0;
        DP[1][0] = 0;
        DP[1][1] = 1;

        for (int i = 2; i < 41; i++) {
            DP[i][0] = DP[i - 1][0] + DP[i - 2][0];  // 0 사용횟수 구하기
            DP[i][1] = DP[i - 1][1] + DP[i - 2][1];  // 1 사용횟수 구하기
        }


        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            int N = Integer.parseInt(br.readLine());

            sb.append(DP[N][0]).append(" ").append(DP[N][1]).append("\n");
        }

        System.out.println(sb);
    }
}
