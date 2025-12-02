

/*
[백준]
9020, 골드바흐의 추측

[문제파악]
1보다 큰 자연수 중에서  1과 자기 자신을 제외한 약수가 없는 자연수를 소수라고 한다.
예를 들어, 5는 1과 5를 제외한 약수가 없기 때문에 소수이다.
하지만, 6은 6 = 2 × 3 이기 때문에 소수가 아니다.
골드바흐의 추측은 유명한 정수론의 미해결 문제로, 2보다 큰 모든 짝수는 두 소수의 합으로 나타낼 수 있다는 것이다.
이러한 수를 골드바흐 수라고 한다. 또, 짝수를 두 소수의 합으로 나타내는 표현을 그 수의 골드바흐 파티션이라고 한다.
예를 들면, 4 = 2 + 2, 6 = 3 + 3, 8 = 3 + 5, 10 = 5 + 5, 12 = 5 + 7, 14 = 3 + 11, 14 = 7 + 7이다.
10000보다 작거나 같은 모든 짝수 n에 대한 골드바흐 파티션은 존재한다.
2보다 큰 짝수 n이 주어졌을 때, n의 골드바흐 파티션을 출력하는 프로그램을 작성하시오.
만약 가능한 n의 골드바흐 파티션이 여러 가지인 경우에는 두 소수의 차이가 가장 작은 것을 출력한다.

[입력]
첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고 짝수 n이 주어진다.

[출력]
각 테스트 케이스에 대해서 주어진 n의 골드바흐 파티션을 출력한다. 출력하는 소수는 작은 것부터 먼저 출력하며, 공백으로 구분한다.

[구현방법]
- 에라토스테네스의 체를 구현한다
- 주어진 값보단 작은 소수부터 시작해서 빼고 남은 숫자가 소수로 존재하는지 찾아본다
- 그걸 모든 소수를 체크할 때까지 (최소한 주어진값/2 때까지 반복한다 - 이때까지 반복했는데 없으면 '소수 두개의 합'으로는 해결되지 않기 때문)
- 아 문제 조건에 두 소수의 차이가 가장 작은걸 구한다니까 한쪽은 계속 더하고 한쪽은 계속 빼서 구해도 되겠다

[보완점]

*/

import java.io.*;
import java.util.*;

public class Main {
    static boolean[] isMultiple;

    // 에라토스테네스의 체
    static void SieveOfEratosthenes () {
        for (int i = 2; i < 10000 + 1; i++) {
            // 배수면 패스
            if (isMultiple[i]) continue;

            // 배수가 아니면 현재 숫자의 배수들을 전부 미리 마킹
            for (int x = 2; x < (10000 + 1)/i; x++) {
                isMultiple[i * x] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        isMultiple = new boolean[10000 + 1];
        SieveOfEratosthenes();

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            int N = Integer.parseInt(br.readLine());
            int left = N / 2;
            int right = N / 2;

            while (1 < left) {
                // 둘이 더해서 N을 만들 수 있는데 둘다 소수라면 답을 찾은 것이다
                if (!isMultiple[left] && !isMultiple[right]) {
                    sb.append(left + " " + right + "\n");
                    break;
                }

                // 둘이 더해서 N이 되어야하니까 투포인터처럼 운용 (이 아이디어 만든 사람 참 똑똑한거 같음..)
                left--;
                right++;
            }
        }

        System.out.println(sb);
    }
}
