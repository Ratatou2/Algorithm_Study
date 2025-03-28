import java.util.*;
import java.io.*;

public class Main {
    static class Checkout implements Comparable<Checkout> {
        long finishTime;
        int counterId;

        Checkout(long finishTime, int counterId) {
            this.finishTime = finishTime;
            this.counterId = counterId;
        }

        @Override
        public int compareTo(Checkout o) {
            if (this.finishTime == o.finishTime) {
                return this.counterId - o.counterId; // 계산대 번호가 작은 것 우선
            }
            return Long.compare(this.finishTime, o.finishTime);
        }
    }

    static class Customer implements Comparable<Customer> {
        long finishTime;
        int counterId;
        int id;

        Customer(long finishTime, int counterId, int id) {
            this.finishTime = finishTime;
            this.counterId = counterId;
            this.id = id;
        }

        @Override
        public int compareTo(Customer o) {
            if (this.finishTime == o.finishTime) {
                return o.counterId - this.counterId; // 계산대 번호가 큰 것 우선
            }
            return Long.compare(this.finishTime, o.finishTime);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 고객 수
        int K = Integer.parseInt(st.nextToken()); // 계산대 수

        PriorityQueue<Checkout> checkoutQueue = new PriorityQueue<>();
        PriorityQueue<Customer> customerQueue = new PriorityQueue<>();

        // 초기 계산대 설정
        for (int i = 1; i <= K; i++) {
            checkoutQueue.offer(new Checkout(0, i));
        }

        // 고객 입력 및 처리
        List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Checkout checkout = checkoutQueue.poll(); // 가장 빨리 끝나는 계산대 선택
            long finishTime = checkout.finishTime + w; // 계산 완료 시간 계산

            Customer customer = new Customer(finishTime, checkout.counterId, id);
            customerQueue.offer(customer); // 고객 우선순위 큐에 삽입
            customerList.add(customer);

            // 계산대 다시 큐에 삽입
            checkoutQueue.offer(new Checkout(finishTime, checkout.counterId));
        }

        // 쇼핑몰을 빠져나가는 순서대로 처리
        long result = 0;
        for (int i = 1; i <= N; i++) {
            Customer customer = customerQueue.poll();
            result += (long) i * customer.id;
        }

        System.out.println(result);
    }
}