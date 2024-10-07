/*
[백준]
12865, 평범한 배낭

[문제파악]
- 세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.
- 준서가 여행에 필요하다고 생각하는 N개의 물건이 있다.
- 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다.
- 아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다.
- 준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.

[입력]
- 첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다.
- 두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.
- 입력으로 주어지는 모든 수는 정수이다.

[출력]
- 한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.

[구현방법]
- 한동안 문자열만 겁나 풀었기에 이제 주제를 바꿔볼까 했다. 그 와중에 냅색(KnapSack) 문제 많이 안풀어서 이거랑 자료구조를 도전해볼까 함.
- 이거 DP 문제다
- 적은 kg일 때 넣을 수 있는 최대 가치를 더해가며 구하면 된다.
- 그렇다면 각 무게마다 최대로 들고갈 수 있는 가치를 구하는 수식을 짜야할 것 같다.. (나도 알아 인마 그래서 어떻게 짜는건데)
- 난 DP가 싫어...
- 원리를 이해하고자 했다. 일단 내가 3kg을 들수 있을 때를 가정하면 두가지를 비교하면 된다.
    1) 2kg에서 들고 있던 최댓값 + 1kg에서 들고 있던 최댓값
    2) 이전 기록에서 3kg에서 최댛나 들 수 있는 최댓값
- 위 두가지로 계속 누적해오면 항상 내가 가져갈 수 있는 최댓값이 된다.
- 알겠는데.. 대충 알겠는데 그래도 어렵다구요~

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 물품의 수
        int K = Integer.parseInt(st.nextToken());  // 들고갈 수 있는 무게
        // DP 배열: 각 무게에서의 최대 가치 저장 (0부터 K까지)
        int[] dp = new int[K + 1];

        // 물품 정보 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());  // 물건의 무게
            int V = Integer.parseInt(st.nextToken());  // 물건의 가치

            // 무게를 고려하여 뒤에서부터 갱신 (중복 선택 방지)
            for (int j = K; j >= W; j--) {
                dp[j] = Math.max(dp[j], dp[j - W] + V);
            }
        }

        // 배낭에 넣을 수 있는 최대 가치 출력
        System.out.println(dp[K]);
    }
}