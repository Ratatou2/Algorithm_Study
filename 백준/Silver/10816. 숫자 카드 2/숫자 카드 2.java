/*
[백준]
10816, 숫자 카드 2

[문제파악]
- 정수가 하나 적혀있는 숫자카드 N개
- M개가 주어졌을 때, N개 중에 이 숫자가 적혀있는 카드가 몇개인지 확인
-

[입력]
- 첫째줄에 숫자 카드 갯수 N (1 <= N <= 500,000)
- 둘째줄에 숫자 카드에 적혀 있는 정수들 (-10,000,000 <= 정수들 <= 10,000,000)
- 셋째줄에 M (1 <= M <= 500,000)
- 넷째줄에 M개의 정수들 (-10,000,000 <= 정수들 <= 10,000,000)

[출력]
- 첫째줄에 입력으로 주어진 M개의 수에 대해, 각 수가 적힌 숫자 카드가 몇개인지 공백을 두고 출력

[구현방법]
- 음 중복되는 숫자 카드가 몇개인지 세둔 map과 숫자들을 중복없이 넣어둔 Hashmap 하나 있으면 될듯
- Map에는 숫자 갯수를 세서 미리 넣어둔다
- M 리스트 입력 받을 때, 해당 Map에 있으면 그 count 숫자를, 없으면 0을 출력한다

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> countNum = new HashMap<>();

        // Map 구성 반복문
        for (int i = 0; i < N; i++) {
            int currentNum = Integer.parseInt(st.nextToken());

            // Map에 있으면 +1, 없으면 추가
            if (countNum.containsKey(currentNum)) {
                countNum.put(currentNum, countNum.get(currentNum) + 1);
            } else {
                countNum.put(currentNum, 1);
            }
        }

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int findNum = Integer.parseInt(st.nextToken());

            // Map에 지금 찾는 숫자가 있으면 그 갯수를 출력, 없으면 0
            if (countNum.containsKey(findNum)) {
                sb.append(countNum.get(findNum)).append(" ");
            } else sb.append(0).append(" ");
        }

        System.out.println(sb);
    }
}