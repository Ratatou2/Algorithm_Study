/*
[백준]
29092 KMP는 왜 KMP일까?

[문제파악]
- 하이픈 뒤의 첫글자인 대문자를 따서 줄임말을 만들란다

[입력]
- 입력은 한 줄로 이루어져 있고, 최대 100글자의 영어 알파벳 대문자, 소문자, 그리고 하이픈 ('-', 아스키코드 45)로만 이루어져 있다.
- 첫 번째 글자는 항상 대문자이다.
- 그리고, 하이픈 뒤에는 반드시 대문자이다.
- 그 외의 모든 문자는 모두 소문자이다.

[출력]
- 첫 줄에 짧은 형태 이름을 출력한다.

[구현방법]
- '-'으로 split하고 charAt(0) 가져오면 될 것 같다
- 아 그리고 StringTokenizer 쓰면 '-'로 구분자 자름을 할 수 있다

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");
        StringBuilder sb = new StringBuilder();

        while (st.hasMoreTokens()) {
            String temp = st.nextToken();
            sb.append(temp.charAt(0));
        }

        System.out.println(sb);
    }
}