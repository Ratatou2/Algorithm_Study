/*
[백준]
11279, 최대힙

[문제파악]
- 널리 잘 알려진 자료구조 중 최대 힙이 있다.
- 최대 힙을 이용하여 다음과 같은 연산을 지원하는 프로그램을 작성하시오.

- 배열에 자연수 x를 넣는다.
- 배열에서 가장 큰 값을 출력하고, 그 값을 배열에서 제거한다.
- 프로그램은 처음에 비어있는 배열에서 시작하게 된다.

[입력]
- 첫째 줄에 연산의 개수 N(1 ≤ N ≤ 100,000)이 주어진다.
- 다음 N개의 줄에는 연산에 대한 정보를 나타내는 정수 x가 주어진다.
- 만약 x가 자연수라면 배열에 x라는 값을 넣는(추가하는) 연산이고, x가 0이라면 배열에서 가장 큰 값을 출력하고 그 값을 배열에서 제거하는 경우이다.
- 입력되는 자연수는 231보다 작다.

[출력]
입력에서 0이 주어진 횟수만큼 답을 출력한다.
만약 배열이 비어 있는 경우인데 가장 큰 값을 출력하라고 한 경우에는 0을 출력하면 된다.

[구현방법]
- 최소힙(1927) 문제처럼 PQ 쓰면 되는 문제다
- 정말 편한게 최대 힙을 구현하는 방식은 그저 PQ를 선언할 때 Collections.reverseOrder() 옵션을 주는 것이다;;;
- 진짜 끝내주는 구현도인 셈이다... 사람들은 정말 똑똑해
- 무튼 reverseOrder를 적용해두면 당연히 큰 수대로 출력한다 

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());

            if (input == 0) {
                if (maxHeap.isEmpty()) sb.append(0).append("\n");
                else sb.append(maxHeap.poll()).append("\n");
            } else maxHeap.add(input);
        }

        System.out.println(sb);
    }
}