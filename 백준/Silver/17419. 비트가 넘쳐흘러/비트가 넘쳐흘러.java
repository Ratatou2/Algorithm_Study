/*
[백준]

[문제파악]
DJ욱제는 비트에 심취한 나머지, 비트를 비틀어 제껴버리는 문제를 내 버렸다!
N자리 이진수 K가 주어진다.
K가 0이 아닐 때까지 아래의 연산을 적용했을 때, 연산이 일어나는 횟수를 구하시오.
K = K-(K&((~K)+1))
아래는 위의 연산에 사용된 연산자에 대한 설명이다.

'+'는 산술 더하기 연산이다. (5 + 2 = 7)
'-'는 산술 빼기 연산이다. (5 - 2 = 3)
'&'는 비트 AND 연산이다. (1101 & 0111 = 0101)
'~'는 비트 NOT 연산이다. 켜진 비트를 끄고, 꺼진 비트를 켜는 연산이다. (~1101 = 0010)

[입력]
첫째 줄에 N이 주어진다.
둘째 줄에 N자리 이진수 K가 주어진다.
K는 0으로 시작하지 않는다. 즉, leading zero는 없다.

[출력]
연산이 일어나는 횟수를 출력한다.

[구현방법]
- 이것은 2진수를 입력 받아서 10진수로 변경만 잘하면 된다
- 밑에도 기술해두었지만 Integer.parseInt에 옵션을 주면 입력을 N진수로 받을 수 있다 (e.g. Integer.parseInt(br.readLine(), N)) 
 
[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine(), 2);  // parseInt할 때 뒤에 옵션을 주면 N진수로 입력 받고 10진수로 변환해준다
        int count = 0;

        while (0 != K) {
            K = K - (K & ((~K) + 1));
            count++;
        }

        System.out.println(count);
    }
}