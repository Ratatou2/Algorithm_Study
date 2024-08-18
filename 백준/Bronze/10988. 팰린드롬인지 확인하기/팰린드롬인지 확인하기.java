/*
[백준]


[문제파악]
- 알파벳 소문자로만 이루어진 단어가 주어진다.
- 이때, 이 단어가 팰린드롬인지 아닌지 확인하는 프로그램을 작성하시오.
- 팰린드롬이란 앞으로 읽을 때와 거꾸로 읽을 때 똑같은 단어를 말한다.
- level, noon은 팰린드롬이고, baekjoon, online, judge는 팰린드롬이 아니다.

[입력]
- 첫째 줄에 단어가 주어진다.
단어의 길이는 1보다 크거나 같고, 100보다 작거나 같으며, 알파벳 소문자로만 이루어져 있다.

[출력]
- 첫째 줄에 팰린드롬이면 1, 아니면 0을 출력한다.

[구현방법]
- 그냥 String으로 받고 charAt() 써서 인덱싱으로 비교해야할듯

[보완점]
- 굳이 반복문 안쓰고 아예 뒤집은 문자열을 만들고, 그것과 같은지 통째로 비교하는 방법도 있다!

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        boolean isPalindrome = true;

        for (int i = 0; i < input.length(); i++) {
            // 대칭되는 자리의 글자를 비교한다
            if (input.charAt(i) != input.charAt(input.length() - 1 - i)) {
                isPalindrome = false;
                break;
            }
        }

        System.out.println(isPalindrome ? "1" : "0");
    }
}