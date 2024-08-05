/*
[백준]
9933, 민균이의 비밀번호

[문제파악]
- 파일의 텍스트 중 하나는 비밀번호이다
- 모든 단어는 홀수 길이이며, 비밀번호를 뒤집어서 쓴 문자열도 포함되어 있다
	- ex. 비밀번호가 "tulipan"인 경우에 목록에는 "napilut"도 존재함
- 파일에 적혀있는 단어가 모두 주어졌을 때, 비밀번호의 길이와 가운데 글자를 출력하라

[입력]
- 첫째 줄에 단어의 수 N (2 ≤ N ≤ 100)이 주어진다.
- 다음 N개 줄에는 파일에 적혀있는 단어가 한 줄에 하나씩 주어진다.
- 단어는 알파벳 소문자로만 이루어져 있으며, 길이는 2보다 크고 14보다 작은 홀수이다.

[출력]
- 첫째 줄에 비밀번호의 길이와 가운데 글자를 출력한다. 항상 답이 유일한 경우만 입력으로 주어진다.

[구현방법]


[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static boolean checkPalindrome(String str) {
        int len = str.length();

        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - 1 - i)) return false;
        }

        return true;
    }

    static String reverseString(String str) {
        StringBuilder sb = new StringBuilder();

        int repeat = str.length();
        for (int i = 0; i < repeat; i++) {
            sb.append(str.charAt(repeat - 1 - i));
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Set<String> checkPassword = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            int mid = input.length() / 2;

            // 회문(좌우대칭 문자열)이거나 좌우 뒤집어둔 단어가 이미 Set안에 있으면 답을 찾은 것이다
            if (checkPalindrome(input) || checkPassword.contains(input)) {
                sb.append(input.length()).append(" ").append(input.charAt(mid));
                break;
            }

            checkPassword.add(input);
            checkPassword.add(reverseString(input));
        }

        System.out.println(sb);
    }
}