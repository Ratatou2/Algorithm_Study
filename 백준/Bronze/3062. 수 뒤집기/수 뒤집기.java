/*
[백준]
3062, 수 뒤집기

[문제파악]
- 수 124를 뒤집으면 421이 되고 이 두 수를 합하면 545가 된다.
- 124와 같이 원래 수와 뒤집은 수를 합한 수가 좌우 대칭이 되는지 테스트 하는 프로그램을 작성하시오.

[입력]
- 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다.
- 각 테스트 케이스는 한 줄에 하나의 정수 N(10 ≤ N ≤ 100000)이 주어진다.

[출력]
- 각 테스트 케이스에 대해서 원래 수와 뒤집은 수를 합한 수가 좌우 대칭이 되면 YES를 아니면 NO를 한 줄에 하나씩 출력한다.

[구현방법]
- 일단, 문자열 상태에서 뒤집고 원래 숫자랑 더한 뒤, 팰린드롬 체크하면 될듯하다
- 한 자릿수씩 더하면 앞자릿수로 올림 하는 경우를 예외처리 하는게 더 복잡할듯 하니 뒤집자

[보완점]
- StringBuilder 객체는 mutable(변경 가능한) 객체란다
- 이말인 즉, 한 곳에서 reverse() 같은 메서드를 호출하면 해당 객체의 내부 값이 직접 변경됨...
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String isPalindrome(int x) {
        String num = String.valueOf(x);
        int numLength = num.length();

        for (int i = 0; i <  numLength / 2; i++) {
            if (num.charAt(i) != num.charAt(numLength - 1 - i)) return "NO";
        }

        return "YES";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringBuilder N = new StringBuilder(br.readLine());

            String originalN = N.toString();
            String reverseN = N.reverse().toString();

            int result = Integer.parseInt(originalN) + Integer.parseInt(reverseN);

            sb.append(isPalindrome(result)).append("\n");
        }

        System.out.println(sb);
    }
}