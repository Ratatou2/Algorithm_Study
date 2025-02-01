/*
[백준]
11651, 좌표 정렬하기 2

[문제파악]
- 2차원 평면 위의 점 N개가 주어진다.
- 좌표를 y좌표가 증가하는 순으로, y좌표가 같으면 x좌표가 증가하는 순서로 정렬한 다음 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 점의 개수 N (1 ≤ N ≤ 100,000)이 주어진다.
- 둘째 줄부터 N개의 줄에는 i번점의 위치 xi와 yi가 주어진다. (-100,000 ≤ xi, yi ≤ 100,000)
- 좌표는 항상 정수이고, 위치가 같은 두 점은 없다.

[출력]
- 첫째 줄부터 N개의 줄에 점을 정렬한 결과를 출력한다.

[구현방법]
- compareTo를 참고하지 말고 제대로 짜보자..!

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Node o) {
            if (this.y == o.y) return this.x - o.x;
            return this.y - o.y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<Node> nodes = new ArrayList<Node>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            nodes.add(new Node(x,y));
        }

        Collections.sort(nodes);

        for (Node node : nodes) {
            sb.append(node.x).append(" ").append(node.y).append("\n");
        }

        System.out.println(sb);
    }
}