/*
[백준]
1373, 2진수 8진수

[문제파악]
- 2진수가 주어졌을 때, 8진수로 변환하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 2진수가 주어진다.
- 주어지는 수의 길이는 1,000,000을 넘지 않는다.

[출력]
- 첫째 줄에 주어진 수를 8진수로 변환하여 출력한다.

[구현방법]
- 자릿수가 3배수면 그냥 앞에서부터 3자리씩 끊어서 하면 됨
- 근데 3으로 나눠서 나머지에 따라 결과가 다름
    - 나머지가 2이면 맨 앞에 0을 한개만 붙이면 되고
    - 나머지가 1이면 맨 앞에 0을 두개 붙이면 된다
    - 이걸 하는 이유는 2진수를 8진수로 바꾸려면 3자리가 필요해서임
- 으휴 바보같이 '1'이랑 숫자 1이랑 비교하고 있으니 안된다카지 짜식아
- 비트연산자 잘 써먹긴했는데 더 효율적인 방법이 있을까?

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int twoToEight(String input) {
        int result = 0;

        for (int i = 0; i < 3; i++) {
            if (input.charAt(i) == '1') {
                result += (1 << (2 - i));
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();

        int digits = input.length() % 3;
        if (digits == 1) input = "00" + input;
        else if (digits == 2) input = "0" + input;

        for (int i = 0; i < input.length(); i += 3) {
            sb.append(twoToEight(input.substring(i, i+3)));
        }

        System.out.println(sb);
    }
}