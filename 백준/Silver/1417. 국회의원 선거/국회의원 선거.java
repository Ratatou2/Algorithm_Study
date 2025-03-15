/*
[백준]
1417, 국회의원 선거

[문제파악]
- 다솜이는 사람의 마음을 읽을 수 있는 기계를 가지고 있다.
- 다솜이는 이 기계를 이용해서 2008년 4월 9일 국회의원 선거를 조작하려고 한다.
- 다솜이의 기계는 각 사람들이 누구를 찍을 지 미리 읽을 수 있다.
- 어떤 사람이 누구를 찍을 지 정했으면, 반드시 선거때 그 사람을 찍는다.
- 현재 형택구에 나온 국회의원 후보는 N명이다.
- 다솜이는 이 기계를 이용해서 그 마을의 주민 M명의 마음을 모두 읽었다.
- 다솜이는 기호 1번이다.
- 다솜이는 사람들의 마음을 읽어서 자신을 찍지 않으려는 사람을 돈으로 매수해서 국회의원에 당선이 되게 하려고 한다.
- 다른 모든 사람의 득표수 보다 많은 득표수를 가질 때, 그 사람이 국회의원에 당선된다.
- 예를 들어서, 마음을 읽은 결과 기호 1번이 5표, 기호 2번이 7표, 기호 3번이 7표 라고 한다면, 다솜이는 2번 후보를 찍으려고 하던 사람 1명과, 3번 후보를 찍으려고 하던 사람 1명을 돈으로 매수하면, 국회의원에 당선이 된다.
- 돈으로 매수한 사람은 반드시 다솜이를 찍는다고 가정한다.
- 다솜이가 매수해야하는 사람의 최솟값을 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 후보의 수 N이 주어진다.
- 둘째 줄부터 차례대로 기호 1번을 찍으려고 하는 사람의 수, 기호 2번을 찍으려고 하는 수, 이렇게 총 N개의 줄에 걸쳐 입력이 들어온다.
- N은 50보다 작거나 같은 자연수이고, 득표수는 100보다 작거나 같은 자연수이다.

[출력]
- 첫째 줄에 다솜이가 매수해야 하는 사람의 최솟값을 출력한다.

[구현방법]
- 1번이 자기 자신이니까 그것을 뺀 나머지는 PQ에 넣고, 가장 큰 수와 두번째로 큰 수를 확인하면 된다
- 이 말인 즉, 가장 큰 수에서 자기 자신에게 회유해서 두번째 큰 수보다 크기만 하면, 국회의원에 당선될 수 있다는 의미임
- 근데 이 로직에는 빈틈이 있는게 예시처럼 5 7 7 들어오면 흠... 첫번째와 두번째 모두에서 1명씩 설득해야하는디...
- 중복제거도 답은 아닌게 예를들어 3 9 9 9 9 9 9 9 9 9 9 가 있다고 치면, 중복제거 하면 답은 6명이다
- 근데 사실 3이 9가 되어도 이미 9는 더 있어서, 10까진 되어야한다 그럼 답은 결국 10명.. 답이 아님
- 지금 생각나는건 걍 자꾸 -1씩 하면서 PQ에 있는거 죄다 갱신하는 것 뿐이군..

[보완점]
- PQ는 꺼낼 때 작은 순으로 꺼내는거지 미리 다 정렬이 되어 있는 것은 아니다 (중요)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());  // 큰 수부터 정렬하기 위한 옵션 추가 (Comparator.reverseOrder())

        int N = Integer.parseInt(br.readLine());
        int dasom = Integer.parseInt(br.readLine());
        int count = 0;

        // 다솜이 뺴고 전부 PQ에 삽입
        for (int i = 0; i < N - 1; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        // PQ에 든게 없을 때까지 반복한다
        while (!pq.isEmpty()) {
            int curr = pq.poll();

            // 다솜이보다 큰 값이면, 한명 회유하고 다시 PQ에 집어넣음
            if (dasom <= curr) {
                dasom++;
                count++;
                pq.add(curr - 1);
            }
        }

        System.out.println(count);
    }
}
