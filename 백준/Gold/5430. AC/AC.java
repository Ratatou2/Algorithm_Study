/*
[백준]
5420, AC

[문제파악]
- 선영이는 주말에 할 일이 없어서 새로운 언어 AC를 만들었다.
- AC는 정수 배열에 연산을 하기 위해 만든 언어이다.
- 이 언어에는 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.
- 함수 R은 배열에 있는 수의 순서를 뒤집는 함수이고, D는 첫 번째 수를 버리는 함수이다.
- 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
- 함수는 조합해서 한 번에 사용할 수 있다.
- 예를 들어, "AB"는 A를 수행한 다음에 바로 이어서 B를 수행하는 함수이다.
- 예를 들어, "RDD"는 배열을 뒤집은 다음 처음 두 수를 버리는 함수이다.
- 배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 테스트 케이스의 개수 T가 주어진다. (T는 최대 100)
- 각 테스트 케이스의 첫째 줄에는 수행할 함수 p가 주어진다.
- p의 길이는 1보다 크거나 같고, 100,000보다 작거나 같다.
- 다음 줄에는 배열에 들어있는 수의 개수 n이 주어진다. (0 ≤ n ≤ 100,000)
- 다음 줄에는 [x1,...,xn]과 같은 형태로 배열에 들어있는 정수가 주어진다. (1 ≤ xi ≤ 100)
- 전체 테스트 케이스에 주어지는 p의 길이의 합과 n의 합은 70만을 넘지 않는다.

[출력]
- 각 테스트 케이스에 대해서, 입력으로 주어진 정수 배열에 함수를 수행한 결과를 출력한다.
- 만약, 에러가 발생한 경우에는 error를 출력한다.

[구현방법]
- 음 어차피 D는 맨 끝을 버리는 기능이라면 LinkedList를 사용하면 양 끝단만 날리는게 O(1)에 가능하다
- 더군다나 R은 그저 앞을 날릴 것인지 뒤를 날릴 것인지 방향만 정해줄 뿐 굳이 다 뒤집을 필요 없다 (이 부분에서 시간만 잡아먹으니까)
- 생각한대로 금방 짜긴했는데 이렇게 지저분한게 맞...나...?
- 시간초과... 아니 근데 다른거 참고해보면 Deque로 풀던데 왜 틀리지? LinkedList도 양 끝에선 O(1)인데..?
- 찾아보니 아무래도 reverse 할 때 터지나보다.. 이거 그냥 앞이든, 끝이든 하나씩 쭉쭉 빼면서 sb에 추가해주는 방식으로 수정해야겠다

[보완점]
- reverse에서 시간초과 터지는게 맞았고, 그 다음 문제는 빈 배열이어도 []를 표기해줘야하는데 그걸 error라고 뱉고 있었음.
- charAt()으로 하는건 숫자가 항상 한자리일 때만 충족되는 조건이잖아... 진짜 생각 너무 짧았다
- 방법은 맨 처음과 맨 마지막을 잘라내고 split(",")을 사용해서 쪼갠 뒤 숫자만을 넣어주는 것!
- 또한 숫자가 딱 한개만 있으면 , 찍히지 않도록 조건을 잘 걸어줘야 한다
- 쉬워보인다 켔더니 고려할 것이 겁나 많네 ㅠ
- 그리고 출력하는 곳도 좀 신경써야 함... 몇 개 출력해야지!가 아니고 비울 때까지 출력해야하면 그냥 while문 쓰는 것도..
- 아직 적재적소에 뭘 써야하는지 감이 안잡힌듯 ㅠ

4
RDD
4
[1,2,3,4]
DD
1
[42]
RRD
6
[1,1,2,3,5,8]
D
0
[]


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String order = br.readLine();
            int N = Integer.parseInt(br.readLine());
            LinkedList<String> queue = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine(),"[],");  // 이런 식으로 구분자를 기준으로 쪼갠 값을 StringTokenizer에 넣어둘 수도 있음!

            for(int i = 0; i < N; i++) {
                queue.add(st.nextToken());
            }

            boolean isForward = true;
            boolean isNull = false;
            for (int i = 0; i < order.length(); i++) {
                char currentChar = order.charAt(i);

                // R이면 방향 뒤집기
                if (currentChar == 'R') {
                    isForward = !isForward;
                } else if (currentChar == 'D') {
                    if (queue.isEmpty()) {
                        isNull = true;
                        break;
                    }

                    if (isForward) queue.pollFirst();  // 정방향 : 앞에서부터 꺼내기
                    else queue.pollLast();  // 역방향 : 뒤에서부터 꺼내기
                }
            }

            if (isNull) sb.append("error\n");
            else {
                sb.append("[");
                // for문으로 해줄게 아니라 그냥 다 비어버릴 때까지 반복하면 된다
                while (!queue.isEmpty()) {
                    sb.append(isForward ? queue.pollFirst() : queue.pollLast());  // 정방향이면 첫번째에서, 역방향이면 가장 마지막에서 가져오면 된다
                    if (!queue.isEmpty()) sb.append(",");  // 온점 찍기
                }

                sb.append("]\n");
            }
        }

        System.out.println(sb);
    }
}