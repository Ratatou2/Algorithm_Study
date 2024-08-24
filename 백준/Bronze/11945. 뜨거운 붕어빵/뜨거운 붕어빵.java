/*
[백준]
11945, 뜨거운 붕어빵

[문제파악]
- 붕어빵이 떨어지고, 뒤집혔응게 뒤집어서 출력해라

[입력]
- 첫째 줄에는 두 개의 정수 N과 M(0≤N,M≤10)이 주어집니다.
- 둘째 줄부터 N개의 줄에 걸쳐 붕어빵의 모양이 주어집니다.
- 각 행에는 공백을 나타내는 ‘0‘ 또는 붕어빵을 나타내는 ‘1’이 총 M개 주어집니다.

[출력]
- 입력으로 주어진 붕어빵이 좌우로 뒤집힌 모양을 출력하세요.

[구현방법]
- 그냥 리스트로 입력받고 reverse() 써서 돌리는게 제일 간편할듯!
- 근데 할수록 번잡해지고 비효율적인데요...??

[보완점]
- StringBuilder에 reverse 기능이 있다는걸 아시는가...???
- 아니 진짜 문자열 뒤집기가 이렇게 쉬우면 여즉 내가 했던 그런 노가다들은 무엇이냐고!!!
- 진짜 혁신이다잉....

5 7
0010000
0111010
1111111
0111010
0010000
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 첫 줄에서 N과 M을 입력받음
        String[] nm = br.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);

        // N개의 붕어빵 모양을 입력받고, 각각을 뒤집어서 출력
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            String reversedLine = new StringBuilder(line).reverse().toString();
            sb.append(reversedLine).append("\n");
        }

        System.out.println(sb);
    }
}