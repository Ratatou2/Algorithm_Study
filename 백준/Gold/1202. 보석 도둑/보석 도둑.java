/*
[백준]
1202, 보석도둑

[문제파악]
- 세계적인 도둑 상덕이는 보석점을 털기로 결심했다.
- 상덕이가 털 보석점에는 보석이 총 N개 있다. 각 보석은 무게 Mi와 가격 Vi를 가지고 있다.
- 상덕이는 가방을 K개 가지고 있고, 각 가방에 담을 수 있는 최대 무게는 Ci이다.
- 가방에는 최대 한 개의 보석만 넣을 수 있다.
- 상덕이가 훔칠 수 있는 보석의 최대 가격을 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 N과 K가 주어진다. (1 ≤ N, K ≤ 300,000)
- 다음 N개 줄에는 각 보석의 정보 Mi와 Vi가 주어진다. (0 ≤ Mi, Vi ≤ 1,000,000)
- 다음 K개 줄에는 가방에 담을 수 있는 최대 무게 Ci가 주어진다. (1 ≤ Ci ≤ 100,000,000)
- 모든 숫자는 양의 정수이다.

[출력]
- 첫째 줄에 상덕이가 훔칠 수 있는 보석 가격의 합의 최댓값을 출력한다.

[구현방법]
- 흠 그냥 클래스 하나 만들어서 Comparable 걸어가지구, 값어치 우선순위로 정렬 걸어두면 되는 문제 아닌가..?
- 주의할 점은 보석의 무게를 가벼운 순으로 정렬하면 안되는게 가방도 PQ 역순으로 정렬하면 가장 큰 가방이 가장 무거우면서 가치가 높은 보석을 처리해줘야 더 많이 챙길 수 있다는 것
- 이 말인 즉, (무게, 가치)가 (100, 50), (50, 50)이 있는데 가방이 (담을 수 있는 용량)이 (110, 50)이라면 110 - 50순으로 처리해야 둘다 챙길 수 있다
- 가치가 같을 때, 무게가 가벼운 것부터 정렬하면 110짜리 가방이 50짜리 보석을 담으면 110이 남는데 50짜리 가방은 이를 담을 수 없기 때문임

[보완점]
- 내가 놓친 빈부분이 있었고 이 부분이 있기에 골2 문제였던 것;;;
- 보석 무게와 가치가 엇비슷하면, 그러니까 아래와 같으면 사실상 11을 먼저 담아줘야만 최대치 값을 뽑아낼 수 있다
    이 보석의 무게와 가치는요 : 8 / 55
    이 보석의 무게와 가치는요 : 8 / 55
    이 보석의 무게와 가치는요 : 11 / 54
    이 보석의 무게와 가치는요 : 14 / 20
    이 보석의 무게와 가치는요 : 9 / 15
    이 보석의 무게와 가치는요 : 4 / 10
    이 보석의 무게와 가치는요 : 4 / 9
    이 보석의 무게와 가치는요 : 8 / 5
    이 보석의 무게와 가치는요 : 4 / 5
- 11을 담아도 최대값인 55로와 1차이에다가 첫번째 가방이 가장 클 테니까 거기에 11을 담아놓아야만.. 그래야 가능하다
- 이거는 그러면 내가 봤을 때, 가방 갯수 K만큼 뽑아다가 거기서 상위 무게를 가지고 있는 것부터 쳐내야할 것 같은데..?
    - 근데 이 로직 자체가 비효율적임 분명 내가 놓친 더 효율적인 뭔가가 있을 것임
- 고민하다 제한시간 넘겨서 아이디어만 읽어봤는데, 우선 현재 가방에 추가 가능한 모든 보석을 넣어둔 PQ를 만든다
- 그리고 PQ에 넣는 작업이 끝나면 거기서 제일 큰 값어치를 지닌을 보석을 가져와서 넣는 것이다..
- 이 아이디어에 필요한 조건은 가방을 최소 무게로 정렬하는 것 (그러면 이후에 나오는 가방은 앞서 나온 가방보단 무조건 클테니, 매번 PQ를 새로 만들 필요없이 최대 효율을 가져갈 수 있다)
- 근데 이 방식으로 구현하면 답은 나오지만, 시간 초과...
- 아 반만 이해했네 요지는 한번 체크한 보석은 두번 안하는게 포인트
- 그럼에도 3% 계속 터짐...
- 근데 가만 보면 가방 갯수와 무게 잘 보면 int로는 터질 수 있다
    - 각 가방이 가장 비싼 보석을 하나씩 가져갈 수 있는 경우
    - 즉, 총합의 최댓값은 1,000,000 × 300,000 = 300,000,000,000이다
    - 근데? int 범위값은? −2,147,483,648 ∼ 2,147,483,647 이니까 터진다

    - 결국 모든 값을 더해줄 totalValue 변수는 long으로 선언해야 한다
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Jewel{
        int weight;
        int value;

        Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "이 보석의 무게와 가치는요 : " + weight + " / " + value;
        }
    }

    static void printPQ (PriorityQueue<?> temp) {
        System.out.println("======================");
        while (!temp.isEmpty()) {
            System.out.println(temp.poll());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 보석 갯수
        int K = Integer.parseInt(st.nextToken());  // 가방 갯수

        Jewel[] jewels = new Jewel[N];
        int[] bags = new int[K];

        // 보석 정보 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            jewels[i] = new Jewel(weight, value);
        }

        // 가방에 담을 수 있는 최대 무게 (가방 최대 무게는 100,000,000이지만 가방에는 한개만 담을 수 있음)
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        // 보석이랑 가방 정렬
        // Arrays.sort(jewels, (o1, o2) -> o1.weight - o2.weight);  // 람다 식으로도 대체 가능
        Arrays.sort(jewels, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                return o1.weight - o2.weight;
            }
        });

        Arrays.sort(bags);

        PriorityQueue<Integer> valuePQ = new PriorityQueue<>((o1, o2) -> o2 - o1);  // value 높은 순으로 정렬 (내림차순)
        long totalValue = 0;
        int index = 0;  // 보석 index

        // 작은 가방 부터 비교
        for (int bag : bags) {
            // 보석 index가 보석 갯수보다 작을 때, 보석 무게를 현재 가방에 넣을 수 있으면 큐에 넣는다
            while (index < N && jewels[index].weight <= bag) {
                valuePQ.add(jewels[index].value);
                index++;  // 인덱스를 늘려버림으로써 이전 보석은 이제 더 이상 탐색하지 않는다
                // 이게 가능한 이유는 보석을 무게 순(오름차순, 가장 가벼운 것 -> 가장 무거운 것)으로 정렬했기 때문이다
            }

            // 다 밀어넣었으면 PQ에서 value가 가장 큰 놈 꺼내서 더한다
            if (!valuePQ.isEmpty()) totalValue += valuePQ.poll();
        }

        System.out.println(totalValue);
    }
}