/*
[백준]
11047, 동전 0

[문제파악]
- 동전이 N 종류 (각각의 동전을 매우 많이 가지고 있다)
- 동전을 적절히 사용해서 그 가치의 합을 K로 만들려고 한다
- 이 때 필요한 동전의 갯수를 최솟값으로 구할 것

[입력]
- 첫째 줄에 N과 K가 주어짐 (1<=N<10, 1<=K<=100,000,000)
- 둘째 줄에 N개의 줄에 동전의 가치 A_i가 오름차순으로 주어짐
	- 1<=A_i<=1,000,000
	- A_i = 1, i <= 2인 경우에 A_i는 A_i-1의 배수

[출력]
- 필요한 동전의 최솟값을 출력

[구현방법]
- 가장 큰 동전부터 사용해서 깎아가면 됨

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
        StringTokenizer st= new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        int totalCoin = 0;

        // 동전 종류 배열
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        // 가장 큰 동전부터 사용
        for (int i = N-1; 0 <= i; i--) {
            int currentCoin = coins[i];

            int count = K / currentCoin;
            totalCoin += count;

            K -= currentCoin * count;
        }

        System.out.println(totalCoin);
    }
}