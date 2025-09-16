

/*
[백준]
16234, 인구 이동

[문제파악]
N×N크기의 땅이 있고, 땅은 1×1개의 칸으로 나누어져 있다.
각각의 땅에는 나라가 하나씩 존재하며, r행 c열에 있는 나라에는 A[r][c]명이 살고 있다.
인접한 나라 사이에는 국경선이 존재한다.
모든 나라는 1×1 크기이기 때문에, 모든 국경선은 정사각형 형태이다.
오늘부터 인구 이동이 시작되는 날이다.
인구 이동은 하루 동안 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.
국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
연합을 해체하고, 모든 국경선을 닫는다.
각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 N, L, R이 주어진다. (1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)
둘째 줄부터 N개의 줄에 각 나라의 인구수가 주어진다.
r행 c열에 주어지는 정수는 A[r][c]의 값이다. (0 ≤ A[r][c] ≤ 100)
인구 이동이 발생하는 일수가 2,000번 보다 작거나 같은 입력만 주어진다.

[출력]
인구 이동이 며칠 동안 발생하는지 첫째 줄에 출력한다.

[구현방법]
- 이거 그냥 좀 정직하게 구현해야할 것 같은데
- BFS를 사용하고, (0,0)에서 탐색하며 진행 (N이 최대 50이라 완탐 가능할듯 싶음)
- (0,0)에서부터 시작해서 상하좌우 탐색하고, 하나라도 연결되면? 그것을 Q에 담고 연결된 모든 친구들을 구한다?
- 아니 내가 아직 해결 못한 지점은 Q로 구현하더라도 분명 섬처럼 동떨어져있지만 서로는 연결된, 또다른 Q가 필요할텐데 얘네들끼리 어떻게 구분을 하지?
- 하나의 Q에 담으면 이게 구별이 안될테고, 둘이 다른 지역이라 각각 더하고 나눠야하는데 전체로 보고 계산하면 그것도 문제인데

- 이거 내가 막힌 지점 해결책 찾음
- 아래처럼 풀면 됨
- 우선 모든 칸을 탐색하는 큰 반복문을 하나 짠다
- if (방문한적 없는 곳이라면) 칸을 하나씩 지나면서 사방탐색 진행
- 해당 사방탐색이 유의미한 차이를 낸다면? 연합이 될 다음 친구를 Q에 집어넣음 (이때 방문처리)
- 그런 식으로 연결될 수 있는 모든 지점을 찾는다
- 그리고 이게 한 세트임
- 이렇게 만들어진 한 세트를 List에 저장함 (좌표와 값을 함께)
- 위 과정을 마지막칸에 도달할 때까지 반복함
- 마지막칸에 도착했으면 이제 세트를 하나씩 꺼내서 평균값을 계산하고 좌표값으로 하나씩 갱신함
- 모든 세트를 소모할 때까지 반복
- 위 과정을 차이를 하나도 구하지 못하고 (= 세트의 size가 0일 때까지) 마지막에 도달할 때까지 반복

[보완점]
- 잘푼 사람들보다 대략 3-4배 느림
- 일단 불필요한 클래스부터 제거해보기
- 해보니까 엄청 유의미하진 않은듯...?

- 그 다음은 isVisited 재사용하기 (계산할 때 방문처리 해제)
- 이것보단 round = 1처럼 방문처리를 숫자로 하는게 더 낫겠다는 아이디어도 있음

- 그 다음은 BFS 계산할 때 그냥 평균은 연합이 정해진 순간에 그냥 계산해둬도 된다는 것!
- List<List<int[]>>로 따로 저장해두지 않아도 됨
*/

import java.io.*;
import java.util.*;

public class Main {
    static int[] moveX = {0, 1, 0, -1};
    static int[] moveY = {-1, 0, 1, 0};

    static int N, L, R;
    static int[][] map;
    static boolean[][] isVisited;

    static boolean isOutOfBound (int x, int y) {
        return x < 0 || y < 0 || N <= x || N <= y;
    }

    static boolean isOpenBorder (int curr, int next) {
        int diff = Math.abs(curr - next);

        return L <= diff && diff <= R;
    }

    static void printMap () {
        StringBuilder sb = new StringBuilder();
        sb.append("==================\n");
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                sb.append(map[row][col] + " ");
            }
            sb.append("\n");
        }

        sb.append("==================\n");
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                sb.append((isVisited[row][col] ? "⬜️" : "⬛️") + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());  // 이상
        R = Integer.parseInt(st.nextToken());  // 이하

        map = new int[N][N];


        // 맵 입력받기
        for (int row = 0; row < N; row++) {
            StringTokenizer temp = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(temp.nextToken());
            }
        }

        int count = 0;
        while (true) {
            boolean isMoved = false;
            isVisited = new boolean[N][N];

            // 맵 탐색
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    // 이미 방문한 경력이 있다면 다른 연합에 포함되어있다는 의미니까 탐색할 이유가 없음
                    if (isVisited[row][col]) continue;

                    int unionTotal = map[row][col];
                    List<int[]> union = new ArrayList<>();
                    Queue<int[]> q = new ArrayDeque<>();
                    int[] start = new int[] {row, col};
                    q.add(start);
                    union.add(start);

                    while (!q.isEmpty()) {
                        int[] curr = q.poll();
                        isVisited[curr[0]][curr[1]] = true;  // 방문처리

                        // 사방탐색
                        for (int i = 0; i < 4; i++) {
                            int nextX = curr[0] + moveX[i];
                            int nextY = curr[1] + moveY[i];

                            // 범위 밖으로 초과 or 이미 방문한 경우 or 국경선 개방 조건을 달성하지 못한 경우, 모두 통과
                            if (isOutOfBound(nextX, nextY)
                                    || isVisited[nextX][nextY]
                                    || !isOpenBorder(map[curr[0]][curr[1]], map[nextX][nextY])) continue;

                            int[] temp = new int[] {nextX, nextY};
                            isVisited[temp[0]][temp[1]] = true;  // 방문처리 (Q에 넣기 전에 해야만 중복막을 수 있음)
                            q.add(temp);
                            union.add(temp);
                            unionTotal += map[temp[0]][temp[1]];
                        }
                    }

                    // 모든 사방 탐색이 끝났는데 union의 size가 1이라면 시작지점외엔 없었다는 의미다
                    // 즉시, 방문처리 해제 후, 아무것도 하지 않는다
                    // 반면에 연합이 형성되었다면, 해당 연합을 기록해둔다
                    if (1 < union.size()) {
                        isMoved = true;
                        int avg = unionTotal / union.size();

                        // 평균 값 계산
                        for (int[] loc : union) {
                            map[loc[0]][loc[1]] = avg;
                        }
                    }
                }
            }

            if (!isMoved) break;
            count++;
        }

        System.out.println(count);
    }
}
