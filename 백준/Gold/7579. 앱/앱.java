/*
[백준]
7579, 앱

[문제파악]
- 우리는 스마트폰을 사용하면서 여러 가지 앱(App)을 실행하게 된다.
- 대개의 경우 화면에 보이는 ‘실행 중’인 앱은 하나뿐이지만 보이지 않는 상태로 많은 앱이 '활성화'되어 있다.
- 앱들이 활성화 되어 있다는 것은 화면에 보이지 않더라도 메인 메모리에 직전의 상태가 기록되어 있는 것을 말한다.
- 현재 실행 중이 아니더라도 이렇게 메모리에 남겨두는 이유는 사용자가 이전에 실행하던 앱을 다시 불러올 때에 직전의 상태를 메인 메모리로부터 읽어 들여 실행 준비를 빠르게 마치기 위해서이다
- 하지만 스마트폰의 메모리는 제한적이기 때문에 한번이라도 실행했던 모든 앱을 활성화된 채로 메인 메모리에 남겨두다 보면 메모리 부족 상태가 오기 쉽다.
- 새로운 앱을 실행시키기 위해 필요한 메모리가 부족해지면 스마트폰의 운영체제는 활성화 되어 있는 앱들 중 몇 개를 선택하여 메모리로부터 삭제하는 수밖에 없다.
- 이러한 과정을 앱의 ‘비활성화’라고 한다.
- 메모리 부족 상황에서 활성화 되어 있는 앱들을 무작위로 필요한 메모리만큼 비활성화 하는 것은 좋은 방법이 아니다.
- 비활성화된 앱들을 재실행할 경우 그만큼 시간이 더 필요하기 때문이다.
- 여러분은 이러한 앱의 비활성화 문제를 스마트하게 해결하기 위한 프로그램을 작성해야 한다
- 현재 N개의 앱, A1, ..., AN이 활성화 되어 있다고 가정하자.
- 이들 앱 Ai는 각각 mi 바이트만큼의 메모리를 사용하고 있다.
- 또한, 앱 Ai를 비활성화한 후에 다시 실행하고자 할 경우, 추가적으로 들어가는 비용(시간 등)을 수치화 한 것을 ci 라고 하자.
- 이러한 상황에서 사용자가 새로운 앱 B를 실행하고자 하여, 추가로 M 바이트의 메모리가 필요하다고 하자.
- 즉, 현재 활성화 되어 있는 앱 A1, ..., AN 중에서 몇 개를 비활성화 하여 M 바이트 이상의 메모리를 추가로 확보해야 하는 것이다.
- 여러분은 그 중에서 비활성화 했을 경우의 비용 ci의 합을 최소화하여 필요한 메모리 M 바이트를 확보하는 방법을 찾아야 한다.

[입력]
- 입력은 3줄로 이루어져 있다.
- 첫 줄에는 정수 N과 M이 공백문자로 구분되어 주어지며,
- 둘째 줄과 셋째 줄에는 각각 N개의 정수가 공백문자로 구분되어 주어진다.
    - 둘째 줄의 N개의 정수는 현재 활성화 되어 있는 앱 A1, ..., AN이 사용 중인 메모리의 바이트 수인 m1, ..., mN을 의미하며,
    - 셋째 줄의 정수는 각 앱을 비활성화 했을 경우의 비용 c1, ..., cN을 의미한다
    - 단, 1 ≤ N ≤ 100, 1 ≤ M ≤ 10,000,000이며, 1 ≤ m1, ..., mN ≤ 10,000,000을 만족한다.
    - 또한, 0 ≤ c1, ..., cN ≤ 100이고, M ≤ m1 + m2 + ... + mN이다.

[출력]
- 필요한 메모리 M 바이트를 확보하기 위한 앱 비활성화의 최소의 비용을 계산하여 한 줄에 출력해야 한다.

[구현방법]
- 누적 kill해서 확보한 메모리
- kill 하거나, 안하거나. 근데 또 cost고려 해야함
- M=60이면, 최소 cost로 60까지 확보
- 근데 또 고민해볼 것은 60을 만들거나, 그 이상이 되거나 상관없다. 어찌 되었건 cost만 최소면 됨.
- 정렬해서 할까 하여도 10, 20, 30, 35, 40 일 때, 10, 20, 30까지의 누적은 60이고 cost는 6 / 반면에 20, 40의 누적도 가능한데 이 경우 cost는 7
- 만약 40의 cost가 2만 작았어도 끝까지 고려했어야 함..
- 이러면 dp를 저장하는 자료구조를 따로 만들어야할까? memory와 cost 둘다 동시에 저장해서 누적해서 계산할 수 있도록?
- 그럼 이것도 top-down?

[보완점]
- 처음엔 dpCost와 dpMemory 두개로 나눠서 진행하는 방식을 생각했으나, 테케 하나를 계속 통과를 못하더라

19 20169
240 2560 434 6 31 577 500 2715 2916 952 2490 258 1983 1576 3460 933 1660 2804 2584
82 77 81 0 36 6 53 78 49 82 82 33 66 8 60 0 98 91 93

- 답은 484인데 자꾸 787...
- 찾아보니 하나로 통일한 뒤 메모리를 고려하여 진행할 수도 있다
- 그 방법은 dp 배열을 그냥 cost로 지정하고, memory를 갱신하면 되는 것이었음...
- 그렇게 갱신해두면 index는 cost가 될 것이고, 각 index에 최대의 memory를 체크할 수 있다
- 그럼 그냥 index 순차적으로 돌면서 dp[i]가 memory와 같거나 초과하는 순간이 최소의 cost니까 거기서 return
- 이렇게 간단할 수가!!!!

- 비용의 최대 합(int maxCost = 100 * N)을 미리 체크하는 것은 더 효율적인 답이 있을 수 있기 때문이다
- 메모리 리스트가 10, 20, 30 이 주어졌는데, 25를 구하라 했다면 10, 20까지만 확인하고 끝내버리는 것보단 30까지 확인하는게 더 효율적일 수 있기 때문이다
- 그래서 dp의 길이 역시 new int[maxCost + 1];로 주는 것임

5 60
30 10 20 35 40
3 0 3 5 4


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] memory = new int[N];
        int[] cost = new int[N];
        int maxCost = 100 * N;  // 비용의 최대 합을 미리 계산해둔다
        int[] dp = new int[maxCost + 1];  // 최소 비용으로 확보할 수 있는 최대 메모리 (index가 cost, 값이 최대 memory)

        st = new StringTokenizer(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st1.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int curMemory = memory[i];
            int curCost = cost[i];

            // 역방향 업데이트해야 중복값 피할 수 있음
            for (int j = maxCost; curCost <= j; j--) {
                dp[j] = Math.max(dp[j], dp[j - curCost] + curMemory);
            }
        }

        // 최소 비용 찾기
        for (int i = 0; i <= maxCost; i++) {
            if (M <= dp[i]) {
                System.out.println(i); // 필요한 메모리를 확보하는 최소 비용 출력
                break;
            }
        }

//        System.out.println(Arrays.toString(dp));
    }
}