/*
[백준]
31403, A + B - C

[문제파악]
- JavaScript에서 +는 수에 대해서는 일반적인 의미의 덧셈 뺄셈의 의미를 가지고 있습니다.
- 하지만 문자열에 대해서 +는 두 문자열을 이어붙이라는 의미이고, -는 양쪽 문자를 수로 해석한 이후에 빼라는 의미입니다.
- A, B, C를 각각 수와 문자열로 생각했을 때 A + B - C를 출력하세요.

[입력]
- 첫 줄에는 정수 A가 주어집니다. (1 ≤ A ≤ 1000)
- 둘째 줄에는 정수 B가 주어집니다. (1 ≤ B ≤ 1000)
- 셋째 줄에는 정수 C가 주어집니다. (1 ≤ C ≤ 1000)

주어지는 모든 수는 0으로 시작하지 않는 양의 정수입니다.

[출력]
- 첫 줄에는 A, B, C를 수로 생각했을 때, A + B - C를 출력하세요.
- 둘째 줄에는 A, B, C를 문자열로 생각했을 때 A + B - C를 출력하세요.

[구현방법]
- 이거 그냥 문자열, 숫자 구분하면 된다
- 아기 상어를 풀었어야했는데...ㅂㄷㅂㄷ

[보완점]


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int calculateInt(String A, String B, String C) {
        return Integer.parseInt(A) + Integer.parseInt(B) - Integer.parseInt(C);
    }

    static int calculateString(String A, String B, String C) {
        return Integer.parseInt(A + B) - Integer.parseInt(C);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String A = br.readLine();
        String B = br.readLine();
        String C = br.readLine();

        sb.append(calculateInt(A, B, C)).append("\n").append(calculateString(A, B, C));

        System.out.println(sb);
    }
}