/*
[백준]


[문제파악]
- 입력 받은 대로 출력하는 프로그램을 작성하시오.

[입력]
- 입력이 주어진다.
- 입력은 최대 100줄로 이루어져 있고, 알파벳 소문자, 대문자, 공백, 숫자로만 이루어져 있다.
- 각 줄은 100글자를 넘지 않으며, 빈 줄이 주어질 수도 있고, 각 줄의 앞 뒤에 공백이 있을 수도 있다.

[출력]
- 입력받은 그대로 출력한다.

[구현방법]
- 이거 읽은 그대로 토해내면 될 거 같은데
- 근데 나는 매번 이런 부분에 약한데, 저걸 whlie문을 돌리는건 알겠는데 어떤 조건으로 멈춰야하지...?
- (input = br.readLine()) != null로는 IDE에선 안멈추는디 흠...

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;

        while((input = br.readLine()) != null) {
            sb.append(input).append("\n");
        }

        System.out.println(sb);
    }
}