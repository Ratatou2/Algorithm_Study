

/*
[백준]
13460, 구슬 탈출 2

[문제파악]
스타트링크에서 판매하는 어린이용 장난감 중에서 가장 인기가 많은 제품은 구슬 탈출이다.
구슬 탈출은 직사각형 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 다음, 빨간 구슬을 구멍을 통해 빼내는 게임이다.
보드의 세로 크기는 N, 가로 크기는 M이고, 편의상 1×1크기의 칸으로 나누어져 있다.
가장 바깥 행과 열은 모두 막혀져 있고, 보드에는 구멍이 하나 있다.
빨간 구슬과 파란 구슬의 크기는 보드에서 1×1크기의 칸을 가득 채우는 사이즈이고, 각각 하나씩 들어가 있다.
게임의 목표는 빨간 구슬을 구멍을 통해서 빼내는 것이다.
이때, 파란 구슬이 구멍에 들어가면 안 된다.
이때, 구슬을 손으로 건드릴 수는 없고, 중력을 이용해서 이리 저리 굴려야 한다.
왼쪽으로 기울이기, 오른쪽으로 기울이기, 위쪽으로 기울이기, 아래쪽으로 기울이기와 같은 네 가지 동작이 가능하다.
각각의 동작에서 공은 동시에 움직인다.
빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다.
빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다.
빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다.
또, 빨간 구슬과 파란 구슬의 크기는 한 칸을 모두 차지한다.
기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.
보드의 상태가 주어졌을 때, 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하는 프로그램을 작성하시오.

[입력]
첫 번째 줄에는 보드의 세로, 가로 크기를 의미하는 두 정수 N, M (3 ≤ N, M ≤ 10)이 주어진다.
다음 N개의 줄에 보드의 모양을 나타내는 길이 M의 문자열이 주어진다.
이 문자열은 '.', '#', 'O', 'R', 'B' 로 이루어져 있다.
'.'은 빈 칸을 의미하고, '#'은 공이 이동할 수 없는 장애물 또는 벽을 의미하며, 'O'는 구멍의 위치를 의미한다.
'R'은 빨간 구슬의 위치, 'B'는 파란 구슬의 위치이다.
입력되는 모든 보드의 가장자리에는 모두 '#'이 있다.
구멍의 개수는 한 개 이며, 빨간 구슬과 파란 구슬은 항상 1개가 주어진다.

[출력]
최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 출력한다. 만약, 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력한다.

[구현방법]
- 일단 R, B 모두 동시에 움직일 수 있다
- 놓치기 쉬운게 하나 있다면 구슬이 동시에 있다보니 돌이 '동시에' 움직여야한다는 것을 놓치기 쉽다
- 즉, 아래와 같은 예시가 있다고 할 때, 단순 반복문으로 R->B 순으로 움직이면 R은 B가 움직이고 나면 움직일 수 있지만 못 움직인다
    - 반복문 순서탓에 B 움직임이 당장 R 차례에선 일어나지 않았기에
    #############
    #.........BR#
    #############
    - 즉, 움직일 것이라면 둘 모두가 동시에 움직이는 것을 계산해두고 둘의 위치가 겹치는지까지 체크해야한다는 것이다
- 그리고 한번 이동했으면 해당 방향으로 벽을 만날 때까지 움직여야함
- 그럼 지금 생각나는 풀이 방식은 아래와 같다 (초기 아이디어는 땡!)
    1) 일단 BFS로 골인 지점까지의 경로를 다 찾는다 (X)
        =>  - R만 먼저 움직여봐야 B가 도중에 들어갔거나 도달했을 때 같은 칸에 있으면 실패다
            - 즉, 미리 사전에 오답인 경우가 있을 수 있음 -> 시간낭비
            - 결국 (R, B) 위치쌍으로 BFS를 해야한다 (O)
    2) 이 때 방향을 꺾는다면 꺾은 횟수를 카운트한다 (애매)
        => - 방향이 아닌 기울이기 횟수가 중요하다 (O)
    3) 중간에 방향 전환이 10번을 넘어가면 해당 경로는 무의미하므로 버린다
    4) 10번 내로 목적지 도달 가능한 경로라면, B와 함께 움직이는 조건으로 시뮬레이션한다
        - 여기까지 살아남은 경로는 10번 이하라는 조건은 검증했으므로, B가 먼저, 또는 동시에 구멍에 빠지지 않는지만 체크한다
    5) B도 같이 움직였을 때, 해당 경로까지 도달 가능하다면 움직임을 최소한으로 갱신한다
    6) 도달점에 도달하지 못하거나, 방향전환이 모두 10번을 넘긴다면 구슬을 빼낼 수 없다는 의미이므로 -1 출력
- 위 내용을 정리하면 아래처럼 움직일 수 있다
    1) BFS의 큐에 R, B 좌표 모두 저장 (depth도 같이 준비한다 - 몇번 기울였는지 체크용)
    2) 4방향으로 R과 B 동시에 굴리기
    3) R만 빠졌는지 체크
    4) B가 빠졌거나 10 < depth면 실패
    5) 이미 방문한 조합은 스킵
    6) 그게 아니라면 큐에 추가

[보완점]
- 고난이도 구현 문제는 아직 뒷심이 조금 모자라지 싶다...
- ㄱㅊ 더 풀면 됨 ㅇㅇ
*/

import java.io.*;
import java.util.*;

public class Main {
    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};
    static int N, M;
    static String[][] map;

    static class Ball {
        int x, y, count;
        
        Ball (int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    static class Location {
        int rx, ry, bx, by, tiltCount;

        Location (int rx, int ry, int bx, int by, int tilitCount) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.tiltCount = tilitCount;
        }

        @Override
        public String toString() {
            return "R 좌표 : (" + rx + ", " + ry + "), B 좌표 : (" + bx + ", " + by + ") - " + tiltCount;
        }
    }

    // 구슬 이동
    static Ball move(int x, int y, int dir) {
        int count = 0;

        // 벽이 아니거나, 출구가 아니면 계속 이동한다 (둘다 아니어야 하니까 && 연산자인 것임)
        while (true) {
            int nextX = x + moveX[dir];
            int nextY = y + moveY[dir];

            // 이동한 곳이 벽이면 좌표 갱신없이 중단
            if (map[nextX][nextY].equals("#")) break;

            // 이동한 곳이 별 문제 없으면 좌표 갱신
            x = nextX;
            y = nextY;
            count++;

            // 이동한 곳이 구멍이면 중단
            if (map[nextX][nextY].equals("O")) break;
        }

        return new Ball (x, y, count);
    }

    // 구슬 이동하는 모습 print
    static void printMap(int rx, int ry, int bx, int by, Ball red, Ball blue) {
        StringBuilder sb = new StringBuilder();
        String[][] temp = new String[N][];

        for (int row = 0; row < N; row++) {
            temp[row] = Arrays.copyOf(map[row], M);
        }

        temp[rx][ry] = ".";
        temp[bx][by] = ".";
        temp[red.x][red.y] = "R";
        temp[blue.x][blue.y] = "B";

        for (int row = 0; row < N; row++) {
            sb.append(Arrays.toString(temp[row])).append("\n");
        }

        System.out.println(sb);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[N][M];
        boolean[][][][] isVisited = new boolean[N][M][N][M];  // Red 좌표가 A일때, Blue가 B좌표인 곳을 이전에 온적이 있나 체크(Set<Pair> 사용해도 됨)
        int minTilt = Integer.MAX_VALUE;

        // 초기 구슬 좌표 기록
        Location startLocation = new Location(0, 0, 0, 0, 0);

        // 지도 입력 받기
        for (int row = 0; row < N; row++) {
            map[row] = br.readLine().split("");

            for (int col = 0; col < M; col++) {
                if (map[row][col].equals("R")) {
                    startLocation.rx = row;
                    startLocation.ry = col;
                } else if (map[row][col].equals("B")) {
                    startLocation.bx = row;
                    startLocation.by = col;
                }
            }
        }

        // 1) BFS의 큐에 R, B 좌표 모두 저장 (depth도 같이 준비한다 - 몇번 기울였는지 체크용)
        Queue<Location> locations = new ArrayDeque<>();
        locations.add(startLocation);

        while (!locations.isEmpty()) {
            Location curr = locations.poll();

            // 2) 4방향으로 R과 B 동시에 굴리기 (주의할 점은 한방향으로 이동해서 벽이나 구멍이 나올때까지 굴러간 위치까지 도달하고 나서 Q에 넣는다는 것!!)
            for (int dir = 0; dir < 4; dir++) {
                Ball moveRed = move(curr.rx, curr.ry, dir);
                Ball moveBlue = move(curr.bx, curr.by, dir);

                // 3) R만 빠졌는지 체크
                if (map[moveRed.x][moveRed.y].equals("O") && !map[moveBlue.x][moveBlue.y].equals("O")) {
                    // [64%에서 틀린 이유] 10회 이하일 때만 갱신하는 조건을 까먹지 말도록하자!!!
                    if (curr.tiltCount + 1 <= 10) {
                        minTilt = Math.min(minTilt, curr.tiltCount + 1);
                    }
                    
                    continue;
                }

                // 4) B가 구멍에 빠졌거나, 10 < depth면 실패
                else if (map[moveBlue.x][moveBlue.y].equals("O") || 10 < curr.tiltCount + 1) {
                    continue;
                }

                // 5) 둘의 좌표가 같을 경우 더 많이 이동한 녀석이 뒤에 있었단 의미이므로 한칸 빼준다
                if (moveRed.x == moveBlue.x && moveRed.y == moveBlue.y) {
                    // 더 많이 이동한 쪽 구분하기
                    if (moveRed.count < moveBlue.count) {
                        moveBlue.x -= moveX[dir];
                        moveBlue.y -= moveY[dir];
                    } else if (moveBlue.count < moveRed.count) {
                        moveRed.x -= moveX[dir];
                        moveRed.y -= moveY[dir];
                    }
                }

                // 6) 방문한적 없다면 방문처리하고, Q에 추가
                if (!isVisited[moveRed.x][moveRed.y][moveBlue.x][moveBlue.y]) {
                    isVisited[moveRed.x][moveRed.y][moveBlue.x][moveBlue.y] = true;  // BFS이므로 방문처리를 롤백할 필요는 없다 -> DFS는 필요할 수 있음
                    locations.add(new Location(moveRed.x, moveRed.y, moveBlue.x, moveBlue.y, curr.tiltCount + 1));
                }
            }
        }

        System.out.println(minTilt == Integer.MAX_VALUE ? -1 : minTilt);
    }
}
