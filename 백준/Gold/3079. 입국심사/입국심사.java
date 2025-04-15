/*
[백준]
3079, 입국심사

[문제파악]
- 상근이와 친구들은 오스트레일리아로 여행을 떠났다.
- 상근이와 친구들은 총 M명이고, 지금 공항에서 한 줄로 서서 입국심사를 기다리고 있다.
- 입국심사대는 총 N개가 있다.
- 각 입국심사관이 심사를 하는데 걸리는 시간은 사람마다 모두 다르다.
- k번 심사대에 앉아있는 심사관이 한 명을 심사를 하는데 드는 시간은 Tk이다.
- 가장 처음에 모든 심사대는 비어있고, 심사를 할 준비를 모두 끝냈다.
- 한 심사대에서는 한 번에 한 사람만 심사를 할 수 있다. 가장 앞에 서 있는 사람은 비어있는 심사대가 보이면 거기로 가서 심사를 받을 수 있다.
- 하지만 항상 이동을 해야 하는 것은 아니다. 더 빠른 심사대의 심사가 끝나길 기다린 다음에 그 곳으로 가서 심사를 받아도 된다.
- 예를 들어, 두 심사대가 있고, 심사를 하는데 걸리는 시간이 각각 7초와 10초라고 하자.
- 줄에 서 있는 사람이 6명이라면, 가장 첫 두 사람은 즉시 심사를 받으러 가게 된다.
- 7초가 되었을 때, 첫 번째 심사대는 비어있게 되고, 세 번째 사람이 그곳으로 이동해서 심사를 받으면 된다.
- 10초가 되는 순간, 네 번째 사람이 이곳으로 이동해서 심사를 받으면 되고, 14초가 되었을 때는 다섯 번째 사람이 첫 번째 심사대로 이동해서 심사를 받으면 된다.
- 20초가 되었을 때, 두 번째 심사대가 비어있게 된다.
- 하지만, 여섯 번째 사람이 그 곳으로 이동하지 않고, 1초를 더 기다린 다음에 첫 번째 심사대로 이동해서 심사를 받으면, 모든 사람이 심사를 받는데 걸리는 시간이 28초가 된다.
- 만약, 마지막 사람이 1초를 더 기다리지않고, 첫 번째 심사대로 이동하지 않았다면, 모든 사람이 심사를 받는데 걸리는 시간이 30초가 되게 된다.
- 상근이와 친구들이 심사를 받는데 걸리는 시간의 최솟값을 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 N과 M이 주어진다. (1 ≤ N ≤ 100,000, 1 ≤ M ≤ 1,000,000,000)
- 다음 N개 줄에는 각 심사대에서 심사를 하는데 걸리는 시간인 Tk가 주어진다. (1 ≤ Tk ≤ 109)

[출력]
- 첫째 줄에 상근이와 친구들이 심사를 마치는데 걸리는 시간의 최솟값을 출력한다.

[구현방법]
- 이거 PQ써야하는 문제일까..? 아니지 그럼 문제 분류가 PQ였겠지 얘가 어떻게 이분탐색이지...?
- 결국 못풀었고... 찾아보니 “시간 T 안에 M명을 처리할 수 있는가?”를 기준으로 이분 탐색하는 전형적인 parametric search (매개변수 탐색) 문제란다
- [어떤 시간 T가 주어졌을 때, 각 심사관이 T // Tk명의 사람을 처리할 수 있다] 이걸 모두 합치면 T 시간 안에 처리할 수 있는 총 인원 수가 됨
    - 이때, 우리가 원하는 건 M명을 모두 심사하는 데 걸리는 최소 시간임
- 이분 탐색 범위 설정
    - 최소 시간: 1초
    - 최대 시간: 가장 느린 심사관 시간 * M명 (모든 사람이 가장 느린 심사관에게 가는 최악의 경우)
- 이분 탐색 진행 방법
    - mid 시간 동안 처리할 수 있는 총 인원 수를 계산
    - sum(mid // t for t in times)로 계산
    - 만약 이 인원 수가 M 이상이면, 시간을 줄여도 된다는 의미 → right = mid
    - 아니면 시간 늘려야 → left = mid + 1
    - 탐색 종료 후 left 출력

- 내가 계속 의문이었던 것은 시간만 고려한 로직으로는 풀이가 불가능할 것이라 생각했음
- 그도 그럴 것이 비어있는 B심사관을 패스하고, 1초 기다렸다가 A심사관에게 받겠다는 판단 요소를 처리할 방법이 필요하다고 생각했기 때문임
- 근데 이 문제는 그렇게 어렵게 생각하지 않았다
- 그냥 시간을 정해놓고 해당 시간 내에 최대 몇명을 처리할 수 있는지를 계산했음...
- 이렇게 하면 그냥 N초 동안 M명의 심사관이 최대 몇명을 처리할 수 있는지 단순 계산으로 구할 수가 있음
- 약간 발상의 전환과 단순하게 보면서 이전 결과값에 따라 다음 판단에 영향을 미치는 것은 내게는 DP와 비슷하단 느낌...
- 어렵다잉...

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static long longestTime;
    static long[] times;

    static long findMinTime() {
        long left = 1;
        long right = longestTime * M;  // 최악의 경우는 가장 오래 걸리는 심사관이 모든 인원을 처리하는 경우다
        long result = right;  // 일단 가장 오래 걸리는 시간은 무조건 가능하니까 갱신해줌

        while (left <= right) {
            long mid = (left + right) / 2;
            long howManyPeople = 0;

            for (long time : times) {
                howManyPeople += mid / time;  // 현재 시간으로 계산 가능한 인원들 구하기

                // 지금 처리할 수 있는 인원이 M을 초과하면 더 이상 계산할 필요가 없음
                // 끝까지 하기도 전에 초과했으면 더 안해봐도 똑같으니까 ㅇㅇ
                if (M <= howManyPeople) break;
            }

            // 처리할 수 있는 사람이 M보다 크거나 같으면, 현재의 mid로 값 갱신
            if (M <= howManyPeople) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 입국 심사대 갯수
        M = Integer.parseInt(st.nextToken());  // 입국 심사 받을 사람 수

        times = new long[N];
        longestTime = 0L;

        // 시간 입력 받기
        for (int i = 0; i < N; i++) {
            times[i] = Long.parseLong(br.readLine());
            longestTime = Math.max(longestTime, times[i]);
        }

        System.out.println(findMinTime());
    }
}