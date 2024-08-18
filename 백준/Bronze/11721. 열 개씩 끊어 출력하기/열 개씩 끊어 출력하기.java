/*
[백준]
11821, 10개씩출력하기

[문제파악]
- 알파벳 소문자와 대문자로만 이루어진 길이가 N인 단어가 주어진다.

[입력]
- 첫째 줄에 단어가 주어진다.
- 단어는 알파벳 소문자와 대문자로만 이루어져 있으며, 길이는 100을 넘지 않는다.
- 길이가 0인 단어는 주어지지 않는다.

[출력]
- 입력으로 주어진 단어를 열 개씩 끊어서 한 줄에 하나씩 출력한다.
- 단어의 길이가 10의 배수가 아닌 경우에는 마지막 줄에는 10개 미만의 글자만 출력할 수도 있다.

[구현방법]
- 흠.. 그냥 한글짜씩 읽어제끼는거 말고 뭐 별수가 있나...

[보완점]


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();

        for (int i = 0; i < input.length(); i++) {
            // 0번 인덱스부터 시작하니까 10번 인덱스(11번째 글자)부터 다음줄에 적는다
            // 추가로 10개씩 끊으면 바로 출력해야하므로 끊어서 출력한다
            // i가 0일 때 줄빠꿈이 일어나면 안된다!
            if (i != 0 && i % 10 == 0) sb.append("\n");
            sb.append(input.charAt(i));
        }

        System.out.println(sb);
    }
}