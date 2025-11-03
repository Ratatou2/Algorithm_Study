

/*
[백준]
11688, 최소공배수 찾기

[문제파악]
세 정수 a, b, L이 주어졌을 때, LCM(a, b, c) = L을 만족하는 가장 작은 c를 찾는 프로그램을 작성하시오. LCM(a, b, c)는 a, b, c의 최소공배수이다.

[입력]
첫째 줄에 a, b, L이 주어진다. (1 ≤ a, b ≤ 106, 1 ≤ L ≤ 1012)

[출력]
첫째 줄에 c를 출력한다. 만약, 가능한 c가 없으면 -1을 출력한다.

[구현방법]
- 최근에 이런 문제를 풀게 됐는데 놀랍게도 내가 이것을 코드로 옮길줄 모른다는 것을 깨달았음...
- 최소공배수를 노트에 적어서 구하라고 하면 구하겠지만.. 소수로 하나씩 나눠가며 구하는 방식에 확신을 가지지 못했음

- 소수를 미리 구해두고, 작은 소수부터 나누는 방식으로 맞추긴 했지만 너무 야매느낌이라 비슷한 문제를 찾았음
- 문제 풀이 방식의 핵심은 '소인수분해 기반의 최소공배수(LCM)'를 구하는 것
- 아 문제 정리하면서 깨달은건 저번에 풀었던 것은 최소'공약수'를 구하는 문제였네...

- 아무튼 개념을 좀 정리해보자면
    - LCM : 최소공배수 (Least Common Multiple)
    - GCD : 최대공약수 (Greatest Common Divisor)
    - 우선 a, b 두 수가 있을 때 최대공약수는 [a * b / (a, b의 최소공배수)]이다
    - 당연히 수학적으로 둘을 곱한 상태에서 최소 공약수를 나누면 불필요한 요소들을 전부 날리고 최소한의 공배수가 되는 것이다

    - 그리고 수학적으로 a * b = GCD(a, b) * LCM(a, b)가 성립한다
    - e.g) 12 * 18 = 216 = 6 * 36

    - 즉, 문제의 조건대로 수식을 수정하면?
    - LCM(a, b) = (a * b) / GCD(a, b)

- 추가로 알아야할 개념 '유클리드 호제법 (Euclidean Algorithm)'
    - 두 수의 최대 공약수(GCD)를 아주 빠르게 구하는 고전적인 알고리즘이다
    - a % b를 통해서 반복하면 나머지가 0이 된 순간, 직전의 나머지가 최대공약수인 셈이다
        - 18 % 12 = 6
        - 12 % 6  = 0 -> 직전의 나머지인 6이 최대공약수이다

        - 이것을 코드로 나타내면 재귀로 나타낼 수 있고 아래와 같음

        # 재귀문
        static long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        # 반복문
        static long gcd(long a, long b) {
            while (b != 0) {
                long temp = a % b;
                a = b;
                b = temp;
            }
            return a;
        }

    - 즉, 유클리드 호제법을 알고 있었어야 빠르게 풀 수 있었던 것
    - 난 늘 이런 수학적 공식은 암기하는게... 더 효율적이었다 (수식 보고 전부 이해됐음 수학과 갔겠지!! ㅠ)
    - 그래도 대충 결은 이해가 된다
    - 이것의 핵심은 '공약수는 두 수의 차에도 그대로 존재한다'라는 명제가 참이기 때문에 성립한다
    - 예를들어, 48, 18이 있다면, 최대공약수는 6이다
    - 이때, 두 수의 차인 30도 최대공약수 6은 30에서도 유효하다
    - 30 - 18 = 12도 마찬가지
    - 이때, 매번 -(빼기)를 반복하면 시간이 오래걸리니 몫을 구하고 한번에 나머지를 구해서 반복 시간을 줄이는 것으로 접근한 것이다

- 그럼 여기서 개념을 확장해서 문제 조건인 LCM(a, b, c) = L을 구하는 것이다
- 주어지는 숫자는 a, b, L 세개 뿐
- 그러면 LCM(a, b)을 구해서 L과 비교한다
    - 이때 L < LCM(a, b)면 만들 수 없다는 것이니까 -1 출력하면 됨
- 결국 LCM(a, b)에서 L이 되기까지 부족한 숫자들을 채워서 곱한다면 그것이 C의 값이 된다
- 그럼 결국 L을 소인수 분해하고 LCM(a, b)에서 중첩되는 것들을 제외하면 C를 구할 수 있음

[보완점]
< (*) 부분 해석 >
- 내가 좀 충격(?!) 받았던 부분이다
- 기본적으로 아까 내용으로 LCM은 (a * b) / GCD(a, b)로 구하는 것이 정석이다
- 근데? 컴퓨터 알고리즘 상에서 a, b 둘다 10^9라면 10^18이 되어 터질 가능성이 높다 (long의 범위는 약 +-9.22 × 10^18)
- 그러니까 순서를 바꿔서 a를 미리 나눠준 상태로 b랑 곱해주는 것이다...
- 진짜 똑똑쓰;;
*/

import java.io.*;
import java.util.*;

public class Main {

    // 최대공약수
    static long GCD(long a, long b) {
        return b == 0 ? a : GCD(b, a % b);
    }

    // 최소공배수 (*) - 정석은 (a * b) / GCD(a, b)
    static long LCM(long a, long b) {
        return a / GCD(a, b) * b;
    }

    // 소인수분해해서 Map 형태로 return
    static Map<Long, Integer> primeFactorization (long n) {
        // 소인수분해한 소수들을 저장해둘 Map 선언
        Map<Long, Integer> primes = new HashMap<>();

        for (long i = 2; i * i <= n; i++) {
            // 딱 나누어 떨어진다면 기록해둔다
            while (n % i == 0) {
                primes.put(i, primes.getOrDefault(i, 0) + 1);
                n /= i;
            }
        }

        // 여기까지 도달했는데 n이 0이 아니면 소수란 소리니까 map에 추가해준다
        if (1 < n) primes.put(n, primes.getOrDefault(n, 0) + 1);

        return primes;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long L = Long.parseLong(st.nextToken());

        long LCM_ab = LCM(a, b);

        // 애초부터 정답이 나올 수 없는 구조일 때 (L이 LCM(a, b)의 공배수 중 하나가 아닐 때)
        if ((L % LCM_ab) != 0)  {
            System.out.println(-1);
            return;
        }

        // 최소공배수인 LCM(a, b)과 L을 소인수분해해서 각자의 숫자를 구성하고 있는 숫자를 파악함
        Map<Long, Integer> factorial_ab = primeFactorization(LCM_ab);
        Map<Long, Integer> factorial_L = primeFactorization(L);

        long c = 1;

        // L의 prime 갯수와 LCM(a, b)의 Prime 갯수를 비교해서 그 차이만큼 C에 곱해야 한다
        for (long prime : factorial_L.keySet()) {
            int count_L = factorial_L.get(prime);
            int count_ab = factorial_ab.getOrDefault(prime, 0);  // LCM(a, b)에는 없는 소수일 수 있으니 getOrDefault 사용

            // L에만 존재하는 소수라면? 그 갯수차이만큼 C에 곱해줘야 함 -> 처음엔 갯수 차이만큼 가지고 있어야하는 줄 알았는데 그게 아님 (LCM(a, b, c) 해서 L을 구하는 것이니까 c는 L만큼 가지고 있어야 함)
            if (count_ab < count_L) {
                for (int i = 0; i < count_L; i++) {
                    c *= prime;
                }
            }
        }

        System.out.println(c);
    }
}
