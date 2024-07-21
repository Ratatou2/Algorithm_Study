/*
[백준]
6236, 용돈관리

[문제파악]
- N일 동안 사용할 금액을 계산, 정확히 M번만 통장에서 돈을 빼 쓰기로 결정
- K원을 인출하며, 통장에서 뺀 돈으로 하루를 보낼 수 있으면 그대로 사용
- 보자라면 남은 금액은 통장에 집어넣고 다시 K원을 인출
- 정확히 M번을 맞추기 위해서 남은 금액이 그날 사용할 금액보다 많더라도 남은 금액은 통장에 집어넣고 다시 K원을 인출할 수 있다
- 인출 금액 K를 최소화 하기로 했다
- 최소 금액 K를 계산하라

[입력]
- 첫째줄에는 N과 M이 공백으로 주어짐 (1 <= N <= 100,000, 1 <= M <= N)
- 둘째줄에는 총 N개의 줄에는 i번째 날에 이용할 금액이 주어진다 (1 <= 금액 <= 10,000)

[출력]
- 첫째줄에 통장에서 인출해야할 최소 금액 K를 출력

[구현방법]
- 일단 누적으로 쓰고 모자랄 떄 더 뽑고 하는 식은 아니었음
- 그냥 인출하고 다음날까지 처리할 수 있으면 틀어막지만, 그게 아니라면 다신 인출해야함
- 그 와중에 count는 맞춰야하고
- 아 이런 문제 왤케 헷갈리냐

[보완점]
- 결국 참고했는데 참고하고 보니 이 문제는 그냥 이분탐색이다
- 근데 거기에 이제 체크 하는 조건 값이 하나 추가된 이분탐색...
- 아직 응용을 하기엔 어려웠나보다 이분탐색 문제 더 풀어봐야겠네
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int N, M, result;
    static int[] weekPlan;

    static int withdrawCount(int money) {
        int count = 1;  // 기본 1회 인출로 시작
        int tempMoney = money;  // 계산에 쓸 금액 (복사)

        // 1회 인출 금액에서 계속 차감하며 몇번 인출하는지 count
        for (int i : weekPlan) {
            tempMoney -= i;

            // 돈이 모자라면 현금을 다시 인출하여 사용
            if (tempMoney < 0) {
                count++;
                tempMoney = money - i;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        weekPlan = new int[N];

        int min = 0;
        int max = 0;

        // 입력값 받기
        for (int i = 0; i < N; i++) {
            int currentMoney = Integer.parseInt(br.readLine());

            weekPlan[i] = currentMoney;
            min = Math.max(min, currentMoney);  // min값은 가장 큰 비용이 되어야한다
            max += currentMoney;  // 동시에 max값은 총합이 되어야한다
        }

        while(min <= max) {
            int mid = (min + max) / 2;

            // 현재 1회 인출 세팅값이 count 조건도 만족할 경우, 일단 답으로 셋팅(result = mid)해두고 더 작은 케이스도 있는지 확인(max = mid - 1)한다
            // 그 외엔 최솟값을 증가시키며 진행한다
            if (withdrawCount(mid) <= M) {
                result = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(result);
    }
}