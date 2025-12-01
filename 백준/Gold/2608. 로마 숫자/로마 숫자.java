

/*
[백준]
2608, 로마 숫자

[문제파악]
로마 시대 때는 현재 사용하는 아라비아 숫자가 아닌 다른 방법으로 수를 표현하였다.

로마 숫자는 다음과 같은 7개의 기호로 이루어진다.

기호	I	V	X	L	C	D	M
값	1	5	10	50	100	500	1000
수를 만드는 규칙은 다음과 같다.

보통 큰 숫자를 왼쪽에 작은 숫자를 오른쪽에 쓴다.
그리고 그 값은 모든 숫자의 값을 더한 값이 된다.
예를 들어 LX = 50 + 10 = 60 이 되고, MLI = 1000 + 50 + 1 = 1051 이 된다.
V, L, D는 한 번만 사용할 수 있고 I, X, C, M은 연속해서 세 번까지만 사용할 수 있다.
예를 들어 VV나 LXIIII 와 같은 수는 없다.
그리고 같은 숫자가 반복되면 그 값은 모든 숫자의 값을 더한 값이 된다.
예를 들어 XXX = 10 + 10 + 10 = 30 이 되고, CCLIII = 100 + 100 + 50 + 1 + 1 + 1 = 253 이 된다.
작은 숫자가 큰 숫자의 왼쪽에 오는 경우는 다음과 같다.
IV = 4, IX = 9, XL = 40, XC = 90, CD = 400, CM = 900 을 나타낸다.
이들 각각은 한 번씩만 사용할 수 있다.
그런데 IV 와 IX 는 같이 쓸 수 없으며 XL 과 XC, CD 와 CM 또한 같이 쓸 수 없다. 이들 외에는 작은 숫자가 큰 숫자 왼쪽 어디에도 나올 수 없다.
예를 들어 XCXC 나 CMCD, VX 나 IIX 와 같은 수는 없다.
참고로 LIX = 50 + 9 = 59, CXC = 100 + 90 = 190 이 된다.
모든 수는 가능한 가장 적은 개수의 로마 숫자들로 표현해야 한다.
예를 들어 60 은 LX 이지 XLXX 가 아니고, 5 는 V 이지 IVI 가 아니다.
아래의 예를 참고 하시오.

DLIII = 500 + 50 + 3 = 553
MCMXL = 1000 + 900 + 40 = 1940
235 = CCXXXV
2493 = MMCDXCIII
로마 숫자로 이루어진 두 수를 입력받아 그 둘을 더한 값을 아라비아 숫자와 로마 숫자로 출력하는 프로그램을 작성하시오.

[입력]
첫째 줄과 둘째 줄에 하나씩 로마 숫자로 표현된 수가 주어진다. 입력된 각 수는 2000 보다 작거나 같고, 두 수의 합은 4000보다 작다.

[출력]
입력으로 주어진 두 수를 더한 값을 첫째 줄에 아라비아숫자로 출력하고 둘째 줄에는 로마 숫자로 출력한다.

[구현방법]
- 앞에서부터 순차적으로 탐색한다
- 이전 것을 기억하고 있다가 현재 것보다 작은 숫자면 뺄셈을 통해서 두 수를 하나로 묶는다
- 이런식으로 지속적으로 탐색해가면서 한자리씩 계산해간다
- 그리고 이제 완성된 숫자를 적어나갈 땐 4인지 6인지에 따라 높은 숫자에서 배서 완성할지, 더해서 완성할지 결정된다

- 오.. 나중에 로마 문자 구할 때 그냥 큰 수부터 빼버리는건 미처 생각못했는데 굉장히 좋은 것 같다 (그리디)

[보완점]

*/

import java.io.*;
import java.util.*;

public class Main {
    // 디폴트 로마 숫자 세팅
    static final int[] alphabet = new int[26];
    static {
        alphabet['I' - 'A'] = 1;
        alphabet['V' - 'A'] = 5;
        alphabet['X' - 'A'] = 10;
        alphabet['L' - 'A'] = 50;
        alphabet['C' - 'A'] = 100;
        alphabet['D' - 'A'] = 500;
        alphabet['M' - 'A'] = 1000;
    }

    // 나올 수 있는 모든 경우의 수를 미리 세팅
    static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    static final String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    static int madeSymbol2Num (String input) {
        int num = 0;
        int prev = alphabet[input.charAt(0) - 'A'];
        for (int i = 1; i < input.length(); i++) {
            int curr = alphabet[input.charAt(i) - 'A'];

            // 이전 숫자보다 현재가 더 크면? -> 뺄셈 (그리고 prev는 0으로 세팅)
            if(prev != 0 && prev < curr) {
                num += (curr - prev);
                prev = 0;
            } else {
                num += prev;
                prev = curr;
            }
        }

        // 마지막 한자리는 따로 더하기
        num += prev;

        return num;
    }

    static String madeNum2Symbol (int input) {
        StringBuilder symbol = new StringBuilder();

        while (0 < input) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] <= input) {
                    symbol.append(romans[i]);
                    input -= values[i];
                    break;
                }
            }
        }

        return symbol.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first = br.readLine();
        String second = br.readLine();

        int firstNum = madeSymbol2Num(first);
        int secondNum = madeSymbol2Num(second);
        int sum = firstNum + secondNum;
        String answer = madeNum2Symbol(sum);

        System.out.println(sum + "\n" + answer);
    }
}
