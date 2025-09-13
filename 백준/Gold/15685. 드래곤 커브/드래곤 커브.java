
/*
[백준]
15685, 드래곤 커브

[문제파악]
드래곤 커브는 다음과 같은 세 가지 속성으로 이루어져 있으며, 이차원 좌표 평면 위에서 정의된다.
좌표 평면의 x축은 → 방향, y축은 ↓ 방향이다.
0세대 드래곤 커브는 아래 그림과 같은 길이가 1인 선분이다.
아래 그림은 (0, 0)에서 시작하고, 시작 방향은 오른쪽인 0세대 드래곤 커브이다.
1세대 드래곤 커브는 0세대 드래곤 커브를 끝 점을 기준으로 시계 방향으로 90도 회전시킨 다음 0세대 드래곤 커브의 끝 점에 붙인 것이다.
끝 점이란 시작 점에서 선분을 타고 이동했을 때, 가장 먼 거리에 있는 점을 의미한다.
2세대 드래곤 커브도 1세대를 만든 방법을 이용해서 만들 수 있다. (파란색 선분은 새로 추가된 선분을 나타낸다)
3세대 드래곤 커브도 2세대 드래곤 커브를 이용해 만들 수 있다.
아래 그림은 3세대 드래곤 커브이다.
즉, K(K > 1)세대 드래곤 커브는 K-1세대 드래곤 커브를 끝 점을 기준으로 90도 시계 방향 회전 시킨 다음, 그것을 끝 점에 붙인 것이다.
크기가 100×100인 격자 위에 드래곤 커브가 N개 있다.
이때, 크기가 1×1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형의 개수를 구하는 프로그램을 작성하시오.
격자의 좌표는 (x, y)로 나타내며, 0 ≤ x ≤ 100, 0 ≤ y ≤ 100만 유효한 좌표이다.

[입력]
첫째 줄에 드래곤 커브의 개수 N(1 ≤ N ≤ 20)이 주어진다.
둘째 줄부터 N개의 줄에는 드래곤 커브의 정보가 주어진다.
드래곤 커브의 정보는 네 정수 x, y, d, g로 이루어져 있다.
x와 y는 드래곤 커브의 시작 점, d는 시작 방향, g는 세대이다. (0 ≤ x, y ≤ 100, 0 ≤ d ≤ 3, 0 ≤ g ≤ 10)
입력으로 주어지는 드래곤 커브는 격자 밖으로 벗어나지 않는다.
드래곤 커브는 서로 겹칠 수 있다.
방향은 0, 1, 2, 3 중 하나이고, 다음을 의미한다.

0: x좌표가 증가하는 방향 (→)
1: y좌표가 감소하는 방향 (↑)
2: x좌표가 감소하는 방향 (←)
3: y좌표가 증가하는 방향 (↓)

[출력]
첫째 줄에 크기가 1×1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 것의 개수를 출력한다.

[구현방법]
- 지금 생각나는 방법은 원점에서 가장 멀리있는 점을 기준으로 다음 점까지 이동하며 움직이는 x, y를 Queue에 넣어두는 것
- 그리고 방향에 따라 해당 방향으로 뻗어나가고 Queue가 비어버리면 끝점을 기준으로 갱신
- 이것을 입력값을 다 소진할 때까지 반복

- 이게 아니었음 / 모든 좌표를 기억할 필요 없다 그렇게 하는게 더 어려울 뿐더러
- 그냥 방향을 외우면 되는 것임!
- 즉, 진행 방향을 기억하고 있다가, 마지막 점을 기준으로 시작점까지 도달하는 '역방향'으로 탐색하면 되는 것이다
- 역방향으로 탐색해 들어가며 여지껏의 진행 방향을 전부 90도 돌리는 방향으로 저장하면 됨
- 그리고 시작점까지 도달하면 현 상태는 '종점을 기준으로 90도 돌린 형태'인 셈이다
- 그리고 이것을 기존의 방향 리스트에 추가하면 되고 위 순서를 반복한다

[보완점]
1) 문제 범위 조건은 0 <= 범위 <= 100 이다 '< 100'이 아님..
2) 방향 탐색 조건을 문제에서 줬음 이거 잘 읽고 moveX, moveY 값 설정해야 안틀림
*/

import java.io.*;
import java.util.*;

public class Main {
    static int[] moveX = {1, 0, -1, 0};
    static int[] moveY = {0, -1, 0, 1};
    static boolean[][] isVisited;

    static boolean isOutOfBound (int x, int y) {
        return x < 0 || y < 0 || 100 < x || 100 < y;
    }

    static void dragonCurve(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        for (int i = 0; i < g; i++) {
            int size = directions.size();

            // 현재 directions에 들어있는 개수만큼만 방향 변환하고 추가되는 것들은 진행 X
            // 또한 마지막 지점에서부터 역순으로 탐지해야가야한다 (왜냐면 회전의 주축이 종점이기 때문)
            for (int j = size - 1; 0 <= j; j--) {
                int curr = directions.get(j);
                directions.add((curr + 1) % 4);
            }
        }

        // 시작 지점 방문처리
        isVisited[x][y] = true;
        int currX = x;
        int currY = y;

        for (int dir : directions) {
            currX += moveX[dir];
            currY += moveY[dir];

            // 무조건 범위 내에 있다고는 했지만 혹시 모르니 조건문
            if (!isOutOfBound(currX, currY)) {
                isVisited[currX][currY] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        isVisited = new boolean[100 + 1][100 + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dragonCurve(x, y, d, g);
        }

        // 정답 갯수 세기
        int count = 0;
        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 100; col++) {
                if (isVisited[row][col] && isVisited[row + 1][col]
                        && isVisited[row][col + 1] && isVisited[row + 1][col + 1]) count++;
            }
        }

        System.out.println(count);
    }
}
