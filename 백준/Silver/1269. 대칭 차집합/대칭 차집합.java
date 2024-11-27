/*
[백준]
1269, 대칭차집합

[문제파악]
- 자연수를 원소로 갖는 공집합이 아닌 두 집합 A와 B가 있다.
- 이때, 두 집합의 대칭 차집합의 원소의 개수를 출력하는 프로그램을 작성하시오.
- 두 집합 A와 B가 있을 때, (A-B)와 (B-A)의 합집합을 A와 B의 대칭 차집합이라고 한다.
- 예를 들어, A = { 1, 2, 4 } 이고, B = { 2, 3, 4, 5, 6 } 라고 할 때,  A-B = { 1 } 이고, B-A = { 3, 5, 6 } 이므로, 대칭 차집합의 원소의 개수는 1 + 3 = 4개이다.

[입력]
- 첫째 줄에 집합 A의 원소의 개수와 집합 B의 원소의 개수가 빈 칸을 사이에 두고 주어진다.
- 둘째 줄에는 집합 A의 모든 원소가, 셋째 줄에는 집합 B의 모든 원소가 빈 칸을 사이에 두고 각각 주어진다.
- 각 집합의 원소의 개수는 200,000을 넘지 않으며, 모든 원소의 값은 100,000,000을 넘지 않는다.

[출력]
- 첫째 줄에 대칭 차집합의 원소의 개수를 출력한다.

[구현방법]
- Map을 사용해서 숫자를 하나씩 추가하고 나중에 숫자들 있는 것들 중에서 count한게 1개 이상인 것들은 배제하면 될듯!!
- 더 빠른 방법이 있으려나..?

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = 0;

        int A_Count = Integer.parseInt(st.nextToken());
        int B_Count = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> numCheck = new HashMap<>();

        StringTokenizer A = new StringTokenizer(br.readLine());
        StringTokenizer B = new StringTokenizer(br.readLine());

        // A의 요소들 Map에 입력하기
        for (int i = 0; i < A_Count; i++) {
            int temp = Integer.parseInt(A.nextToken());
            numCheck.put(temp, numCheck.getOrDefault(temp, 0) + 1);
        }

        // B의 요소들 Map에 입력하기
        for (int i = 0; i < B_Count; i++) {
            int temp = Integer.parseInt(B.nextToken());
            numCheck.put(temp, numCheck.getOrDefault(temp, 0) + 1);
        }

        // Map을 순회하며 2개 이상인 것들은 세지 않는다
        for (Map.Entry<Integer, Integer> temp : numCheck.entrySet()) {
            if (temp.getValue() < 2) count++;
        }

        System.out.println(count);
    }
}