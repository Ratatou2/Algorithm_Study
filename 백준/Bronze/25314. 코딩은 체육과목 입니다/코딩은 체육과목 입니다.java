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


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int repeat = 0 == N % 4 ? N/4 : N/4 + 1;

        for (int i = 0; i < repeat; i++) sb.append("long ");
        sb.append("int");

        System.out.println(sb);
    }
}