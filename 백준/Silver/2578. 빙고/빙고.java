

/*
[백준]
2578, 빙고

[문제파악]
빙고 게임은 다음과 같은 방식으로 이루어진다.
먼저 아래와 같이 25개의 칸으로 이루어진 빙고판에 1부터 25까지 자연수를 한 칸에 하나씩 쓴다
다음은 사회자가 부르는 수를 차례로 지워나간다.
예를 들어 5, 10, 7이 불렸다면 이 세 수를 지운 뒤 빙고판의 모습은 다음과 같다.
차례로 수를 지워가다가 같은 가로줄, 세로줄 또는 대각선 위에 있는 5개의 모든 수가 지워지는 경우 그 줄에 선을 긋는다.
이러한 선이 세 개 이상 그어지는 순간 "빙고"라고 외치는데, 가장 먼저 외치는 사람이 게임의 승자가 된다.
철수는 친구들과 빙고 게임을 하고 있다.
철수가 빙고판에 쓴 수들과 사회자가 부르는 수의 순서가 주어질 때, 사회자가 몇 번째 수를 부른 후 철수가 "빙고"를 외치게 되는지를 출력하는 프로그램을 작성하시오.

[입력]
첫째 줄부터 다섯째 줄까지 빙고판에 쓰여진 수가 가장 위 가로줄부터 차례대로 한 줄에 다섯 개씩 빈 칸을 사이에 두고 주어진다.
여섯째 줄부터 열째 줄까지 사회자가 부르는 수가 차례대로 한 줄에 다섯 개씩 빈 칸을 사이에 두고 주어진다.
빙고판에 쓰여진 수와 사회자가 부르는 수는 각각 1부터 25까지의 수가 한 번씩 사용된다.

[출력]
첫째 줄에 사회자가 몇 번째 수를 부른 후 철수가 "빙고"를 외치게 되는지 출력한다.

[구현방법]
- 이거... 일단 5개를 부를 때까진 기다리고 (빙고의 최소 조건) 그 이후로부터는 X좌표 불릴 때마다 9방향 탐색
- 9방향 탐색할 때, 서로 상반되는 방향으로 진행 시작해서 BFS로 X가 안나오는 끝에 도달할 때까지 반복
- 그렇게 갯수가 총 5개면 빙고이니까 현재 부른 수를 출력

- 아... 뭔가 실버 4답지 않아서 찾아보니  이거 그냥 매번 12줄을 확인하면 된다네...? (가로 5줄, 세로 5줄, 대각선 2줄)
- 특히 이런식으로 짤 경우 빙고 찾는 부분은 그냥 100% 하드코딩이다

[보완점]

*/

import java.io.*;
import java.util.*;

public class Main {
    static boolean isBingo(boolean[][] isCalled) {
        int bingoCount = 0;

        // 가로
        for (int row = 0; row < 5; row++) {
            boolean complete = true;
            for (int col = 0; col < 5; col++) {
                if (!isCalled[row][col]) {
                    complete = false;
                    break;
                }
            }
            if (complete) bingoCount++;
        }

        // 세로
        for (int col = 0; col < 5; col++) {
            boolean complete = true;
            for (int row = 0; row < 5; row++) {
                if (!isCalled[row][col]) {
                    complete = false;
                    break;
                }
            }
            if (complete) bingoCount++;
        }

        // 대각선 (왼쪽 위 → 오른쪽 아래)
        boolean complete = true;
        for (int i = 0; i < 5; i++) {
            if (!isCalled[i][i]) {
                complete = false;
                break;
            }
        }
        if (complete) bingoCount++;

        // 대각선 (오른쪽 위 → 왼쪽 아래)
        complete = true;
        for (int i = 0; i < 5; i++) {
            if (!isCalled[i][4 - i]) {
                complete = false;
                break;
            }
        }
        if (complete) bingoCount++;

        return 3 <= bingoCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[5][5];
        Map<Integer, int[]> location = new HashMap<>();

        // 맵 입력 받기
        for (int row = 0; row < 5; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int col = 0; col < 5; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                location.put(map[row][col], new int[] {row, col});
            }
        }

        // 번호 지우기
        boolean[][] isCalled = new boolean[5][5];
        int count = 0;

        for (int row = 0; row < 5; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int col = 0; col < 5; col++) {
                count++;
                int callNum = Integer.parseInt(st.nextToken());
                int[] curr = location.get(callNum);

                isCalled[curr[0]][curr[1]] = true;
                if (5 <= count && isBingo(isCalled)) {
                    System.out.println(count);
                    return;
                }
            }
        }
    }
}
