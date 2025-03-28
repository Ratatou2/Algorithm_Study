/*
[백준]
17612, 쇼핑몰

[문제파악]
- 대형 쇼핑몰에서 쇼핑을 마친 N명의 고객들이 계산을 하고 쇼핑몰을 떠나고자 계산대 앞에 줄을 서 있다.
- 각 고객은 커다란 짐수레(cart)에 물건을 담아 계산대로 간다.
- 쇼핑몰에는 아래 그림과 같이 K개의 계산대가 병렬로 배치되어 있다.
- 쇼핑몰 안내원들은 계산대에 온 사람들을 가장 빨리 계산을 마칠 수 있는 계산대로 안내를 한다.
- 안내원은 각 계산대에서 기다리고 있는 사람들이 계산을 하는데 얼마나 걸리는지 이미 알고 있다.
- 안내원이 고객을 계산대로 안내할 때 두 계산대에서 기다려야 할 시간이 같을 경우에는 가장 번호가 작은 계산대로 안내를 한다.
- 즉 3번, 5번 계산대에서 기다릴 시간이 똑같이 15분으로 최소일 경우에는 3번으로 안내를 한다.
- 계산을 마친 고객은 출구를 통하여 쇼핑몰을 완전히 빠져 나간다.
- 만일 계산대에서 계산을 마친 고객의 시간이 동일하다면 출구에 가까운 높은 번호 계산대의 고객부터 먼저 빠져나간다.
- 예를 들어 두 계산대 4번과 10번에서 두 고객이 동시에 계산을 마쳤다면 계산대의 번호가 더 높은 10번 계산대의 고객이 먼저 쇼핑몰을 나간다.
- 물건을 계산하는 데에는 종류에 관계없이 동일하게 1분이 소요된다.
- 즉, 물건이 w개 있는 손님이 계산을 마치는 데에는 정확히 w분이 소요된다.
- 여러분은 계산대로 들어가기 위하여 줄을 서 있는 고객 N명의 정보( 회원번호, 구매한 물건의 수)를 알고 있을 때, 이들이 계산을 하고 쇼핑몰을 빠져나오는 순서를 구해야 한다.
- 계산대로 들어가고 계산대에서 나오는데 걸리는 시간은 없다고 가정한다.

[입력]
- 입력의 첫 줄에는 2개의 정수 N(1 ≤ N ≤ 100,000)과 k(1 ≤ k ≤ 100,000)가 주어진다.
- 다음 줄부터 N개의 줄에 걸쳐 고객 N명의 정보가 줄 맨 앞의 고객부터 맨 뒤 고객까지 순서대로 주어진다.
- i번째 줄은 줄의 앞에서 i번째 고객의 회원번호 idi(1 ≤ idi ≤ 1,000,000)와 그가 구입한 물건의 수 wi(1 ≤ wi ≤ 20)로 이루어져 있다.
- N명의 회원번호는 모두 다르다.

[출력]
- 고객 N명의 회원번호를 쇼핑몰을 빠져나가는 순서대로 r1, r2, ..., rN이라 할 때, 1×r1 + 2×r2 + ... + N×rN의 값을 출력한다.
- 출력값이 int 범위를 넘어갈 수 있음에 유의하라.

[구현방법]
- 기다릴 시간이 똑같으면 작은 숫자의 계산대부터 배치
- 걸리는 시간이 똑같으면 높은 숫자의 계산대부터 퇴장
- 일단 최단 시간에 배치하는 것은 PQ 한개로 해결 가능
- 근데 뒷부분으 어떻게... 무언가 갱신해주면서 O(1)짜리 자료 셋이 필요할듯하다
- 역시 배열로 인덱스...? 싶다가도, 결국 누가 최소인지 찾으려면 정렬하거나, 완탐하거나
- 둘다 비효율.. 처음부터 정렬이 되어있어야 한다
- 그러면 Map을 인덱스로 쓸까도 싶었지만, 그러면? 하나씩 밀리는 값에 대한건 따로 업데이트가 안됨
- 결국 TreeSet..? 아니면 얘도 PQ로 하고 Comparator로 정렬 규칙을 두개를 따로 만드는...

- 그럼 일단 로직 순서는
- PQ를 하나 만들고 거기에는 계산대 순서만큼 일단 끝나는 시간을 밀어넣는다
- 그러고 다음 제일 먼저 끝나는 곳은 빼면서 다음 친구를 밀어넣는다
    - 이때, 그 다음 PQ 인자를 체크(peek)하면서 현재 끝나는 시간과 같다면, 더 나중에 나온 놈이 먼저 나가야하니 따로 저장한다
        - 이때도 현재 끝나는 시간과 같다면 같지 않을 때까지 peek하며 탐색한다 (PQ 하나 따로 만들어서 거기에 디밀어야할듯)
- 빼낼 때 따로 정해둔 index와 곱하면서 result에 더하고 index는 증가시킨다
- 내가 놓친 부분, 입력 받으면서 PQ를 진행하면 PQ를 다 비우기 전에 for문이 끝날 수 있다는 것

[보완점]
- 일단 내가 시도한 방법은 예외처리를 하면, 풀리긴하는데 시간 초과가 난다
- 비효율적이란 소리죠 90분도 넘겼기 때문에 다른 풀이를 확인했는데 햐.. 훨씬 더 깔끔한 방법이 있었음.
- 일단 그냥 계산대 전용 PQ와 고객 전용 PQ를 따로 만들면 지속적으로 PQ에서 순서맞춰 꺼내가며 계산할 필요가 없었다
- 무슨 말이냐면 계산대에서 순서 맞추고 빨리 끝나는 곳을 체크하는 PQ를 하나 두고, 끝난 고객을 순서대로 내보내는 PQ를 하나 만든다
- 그리고는 계산대에서 빨리 끝나는 친구만 갈아치우면서, 뺀 애들은 계속 고객 PQ에 넣는다 
- 계산대 PQ는 이미 시간이 같을 경우, 카운터 번호가 더 큰 것으로 정렬해둬서 그냥 계속 poll 하기만 하면 됨
- 빼내면 다음 사람 밀어넣고...
- 이걸 고객 인원 수 만큼만 반복하면 된다
- 쉽지 않네.. 추후에 다시 풀어보자
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 카운터 class
    static class Checkout implements Comparable<Checkout> {
        long finishTime;
        int counterId;

        Checkout(long finishTime, int counterId) {
            this.finishTime = finishTime;
            this.counterId = counterId;
        }

        // 정렬 기준은 끝나는 시간 내림차순 (끝나는 시간이 같을 경우, 계산대 번호가 작은 것부터 배치)
        @Override
        public int compareTo(Checkout o) {
            if (this.finishTime == o.finishTime) return this.counterId - o.counterId;  // 계산대 번호가 작은 것 우선
            return Long.compare(this.finishTime, o.finishTime);
        }
    }

    // 고객 class
    static class Customer implements Comparable<Customer> {
        long finishTime;
        int counterId;
        int id;

        Customer(long finishTime, int counterId, int id) {
            this.finishTime = finishTime;
            this.counterId = counterId;
            this.id = id;
        }

        // 정렬 기준은 끝나는 시간 내림차순 (끝나는 시간이 같을 경우, 계산대 번호가 큰 것부터 퇴장)
        @Override
        public int compareTo(Customer o) {
            if (this.finishTime == o.finishTime) return o.counterId - this.counterId;  // 계산대 번호가 큰 것 우선
            return Long.compare(this.finishTime, o.finishTime);
        }

        @Override
        public String toString() {
            return "끝나는 시간 : " + finishTime + " / " + counterId + " / " + id;
        }
    }

    static void printPQ(PriorityQueue<Customer> pq) {
        PriorityQueue<Customer> q = new PriorityQueue<Customer>(pq);

        for (int i = 0; i < pq.size(); i++) {
            System.out.println(q.poll());
        }
        System.out.println("===============");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 고객 수
        int K = Integer.parseInt(st.nextToken());  // 계산대 수

        PriorityQueue<Checkout> checkoutQ = new PriorityQueue<>();
        PriorityQueue<Customer> customerQ = new PriorityQueue<>();

        // 초기 계산대 설정 (K 갯수만큼 집어넣고 대신, 아직 고객은 배치 X)
        for (int i = 1; i <= K; i++) {
              checkoutQ.offer(new Checkout(0, i));
        }

        // 고객 입력 및 처리
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Checkout checkout = checkoutQ.poll();  // 현재 가장 빨리 끝나는 계산대 선택
            long finishTime = checkout.finishTime + w;  // 계산 완료 시간 계산 (현 카운터의 끝날 시간 + 소요될 작업시간)

            customerQ.offer(new Customer(finishTime, checkout.counterId, id));  // 고객 우선순위 큐에 삽입
            checkoutQ.offer(new Checkout(finishTime, checkout.counterId));  // 계산대 다시 큐에 삽입
        }

        // 쇼핑몰을 빠져나가는 순서대로 처리 (int로 하면 터질 수 있다)
        long result = 0;
        for (int i = 1; i <= N; i++) {
            result += (long) i * customerQ.poll().id;
        }

        System.out.println(result);
    }
}
