/*
[백준]
1978, 소수 찾기

[문제파악]
- 주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력하시오.

[입력]
- 첫 줄에 수의 개수 N이 주어진다. N은 100이하이다.
- 다음으로 N개의 수가 주어지는데 수는 1,000 이하의 자연수이다.

[출력]
- 주어진 수들 중 소수의 개수를 출력한다.

[구현방법]
- 에라토스테네스의 체인가 그거 쓰면 될듯
- 어차피 Boolean 쓸거면 BitSet 쓰면 더 빠를지도?
- 역시 수학문제는 싫어...

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Main {
    public static BitSet sieveOfEratosthenes(int limit) {
        // 모든 숫자를 소수로 가정합니다 (True로 초기화)
        BitSet isPrime = new BitSet(limit + 1);
        isPrime.set(2, limit + 1);  // 2부터 끝까지 모든 값을 소수라고 가정한다

        // 2부터 limit의 제곱근까지 반복합니다
        // i * i = limit이면 i는 제곱근이란 소리다
        for (int i = 2; i * i <= limit; i++) {
            // 현재 i가 소수가 아니면 패스
            if (!isPrime.get(i)) continue;

            // 소수인 경우, i의 모든 배수를 모두 소수가 아니라고 표기한다 (for 문 건너뜀)
            for (int multiple = i * i; multiple <= limit; multiple += i) {
                isPrime.clear(multiple);
            }
        }

        return isPrime;
    }

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        BitSet sieveOfEratosthenes = sieveOfEratosthenes(1000 + 1);

        int count = 0;
        for (int i = 0; i < N; i++) {
            int checkNum = Integer.parseInt(st.nextToken());

            if (sieveOfEratosthenes.get(checkNum)) count++;
        }

        System.out.println(count);
    }
}