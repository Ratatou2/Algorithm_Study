/*
[백준]
1157, 단어공부

[문제파악]
- 알파벳 대소문자로 된 단어가 주어지면, 이 단어에서 가장 많이 사용된 알파벳이 무엇인지 알아내는 프로그램을 작성하시오. 
- 단, 대문자와 소문자를 구분하지 않는다.

[입력]
- 첫째 줄에 알파벳 대소문자로 이루어진 단어가 주어진다. 
- 주어지는 단어의 길이는 1,000,000을 넘지 않는다.


[출력]
- 첫째 줄에 이 단어에서 가장 많이 사용된 알파벳을 대문자로 출력한다. 
- 단, 가장 많이 사용된 알파벳이 여러 개 존재하는 경우에는 ?를 출력한다.


[구현방법]
- char 'A'가 65인거 사용해서 index 계산해가지고 풀어야할듯?

[보완점]
- 너무 어렵게 생각했나 가장 최다 빈도수 문자열을 판별하는 부분에서 고민을 좀 했다
- 일단 maxCount를 하나 잡고 그거보다 클 경우에만 갱신해주면 된다
- 거기에 더해 maxCount와 같다면 "?"로 다시 셋팅해주면 됨
- 크게 어려울 것은 없었는데 너무 복잡하게 생각했나보다
- 생각보다 고민해볼 것도 많고, JAVA를 써먹은지 얼마 안돼서 많이 배우는 부분이 있어서 문자열 문자가 좋음
- 그리고 매번 파이썬이 그리워지지... ㅋㅋㅋㅋㅋㅋㅋㅋㅋ

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int[] alphabet = new int[26];
        for (int i = 0; i < input.length(); i++) {
            int index = Character.toUpperCase(input.charAt(i)) - 'A';  // charAt을 통해 한글자로 잘라오고, 대문자로 변경
            alphabet[index] += 1;  // 각 문자열의 아스키 코드를 index로 사용
        }

        int maxCount = 0;
        char maxChar = '?';
        for (int i = 0; i < 26; i++) {
            if (maxCount < alphabet[i]) {
                maxCount = alphabet[i];
                maxChar = (char) (i + 'A');  // 가장 많이 나온 알파벳 저장
            } else if (alphabet[i] == maxCount) {
                maxChar = '?';  // 동일한 빈도수의 알파벳이 존재할 경우 "?"로 설정
            }
        }

        System.out.println(maxChar);  // 결과 출력
    }
}