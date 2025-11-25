

/*
[백준]
14500, 테트로미노

[문제파악]
폴리오미노란 크기가 1×1인 정사각형을 여러 개 이어서 붙인 도형이며, 다음과 같은 조건을 만족해야 한다.

정사각형은 서로 겹치면 안 된다.
도형은 모두 연결되어 있어야 한다.
정사각형의 변끼리 연결되어 있어야 한다.
즉, 꼭짓점과 꼭짓점만 맞닿아 있으면 안 된다.
정사각형 4개를 이어 붙인 폴리오미노는 테트로미노라고 하며, 다음과 같은 5가지가 있다.

아름이는 크기가 N×M인 종이 위에 테트로미노 하나를 놓으려고 한다. 종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 정수가 하나 쓰여 있다.
테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램을 작성하시오.
테트로미노는 반드시 한 정사각형이 정확히 하나의 칸을 포함하도록 놓아야 하며, 회전이나 대칭을 시켜도 된다.

[입력]
첫째 줄에 종이의 세로 크기 N과 가로 크기 M이 주어진다. (4 ≤ N, M ≤ 500)
둘째 줄부터 N개의 줄에 종이에 쓰여 있는 수가 주어진다.
i번째 줄의 j번째 수는 위에서부터 i번째 칸, 왼쪽에서부터 j번째 칸에 쓰여 있는 수이다.
입력으로 주어지는 수는 1,000을 넘지 않는 자연수이다.

[출력]
첫째 줄에 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값을 출력한다.

[구현방법]
- 우선 도형의 형태가 정해졌으니까 회전한 형태를 좌표로 지정해서 저장해둔다
- 그렇게 한칸한칸 탐색할 때마다 형태 다돌려가며 좌표에 적힌 숫자 다 더해서 값 계속 갱신
- 근데 이게 2초안에 끝나나? 최악의 경우를 계산해보면 500 * 500 * 5(도형 갯수) * 2(도형마다 오른쪽, 아래 방향으로 두번 진행 필요)
    - 오른쪽, 아래만 탐색하면 되는 이유는 시작점부터 훑으면서 내려올 것이라서 도형의 범위가 겹치는 왼쪽, 위 방향은 뺀 것
    - 그럼 결과적으로 2,500,000면.. 충분히 될 것 같은데

- 아 도형 갯수에 에러가 있는게 5개가 아니다
- 좌표를 하나씩 다 딴다고 생각했을 때 나올수 있는 총 갯수는?
- 이제보니 이 문제의 핵심은 회전, 대칭한 좌표를 잘 기록하는 것이 핵심임
- 그리고 flag를 잘 세워야한다 
- 특히 중간에 취소되는 경우를 잘 봐야함

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static List<int[][]> shapes = new ArrayList<>();

    // 회전 및 대칭 좌표 기본 세팅
    static void initShapes() {
        // 1자 도형
        shapes.add(new int[][]{{0,0},{0,1},{0,2},{0,3}}); // ----
        shapes.add(new int[][]{{0,0},{1,0},{2,0},{3,0}}); // |

        // 정사각형
        shapes.add(new int[][]{{0,0},{0,1},{1,0},{1,1}});

        // L 모양
        shapes.add(new int[][]{{0,0},{1,0},{2,0},{2,1}});
        shapes.add(new int[][]{{0,1},{1,1},{2,1},{2,0}});
        shapes.add(new int[][]{{0,0},{0,1},{1,0},{2,0}});
        shapes.add(new int[][]{{0,0},{0,1},{1,1},{2,1}});
        shapes.add(new int[][]{{0,0},{0,1},{0,2},{1,0}});
        shapes.add(new int[][]{{0,0},{0,1},{0,2},{1,2}});
        shapes.add(new int[][]{{0,2},{1,0},{1,1},{1,2}});
        shapes.add(new int[][]{{0,0},{1,0},{1,1},{1,2}});

        // 번개 모양
        shapes.add(new int[][]{{0,0},{0,1},{1,1},{1,2}});
        shapes.add(new int[][]{{0,1},{1,0},{1,1},{2,0}});
        shapes.add(new int[][]{{0,1},{0,2},{1,0},{1,1}});
        shapes.add(new int[][]{{0,0},{1,0},{1,1},{2,1}});

        // T 모양
        shapes.add(new int[][]{{0,0},{0,1},{0,2},{1,1}});
        shapes.add(new int[][]{{0,1},{1,0},{1,1},{2,1}});
        shapes.add(new int[][]{{0,1},{1,0},{1,1},{1,2}});
        shapes.add(new int[][]{{0,0},{1,0},{1,1},{2,0}});
    }
    
    static boolean isOutOfBound (int x, int y) {
        return x < 0 || y < 0 || N <= x || M <= y;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        initShapes();

        // 지도 입력 받기
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 지도 탐색하며 최댓값 갱신
        int maxSum = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                // 도형좌표 체크
                for (int[][] shape : shapes) {
                    int currSum = 0;
                    boolean overflowMap = false;
                    
                    // 도형 단위 하나씩 직접 더해보기
                    for (int[] location: shape) {
                        int nextX = row + location[0];
                        int nextY = col + location[1];

                        // map 밖으로 넘칠 경우 더 볼 필요 없이 중단
                        if (isOutOfBound(nextX, nextY)) {
                            overflowMap = true;
                            break;
                        }

                        currSum += map[nextX][nextY];
                    }

                    if (!overflowMap) maxSum = Math.max(maxSum, currSum);
                }
            }
        }

        System.out.println(maxSum);
    }
}
