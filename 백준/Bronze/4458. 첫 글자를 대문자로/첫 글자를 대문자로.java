/*
[백준]
4458, 첫 글자를 대문자로

[문제파악]
- 문장을 읽은 뒤, 줄의 첫 글자를 대문자로 바꾸는 프로그램을 작성하시오.


[입력]
- 첫째 줄에 줄의 수 N이 주어진다.
- 다음 N개의 줄에는 문장이 주어진다.
- 각 문장에 들어있는 글자의 수는 30을 넘지 않는다.
- 모든 줄의 첫 번째 글자는 알파벳이다.


[출력]
- 각 줄의 첫글자를 대문자로 바꾼뒤 출력한다.

[구현방법]
- 문자열 charAt(0) 해가지구 첫번째만 대문자로 하면될듯!
- 근데 어떻게 결합하지?
    - 일단 첫문자 대문자인건 Character의 UpperCase 쓰면 될듯
    - String의 SubString을 쓰면 된다
    - 더 빠른 방법은 없으려나 그래도 subString을 열어보니 내부적으로 for문을 돌진 않으니 생각보다 빠를지도 모른다

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
            String input = br.readLine();

            // 대문자가 아니면 대문자로 변환
            if (!Character.isUpperCase(input.charAt(0))) {
                input = Character.toUpperCase(input.charAt(0)) + input.substring(1);
                sb.append(input);
            } else sb.append(input);

            sb.append("\n");
        }

        System.out.println(sb);
    }
}