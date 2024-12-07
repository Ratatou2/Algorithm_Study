/*
[백준]
1987, 알파벳

[문제파악]
- 세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다.
- 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.
- 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다.
- 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
- 좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오.
- 말이 지나는 칸은 좌측 상단의 칸도 포함된다.

[입력]
- 첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다. (1 ≤ R,C ≤ 20)
- 둘째 줄부터 R개의 줄에 걸쳐서 보드에 적혀 있는 C개의 대문자 알파벳들이 빈칸 없이 주어진다.

[출력]
- 첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.

[구현방법]
- 개인적으론 이런 문제는 우선 가로세로를 잘 구분해야 했음
- 또한 알파벳을 기록하는 것도 필요함 같은 곳은 못가게 해야하니까
- 중복제거가 되는 Set으로 해서 contain 인지 확인하면 편할듯
- 처음 시작할 때, 시작점인 [1][1] 지점도 중복 처리 및 카운트에 넣어야함을 잊지말자

[보완점]

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] moveRow = {-1, 0, 1, 0};
    static int[] moveCol = {0, -1, 0, 1};

    static int R, C;
    static int maxCount = Integer.MIN_VALUE;
    static Set<String> pastAlphabet;
    static char[][] board;
    static boolean[][] isVisited;

    static void toString (char[][] input) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < input.length; i++) {
            sb.append(Arrays.toString(input[i])).append("\n");
        }

        System.out.println(sb);
    }

    static void DFS(int row, int col, int count) {
        if (maxCount < count) maxCount = count;

        isVisited[row][col] = true;

        // 4방탐색
        for (int i = 0; i < moveRow.length; i++) {
            int nextRow = row + moveRow[i];
            int nextCol = col + moveCol[i];

            // 범위 밖을 벗어났거나, 방문한 적이 있거나, 이미 같은 알파벳을 보유중이라면 패스
            if (isOutOfBound(nextRow, nextCol)
                    || isVisited[nextRow][nextCol]
                    || isContain(board[nextRow][nextCol])) continue;

            // 현재 위치 알파벳
            char currentChar = board[nextRow][nextCol];
            
            // 다음 DFS를 진행하기 전에, 중복 처리와 방문처리를 진행한다
            pastAlphabet.add(String.valueOf(currentChar));
            isVisited[nextRow][nextCol] = true;

            // System.out.println(pastAlphabet);
            DFS(nextRow, nextCol, count + 1);

            // 한 조건의 DFS가 끝났으므로 방금 전에 진행했던 중복처리와 방문처리를 롤백한다
            pastAlphabet.remove(String.valueOf(currentChar));
            isVisited[nextRow][nextCol] = false;
        }
    }

    static boolean isOutOfBound (int row, int col) {
        return row < 1 || R < row || col < 1 || C < col;
    }

    static boolean isContain (char c) {
        return pastAlphabet.contains(String.valueOf(c));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        pastAlphabet = new HashSet<>();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R+1][C+1];
        isVisited = new boolean[R+1][C+1];

        for (int row = 1; row <= R; row++) {
            String input = br.readLine();

            for (int col = 1; col <= C; col++) {
                board[row][col] = input.charAt(col - 1);  // charAt(index)는 0부터 시작이니까 1부터 시작하는 col은 -1로 계산해줘야한다
            }
        }

        // toString(board);

        // 처음 시작할 때, 시작점인 [1][1] 지점도 중복 처리 및 카운트에 넣어야함을 잊지말자
        pastAlphabet.add(String.valueOf(board[1][1]));
        DFS(1, 1, 1);

        System.out.println(maxCount);
    }
}