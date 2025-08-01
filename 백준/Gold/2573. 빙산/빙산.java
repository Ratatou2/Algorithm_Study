

/*
[백준]
2573, 빙산

[문제파악]
지구 온난화로 인하여 북극의 빙산이 녹고 있다.
빙산을 그림 1과 같이 2차원 배열에 표시한다고 하자.
빙산의 각 부분별 높이 정보는 배열의 각 칸에 양의 정수로 저장된다.
빙산 이외의 바다에 해당되는 칸에는 0이 저장된다.
그림 1에서 빈칸은 모두 0으로 채워져 있다고 생각한다.


 	2	4	5	3
 	3	 	2	5	2
 	7	6	2	4

그림 1. 행의 개수가 5이고 열의 개수가 7인 2차원 배열에 저장된 빙산의 높이 정보

빙산의 높이는 바닷물에 많이 접해있는 부분에서 더 빨리 줄어들기 때문에, 배열에서 빙산의 각 부분에 해당되는 칸에 있는 높이는 일년마다
그 칸에 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다.
단, 각 칸에 저장된 높이는 0보다 더 줄어들지 않는다.
바닷물은 호수처럼 빙산에 둘러싸여 있을 수도 있다.
따라서 그림 1의 빙산은 일년후에 그림 2와 같이 변형된다.

그림 3은 그림 1의 빙산이 2년 후에 변한 모습을 보여준다.
2차원 배열에서 동서남북 방향으로 붙어있는 칸들은 서로 연결되어 있다고 말한다.
따라서 그림 2의 빙산은 한 덩어리이지만, 그림 3의 빙산은 세 덩어리로 분리되어 있다.


 	 	2	4	1
 	1	 	1	5
 	5	4	1	2

그림 2


 	 	 	3
 	 	 	 	4
 	3	2

그림 3

한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램을 작성하시오.
그림 1의 빙산에 대해서는 2가 답이다.
만일 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력한다.

[입력]
첫 줄에는 이차원 배열의 행의 개수와 열의 개수를 나타내는 두 정수 N과 M이 한 개의 빈칸을 사이에 두고 주어진다.
N과 M은 3 이상 300 이하이다.
그 다음 N개의 줄에는 각 줄마다 배열의 각 행을 나타내는 M개의 정수가 한 개의 빈 칸을 사이에 두고 주어진다.
각 칸에 들어가는 값은 0 이상 10 이하이다.
배열에서 빙산이 차지하는 칸의 개수, 즉, 1 이상의 정수가 들어가는 칸의 개수는 10,000 개 이하이다.
배열의 첫 번째 행과 열, 마지막 행과 열에는 항상 0으로 채워진다.

[출력]
첫 줄에 빙산이 분리되는 최초의 시간(년)을 출력한다.
만일 빙산이 다 녹을 때까지 분리되지 않으면 0을 출력한다.

[구현방법]
1) 우선 입력을 받을 때, 빙산 좌표를 기록해둔다
2) 그리고 빙산을 기준으로 상하좌우 탐색을 하여 바다랑 닿아있는 면적만큼 빙산에서 뺀다
3) 위 과정을 모든 빙산에게 적용하였다면 year를 + 1하고, 빙산 하나를 기준으로 BFS 탐색을 진행하여 서로 연결되어있는지 체크
4) 여전히 연결되어있다면 반복, 탐색이 2번 이상 이어진다면, 빙산은 끊어진 것으로 간주 해당 시점에서 year를 출력하고 종료한다

- 내 아이디어에서 유의할 점은 2번을 진행할 때, 빙산 계산은 어디에 기록해뒀다가 일괄로 하는 것이 좋다는 것이다
- 예를들어 A와 B가 붙어있는 빙산이라고하자
- 이때 A가 두개의 면적이 닿아있고, 2라서 이번에 녹아버렸다 (=0)
- 근데 이걸 미리 계산해버리면 B 입장에선 아직 A는 나랑 같이 녹아야해서 빙산 취급이어야 하지만, 숫자로는 0으로 바다취급이기 때문에 괜시리 바다면적이 +1이 될 수 있다는 것이다

[보완점]
- 메모리 초과 발생 (원인으로 예측되는 사안)
    1) 불필요한 클래스 -> int[]로 대체
    2) isVisited를 매번 생성 -> 재활용하기 (Array.fill)
    3) poll() 기반의 Queue는 매 턴 메모리 복사가 발생한다고 함 -> List로 바꾸는게 더 효율적임

- 셋다 적용했는데도 여전히 3%에서 터진다...
- 찾아보니 int[] 말고 int로 압축해서 좌표를 저장하는 방법이 있단다
- 가로 길이인 M을 이용해서 좌표를 1줄로 쭉~ 늘여뜨리는 방식이라고 생각하면 된다
        int pos = x * M + y;
        queue.add(pos);

        // 꺼낼 때
        int curr = queue.poll();
        int cx = curr / M;
        int cy = curr % M;

    - 예시를 보면 아래 같은 배열이 있다고 하자
        (0,0) (0,1) (0,2) (0,3) (0,4)
        (1,0) (1,1) (1,2) (1,3) (1,4)
        (2,0) (2,1) (2,2) (2,3) (2,4)

    - 이 때, 우리는 이것을 한줄로 쭉 피면 아래와 같은 패턴을 찾을 수 있다
        (0,0) → 0 * 5 + 0 = 0
        (0,1) → 0 * 5 + 1 = 1
        (1,0) → 1 * 5 + 0 = 5
        (2,3) → 2 * 5 + 3 = 13

    - 이것을 pos라고 하며 좌표를 압축하는 방법이다


- 이것도 해결방안이 아니었다...
- 오히려 N과 M의 최댓값은 300이므로 빙하의 상태를 담은 List를 구현해서 쓰기보단, map을 전체 탐색하는 것이 올바른 풀이방법이었다...
- 메모리보다 시간이 더 여유로웠던 셈
- List<Integer>를 생성하면 각 요소마다 박싱(boxing)된 Integer 객체를 생성하므로 메모리 비용이 훨씬 더 큰 것이다.. ㅠ
- 더군다나 기존 코드는 List를 매번 새로 만들고 교체했기에 객체가 누적되면 GC(Garbage Collector) 대상이 되고, 힙이 압박이 되고, 결국 메모리 초과 되는 것이다
- 결국... 최대 크기의 map이라고 해봐야 300 * 300이니까 9만이다
- 그러나 바다면 넘어갈 것이기 때문에 (추가 연산 실행 X) 사실상 1초면 넉넉하다는 것이다
    - 더군다나 빙하가 줄어들수록 연산량도 줄어든다!
*/

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] isVisited;

    static int[] moveX = {-1, 0, 1, 0};
    static int[] moveY = {0, 1, 0, -1};

    // isVisited 초기화
    static void resetIsVisited() {
        for (boolean[] temp : isVisited) {
            Arrays.fill(temp, false);
        }
    }

    // 맵 바깥으로 나갔는지 체크
    static boolean isOutOfBound(int x, int y) {
        return x < 0 || N <= x || y < 0 || M <= y;
    }

    // 빙산 녹이기
    static void meltIceberg() {
        int[][] melt = new int[N][M];

        // 전체 맵 탐색하며 녹는 양 계산
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (map[x][y] == 0) continue;   // 바다인 경우는 패스

                // 사방탐색 시작
                int count = 0;  // 녹아야 하는 양을 계산
                for (int i = 0; i < 4; i++) {
                    int nextX = x + moveX[i];
                    int nextY = y + moveY[i];
                    
                    // 이동한 좌표가 범위를 벗어났거나, 바다가 아니면 패스
                    if (isOutOfBound(nextX, nextY) || map[nextX][nextY] != 0) continue;
                    count++;
                }
                
                // 녹은 빙하 기록
                melt[x][y] = count;
            }
        }

        // 녹이기 적용
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                // 바다면 패스
                if (map[x][y] <= 0) continue;
                
                map[x][y] = Math.max(map[x][y] - melt[x][y], 0);
            }
        }
    }

    // 빙산끼리 연결되어있는지 체크
    static boolean isConnected() {
        resetIsVisited();
        boolean foundStart = false;
        int totalIce = 0;
        int startX = 0;
        int startY = 0;

        // 시
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                // 빙하 여부 체크
                if (0 < map[x][y]) {
                    totalIce++;  // 추후에 모든 빙하가 연결됐는지 확인하기 위해 count
                    
                    // 빙하이면서, 시작 지점을 등록해둔 적이 없을 땐, 현재 빙하를 시작지점으로 갱신
                    if (!foundStart) {
                        startX = x;
                        startY = y;
                        foundStart = true;
                    }
                }
            }
        }

        // 다 녹았다면 종료 (다 녹았으면 연결됐는지 탐색하는 과정은 무의미하다)
        if (totalIce == 0) return true; 

        // 연결된 빙하의 갯수를 체크
        int connected = BFS(startX, startY);
        
        // 연결된 빙하의 갯수가 전체 빙하의 갯수와 같다면 아직 분리된 것이 아니다
        return connected == totalIce;
    }

    // BFS로 연결된 빙산 수 세기
    static int BFS(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        isVisited[x][y] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int tempX = curr[0];
            int tempY = curr[1];

            // 사방탐색 진행
            for (int i = 0; i < 4; i++) {
                int nextX = tempX + moveX[i];
                int nextY = tempY + moveY[i];

                // 범위 밖으로 나갔거나, 방문했던 곳이거나, 바다면 패스
                if (isOutOfBound(nextX, nextY) || isVisited[nextX][nextY] || map[nextX][nextY] == 0) continue;

                // 방문처리하고, Q에 추가한 뒤, 빙하 + 1 진행
                isVisited[nextX][nextY] = true;
                queue.add(new int[]{nextX, nextY});
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isVisited = new boolean[N][M];
        int year = 0;

        // map 입력 받기
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            // 하나의 섬으로 연결되어있지 않으면 중단
            if (!isConnected()) break;

            // 빙하를 한번 녹이고, 1년 증가
            meltIceberg();
            year++;

            // 다 녹은 경우
            boolean allMelted = true;
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    // 하나라도 녹지 않았다면? 중단
                    if (0 < map[x][y]) {
                        allMelted = false;
                        break;
                    }
                }

                // 전부 녹지 않았으면 중단한다 (한번 더 녹여야하니까)
                if (!allMelted) break;
            }

            // 전부 녹았으면 또 녹일 필요 없다 (다만 답은 0을 출력)
            if (allMelted) {
                year = 0;
                break;
            }
        }

        System.out.println(year);
    }
}