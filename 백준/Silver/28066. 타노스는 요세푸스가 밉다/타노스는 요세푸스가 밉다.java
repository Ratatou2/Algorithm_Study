

/*
[백준]
28066, 타노스는 요세푸스가 밉다

[문제파악]
N마리의 청설모가 1번부터 N번까지 순서대로 시계 방향으로 원을 이루면서 앉아있다.
타노스는 손을 튕겨서 순서대로 두 번째 청설모를 제거해 왔는데, 옆 나라의 수학자 요세푸스도 이미 그 방식을 사용해 왔다는 것을 알자 기분이 상했다.
그래서 타노스는 새롭게 청설모를 제거하는 방식을 고안했다.

시작은 1번 청설모를 첫 번째 청설모로 한다.
타노스가 손을 튕기면 첫 번째 청설모부터 시계 방향으로 K마리의 청설모가 선택된다.
이후 첫 번째 청설모를 제외한 2, \cdots , K번째 청설모가 번호가 증가하는 순서대로 제거되고 첫 번째 청설모만 살아남는다.
단, 남아 있는 청설모가 K마리보다 적으면 첫 번째 청설모를 제외한 모든 청설모가 제거된다.
제거된 후 남아있는 청설모가 2마리 이상일 경우 첫 번째 청설모의 오른쪽 청설모가 첫 번째 청설모가 되고, 제거하는 과정을 다시 진행한다.
이 과정은 청설모가 1마리 남을 때까지 계속된다.
N, K가 주어질 때 마지막으로 남는 청설모의 번호를 구하여라.

[입력]
첫째 줄에 정수 N과 K가 공백을 사이에 두고 주어진다. (2 \leq N, K \leq 10^{6})

[출력]
마지막으로 남는 청설모의 번호를 출력한다.

[구현방법]
- 청설모 삭제 방법
    1) 현재 남아있는 청설모가 K보다 많으면, 첫번째부터 N번까지 선택되고 첫번째를 제외하고 모두 삭제
    2) 현재 남아있는 청설모가 K보다 적거나, 같으면 첫번쨰 제외하고 모두 삭제

[보완점]
- LinkedList에서 인덱스를 이용해 원소를 삭제하는 remove(index) 연산은 리스트를 처음부터 순회해야 하므로 O(N)의 시간이 걸림
    - 이 문제에서는 Queue를 쓰는게 더 효율적이다
    - Queue의 Poll은 O(1) 이기 떄문이다
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int result = 0;
        Queue<Integer> squirrels = new ArrayDeque<>();

        // 청설모 밀어넣기
        for (int i = 1; i <= N; i++) {
            squirrels.add(i);
        }

        while (true) {
            // 남아있는 청설모가 지울 횟수보다 적으면 첫번쨰 청설모 빼고 다 치운다 (= 첫번째 청솔모가 정답)
            if (squirrels.size() < K) {
                result = squirrels.peek();
                break;
            } else {
                // 첫번쨰 청설모는 뽑아서 맨뒤로 넣는다
                squirrels.add(squirrels.poll());

                // 청솔모 K만큼 제거
                for (int i = 1; i < K; i++) {
                    squirrels.poll();
                }
            }
        }

        System.out.println(result);
    }
}
