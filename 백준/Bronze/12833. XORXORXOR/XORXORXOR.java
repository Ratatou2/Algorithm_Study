/*
[백준]
12833, XORXORXOR

[문제파악]
- 세 수 A, B, C를 입력 받고
- ((((A XOR B) XOR B) XOR B) … ) XOR B 형태로 연산을 C회 했을 때의 결과값을 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 A, B, C (0 < A, B, C ≤ 109)


[출력]
- 첫째줄에 계산된 결과를 출력.

[구현방법]
- 단순한 반복문인데 비트마스킹에 대해 공부하는 용도로 사용하자
and (&) : 양쪽 비트가 모두 1이면 1
or  (|) : 양쪽 비트 중 한개만 1이면 1
xor (^) : 양쪽 비트가 서로 다르면 1
not (~) : 원래 비트와 반대로

13 ^ 3이면
1101
0011
두개를 XOR하는 것이다
서로 달라야 1이니까
1110이 되는 것이고 10진수로 환산하면
2 + 4 + 8 = 14인 셈

[보완점]
- 와 XOR 두번하면 자기 자신이 되는 것을 고려해서 반복 횟수를 줄이는 C % 2로 분기 나누는 방법도 있었음 ㄷㄷ
- XOR 연산은 다음과 같은 성질을 가지고 있다
    - a XOR a = 0  // 자기 자신과는 전부 똑같으니까 서로 달라야 1이 나오는 XOR 입장에서는 저랟로 1이 나올 수가 없음
    - a XOR 0 = a  // 0은 그냥 0이니까, 자기 자신의 1인 부분은 전부 1로 나오고 0은 둘다 0일테니 0이 나와서 자기자신 그대로 도출됨
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

//        for (int i = 0; i < C; i++) {
//            A = A ^ B;
//        }
//        System.out.println(A);

        // XOR 두번하면 자기 자신
        if (C % 2 == 0) System.out.print(A);
        else System.out.print(A^B);
    }
}