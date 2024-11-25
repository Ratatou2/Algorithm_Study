/*
[백준]
10872, 팩토리얼

[문제파악]
- 0보다 크거나 같은 정수 N이 주어진다.
- 이때, N!을 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 정수 N(0 ≤ N ≤ 12)이 주어진다.

[출력]
- 첫째 줄에 N!을 출력한다.

[구현방법]
- 어떤 자료구조를 쓴다기보단 일단 그냥

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result = 1;

        int N = Integer.parseInt(br.readLine());

        if (N != 0) {
            for (int i = N; 0 < i; i--) {
                result *= i;
            }
        }

        System.out.println(result);
    }
}