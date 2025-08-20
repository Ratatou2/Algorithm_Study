

/*
[백준]
28279, 덱 2

[문제파악]
정수를 저장하는 덱을 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.

명령은 총 여덟 가지이다.

1 X: 정수 X를 덱의 앞에 넣는다. (1 ≤ X ≤ 100,000)
2 X: 정수 X를 덱의 뒤에 넣는다. (1 ≤ X ≤ 100,000)
3: 덱에 정수가 있다면 맨 앞의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
4: 덱에 정수가 있다면 맨 뒤의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
5: 덱에 들어있는 정수의 개수를 출력한다.
6: 덱이 비어있으면 1, 아니면 0을 출력한다.
7: 덱에 정수가 있다면 맨 앞의 정수를 출력한다. 없다면 -1을 대신 출력한다.
8: 덱에 정수가 있다면 맨 뒤의 정수를 출력한다. 없다면 -1을 대신 출력한다.

[입력]
첫째 줄에 명령의 수 N이 주어진다. (1 ≤ N ≤ 1,000,000)
둘째 줄부터 N개 줄에 명령이 하나씩 주어진다.
출력을 요구하는 명령은 하나 이상 주어진다.

[출력]
출력을 요구하는 명령이 주어질 때마다 명령의 결과를 한 줄에 하나씩 출력한다.

[구현방법]
- 명령에 따라 분기를 잘 하면 될듯

[보완점]
- 큐가 비어있을 땐 지우는 명령어를 실행하면 안된다
- 예외처리 및 세세한 분기 신경쓸 것
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        LinkedList<String> deque = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "1":
                    deque.addFirst(st.nextToken());
                    break;
                case "2":
                    deque.add(st.nextToken());
                    break;
                case "3":
                    sb.append(deque.isEmpty() ? -1 : deque.removeFirst()).append("\n");
                    break;
                case "4":
                    sb.append(deque.isEmpty() ? -1 : deque.removeLast()).append("\n");
                    break;
                case "5":
                    sb.append(deque.size()).append("\n");
                    break;
                case "6":
                    sb.append(deque.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "7":
                    sb.append(deque.isEmpty() ? -1 : deque.getFirst()).append("\n");
                    break;
                case "8":
                    sb.append(deque.isEmpty() ? -1 : deque.getLast()).append("\n");
                    break;
            }
        }

        System.out.println(sb);
    }
}
