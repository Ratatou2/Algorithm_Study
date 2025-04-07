/*
[백준]
2470, 두 용액

[문제파악]
- KOI 부설 과학연구소에서는 많은 종류의 산성 용액과 알칼리성 용액을 보유하고 있다.
- 각 용액에는 그 용액의 특성을 나타내는 하나의 정수가 주어져있다.
- 산성 용액의 특성값은 1부터 1,000,000,000까지의 양의 정수로 나타내고, 알칼리성 용액의 특성값은 -1부터 -1,000,000,000까지의 음의 정수로 나타낸다.
- 같은 양의 두 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의한다.
- 이 연구소에서는 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다.
- 예를 들어, 주어진 용액들의 특성값이 [-2, 4, -99, -1, 98]인 경우에는 특성값이 -99인 용액과 특성값이 98인 용액을 혼합하면 특성값이 -1인 용액을 만들 수 있고, 이 용액이 특성값이 0에 가장 가까운 용액이다.
- 참고로, 두 종류의 알칼리성 용액만으로나 혹은 두 종류의 산성 용액만으로 특성값이 0에 가장 가까운 혼합 용액을 만드는 경우도 존재할 수 있다.
- 산성 용액과 알칼리성 용액의 특성값이 주어졌을 때, 이 중 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾는 프로그램을 작성하시오.

[입력]
- 첫째 줄에는 전체 용액의 수 N이 입력된다.
- N은 2 이상 100,000 이하이다.
- 둘째 줄에는 용액의 특성값을 나타내는 N개의 정수가 빈칸을 사이에 두고 주어진다.
- 이 수들은 모두 -1,000,000,000 이상 1,000,000,000 이하이다.
- N개의 용액들의 특성값은 모두 다르고, 산성 용액만으로나 알칼리성 용액만으로 입력이 주어지는 경우도 있을 수 있다.

[출력]
- 첫째 줄에 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값을 출력한다.
- 출력해야 하는 두 용액은 특성값의 오름차순으로 출력한다.
- 특성값이 0에 가장 가까운 용액을 만들어내는 경우가 두 개 이상일 경우에는 그 중 아무것이나 하나를 출력한다.

[구현방법]
- 2467, 용액 문제와 동일하다
- 근데 이번엔 이분 탐색으로 풀고 싶음
- 정렬을 하고 가운데를 기점으로 좌우를 나눠서 탐색을 해서 두개의 mid를 포인터로써 써야하나?
- 그냥 하나를 고정하고 그 값과 끝자락 사이에서 mid를 정하고 그 mid를 옮겨가며 탐색하는 방식
- 고정한 하나는 for문을 통해 계속 옮겨준다 (다만, 절반 지점까지만 탐색..?)
    - 절반 지점까지 탐색은 불가한 것이 -100, -90, -80, 10, 20, 30이 있다면? (10, 20)이 베스트 케이스임)
    - 근데? 절반 지점까지만 탐색하면 이 조합을 못찾지

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

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] liquids = new int[N];

        for (int i = 0; i < N; i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 정렬
        Arrays.sort(liquids);
        
        int min = Integer.MAX_VALUE;
        int[] answer = new int[2];
        for (int i = 0; i < N; i++) {
            int fixed = liquids[i];  // 현재 고정될 숫자
            int left = i + 1;  // for문이 왼쪽부터 탐색해 나가기 때문에 i+1부터 탐색한다
            int right = N - 1;

            // 지금은 하나의 값이 고정되어 있고, 그에 짝이 되는 값을 정확히 찾아야하기 때문에 left == right 지점까지 탐색한다
            while (left <= right) {
                int mid = (left + right) / 2;
                int sum = fixed + liquids[mid];

                // 최솟값 갱신시, 답을 저장해둔다
                if (Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    answer[0] = fixed;
                    answer[1] = liquids[mid];

                    if (min == 0) break;  // 값을 갱신했는데 0이다? 그럼 더 볼 것 없이 종료
                }

                // 이분탐색 위치 조정
                if (sum < 0) left = mid + 1;  // 합산보다 작으면 left를 늘려서 liquids[mid] 값을 키운다
                else right = mid - 1;  // 합산보다 크면, right를 줄려서 liquids[mid] 값을 줄인다
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}