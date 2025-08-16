

/*
[백준]
1021, 회전하는 큐

[문제파악]
지민이는 N개의 원소를 포함하고 있는 양방향 순환 큐를 가지고 있다.
지민이는 이 큐에서 몇 개의 원소를 뽑아내려고 한다.

지민이는 이 큐에서 다음과 같은 3가지 연산을 수행할 수 있다.

첫 번째 원소를 뽑아낸다.
이 연산을 수행하면, 원래 큐의 원소가 a1, ..., ak이었던 것이 a2, ..., ak와 같이 된다.
왼쪽으로 한 칸 이동시킨다.
이 연산을 수행하면, a1, ..., ak가 a2, ..., ak, a1이 된다.
오른쪽으로 한 칸 이동시킨다.
이 연산을 수행하면, a1, ..., ak가 ak, a1, ..., ak-1이 된다.
큐에 처음에 포함되어 있던 수 N이 주어진다.
그리고 지민이가 뽑아내려고 하는 원소의 위치가 주어진다. (이 위치는 가장 처음 큐에서의 위치이다.)
이때, 그 원소를 주어진 순서대로 뽑아내는데 드는 2번, 3번 연산의 최솟값을 출력하는 프로그램을 작성하시오.

[입력]
첫째 줄에 큐의 크기 N과 뽑아내려고 하는 수의 개수 M이 주어진다.
N은 50보다 작거나 같은 자연수이고,
M은 N보다 작거나 같은 자연수이다.
둘째 줄에는 지민이가 뽑아내려고 하는 수의 위치가 순서대로 주어진다.
위치는 1보다 크거나 같고, N보다 작거나 같은 자연수이다.

[출력]
첫째 줄에 문제의 정답을 출력한다.

[구현방법]
- 이건 deque 써서 앞뒤로 뽑는건 쉬울텐데, 숫자가 목록에 없으면 버리고 있으면 다시 집어넣고의 반복인가? 앞뒤로 어느쪽으로 넣는게 더 효율적이줄 알고...?
- 그리고 오늘도 대단히 불친절한 설명... 대체 '그 원소를 주어진 순서대로 뽑아내는데 드는 2번, 3번 연산의 최솟값을 출력하는 프로그램을 작성하시오.'..? 뭔소리
    - 2번 연산과 3번 연산을 사용한 최소횟수를 출력하라는 의미였음...
- 아 근데 이거 구현하려고보니 deque로 하면 for문을 돌면서 뽑아낼 숫자의 인덱스를 찾아야한다.. 너무 비효율적
- LinkedList로 해야 편리할듯하다
- 문제 똑바로 읽자 좀... (뽑아서 제거는 '항상' 첫번째만 가능하다...)
[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int moveCount = 0;

        // N까지 필요한 숫자 넣어두기
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            deque.add(i);
        }

        // 뽑아낼 숫자들
        int[] target = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        // 어디에 위치해있는지는 어떻게 알지...?
        for (int num : target) {
            int location = deque.indexOf(num);

            // 뽑아낼 숫자가 덱의 절반 크기보다 크면? 뒤쪽에서 뽑아내며 돌리는게 더 효율적
            if ((deque.size() / 2) < location) {
                while (true) {
                    int back = deque.getLast();
                    deque.removeLast();
                    moveCount++;  // 뒤에서 뽑아내는건 정답으로 처리되는 경우의 수가 없으니까 총횟수에서 바로 +1

                    // 우리에게 필요한걸 찾아서 맨 앞으로 보냈으면, 중단
                    if (back == num) break;
                    
                    deque.addFirst(back);
                }
            } else {
                while (true) {
                    int front = deque.getFirst();
                    deque.removeFirst();

                    if (front == num) break;

                    deque.addLast(front);
                    moveCount++;
                }
            }
        }

        System.out.println(moveCount);
    }
}
