/*
[백준]
1966, 프린터큐

[문제파악]
- 여러분도 알다시피 여러분의 프린터 기기는 여러분이 인쇄하고자 하는 문서를 인쇄 명령을 받은 ‘순서대로’, 즉 먼저 요청된 것을 먼저 인쇄한다.
- 여러 개의 문서가 쌓인다면 Queue 자료구조에 쌓여서 FIFO - First In First Out - 에 따라 인쇄가 되게 된다.
- 하지만 상근이는 새로운 프린터기 내부 소프트웨어를 개발하였는데, 이 프린터기는 다음과 같은 조건에 따라 인쇄를 하게 된다.

- 현재 Queue의 가장 앞에 있는 문서의 ‘중요도’를 확인한다.
- 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면, 이 문서를 인쇄하지 않고 Queue의 가장 뒤에 재배치 한다.
- 그렇지 않다면 바로 인쇄를 한다.
- 예를 들어 Queue에 4개의 문서(A B C D)가 있고, 중요도가 2 1 4 3 라면 C를 인쇄하고, 다음으로 D를 인쇄하고 A, B를 인쇄하게 된다.

- 여러분이 할 일은, 현재 Queue에 있는 문서의 수와 중요도가 주어졌을 때, 어떤 한 문서가 몇 번째로 인쇄되는지 알아내는 것이다.
- 예를 들어 위의 예에서 C문서는 1번째로, A문서는 3번째로 인쇄되게 된다.

[입력]
- 첫 줄에 테스트케이스의 수가 주어진다.
- 각 테스트케이스는 두 줄로 이루어져 있다.
- 테스트케이스의 첫 번째 줄에는 문서의 개수 N(1 ≤ N ≤ 100)과, 몇 번째로 인쇄되었는지 궁금한 문서가 현재 Queue에서 몇 번째에 놓여 있는지를 나타내는 정수 M(0 ≤ M < N)이 주어진다.
- 이때 맨 왼쪽은 0번째라고 하자.
- 두 번째 줄에는 N개 문서의 중요도가 차례대로 주어진다.
- 중요도는 1 이상 9 이하의 정수이고, 중요도가 같은 문서가 여러 개 있을 수도 있다.

[출력]
- 각 테스트 케이스에 대해 문서가 몇 번째로 인쇄되는지 출력한다.

[구현방법]
- 일단 Queue를 써야겠지 너무 당연하게도
- 문서 갯수를 알려주니 for문을 써서 [영단어순서] - [N번째]를 세트로 집어넣으면 될 것 같다 (class 하나 만들어서)
- 문제는 전체의 중요도를 어떻게 아느냐는 것이지. Set을 써서 sort()를 한다음 자신보다 더 큰게 있는지를 체크하는 방식으로 해야할까
    - set을 쓸거면 TreeSet을 쓰는게 낫겠지 정렬이 되어있으니까
- 자신보다 큰 것을 어떻게 효율적으로 관리하지...?
- Map으로 몇개인지 체크하는게 베스트일까...?

[보완점]
- 나는 기본적으로 Queue와 Map을 통해서 프린터 순서와 중요도를 관리하려고 했음
- Map에 넣어둔걸 Count하는거지 근데 이 방법은 매번 Map을 for문 돌려서 나보다 더 중요한게 있는지 체크해야함
- 굉장히 비효율적임. 더군다나 count 된 숫자가 0이면 체크하고 넘어가야하고, 0 이상이면 차감도 해줘야함
- PQ를 쓰면 이런 이슈를 한번에 해결할 수 있다. 숫자가 높은 (= 중요한 우선순위)별로 나열될테니까 그런 경우엔 PQ에서 하나를 꺼내고 현재 값보다 큰지 작은지만 체크하면 됨
- PQ에서 꺼낸게 더 크다면 현재 중요도는 그보다 낮다는 뜻이고 그러면 그냥 다시 Q에 삽입하면 됨 PQ에 있는 것 또한 들고 있으면 됨
- 최소한 자기랑 똑같은걸 만날 때까지 이걸 반복한다
- 관리도 편하고 덜 복잡하고 훨-씬 더 효율적인 방법임

3
1 0
5
4 2
1 2 3 4
6 0
1 1 9 1 1 1


*/

import javax.print.Doc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Doc {
        int index;       // 문서의 인덱스
        int importance;  // 문서의 중요도

        Doc(int index, int importance) {
            this.index = index;
            this.importance = importance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // 테스트케이스 수

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());  // 문서 개수
            int M = Integer.parseInt(st.nextToken());  // 목표 문서 인덱스

            // 문서의 중요도를 저장
            st = new StringTokenizer(br.readLine());
            Queue<Doc> printerQueue = new LinkedList<>();  // 프린터 순서를 저장
            PriorityQueue<Integer> maxImportance = new PriorityQueue<>(Collections.reverseOrder());  // 중요도를 관리 (최대값 우선)

            for (int i = 0; i < N; i++) {
                int importance = Integer.parseInt(st.nextToken());
                printerQueue.add(new Doc(i, importance));  // 문서 추가
                maxImportance.add(importance);  // 중요도 추가
            }

            int count = 0;  // 인쇄 순서
            while (!printerQueue.isEmpty()) {
                Doc current = printerQueue.poll();

                // 현재 문서가 가장 높은 중요도인지 확인
                if (current.importance == maxImportance.peek()) {
                    count++;  // 인쇄 순서 증가
                    maxImportance.poll();  // 중요도를 우선순위 큐에서 제거

                    // 목표 문서인지 확인
                    if (current.index == M) {
                        System.out.println(count);
                        break;
                    }
                } else {
                    // 중요도가 높지 않으면 다시 큐의 뒤로 보냄
                    printerQueue.add(current);
                }
            }
        }
    }
}