/*
[백준]


[문제파악]
- 알파벳 소문자로만 이루어진 단어가 주어진다.
- 이때, 모음(a, e, i, o, u)의 개수를 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 단어가 주어진다.
- 단어의 길이는 1보다 크거나 같고, 100보다 작거나 같으며, 알파벳 소문자로만 이루어져 있다.

[출력]
- 첫째 줄에 모음의 개수를 출력한다.

[구현방법]
- 이거 contains() 써도 되고, indexOf() 써도 된다
- 대신 contains() 쓰려면 String 내부에 "a" 이런식으로 (=temp.contains("a")) 찾아야함
- 즉, aeiou를 하나의 문자열로 만든 뒤 contains 쓰면 된다
- indexOf는 그냥 char[] 배열에 현재 문자(char)가 있는지 확인하면 된다
- 보통 indexOf가 더 빠르다는데 뭣이 더 빠른지 봅시다

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        char[] vowel = {'a', 'e', 'i', 'o', 'u'};
        String vowels = "aeiou";
        String input = br.readLine();
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            if (vowels.indexOf(input.charAt(i)) != -1) {
                count++;
            }
        }

        System.out.println(count);
    }
}