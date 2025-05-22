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
- 51점 부분 점수인 이유 N은 1,000,000 까지 가능하고 이 말인 즉, 2^1,000,000 크기의 수까지 가능하다는 것이다
- 즉, BigInteger로 해줘야 함 (int는 가볍게 넘치는 큰 숫자라서)

- 그게 아니고 이건 그냥 비트의 동작 원리를 깊게 이해하고 있으면 아주 쉽게 풀 수 있는 문제였다
- 일단 결론부터 말하면 K를 입력 받았으면 비트 1의 갯수를 세면 된다
- 그게 되는 이유는? 문제에서 주어진 'K-(K&((~K)+1))' 연산이 K에서 가장 오른쪽에 있는 1비트 하나씩을 제거하는 연산이기 때문이다
    - 1110가 주어졌다고 가정하면?
    - 1100
    - 1000
    - 0000
    - 총 3번의 연산이 필요한데 이 말인 즉, 1의 갯수를 세는 것과 동일하다

- 그렇다면? 왜 이 연산이 가장 오른쪽 1비트를 추출할까?
- 이는 2의 보수(Two's Complement) 원리 때문이다
    1) ~K는 모든 비트를 뒤집습니다
    2) (~K) + 1은 K의 2의 보수입니다
    3) K & ((~K) + 1)에서:
        - K의 가장 오른쪽 1비트 이후의 0들은 ~K에서 1이 되고, +1로 인해 carry가 전파됩니다
        - 결국 K의 가장 오른쪽 1비트 위치에서만 둘 다 1이 되어 AND 결과가 1이 됩니다
    4) 그리고 이 값을? K에서 빼면? 아래와 같은 순서로 진행되는 것이다
        - 1010에 공식을 적용하면?
            1) 0101 -> 101 (비트 뒤집기)
            2) 110 (+1 해버리기)
            3) 10 (1010과 110을 & 처리하기)
            4) 1000 (K인 1010에서 10을 빼버리기)

- 결과적으로 K가 0이 될 때까지의 연산 횟수 = K에 포함된 1비트의 개수이다
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        String K = br.readLine();

        int answer = 0;
        for (int i = 0; i < K.length(); i++) {
            if(K.charAt(i) == '1') answer++;
        }
        System.out.println(answer);
    }
}