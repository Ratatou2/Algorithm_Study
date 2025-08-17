/*
[백준]
11652, 카드

[문제파악]
준규는 숫자 카드 N장을 가지고 있다.
숫자 카드에는 정수가 하나 적혀있는데, 적혀있는 수는 -262보다 크거나 같고, 262보다 작거나 같다.
준규가 가지고 있는 카드가 주어졌을 때, 가장 많이 가지고 있는 정수를 구하는 프로그램을 작성하시오.
만약, 가장 많이 가지고 있는 정수가 여러 가지라면, 작은 것을 출력한다.

[입력]
첫째 줄에 준규가 가지고 있는 숫자 카드의 개수 N (1 ≤ N ≤ 100,000)이 주어진다.
둘째 줄부터 N개 줄에는 숫자 카드에 적혀있는 정수가 주어진다.

[출력]
첫째 줄에 준규가 가장 많이 가지고 있는 정수를 출력한다.

[구현방법]
- Map 사용
    - 다만 Map은 정렬할 수 없다
    - 따라서 정렬하려면 커스텀 만들거나 List로 변형해야하거나 해야한다
- 그리고 문제에 굉장히 ㅊ친절하게 적혀있듯 2의 +-62 제곱까지 되니까 int는 절대 안된다
    - long 써야함
[보완점]
*/


import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> count = new HashMap<>();

        // 값 집어넣기
        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            count.put(input, count.getOrDefault(input, 0) + 1);
        }

        // 최댓값 찾기
        String resultNum = "";
        int resultValue = 0;
        for (Map.Entry<String, Integer> temp : count.entrySet()) {
            // 현재 갱신된 값보다 더 많이 나온 숫자를 찾는다면? 갱신할 것
            if (resultValue < temp.getValue()) {
                resultNum = temp.getKey();
                resultValue = temp.getValue();
            } else if (resultValue == temp.getValue()) {
                long oldNum = Long.parseLong(resultNum);
                long newNum = Long.parseLong(temp.getKey());
                
                // 둘이 같은 경우엔 숫자가 더 작은걸 값으로 갱신한다
                if (newNum < oldNum) {
                    resultNum = temp.getKey();
                }
            }
        }

        System.out.println(resultNum);
    }
}
