

/*
[백준]
20057, 마법사 상어와 토네이도

[문제파악]
마법사 상어가 토네이도를 배웠고, 오늘은 토네이도를 크기가 N×N인 격자로 나누어진 모래밭에서 연습하려고 한다.
위치 (r, c)는 격자의 r행 c열을 의미하고, A[r][c]는 (r, c)에 있는 모래의 양을 의미한다.
토네이도를 시전하면 격자의 가운데 칸부터 토네이도의 이동이 시작된다.
토네이도는 한 번에 한 칸 이동한다.
다음은 N = 7인 경우 토네이도의 이동이다.

토네이도가 한 칸 이동할 때마다 모래는 다음과 같이 일정한 비율로 흩날리게 된다.

토네이도가 x에서 y로 이동하면, y의 모든 모래가 비율과 α가 적혀있는 칸으로 이동한다.
비율이 적혀있는 칸으로 이동하는 모래의 양은 y에 있는 모래의 해당 비율만큼이고, 계산에서 소수점 아래는 버린다.
α로 이동하는 모래의 양은 비율이 적혀있는 칸으로 이동하지 않은 남은 모래의 양과 같다.
모래가 이미 있는 칸으로 모래가 이동하면, 모래의 양은 더해진다.
위의 그림은 토네이도가 왼쪽으로 이동할 때이고, 다른 방향으로 이동하는 경우는 위의 그림을 해당 방향으로 회전하면 된다.
토네이도는 (1, 1)까지 이동한 뒤 소멸한다. 모래가 격자의 밖으로 이동할 수도 있다.
토네이도가 소멸되었을 때, 격자의 밖으로 나간 모래의 양을 구해보자.

[입력]
첫째 줄에 격자의 크기 N이 주어진다.
둘째 줄부터 N개의 줄에는 격자의 각 칸에 있는 모래가 주어진다.
r번째 줄에서 c번째 주어지는 정수는 A[r][c] 이다.

[출력]
격자의 밖으로 나간 모래의 양을 출력한다.

[구현방법]
- 기본적으로 현재 기준으로 +2칸까지 날아갈 수 있으니까 배열을 상하좌우 +2, 즉 배열의 크기를 총 4를 늘려서 만든다
- 그리고 외곽 지역만 count하면 쉬울듯 함
- 나머지는 외곽부터 이동하면 되고, 0이면 날릴 것이 없음
- 달팽이처럼 움직이는 규칙을 구해야하긴 함
    - 보아하니 좌->하 로 1씩 움직이는 것을 시작으로 우->상은 이전 이동값에 +1하면되고 N*N이라서 (0, 0)에 도달할 때까지 반복하면 됨

- 더 좋은 방법은 배열을 확장해서 외곽을 세는 것이 아니라 모래를 계산하는데 외곽으로 나갔다면? 그때 그냥 나간 모래 값에 추가하는 방식이 더 깔끔하다
- 그리고 구상단계에서는 내가 하나 놓친게 있는데, 바로 날리는 모래의 방향이 이동한다는 것
- 배열을 하나 구현해놓고 방향을 돌려가며 쓰는 것보단 4가지 방향 모두를 준비해놓고 갖다 쓰는게 실수를 줄이고 속도도 빠르게 만드는 방법일까..? 아님 비효율적인 방법?
- 근데 알고리즘 시험이면? 정확한게 중요하겠지? 로직 실패하면 다 뜯어야하니까... 종이에 그려봐야겠다

- 일단 회전 로직은 아래처럼 짜면 됨
- 기준점 (r, c)
    - 90도 회전 : (c, -r)
    - 180도 회전 : (-r, -c)
    - 270도 회전 : (-c, r)
- 왜 이렇게 나오는지는 종이에 (2, 1)찍고 90도씩 돌려보니 확인할 수 있었음
- 근데 직접 돌리는거 작성해두려니까 죽을맛이라 ㅋㅋㅋㅋ... 하나는 직접 작성하고 나머지는 돌려놓고 랜덤으로 하나 찍어서 다 그린거랑 대조해서 확인했음...
- 모래를 %로 나누고 남은 a는 비율로 계산해서 55% 고정이 아니다. 소숫점으로 버려지느라 계산이 0으로 됐다면 해당 모래 모두 다 계산해야한다는 의미!
[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    // 좌 - 하 - 우 - 상 순으로 움직이도록 세팅
    static int[] moveX = {0, 1, 0, -1};
    static int[] moveY = {-1, 0, 1, 0};

    static int N, startIndex, totalSand;
    static int[][] map;
    static List<int[]> tornadoLeft, tornadoUp, tornadoRight, tornadoDown;

    // 토네이도 방향에 따른 모래의 이동 세팅
    static void settingTornado() {
        tornadoLeft = new ArrayList<>();
        tornadoLeft.add(new int[] {-2, 0, 2});
        tornadoLeft.add(new int[] {-1, -1, 10});
        tornadoLeft.add(new int[] {-1, 0, 7});
        tornadoLeft.add(new int[] {-1, 1, 1});
        tornadoLeft.add(new int[] {0, -2, 5});
        tornadoLeft.add(new int[] {1, -1, 10});
        tornadoLeft.add(new int[] {1, 0, 7});
        tornadoLeft.add(new int[] {1, 1, 1});
        tornadoLeft.add(new int[] {2, 0, 2});
        tornadoLeft.add(new int[] {0, -1, 55});     // a는 비율인 55로 고정해두고 55를 만나면 분기를 타도록 세팅

        tornadoUp = new ArrayList<>();
        for (int[] temp : tornadoLeft) {
            int r = temp[0];
            int c = temp[1];
            int percent = temp[2];

            tornadoUp.add(new int[] {c, -r, percent});
        }

        tornadoRight = new ArrayList<>();
        for (int[] temp : tornadoLeft) {
            int r = temp[0];
            int c = temp[1];
            int percent = temp[2];

            tornadoRight.add(new int[] {-r, -c, percent});
        }

        tornadoDown = new ArrayList<>();
        for (int[] temp : tornadoLeft) {
            int r = temp[0];
            int c = temp[1];
            int percent = temp[2];

            tornadoDown.add(new int[] {-c, r, percent});
        }
    }

    // 범위 밖으로 나갔는지 계산 (외곽으로 날아가버린 모래양 계산)
    static boolean isOutOfBound (int x, int y) {
        return x < 0 || y < 0 || N <= x || N <= y;
    }

    // 토네이도의 이동
    static void moveTornado(int startIndex) {
        int currX = startIndex;
        int currY = startIndex;
        int moveCount = 1;  // moveCount만큼 이동 (좌 = 하, 우 = 상이 한세트로 묶여서 같은 횟수만큼 이동하고 +1을 한다)

        while (true) {
            // 1) 좌
            for (int i = 0; i < moveCount; i++) {
                currX += moveX[0];
                currY += moveY[0];

                // 이동한 위치에 모래가 있다면 날려보내야 함
                if (map[currX][currY] != 0) {
                    blowSand(currX, currY, tornadoLeft);
                }

                // 종료지점 만나면 그대로 종료
                if (currX == 0 && currY == 0) return;
            }

            // 2) 하
            for (int i = 0; i < moveCount; i++) {
                currX += moveX[1];
                currY += moveY[1];

                // 이동한 위치에 모래가 있다면 날려보내야 함
                if (map[currX][currY] != 0) {
                    blowSand(currX, currY, tornadoDown);
                }
            }

            moveCount++;

            // 3) 우
            for (int i = 0; i < moveCount; i++) {
                currX += moveX[2];
                currY += moveY[2];

                // 이동한 위치에 모래가 있다면 날려보내야 함
                if (map[currX][currY] != 0) {
                    blowSand(currX, currY, tornadoRight);
                }
            }

            // 4) 상
            for (int i = 0; i < moveCount; i++) {
                currX += moveX[3];
                currY += moveY[3];

                // 이동한 위치에 모래가 있다면 날려보내야 함
                if (map[currX][currY] != 0) {
                    blowSand(currX, currY, tornadoUp);
                }
            }

            moveCount++;
        }
    }

    // 현재 좌표기준으로 주변으로 날아가는 모래 계산
    static void blowSand(int x, int y, List<int[]> currTornado) {
        int currTotalSand = map[x][y];

        for (int[] cell : currTornado) {
            int nextX = x + cell[0];
            int nextY = y + cell[1];
            int sand = (cell[2] == 55) ? currTotalSand : (int) (map[x][y] * cell[2] / 100);  // (*)

            // 범위 밖으로 날아갔으면 계산하고, 아니면 칸에다가 더해주면 됨
            if (isOutOfBound(nextX, nextY)) totalSand += sand;
            else map[nextX][nextY] += sand;

            currTotalSand -= sand;  // 날아간 모래 계산
        }
        
        map[x][y] = 0;  // 다 계산했으면 기준 좌표의 모래는 0으로 초기화
    }

    // 현재 좌표와 날아간 모래들을 표기하기 위한 print
//    static void printMap (int x, int y) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("=====================\n");
//        for (int row = 0; row < N; row++) {
//            for (int col = 0; col < N; col++) {
//                if (row == x && col == y) sb.append("⬜️");
//                else sb.append(map[row][col]).append(" ");
//            }
//
//            sb.append("\n");
//        }
//
//        System.out.println(sb);
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        settingTornado();       // 토네이도 방향 세팅
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        totalSand = 0;

        // 지도 입력받기
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 토네이도 이동
        moveTornado(N / 2);

        // 결과값 출력
        System.out.println(totalSand);
    }
}

/*
- 55는 a라는 의미
- 즉, a는 비율 상 날려보내고 남음 모래 전부이다
- 이때 소숫점은 전부 버리기 때문에, 모래의 양이 5처럼 1%를 구할 수 없는 작은 수의 경우 55% 비율보다 더 많은 양의 모래가 남기 마련이다
- 따라서 55라는 a를 특정할 수 있는 비율을 만난 경우 남은 모래 전부를 계산하여 a 좌표에 몰아준다
- 이때 a는 항상 남은 모래의 총량이기 때문에, 날아갈 모래들을 '전부 계산해야만' 구할 수 있다
- 따라서 List의 가장 마지막에 넣음으로써 자연스레 날아간 모래를 모두 구한 이후로 계산할 수 있도록 세팅한다
* */
