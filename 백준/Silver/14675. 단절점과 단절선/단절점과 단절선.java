/*
[백준]
14675, 단절점과 단절선

[문제파악]
- 그래프 이론에서 단절점(cut vertex)과 단절선(bridge)은 다음과 같이 정의 된다.
    단절점(cut vertex) : 해당 정점을 제거하였을 때, 그 정점이 포함된 그래프가 2개 이상으로 나뉘는 경우, 이 정점을 단절점이라 한다.
    단절선(bridge) : 해당 간선을 제거하였을 때, 그 간선이 포함된 그래프가 2개 이상으로 나뉘는 경우, 이 간선을 단절선이라 한다.
- 이 단절점과 단절선을 우리는 트리(tree)에서 구하려고 한다.
- 그래프 이론에서 트리(tree)의 정의는 다음과 같다.
    트리(tree) : 사이클이 존재하지 않으며, 모든 정점이 연결되어 있는 그래프
- 트리의 정보와 질의가 주어질 때, 질의에 대한 답을 하시오.

[입력]
- 프로그램의 입력은 표준 입력으로 받는다.
- 입력의 첫 줄에는 트리의 정점 개수 N이 주어진다. (2 ≤ N ≤ 100,000)
- 트리의 정점은 1번부터 n번까지 존재한다.
- 다음 줄부터 N-1개의 줄에 걸쳐 간선의 정보 a, b가 주어진다.
- 이는 a정점과 b정점이 연결되어 있다는 뜻이며, 입력으로 주어지는 정보는 트리임이 보장된다. (1 ≤ a, b ≤ N)
- 다음 줄에는 질의의 개수 q가 주어진다. (1 ≤ q ≤ 100,000)
- 다음 q줄에는 질의 t k가 주어진다. (1 ≤ t ≤ 2)
- t가 1일 때는 k번 정점이 단절점인지에 대한 질의, t가 2일 때는 입력에서 주어지는 k번째 간선이 단절선인지에 대한 질의이다.
- t가 1일 때는 (1 ≤ k ≤ n)이며, t가 2일 때는 (1 ≤ k ≤ n - 1)이다.

[출력]
- 프로그램의 출력은 표준 출력으로 한다.
- q줄에 대하여 해당 질의에 대한 답을 한다.
- 각각은 개행으로 구분하며, 질의가 맞다면 ‘yes’를, 질의가 틀리면 ‘no’를 출력한다.

[구현방법]
- 일단 인접리스트로 구현하는게 나을듯함
- k가 단절선인지 아닌지 어떻게 알지?로 고민을 좀 많이했는데 트리의 정의는 모든 노드들이 어떻게든 연결되어 있다는 것이다
- 그러니까 어디든간에 하나 끊으면 그 간선 기점으로 트리는 두개가 생긴다
- 이 말인 즉, 현 조건에서 어디든간에 끊으면 단절선인게 확정이란 것이다

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> list = new ArrayList<>();

        // 인접 리스트 초기화
        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        // 간선 입력 받기
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 양방향 입력
            list.get(a).add(b);
            list.get(b).add(a);
        }

        // 질의 시작
        int q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());  // 질의 종류
            int k = Integer.parseInt(st.nextToken());  // k번째 간선 or 정점

            if (t == 1) {  // k가 단절점인지?
                sb.append(2 <= list.get(k).size() ? "yes" : "no").append("\n");
            } else if (t == 2) {  // k가 단절선인지? -> 근데 트리 구조에선 그냥 다 연결되어 있으니까 뭘 자르든 단절선 취급 아닌가?
                sb.append("yes").append("\n");
            }
        }

        System.out.println(sb);
    }
}