/*
[백준]
9024, 두 수의 합

[문제파악]
- 여러 개의 서로 다른 정수 S = {a1, a2, …, an} 와 또 다른 정수 K 가 주어졌을 때, S 에 속하는 서로 다른 두 개의 정수의 합이 K 에 가장 가까운 두 정수를 구하시오.
- 예를 들어, 10 개의 정수 S = { -7, 9, 2, -4, 12, 1, 5, -3, -2, 0}가 주어졌을 때, K = 8 에 그 합이 가장 가까운 두 정수는 {12, -4} 이다.
- 또한 K = 4 에 그 합이 가장 가까운 두 정수는 {-7, 12}, {9, -4}, {5, -2}, {5, 0}, {1, 2} 등의 다섯 종류가 있다.
- 여러 개의 서로 다른 정수가 주어졌을 때, 주어진 정수들 중에서 서로 다른 두 정수의 합이 주어진 또 다른 정수에 가장 가까운 두 정수의 조합의 수를 계산하는 프로그램을 작성하시오.

[입력]
- 프로그램은 표준입력으로 입력을 받는다.
- 프로그램 입력은 t 개의 테스트 케이스로 구성된다.
- 입력의 첫 번째 줄에 테스트 케이스의 개수를 나타내는 정수 t 가 주어진다.
- 두 번째 줄부터 두 줄에 한 개의 테스트 케이스에 해당하는 데이터가 주어진다.
- 각 테스트 케이스의 첫 번째 줄에는 두 개의 정수 n 과 K (2 ≤ n ≤ 1,000,000, -108 ≤ K ≤ 108 )가 한 개의 공백을 사이에 두고 입력된다.
- 두 번째 줄에는 n 개의 정수가 하나의 공백을 사이에 두고 주어지며, 각 정수의 최댓값은 108 이고, 최솟값은 -108 이다.
- 잘못된 데이터가 입력되는 경우는 없다.

[출력]
- 출력은 표준출력(standard output)을 사용한다.
- 입력되는 테스트 케이스의 순서대로 다음 줄에 이어서 각 테스트 케이스의 결과를 출력한다.
- 각 테스트 케이스의 출력되는 첫 줄에 입력으로 주어진 n 개의 정수들 중에서 서로 다른 두 정수의 합이 주어진 또 다른 정수 K 에 가장 가까운 두 정수의 조합의 수를 출력한다.

[구현방법]
- 이 문제는 분명 투 포인터로 하면 굉장히 쉬울 것이다
- K값에 가까워지도록 끝에서부터 포인터를 왔다갔다 하면 되니까, 그러면서 값을 갱신하면 되니까
- 근데 이분탐색... 연습해야하니까 이분 탐색으로 간다
- 저번에 풀었던 문제랑 방식은 똑같은듯 함
- 숫자 하나 잡고 left, right 조절하면서 이분 탐색. 아마 n log n 걸렸던듯?
- 근데 얘는 경우의 수를 다 따져야하고, 가장 가까운 정수 값도 기억해둬야 함... 귀찮

[보완점]
- 내가 놓친 부분은 아래와 같다
    1) left는 for문의 인덱스인 i로 설정하면 target과 같아질 염려가 있으므로(자기 자신과 더하는 꼴이다) i + 1로 설정해야 함
    2) diff == minDiff 를 찾고 나서 인덱스를 옮길 필요가 없음
        - count++만 해주는 것은 어차피 index를 움직일 필요가 없는게, 문제 조건이 '서로 다른 숫자들'이다
        - 즉, diff == k를 찾아버렸다면? 이보다 더 베스트 케이스는 존재할 수가 없다
        - 그러므로 for문으로 다음 mid를 찾고 그 값으로 또 다시 탐색을 진행하면 된다
- 접근 방식은 틀리지 않았는데 로직 구현이 미흡했음
- 그래도 이분 탐색을 적용하는데 조금씩 나아지고 있다!! 더 풀어보자!!
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int test = 0; test < t; test++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // 숫자들 입력 받기
            int[] nums = new int[n];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            // 이분 탐색을 위한 정렬
            Arrays.sort(nums);

            int minDiff = Integer.MAX_VALUE;  // 최소 차이 (근삿값)
            int count = 0;  // 근삿값 갯수

            // 인자 하나 붙잡고 이분 탐색 시작
            for (int i = 0; i < n; i++) {
                int left = i + 1;  // 현재 인자를 제외하고 진행해야하니까 + 1
                int right = n - 1;  // 배열 인덱스는 0부터 시작이니까 -1

                while (left <= right) {
                    int mid = (left + right) / 2;
                    int currSum = nums[i] + nums[mid];
                    int currApprox = Math.abs(k - currSum);  // 현재 근삿값

                    // 현재 근삿값과 동일하면 count + 1
                    if (minDiff == currApprox) {
                        count++;
                    } else if (currApprox < minDiff) {  // 더 작은 근삿값을 찾았다면? 근삿값 갱신 및 count 초기화
                        minDiff = currApprox;
                        count = 1;
                    }

                    if (currSum < k) left = mid + 1;  // 현재 합이, 근사치보다 작으면? left를 늘려야지
                    else right = mid - 1;  // 현재 합이, 근사치보다 크면? right를 줄인다
                }
            }

            sb.append(count + "\n");
        }

        System.out.println(sb);
    }
}