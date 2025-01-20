/*
[백준]
2609, 최대공약수와 최소공배수

[문제파악]
- 두 개의 자연수를 입력받아 최대 공약수와 최소 공배수를 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에는 두 개의 자연수가 주어진다.
- 이 둘은 10,000이하의 자연수이며 사이에 한 칸의 공백이 주어진다.

[출력]
- 첫째 줄에는 입력으로 주어진 두 수의 최대공약수를, 둘째 줄에는 입력으로 주어진 두 수의 최소 공배수를 출력한다.

[구현방법]
- 수학적으로는 풀겠는데 이거 뭐 map 써서 풀어야하나..?
- 공부해보니 유클리드 호제법이란게 있다고..... 그냥 신기...
- 유클리드 호제법이란?
    - 최대공약수를 효율적으로 구하는 알고리즘
    - 유클리드가 제안한 알고리즘으로 두 수를 나누면서 나머지를 이용해 반복적으로 계산하는 방식이다
- 결국 수학 공식이고 그걸 알고리즘으로 만들었다는...

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 유클리드 호제법을 이용해 GCD 계산
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // LCM 계산 (GCD를 이용)
    public static int lcm(int a, int b, int gcd) {
        return (a * b) / gcd;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        // 최대공약수와 최소공배수 계산
        int gcd = gcd(a, b);
        int lcm = lcm(a, b, gcd);

        sb.append(gcd).append("\n").append(lcm);

        System.out.println(sb);
    }
}