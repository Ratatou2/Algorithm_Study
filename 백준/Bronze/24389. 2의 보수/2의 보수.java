/*
[백준]
24389, 2의 보수

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
- XOR 연산을 쓰면 그냥 몇개 차이나는지 계산 가능..이라네... 머쓱..
- 그리고 Integer 함수 중에 bitCount 함수도 있음;;
- 무엇보다 자바는 기본적으로 10진수로 숫자를 입력하고 출력한다 But!!! 내부 게산, 모든 정수는 실질적으로 이진수로 저장되고, 연산도 이진수로 처리된다
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int complementN = ~N + 1;

        int diff = N ^ complementN;

        System.out.println(Integer.bitCount(diff));
    }
}