

/*
[백준]
1644, 소수의 연속합

[문제파악]
하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다.
몇 가지 자연수의 예를 들어 보면 다음과 같다.
    3 : 3 (한 가지)
    41 : 2+3+5+7+11+13 = 11+13+17 = 41 (세 가지)
    53 : 5+7+11+13+17 = 53 (두 가지)
하지만 연속된 소수의 합으로 나타낼 수 없는 자연수들도 있는데, 20이 그 예이다.
7+13을 계산하면 20이 되기는 하나 7과 13이 연속이 아니기에 적합한 표현이 아니다.
또한 한 소수는 반드시 한 번만 덧셈에 사용될 수 있기 때문에, 3+5+5+7과 같은 표현도 적합하지 않다.
자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 4,000,000)

[출력]
첫째 줄에 자연수 N을 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 출력한다.

[구현방법]
- 일단 이 문제의 풀이식을 고민해보자면 아래와 같다
    1) 일단 '에라토스테네스의 체'를 구현해서 현재의 숫자 N까지 구한다
    2) 연속되는 숫자를 더해서 N에 근접할 수 있는지를 구한다
    3) 이때 사용해야하는 방식은 아마 투포인터로 예상된다
        - 사유는 몇개를 더한 시점에 N보다 크면 '왼쪽 포인터(start)'를 증가, N보다 작으면 '오른쪽 포인터(end)'를 증가시켜 구간을 확장


[보완점]
(*) Q. 근데 왜 end를 줄이지 않고 start를 하나 빼고 index를 오른쪽이로 이동시켜서 값을 줄이걸까?
    A. end는 다음 경우의 수를 계속 찾아봐야하기 때문
        - 기존에 계속 end를 증가시켜야 연속되는 숫자들의 합으로 N을 구할 수 있다
        - 그리고 end를 갑자기 또 줄여버리면 중복 계산이 일어날 수 있음

(**) Q. 그렇다면 왜 제곱수인가?
     A. 이미 i와 j의 곱 형태는 i가 더 작을 때 jxi 형태로 싹다 지워졌기 때문이다.
        - 즉, i * i이전의 배수들은 이미 더 작은 수들에 의해 제거됐다
        - 2와 4를 생각해보면 이해하기 쉽다
        - 8의 경우 2의 제곱수라서 이미 지워졌다
        - 그 와중에 4의 차례가 와서 배수들을 지운다한들 8, 12, ... 등등등 이미 여럿 중복되는 수들이 지워졌을 것이다.

(***) Q. 하더라도 j의 배수를 구해서 지우는게 나은 것 아닌가..?
      A. j = i부터 시작해서 배수로 다 지우면 중복이 너무 많아진다
        - 예를들어 j = 2부터 시작하면 i = 6일 때 12, 18, 36 등 이미 이전 루프에서 지워진 수들을 또 지워야만한다 (비효율 : 시간낭비 + 중복작업)
        - 하나 더 예시를 들어주자면, i = 5일 때 생각해보면 됨
            - j = 2부터 시작하면: 5×2 = 10, 5×3 = 15, 5×4 = 20, 5×5 = 25, ...
            - 근데 10, 15, 20은 이미 i=2,3,4에서 다 지워진 상태!!
*/

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] isNotPrime;

    static void updatePrimeArray() {
        isNotPrime[0] = true;
        isNotPrime[1] = true;

        // i * i <= N까지 도는 이유) 이후는 배수가 N을 넘음 (**)
        for (int i = 2; i * i <= N; i++) {
            // 소수가 아니면 (이미 체크 됐으면) 패스
            if (isNotPrime[i]) continue;

            // 소수로 판명된 숫자의 제곱수부터 시작해서 N 도달까지의 배수를 모두 '소수가 아님'을 체크한다
            // j = i * i부터 지우는 이유) 이보다 작은 배수는 다른 수에 의해 이미 지워졌기 때문이다 (***)
            for (int j = i * i; j <= N; j += i) {
                isNotPrime[j] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        isNotPrime = new boolean[N + 1];  // false일 때 소수임

        // 소수 구하고 기록해두기
        updatePrimeArray();
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            // 소수면 리스트에 추가하기
            if (!isNotPrime[i]) primes.add(i);
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int count = 0;

        // 투포인터
        while (true) {
            if (N <= sum) {
                if (N == sum) count++;
                sum -= primes.get(start++);  // 지금 sum이 N보다 크거나 같으니까, 기존의 start를 빼고 start의 index를 하나 늘려 이동한다 (*)
            } else {
                if (end == primes.size()) break;  // 더 이상 확장할 범위가 없는데, 여전히 N보다 작으면 더 볼 것 없다
                sum += primes.get(end++);  // sum이 N보다 작으면, 더 큰 값을 만들기 위해 end 지점의 소수를 더하고 end를 한 칸 옮긴다.
            }
        }

        System.out.println(count);
    }
}
