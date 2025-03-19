/*
[백준]
1655, 가운데를 말해요

[문제파악]
- 백준이는 동생에게 "가운데를 말해요" 게임을 가르쳐주고 있다.
- 백준이가 정수를 하나씩 외칠때마다 동생은 지금까지 백준이가 말한 수 중에서 중간값을 말해야 한다.
- 만약, 그동안 백준이가 외친 수의 개수가 짝수개라면 중간에 있는 두 수 중에서 작은 수를 말해야 한다.
- 예를 들어 백준이가 동생에게 1, 5, 2, 10, -99, 7, 5를 순서대로 외쳤다고 하면, 동생은 1, 1, 2, 2, 2, 2, 5를 차례대로 말해야 한다.
- 백준이가 외치는 수가 주어졌을 때, 동생이 말해야 하는 수를 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에는 백준이가 외치는 정수의 개수 N이 주어진다.
- N은 1보다 크거나 같고, 100,000보다 작거나 같은 자연수이다.
- 그 다음 N줄에 걸쳐서 백준이가 외치는 정수가 차례대로 주어진다.
- 정수는 -10,000보다 크거나 같고, 10,000보다 작거나 같다.

[출력]
- 한 줄에 하나씩 N줄에 걸쳐 백준이의 동생이 말해야 하는 수를 순서대로 출력한다.

[구현방법]
- 뭐 숫자 중간값 찾는거야 PQ에 밀어넣으면 정렬될테니까 문제는 없겠지만... 이걸 중간값을 매번 찾는 것이 문제이다
- 제한 시간 0.1초 ㅋㅋ 이거 완탐으로 절대 못찾는다 O(1) 정도는 되어야 할듯 최소한 O(log N)은 되어야 코드 돌려볼 수 있을듯하다
- PQ를 두개 써서 중간값 기준으로 나눌까 근데 그럼 매번 갯수(size) 세고, 갯수 따라 오른쪽 왼쪽으로 하나씩 이동시켜야하고 흠..
- 배열로 만드는건? 아 이게 순서가 보장되는게 아니라서... 그러니까 PQ에 들어있는 상태가 정렬이 되어있다고 말할 수 없음
- PQ문제라는건 PQ로 풀리니까 그런거잖아 (당연한 말;;)
- PQ의 장점은 poll과 add가 O(1)이라는거지.. 그럼 그냥 이걸 계속 반복하라고? 0.1초인데.. 그럼 골2가 아닐건데 시도나 해보자

- 음 역시 제대로 동작하지 않았고, 결국 참고했음 ㅠ
- 우선 내가 생각했던 대로 두개의 PQ를 쓰는 것은 맞았다
- 요지는 둘을 스왑하는 로직까지 고려해주는 것
- 즉 내가 이게 될까? 했던 지점이 PQ를 두개 쓰면 서로 값이나 갯수가 안맞으면 옮겨줘야하는데 이게 맞나? 였는데 맞았다
- 그걸 구현하면 됨 그냥... 실마리를 알아도 구현할줄 모르다니.. 더 공부해야 함이다

- PQ 우선순위 정할 때 그냥 Comparator.reverseOrder()를 써도 되지만, PriorityQueue<>((o1, o2) -> o1 - o2)처럼 비교해줘도 됨
- 두 개의 PQ를 운영하면서 서로 교환해주는건 정렬만 잘해두면 서로 값을 비교하며 진행하면 된다
- 정렬을 하나는 내림차순, 하나는 오름차순으로 peek()만 하면서 값들 비교해주고,
- 작은 값들만 모아둔 곳의 가장 큰 값이, 큰 값들만 모아둔 곳의 가장 작은 값보다 크게 되면 둘을 바꿔주면 된다
- 이 바꾸는 과정은 정말 솔직하고 별 것 없음 각자 하나씩 poll()하고 서로 교환해서 집어넣으면 된다 (so ez)

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        // largeNumPQ : 큰 숫자들 오름차순 정렬 (즉, 맨 앞에 있는게 큰 숫자들 중 가장 작은 친구이다)
        // smallNumPQ : 작은 숫자들 내림차순 정렬 (즉, 맨 앞에 있는게 작은 숫자들 중 제일 큰 친구이다)
        PriorityQueue<Integer> largeNumPQ = new PriorityQueue<>();
        PriorityQueue<Integer> smallNumPQ = new PriorityQueue<>((o1, o2) -> o2 - o1);  // Compartor.reverseOrder()랑 같은 기능이다

        for (int i = 0 ; i < n; i++){
            int num = Integer.parseInt(br.readLine());

            // 둘의 크기가 똑같으면 작은 쪽에 밀어넣는다
            if (largeNumPQ.size() == smallNumPQ.size()) smallNumPQ.offer(num);
            else largeNumPQ.offer(num);   // 그게 아니라면 큰 쪽에 밀어넣음

            // largeNumPQ와 smallNumPQ 둘다 비어있지 않다면, 둘을 비교할 수 있다는 의미
            if (!largeNumPQ.isEmpty() && !smallNumPQ.isEmpty())
                // 즉, largeNumPQ에서 첫번째 (가장 작은값) 보다, smallNumPQ의 가장 큰 값이 크다면 옮겨주도록 하자
                if (largeNumPQ.peek() < smallNumPQ.peek()){
                    // 큰 값에서 가장 작은 첫번째를 뽑아서
                    int minNumInLargeNumPQ = largeNumPQ.poll();
                    largeNumPQ.offer(smallNumPQ.poll());  // 큰 값이 모여있는 PQ에는 samllNumPQ에서 가장 큰 값을 밀어넣고
                    smallNumPQ.offer(minNumInLargeNumPQ);  // 작은 값이 모여있는 PQ에는 largeNumPQ에서 가장 작은 값을 밀어넣어 둘을 바꿔준다 (switch)
                }

            // 이런 식으로 구성하면 작은 값이 모여있는 PQ에 가장 첫번째 값이 중앙값이 된다
            // smallNumPQ에서는 가장 큰 값이며, 동시에 largeNumPQ에서는 가장 작은 값 취급이 된다
            sb.append(smallNumPQ.peek()).append("\n");
        }

        System.out.println(sb);
    }
}