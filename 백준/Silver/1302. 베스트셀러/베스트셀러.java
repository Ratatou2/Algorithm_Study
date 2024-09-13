/*
[백준]
1302, 베스트셀러

[문제파악]
- 김형택은 탑문고의 직원이다.
- 김형택은 계산대에서 계산을 하는 직원이다.
- 김형택은 그날 근무가 끝난 후에, 오늘 판매한 책의 제목을 보면서 가장 많이 팔린 책의 제목을 칠판에 써놓는 일도 같이 하고 있다.
- 오늘 하루 동안 팔린 책의 제목이 입력으로 들어왔을 때, 가장 많이 팔린 책의 제목을 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 오늘 하루 동안 팔린 책의 개수 N이 주어진다.
    - 이 값은 1,000보다 작거나 같은 자연수이다.
- 둘째부터 N개의 줄에 책의 제목이 입력으로 들어온다.
- 책의 제목의 길이는 50보다 작거나 같고, 알파벳 소문자로만 이루어져 있다.

[출력]
- 첫째 줄에 가장 많이 팔린 책의 제목을 출력한다.
- 만약 가장 많이 팔린 책이 여러 개일 경우에는 사전 순으로 가장 앞서는 제목을 출력한다.

[구현방법]
- 이거 맵 써서 계산하고, Comparable? 그거 걸면 쉽게 풀 수 있을 것 같은디
- 와... Collections.max(bestSeller.values()) 쓰면 Map의 value 중에 가장 큰 수 찾아다준다...
- 아 for문 돌려서 max 값 찾은 다음, 리스트에 추가 후 정렬해서 사전적 0 번째 인덱스 값을 찾는게 낫겠어
- 더 효율적인 방법은 다른 코드를 봐보자 (근데 살펴보니 11804kb 64ms로 5등정도 나옴 나름 효율적인 편! ㅎㅎ)
- 근데 이제 TreeMap 구조를 써가지고 자동정렬이란 기능이 들어가니 유용하게 써먹어보자
- 방식은 똑같이 가되 Hash Map 쓰고 for문 한번 돌려보자 어떤게 더 효율적일지
- TreeMap이 근사한 차이로 쥐톨만큼 더 빠르다
- 둘의 큰 차이는 없는 편임

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class BOJ_1302_베스트셀러 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        Map<String, Integer> bestSeller = new HashMap<>();
//
//        int N = Integer.parseInt(br.readLine());
//
//        for (int i = 0; i < N; i++) {
//            String bookName = br.readLine();
//            bestSeller.put(bookName, bestSeller.getOrDefault(bookName, 0) + 1);
//        }
//
//        // Collections를 쓰면 max를 쓸수 있다!?!?!?
//        int maxCount = Collections.max(bestSeller.values());
//        List<String> temp = new ArrayList<>();
//        for (Map.Entry<String, Integer> Entry : bestSeller.entrySet()) {
//            // 일단 TreeMap 이기 때문에 정렬되어 있는 상태이고 그렇기 때문에 첫번째 만난 친구가 정답이다
//            if (Entry.getValue() == maxCount) {
//                temp.add(Entry.getKey());
//            }
//        }
//
//        Collections.sort(temp);
//        System.out.println(temp.get(0));
//    }

    // TreeMap 구조
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> bestSeller = new TreeMap<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String bookName = br.readLine();
            bestSeller.put(bookName, bestSeller.getOrDefault(bookName, 0) + 1);
        }

        // Collections를 쓰면 max를 쓸수 있다!?!?!?
        int maxCount = Collections.max(bestSeller.values());
        for (Map.Entry<String, Integer> Entry : bestSeller.entrySet()) {
            // 일단 TreeMap 이기 때문에 정렬되어 있는 상태이고 그렇기 때문에 첫번째 만난 친구가 정답이다
            if (Entry.getValue() == maxCount) {
                System.out.println(Entry.getKey());
                return;
            }
        }
    }
}
