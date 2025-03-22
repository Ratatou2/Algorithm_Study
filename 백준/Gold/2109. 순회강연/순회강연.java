/*
[백준]
2109, 순회강연

[문제파악]
- 한 저명한 학자에게 n(0 ≤ n ≤ 10,000)개의 대학에서 강연 요청을 해 왔다.
- 각 대학에서는 d(1 ≤ d ≤ 10,000)일 안에 와서 강연을 해 주면 p(1 ≤ p ≤ 10,000)만큼의 강연료를 지불하겠다고 알려왔다.
- 각 대학에서 제시하는 d와 p값은 서로 다를 수도 있다.
- 이 학자는 이를 바탕으로, 가장 많은 돈을 벌 수 있도록 순회강연을 하려 한다.
- 강연의 특성상, 이 학자는 하루에 최대 한 곳에서만 강연을 할 수 있다.
- 예를 들어 네 대학에서 제시한 p값이 각각 50, 10, 20, 30이고, d값이 차례로 2, 1, 2, 1 이라고 하자.
- 이럴 때에는 첫째 날에 4번 대학에서 강연을 하고, 둘째 날에 1번 대학에서 강연을 하면 80만큼의 돈을 벌 수 있다.

[입력]
- 첫째 줄에 정수 n이 주어진다.
- 다음 n개의 줄에는 각 대학에서 제시한 p값과 d값이 주어진다.

[출력]
- 첫째 줄에 최대로 벌 수 있는 돈을 출력한다.

[구현방법]
- n이 0일때를 고려하라니 너무한거 아니냐고;;

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Lecture implements Comparable<Lecture> {
        int deadline, pay;

        Lecture(int pay, int deadline) {
            this.deadline = deadline;
            this.pay = pay;
        }

        @Override
        public int compareTo(Lecture o) {
            // 기본적으로 데드라인이 많은 남은 순서대로 정렬하되 그중에서도 pay가 높은 순으로 정렬
            if (this.deadline == o.deadline) return o.pay - this.pay;
            return o.deadline - this.deadline;
        }

        @Override
        public String toString() {
            return "현 강의 info -- " + deadline + "일 남음 & " + pay + "\n";
        }
    }

//    static void printPQ(PriorityQueue<Integer> pq) {
//        PriorityQueue<Integer> temp = new PriorityQueue<>(pq);
//        StringBuilder sb = new StringBuilder();
//
//        while (!temp.isEmpty()) {
//            sb.append(temp.poll() + " ");
//        }
//
//        System.out.println(sb);
//        System.out.println("=================");
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Lecture[] lectures = new Lecture[n];

        if (n == 0) {
            System.out.println(0);
            System.exit(0);
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(lectures);
//        System.out.println(Arrays.toString(lectures));

        // 날짜에 맞춰 진행할 수 있는 lecture들을 추가하는 식으로 진행하는게 베스트 케이스 일듯하다
        // 이때 lecture들을 위한 index도 따로 두는게 좋음 그래야 중복 탐색 안함
        int lecIndex = 0;
        int totalPay = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        // 강의할 수 있는, 가장 멀리 남은 날부터 탐색 시작
        for (int i = lectures[0].deadline; 0 < i; i--) {
            // 모든 강의 탐색이 끝나기 전 & 강의의 데드라인이 현재의 날짜보다 더 크거나, 같을 때 (= 최소 오늘까지 강의할 수 있을때)
            while (lecIndex < n && i <= lectures[lecIndex].deadline) {
                pq.add(lectures[lecIndex++].pay);  // 해당 강의는 PQ에 넣고 강의 index 증가시킨다
            }

            // PQ가 비어있지 않으면, 가장 pay가 큰 수업 꺼내서 더한다
            if (!pq.isEmpty()) totalPay += pq.poll();
        }

        System.out.println(totalPay);
    }
}
