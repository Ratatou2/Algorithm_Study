/*
[백준]
9372, 상근이의 여행

[문제파악]
- 상근이는 겨울방학을 맞아 N개국을 여행하면서 자아를 찾기로 마음먹었다.
- 하지만 상근이는 새로운 비행기를 무서워하기 때문에, 최대한 적은 종류의 비행기를 타고 국가들을 이동하려고 한다.
- 이번 방학 동안의 비행 스케줄이 주어졌을 때, 상근이가 가장 적은 종류의 비행기를 타고 모든 국가들을 여행할 수 있도록 도와주자.
- 상근이가 한 국가에서 다른 국가로 이동할 때 다른 국가를 거쳐 가도(심지어 이미 방문한 국가라도) 된다.

[입력]
- 첫 번째 줄에는 테스트 케이스의 수 T(T ≤ 100)가 주어지고,
- 각 테스트 케이스마다 다음과 같은 정보가 주어진다.
- 첫 번째 줄에는 국가의 수 N(2 ≤ N ≤ 1 000)과 비행기의 종류 M(1 ≤ M ≤ 10 000) 가 주어진다.
- 이후 M개의 줄에 a와 b 쌍들이 입력된다. a와 b를 왕복하는 비행기가 있다는 것을 의미한다. (1 ≤ a, b ≤ n; a ≠ b)
- 주어지는 비행 스케줄은 항상 연결 그래프를 이룬다.

[출력]
- 테스트 케이스마다 한 줄을 출력한다.
- 상근이가 모든 국가를 여행하기 위해 타야 하는 비행기 종류의 최소 개수를 출력한다.

[구현방법]
- 난 뭐 하나씩 건너갈 때마다 +1인가 ... 싶었는데 그게 아니더라
- 건너갈 필요도 없고 탐색할 필요도 없음...
- 그냥 노드와 간선 갯수 물어본거임
- 이해하기 쉬운 문제는 아니었던듯
- 근데 그냥 모든 비행기 종류를 1개로 통일해버리면 되는거 아닌가...
- 모든 국가 넘어갈 때 한개로 써버리면 되는거 아닌가 했음 말이 좀 이상하달까.
- 그러니까 A 비행기가 있다고 하면 모든 나라를 거칠 때 그 비행기만 타면 최소 비행기를 타는 건 아니냔거지
- 문제 취지는 이해하겠지만 이렇게 해도 문제 조건은 만족인데.. 흠...

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            for (int j = 0; j < M; j++) br.readLine();

            sb.append(N - 1).append("\n");
        }

        System.out.println(sb);
    }
}