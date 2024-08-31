/*
[백준]
2075, N번째 큰수

[문제파악]
- N×N의 표에 수 N2개 채워져 있다.
- 채워진 수에는 한 가지 특징이 있는데, 모든 수는 자신의 한 칸 위에 있는 수보다 크다는 것이다.
- N=5일 때의 예를 보자.

12	7	9	15	5
13	8	11	19	6
21	10	26	31	16
48	14	28	35	25
52	20	32	41	49

- 이러한 표가 주어졌을 때, N번째 큰 수를 찾는 프로그램을 작성하시오.
- 표에 채워진 수는 모두 다르다.

[입력]
- 첫째 줄에 N(1 ≤ N ≤ 1,500)이 주어진다.
- 다음 N개의 줄에는 각 줄마다 N개의 수가 주어진다.
- 표에 적힌 수는 -10억보다 크거나 같고, 10억보다 작거나 같은 정수이다.


[출력]
- 첫째 줄에 N번째 큰 수를 출력한다.


[구현방법]
- TreeSet쓰면 알아서 정렬도 해주지 않나? 문제 조건도 중복되는 것이 없다니까 이거 써도 될듯한데
- 거기에 NlogN의 소요시간이니 괜찮을듯! -10억 < N < +10억
- 근데 이거 List로 변환해야 가져올 수 있네

[보완점]
- 이거 PQ써도 된다 (PriorityQueue)
- 오히려 PQ를 써야 더 효율적이다
- 내부적으로 이미 정렬을 다 해주고, 최악의 탐색시간이 logN이기 때문임
- PQ는 내부적으로 Heap으로 구현되어있음
- Heap은 완전 이진 트리구조
- PQ는 기본적으로 최소 힙이기 때문에 Collections.reverseOrder() 옵션을 줘야한다
- 아니 근데 짜고 보니까 결국 PQ도 인덱스로 못 가져오는데...?
- 이럴거면 TreeSet도 reverse만 걸어뒀으면 더 빨랐겠는데?

- 테스트 이후 결과 (동일한 조건 역정렬 + for문으로 탐색)
    - 결과
        - TreeSet : 382504kb, 1920ms
        - PQ : 334216kb, 816ms
    - 내가 간과했던 것이 PQ는 순차적 탐색에도 최악의 경우 소요시간은 logN라는 것이다
    - 반면에 TreeSet은 순차적 탐색이니까 O(N)인 것
    - PQ가 더 좋은 성능을 보였다

- 여기에 좀 더 나아가서 PQ를 제대로 써봅시다
- N개만 들어가는 최소힙을 만들고, 값이 입력들어올 때마다 갱신해주면 루트 노드가 N번째로 큰 숫자가 된다

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());

                // 들어있는게 N개보다 작으면 일단 넣어준다
                if (pq.size() < N) pq.add(num);
                else {
                    // N개보다 많으면 최솟값과 비교해서 넣어준다
                    if (pq.peek() < num) {
                        pq.poll();
                        pq.add(num);
                    }
                }
            }
        }

        // 최소힙 구조니까, 루트 노드가 N번째로 큰 숫자이다
        System.out.println(pq.peek());
    }


    // TreeSet 사용
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        Set<Integer> treeSet = new TreeSet<Integer>(Collections.reverseOrder());
//        int N = Integer.parseInt(br.readLine());
//
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//
//            for (int j = 0; j < N; j++) {
//                treeSet.add(Integer.parseInt(st.nextToken()));
//            }
//        }
//
//        int index = 1;
//        for (Integer i : treeSet) {
//            if (index == N) {
//                System.out.println(i);
//                return;
//            }
//
//            index++;
//        }
//    }

    // PQ 사용
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        int N = Integer.parseInt(br.readLine());
//        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
//
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//
//            for (int j = 0; j < N; j++) {
//                pq.add(Integer.parseInt(st.nextToken()));
//            }
//        }
//
//        for (int i = 0; i < pq.size(); i++) {
//            int temp = pq.poll();
//            if (i + 1 != N) continue;
//
//            System.out.println(temp);
//            return;
//        }
//    }
}