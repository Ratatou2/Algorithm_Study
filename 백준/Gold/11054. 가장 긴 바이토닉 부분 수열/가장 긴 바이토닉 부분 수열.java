

/*
[백준]
11054, 가장 긴 바이토닉 부분 수열

[문제파악]
수열 S가 어떤 수 Sk를 기준으로 S1 < S2 < ... Sk-1 < Sk > Sk+1 > ... SN-1 > SN을 만족한다면, 그 수열을 바이토닉 수열이라고 한다.
예를 들어, {10, 20, 30, 25, 20}과 {10, 20, 30, 40}, {50, 40, 25, 10} 은 바이토닉 수열이지만, {1, 2, 3, 2, 1, 2, 3, 2, 1}과 {10, 20, 30, 40, 20, 30} 은 바이토닉 수열이 아니다.
수열 A가 주어졌을 때, 그 수열의 부분 수열 중 바이토닉 수열이면서 가장 긴 수열의 길이를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 수열 A의 크기 N이 주어지고, 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ Ai ≤ 1,000)

[출력]
첫째 줄에 수열 A의 부분 수열 중에서 가장 긴 바이토닉 수열의 길이를 출력한다.

[구현방법]
- 일단 부분 수열을 구한 다음에 구해야할 것 같은데..?
- 바이토닉 수열 조건을 만족하려면 순차적으로 증가하거나, 순차적으로 감소하거나, 둘다 '순차적으로' 한 수열 안에 있으면 된다
- 그러면 하나씩 이동하면서 수열에 넣을지 말지, 이전보다 큰 수인지 아닌지, 앞의 숫자를 봤을 때 넣는게 나은지 아닌지를 계산해야할듯
- 이 과정을 거치려면 DP로 구현해야할듯 싶다
- 근데 DP에는 그럼 이전 값을 기억하면 되는건가? (오름차순인지, 내림차순인지 기억할 플래그(boolean)하나 두고?)

- 왼쪽에서 '가장 긴 증가 부분 수열(LIS)'를 구하고, 오른쪽에서 가장 긴 감소 부분 수열을 구하는게 방법이더라
- LIS를 구하려면.. DP[] 하나 만들고 현재 값이 이전값보다 크면? 이전의 max DP값과 현재까지의 값과 이전 DP값 +1 한 것 둘 중 어느쪽이 더 큰지 비교

[보완점]
- DP는 늘 새롭게 느껴진다는 것... 꾸준히 하면 얘도 좀 나아지겠지 뭐
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] DP_up = new int[N];
        int[] DP_down = new int[N];

        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 숫자들 입력 받기
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // DP의 초기값을 전부 1로 세팅한다 (자기 자신도 하나의 부분 수열이 될 수 있기 때문이다)
        for (int i = 0; i < N; i++) {
            DP_up[i] = 1;
            DP_down[i] = 1;
        }

        // 왼쪽에서부터 LIS 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) DP_up[i] = Math.max(DP_up[i], DP_up[j] + 1);
            }
        }

        // 오른쪽에서부터 LDS 구하기
        for (int i = N - 1; 0 <= i; i--) {
            for (int j = N - 1; i < j; j--) {
                if (nums[j] < nums[i]) DP_down[i] = Math.max(DP_down[i], DP_down[j] + 1);
            }
        }

        // 이제 하나씩 탐색하며 LIS + LDS 조합 중 가장 긴 지점을 찾으면 됨
        // 예를들면 i = 4 위치일 때 LIS + LDS를 더하면 몇인지 구하는 것임
        // 그리고 N-1까지 이동하면서 그 값을 갱신하면 됨
        // 또 마지막에는 LIS, LDS 양쪽 모두에 자기 자신이 포함된 것이니까 중복을 제거하기 위해 -1을 해주면 됨
        int maxLenth = 0;
        for (int i = 0; i < N; i++) {
            maxLenth = Math.max(maxLenth, DP_up[i] + DP_down[i] - 1);
        }

        System.out.println(maxLenth);
    }
}
