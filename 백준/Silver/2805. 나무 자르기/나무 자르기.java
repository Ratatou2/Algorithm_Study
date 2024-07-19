/*
[백준]
2805, 나무 자르기

[문제파악]
- M미터 나무 필요
- 절단기에 높이 H 지정, 톱날이 H만큼 올라가고 한줄에 속해있는 나무는 모두 잘린다
	- 높이가 H보다 큰 나무는 H 윗부분이 잘릴 것이고, 낮은 나무는 안 잘릴 것이다
- 나무를 필요한만큼만 가져가려고 할 때, 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 구하라

[입력]
- 첫째줄에 나무의 수 N, 집으로 가져가려는 나무의 길이 M
	- (1 <= N <= 1,000,000)
	- (1 <= M <= 2,000,000,000)
- 둘째줄에 나무의 높이
	- 나무의 높이의 합은 항상 M보다 크거나 작다 (항상 필요한 나무를 집에 가져갈 수 있다)
	- 높이는 정수 (0 <= H <= 1,000,000,000)

[출력]
- 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 출력

[구현방법]
- 이분탐색처럼 나무 자를 높이를 중간 값으로 잡고 누적합으로 진행해서 적합한 높이를 찾아야하나...
- 근데 이렇게 하면 너무 복잡하고 번거로운데...
- 애초에 나무 갯수가 최대 1,000,000개에 구해야하는 M이 2,000,000,000이라 중간값부터 찾아서 누적합하기엔 기회비용이 너무 큰데요.
    - 중간에 한번이라도 뻑나면 이걸 언제 또 중간값을 찾 (제한시간 1초임)

[보완점]
- 단순히 이분탐색은 아니었음! Upper Bound, Lower Bound 라는게 필요함
- 그 이유는 이분탐색처럼 딱 맞아 떨어지는, 일치하는 값을 찾는게 아니기 때문임
- 파라메트릭 알고리즘?! (주어진 범위 내에서 원하는 값 or 조건이 일치하는 값을 찾아내는 알고리즘)
- 생각보다 기준값(자를 높이)을 정하고 그 값으로 순회해서 차이를 다 더하는게 오래 걸리지 않음
- 
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = br.readLine().split(" ");

        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int[] trees = new int[N];
        temp = br.readLine().split(" ");

        int min = 0;
        int max = 0;
        // 굳이 정렬할 필요없이, max를 찾아두면 됨. 거기서부터 차감하면 되니까
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(temp[i]);
            max = Math.max(max, trees[i]);
        }

        while(min <= max) {
            int mid = (min + max) / 2;
            long sum = 0;

            // 중간 값보다 크면, 자를 높이에서 나머지 나무 길이를 더함
            for(int i = 0; i < N; i++) {
                if(mid < trees[i]) sum += trees[i] - mid;
            }

            // 더한 값이 기준값(M)보다 크면 최소를 mid로 셋팅하고 +1 해서 기준값을 올린다 (자를 높이를 올리는 셈)
            if(M <= sum) min = mid + 1;
            // 더한 값이 기준값(M)보다 작으면 최대를 mid로 셋팅하고 -1해서 기준값을 내린다 (자를 높이를 내리는 셈)
            else if(sum < M) max = mid - 1;
        }

        System.out.println(max);
    }
}