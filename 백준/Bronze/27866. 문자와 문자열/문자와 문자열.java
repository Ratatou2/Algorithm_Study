/*
[백준]
27866, 문자와 문자열

[문제파악]
- 단어 S와 정수 i가 주어졌을 때, S의 i번째 글자를 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 영어 소문자와 대문자로만 이루어진 단어 S가 주어진다. 단어의 길이는 최대 1,000이다.
- 둘째 줄에 정수 i가 주어진다. (1 <= i <= |S|)

[출력]
- S의 i번째 글자를 출력한다.

[구현방법]
- Class 따려고 진행한 문제
- 번째는 index에 적용할 땐 -1을 해줘야 하는 것만 잊지 않으면 된다 (index는 0번째부터 시작이니까)

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int index = Integer.parseInt(br.readLine());

        System.out.println(input.charAt(index - 1));
    }
}