/*
[백준]
13904, 과제

[문제파악]
- 웅찬이는 과제가 많다.
- 하루에 한 과제를 끝낼 수 있는데, 과제마다 마감일이 있으므로 모든 과제를 끝내지 못할 수도 있다.
- 과제마다 끝냈을 때 얻을 수 있는 점수가 있는데, 마감일이 지난 과제는 점수를 받을 수 없다.
- 웅찬이는 가장 점수를 많이 받을 수 있도록 과제를 수행하고 싶다.
- 웅찬이를 도와 얻을 수 있는 점수의 최댓값을 구하시오.

[입력]
- 첫 줄에 정수 N (1 ≤ N ≤ 1,000)이 주어진다.
- 다음 줄부터 N개의 줄에는 각각 두 정수 d (1 ≤ d ≤ 1,000)와 w (1 ≤ w ≤ 100)가 주어진다.
- d는 과제 마감일까지 남은 일수를 의미하며, w는 과제의 점수를 의미한다.

[출력]
- 얻을 수 있는 점수의 최댓값을 출력한다.

[구현방법]
- 이거 그냥, 데드라인 짧은 순, 데드라인 같을 땐 점수 높은 순으로 정렬
- 그 뒤 하루 하나씩 제일 기간 짧게 남은 것 중 가장 높은 점수를 Q 텅~ 빌 때까지 반복하면 될듯
- 아냐 그러면 안돼 이거 간질간질하다 알 것 같은데..
- 아 이거다 끝에서부터 삽입하는거임
- 끝 날짜부터 역순으로 탐색하는거지 7일이면 7일부터 1일까지 역순으로 탐색하며 가장 높은 score를 get하면 된다
- 이 말인 즉, 1일 째면 데드라인이 1~7남은 것들 다할 수 있는데 이 중 가장 점수 높은걸 골라서 하면된다
- 이게 되는 이유는 역순부터 탐지 했기 때문에 데드라인이 임박한 것들은 이미 해당 날짜에 다 골라가서 없다
- 1일 남은 과제가 50이고, 4일 남은 과제가 100, 110, 120, 130 이라면 1일차엔 50을 푸는 것보단 데드라인이 4일남은 것들 중 하나를 푸는게 이득이다
- 이걸 알려면 PQ에 역순으로 탐지하며 점수들을 다 집어넣어야함

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
    static class Task implements Comparable<Task>{
        int deadline, score;

        Task(int deadline, int score) {
            this.deadline = deadline;
            this.score = score;
        }

        @Override
        public int compareTo(Task o) {
            if (o.deadline == this.deadline) return o.score - this.score;
            return o.deadline - this.deadline;
        }

        @Override
        public String toString() {
            return "Task [deadline=" + deadline + ", score=" + score + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Task[] tasks = new Task[N + 1];

        StringTokenizer st;
        tasks[0] = new Task(0, 0);
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            tasks[i] = new Task(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(tasks);

        int index = 0;
        int totalScore = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        // 끝 날짜서 부터 역순으로 탐지한다
        for (int i = tasks[0].deadline; 1 <= i; i--) {
            while (true) {
                Task curr = tasks[index];
                if (i != curr.deadline) break;  // 날짜가 다르면 break, 다음 과제 탐색

                pq.add(curr.score);
                index++;
            }

            // PQ가 비어있지 않으면, 가장 큰 것 꺼내서 과제를 수행한다
            if(!pq.isEmpty()) {
                totalScore += pq.poll();
            }
        }

        System.out.println(totalScore);
    }
}