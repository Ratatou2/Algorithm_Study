/*
[백준]
2512, 예산

[문제파악]
- 예상 배정은 아래와 같이 한다
	- 모든 요청이 배정될 수 있는 경우에는 요청한 금액을 그대로 배정한다
	- 모든 요청이 배정될 수 없는 경우에는 특정한 '정수' 상한액을 계산한다
	- 그 이상인 예산 요청에는 모두 상한액을 배정한다
	- 상한액 이하의 예산요청에 대해서는 요청한 금액을 그대로 배정한다
			ex) 전체 국가 예산 485
				- 지방 예산 요청 각각 120, 110, 140, 150
				- 이 경우 상한액을 127로 잡으면 위 요청들에 대해 120, 110, 127, 127을 배정하고 그 합이 484로 가능한 최대임
- 여러 지방의 예산 요청과 국가예산의 총액이 주어졌을 때, 위의 조건을 모두 만족하도록 에산을 배정하라

[입력]
- 첫째줄에 지방의 수를 의미하는 정수 N (3 <= N <= 10,000)
- 둘째줄에 지방의 예산요청을 표현하는 N개의 정수가 빈칸을 사이에 두고 주어진다
	- 1 <= 이 값들 <= 100,000
- 셋째줄에 총 예산을 나타내는 정수 M (N <= M <= 1,000,000,000)

[출력]
- 첫째줄에 배정된 예산들중 최댓값인 정수를 출력

[구현방법]
- 이것도 나무자르기랑 동일하게 풀어야할듯
- min, max 예산을 조절하며 평균치를 조금씩 조절해야할듯하다

[보완점]
- UpperBound를 알고 적용하는게 더 나을 것 같음
- 범위 지정을 avg(평균)으로 옮기면 탐색 속도가 훨씬 빠르다
- 초기 컨셉
    if (sum <= M) minBudget++;
    else maxBudget--;
- 변경 후
    if (sum <= M) minBudget = avgBudget + 1;
    else maxBudget = avgBudget;

- 공부하다 알았는데 UpperBound / LowerBound는 그냥 일종의 공식이었네...

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] budgets = new int[N];

        int minBudget = 0;
        int maxBudget = 0;
        int totalBudget = 0;
        for (int i = 0; i < N ; i++) {
            budgets[i] = Integer.parseInt(st.nextToken());
            minBudget = Math.min(minBudget, budgets[i]);
            maxBudget = Math.max(maxBudget, budgets[i]);
            totalBudget += budgets[i];
        }


        // 예산 만족시, 그냥 최댓값 출력하고 끝내면 됨
        if (M < totalBudget) {
            while (minBudget <= maxBudget) {
                int sum = 0;
                int avgBudget = (minBudget + maxBudget) / 2;

                // 전체 예산 계산
                for (int i = 0; i < N; i++) {
                    if (M < sum) break;
                    int currentBudget = budgets[i];

                    // 예산이 남으면, 예산 요청만큼만 할당한다
                    if (currentBudget < avgBudget) sum += currentBudget;
                    else sum += avgBudget;
                }

                if (sum <= M) minBudget = avgBudget + 1;  // 할당한 예상 총합이 전체 자금보다 작으면 최소 예산을 올려 평균치를 높인다
                else maxBudget = avgBudget - 1;  // 할당한 예산 총합이 전체 자금보다 높으면 최대 예상을 낮춰 평균치를 낮춘다
            }
        }

        System.out.println(maxBudget);
    }
}