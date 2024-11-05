/*
[백준]
11866, 요세푸스 문제 0

[문제파악]
요세푸스 문제는 다음과 같다.
1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(≤ N)가 주어진다.
이제 순서대로 K번째 사람을 제거한다.
한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다.
이 과정은 N명의 사람이 모두 제거될 때까지 계속된다.
원에서 사람들이 제거되는 순서를 (N, K)-요세푸스 순열이라고 한다.
예를 들어 (7, 3)-요세푸스 순열은 <3, 6, 2, 7, 5, 1, 4>이다.
N과 K가 주어지면 (N, K)-요세푸스 순열을 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 N과 K가 빈 칸을 사이에 두고 순서대로 주어진다. (1 ≤ K ≤ N ≤ 1,000)

[출력]
예제와 같이 요세푸스 순열을 출력한다.

[구현방법]
- 큐로 구하면 될듯하다
- 사실 배열로, index로 해버리는게 제일 빠르겠으나 헷갈리기도 하고, 배열의 끝에 도달하는 순간 처음부터 또 index를 갱신하며 계산해줘야한다
- 오히려 더 복잡하고 헷갈릴 수 있기에 시간은 조금 더 걸릴지 모르겠으나, 큐로 구현하는게 나을듯하다

[보완점]
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<Integer>();

        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        sb.append("<");

        int index = 1;
        while (!q.isEmpty()) {
            int temp = q.poll();

            if (index == K) {
                sb.append(temp);
                sb.append(q.isEmpty() ? "" : ", ");
                index = 1;
            } else {
                q.add(temp);
                index++;
            }
        }

        sb.append(">");

        System.out.println(sb);
    }
}