/*
[백준]
1541, 잃어버린 괄호

[문제파악]
- 식에는 괄호가 없다
- 괄호를 적절히 쳐서 이 식의 값을 최소로 만들어라

[입력]
- 첫째줄에 식이 주어짐
	- 식은 0~9, +, - 로만 이루어져 있다
	- 가장 처음과 마지막 문자는 숫자이다
	- 연속해서 두 개 이상의 연산자가 나타나지 않은다
	- 5자리보다 많이 연속되는 숫자는 없다 (N <= 99999)
	- 수는 0으로 시작할 수 있다
	- 식의 길이는 50보다 작거나 같다

[출력]
- 정답 출력

[구현방법]
- 음.. 예전에도 아이디어가 안 떠올랐던 문제
- 사실 곱셈, 나눗셈이 나오면 그것 기준으로 오른쪽, 왼쪽의 수식을 먼저 묶냐 마냐로 결정하면 될 문제긴하다 (근데 여기선 +, - 뿐임)
- 근데 어디서 어디까지 얼마나 묶고 이걸 어떻게 구현해야할지는 감이 안 잡히네..
- 나눈 것 기준으로 왼쪽 탐색, 오른쪽 탐색 나누고 괄호 범위를 얼마나 묶을지를 계산하는 식을 만드는게 현재 도출된 아이디어
- 비효율적인데 이런 방식이 맞나?

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // + 수식을 전부 계산해주는 기능
    public static int calculate(String input) {
        int answer = 0;
        String[] temp = input.split("\\+");  // + 기호 기준을 쪼갠다

        // String을 쪼갰는데, 그 길이가 1 이상이면 +로 붙은 숫자들이 여러 개라는 의미 -> 다 더한다
        if (1 < temp.length) {
            for (String s : temp) {
                answer += Integer.parseInt(s);
            }
        } else {
            answer = Integer.parseInt(temp[0]);
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("\\-");

        int answer = calculate(input[0]);
        for (int i = 1; i < input.length; i++) {
            answer -= calculate(input[i]);
        }

        System.out.println(answer);
    }
}