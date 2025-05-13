/*
[백준]
2338, 긴 자리 계산

[문제파악]
두 수 A, B를 입력받아, A+B, A-B, A×B를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 A가, 둘째 줄에 B가 주어진다.
각각의 수는 10진수로 1,000자리를 넘지 않으며 양수와 음수가 모두 주어질 수 있다.

[출력]
첫째 줄에 A+B, 둘째 줄에 A-B, 셋째 줄에 A×B를 출력한다.
각각을 출력할 때, 답이 0인 경우를 제외하고는 0으로 시작하게 해서는 안 된다(1을 01로 출력하면 안 된다는 의미).

[구현방법]
- 1000자리라는 것은 BigInteger를 써야한다는 의미이다
- 브론즈 문제에서 형변환을 신경써야할줄이야..?

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        BigInteger A = new BigInteger(br.readLine());
        BigInteger B = new BigInteger(br.readLine());

        sb.append(A.add(B)).append("\n")
                .append(A.subtract(B)).append("\n")
                .append(A.multiply(B)).append("\n");

        System.out.println(sb);
    }
}