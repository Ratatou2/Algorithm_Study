/*
[백준]


[문제파악]
- 짝이 없는 경재는 매일 홀로 있다보니 홀수를 판별할 수 있는 능력이 생겼다.
- 창식이는 경재의 말이 사실인지 그 능력을 시험해보려 한다.
- 창식이의 의심이 끝이 없을 것 같아 N개만 확인하기로 정했다.
- N개의 정수가 주어지면 홀수인지 짝수인지를 출력하는 프로그램을 만들어 경재의 능력을 검증할 수 있게 도와주자.

[입력]
- 첫 번째 줄에 숫자의 개수 N(1 <= N <= 100)이 주어진다.
- 두 번째 줄부터 N+1번째 줄에 걸쳐 홀수인지 짝수인지 확인할 정수 K (1 <= K <= 10^60)가 주어진다.

[출력]
- N개의 줄에 걸쳐 한 줄씩 정수 K가 홀수라면 'odd'를, 짝수라면 'even'을 출력한다.

[구현방법]
- 숫자가 너무 커서 NumberFormat 에러난다 숫자 크기, 조건 잘 좀 보자...
- String으로 처리해서 맨 끝자리 숫자만 체크하면 될듯

[보완점]
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String n = br.readLine();
            int temp = n.charAt(n.length() - 1);
            sb.append(temp % 2 == 0 ? "even" : "odd").append("\n");
        }

        System.out.println(sb);
    }
}