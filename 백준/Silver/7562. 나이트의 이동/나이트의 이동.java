/*
[백준]
7562, 나이트의 이동

[문제파악]
- 체스판 위에 한 나이트가 놓여져 있다.
- 나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다.
- 나이트가 이동하려고 하는 칸이 주어진다.
- 나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?

[입력]
- 입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.
- 각 테스트 케이스는 세 줄로 이루어져 있다.
- 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다.
- 체스판의 크기는 l × l이다. 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다.
- 둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.

[출력]
- 각 테스트 케이스마다 나이트가 최소 몇 번만에 이동할 수 있는지 출력한다.

[구현방법]
- 사방 탐색을 잘 짜면 될 문제
- 이동 가능한 경우의 수를 탐색하며 BFS에 넣고 돌리다가, 목적지에 도착하는 순간 return 해버리면 될듯하다

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int I;
    static int[] move_X = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] move_Y = {1, 2, 2 ,1, -1, -2, -2 ,-1};
    static boolean[][] isVisited;

    static class Node {
        int x, y, count;

        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public String toString() {
            return "현재 위치 : (" + x + ", " + y + ")";
        }
    }

    // 도착했는지 체크
    static boolean isArrive(int kngiht_x, int kngiht_y, Node dest) {
        return kngiht_x == dest.x && kngiht_y == dest.y;
    }

    // map 범위 밖으로 나갔는지 체크
    static boolean isOutOfBound(int x, int y) {
        return x < 0 || I <= x || y < 0 || I <= y;
    }

    // BFS 탐색
    static int BFS(Node knight, Node dest) {
        Queue<Node> q = new ArrayDeque<>();  // 잦은 (처음 or 끝) 삽입, 삭제는 ArrayDeque가 더 효율적, 포인터를 위한 메모리를 안쓰기 때문
        q.add(knight);
        isVisited[knight.x][knight.y] = true;  // 현 위치 방문처리

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 현 위치에서 나이트가 이동 가능한 모든 경로 탐색
            for (int i = 0; i < move_X.length; i++) {
                int next_X = cur.x + move_X[i];
                int next_Y = cur.y + move_Y[i];

                // 맵 범위 밖을 벗어났거나, 방문했던 곳이라면 return
                if (isOutOfBound(next_X, next_Y) || isVisited[next_X][next_Y]) continue;
                // 도착했으면 결과값 return
                if (isArrive(next_X, next_Y, dest)) return cur.count + 1;

                // 도착한게 아니라면 q에 넣고 방문처리
                q.add(new Node(next_X, next_Y, cur.count + 1));
                isVisited[next_X][next_Y] = true;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 갯수

        // 테스트 케이스 시작
        for (int test = 1; test <= T; test++) {
            I = Integer.parseInt(br.readLine());  // 체스판 한 변의 길이
            isVisited = new boolean[I][I];

            // 나이트 좌표 입력받기
            st = new StringTokenizer(br.readLine());
            Node knight = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

            // 도착지 좌표 입력받기
            st = new StringTokenizer(br.readLine());
            Node dest = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

            // 시작부터 도착해있으면 더 볼 것 없
            if (isArrive(knight.x, knight.y, dest)) {
                sb.append("0" + "\n");
                continue;
            }

            sb.append(BFS(knight, dest)).append("\n");
        }

        System.out.println(sb);
    }
}
