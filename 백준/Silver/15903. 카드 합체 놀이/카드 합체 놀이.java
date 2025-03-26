/*
[백준]
15903, 카드 합체 놀이

[문제파악]
- 석환이는 아기다. 아기 석환이는 자연수가 쓰여져있는 카드를 갖고 다양한 놀이를 하며 노는 것을 좋아한다.
- 오늘 아기 석환이는 무슨 놀이를 하고 있을까? 바로 카드 합체 놀이이다!
- 아기 석환이는 자연수가 쓰여진 카드를 n장 갖고 있다.
- 처음에 i번 카드엔 ai가 쓰여있다.
- 카드 합체 놀이는 이 카드들을 합체하며 노는 놀이이다.
- 카드 합체는 다음과 같은 과정으로 이루어진다.
- x번 카드와 y번 카드를 골라 그 두 장에 쓰여진 수를 더한 값을 계산한다. (x ≠ y)
- 계산한 값을 x번 카드와 y번 카드 두 장 모두에 덮어 쓴다.
- 이 카드 합체를 총 m번 하면 놀이가 끝난다. m번의 합체를 모두 끝낸 뒤, n장의 카드에 쓰여있는 수를 모두 더한 값이 이 놀이의 점수가 된다. 이 점수를 가장 작게 만드는 것이 놀이의 목표이다.
- 아기 석환이는 수학을 좋아하긴 하지만, 아직 아기이기 때문에 점수를 얼마나 작게 만들 수 있는지를 알 수는 없었다(어른 석환이는 당연히 쉽게 알 수 있다).
- 그래서 문제 해결 능력이 뛰어난 여러분에게 도움을 요청했다.
- 만들 수 있는 가장 작은 점수를 계산하는 프로그램을 만들어보자.

[입력]
- 첫 번째 줄에 카드의 개수를 나타내는 수 n(2 ≤ n ≤ 1,000)과 카드 합체를 몇 번 하는지를 나타내는 수 m(0 ≤ m ≤ 15×n)이 주어진다.
- 두 번째 줄에 맨 처음 카드의 상태를 나타내는 n개의 자연수 a1, a2, …, an이 공백으로 구분되어 주어진다. (1 ≤ ai ≤ 1,000,000)

[출력]
- 첫 번째 줄에 만들 수 있는 가장 작은 점수를 출력한다.

[구현방법]
- 일단 수학적으로 접근했을 때 작은 것들끼리 더하는 것이 효율적이다 (더한 값 모두가 두장에 복사되기 때문)
- 1 2 3 4 100이 있을 때, 5번 한다고 가정하자
    - 작은 것끼리 더하는 순서로 처리하면 138
    - 큰 것끼리 더하면 처음부터 104가 두개가 나오니까 압도적으로 큰 숫자가 나온다
- 그러니 PQ에 다 넣고, 앞에서 두개 poll 한다음, 두개를 더한 값을 PQ에 두번 넣으면 된다
- 이러면 자동으로 작은 숫자로 정렬되니 최솟값으로 계산할 수 있다

[보완점]
- 문제 조건 잘 보자. 골드에 근접한 문제부터는 문제 조건을 잘 보는 습관을 길러야한다
- int형으로 받으면 터질 수준이다 (덧셈 연산을 반복하며 int를 초과할 수 있다)
    - ai 값은 최대 1,000,000이고, 카드 개수 n은 최대 1,000개.
    - 최악의 경우, n=1000, m=15000일 때, 계속해서 두 개의 작은 수를 더하고 복사하는 연산을 반복하면 숫자가 기하급수적으로 커짐.
    - 예를 들어, 처음에 가장 작은 두 숫자가 1,000,000이라고 가정하면:
        1,000,000 + 1,000,000 = 2,000,000
        2,000,000 + 2,000,000 = 4,000,000
        4,000,000 + 4,000,000 = 8,000,000
- 그리고 X != Y라는 조건이 있으니 이대로 내면 터지겠죠?
- 어!! 안터졌다;; 이러면 안되는데
- 아, 카드가 같지 말라는 것이지 거기에 쓰여있는 숫자가 같지 말란 얘기는 없었네. 이래서 문제를 잘 읽어봐야한다 ㅠ
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 카드 갯수
        int M = Integer.parseInt(st.nextToken());  // 카드 합체 횟수

        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()) {
            pq.add(Long.parseLong(st.nextToken()));
        }

        // 카드 합체
        for (int i = 0; i < M; i++) {
            long temp = pq.poll() + pq.poll();
            pq.add(temp);
            pq.add(temp);
        }

        long sum = 0;
        // PQ에 있는 것들 전부 덧셈
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }

        System.out.println(sum);
    }
}