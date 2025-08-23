

/*
[백준]


[문제파악]
한가롭게 방학에 놀고 있던 도현이는 갑자기 재밌는 자료구조를 생각해냈다. 그 자료구조의 이름은 queuestack이다.
queuestack의 구조는 다음과 같다. 1번, 2번, ... , N번의 자료구조(queue 혹은 stack)가 나열되어있으며, 각각의 자료구조에는 한 개의 원소가 들어있다.

queuestack의 작동은 다음과 같다.
x_0을 입력받는다.x_0을 1번 자료구조에 삽입한 뒤 1번 자료구조에서 원소를 pop한다.
그때 pop된 원소를 x_1이라 한다.x_1을 2번 자료구조에 삽입한 뒤 2번 자료구조에서 원소를 pop한다. 그때 pop된 원소를 x_2이라 한다.
...
x_{N-1}을 N번 자료구조에 삽입한 뒤 N번 자료구조에서 원소를 pop한다.
그때 pop된 원소를 x_N이라 한다.x_N을 리턴한다.
도현이는 길이 M의 수열 C를 가져와서 수열의 원소를 앞에서부터 차례대로 queuestack에 삽입할 것이다.
이전에 삽입한 결과는 남아 있다. (예제 1 참고)

queuestack에 넣을 원소들이 주어졌을 때, 해당 원소를 넣은 리턴값을 출력하는 프로그램을 작성해보자.

[입력]
첫째 줄에 queuestack을 구성하는 자료구조의 개수 N이 주어진다. (1 \leq N \leq 100\,000)
둘째 줄에 길이 N의 수열 A가 주어진다. i번 자료구조가 큐라면 A_i = 0, 스택이라면 A_i = 1이다.
셋째 줄에 길이 N의 수열 B가 주어진다. B_i는 i번 자료구조에 들어 있는 원소이다. (1 \leq B_i \leq 1\,000\,000\,000)
넷째 줄에 삽입할 수열의 길이 M이 주어진다. (1 \leq M \leq 100\,000)
다섯째 줄에 queuestack에 삽입할 원소를 담고 있는 길이 M의 수열 C가 주어진다. (1 \leq C_i \leq 1\,000\,000\,000)
입력으로 주어지는 모든 수는 정수이다.

[출력]
수열 의 원소를 차례대로 queuestack에 삽입했을 때의 리턴값을 공백으로 구분하여 출력한다.

[구현방법]
- 난잡하다.. 정리부터 하자
    - 첫번째 줄에 자료 구조 갯수 N이 주어짐
    - 두번째 줄에 각 자료구조의 유형이 주어짐 (0이면 큐, 1이면 스택을 의미)
    - 세번째 줄은 각 자료구조에 들어가 있는 원소
    - 네번째 줄은 여기에 입력할 길이 M이 주어짐
    - 다섯번째 줄은 입력할 수들이 주어짐
- 주어진 수들은 1번부터 입력하면 된다
    - 핵심은 큐는 가장 먼저 들어간게 다음 자료구조로 이동, 스택은 가장 마지막에 들어간게 다음 자료구조로 이동
    - 즉, 큐 뺴고 스택은 그냥 다음으로 들어가야할 숫자 그대로 이동하면 된다

[보완점]
- 위와 같이 정석대로 풀면 시간 초과가 난다 (N * M = 100억 연산...)
- 방법은 스택은 전부 무시하고 전체를 하나의 덱으로 보고 연산하면 되는 것이었다
- 어차피 새로 들어오는건 저장되고, 가장 마지막 큐 자료구조에서 가장 오래된 것이 답이니까
- 즉, 역순으로 덱을 구성해야 함
- 자료구조 순으로
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 초기 수열 형태 인풋 받기
        int N = Integer.parseInt(br.readLine());
        String[] type = br.readLine().split(" ");
        List<String>[] nums = new ArrayList[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 초기값 세팅
        for (int i = 0; i < N; i++) {
            List<String> temp = new ArrayList<>();
            temp.add(st.nextToken());
            nums[i] = temp;
        }

        // 추가할 수열 인풋 받기
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        // 스택이 아니면 deque에 밀어넣는다
        Deque<String> deque = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (type[i].equals("1")) continue;

            deque.add(nums[i].get(0));
        }

        // 추가할 수열 인자 하나씩 추가하기
        for (int i = 0; i < M; i++) {
            String curr = st.nextToken();

            // deque에는 입력 순서대로 존재함 -> 즉, 앞에서 밀어넣고 맨 뒤에 것을 빼면 된다는 의미
            deque.addFirst(curr);

            // 마지막에 꺼내져 있는 친구를 정답에추가
            sb.append(deque.removeLast()).append(" ");
        }

        System.out.println(sb);
    }
}
