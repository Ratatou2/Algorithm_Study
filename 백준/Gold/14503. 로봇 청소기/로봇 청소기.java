

/*
[백준]
14503, 로봇 청소기

[문제파악]
- 로봇 청소기와 방의 상태가 주어졌을 때, 청소하는 영역의 개수를 구하는 프로그램을 작성하시오.
- 방은 N x M 크기의 직사각형이며 1x1 크기의 정사각형 칸으로 나누어진다.
- 각 칸은 벽 또는 빈 칸이다.
- 로봇 청소기는 동/서/남/북 중 하나를 바라보고 시작하며, 초기에는 빈 칸에 위치한다.
- 로봇은 다음 규칙에 따라 움직인다:
  1. 현재 칸이 청소되지 않았다면 청소한다.
  2. 주변 4칸에 청소되지 않은 빈 칸이 없다면, 방향을 유지한 채 뒤로 후진한다.
     - 뒤가 벽이면 작동을 멈춘다.
  3. 주변 4칸 중 청소되지 않은 칸이 있다면, 반시계 방향으로 90도 회전하고
     - 앞 칸이 청소되지 않은 빈 칸이면 전진한다.
  4. 다시 1번으로 돌아간다.

[입력]
- 첫째 줄: N, M (3 ≤ N, M ≤ 50)
- 둘째 줄: 로봇 청소기의 시작 좌표 (r, c)와 방향 d
  - d = 0: 북, 1: 동, 2: 남, 3: 서
- 이후 N개의 줄: 각 칸의 상태 (0 = 청소되지 않은 빈 칸, 1 = 벽)
- 가장자리 칸은 모두 벽이다.
- 로봇이 위치한 칸은 빈 칸이다.

[출력]
- 로봇 청소기가 멈출 때까지 청소한 칸의 개수

[구현방법]
- 그냥 방향 정해놓고 사방탐색 진행하면서 isVisted 체크해야하는 전형적인 시뮬 문제
- 청소한 방은 2로 세팅

[보완점]

*/

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][]  room;   // 0 : 청소되지 않은 방, 1 : 벽, 2 : 청소된 방
    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};
    static int[] robot;

    static void printRoom() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================\n");

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                sb.append(room[row][col] + " ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int cleanRoom() {
        int cleaned = 0;

        while (true) {
            int robotX = robot[0];
            int robotY = robot[1];
            int robotDir = robot[2];

            // 1. 현재 위치가 청소가 안되어 있으면 청소
            if (room[robotX][robotY] == 0) {
                room[robotX][robotY] = 2;  // 2 = 청소했다는 의미 (숫자를 2로 갱신함으로써 isCleaned를 체크할 배열이 필요 없음)
                cleaned++;
            }

            // 2. 주변 청소되지 않은 빈칸 유무 체크
            boolean isExist = false;
            for (int i = 0; i < 4; i++) {
                int nextDir = (robotDir + i) % 4;
                int nextX = robotX + moveX[nextDir];
                int nextY = robotY + moveY[nextDir];

                // 맵 범위 밖으로 나가는 것을 체크할 필요가 없는 이유는 문제 조건에 최외곽이 전부 벽이기 때문이다
                // 그러므로 청소 안된 칸을 발견한다면, flag를 존재함(true)으로 설정하고 그대로 종료
                if (room[nextX][nextY] == 0) {
                    isExist = true;
                    break;
                }
            }

            // 3. 빈칸 여부에 따라 분기처리
            if (isExist) {  // 3-1. 빈칸이 있는 경우
                robotDir = (robotDir - 1 + 4) % 4;  // 로봇 방향 갱신
                int nextX = robotX + moveX[robotDir];
                int nextY = robotY + moveY[robotDir];

                // 갱신된 방향이 청소되지 않은 빈칸인 경우
                // 한칸 전진 (= 좌표갱신)
                if (room[nextX][nextY] == 0) {
                    robot[0] = nextX;
                    robot[1] = nextY;
                }

                // 빈칸이 있는 경우 - 이동 여부와 상관없이 방향은 갱신되어야 함
                robot[2] = robotDir;
            } else {  // 3-2. 빈칸이 없는 경우
                robotDir = (robotDir - 2 + 4) % 4;  // 로봇 방향 갱신
                int nextX = robotX + moveX[robotDir];
                int nextY = robotY + moveY[robotDir];

                // 뒤쪽 칸이 후진할 수 있는 곳이라면 (청소 여부와 상관없이) 후진한다 (이때 방향은 갱신없이 유지, Only 좌표만 갱신)
                if (room[nextX][nextY] != 1) {
                    robot[0] = nextX;
                    robot[1] = nextY;
                } else {
                    return cleaned;  // 뒤쪽이 벽이라 움직일 수 없으면 그대로 종료
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        robot = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        room = new int[N][M];

        // 방 구조 입력받기
        for (int row = 0; row < N; row++) {
            StringTokenizer input = new StringTokenizer(br.readLine());

            for (int col = 0; col < M; col++) {
                room[row][col] = Integer.parseInt(input.nextToken());
            }
        }

        System.out.println(cleanRoom());;
    }
}
