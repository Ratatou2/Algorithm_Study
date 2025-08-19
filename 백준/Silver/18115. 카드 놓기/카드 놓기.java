

/*
[백준]
18115, 카드놓기

[문제파악]
수현이는 카드 기술을 연습하고 있다.
수현이의 손에 들린 카드를 하나씩 내려놓아 바닥에 쌓으려고 한다.
수현이가 쓸 수 있는 기술은 다음 3가지다.

제일 위의 카드 1장을 바닥에 내려놓는다.
위에서 두 번째 카드를 바닥에 내려놓는다. 카드가 2장 이상일 때만 쓸 수 있다.
제일 밑에 있는 카드를 바닥에 내려놓는다. 카드가 2장 이상일 때만 쓸 수 있다.
수현이는 처음에 카드 N장을 들고 있다. 카드에는 1부터 N까지의 정수가 중복되지 않게 적혀 있다.
기술을 N번 사용하여 카드를 다 내려놓았을 때, 놓여 있는 카드들을 확인했더니 위에서부터 순서대로 1, 2, …, N이 적혀 있었다!

놀란 수현이는 처음에 카드가 어떻게 배치되어 있었는지 궁금해졌다. 처음 카드의 상태를 출력하여라.

[입력]
첫 번째 줄에는 N (1 ≤ N ≤ 106)이 주어진다.

두 번째 줄에는 길이가 N인 수열 A가 주어진다.
Ai가 x이면, i번째로 카드를 내려놓을 때 x번 기술을 썼다는 뜻이다.
Ai는 1, 2, 3 중 하나이며, An은 항상 1이다.

[출력]
초기 카드의 상태를 위에서부터 순서대로 출력하여라.

[구현방법]
- 최근에 문제를 풀다보니 인덱스 관리 및 계산 위주의 문제에 약하길래 당분간은 이부분을 보완하려한다
- 특히 인덱스 관련해서 LikedList를 많이 써야하는 덱 문제를 좀 위주로 풀려고 함 (낮은 난이도 노상관)
- 이 분야는 확실히 그림 그리면서 풀어야 잘된다

예시)
5
1 1 1 1 1

답)
5 4 3 2 1

- 이 문제는 예시를 보면 맨 위가 최하단으로 간다 즉, 인덱스로만 보면 맨 밑바닥이 (초기값의) 0번 인덱스, 최상단이 N번 인덱스이다
- 이렇게 되면? 다 쌓은 카드는 늘 위에서부터 1, 2, 3, 4, ... , N이니까 다 쌓인 카드는 위에서부터 N번호를 부여하면 된다
- 그림으로는 쉬운데 말로 쓰려니 어렵군
- 뭔말이냐면 맨 위가 0번 (인덱스), 맨 아래가 N-1번 (인덱스)인 카드를 예시대로 뽑아서 배열이든 LinkedList에 추가한다
- 그 상태에서 순서가 다 정해지고 나면, 배열(or LinkedList)의 첫번쨰 카드부터 N번호부터 1까지 할당하면 원래 카드더미의 순서를 알 수 있게된다

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 카드 내려놓는 순서 계산용 List
        LinkedList<Integer> calculateOrder = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            calculateOrder.add(i);
        }

        int[] originOrder = new int[N];

        // 인덱스는 정답을 맞출 배열의 순서를 의미
        for (int i = 0; i < N; i++) {
            int order = Integer.parseInt(st.nextToken());

            // 'An은 항상 1이다' 라는 조건 덕분에 우리는 카드가 몇장남았는지 체크할 필요가 없다
            switch (order) {
                case 1:
                    // 제일 위의 카드를 바닥에 내려놓음
                    originOrder[i] = calculateOrder.getFirst();
                    calculateOrder.removeFirst();
                    break;
                case 2:
                    // 위에서 두번째 카드를 바닥에 내려둠
                    originOrder[i] = calculateOrder.get(1);
                    calculateOrder.remove(1);
                    break;
                case 3:
                    // 제일 밑에 있는 카드를 내려놓음
                    originOrder[i] = calculateOrder.getLast();
                    calculateOrder.removeLast();
                    break;
            }
        }

        int[] answer = new int[N];
        int numbering = N;
        // 카드가 내려놓아진 순서에 따라 인덱스 순서는 다르지만, 내려둔 순서 그대로 번호를 부여하면 정답을 구할 수 있다
        for (int i = 0; i < N; i++) {
            answer[originOrder[i]] = numbering--;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.println(sb);
    }
}
