/*
[백준]
11718, 그대로 출력하기

[문제파악]
- 입력 받은 대로 출력하는 프로그램을 작성하시오.

[입력]
- 입력이 주어진다.
- 입력은 최대 100줄로 이루어져 있음
- 알파벳 소문자, 대문자, 공백, 숫자로만 이루어져 있다.
- 각 줄은 100글자를 넘지 않으며, 빈 줄은 주어지지 않는다.
- 또, 각 줄은 공백으로 시작하지 않고, 공백으로 끝나지 않는다.

[출력]
- 입력받은 그대로 출력한다.

[구현방법]
- 흠.. 이거 입력값이 끝난 줄 어떻게 알지? (난이도가 쉬운 문제를 풀더라도 이런 자잘한 고민들을 하는건 꽤 도움이 됨. 몰랐던걸 알게됨!)

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

        while (input != null) {
            sb.append(input).append("\n");

            input = br.readLine();
        }

        System.out.println(sb);
    }
}