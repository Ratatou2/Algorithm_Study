/*
[백준]
13975, 파일합치기 3

[문제파악]
- 소설가인 김대전은 소설을 여러 장(chapter)으로 나누어 쓰는데, 각 장은 각각 다른 파일에 저장하곤 한다.
- 소설의 모든 장을 쓰고 나서는 각 장이 쓰여진 파일을 합쳐서 최종적으로 소설의 완성본이 들어있는 한 개의 파일을 만든다.
- 이 과정에서 두 개의 파일을 합쳐서 하나의 임시파일을 만들고, 이 임시파일이나 원래의 파일을 계속 두 개씩 합쳐서 파일을 합쳐나가고, 최종적으로는 하나의 파일로 합친다.
- 두 개의 파일을 합칠 때 필요한 비용(시간 등)이 두 파일 크기의 합이라고 가정할 때, 최종적인 한 개의 파일을 완성하는데 필요한 비용의 총 합을 계산하시오.
- 예를 들어, C1, C2, C3, C4가 네 개의 장을 수록하고 있는 파일이고, 파일 크기가 각각 40, 30, 30, 50 이라고 하자.
- 이 파일들을 합치는 과정에서, 먼저 C2와 C3를 합쳐서 임시파일 X1을 만든다. 이때 비용 60이 필요하다.
- 그 다음으로 C1과 X1을 합쳐 임시파일 X2를 만들면 비용 100이 필요하다.
- 최종적으로 X2와 C4를 합쳐 최종파일을 만들면 비용 150이 필요하다.
- 따라서, 최종의 한 파일을 만드는데 필요한 비용의 합은 60+100+150=310 이다.
- 다른 방법으로 파일을 합치면 비용을 줄일 수 있다.
- 먼저 C1과 C2를 합쳐 임시파일 Y1을 만들고, C3와 C4를 합쳐 임시파일 Y2를 만들고, 최종적으로 Y1과 Y2를 합쳐 최종파일을 만들 수 있다.
- 이때 필요한 총 비용은 70+80+150=300 이다.
- 소설의 각 장들이 수록되어 있는 파일의 크기가 주어졌을 때, 이 파일들을 하나의 파일로 합칠 때 필요한 최소비용을 계산하는 프로그램을 작성하시오.

[입력]
- 프로그램은 표준 입력에서 입력 데이터를 받는다.
- 프로그램의 입력은 T개의 테스트 데이터로 이루어져 있는데, T는 입력의 맨 첫 줄에 주어진다.
- 각 테스트 데이터는 두 개의 행으로 주어지는데, 첫 행에는 소설을 구성하는 장의 수를 나타내는 양의 정수 K (3 ≤ K ≤ 1,000,000)가 주어진다.
- 두 번째 행에는 1장부터 K장까지 수록한 파일의 크기를 나타내는 양의 정수 K개가 주어진다.
- 파일의 크기는 10,000을 초과하지 않는다.

[출력]
- 프로그램은 표준 출력에 출력한다.
- 각 테스트 데이터마다 정확히 한 행에 출력하는데, 모든 장을 합치는데 필요한 최소비용을 출력한다.

[구현방법]
- 이것의 요지는 두개씩 묶어서 만드는 것
- 특히, 어떻게 더하든 두개끼리 잘 더하면 된다
- 그러니까 PQ로 오름차순으로 짠다음, 앞에서 계속 두개씩 더하고 다시 그 값을 PQ에 넣으면 된다
- 그렇게 누적합도 계산하고, 계산하고서는 PQ에 넣다보면 PQ에 하나밖에 남지 않는 순간이 온다
- 그 순간에 이미 누적합에 다 더했기 때문에 누적합 print 하면 끝
- 그러다 불현듯 떠나가는.. totalSum 값이 int를 초과해 터질 수도 있겠다는 생각...?
- 그럴려면 당연히 PQ의 자료형도 Long으로 해줘야겠죠;;

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
//    static void printPQ(PriorityQueue<Integer> pq) {
//        PriorityQueue<Integer> temp = new PriorityQueue<>(pq);
//
//        while (!temp.isEmpty()) {
//            System.out.print(temp.poll() + " ");
//        }
//        System.out.println("\n+=========+");
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Long> pq;

        int T = Integer.parseInt(br.readLine());

        // 테스트 케이스만큼 반복
        for (int test = 0; test < T; test++) {
            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            pq = new PriorityQueue<>();

            // pq에 입력
            while (st.hasMoreTokens()) {
                pq.add(Long.parseLong(st.nextToken()));
            }

            // pq에 하나가 남을 때까지, 두개씩 묶어서 덧셈 반복
            long totalSum = 0;
            while (1 < pq.size()) {
                long temp = pq.poll() + pq.poll();

                totalSum += temp;
                pq.add(temp);
            }

            System.out.println(totalSum);
        }
    }
}
