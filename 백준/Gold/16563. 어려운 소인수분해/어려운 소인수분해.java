
/*
[백준]
16563, 어려운 소인수분해

[문제파악]
지원이는 대회에 출제할 문제에 대해서 고민하다가 소인수분해 문제를 출제해야겠다고 마음을 먹었다.
그러나 그 이야기를 들은 동생의 반응은 지원이의 기분을 상하게 했다.

"소인수분해? 그거 너무 쉬운 거 아니야?"

지원이는 소인수분해의 어려움을 알려주고자 엄청난 자신감을 가진 동생에게 2와 500만 사이의 자연수 N개를 주고 소인수분해를 시켰다.
그러자 지원이의 동생은 기겁하며 쓰러졌다.
힘들어하는 지원이의 동생을 대신해서 여러분이 이것도 쉽다는 것을 보여주자!

[입력]
첫째 줄에는 자연수의 개수 N (1 ≤ N ≤ 1,000,000)이 주어진다.
둘째 줄에는 자연수 ki (2 ≤ ki ≤ 5,000,000, 1 ≤ i ≤ N)가 N개 주어진다.

[출력]
N줄에 걸쳐서 자연수 ki의 소인수들을 오름차순으로 출력하라.

[구현방법]
- DP처럼 구해야하나? 최대 500만 이니까 하나씩 나눠서 구하면 무조건 터질 것이다 (자연수가 최대 1,000,000개까지 들어오기 때문)
- Map<Integer, List<Integer>>로 숫자에 대한 소인수 분해로 진행하면 될 것 같은데
- 몇제곱인지는 필요 없으니까 그냥 숫자 유무만 넣는 것이다

- 음 저 아이디어는 틀렸음
- 이 문제는 에라토스테네스의 변형이라고 함;; ㄷ

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 5_000_000;   // 문제에서 ki 최대값
    static int[] spf = new int[MAX + 1]; // SPF: smallest prime factor(가장 작은 소인수)

    // SPF(가장 작은 소인수) 배열을 채우는 함수
    static void buildSpf() {

        // i = 2부터 5,000,000(문제조건 최대값)까지 훑는다
        for (int i = 2; i <= MAX; i++) {
            if (spf[i] != 0) continue;  // 이미 소수가 기록되어있다면 패스

            // spf[i] == 0이면 아직 어떤 소수로도 방문된 적 없음 → i는 소수
            spf[i] = i;  // 소수는 자기 자신이 가장 작은 소인수

            // i*i 부터 시작해도 됨 (i보다 작은 소수들이 이미 방문했을 테니까)
            if ((long) i * i <= MAX) {
                for (int j = i * i; j <= MAX; j += i) {
                    // j에 아직 기록된 소인수가 없다면 i를 넣어줌 (처음 방문된 소수가 가장 작은 소인수)
                    // 쉽게 말해 i의 배수에는 모두 i를 넣어줌으로써 가장 작은 소수를 적어두는 셈
                    if (spf[j] == 0) spf[j] = i;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 미리 각 숫자별로 최소 소수를 기록해둠
        buildSpf();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int t = 0; t < N; t++) {
            int k = Integer.parseInt(st.nextToken());

            // k가 1이 될 때까지 반복
            while (1 < k) {
                int p = spf[k]; // k의 가장 작은 소인수 구하기
                sb.append(p).append(' ');  // 기록하고
                k /= p; // 가장 작은 소인수로 나누어서 계속 진행
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }
}
