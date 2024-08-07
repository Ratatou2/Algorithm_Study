/*
[백준]
1212, 8진수 2진수

[문제파악]
- 8진수가 주어졌을 때, 2진수로 변환하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 8진수가 주어진다.
- 주어지는 수의 길이는 333,334을 넘지 않는다.

[출력]
- 첫째 줄에 주어진 수를 2진수로 변환하여 출력한다. 수가 0인 경우를 제외하고는 반드시 1로 시작해야 한다.

[구현방법]
- 8진수를 2진수로 바꾸고, 2진수는 Integer.toBinaryString 쓰면 될 것 같다

[보완점]
- charAt을 쓸거면 문자열 취급이니까 '0'을 빼줘야 한다
- 더군다나 수의 '길이'가 333,333일 수 있기 때문에 int로 못한다
- 이 말인 즉, 8진수를 자릿수 별로 잘라서 2진수 변환해야 함
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        long length = input.length();

        for (int i = 0; i < length; i++) {
            String temp = Integer.toBinaryString(input.charAt(i) - '0');
            int tempLength = temp.length();
            
            // i != 0가 있는 이유는 맨 앞부터 진행하기 때문임
            if (tempLength == 2 && i != 0) temp = "0" + temp;
            else if (tempLength == 1 && i != 0) temp = "00" + temp;

            sb.append(temp);
        }

        System.out.println(sb);
    }
}