

/*
[백준]
3190, 뱀

[문제파악]
'Dummy' 라는 도스게임이 있다. 이 게임에는 뱀이 나와서 기어다니는데, 사과를 먹으면 뱀 길이가 늘어난다.
뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.

게임은 NxN 정사각 보드위에서 진행되고, 몇몇 칸에는 사과가 놓여져 있다.
보드의 상하좌우 끝에 벽이 있다.
게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다.
뱀은 처음에 오른쪽을 향한다.

뱀은 매 초마다 이동을 하는데 다음과 같은 규칙을 따른다.

먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.

[입력]
첫째 줄에 보드의 크기 N이 주어진다. (2 ≤ N ≤ 100)
다음 줄에 사과의 개수 K가 주어진다. (0 ≤ K ≤ 100)
다음 K개의 줄에는 사과의 위치가 주어지는데, 첫 번째 정수는 행, 두 번째 정수는 열 위치를 의미한다.
사과의 위치는 모두 다르며, 맨 위 맨 좌측 (1행 1열) 에는 사과가 없다.
다음 줄에는 뱀의 방향 변환 횟수 L 이 주어진다. (1 ≤ L ≤ 100)
다음 L개의 줄에는 뱀의 방향 변환 정보가 주어지는데, 정수 X와 문자 C로 이루어져 있으며. 게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다.
X는 10,000 이하의 양의 정수이며, 방향 전환 정보는 X가 증가하는 순으로 주어진다.

[출력]
첫째 줄에 게임이 몇 초에 끝나는지 출력한다.

[구현방법]
- 몸 길이가 늘어날 수록 맨 앞과 맨 뒤만 이동하면 된다
- 즉 Deque를 써서 계산하면 O(1)만에 빠르게 끝낼 수 있을듯하다
- 구현 순서는
    1) 좌상단에서 오른쪽으로 이동 시작
    2) 이동한 지점의 상태에 따라 분기처리
        (1) 벽이나 자기 몸이라면 그대로 종료
        (2) 사과라면, 꼬리는 이동 X
        (3) 아무것도 아니라면, Deque의 마지막(뱀의 꼬리)를 제거

[보완점]
- 아래 두가지 방법 중 무엇이 더 효율적인가?
// 방법 1
StringTokenizer st;
for (int i = 0; i < K; i++) {
    st = new StringTokenizer(br.readLine());
}

// 방법 2
for (int i = 0; i < K; i++) {
    StringTokenizer st = new StringTokenizer(br.readLine());
}

- 보통은 방법 2
- 각 반복마다 새로운 st 참조 변수가 스택(Stack) 메모리에 생성됨
    - 반복이 끝나면 해당 st 변수는 스택에서 사라짐
    - 그 변수가 가리키던 객체는 참조가 없어지므로 가비지 컬렉션의 대상
- 실질적으로 자바 컴파일러의 최적화 덕분에 둘의 메모리 관리 측면에서의 차이는 거의 없다고 함
- 다만, 변수의 가장 효율적인 방향은 변수는 필요한 가장 좁은 범위(Scope) 내에서 선언하는 것
- 방법 1로 하면 외부에서도 st에 접근 가능하여 실수를 유발할 수 있음

착각한 것이 2가지 있음
    [1] '2) sec초 동안 이동한 지점의 상태에 따라 분기처리'라는게 sec초 동안만 움직이란게 아니다
        - sec초 이후에 방향을 바꾸라는 것인지 결국 계속 움직여야함 (for문이 아닌 while문이 적합하다)
    [2] 방향전환 로직은 왼쪽으로 이동할 때 -1만 하는게 아니라 +4까지 해줘야한다
        - 예를 들어 위쪽(= 인덱스 0)을 향할 때 왼쪽으로 90도라면 Math.abs(0 - 1) % 4 = 오른쪽(1)이 아니다
        - 오히려 왼쪽을 가리켜야 하므로 Math.abs(0 - 1 + 4) % 4 = 왼쪽(3)을 만들어야한다
*/

import java.io.*;
import java.util.*;

public class Main {
    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};

    static boolean isOutOfBound (int x, int y, int N) {
        return x < 0 || N <= x || y < 0 || N <= y;
    }

    // 뱀 이동현황 출력 함수
    /*
    static void printSnake (int[][] map) {
        StringBuilder sb = new StringBuilder();
        sb.append("====================\n");
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map.length; y++) {
                switch (map[x][y]) {
                    case 0:
                        sb.append("⬜️ ");
                        break;
                    case 1:
                        sb.append("🟦️ ");
                        break;
                    case 7:
                        sb.append("🍎 ");
                }

            }
            sb.append("\n");
        }
        sb.append("====================\n");
        System.out.println(sb);
    }
    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  //
        int K = Integer.parseInt(br.readLine());  // 사과의 갯수

        int[][] map = new int[N][N];

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 문제는 (1,1) 부터 시작, 코드는 (0, 0)부터 시작이기 때문에 -1로 시작
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            map[x][y] = 7;   // 사과 표기
        }

        int L = Integer.parseInt(br.readLine());    // 방향 전환 횟수

        // 뱀 초기 정보 세팅
        int dir = 1;                                        // 초기 이동방향은 오른쪽 (인덱스)
        int time = 0;                                       // 시간 카운트
        Deque<int[]> snake = new ArrayDeque<>();
        snake.add(new int[] {0, 0});                        // 시작 지점 입력
        map[0][0] = 1;                                      // 지도에 뱀 표기
        Map<Integer, Boolean> chageDir = new HashMap<>();    // 뱀의 방향 전환 기록용

        // 뱀 방향 전환 기록
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            boolean isRight = st.nextToken().equals("D");

            chageDir.putIfAbsent(sec, isRight);
        }

        // 2) sec초 동안 이동한 지점의 상태에 따라 분기처리
        while (true) {
            time++;

            int[] currHead = snake.getFirst();
            int nextX = currHead[0] + moveX[dir];
            int nextY = currHead[1] + moveY[dir];

            // 이동한 곳이 벽이나 자기 몸이라면 그대로 종료
            if (isOutOfBound(nextX, nextY, N) || map[nextX][nextY] == 1) break;
            snake.addFirst(new int[] {nextX, nextY});   // 머리 한칸 이동

            if (map[nextX][nextY] != 7) {
                int[] temp = snake.removeLast();    // 사과를 먹은게 아니라면 꼬리 한칸 제거
                map[temp[0]][temp[1]] = 0;          // 꼬리 한칸 제거했으므로 비어 있는 표기
            }

            map[nextX][nextY] = 1;                  // 이동한 좌표 뱀으로 표기

            // printSnake(map);                        // 현재 이동현황 출력

            // 왼쪽 또는 오른쪽으로 90도 방향 전환
            if (chageDir.containsKey(time)) {
                dir = chageDir.get(time) ? (dir + 1) % 4 : Math.abs(dir - 1 + 4) % 4;
            }
        }

        System.out.println(time);
    }
}
