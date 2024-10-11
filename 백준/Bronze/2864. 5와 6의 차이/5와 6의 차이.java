/*
[백준]
2864, 5와 6의 차이

[문제파악]
- 상근이는 2863번에서 표를 너무 열심히 돌린 나머지 5와 6을 헷갈리기 시작했다.
- 상근이가 숫자 5를 볼 때, 5로 볼 때도 있지만, 6으로 잘못 볼 수도 있고, 6을 볼 때는, 6으로 볼 때도 있지만, 5로 잘못 볼 수도 있다.
- 두 수 A와 B가 주어졌을 때, 상근이는 이 두 수를 더하려고 한다.
- 이때, 상근이가 구할 수 있는 두 수의 가능한 합 중, 최솟값과 최댓값을 구해 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 두 정수 A와 B가 주어진다. (1 <= A,B <= 1,000,000)

[출력]
- 첫째 줄에 상근이가 구할 수 있는 두 수의 합 중 최솟값과 최댓값을 출력한다.

[구현방법]
- 한글자씩 비교해서 5이면 6으로 만들고, 6이면 5로 만들어서 min, max 따로 계산하면 된다.
- 근데 구현하고 보니 뭔가 복잡시러운데...

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int toInt(String num1, String num2) {
        return Integer.parseInt(num1) + Integer.parseInt(num2);
    }

    static String[] makeMaxMin(String num) {
        String[] answer = new String[2];
        StringBuilder maxNum = new StringBuilder();
        StringBuilder minNum = new StringBuilder();

        for (char c : num.toCharArray()) {
            if (c == '5') {
                maxNum.append("6");
                minNum.append(c);
            }
            else if (c == '6') {
                maxNum.append(c);
                minNum.append("5");
            }
            else {
                maxNum.append(c);
                minNum.append(c);
            }
        }

        answer[0] = minNum.toString();
        answer[1] = maxNum.toString();

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String[] first = makeMaxMin(st.nextToken());
        String[] second = makeMaxMin(st.nextToken());

        System.out.println(toInt(first[0], second[0]) + " " + toInt(first[1], second[1]));
    }
}