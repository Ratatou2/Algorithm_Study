

/*
[백준]
14499, 주사위 굴리기

[문제파악]
크기가 N×M인 지도가 존재한다. 지도의 오른쪽은 동쪽, 위쪽은 북쪽이다.
이 지도의 위에 주사위가 하나 놓여져 있으며, 주사위의 전개도는 아래와 같다.
지도의 좌표는 (r, c)로 나타내며, r는 북쪽으로부터 떨어진 칸의 개수, c는 서쪽으로부터 떨어진 칸의 개수이다.

  2
4 1 3
  5
  6
주사위는 지도 위에 윗 면이 1이고, 동쪽을 바라보는 방향이 3인 상태로 놓여져 있으며, 놓여져 있는 곳의 좌표는 (x, y) 이다.
가장 처음에 주사위에는 모든 면에 0이 적혀져 있다.
지도의 각 칸에는 정수가 하나씩 쓰여져 있다.
주사위를 굴렸을 때, 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
주사위를 놓은 곳의 좌표와 이동시키는 명령이 주어졌을 때, 주사위가 이동했을 때 마다 상단에 쓰여 있는 값을 구하는 프로그램을 작성하시오.
주사위는 지도의 바깥으로 이동시킬 수 없다.
만약 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안 된다.

[입력]
첫째 줄에 지도의 세로 크기 N, 가로 크기 M (1 ≤ N, M ≤ 20), 주사위를 놓은 곳의 좌표 x, y(0 ≤ x ≤ N-1, 0 ≤ y ≤ M-1), 그리고 명령의 개수 K (1 ≤ K ≤ 1,000)가 주어진다.
둘째 줄부터 N개의 줄에 지도에 쓰여 있는 수가 북쪽부터 남쪽으로, 각 줄은 서쪽부터 동쪽 순서대로 주어진다.
주사위를 놓은 칸에 쓰여 있는 수는 항상 0이다.
지도의 각 칸에 쓰여 있는 수는 10 미만의 자연수 또는 0이다.
마지막 줄에는 이동하는 명령이 순서대로 주어진다.
동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4로 주어진다.

[출력]
이동할 때마다 주사위의 윗 면에 쓰여 있는 수를 출력한다.
만약 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안 된다.

[구현방법]
- 주사위는 말 그대로 '굴러가는' 듯하다
- 내가 구현하다 막힌 부분은 굴러가는 주사위의 규칙을 어떻게 정의해야하는가? (어떻게 해야 빨리, 효율적으로 구할 수 있는가?)
- 이 부분을 정의해두지 않아서 이대로 구현하면 매번 주사위 면의 위치를 구하는 것에 굉장히 많은 시간을 소요해야만 했음
- 이에 대한 해답은 굴린 후에 '면을 스왑'하는 것에 있다
- 즉, 초기 아이디어처럼 주사위 면 숫자에 대한 것은 1차원 배열로 해결하고, 굴리는 순간 각 위치의 규칙에 따라 '스왑'하는 것이다
- 이렇게 하면 매번 주사위를 굴리지 않고도, '현재 바닥면에 붙어있는 면'은 매번 고정되니 반대면을 구하기도 쉬워짐
    - 더군다나 초기 아이디어와 달리 반댓면을 위한 배열도 필요 없어지니 메모리 절약은 덤이다

- 이것과 별개로 주의해야할 점은 지도에 닿아있는 바닥은 (주사위 전개도 상) 6이고, 그 반댓면인 윗면은 1이란 점이다

[보완점]


출력

*/

import java.io.*;
import java.util.*;

public class Main {
    // 이해하기 쉽게 index 1부터 동, 서, 북, 남으로 기록해둔다 (인덱스 0번은 안쓰는 것임)
    static int[] moveX = {0, 0, 0, -1, 1};
    static int[] moveY = {0, 1, -1, 0, 0};

    // 주사위 숫자 기록용 - 이건 그냥 어디 그려놓든 해서 외워야 함 - 효율을 위해 1차원 배열
    // 순서대로 북측면, 왼쪽, 윗면, 오른쪽, 남측면, 바닥
    static int[] diceNum = {0, 0, 0, 0, 0, 0};
    static int N, M, x, y, K;
    static int[][] map;

    static boolean outOfBound(int x, int y) {
        return x < 0 || y < 0 || N <= x || M <= y;
    }

    static void printMap() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                sb.append(map[row][col] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void swapDice(int dir) {
        int[] cloneDice = diceNum.clone();

        // 동서북남에 따라 스왑되도록 세팅
        switch (dir) {
            case 1:  // 동
                diceNum[2] = cloneDice[1];  // 윗면 - 왼쪽으로 교체
                diceNum[3] = cloneDice[2];  // 오른쪽 - 위쪽으로 교체
                diceNum[1] = cloneDice[5];  // 왼쪽 - 바닥으로 교체
                diceNum[5] = cloneDice[3];  // 바닥 - 오른쪽으로 교체
                break;
            case 2:  // 서
                diceNum[2] = cloneDice[3];  // 윗면 - 오른쪽으로 교체
                diceNum[1] = cloneDice[2];  // 왼쪽 - 윗면으로 교체
                diceNum[3] = cloneDice[5];  // 오른쪽 - 바닥으로 교체
                diceNum[5] = cloneDice[1];  // 바닥 - 왼쪽으로 교체
                break;
            case 3:  // 북
                diceNum[2] = cloneDice[4];  // 윗면 - 남측면으로 교체
                diceNum[0] = cloneDice[2];  // 북측면 - 윗면으로 교체
                diceNum[4] = cloneDice[5];  // 남측면 - 바닥으로 교체
                diceNum[5] = cloneDice[0];  // 바닥 - 북측면으로 교체
                break;
            case 4:  // 남
                diceNum[2] = cloneDice[0];  // 위쪽 - 북측면으로 교체
                diceNum[0] = cloneDice[5];  // 북측면 - 바닥으로 교체
                diceNum[4] = cloneDice[2];  // 남측면 - 윗면으로 교체
                diceNum[5] = cloneDice[4];  // 바닥 - 남측면으로 교체
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(br.readLine());

        N = Integer.parseInt(input.nextToken());
        M = Integer.parseInt(input.nextToken());
        x = Integer.parseInt(input.nextToken());
        y = Integer.parseInt(input.nextToken());
        K = Integer.parseInt(input.nextToken());

        map = new int[N][M];

        // map 입력
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 주사위 굴리기
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            int rollDir = Integer.parseInt(st.nextToken());  // 현재 굴러가야하는 방향

            int nextX = x + moveX[rollDir];
            int nextY = y + moveY[rollDir];

            // 범위 밖으로 탈출하면 더 볼 필요 없이 무시
            if (outOfBound(nextX, nextY)) continue;

            // 주사위 좌표 갱신
            x = nextX;
            y = nextY;

            // 굴리고, 바닥면의 숫자를 조건에 따라 갱신
            swapDice(rollDir);
            if (map[x][y] == 0) map[x][y] = diceNum[5];
            else {
                diceNum[5] = map[x][y];
                map[x][y] = 0;
            }

            // 윗면에 쓰인 수를 출력
            sb.append(diceNum[2]).append("\n");
        }

        System.out.println(sb);
    }
}
