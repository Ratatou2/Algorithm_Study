

/*
[백준]
20956, 아이스크림 도둑 지호

[문제파악]
아이스크림 가게에는 다양한 맛의 아이스크림 N개가 한 줄로 배치되어 있다.
아이스크림에는 번호가 매겨져 있는데, 가장 왼쪽 아이스크림이 1번, 그 오른쪽은 2번, ..., 가장 오른쪽 아이스크림은 N번이다.
지호는 항상 양이 가장 많은 아이스크림을 선택하여 전부 먹는다.
양이 가장 많은 아이스크림이 여러 개라면 가장 왼쪽에 있는 것을 먹는다.
지호는 대다수의 사람과 마찬가지로 민트초코 맛을 싫어한다.
다행히 지호는 아이스크림의 양이 주어질 때 아이스크림의 맛을 알 수 있다.
지호의 판별법에 따르면, 아이스크림의 양이 7의 배수라면 민트초코 맛이고, 그렇지 않다면 민트초코 맛이 아니라고 한다.
민트초코 맛 아이스크림을 먹은 지호는 크게 분노하여 남아 있는 아이스크림의 순서를 좌우로 뒤집는다.
즉, K개의 아이스크림이 있다면 i번째 아이스크림과 (K - i + 1)번째 아이스크림의 위치를 뒤바꾼다. (1 ≤ i ≤ ⌊K / 2⌋)
지호는 N개의 아이스크림 중 M개의 아이스크림을 먹으려 한다.
아이스크림의 양이 주어졌을 때, 지호가 먹은 아이스크림의 번호를 구하는 프로그램을 작성하시오.

[입력]
첫 번째 줄에 전체 아이스크림의 개수 N과 지호가 먹을 아이스크림의 개수 M이 주어진다.
두 번째 줄에 N개의 정수 A1, A2, ..., AN이 주어진다.
이때 Ai는 i번 아이스크림의 양을 의미한다.
모든 입력은 공백으로 구분되어 주어진다.

[출력]
M개의 줄에 걸쳐 i(1 ≤ i ≤ M)번째 줄에는 지호가 i번째로 먹은 아이스크림의 번호를 출력한다.

[구현방법]
- 아이스크림의 번호와 양을 저장하는 클래스를 만들면 좋을 것 같다
- 근데 좌우를 바꾼다는게 좌우 대칭 전부를 의미하는 것인가?
    - 맞다고 함
- 그러면 전체를 뒤집을게 아니라 좌우 어디서 뽑아낼지만 정하면 될 것 같은데
- 그리고 매번 아이스크림 중 최대값, 가장 양이 많은 것이 무엇인지 카운트해야한다..
- 그럼 양이 많은 것들을 위한 정렬된 순서의 list를 하나 준비하고, 하나는 deque로 좌우에서 먹는 것을 제거하는 것이 괜찮을듯하다
    - 예를들어 아이스크림의 양이 정렬하면 [8, 7, 7, 6, 6, 5, 4]이고, 정렬하지 않았을 경우 [4, 6, 7, 5, 7, 8] 이라면
    - 하나씩 가장 큰 것은 8이니까 8을 만날때까지 탐색.. 완탐은 좀문제인게 N이 10만이다. 두번만 탐색 해도 100만인데 너무 별로지 않은가?
- 그럼 양을 정렬하고 그것을 기준으로 삼을 Map을 준비해야할듯..?
- 정리하면
    1) 가장 큰 양을 찾기
    2) 그 양에서 가장 왼쪽 것을 찾기
    3) 먹고 (민초 여부에 따라) 상태 업뎃

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());                   // 전체 아이스크림 갯수
        int M = Integer.parseInt(st.nextToken());                   // 먹을 아이스크림 갯수

        Map<Integer, Deque<Integer>> locations = new HashMap<>();    // 각 용량에 따른 좌표
        List<Integer> amounts = new LinkedList<>();                  // 용량의 종류

        st = new StringTokenizer(br.readLine());

        // 0) 인덱스와 아이스크림의 양을 입력
        for (int i = 0; i < N; i++) {
            int iceAmount = Integer.parseInt(st.nextToken());
            locations.putIfAbsent(iceAmount, new ArrayDeque<>());    // 현재의 아이스크림 양을 기록하는 것이 처음이라면 새로 추가
            Deque<Integer> temp = locations.get(iceAmount);          // 현재의 아이스크림 양과 일치하는 key에 index 기록
            temp.add(i);
            amounts.add(iceAmount);                                 // 아이스크림 용량 추가
        }

        amounts.sort(Collections.reverseOrder());                   // 가장 큰 용량의 아이스크림 순으로 정렬
        boolean isReverse = false;                                  // 역뱡향 탐색 체크

        for (int eat = 0; eat < M; eat++) {
            int curr = amounts.remove(0);                       // 1) 가장 큰 양을 찾기
            Deque<Integer> temp  = locations.get(curr);              // 해당 아이스크림 용량을 가진 list 가져옴

            int currIndex = isReverse ? temp.pollLast() : temp.pollFirst();  // 2) 그 양에서 가장 왼쪽 것을 찾기

            sb.append(currIndex + 1).append("\n");                 // 3) 먹고 (민초 여부에 따라) 상태 업뎃

            if (curr % 7 == 0) isReverse = !isReverse;              // 현재 아이스크림이 민초(=7의 배수)인 경우, 좌우 방향 반대로 뒤집기
        }

        System.out.println(sb);
    }
}
