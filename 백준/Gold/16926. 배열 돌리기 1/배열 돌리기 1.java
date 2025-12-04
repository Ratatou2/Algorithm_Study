

/*
[백준]
16926, 배열 돌리기 1

[문제파악]
크기가 N×M인 배열이 있을 때, 배열을 돌려보려고 한다. 배열은 다음과 같이 반시계 방향으로 돌려야 한다.

A[1][1] ← A[1][2] ← A[1][3] ← A[1][4] ← A[1][5]
   ↓                                       ↑
A[2][1]   A[2][2] ← A[2][3] ← A[2][4]   A[2][5]
   ↓         ↓                   ↑         ↑
A[3][1]   A[3][2] → A[3][3] → A[3][4]   A[3][5]
   ↓                                       ↑
A[4][1] → A[4][2] → A[4][3] → A[4][4] → A[4][5]
예를 들어, 아래와 같은 배열을 2번 회전시키면 다음과 같이 변하게 된다.

1 2 3 4       2 3 4 8       3 4 8 6
5 6 7 8       1 7 7 6       2 7 8 2
9 8 7 6   →   5 6 8 2   →   1 7 6 3
5 4 3 2       9 5 4 3       5 9 5 4
 <시작>         <회전1>        <회전2>
배열과 정수 R이 주어졌을 때, 배열을 R번 회전시킨 결과를 구해보자.

[입력]
첫째 줄에 배열의 크기 N, M과 수행해야 하는 회전의 수 R이 주어진다.
둘째 줄부터 N개의 줄에 배열 A의 원소 Aij가 주어진다.

[출력]
입력으로 주어진 배열을 R번 회전시킨 결과를 출력한다.

[구현방법]
- 돌리는 규칙만 구하면 된다
- 즉 몇번을 회전시키든 4로 나눠서 회전하면 된다
- 돌렸을 때의 좌표를 그냥 하드코딩하든 규칙으로 찾든 하면 됨

- 땡! 정사각형이었으면 맞았겠지만, 이번 문제에서는 틀린 방식임
- 오히려 맨 바깥부터 한층씩 들어가서 한겹씩 돌린다고 생각하고 접근해야함
- 이러면 규칙을 찾아야하는데... 이런게 제일 싫다 ㅠ 귀찮
- 일단 첫번째 레이어라고 가정하면 이런 값이 들어간다
    <<layer 1>>
    - 맨 윗줄 : 0, [0 ~ M-1]
    - 중간(첫 시작과 맨 끝부분) [1~N-2], 0, [1~N-2], [M-1]
    - 맨 아랫줄 : N-1, [0 ~M-1]
- 이제 여기서부터 범위 한칸씩 줄여나가면서 조여가면 가장 안쪽 레이어까지 도달할 수 있다
- 그럼 R % (각 레이어의 전체 길이) 해주면 실질적으로 몇 칸 움직여야하는지 알게됨
- 내가 짠 레이어 구하는 초기 코드는 회전을 전-혀 고려하지 않아서 수정이 필요함..

[보완점]
- Collections에 ratate 함수가 있는데 이건 얼마나 빨라질까 궁금
*/

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[][] map;

    // 현재 레이어 가져오는 함수
    static List<Integer> getLayer (int index) {
        int top = index;
        int left = index;
        int bottom = N - 1 - index;
        int right = M - 1 - index;

        List<Integer> layerNums = new ArrayList<>();

        // 회전 규칙은 맨 윗줄 -> 오른쪽 -> 바닥 (뒤에서부터) -> 왼쪽 (아래서부터)
        // 1. 맨 윗줄
        for (int i = top; i < M - index; i++) {
            layerNums.add(map[top][i]);
        }

        // 2. 맨 오른쪽
        for (int i = index + 1; i < N - index; i++) {
            layerNums.add(map[i][right]);
        }

        // 3. 맨 아랫줄 (끝에서부터)
        for (int i = M - index - 2; index - 1 < i; i--) {
            layerNums.add(map[bottom][i]);
        }

        // 4. 맨 왼쪽 (밑에서부터)
        for (int i = N - index - 2; index < i; i--) {
            layerNums.add(map[i][left]);
        }

        return layerNums;
    }

    // Map 출력 함수
    static String printMap() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                sb.append(map[row][col]).append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    // 회전시키는 함수
    static List<Integer> rotate (List<Integer> input) {
        int howManyRotate = R % input.size();

        // 함수 구현
        Collections.rotate(input, -howManyRotate);

        // 직접 구현
//        for (int r = 0; r < howManyRotate; r++) {
//            int temp = input.get(0);
//            input.remove(0);
//            input.add(temp);
//        }

        return input;
    }

    // 회전 시킨 값을 입력하는 함수
    static void writeLayer(int index, List<Integer> input) {
        int top = index;
        int left = index;
        int bottom = N - 1 - index;
        int right = M - 1 - index;

        int layerIndex = 0;

        // 1. 맨 윗줄
        for (int i = top; i < M - index; i++) {
            map[top][i] = input.get(layerIndex++);
        }

        // 2. 맨 오른쪽
        for (int i = index + 1; i < N - index; i++) {
            map[i][right] = input.get(layerIndex++);
        }

        // 3. 맨 아랫줄 (끝에서부터)
        for (int i = M - index - 2; index - 1 < i; i--) {
            map[bottom][i] = input.get(layerIndex++);
        }

        // 4. 맨 왼쪽 (밑에서부터)
        for (int i = N - index - 2; index < i; i--) {
            map[i][left] = input.get(layerIndex++);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 맵(숫자 배치) 기록하기
        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(input.nextToken());
            }
        }

        int layerCount = Math.min(N, M) / 2;

        for (int layer = 0; layer < layerCount; layer++) {
            List<Integer> currLayer = getLayer(layer);

            currLayer = rotate(currLayer);
            writeLayer(layer, currLayer);
        }

        System.out.println(printMap());
    }
}
