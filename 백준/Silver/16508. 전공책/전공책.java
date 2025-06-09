/*
[백준]
16508, 전공책

[문제파악]
곧 졸업을 앞둔 민호는 대학교 생활 동안 구매만 해놓고 한 번도 펴보지 않은 전공책에 먼지가 쌓여 있는 것을 보고는, 이 책들을 어떻게 처리해야 할지 고민 중이다.
열심히 고민한 끝에 민호는 결국 전공책을 모두 버리기로 마음먹었다.
하지만 그냥 버리기엔 심심한 민호는 전공책 제목에 있는 글자들을 오려서 단어 만들기 놀이를 하려고 한다.
단어 만들기 놀이는 아래 예시와 같다.

1번 책 : COMPUTERARCHITECTURE (35,000원)
2번 책 : ALGORITHM (47,000원)
3번 책 : NETWORK (43,000원)
4번 책 : OPERATINGSYSTEM (40,000원)
만약 민호가 만들고 싶은 단어가 ALMIGHTY라고 하면, 위 4개의 책 중, 1번 책에서 A를, 2번 책에서 L, M, I, G, H, T를, 4번 책에서 Y를 오려내어 원하는 단어를 만들 수 있다.
이때 드는 비용은 1번, 2번, 4번 책 가격의 합인 122,000원이다.

만약 ANT라는 단어를 만들고 싶다고 하면, 2번 책에서 A를, 3번 책에서 N, T를 오려내어 원하는 단어를 만들 수 있다.
이때 드는 비용은 2번과 3번 책 가격을 합하여 90,000원이다.
그런데, ANT라는 단어에서 A를 2번 책에서 오려내지 않고, 4번 책에 있는 A를 오려낼 수도 있다.
만약 4번 책에서 A를 오려냈을 때 드는 비용은 3번과 4번 책 가격을 합하여 83,000원으로 2번과 3번 책을 고른 비용보다 작다.
하지만, 4번 책에는 ANT가 모두 포함되어 있으므로, 4번 책만 선택했을 때 드는 비용이 40,000원이다.
이는 ANT라는 단어를 만들기 위해서 가장 적은 비용이다.
민호는 여러 개의 전공책들을 나열해 놓고는, 심각한 고민 끝에 전공책 제목에 있는 글자를 오려내어 자신이 원하는 단어를 만들기 위해서는 여러 가지 경우가 있다는 것을 깨달았다.
매우 심심했던 민호는 가장 적은 비용으로 자신이 원하는 단어를 만들려면 어떤 전공책들을 선택해야 하는지 궁금했다.
하지만 일일이 가능한 조합을 만들어 보는 것은 매우 시간 낭비라고 생각한 민호는 컴퓨터공학과답게 프로그램을 만들려고 한다.
민호를 도와 각 전공책의 가격과 제목이 주어졌을 때, 가장 적은 비용으로 민호가 원하는 단어를 만들기 위해서 어떤 전공책을 선택해야 하는지 알아보자.

[입력]
첫 번째 줄에는 민호가 만들고자 하는 단어를 의미하는 문자열 T (1 ≤ |T| ≤ 10)가 주어진다. T는 항상 대문자이며, |T|는 문자열 T의 길이를 의미한다.
두 번째 줄에는 민호가 가진 전공책의 개수를 의미하는 정수 N (1 ≤ N ≤ 16)이 주어진다.
다음 N개의 각 줄에는 전공책 가격을 의미하는 정수 Ci (10,000 ≤ Ci ≤ 100,000)와 제목을 의미하는 문자열 Wi (1 ≤ |Wi| ≤ 50)가 주어진다. Wi는 항상 대문자이다.

[출력]
민호가 원하는 단어 T를 만들기 위해서 선택해야 하는 전공책의 가장 적은 가격의 합을 출력한다. 만약 단어를 만들 수 없다면 -1을 출력한다.

[구현방법]
- 흠... 모든 조합을 다 해봐야하나?
- 근데 그렇게 비효율적인 문제일리가 없지
- 이거 그냥 비트마스킹으로 철자 하나씩 체크해서 구하고자 하는 단어 만들 수 있는지 체크하는건가?
- 근데 그렇게 한다쳐도 조합은 어떻게...?

- 요지는 크게 3개
    1) 전공책들을 부분 집합으로 모두 탐색 (bitmask 사용)
    2) 선택된 책들에서 알파벳 수를 세고, 목표 단어와 비교
    3) 만들 수 있으면 최소 비용 갱신
- 조합을 비트마스킹으로 하는 것이었음... 진짜... 완탐할 때 자주 쓴다니까 기억해두자 ㅠ

[보완점]
*/

import java.io.*;

public class Main {
    // 주어진 문자열의 알파벳 개수 세기
    static int[] countLetters(String s) {
        int[] count = new int[26];

        // 철자별로 하나씩 잘라서 문자열 갯수 카운트
        for (char c : s.toCharArray()) {
            count[c - 'A']++;
        }

        return count;
    }

    // targetCount를 totalCount로 만들 수 있는가?
    static boolean canMake(int[] targetCount, int[] totalCount) {
        for (int i = 0; i < 26; i++) {
            // 타겟 갯수가 더 많으면 못만든단 뜻이니까 false
            if (totalCount[i] < targetCount[i]) return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = br.readLine();
        int n = Integer.parseInt(br.readLine());

        int[] costs = new int[n];
        String[] titles = new String[n];

        // 책과 비용 기록
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            costs[i] = Integer.parseInt(parts[0]);
            titles[i] = parts[1];
        }

        int minCost = Integer.MAX_VALUE;
        int[] targetCount = countLetters(target);

        // 모든 조합 탐색 (bitmask)
        // 매번 이 부분을 헷갈려하는데 비트마스킹으로 조합을 짜는 것은 크게 어려운 것이 아니다 (개념을 이해하고 있으면 됨)
        // 5개 중에 00101이 있다면? 3번과 1번을 선택한 것으로 보면 된다
        // 5개 중에 10100이 있다면? 5번과 3번을 선택한 것으로 보면 된다
        // 즉, 문제에선 N까지 주어지기에 1번부터 N까지 밀어버린 숫자까지 반복하면 되는 것이다
        for (int mask = 1; mask < (1 << n); mask++) {
            int totalCost = 0;
            int[] totalCount = new int[26];

            // 이 부분은 이제 비트를 순차적으로 탐색하면서 1인지를 찾는 것이다 (= (1 << i) 부분)
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {  // → mask의 i번째 비트가 1인지 확인 (즉, i번째 책이 이 조합에서 선택된 경우를 의미)
                    totalCost += costs[i];  // 선택된 책이니까 해당 책의 비용을 totalCost에 더함
                    int[] bookCount = countLetters(titles[i]);

                    // 계산해서 가지고 온 알파벳 갯수를 전체 카운트 배열에 더해준다
                    for (int j = 0; j < 26; j++) {
                        totalCount[j] += bookCount[j];
                    }
                }
            }

            // 현 조합으로 목푶한 바를 만ㄷ르 수 있다면? 최솟값 갱신
            if (canMake(targetCount, totalCount)) {
                minCost = Math.min(minCost, totalCost);
            }
        }

        System.out.println(minCost == Integer.MAX_VALUE ? -1 : minCost);
    }
}