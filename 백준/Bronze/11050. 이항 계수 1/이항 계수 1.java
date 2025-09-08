

/*
[백준]
11050, 이항계수 1

[문제파악]
- 이항계수 계산하기

[구현방법]
- 이항계수의 정의 : n개의 서로 다른 원소 중에서 r개를 순서 없이 선택하는 경우의 수
    - e.g. 5개 중에 2개 선택 5C2
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int answer = 1;

        for (int i = 0; i < K; i++) {
            answer *= N - i;
        }

        for (int i = K; 1 < i; i--) {
            answer /= i;
        }

        System.out.println(answer);
    }
}
