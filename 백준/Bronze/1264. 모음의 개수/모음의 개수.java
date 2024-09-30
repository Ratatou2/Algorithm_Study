/*
[백준]
1264 모음의 개수

[문제파악]
- 영문 문장을 입력받아 모음의 개수를 세는 프로그램을 작성하시오.
- 모음은 'a', 'e', 'i', 'o', 'u'이며 대문자 또는 소문자이다.

[입력]
- 입력은 여러 개의 테스트 케이스로 이루어져 있으며, 각 줄마다 영어 대소문자, ',', '.', '!', '?', 공백으로 이루어진 문장이 주어진다.
- 각 줄은 최대 255글자로 이루어져 있다.
- 입력의 끝에는 한 줄에 '#' 한 글자만이 주어진다.

[출력]
각 줄마다 모음의 개수를 세서 출력한다.

[구현방법]
- 이것도 저번이랑 비슷한데 indexOf로 찾으면 될 것 같다
- 아 대문자, 소문자 둘 다 찾아야 함을 잊지말자

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String v = "aeiou";

        while (true) {
            int count = 0;
            String input = br.readLine().toLowerCase();  // 대, 소문자 둘 다 찾아야 한다
            if (input.equals("#")) break;

            for (int i = 0; i < input.length(); i++) {
                if (v.indexOf(input.charAt(i)) == -1) continue;
                count++;
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}