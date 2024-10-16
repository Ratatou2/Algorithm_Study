/*
[백준]
2857, FBI

[문제파악]
- 5명의 요원 중 FBI 요원을 찾는 프로그램을 작성하시오.
- FBI요원은 요원의 첩보원명에 FBI가 들어있다.

[입력]
- 5개 줄에 요원의 첩보원명이 주어진다.
- 첩보원명은 알파벳 대문자, 숫자 0~9, 대시 (-)로만 이루어져 있으며, 최대 10글자이다.

[출력]
- 첫째 줄에 FBI 요원을 출력한다.
- 이때, 해당하는 요원이 몇 번째 입력인지를 공백으로 구분하여 출력해야 하며, 오름차순으로 출력해야 한다.
- 만약 FBI 요원이 없다면 "HE GOT AWAY!"를 출력한다.

[구현방법]
- '-'로 split 해서는 FBI가 연속하게 있는지 찾아야할듯하다
- 생각해보니 String에는 contains라는 아주아주 훌륭한 기능이 있다!

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 5; i++) {
            String input = br.readLine();

            if (input.contains("FBI")) sb.append(i).append(" ");
        }

        System.out.println(sb.length() == 0 ? "HE GOT AWAY!" : sb);
    }
}