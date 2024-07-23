/*
[백준]
4158, CD

[문제파악]
- 두 친구가 가지고 있는 CD를 동시에 팔려고 한다
- 몇개나 팔 수 있을까?

[입력]
- 입력은 여러개의 테스트 케이스 (아쫌!)
- 첫째줄에 상근이 CD N, 선영이 CD M
	- N과 M은 최대 백만원
- 둘째줄에서 N개 줄에는 상근이가 가지고 있는 CD의 번호가 오름차순으로 주어짐
- 셋째줄에는 M개 줄에는 선영이가 가지고 있는 CD의 번호가 오름차순으로 주어짐
- CD의 번호는 10억을 넘지 않는 양의 정수
- 입력의 마지막 줄에는 0 0이 주어짐 (대체 왜?)

[출력]
- 두 사람이 동시에 가지고 있는 CD의 갯수를 출력

[구현방법]
- 흠.. 이걸 어떻게 이분탐색한다는거지 Map 쓰는게 젤 간편하지 않나
- 아 CD를 탐색할 때 필요하곘네
- 근데 Map은 기본적으로 탐색에 O(1)이다 안써도 되..지 않을까?
- 아오 뭔 테스트케이스야 진짜 이런 쓸데 없는 곳에 시간낭비하는게 너무 아까움

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Map<Integer, Integer> sangeun = new HashMap<>();
            Map<Integer, Integer> sunyoung  = new HashMap<>();

            if (N == 0 && M == 0) break;

            int count = 0;

            for (int i = 0; i < N; i++) {
                int tempCD = Integer.parseInt(br.readLine());
                sangeun.put(tempCD, 1);
            }

            for (int i = 0; i < M; i++) {
                int tempCD = Integer.parseInt(br.readLine());
                sunyoung.put(tempCD, 1);
            }


            for (int key : sangeun.keySet()) {
                if (sunyoung.get(key) != null) count++;
            }

            System.out.println(count);
        }
    }
}