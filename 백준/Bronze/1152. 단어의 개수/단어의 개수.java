/*
[백준]
1152, 단어의 개수

[문제파악]
- 영어 대소문자와 공백으로 이루어진 문자열이 주어진다
- 몇개의 단어가 있을까? 구해라
- 한 단어가 여러번 등자앟면 등장한 횟수만큼 모두 세어야 한다

[입력]
- 첫째줄엥 대소문자와 공백으로 이루어진 문자열이 주어짐
- 문자열의 길이는 1,000,000을 넘지 않음
- 단어는 공백 한개로 구분되며, 공백이 여러개가 연속해서 나오는 경우는 없다
- 문자열은 공백으로 시작하거나 끝날 수 있다

[출력]
- 단어의 갯수 출력

[구현방법]
- 이거 그냥 split(" ") 하고 그 배열 length 구하면 되는거 아닌가..?
- 아 앞뒤 공백은 제거해줘야 한다 (조건 : 문자열은 공백으로 시작하거나 끝날 수 있다)
- trim()과 strip()이 있는데 strip이 더 많은(공백) 종류를 제거 해줌

[보완점]
- 이거 공백 하나만 들어오면 당하네... 예외처리 필요하네...
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine().strip();
        int result = 0;
        if (!input.isEmpty()) {
            String[] tempString = input.split(" ");
            result = tempString.length;
        }
        
        System.out.println(result);
    }
}