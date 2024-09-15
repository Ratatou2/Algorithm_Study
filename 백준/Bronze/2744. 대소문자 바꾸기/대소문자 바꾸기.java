import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
[백준]
2744, 대소문자 바꾸기

[문제파악]
- 영어 소문자와 대문자로 이루어진 단어를 입력받은 뒤, 대문자는 소문자로, 소문자는 대문자로 바꾸어 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 영어 소문자와 대문자로만 이루어진 단어가 주어진다. 단어의 길이는 최대 100이다.

[출력]
- 첫째 줄에 입력으로 주어진 단어에서 대문자는 소문자로, 소문자는 대문자로 바꾼 단어를 출력한다.

[구현방법]
- 이거 또 찾아보면 char로 대문자인지 소문자인지 구분하는 것있을 것 같은데...
- 기능이 정확히 뭔지 모르니까 'a' <= _ <= 'z' 랑 'A' <= _ <= 'Z' 사잇값으로 구해야겠다

[보완점]
- 일단 Character에 toUpperCase, toLowerCase가 있다!! 와!!!
- 더군다나 isUpperCase로 대문자로 체크할 수 있음!! 와!!!!
- 이거로 쓰면 한줄로 가능할듯

*/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();

        for (int i = 0; i < input.length(); i++) {
            char curChar = input.charAt(i);

            sb.append(Character.isUpperCase(curChar) ? Character.toLowerCase(curChar) : Character.toUpperCase(curChar));
        }

        System.out.println(sb);
    }

    // char 범위 값으로 대문자, 소문자 체크하는 방식
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        String input = br.readLine();
//        for (int i = 0; i < input.length(); i++) {
//            char curChar = input.charAt(i);
//            
//            if ('a' <= curChar && curChar <= 'z') {
//                sb.append(Character.toUpperCase(curChar));
//            } else if ('A' <= curChar && curChar <= 'Z') {
//                sb.append(Character.toLowerCase(curChar));
//            }
//        }
//    }
}