

/*
[백준]
1934, 최소공배수

[문제파악]
두 자연수 A와 B에 대해서, A의 배수이면서 B의 배수인 자연수를 A와 B의 공배수라고 한다.
이런 공배수 중에서 가장 작은 수를 최소공배수라고 한다.
예를 들어, 6과 15의 공배수는 30, 60, 90등이 있으며, 최소 공배수는 30이다.
두 자연수 A와 B가 주어졌을 때, A와 B의 최소공배수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 테스트 케이스의 개수 T(1 ≤ T ≤ 1,000)가 주어진다. 둘째 줄부터 T개의 줄에 걸쳐서 A와 B가 주어진다. (1 ≤ A, B ≤ 45,000)

[출력]
첫째 줄부터 T개의 줄에 A와 B의 최소공배수를 입력받은 순서대로 한 줄에 하나씩 출력한다.

[구현방법]
- LCM을 구하는 문제이다
- 일정도 있었고 어제 풀었던 문제 복습하려고 쉬운거 찾았는데 이건 뭐.. 수식은 기억나는데 뭘 해야구하더라... 가 기억이 안나네 ㅋㅋ ㅠ
- 복잡해보이지만 핵심은 하나다
- 가장 큰 '최대공약수'를 구하기 위해선 [두 수를 곱한 값]을 [최소공배수]로 나눠주면 된다
- 걍 수학적 개념임 ㅠㅠ 외워 어쩌겠어 내가 뭘할 수 있겠어 의문 갖지말고 외우란말이야 어차피 하나의 정의라고

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    static int GCD(int a, int b) {
        return b == 0 ? a : GCD(b, a % b);
    }

    static int LCM(int a, int b) {
        return a / GCD(a, b) * b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            sb.append(LCM(A, B)).append("\n");
        }

        System.out.println(sb);
    }
}
