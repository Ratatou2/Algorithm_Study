

/*
[백준]
2346, 풍선 터뜨리기

[문제파악]
1번부터 N번까지 N개의 풍선이 원형으로 놓여 있고. i번 풍선의 오른쪽에는 i+1번 풍선이 있고, 왼쪽에는 i-1번 풍선이 있다.
단, 1번 풍선의 왼쪽에 N번 풍선이 있고, N번 풍선의 오른쪽에 1번 풍선이 있다.
각 풍선 안에는 종이가 하나 들어있고, 종이에는 -N보다 크거나 같고, N보다 작거나 같은 정수가 하나 적혀있다.
이 풍선들을 다음과 같은 규칙으로 터뜨린다.
우선, 제일 처음에는 1번 풍선을 터뜨린다.
다음에는 풍선 안에 있는 종이를 꺼내어 그 종이에 적혀있는 값만큼 이동하여 다음 풍선을 터뜨린다.
양수가 적혀 있을 경우에는 오른쪽으로, 음수가 적혀 있을 때는 왼쪽으로 이동한다.
이동할 때에는 이미 터진 풍선은 빼고 이동한다.
예를 들어 다섯 개의 풍선 안에 차례로 3, 2, 1, -3, -1이 적혀 있었다고 하자.
이 경우 3이 적혀 있는 1번 풍선, -3이 적혀 있는 4번 풍선, -1이 적혀 있는 5번 풍선, 1이 적혀 있는 3번 풍선, 2가 적혀 있는 2번 풍선의 순서대로 터지게 된다.

[입력]
첫째 줄에 자연수 N(1 ≤ N ≤ 1,000)이 주어진다.
다음 줄에는 차례로 각 풍선 안의 종이에 적혀 있는 수가 주어진다.
종이에 0은 적혀있지 않다.

[출력]
첫째 줄에 터진 풍선의 번호를 차례로 나열한다.

[구현방법]


[보완점]
- 인덱스 계산이 왤케 헷갈리지...? 단순한건데 너무 어렵게 생각하는 경향이 있는 것 같다
- 그리고 이동좌표, 인덱스 이런식으로 기억해야할 값이 두개고 인덱스가 계속 변화하는 상황이면 자료구조써서 하나로 묶어도 된다.. 왜 자꾸 답답하게 짜는겨
*/


import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 인덱스 순차적으로 입력하기
        List<int[]> deque = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            deque.add(new int[] {i , Integer.parseInt(st.nextToken())});
        }

        int index = 0;  // 첫번째부터 시작
        while (!deque.isEmpty()) {
            int[] curr = deque.get(index);
            int num = curr[0];
            int move = curr[1];

            sb.append(num).append(" ");
            deque.remove(index);

            if (deque.isEmpty()) break;

            if (0 < move) {
                // 양수면 오른쪽으로 이동한다 (제거 후 인덱스가 한칸 앞당겨지므로 -1을 해줌
                index = (index + move - 1) % deque.size();
            } else {
                // 음수면 왼쪽으로 이동한다
                index = (index + move) % deque.size();

                if (index < 0) index += deque.size();
            }
        }

        System.out.println(sb);

    }
}
