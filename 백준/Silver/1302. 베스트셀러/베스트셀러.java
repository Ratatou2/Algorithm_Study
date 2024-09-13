/*
[백준]


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
- 더 효율적인 방법은 다른 코드를 봐보자

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> bestSeller = new TreeMap<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String bookName = br.readLine();
            bestSeller.put(bookName, bestSeller.getOrDefault(bookName, 0) + 1);
        }

        int maxCount = Collections.max(bestSeller.values());
        List<String> temp = new ArrayList<>();
        for (Map.Entry<String, Integer> Entry : bestSeller.entrySet()) {
            if (Entry.getValue() == maxCount) {
                temp.add(Entry.getKey());
            }
        }
        
        Collections.sort(temp);
        System.out.println(temp.get(0));
    }
}