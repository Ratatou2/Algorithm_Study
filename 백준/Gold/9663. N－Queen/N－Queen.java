

/*
[백준]
9663, N-Queen

[문제파악]
N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 N이 주어진다. (1 ≤ N < 15)

[출력]
첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.

[구현방법]
- 백트래킹에도 약한 것 같아서 오늘은 이것을 골랐다. 아마 제대로 풀진 못하고 공부하는 식으로 몇시간 쏟겠지...?
- 일단 바로 생각나는 아이디어는 이중 for문으로 탐색해가며 현재 자리에 놓을 수 있는지 없는지를 체크하는 방법
- N이 15이고 10초를 주니까 하나씩 다 탐색해봐도 될 것 같은데...? 너무 눈대중인가
- 근데 그걸 다 차치하고 현 자리에서 되고 안되고 하는 방식으로 '모든 조건'을 탐색하기엔 굉장히 비효율적이다 (좀 더 논리적인 방법이 필요)
- 아니면 N개면 N-1개까지는 최대한 오밀조밀 배치해두고, 마지막 1개만 돌아다니면서 되는 모든 경우의 수를 다 체크하는건?
- 근데 이거는 N-1번째 Queen을 한칸이라도 다른 곳에 배치하면 또 다른 가능성이 생길 수 있는데 이러면 또 가능한 하나의 경우의 수를 놓치는게 생긴다
- 아니 심지어 서로 범주내에 들어오면 안되는 것은 그냥 boolean[][] 배열만들고 퀸 하나 둘때마다 해당 좌표 기준, 퀸이 움직일 수 있는 모든 범주를 true로 둬서 못 두게 해야하나?
- 품이 굉장히 많이 들겠는데... 뭐 이건 딱히 일은 아닌데.. 흠.. 뭔가 비효율적이란 말이지...
- 백트래킹 얼마 풀어보지도 못해서 도저히 생각이 안남..

[보완점]
- 흠. 일단 한 행에 하나밖에 못둠 (퀸은 현 위치로부터 상하좌우 및 모든 대각선을 칸 수 제한없이 움직일 수 있다는 사실을 잊지말자
- 이말인 즉, boolean[][]일 필요 없이 행마다 체크하기 위한 boolean[]면 충분하다는 의미임
- 그리고 모든 경우의 수? ㅋㅋㅋㅋ 절대 불가 15! = 1.3조 경우의 수..

< (*) 대각선을 체크하는 방법 >
- 이것도 그냥 수학적 방법이다
- 예를들어 시작점이 (0, 0)이라고 하면 대각선의 점들은 ~, (-1, -1), (1, 1), ~가 된다 이 말인 즉, Math.abs(-1-(1)) = Math.abs(1-(-1))은 항상 같다는 명제가 성립한다 (대각선을 구하는 것과 같음)
- 그러므로? 이것을 그냥 코드로 바꿔보면 어차피 board에 각 좌표에 퀸이 어딨는지 알 수 있으니, 해당 점과 좌표를 비교해서 빼면 된다 (행은 행끼리, 열은 열끼리)
*/

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] board;
    static int count;

    static boolean isPossible(int row) {
        // 우선 한 행(row)에 한 Queen만 두니까 열(col)만 체크하면 됨 (또한, 현재의 행 이전까지만 퀸이 놓여져 있는 상황이니 i < row 조건으로 범위 조절)
        for (int i = 0; i < row; i++) {
            // 같은 열에 있거나, 대각선으로 겹치면 여기엔 둘 수 없음 (*)
            if (board[i] == board[row]
                    || Math.abs(row - i) == Math.abs(board[row] - board[i])) {
                return false;
            }
        }

        return true;
    }

    static void DFS(int row) {
        // 모든 행에 Queen을 놓았다? -> 카운트 +1 후, 재귀 종료지점
        if (row == N) {
            count++;
            return;
        }

        for (int col = 0; col < N; col++) {
            board[row] = col;  // 현재 행에서 col 열에 퀸을 두었다고 가정

            // 현 조합이 가능하다면(=경로에 다른 Queen이 없다면) 다음 행으로 진행
            if (isPossible(row)) {
                DFS(row + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N];
        count = 0;

        DFS(0); // 0번째 행부터 퀸을 놓으며 시작
        System.out.println(count);
    }
}
