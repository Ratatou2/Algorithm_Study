/*
[백준]
20300, 서강근육맨

[문제파악]
- PT 한번 받을 때 기구 최대 2개만 선택 가능
- N개의 운동기구를 한번씩 사용해보고 싶어, 매번 이전엔 쓰지 않았던 것을 선택
- 대신 근손실을 유의해야하기에, 근손실 정도가 M을 넘지 않도록 한다
- M의 최솟값을 구하자 (두 운동기구의 근손실 정도의 합이 M을 넘지 않아야 함)

[입력]
- 첫째줄에 운동기구 갯수 N (1 <= N <= 10,000)
- 둘째줄에 각 운동기구의 근솔실 정도를 나타내는 정수들이 주어짐 (0 <= t_i <= 10^18)

[출력]
- M의 최솟값을 구하라

[구현방법]
- 예시를 보면 [1, 2, 3, 4, 5]일 때, 첫째 날에 1, 4 / 둘째 날에 2, 3 / 마지막 날에 5를 선택하면 M은 5가 되며 이것이 M이 최소일 때임
- 이말인 즉, 최솟값과 최댓값끼리 짝지으면 됨

[보완점]
- 홀수인 경우, 내가 초기에 생각한대로 마지막값이 최소 근손실 값이다
- 짝수인 경우, 원래 하려던 것처럼 최솟값과 최댓값의 합산으로 계산하면 됨
- NumberFormatError 발생 - 문제 잘보자 10^18은 int의 최대인 21억을 가볍게 상회한다
    - 이 말인 즉, 배열은 long으로 받아야 함
    - StringTokenizer도 Long으로 parse해야 함
    - 파이썬이 좋았지...
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
        long[] machines = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            machines[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(machines);

        int start = 0;
        int end = N-1;
        long minMuscleLoss = 0;
        if (N % 2 != 0) {
            end -= 1;
            minMuscleLoss = machines[N-1];
        }

        while (start < N/2 + 1) {
            minMuscleLoss = Math.max(machines[start] + machines[end], minMuscleLoss);  // 현재 근손실과 그간 최소 근손실 비교

            start += 1;
            end -= 1;
        }

        System.out.println(minMuscleLoss);
    }
}