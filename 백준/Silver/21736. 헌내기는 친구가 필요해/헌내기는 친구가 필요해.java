/*
[백준]
21736, 헌내기는 친구가 필요해

[문제파악]
- 2020년에 입학한 헌내기 도연이가 있다.
- 도연이는 비대면 수업 때문에 학교에 가지 못해 학교에 아는 친구가 없었다.
- 드디어 대면 수업을 하게 된 도연이는 어서 캠퍼스 내의 사람들과 친해지고 싶다.

- 도연이가 다니는 대학의 캠퍼스는 N * M 크기이며 캠퍼스에서 이동하는 방법은 벽이 아닌 상하좌우로 이동하는 것이다.
- 단, 캠퍼스의 밖으로 이동할 수는 없다.
- 불쌍한 도연이를 위하여 캠퍼스에서 도연이가 만날 수 있는 사람의 수를 출력하는 프로그램을 작성해보자.

[입력]
- 첫째 줄에는 캠퍼스의 크기를 나타내는 두 정수 N, M이 주어진다.
- 둘째 줄부터 N개의 줄에는 캠퍼스의 정보들이 주어진다.
- O는 빈 공간, X는 벽, I는 도연이, P는 사람이다. I가 한 번만 주어짐이 보장된다.

[출력]
- 첫째 줄에 도연이가 만날 수 있는 사람의 수를 출력한다.
- 단, 아무도 만나지 못한 경우 TT를 출력한다.

[구현방법]
- 갈 수 있는 곳은 모두 방문하면서 P를 찾으면 된다
- 이런 경우는 BFS로 푸는 것이 나음
- 이런 문제 풀 땐, 항상 row, column을 헷갈리면 안됨 (내가 약한 부분)
- Queue에 넣었으면 방문처리 꼭 해야한다 안그러면 무한루프임 (까먹지 말 것!!)

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 시계 방향으로 이동
    static int[] moveRow = {-1, 0, 1, 0};
    static int[] moveColumn = {0, 1, 0, -1};

    static int N, M, count;
    static String[][] campus;
    static boolean[][] isVisited;

    static class Node {
        int row, col;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static void toString(String[][] input) {
        for (int row = 0; row < input.length; row++) {
            System.out.println(Arrays.toString(input[row]));
        }
    }

    static void BFS(int row, int col) {
        Queue<Node> q = new LinkedList<Node>();
        
        q.add(new Node(row, col));
        isVisited[row][col] = true;  // 현재 도연이 자리도 방문처리

        while(!q.isEmpty()) {
            Node cur = q.poll();

            // 사람을 만났으면, 카운트
            if (campus[cur.row][cur.col].equals("P")) count++;

            // 현재 이동 가능은 상하좌우 4방 탐색
            for (int i = 0; i < moveColumn.length; i++) {
                int nextRow = cur.row + moveColumn[i];
                int nextCol = cur.col + moveRow[i];

                // 범위 밖으로 나갔거나, 방문했던 곳이거나, 벽이면 못간다
                if (isOutOfBound(nextRow, nextCol)
                        || isVisited[nextRow][nextCol]
                        || campus[nextRow][nextCol].equals("X")) continue;

                q.add(new Node(nextRow, nextCol));
                isVisited[nextRow][nextCol] = true;  // Q에 넣은 곳은 방문처리
            }
        }
    }
    
    static boolean isOutOfBound(int row, int col) {
        return row < 0 || N <= row|| col < 0 || M <= col;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // row (배열)
        M = Integer.parseInt(st.nextToken());  // column (요소 하나하나)

        campus = new String[N][M];
        isVisited = new boolean[N][M];

        Node DoYeon = new Node(-1, -1);

        for (int row = 0; row < N; row++) {
            String input = br.readLine();

            for (int col = 0; col < M; col++) {
                campus[row][col] = input.charAt(col) + "";
                
                // 도연이 좌표 기록
                if (campus[row][col].equals("I")) {
                    DoYeon.row = row;
                    DoYeon.col = col;
                }
            }
        }

        // toString(campus);

        // 도연이 좌표부터 BFS 시작
        BFS(DoYeon.row, DoYeon.col);

        System.out.println(count == 0 ? "TT" : count);
    }
}