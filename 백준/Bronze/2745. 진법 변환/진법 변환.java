/*
[백준]
2745, 진법변환

[문제파악]
- B진법 수 N이 주어진다. 이 수를 10진법으로 바꿔 출력하는 프로그램을 작성하시오.
- 10진법을 넘어가는 진법은 숫자로 표시할 수 없는 자리가 있다. 이런 경우에는 다음과 같이 알파벳 대문자를 사용한다.
- A: 10, B: 11, ..., F: 15, ..., Y: 34, Z: 35

[입력]
- 첫째 줄에 N과 B가 주어진다. (2 ≤ B ≤ 36)
- B진법 수 N을 10진법으로 바꾸면, 항상 10억보다 작거나 같다.

[출력]
- 첫째 줄에 B진법 수 N을 10진법으로 출력한다.

[구현방법]
- 문제에 B는 36진법까지로 고정되어 있다
    - 이는 10진법에 알파벳인 26자를 진법으로 표기해서 최대 표기 가능한 36진법까지 표기위한 방식으로 보인다
- 다만 처음엔 B진법이래놓고 11진법의 대체제인 B가 표기되어 있어 이게 뭔소리인가 싶었음;; 
    - 이런 변수는 구분하기 위해 다른걸 써주는게 푸는 사람입장에선 이해하기 쉬운데 말이다
- 그래서 이해하고 나니 그냥 뭐.. 0 자리수 부터 진법으로 제곱해가며 더하면서 구하면 되는 수학문제였다

[보완점]


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());

        int result = 0;
        int power = 1;         // 가중치 (B의 거듭 제곱)

        // 문자열 N의 각 자리를 거꾸로 탐색
        for (int i = N.length() - 1; 0 <= i; i--) {
            char c = N.charAt(i);

            // 숫자와 알파벳을 구분하여 10진수로 변환
            int numValue;
            if (Character.isDigit(c)) numValue = c - '0';  // 숫자일 경우
            else numValue = c - 'A' + 10;  // 알파벳일 경우

            // 10진법으로 환산하는 수식
            result += numValue * power;
            power *= B;  // 진법 변환이니까 제곱수, B만큼 증가
        }

        System.out.println(result);
    }
}