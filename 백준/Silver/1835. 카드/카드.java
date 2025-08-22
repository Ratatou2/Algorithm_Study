
/*
[백준]


[문제파악]
1부터 N까지의 숫자가 적힌 카드가 있다. 찬유는 이 카드를 가지고 마술을 하려 한다.
마술을 하는 순서는 다음과 같다.

먼저 1부터 N까지의 숫자가 적힌 카드에서 첫 번째 카드를 가장 뒤로 옮긴다.
그러고 나서 첫 번째 카드를 책상 위에 올려놓는다.
그런데 그 카드는 1이 되어야 한다.
그리고 남은 카드 중에서 첫 번째 카드를 가장 뒤로 옮기고, 또 가장 앞에 있는 카드를 가장 뒤로 옮긴다.(2번 반복) 그리고 가장 앞에 있는 카드를 책상 위에 올려놓는다. 그런데 그 카드는 2가 되어야 한다.
또 남은 카드 중에서 첫 번째 카드를 가장 뒤로 옮기고... (3번 반복) 그리고 가장 앞에 있는 카드를 책상위에 올려놓는데 그것은 3이 된다.
또 남은 카드 중에서 첫 번째 카드를 가장 뒤로 옮기고.. (4번 반복) 그리고 가장 앞에 있는 카드를 책상 위에 올려놓는데 그것은 4이다.
위 과정을 계속 반복하여 N번 카드만 남을 때 까지 반복한다.
위와 같은 카드를 하려면 미리 카드의 순서를 알고 있어야 한다.
카드의 개수 N이 주어져 있을 때 위의 마술을 하기 위한 카드의 초기 순서를 구하는 프로그램을 작성하시오.

[입력]
첫 번째 줄에 카드의 개수 N(1 ≤ N ≤ 1,000)이 주어진다.

[출력]
첫 번째 줄부터 N번째 줄까지 차례로 카드의 순서를 출력한다.

[구현방법]
- 역순으로 진행해서 카드를 손에 다시 올리는 식으로 순서를 구해야할 것 같다
- 핵심은 첫번째 카드를 맨 뒤로 보낸 뒤, (두번째에서 첫번째가 된) 첫번째 카드를 내려놓는다는 것
- 결과적으로 책상 위에 내려놓는 것은 번호 순이 된다
- 역순으로 진행하려면? 내려놓은 카드를 맨 앞에 추가한 뒤, 맨 뒤에서 앞으로 N번만큼 보내면 된다
    - N은 현재 '내려놓은 카드 숫자'와 같다
    - 내려놓는 순서가 번호 순이기 때문이다

Q. LinkedList로도 Deque 기능 다 구현 가능한데 왜 Deque를 사용할까??
    - 사용 의도의 명확성과 적절한 도구 선택한다
    - 설계 원칙인 '최소한의 필요한 기능만'을 준수
    - Deque에는 LinkedList처럼 리스트 기능은 필요 없기에 Deque로 구현 (get(), set(), indexOf() 등등은 필요 없음 + add의 중간 삽입 과정도 Deque에선 필요 X)

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> q = new ArrayDeque<>();

        int N = Integer.parseInt(br.readLine());

        q.add(N);
        for (int i = N - 1; 0 < i; i--) {
            // 현재 숫자 덱에 추가
            q.addFirst(i);

            // 맨 뒤에서 앞으로 현재 숫자만큼 반복한다
            for (int repeat = 0; repeat < i; repeat++) {
                q.addFirst(q.pollLast());
            }
        }

        // 결과값 출력
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            sb.append(q.poll()).append(" ");
        }

        System.out.println(sb);
    }
}
