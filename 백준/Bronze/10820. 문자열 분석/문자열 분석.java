/*
[백준]
10820, 문자열 분석

[문제파악]
- 문자열 N개가 주어진다.
- 이때, 문자열에 포함되어 있는 소문자, 대문자, 숫자, 공백의 개수를 구하는 프로그램을 작성하시오.
- 각 문자열은 알파벳 소문자, 대문자, 숫자, 공백으로만 이루어져 있다.

[입력]
- 첫째 줄부터 N번째 줄까지 문자열이 주어진다. (1 ≤ N ≤ 100)
- 문자열의 길이는 100을 넘지 않는다.

[출력]
첫째 줄부터 N번째 줄까지 각각의 문자열에 대해서 소문자, 대문자, 숫자, 공백의 개수를 공백으로 구분해 출력한다.

[구현방법]
- 이거 그냥 문자열 입력 받으면 charAt()으로 검사하는게 베스트인 것 같다
- 카운트하는 배열은 따로 만들어서 추가해야할듯

[보완점]
- input이 null일 수 있다는 것을 잊지말고 예외처리 해줄 것


This is String
SPACE    1    SPACE
 S a M p L e I n P u T
0L1A2S3T4L5I6N7E8

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String input = br.readLine();

            if (input == null || input.equals("") || input.equals(" ") || input.equals("\n")) break;
            int[] count = new int[4];
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);

                // 소문자, 대문자, 숫자, 공백 카운트
                if (Character.isLowerCase(c)) count[0]++;
                else if (Character.isUpperCase(c)) count[1]++;
                else if (Character.isDigit(c)) count[2]++;
                else count[3]++;
            }

            // 답 추가
            for (int i = 0; i < 4; i++) {
                sb.append(count[i]).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}