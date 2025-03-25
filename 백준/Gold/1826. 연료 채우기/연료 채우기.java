/*
[백준]
1826, 연료채우기

[문제파악]
- 성경이는 트럭을 정글 속에서 운전하다가 트럭의 연료탱크에 갑자기 구멍이 나서 1km를 가는데 1L의 연료가 새 나가게 되었다.
- 이것을 고치기 위해서는 가장 가까운 마을에 가야 한다.
- 그런데 그냥 가다가는 중간에 연료가 다 빠질 수가 있다.
- 다행스럽게도 정글 곳곳에 연료를 채울 수 있는 주유소가 N개 있다.
- 그런데 정글 속에서 중간에 차를 멈추는 행위는 매우 위험한 행위이므로 주유소에서 멈추는 횟수를 최소화 하려 한다.
- 그리고 다행이도 성경이의 트럭은 매우 좋아서 연료 탱크에는 연료의 제한이 없이 많이 충분히 많이 넣을 수 있다고 한다.
- 각각의 주유소의 위치와, 각 주유소에서 얻을 수 있는 연료의 양이 주어져 있을 때, 주유소에서 멈추는 횟수를 구하는 프로그램을 작성하시오.
- 정글은 일직선이고, 성경이의 트럭과 주유소도 모두 일직선 위에 있다.
- 주유소는 모두 성경이의 트럭을 기준으로 오른쪽에 있다.

[입력]
- 첫째 줄에 주유소의 개수 N(1 ≤ N ≤ 10,000)가 주어지고 두 번째 줄부터 N+1번째 줄 까지 주유소의 정보가 주어진다.
- 주유소의 정보는 두개의 정수 a,b로 이루어 져 있는데 a(1 ≤ a ≤ 1,000,000)는 성경이의 시작 위치에서 주유소 까지의 거리, 그리고 b(1 ≤ b ≤ 100)는 그 주유소에서 채울 수 있는 연료의 양을 의미한다.
- 그리고 N+2번째 줄에는 두 정수 L과 P가 주어지는데 L(1 ≤ L ≤ 1,000,000)은 성경이의 위치에서 마을까지의 거리, P(1 ≤ P ≤ 1,000,000)는 트럭에 원래 있던 연료의 양을 의미한다.
- 모든 주유소와 마을은 서로 다른 좌표에 있고, 마을은 모든 주유소보다 오른쪽에 있다.

[출력]
- 첫째 줄에 주유소에서 멈추는 횟수를 출력한다.
- 만약 마을에 도착하지 못할 경우 -1을 출력한다.

[구현방법]
- 생각해보니 그저 많이 넣으면 된다 (기름을 딱 맞추란 소리는 없다)
- 많이 넣고 적게 넣으면 그걸로 해결..
- 그럼 끝에서부터 체크하면 될 것 같다 (정렬 기준은 멀리 있는 순, 거리가 같으면 기름을 많이 넣을 수 있는 순)
- 이케 해도 안되는게 현재 연료도 고려해야해서... 흠.. 일단 규칙만 찾으면 풀겠는데 그걸 몾찾겠음.. 10분만 더 고민해보쟈 ㅠ
- 헉! 이게 아니었다..! 현재 연료로 도달할 수 있는 모든 주유소들을 PQ에 넣어두고 기름 모자랄 때마다 가장 큰 값을 넣으면 됐던 것..!!
- 아니 이렇게 쉽게..? 이건 다시 풀어보자
- 아오 감질나

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
    static class Station implements Comparable<Station>{
        int dist, fuel;

        public Station(int dist, int fuel) {
            this.dist = dist;
            this.fuel = fuel;
        }

        @Override
        public String toString() {
            return "현재 주유소 " + this.dist + " - " + this.fuel;
        }

        @Override
        public int compareTo(Station o) {
            if (this.dist == o.dist) return o.fuel - this.fuel;
            return this.dist - o.dist;
        }
    }

    static String printPQ (PriorityQueue<Station> pq) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Station> q = new PriorityQueue<Station>(pq);

        while (!q.isEmpty()) {
            sb.append(q.poll() + "\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());  // 주유소 갯수
        Station[] stations = new Station[N];

        // 주유소 정보 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            stations[i] = new Station(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(stations);  // 정렬 꼭 해줘야한다

        st = new StringTokenizer(br.readLine());
        int destination = Integer.parseInt(st.nextToken());
        int currFuel = Integer.parseInt(st.nextToken());

        // 현 위치에서 갈 수 있는, 연료가 가장 많은 주유소 순으로 정렬할 PQ
        PriorityQueue<Integer> candidateStation = new PriorityQueue<>(Comparator.reverseOrder());
        int count = 0;
        int index = 0;

        // 현재 연료로 마을 도달할 때까지 반복
        while (currFuel < destination) {
            // 주유소 저장해둔 PQ가 비어있지 않고, 현재 연료로 갈 수 있는 곳에 있을 때, 후보지에 추가함
            while (index < N && stations[index].dist <= currFuel) {
                candidateStation.add(stations[index].fuel);
                index++;
            }


            // 더 이상 들를 곳이 없는데 여전히 도착 못했다면 못간다는 의미다 (= -1)
            if (candidateStation.isEmpty()) {
                System.out.println(-1);
                return;
            }

            // 아직 while문 돌고 있단건 도착 못했다는 의미니까 그냥 하나 들려서 기름 더 넣는다
            currFuel += candidateStation.poll();
            count++;
        }

        System.out.println(count);
    }
}