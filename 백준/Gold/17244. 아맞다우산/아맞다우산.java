

/*
[백준]
17244, 아맞다우산

[문제파악]
경재씨는 저녁 약속을 가기 전 챙기지 않은 물건들이 있는 지 확인하고 있다.
필요한 물건은 전부 챙긴 것 같았고 외출 후 돌아오는 길에 경재씨는 외쳤다.
"아 맞다 우산!!!"
경재 씨는 매번 외출하고 나서야 어떤 물건을 집에 놓고 왔다는 것을 떠올릴 때마다 자책감에 시달리는 것이 너무 싫었다.
외출이 잦은 경재 씨는 반복되는 일을 근절하기 위해 꼭 챙겨야 할 물건들을 정리해보았다.
하지만 지갑, 스마트폰, 우산, 차 키, 이어폰, 시계, 보조 배터리 등 종류와 개수가 너무 많았다.
평소 불필요한 움직임을 아주 싫어하는 경재 씨는 이 물건들을 최대한 빠르게 챙겨서 외출하는 이동 경로를 알고 싶었다.
경재 씨는 한 걸음에 상하좌우에 인접한 칸으로만 움직일 수 있다.
경재 씨를 위해 집을 위에서 바라본 모습과 챙겨야 할 물건들의 위치들을 알고 있을 때, 물건을 모두 챙겨서 외출하기까지 최소 몇 걸음이 필요한지 구하는 프로그램을 작성하자.

[입력]
첫 번째 줄에는 집의 가로 길이 N과 세로 길이 M이 입력된다. (3 ≤ N, M ≤ 50)
두 번째 줄부터는 집의 구조가 예제 입력과 같이 주어진다.
비어있는 곳은 '.'로 벽은 '#'로 입력된다.
경재 씨의 현재 위치는 S, 나가는 문의 위치는 E, 챙겨야 하는 물건은 종류에 상관없이 X로 입력된다.
챙겨야 하는 물건은 최대 5개까지 있을 수 있다.
집은 언제나 벽으로 둘러싸여 있고, 나가는 문은 언제나 하나이다.

[출력]
S에서 출발하여 모든 물건을 챙겨서 E까지 도착할 수 있는 최소 시간을 출력한다.
모든 물건을 챙길 수 없는 경우는 주어지지 않는다.

[구현방법]
- N이 최대 50이니까 BFS로 쭉 탐색 돌리면서 최소의 경우의 수로 나갈 수 있는걸 찾도록 짜도 될 것 같다
- 문제는 무한 루프가 생길텐데 이것들은 어떻게 처리하느냐..
- X가 5개 되는 시점에서 2/N 이내로 탈출 못하면 break..? 근데 이것보단 더 타이트하게 조일 수 있을 것 같은데
- 근데 어차피 한번이라도 다챙겨서 탈출하면 기준, 즉 비교 가능한 이동횟수가 생기니까 이걸 넘는 순간 더 볼 필요없이 다 빼버리면 괜찮을 것도 같고

- 더 효율적인 방법이 있었다...
- 우리의 목적을 상기시켜보면 '모든 X를 모아가며 start 지점에서 end 지점으로 탈출하는 것'이다
- 그럼 이제 BFS로 S, X, E들 간의 거리를 구해둔다
- 그 뒤에 모든 x를 순회하는 순열을 짜서 각 거리의 합을 구한뒤 그중의 최소값을 고르면 된다
- 참.. 다들 똑똑해 이런 방식을 생각해낸다는게...

- 초기엔 S, E 지점을 따로 관리할까 했으나 그냥 같은 List에 넣는게 낫겠단 생각이 들었음
    - 그런데? 항상 S, X, E 순서로 들어오는게 아니기 때문에 입력을 받을 때는 구분해서 해야한다

[보완점]
- 한 때 순열 오지게 풀어서 다 외웠는데 이번에 기억안나서 충격먹음.. 다시 외워..
- 챙겨야할 물건이 '최대' 5개랬을뿐 늘 있다곤 안헀다 (없을수도 있음 주의...)
    - 그렇기 때문에 S -> E도 미리 구해둬야 함
- 방문처리는 큐에서 꺼낼 때가 아니라, 넣을 떄가 나음 (이후로 들어오는 중복 제거용)
*/

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, minDist;
    static char[][] map;
    static int[][] dist;
    static int[] moveX = {-1, 0 ,1 ,0};
    static int[] moveY = {0, 1, 0, -1};

    // 입력받은 두 지점간의 거리 구하기
    static int BFS(int[] start, int[] end) {
        // 인덱스 0 - x좌표 / 인덱스 1 - y좌표 / 인덱스 2 - 이동한 횟수
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[M][N];

        q.add(new int[] {start[0], start[1], 0});
        isVisited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            // 목표 지점에 도달했다면 이동 횟수 갱신 후 함수 중단 (BFS 특성상 목적지에 도달한 최초가 최단거리다)
            if (curr[0] == end[0] && curr[1] == end[1]) {
                return curr[2];
            }

            for (int i = 0; i < 4; i++) {
                int nextX = curr[0] + moveX[i];
                int nextY = curr[1] + moveY[i];
                int nextMoveCount = curr[2] + 1;

                // 범위 밖으로 나갔거나, 방문한 곳이거나, 벽이면 패스
                if (isOutOfBound(nextX, nextY) || map[nextX][nextY] == '#' || isVisited[nextX][nextY]) continue;

                // 방문처리 (보통 Q에 넣을 때 함)
                isVisited[nextX][nextY] = true;

                // moveCount + 1하고 큐에 입력
                q.add(new int[] {nextX, nextY, nextMoveCount});
            }
        }

        return -1;
    }

    static boolean isOutOfBound(int x, int y) {
        return x < 0 || M <= x || y < 0 || N <= y;
    }

    // 순열로 경로 짜기 (나온 경로는 BFS로 구한 인접행렬 거리로 합산)
    // 거리 구하기 : 현재위치(S) - 순열로 짠 X들의 경로 - 나가는 위치(E)
    static void permutation(int depth, boolean[] isVisited, int index, int currDist, int repeat) {
        if (depth == repeat) {
            minDist = Math.min(minDist, currDist + dist[index][repeat + 1]);
            return;
        }

        for (int i = 1; i <= repeat; i++) {
            if (isVisited[i]) continue;

            isVisited[i] = true;
            permutation(depth + 1, isVisited, i, currDist + dist[index][i], repeat);
            isVisited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 가로
        M = Integer.parseInt(st.nextToken());  // 세로
        map = new char[M][N];
        List<int[]> xLocations = new ArrayList<>();  // 인덱스와 좌표 기억용
        int[] startLocation = null, endLocation = null;
        minDist = Integer.MAX_VALUE;

        // map 입력 받기
        for (int row = 0; row < M; row++) {
            char[] input = br.readLine().toCharArray();

            for (int col = 0; col < N; col++) {
                char temp = input[col];
                map[row][col] = temp;

                // S, E, X들의 좌표 기록해두기
                if (temp == 'X') xLocations.add(new int[] {row, col});
                else if(temp == 'S') startLocation = new int[] {row, col};
                else if (temp == 'E') endLocation = new int[] {row, col};
            }
        }

        // 항상 일관되게 S-X-E 순서를 유지하기 위해 List를 새로 만든다
        List<int[]> locations = new ArrayList<>();
        locations.add(startLocation);
        locations.addAll(xLocations);
        locations.add(endLocation);


        // BFS로 각 좌표끼리의 최단 거리 측정해두기 (인접행렬이 낫겠네요)
        // 인접 행렬은 대각선을 기준으로 대칭이니까 범위를 많이 줄일 수 있다
        int locationCount = locations.size();
        dist = new int[locationCount][locationCount];
        for (int start = 0; start < locationCount; start++) {
            for (int end = start + 1; end < locationCount; end++) {
                // 자기 자신까지의 거리는 기록할 필요없다
                if (start == end) continue;

                int temp = BFS(locations.get(start), locations.get(end));
                dist[start][end] = temp;
                dist[end][start] = temp;
            }
        }

        int countX = xLocations.size();

        // 챙겨야할 물건 없을 수 있음 주의!!!!
        if (countX == 0) minDist = dist[0][1];  // 챙길 물건이 없다는 것은 S와 E 뿐이라는 것 => 인접행렬의 크기가 [2]임 그래서 [0][1]을 최솟값으로 세팅하는 것이다
        else {
            boolean[] isVisited = new boolean[countX + 1];
            isVisited[0] = true;
            permutation(0, isVisited, 0, 0, countX);
        }

        System.out.println(minDist);
    }
}
