/*
[백준]
11655, ROT13

[문제파악]
- ROT13은 카이사르 암호의 일종으로 영어 알파벳을 13글자씩 밀어서 만든다.
- 예를 들어, "Baekjoon Online Judge"를 ROT13으로 암호화하면 "Onrxwbba Bayvar Whqtr"가 된다.
- ROT13으로 암호화한 내용을 원래 내용으로 바꾸려면 암호화한 문자열을 다시 ROT13하면 된다.
- 앞에서 암호화한 문자열 "Onrxwbba Bayvar Whqtr"에 다시 ROT13을 적용하면 "Baekjoon Online Judge"가 된다.
- ROT13은 알파벳 대문자와 소문자에만 적용할 수 있다.
- 알파벳이 아닌 글자는 원래 글자 그대로 남아 있어야 한다.
- 예를 들어, "One is 1"을 ROT13으로 암호화하면 "Bar vf 1"이 된다.
- 문자열이 주어졌을 때, "ROT13"으로 암호화한 다음 출력하는 프로그램을 작성하시오.

[입력]
첫째 줄에 알파벳 대문자, 소문자, 공백, 숫자로만 이루어진 문자열 S가 주어진다. S의 길이는 100을 넘지 않는다.

[출력]
첫째 줄에 S를 ROT13으로 암호화한 내용을 출력한다.

[구현방법]
- 이거 char 쓰면 유니코드에 숫자로 더할 수 있으니까 그렇게 하면 되지 않을까?
- 아 이게 참 생각지도 못한 방법이 있었다
- 사람들이 똑똑한게 A~Z, a~z 범위를 초과할 수도 있다 그럴 경우를 대비해야 함
- 일례로 z를 밀어버리면 초과해버리니까, 그래서 일단 첫 글자인 'a' 또는 'A'를 뺸다
- 그 상태에서 13을 더하면 13자리를 밀어버린 셈이고, 알파벳이 26자리니까 나머지를 구한다 (그러면 알파벳 순서로 N번째를 구하게 된다)
- 이걸 다시 아스키 코드로 구해야하니까 'a' 또는 'A'를 더하면! 된다
- 사람들 정말 똑똑한듯... 정말 많이 배운다  

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
            char cur = input.charAt(i);

            if ('A' <= cur && cur <= 'Z') {
                cur = (char) ((cur - 'A' + 13) % 26 + 'A');
            } else if ('a' <= cur && cur <= 'z') {
                cur = (char) ((cur - 'a' + 13) % 26 + 'a');
            }

            sb.append(cur);
        }

        System.out.println(sb);
    }
}