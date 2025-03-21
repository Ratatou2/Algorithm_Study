/*
[백준]
2696, 중앙값 구하기

[문제파악]
- 어떤 수열을 읽고, 홀수번째 수를 읽을 때 마다, 지금까지 입력받은 값의 중앙값을 출력하는 프로그램을 작성하시오.
- 예를 들어, 수열이 1, 5, 4, 3, 2 이면,
    홀수번째 수는 1번째 수, 3번째 수, 5번째 수이고,
    1번째 수를 읽었을 때 중앙값은 1,
    3번째 수를 읽었을 때는 4,
    5번째 수를 읽었을 때는 3이다.

[입력]
- 첫째 줄에 테스트 케이스의 개수 T(1 ≤ T ≤ 1,000)가 주어진다.
- 각 테스트 케이스의 첫째 줄에는 수열의 크기 M(1 ≤ M ≤ 9999, M은 홀수)이 주어지고, 그 다음 줄부터 이 수열의 원소가 차례대로 주어진다.
- 원소는 한 줄에 10개씩 나누어져있고, 32비트 부호있는 정수이다.

[출력]
- 각 테스트 케이스에 대해 첫째 줄에 출력하는 중앙값의 개수를 출력하고, 둘째 줄에는 홀수 번째 수를 읽을 때 마다 구한 중앙값을 차례대로 공백으로 구분하여 출력한다.
- 이때, 한 줄에 10개씩 출력해야 한다.

[구현방법]
- 이거 또 자꾸 헷갈리는데 정리를 해보자
- 요지는 PQ를 두개 만드는 것
    1) 하나는 작은 숫자를 역순으로 정렬 (smallPQ)
    2) 하나는 큰 숫자를 정방향으로 정렬 (largePQ)
- size를 비교해서 같으면 smallPQ에 넣고, 다르면 largePQ에 넣는다
- 그리고는 두 PQ가 모두 비어있는게 아니라면, smallPQ에 있는 첫번째와 largePQ에 있는 첫번째를 비교한다
- 만약 smallPQ에 있는게 largePQ에 있는 것보다 크다면 둘을 스위치해준다
넘 어렵게 생각말기 ㅠ

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static void printPQ(PriorityQueue<Integer> pq) {
        PriorityQueue<Integer> temp = new PriorityQueue<>(pq);
        StringBuilder sb = new StringBuilder();

        while (!temp.isEmpty()) {
            sb.append(temp.poll() + " ");
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int count = 0;

        for (int test = 0; test < T; test++) {
            count = 0;
            int N = Integer.parseInt(br.readLine());
            int[] nums = new int[N];
            int index = 0;  // 배열 저장과 출력값을 위한 인덱스
            StringBuilder testSB = new StringBuilder();

            PriorityQueue<Integer> smallNumPQ = new PriorityQueue<>(Comparator.reverseOrder());  // 가장 큰 순서대로 정렬
            PriorityQueue<Integer> largeNumPQ = new PriorityQueue<>();  // 가장 작은 순서대로 정렬

            // 입력받기
            for (int i = 0; i < (N / 10) + 1; i++) {
                st = new StringTokenizer(br.readLine());

                while (st.hasMoreTokens()) {
                    nums[index++] = Integer.parseInt(st.nextToken());
                }
            }

            // 인덱스가 0번부터 시작이니까, 여기서 홀수는 인덱스가 짝수가 되는 시점이다
            index = 1;
            for (int i = 0; i < N; i++) {
                // 두 PQ의 크기가 똑같으면 작은 쪽에 밀어넣고, 다르면 큰쪽에 밀어넣는다
                if (smallNumPQ.size() == largeNumPQ.size()) smallNumPQ.add(nums[i]);
                else largeNumPQ.add(nums[i]);

                // 둘중 하나라도 더 비어있으면 더 볼 것 없이 다음 탐색 진행
                if (!smallNumPQ.isEmpty() && !largeNumPQ.isEmpty()) {
                    // 큰 수만 모아둔 곳의 가장 작은 값보다 작은 수만 모아둔 곳의 가장 큰 값이 크면 둘을 교환해야한다
                    if (largeNumPQ.peek() < smallNumPQ.peek()) {
                        int temp = smallNumPQ.poll();  // 작은 쪽에 있는 큰 값은 임시 저장해두기
                        smallNumPQ.add(largeNumPQ.poll());  // 큰쪽에 있는 작은 값은 smallNumPQ에 넣기
                        largeNumPQ.add(temp);  // 임시저장해둔 큰 값은 largeNumPQ에 넣기
                    }
                }

                // 중앙값을 구해야하는 홀수 타이밍 (조건이 % 2 ==0인 이유는 인덱스가 0번부터 시작이기 때문이다)
                if (i % 2 == 0) {
                    testSB.append(smallNumPQ.peek()).append(" ");
                    if (index % 10 == 0) testSB.append("\n");
                    index++;
                    count++;
                }
            }

            sb.append(count).append("\n").append(testSB).append("\n");
        }

        System.out.println(sb);
    }
}