/*
[백준]


[문제파악]
컴퓨터는 뺄셈을 처리할 때 내부적으로 2의 보수를 사용한다.
어떤 수의 2의 보수는 해당하는 숫자의 모든 비트를 반전시킨 뒤, 1을 더해 만들 수 있다.
이때, 32비트 기준으로 처음 표현했던 수와 그 2의 보수의 서로 다른 비트 수를 출력하라.

[입력]
첫째 줄에 정수 N(1 ≤ N ≤ 109)이 주어진다.

[출력]
첫째 줄에 N과 N의 보수의 서로 다른 비트 수를 출력한다.

[구현방법]
- 최근에 비트 문제에 털리고나서 도전하는 비트 문제...
- 보수는 ~를 붙이면 만들 수 있단다
- 2의 보수니까 1을 더해야하고 1을 더하는 것은 당연히

[보완점]
*/

import com.sun.security.jgss.GSSUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int complementN = ~N + 1;

        // 2진수 문자열로 표현할 건데 32자리 중 빈자리가 있다면 0으로 채운다
        String N_Str = String.format("%32s", Integer.toBinaryString(N)).replace(' ', '0');
        String complementN_Str = String.format("%32s", Integer.toBinaryString(complementN)).replace(' ', '0');

        int count = 0;
        for (int i = 0; i < N_Str.length(); i++) {
            if (N_Str.charAt(i) != complementN_Str.charAt(i)) count++;
        }

        System.out.println(count);
    }
}