
/*
[백준]


[문제파악]
선린의 대표 스윗남인 준영이는 여자친구 아스나를 위한 선물을 준비 중이다.
그는 N개의 진주로 이루어진 원형의 진주 목걸이를 선물해 줄 생각이다.

i (1 \leq i \leq N)번째 진주알은 가치 P_{i}를 지닌다.
이 진주알 N개를 적당한 순서로 재배열하여 목걸이의 가치가 최대가 되는 목걸이를 선물하려고 한다.
이때 임의로 재배열한 후 i번째 진주알의 가치를 A_i (1 \leq i \leq N)라 하자.
목걸이의 가치 X는 서로 인접한 진주알 쌍에 대해 두 진주알의 가치를 곱한 값의 합이다.

수식으로 표현하면 다음과 같다.
X=A_{1}\times A_{2}+A_{2}\times A_{3} + \cdots +A_{N-1}\times A_{N}+A_{N}\times A_{1}

목걸이의 가치 X의 최댓값과 그 가치가 나오기 위해 목걸이를 재배열했을 때 i번째 진주알의 가치 A_i를 출력하라.
가치가 최대인 목걸이 배치가 여러 가지 존재할 경우, 그 중 하나를 아무거나 출력한다.

[입력]
첫째 줄에 진주알의 개수 N이 주어진다.
둘째 줄에 각 진주알의 가치 P_1, P_2, \cdots, P_N이 공백으로 구분되어 주어진다.
입력으로 주어지는 수는 모두 정수이다.

[출력]
첫째 줄에 목걸이의 가치의 최댓값 X를 출력하라.
둘째 줄에 위의 가치가 나오기 위한 목걸이를 재배열하였을 때 각 진주알의 가치에 해당하는 N개의 수 A_1, A_2, \cdots, A_N를 공백으로 구분하여 출력하라.

[구현방법]
- 그냥 정렬해서 제일 큰수부터 앞에다가 두면 안되나..? 근데 이런 문제가 골드3 일리는 없겠지...?
- 출력 두번째 조건이 골3인 이유인 것 같다
    - '위의 가치가 나오기 위한 목걸이를 재배열하였을 때, 각 진주알의 가치에 해당하는 N개의 수를 공백으로 구분하여 출력하는 것
    - 대체 이게 뭔ㄷ..
- 단순 정렬은 아니였다
- 정렬은 맞는데 핵심은 '큰수들끼리 모여있는 것'이다
- 그래서 서로 곱치하고 지지고 복고해서 제일 큰 수를 유지할 수 있음
- 그리고 두번째 출력 조건은 그런 형태의 순서를 그냥 출력하는 것!

[보완점]

제한
3 \leq N \leq 2\,0001 \leq P_{i} \leq 1\,000 (1 \leq i \leq N)
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 숫자 입력 받기
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(nums);

        // 큰 수끼리 뭉쳐두기
        Deque<Integer> neckless = new ArrayDeque<>();
        for (int i = nums.length - 1; 0 <= i; i--) {
            if (i % 2 == 0) neckless.addFirst(nums[i]);
            else neckless.addLast(nums[i]);
        }

        // 계산하기
        StringBuilder sb = new StringBuilder();
        int result = neckless.getFirst() * neckless.getLast();
        int prev = neckless.pollFirst();
        sb.append(prev).append(" ");
        
        // 숫자를 하나씩 뽑아서 이전 숫자와 곱한뒤 더해줌
        for (int i = 1; i < N; i++) {
            int curr = neckless.pollFirst();
            result += prev * curr;
            sb.append(curr).append(" ");
            prev = curr;
        }

        System.out.println(result);
        System.out.println(sb);
    }
}
