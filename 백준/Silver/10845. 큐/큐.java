/*
[백준]
10845, 큐

[문제파악]
- 정수를 저장하는 큐를 구현한 다음, 입력으로 주어지는 명령을 처리하시오.
- 명령은 총 여섯 가지이다.
	- push X: 정수 X를 큐에 넣는 연산이다.
	- pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다.
		- 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	- size: 큐에 들어있는 정수의 개수를 출력한다.
	- empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
	- front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	- back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.

[입력]
- 첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다.
- 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다.
- 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다.
- 문제에 나와있지 않은 명령이 주어지는 경우는 없다.

[출력]
- 출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.

[구현방법]
- LinkedList는 맨 앞, 맨 뒤는 삭제가 O(1)이다

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb;

    static StringBuilder addLine() {
        return sb.append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;
        LinkedList<String> queue = new LinkedList<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "push":
                    queue.add(st.nextToken());
                    break;
                case "pop":
                    if (queue.isEmpty()) {
                        sb.append("-1");
                    } else {
                        sb.append(queue.getFirst());
                        queue.removeFirst();
                    }
                    addLine();
                    break;
                case "size":
                    sb.append(queue.size());
                    addLine();
                    break;
                case "empty":
                    sb.append(queue.isEmpty() ? "1" : "0");
                    addLine();
                    break;
                case "front":
                    if (queue.isEmpty()) sb.append("-1");
                    else sb.append(queue.getFirst());
                    addLine();
                    break;
                case "back":
                    if (queue.isEmpty()) sb.append("-1");
                    else sb.append(queue.getLast());
                    addLine();
            }
        }

        System.out.println(sb);
    }
}