
/*
[백준]
1018, 체스판 다시 칠하기

[문제파악]
지민이는 자신의 저택에서 MN개의 단위 정사각형으로 나누어져 있는 M×N 크기의 보드를 찾았다.
어떤 정사각형은 검은색으로 칠해져 있고, 나머지는 흰색으로 칠해져 있다.
지민이는 이 보드를 잘라서 8×8 크기의 체스판으로 만들려고 한다.
체스판은 검은색과 흰색이 번갈아서 칠해져 있어야 한다. 구체적으로, 각 칸이 검은색과 흰색 중 하나로 색칠되어 있고, 변을 공유하는 두 개의 사각형은 다른 색으로 칠해져 있어야 한다.
따라서 이 정의를 따르면 체스판을 색칠하는 경우는 두 가지뿐이다. 하나는 맨 왼쪽 위 칸이 흰색인 경우, 하나는 검은색인 경우이다.
보드가 체스판처럼 칠해져 있다는 보장이 없어서, 지민이는 8×8 크기의 체스판으로 잘라낸 후에 몇 개의 정사각형을 다시 칠해야겠다고 생각했다.
당연히 8*8 크기는 아무데서나 골라도 된다.
지민이가 다시 칠해야 하는 정사각형의 최소 개수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 N과 M이 주어진다.
N과 M은 8보다 크거나 같고, 50보다 작거나 같은 자연수이다.
둘째 줄부터 N개의 줄에는 보드의 각 행의 상태가 주어진다.
B는 검은색이며, W는 흰색이다.

[출력]
첫째 줄에 지민이가 다시 칠해야 하는 정사각형 개수의 최솟값을 출력한다.

[구현방법]
- 완탐으로 좌측 상단부터 8*8칸 탐색하며 칠하는 갯수 카운트 하면 되는거 아닌가?
- 색칠하면 바꿔두고
- 아... 8*8칸으로 아무 곳이나 잘랐을 떄, 해당 보드 판에서 규칙에 위배되어 바꿔야하는 부분이 있다면 그것을 세는 것이다
- 그리고 또한 패턴은 2가지 종류이므로 둘 다 칠해버리고 가장 적은 수를 캐치하면 됨

- 나는 아래처럼 정의해두고 진행하려고 했음
    static char[] checkType1 = {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'};
    static char[] checkType2 = {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'};

- 이 역시 문제를 또 너무 어렵게 생각한 문제임...
- (row + col) % 2 == 0 이 수식 하나면 행, 열에 따른 패턴 분석이 가능함
    - row, col은 각각 하나씩 증가하니 시작을 B로 잡느냐, W로 잡느냐에 따라 B, W가 달라지니 그것을 쉽게 계산하기 위함임

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;

    static int minBoardCount(int currRow, int currCol) {
        int startBlack = 0;  // 맨 왼쪽 위가 B일 경우 다시 칠해야 하는 칸 수
        int startWhite = 0;  // 맨 왼쪽 위가 W일 경우 다시 칠해야 하는 칸 수

        for (int row = currRow; row < currRow + 8; row++) {
            for (int col = currCol; col < currCol + 8; col++) {
                char expectedBlack = ((row + col) % 2 == 0) ? 'B' : 'W';
                char expectedWhite = ((row + col) % 2 == 0) ? 'W' : 'B';

                if (board[row][col] != expectedBlack) startBlack++;
                if (board[row][col] != expectedWhite) startWhite++;
            }
        }

        return Math.min(startBlack, startWhite);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 세로
        M = Integer.parseInt(st.nextToken());  // 가로

        board = new char[N][M];
        int min = Integer.MAX_VALUE;

        // 인풋 받기
        for (int row = 0; row < N; row++) {
            String input = br.readLine();

            for (int col = 0; col < M; col++) {
                board[row][col] = input.charAt(col);
            }
        }

        // 탐색하며 최소 카운트 계산
        for (int row = 0; row <= N - 8; row++) {
            for (int col = 0; col <= M - 8; col++) {
                min = Math.min(minBoardCount(row, col), min);
            }
        }

        System.out.println(min);
    }
}
