/*
[백준]


[문제파악]
- 알파벳 소문자로만 이루어진 단어 S가 주어진다.
- 각 알파벳이 단어에 몇 개가 포함되어 있는지 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 단어 S가 주어진다.
- 단어의 길이는 100을 넘지 않으며, 알파벳 소문자로만 이루어져 있다.

[출력]
- 단어에 포함되어 있는 a의 개수, b의 개수, …, z의 개수를 공백으로 구분해서 출력한다.

[구현방법]
- 배열 만들고 단어 하나당 체크해서 알파벳 위치 증가 시켜주면 됨
- 근데 a랑 A랑 아스키 코드가 몇번이더라... 40-60 사이였는디
- 사실 char A, a 찍어보면 알 수 있다 index 0번부터 쓰려면, 97을 빼면 됨
- 아 근데 좀 바보 같은게 ㅋㅋㅋ 그냥 'A'랑 'a'를 빼면 아스키 코드를 알 필요도 없던거잖아...

[보완점]
- 생각해보니까 갯수를 세야하네 int[]로 바꿉시다
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        int[] alphabet = new int[26];

        for (int i = 0; i < input.length(); i++) {
            alphabet[input.charAt(i) - 97]++;
        }

        for (int i = 0; i < 26; i++) {
            sb.append(alphabet[i]).append(" ");
        }

        System.out.println(sb);
    }
}