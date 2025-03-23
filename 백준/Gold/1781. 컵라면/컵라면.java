/*
[백준]
1781, 컵라면

[문제파악]
- 상욱 조교는 동호에게 N개의 문제를 주고서, 각각의 문제를 풀었을 때 컵라면을 몇 개 줄 것인지 제시 하였다.
- 하지만 동호의 찌를듯한 자신감에 소심한 상욱 조교는 각각의 문제에 대해 데드라인을 정하였다.
-     문제 번호	1	2	3	4	5	6	7
-     데드라인	1	1	3	3	2	2	6
-     컵라면 수	6	7	2	1	4	5	1
- 위와 같은 상황에서 동호가 2, 6, 3, 1, 7, 5, 4 순으로 숙제를 한다면 2, 6, 3, 7번 문제를 시간 내에 풀어 총 15개의 컵라면을 받을 수 있다.
- 문제는 동호가 받을 수 있는 최대 컵라면 수를 구하는 것이다.
- 위의 예에서는 15가 최대이다.
- 문제를 푸는데는 단위 시간 1이 걸리며, 각 문제의 데드라인은 N이하의 자연수이다.
- 또, 각 문제를 풀 때 받을 수 있는 컵라면 수와 최대로 받을 수 있는 컵라면 수는 모두 231보다 작은 자연수이다.

[입력]
- 첫 줄에 숙제의 개수 N (1 ≤ N ≤ 200,000)이 들어온다.
- 다음 줄부터 N+1번째 줄까지 i+1번째 줄에 i번째 문제에 대한 데드라인과 풀면 받을 수 있는 컵라면 수가 공백으로 구분되어 입력된다.

[출력]
- 첫 줄에 동호가 받을 수 있는 최대 컵라면 수를 출력한다.

[구현방법]
- 이것도 마지막날에서부터 풀 수 있는 것들을 Q에 넣으며넛 항상 최대 이득만 풀면 된다

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
    static class Problem implements Comparable<Problem> {
        int deadline, ramen;

        Problem(int deadline, int ramen) {
            this.deadline = deadline;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.deadline == o.deadline) return o.ramen - this.ramen;
            return o.deadline - this.deadline;
        }

        @Override
        public String toString() {
            return "현 문제 " + deadline + "일 남음, 리워드는 " + ramen + "개";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Problem[] problems = new Problem[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            problems[i] = new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(problems);

        int probIndex = 0;
        int totalRamen = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        // 가장 멀리 있는 데드라인에서부터 하루씩 차감하며 for문 진행
        for (int day = problems[0].deadline; 0 < day; day--) {
            while (probIndex < N) {
                // 오늘 날짜가, 현재 문제의 데드라인보다 작거나 같으면, 즉 풀 수 있으면
                if (day <= problems[probIndex].deadline) {
                    pq.add(problems[probIndex].ramen);  // PQ에 보상 라면 갯수를 넣고
                    probIndex++;  // 다음 문제로 이동
                } else break;  // 아니면 break (if문을 타면 계속 오늘 날짜로 확인할 수 있기에 else로 break를 두면 된다
            }

            // 만약 PQ가 비어있지 않으면, 오늘 문제를 하나 푼셈치고 보상을 받아가면 된다
            if (!pq.isEmpty()) {
                totalRamen += pq.poll();
            }
        }

        System.out.println(totalRamen);
    }
}
