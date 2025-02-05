/*
[백준]
2108, 통계학

[문제파악]
- 수를 처리하는 것은 통계학에서 상당히 중요한 일이다.
- 통계학에서 N개의 수를 대표하는 기본 통계값에는 다음과 같은 것들이 있다.
- 단, N은 홀수라고 가정하자.
    산술평균 : N개의 수들의 합을 N으로 나눈 값
    중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값
    최빈값 : N개의 수들 중 가장 많이 나타나는 값
    범위 : N개의 수들 중 최댓값과 최솟값의 차이
- N개의 수가 주어졌을 때, 네 가지 기본 통계값을 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 수의 개수 N(1 ≤ N ≤ 500,000)이 주어진다.
- 단, N은 홀수이다.
- 그 다음 N개의 줄에는 정수들이 주어진다.
- 입력되는 정수의 절댓값은 4,000을 넘지 않는다.

[출력]
첫째 줄에는 산술평균을 출력한다. 소수점 이하 첫째 자리에서 반올림한 값을 출력한다.
둘째 줄에는 중앙값을 출력한다.
셋째 줄에는 최빈값을 출력한다. 여러 개 있을 때에는 최빈값 중 두 번째로 작은 값을 출력한다.
넷째 줄에는 범위를 출력한다.

[구현방법]
- map을 써서 일단 갯수를 파악해야할 것 같다 (숫자 - 횟수)
- 산술 평균은 입력 받고 map에 입력 하면서 계산하면 될 것 같다
- 중앙값은 map을 입력받고, for문을 돌면서 '전체갯수/2' 지점이 되는 숫자를 찾아야할 것 같다
- 최빈값은 map에서 가장 많은 횟수를 찾으면 될듯하다
- 범위는 최댓값에서 최솟값을 빼면 된다

- 예외를 조심해야할 것 같다. 예시를 보아하니 값이 하나뿐일 땐 범위가 0이다

[보완점]
- TreeMap을 써야 정렬이 자동으로 된다...
- TreeMap을 쓰면 firstEntry, lastEntry라는 함수를 쓸 수 있음.. ㄷㄷ
- TreeSet을 쓰면 장점은 자동 정렬 기능을 활용할 수 있다는 것이지만, 단점은 삽입 및 탐색에 O(log N)이 걸려 성능이 상대적으로 느리다는 것임
- 오히려 배열을 쓰는게 더 빠를 줄 이야...
- 또한 Collections의 max 함수를 쓰면 한번에 Map의 최댓값을 찾을 수 있음... (e.g. Collections.max(numCount.values());)
- 아직 자료 구조 공부도 더 해야겠구나... ㅠ
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        TreeMap<Integer, Integer> numCount = new TreeMap<>();
        int totalAdd = 0;
        int input;

        int ans1, ans2 = 0, ans3 = 0, ans4;
        List<Integer> maxNums = new ArrayList<>(); // 최빈값 리스트

        // 입력받기
        for (int i = 0; i < N; i++) {
            input = Integer.parseInt(br.readLine());
            totalAdd += input;
            numCount.put(input, numCount.getOrDefault(input, 0) + 1);
        }

        // (1) 산술평균 (반올림) - 정수로 나누면 소숫점을 구할 수 없어서 round 계산 전에는 (double) 처리 한 것임
        ans1 = (int) Math.round((double) totalAdd / N);

        // (2) 중앙값 찾기
        int tempCount = 0;
        int middleIndex = N / 2 + 1; // 중앙값 위치
        for (Map.Entry<Integer, Integer> entry : numCount.entrySet()) {
            tempCount += entry.getValue();

            if (middleIndex <= tempCount) {
                ans2 = entry.getKey();
                break;
            }
        }

        // (3) 최빈값들 찾기
        int maxCount = Collections.max(numCount.values());  // 최대 빈도수
        for (Map.Entry<Integer, Integer> entry : numCount.entrySet()) {
            // maxCount와 같은 것을 찾으면 리스트에 추가한다
            if (entry.getValue() == maxCount) {
                maxNums.add(entry.getKey());
            }
        }

        // (3) 최빈값 결정 - 한개일 경우, 첫번째꺼 바로 픽, 아닐 경우 두 번째로 작은 값 선택
        if (maxNums.size() == 1) {
            ans3 = maxNums.get(0);
        } else {
//            Collections.sort(maxNums);  // 반드시 필요한가? maxNums에 추가했던 값 기준은 numCount이고 for문 돌았을 땐 TreeMap이라 이미 다 정렬 되어있을텐데?
            ans3 = maxNums.get(1);
        }

        // (4) 범위 계산
        ans4 = numCount.lastKey() - numCount.firstKey();

        // 출력
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);
        System.out.println(ans4);
    }
}