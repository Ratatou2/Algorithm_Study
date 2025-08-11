

/*
[백준]
1798, 오큰수

[문제파악]
크기가 N인 수열 A = A1, A2, ..., AN이 있다. 수열의 각 원소 Ai에 대해서 오큰수 NGE(i)를 구하려고 한다.
Ai의 오큰수는 오른쪽에 있으면서 Ai보다 큰 수 중에서 가장 왼쪽에 있는 수를 의미한다.
그러한 수가 없는 경우에 오큰수는 -1이다.
예를 들어, A = [3, 5, 2, 7]인 경우 NGE(1) = 5, NGE(2) = 7, NGE(3) = 7, NGE(4) = -1이다.
A = [9, 5, 4, 8]인 경우에는 NGE(1) = -1, NGE(2) = 8, NGE(3) = 8, NGE(4) = -1이다.

[입력]
첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
둘째 줄에 수열 A의 원소 A1, A2, ..., AN (1 ≤ Ai ≤ 1,000,000)이 주어진다.

[출력]
총 N개의 수 NGE(1), NGE(2), ..., NGE(N)을 공백으로 구분해 출력한다.

[구현방법]
- 이거 단순 반복문으로...는 안될듯하다
- 일단 N은 최댓값이 1,000,000 = 10^6인데, 하나씩 죄다 비교하든 최악의 결과값은 10^12... (1억의 1만배니까 단순 계산으로 1만초 - 절대 안된다)
- 다른 방식이 필요함
- stack이라는데 어떻게... 쓰지...? 역순으로 뒤집어넣어도 해결할 순 없는데

- 이거는 문제를 많이 풀어봤다면 좀 더 금방 푸는 방법을 떠올렸을 것 같다 ㅠ
- 세상 어렵다!! 까진 아닌데 왤케 못풀었지 흠.. 당분간 자료구조 골드 문제들을 열심히 해봐야겠다

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] nums = new int[N];
        Stack<Integer> stackForNGE = new Stack<>();

        int[] answer = new int[N];
        Arrays.fill(answer, -1);  // 처음부터 -1 채워넣으면 편함

        // 숫자 입력 받기
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            while (!stackForNGE.isEmpty() && nums[stackForNGE.peek()] < nums[i]) {
                answer[stackForNGE.pop()] = nums[i];
            }

            stackForNGE.push(i);
        }

        for (int temp : answer) {
            sb.append(temp).append(" ");
        }

        System.out.println(sb);
    }
}
