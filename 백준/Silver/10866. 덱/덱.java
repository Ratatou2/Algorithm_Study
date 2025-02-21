/*
[백준]
10866, 덱

[문제파악]
- 정수를 저장하는 덱(Deque)를 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.
- 명령은 총 여덟 가지이다.
    - push_front X: 정수 X를 덱의 앞에 넣는다.
    - push_back X: 정수 X를 덱의 뒤에 넣는다.
    - pop_front: 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
    - pop_back: 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
    - size: 덱에 들어있는 정수의 개수를 출력한다.
    - empty: 덱이 비어있으면 1을, 아니면 0을 출력한다.
    - front: 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
    - back: 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.

[입력]
- 첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다.
- 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다.
- 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다.
- 문제에 나와있지 않은 명령이 주어지는 경우는 없다.

[출력]
- 출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.

[구현방법]
- 하나씩 구현하면 된다
- 근데 이걸 진짜 직접 구현해보려면 배열로 구현해서 직접 길이를 늘리고 줄이고 새로 만들고 그런식으로 구현해야겠지?
- 나처럼 LinkedList 쓰면 반칙이긴하다
- 이럴거면 deque로 구현하지... 라고 생각할 수 있음 ㅇㅈ

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> deque = new LinkedList<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String action = st.nextToken();
//             X: 정수 X를 덱의 앞에 넣는다.
//                    - push_back X: 정수 X를 덱의 뒤에 넣는다.
//                    - pop_front: 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//            - : 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//            - : 덱에 들어있는 정수의 개수를 출력한다.
//                    - empty: 덱이 비어있으면 1을, 아니면 0을 출력한다.
//            - : 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//            - bac
            switch (action) {
                case "push_front" :
                    deque.add(0, Integer.parseInt(st.nextToken()));
                    break;
                case "push_back" :
                    deque.add(Integer.parseInt(st.nextToken()));
                    break;
                case "pop_front" :
                    sb.append(deque.isEmpty() ? -1 : deque.pollFirst()).append("\n");
                    break;
                case "pop_back" :
                    sb.append(deque.isEmpty() ? -1 : deque.pollLast()).append("\n");
                    break;
                case "size" :
                    sb.append(deque.size()).append("\n");
                    break;
                case "front" :
                    sb.append(deque.isEmpty() ? -1 : deque.peekFirst()).append("\n");
                    break;
                case "back" :
                    sb.append(deque.isEmpty() ? -1 : deque.peekLast()).append("\n");
                    break;
                case "empty" :
                    sb.append(deque.isEmpty() ? 1 : 0).append("\n");
            }
        }

        System.out.println(sb);
    }
}