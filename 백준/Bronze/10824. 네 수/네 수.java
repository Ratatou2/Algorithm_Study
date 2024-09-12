/*
[백준]
10824, 네 수

[문제파악]
- 네 자연수 A, B, C, D가 주어진다.
- 이때, A와 B를 붙인 수와 C와 D를 붙인 수의 합을 구하는 프로그램을 작성하시오.
- 두 수 A와 B를 합치는 것은 A의 뒤에 B를 붙이는 것을 의미한다.
- 즉, 20과 30을 붙이면 2030이 된다.

[입력]
- 첫째 줄에 네 자연수 A, B, C, D가 주어진다. (1 ≤ A, B, C, D ≤ 1,000,000)

[출력]
- A와 B를 붙인 수와 C와 D를 붙인 수의 합을 출력한다.

[구현방법]
- 이거는 그냥 처음엔 String 상태로 더해서 이어 붙인다음 숫자로 변환해서 더해주면 된다

[보완점]
- 자릿수 때문에 int는 터질지도?
- StringTokenizer 쓰고, 변환 과정 수정하면 좀 더 빨라질까...?
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        System.out.println(Long.parseLong(st.nextToken() + st.nextToken()) + Long.parseLong(st.nextToken() + st.nextToken()));
    }
}