/*
[백준]
25314, 코딩은 체육과목 입니다

[문제파악]

[입력]
- 첫 번째 줄에는 문제의 정수 N이 주어진다.

[출력]
- 혜아가 N바이트 정수까지 저장할 수 있다고 생각하는 정수 자료형의 이름을 출력하여라.

[구현방법]
- N을 4로 나눠서 그 갯수만큼 long을 작성하고 맨 마지막에 int를 붙여준다

[보완점]
- 문제를 잘 좀 읽자 N은 '항상' 4의 배수로 제공된다
- 즉 삼항연산자를 써서 4의 배수인지를 체크하는 코드는 필요 없는 셈

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        // int repeat = 0 == N % 4 ? N/4 : N/4 + 1;  // N은 항상 4의 배수로 주어지기 때문에 사실상 필요 없는 부분이다

        for (int i = 0; i < N/4; i++) sb.append("long ");
        sb.append("int");

        System.out.println(sb);
    }
}