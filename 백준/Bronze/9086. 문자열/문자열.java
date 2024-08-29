/*
[백준]


[문제파악]
- 문자열을 입력으로 주면 문자열의 첫 글자와 마지막 글자를 출력하는 프로그램을 작성하시오.

[입력]
- 입력의 첫 줄에는 테스트 케이스의 개수 T(1 ≤ T ≤ 10)가 주어진다.
- 각 테스트 케이스는 한 줄에 하나의 문자열이 주어진다.
- 문자열은 알파벳 A~Z 대문자로 이루어지며 알파벳 사이에 공백은 없으며 문자열의 길이는 1000보다 작다.

[출력]
- 각 테스트 케이스에 대해서 주어진 문자열의 첫 글자와 마지막 글자를 연속하여 출력한다.

[구현방법]
- 이거 그냥 charAt() 써가지고 0번째 인덱스 length -1 인덱스 출력하면 될듯하다

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            String input = br.readLine();

            // 첫 글자, 끝 글짜, 출바꿈 추가
            sb.append(input.charAt(0)).append(input.charAt(input.length()-1)).append("\n");
        }

        System.out.println(sb);
    }
}