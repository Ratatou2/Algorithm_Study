/*
[백준]
2440, 별 찍기 - 3

[문제파악]
첫째 줄에는 별 N개, 둘째 줄에는 별 N-1개, ..., N번째 줄에는 별 1개를 찍는 문제

[입력]
첫째 줄에 N(1 ≤ N ≤ 100)이 주어진다.

[출력]
첫째 줄부터 N번째 줄까지 차례대로 별을 출력한다.

[구현방법]
- 아아 이 웬수같은 문제
- 몇 년 전만해도 날 깔보며 그 앙증맞은 * 을 흔들어 댔겠지만 오늘의 나는 다르다
- 오늘의 나는 네가 한 트럭 몰려온다해도 아무 소용없다는 걸 보여주지
- 이제... 너 따위는 1분 컷이라고

[보완점]


입력

출력
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // 첫번째 for문은 N개의 줄을 출력한다
        for (int row = 0; row < N; row++) {
            // 두번째 for문은 한줄의 별 갯수를 출력한다
            for (int star = N; row < star; star--) {
                sb.append("*");
            }
            
            sb.append("\n");
        }

        System.out.println(sb);
    }
}