/*
[백준]


[문제파악]
- 과거 이집트인들은 각 변들의 길이가 3, 4, 5인 삼각형이 직각 삼각형인것을 알아냈다.
- 주어진 세변의 길이로 삼각형이 직각인지 아닌지 구분하시오.

[입력]
- 입력은 여러개의 테스트케이스로 주어지며 마지막줄에는 0 0 0이 입력된다.
- 각 테스트케이스는 모두 30,000보다 작은 양의 정수로 주어지며, 각 입력은 변의 길이를 의미한다.


[출력]
- 각 입력에 대해 직각 삼각형이 맞다면 "right", 아니라면 "wrong"을 출력한다.

[구현방법]
- Class2 따야지!
- 이거 그냥 피타고라스의 정리다

[보완점]
- C가 항상 대각선으로 주어지진 않는다... 정렬해버리는게 제일 쉽겠다
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean isRightTriangle (int A, int B, int C) {
        return C * C == (A * A) + (B * B);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            int[] temp = new int[3];
            String result;

            for (int i = 0; i < 3; i++) {
                temp[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(temp);

            int A = temp[0];
            int B = temp[1];
            int C = temp[2];

            if (A == 0 && B == 0 && C == 0) break;
            result = isRightTriangle(A, B, C) ? "right" : "wrong";
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}