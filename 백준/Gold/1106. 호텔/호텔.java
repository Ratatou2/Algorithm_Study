/*
[백준]
1106, 호텔

[문제파악]
- 세계적인 호텔인 형택 호텔의 사장인 김형택은 이번에 수입을 조금 늘리기 위해서 홍보를 하려고 한다.
- 형택이가 홍보를 할 수 있는 도시가 주어지고, 각 도시별로 홍보하는데 드는 비용과, 그 때 몇 명의 호텔 고객이 늘어나는지에 대한 정보가 있다.
- 예를 들어, “어떤 도시에서 9원을 들여서 홍보하면 3명의 고객이 늘어난다.”와 같은 정보이다.
- 이때, 이러한 정보에 나타난 돈에 정수배 만큼을 투자할 수 있다.
- 즉, 9원을 들여서 3명의 고객, 18원을 들여서 6명의 고객, 27원을 들여서 9명의 고객을 늘어나게 할 수 있지만, 3원을 들여서 홍보해서 1명의 고객, 12원을 들여서 4명의 고객을 늘어나게 할 수는 없다.
- 각 도시에는 무한 명의 잠재적인 고객이 있다.
- 이때, 호텔의 고객을 적어도 C명 늘이기 위해 형택이가 투자해야 하는 돈의 최솟값을 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 C와 형택이가 홍보할 수 있는 도시의 개수 N이 주어진다.
- C는 1,000보다 작거나 같은 자연수이고, N은 20보다 작거나 같은 자연수이다.
- 둘째 줄부터 N개의 줄에는 각 도시에서 홍보할 때 대는 비용과 그 비용으로 얻을 수 있는 고객의 수가 주어진다.
- 이 값은 100보다 작거나 같은 자연수이다.

[출력]
- 첫째 줄에 문제의 정답을 출력한다.

[구현방법]
- 중복이 가능함. 이럴 경우 Down-Top으로 진행해도 된다
- 근데 또 문제에서는 최솟값을 구하라고 한다 흠... 최솟값이라
- 아 이거 꽤 고민을 해봤는데, 발상을 조금 전환하면 된다.
- 일단 dp를 cost로 만든다 (1 ~ C + 1 범위까지)
- 그리고 매 cost에서 최대로 늘릴 수 있는 customer를 구한다
- 그 상태에서 dp를 하나씩 살펴보며 C에 가장 빨리 도달하거나 그 이상값이 되는 순간의 index를 출력하면 최소 cost이다
- C가 1,000 이하라서 미리 배열을 그만큼 만들어둘까 했는데, 그럼 배열 다 채우느라 시간 소모할 것 같아서 크기는 입력값으로 가변으로 하였음
    - 정답을 구하는데는 C까지면 충분할 것이라는 판단이었음
    - [오답] 바로 이 부분이 틀린 점이었음 (아니 이거 입력값을 어디까지 받아야하나?)
    - 현재 반례 하나를 못 뚫고 있음 이유는 배열의 길이가 너무 길어져야 해서
    - 현재 dp를 cost로 만들어서인 것 같은데.. customer로 수정해보자 min값으로

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());  // 늘려야하는 고객의 수
        int N = Integer.parseInt(st.nextToken());  // 도시 갯수

        int[] dp = new int[C + 100 + 1];
        // 냅다 Max로 해버리면 1만 더해도 바로 - 되버려서...
        // 그리고 반복문 쓸 것 없이 Arrays.fill 이란 함수 쓰면 된다
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;  // 이거 꼭 해줘야 함

        // System.out.println(Arrays.toString(dp));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());  // 비용
            int customer = Integer.parseInt(st.nextToken());  // 늘어나는 인구

            for (int j = customer; j <= C + 100; j++) {
                dp[j] = Math.min(dp[j], dp[j - customer] + cost);
            }

            // System.out.println(Arrays.toString(dp));
        }

        int minCost = Integer.MAX_VALUE;

        // 시작 지점 C로 해야함
        // 이걸 안하면 그냥 냅다 1부터 시작해서 최솟값으로 잡아버리기 때문임
        // 애초에 dp의 index는 people이니까, 최소한의 인원을 채우려면 dp[C]부터 봐야함
        // 추가로 탐색하는 건 더 많은 사람을 데려가더라도 더 효율적인 방법(= 더 적은 비용)이 있을까봐 탐색하는 것임
        for (int i = C; i <= C + 100; i++) {
            minCost = Math.min(minCost, dp[i]);
        }

        System.out.println(minCost);
    }
}