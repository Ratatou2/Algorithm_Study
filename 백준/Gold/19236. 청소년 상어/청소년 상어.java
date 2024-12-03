/*
[백준]
19236, 청소년 상어

[문제파악]
- 아기 상어가 성장해 청소년 상어가 되었다.
- 4×4크기의 공간이 있고, 크기가 1×1인 정사각형 칸으로 나누어져 있다.
- 공간의 각 칸은 (x, y)와 같이 표현하며, x는 행의 번호, y는 열의 번호이다.
- 한 칸에는 물고기가 한 마리 존재한다.
- 각 물고기는 번호와 방향을 가지고 있다.
- 번호는 1보다 크거나 같고, 16보다 작거나 같은 자연수이며, 두 물고기가 같은 번호를 갖는 경우는 없다.
- 방향은 8가지 방향(상하좌우, 대각선) 중 하나이다.

- 오늘은 청소년 상어가 이 공간에 들어가 물고기를 먹으려고 한다.
- 청소년 상어는 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어가게 된다.
- 상어의 방향은 (0, 0)에 있던 물고기의 방향과 같다. 이후 물고기가 이동한다.
- 물고기는 번호가 작은 물고기부터 순서대로 이동한다.
- 물고기는 한 칸을 이동할 수 있고, 이동할 수 있는 칸은 빈 칸과 다른 물고기가 있는 칸, 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸이다.
- 각 물고기는 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다.
- 만약, 이동할 수 있는 칸이 없으면 이동을 하지 않는다.
- 그 외의 경우에는 그 칸으로 이동을 한다.
- 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동한다.
- 물고기의 이동이 모두 끝나면 상어가 이동한다.
- 상어는 방향에 있는 칸으로 이동할 수 있는데, 한 번에 여러 개의 칸을 이동할 수 있다.
- 상어가 물고기가 있는 칸으로 이동했다면, 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가지게 된다.
- 이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않는다.
- 물고기가 없는 칸으로는 이동할 수 없다. 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다.
- 상어가 이동한 후에는 다시 물고기가 이동하며, 이후 이 과정이 계속해서 반복된다.

- 상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 구해보자.

[입력]
- 첫째 줄부터 4개의 줄에 각 칸의 들어있는 물고기의 정보가 1번 행부터 순서대로 주어진다.
- 물고기의 정보는 두 정수 ai, bi로 이루어져 있고, ai는 물고기의 번호, bi는 방향을 의미한다.
- 방향 bi는 8보다 작거나 같은 자연수를 의미하고, 1부터 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗ 를 의미한다.

[출력]
- 상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 출력한다.

[구현방법]
- 일단 물고기 정보를 가지고 있을 Fish 클래스 하나가 있어야할듯하다 (Number & Direction)
    - 물고기가 죽었는지 살았는지 체크할 isAlive 상태값도 하나 필요
- 그리고 배열을 만들어서 넣어둔다음 for문으로 각 물고기를 이동시키며 위치를 바꿔야할듯하다
- 45도 반시계 방향으로 돌려주는 함수도 필요함
- 이것은 그럼 방햐응로 여러칸을 이동할 수 있으니까 DFS로 각 조건에 따라 다 구해봐야하나...?
    - 근데 그런 식이면 1초내에 어떻게 끝내는 것이죠...?
    - 아 백트래킹 써야하나보다. 이전 기록 중 베스트 케이스보다 작으면 더 시도해볼 필요가 없겠다 (근데 일말의 가능성이 있지 않나 싶긴함. 물고기들은 계속 움직이는걸?)

[보완점]
- 필요한 기능은 전부 다 구현했으나 내가 아직 취약한 부분은 백트래킹 파트
- 이거 그냥 DFS로 구현하고 중간에 체크하다가 이전 값보다 작으면 튕구면 되는거 같은데 흠...
- 내가 백트래킹에 대해서 너무 어렵게 생각했던 것 같다 (그럼에도 불구하고 이 문제는 어려웠음 ㅠ)
- 일단 범위 밖으로 나가는지, 상어를 체크하는지, 있다면 해당 방향으로는 진행하지 않는 로직을 넣어둔 것도 백트래킹에 속함
- 또한 이전 상태를 기록해두었다가 DFS가 끝나고 돌아오면 다시 이전 상태로 롤백하는 등의 모든 과정이 백트래킹임
- 내가 이 문제를 풀 때 고생했던 두가지는 
    1) 상어의 이동이 한번에 여러 칸을 이동할 수 있다는 것을 간과하지 않는 것
    2) 그러다보니 굉장히 다양한 경우의 수의 DFS를 해야하는데 그냥 복사해서 쓰는게 더 간편하다는 것
- 물고기 이동 코드는 나름 괜찮았던 것 같은데 DFS를 못 풀다시피 했다 ㅠ
- 추후에 다시 풀어보자 꼭! 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 0번 인덱스 제외하고 1~8까지 반시계 방향으로 이동 가능
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int maxSum = 0;

    static class Fish {
        int x, y, dir;
        boolean isAlive;

        Fish(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.isAlive = true;
        }
    }

    // 범위 밖으로 나갔는지 체크
    static boolean isOutOfBound (int x, int y) {
        return x < 0 || 4 <= x || y < 0 || 4 <= y;
    }

    static void dfs(int[][] sea, Fish[] fishes, int x, int y, int dir, int sum) {
        maxSum = Math.max(maxSum, sum);

        // 상태 복사
        int[][] tempSea = new int[4][4];
        Fish[] tempFishes = new Fish[17];

        // 물고기 위치 기록 배열인 sea 복사
        for (int i = 0; i < 4; i++) {
            System.arraycopy(sea[i], 0, tempSea[i], 0, 4);
        }

        // 물고기 좌표와 상태 기록 배열인 fishes 복사
        for (int i = 1; i <= 16; i++) {
            if (fishes[i] != null) {
                tempFishes[i] = new Fish(fishes[i].x, fishes[i].y, fishes[i].dir);
                tempFishes[i].isAlive = fishes[i].isAlive;
            }
        }

        // 물고기 이동
        moveFishes(tempSea, tempFishes);

        // 상어 이동 (step : 한 방향으로 한번에 여러 칸을 이동할 수 있다. 각각의 케이스에서 DFS 진행)
        for (int step = 1; step < 4; step++) {
            int nx = x + dx[dir] * step;
            int ny = y + dy[dir] * step;

            // 맵 밖으로 벗어났거나 0, 즉 빈 공간이면 진행할 수 없다 (방향 전환 필요)
            if (isOutOfBound(nx, ny) || tempSea[nx][ny] == 0) continue;

            int fishNum = tempSea[nx][ny];  // 이동할 자리에 있을 물고기번호
            tempSea[x][y] = 0;  // 지나온 자리는 비어있음 처리 (잡아먹은 물고기가 있던 번호를 처리하는 부분이기도 함)
            tempSea[nx][ny] = -1;  // 다음 스텝의 자리에 상어 표기
            tempFishes[fishNum].isAlive = false;  // 이동할 자리에 있는 물고기 사망처리

            // 현재 잡아먹은 물고기 번호 더한 상태에서 다시 DFS 시작
            dfs(tempSea, tempFishes, nx, ny, tempFishes[fishNum].dir, sum + fishNum);

            // 하나의 경우의 수가 끝났으므로 이전 상태로 롤백
            tempFishes[fishNum].isAlive = true;
            tempSea[nx][ny] = fishNum;
            tempSea[x][y] = -1;
        }
    }

    // 1번 물고기부터 순차적으로 이동 진행
    static void moveFishes(int[][] sea, Fish[] fishes) {
        for (int i = 1; i <= 16; i++) {
            if (!fishes[i].isAlive) continue;  // 죽어있는 물고기는 위치 바꿀 필요 없음

            // 현재 물고기의 좌표 및 방향
            int curr_x = fishes[i].x;
            int curr_y = fishes[i].y;
            int curr_dir = fishes[i].dir;

            // 45도씩 한바퀴 돌려면 8번 반복 필요
            for (int j = 0; j < 8; j++) {
                int nx = curr_x + dx[curr_dir];
                int ny = curr_y + dy[curr_dir];

                // 범위 안 이거나, 상어가 없다면 이동할 수 있음
                if (!isOutOfBound(nx, ny) && sea[nx][ny] != -1) {
                    // 비어있으면 그냥 이동하면 된다 (대신 이동하고 난 뒤의 자리는 비어있다고 0으로 표기)
                    if (sea[nx][ny] == 0) {
                        sea[nx][ny] = i;
                        sea[curr_x][curr_y] = 0;
                    } else {  // 비어 있지 않으면, 그 물고기랑 스왑하면 됨
                        int swapFish = sea[nx][ny];
                        sea[nx][ny] = i;
                        sea[curr_x][curr_y] = swapFish;

                        // 바꿀 물고기한테 현재 물고기의 좌표 전달
                        fishes[swapFish].x = curr_x;
                        fishes[swapFish].y = curr_y;
                    }

                    // 현재 물고기의 좌표는 이동할 좌표
                    fishes[i].x = nx;
                    fishes[i].y = ny;
                    fishes[i].dir = curr_dir;
                    break;
                }

                // 45도 꺾어서 방향 전환 (여기까지 왔다는 것은 범위 밖이거나, 상어를 마주했다는 의미)
                curr_dir = (curr_dir % 8) + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Fish[] fishes = new Fish[17];  // 물고기들의 정보를 순차적으로 저장할 배열
        int[][] sea = new int[4][4];  // 바다에 물고기 배치를 저장할 배열

        // 입력값 받기
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                int fishNum = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                fishes[fishNum] = new Fish(i, j, direction);
                sea[i][j] = fishNum;
            }
        }

        // 첫 물고기 잡아먹고 시작
        int firstFishNum = sea[0][0];
        fishes[firstFishNum].isAlive = false;  // 자리에 있던 물고기 사망처리
        int sharkDir = fishes[firstFishNum].dir;  // 지금부터 상어의 방향은 방금 잡아먹은 물고기의 방향
        sea[0][0] = -1; // 상어 위치 표시

        // 첫 DFS 시작
        dfs(sea, fishes, 0, 0, sharkDir, firstFishNum);

        System.out.println(maxSum);
    }
}