/*
[백준]
28702, FizzBuzz

[문제파악]
i가
3의 배수이면서
5의 배수이면 “FizzBuzz”를 출력합니다.

i가
3의 배수이지만
5의 배수가 아니면 “Fizz”를 출력합니다.

i가
3의 배수가 아니지만
5의 배수이면 “Buzz”를 출력합니다.

i가
3의 배수도 아니고
5의 배수도 아닌 경우
i를 그대로 출력합니다.

FizzBuzz 문제에서 연속으로 출력된 세 개의 문자열이 주어집니다.
이때, 이 세 문자열 다음에 올 문자열은 무엇일까요?


[입력]
- FizzBuzz 문제에서 연속으로 출력된 세 개의 문자열이 한 줄에 하나씩 주어집니다.
- 각 문자열의 길이는 8 이하입니다.
- 입력이 항상 FizzBuzz 문제에서 연속으로 출력된 세 개의 문자열에 대응됨이 보장됩니다.

[출력]
- 연속으로 출력된 세 개의 문자열 다음에 올 문자열을 출력하세요.
- 여러 문자열이 올 수 있는 경우, 아무거나 하나 출력하세요.

[구현방법]
- 일단 숫자 하나는 무조건 알게 될테니, 해당 지점부터 + i를 한다 (i는 3번의 반복문 - index)
- 그러면 다음에 올 숫자를 구할 수 있고, 그 숫자로 FizzBuzz 조건 찾으면 될듯

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String FizzBuzz(int num) {
        String result = String.valueOf(num);

        if (num % 3 == 0) {
            if (num % 5 == 0) result = "FizzBuzz";
            else result = "Fizz";
        } else if (num % 5 == 0) result = "Buzz";

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        for (int i = 0; i < 3; i++) {
            input = br.readLine();

            // 숫자인지 체크
            if (Character.isDigit(input.charAt(0))) {
                System.out.println(FizzBuzz(Integer.parseInt(input) + (3 - i)));
                break;
            }

        }
    }
}