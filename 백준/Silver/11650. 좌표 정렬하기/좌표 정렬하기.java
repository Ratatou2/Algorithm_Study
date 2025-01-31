/*
[백준]
11650, 좌표 정렬하기

[문제파악]
- 2차원 평면 위의 점 N개가 주어진다.
- 좌표를 x좌표가 증가하는 순으로, x좌표가 같으면 y좌표가 증가하는 순서로 정렬한 다음 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 점의 개수 N (1 ≤ N ≤ 100,000)이 주어진다.
- 둘째 줄부터 N개의 줄에는 i번점의 위치 xi와 yi가 주어진다. (-100,000 ≤ xi, yi ≤ 100,000)
- 좌표는 항상 정수이고, 위치가 같은 두 점은 없다.

[출력]
- 첫째 줄부터 N개의 줄에 점을 정렬한 결과를 출력한다.

[구현방법]
- map 써서 정렬하면 안될까...? 근데 중복 값에 대해서는 처리할 수가 없구나
- 그러면 그냥 class 만들어서 sort하는게 나을듯하다
- 매번 Comparable과 Comparator를 헷갈리고 어버버하는 것은 정리가 필요한 시점이란 것이다
- 어느정도 문제를 풀고 반복하면 외워질법도한데 어째 이리 안 외워지는지 아예 한번 다지고 가야겠단 생각이 들었다;;
- 사실 class를 만들고 Comparable을 구현할줄 알면 굉장히 쉬운 문제이다
- 그래프 탐색 외에 정렬도 더 공부해야할듯 싶다

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int x;
        int y;

         public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if (this.x == o.x) return this.y - o.y;
            return this.x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<Node> nodes = new ArrayList<Node>();

        // 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            nodes.add(new Node(x, y));
        }

        Collections.sort(nodes);  // 정렬하기

        // 결과값 출력하기
        for (Node node : nodes) {
            sb.append(node.x).append(" ").append(node.y).append("\n");
        }

        System.out.println(sb);
    }
}