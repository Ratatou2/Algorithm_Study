/*
[백준]
4470, 줄번호

[문제파악]
- 텍스트에서 줄을 입력받은 뒤, 줄 번호를 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 줄의 수 N이 주어진다.
- 둘째 줄부터 N개의 줄에 각 줄의 내용이 주어진다.
- 각 줄에 있는 글자의 개수는 50글자를 넘지 않는다.

[출력]
- 각 문장의 앞에 줄 번호를 추가한 뒤 출력한다.
- 줄 번호는 1번부터 시작한다.
- 줄번호를 추가하는 형식은 출력 예제를 참고하면 된다.

[구현방법]
- 이거 그냥 한줄씩 추가할 때 앞에 반복문 i 숫자랑 . 붙이면 해결!

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

        for (int i = 1; i <= N; i++) {
            sb.append(i).append(". ").append(br.readLine()).append("\n");
        }

        System.out.println(sb);
    }
}