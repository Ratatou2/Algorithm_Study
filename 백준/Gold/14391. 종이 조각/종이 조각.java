

/*
[백준]
14391, 종이 조각

[문제파악]
영선이는 숫자가 쓰여 있는 직사각형 종이를 가지고 있다. 종이는 1×1 크기의 정사각형 칸으로 나누어져 있고, 숫자는 각 칸에 하나씩 쓰여 있다.
행은 위에서부터 아래까지 번호가 매겨져 있고, 열은 왼쪽부터 오른쪽까지 번호가 매겨져 있다.
영선이는 직사각형을 겹치지 않는 조각으로 자르려고 한다. 각 조각은 크기가 세로나 가로 크기가 1인 직사각형 모양이다.
길이가 N인 조각은 N자리 수로 나타낼 수 있다.
가로 조각은 왼쪽부터 오른쪽까지 수를 이어 붙인 것이고, 세로 조각은 위에서부터 아래까지 수를 이어붙인 것이다.
아래 그림은 4×4 크기의 종이를 자른 한 가지 방법이다.

각 조각의 합은 493 + 7160 + 23 + 58 + 9 + 45 + 91 = 7879 이다.
종이를 적절히 잘라서 조각의 합을 최대로 하는 프로그램을 작성하시오.

[입력]
첫째 줄에 종이 조각의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 4)
둘째 줄부터 종이 조각이 주어진다. 각 칸에 쓰여 있는 숫자는 0부터 9까지 중 하나이다.

[출력]
영선이가 얻을 수 있는 점수의 최댓값을 출력한다.

[구현방법]
- 이거 뭐 어케.. 자르라고...? 하나씩?? 말도 안되라고 생각했는데 사실 그게 맞았다
- N, M이 최대 4개라서 최대칸수는 16칸
- 각 칸마다 가진 선택지는 2가지
    - 1번, 가로조각으로 쓴다
    - 2번, 세로조각으로 쓴다
- 그럼 결과적으로 선택지가 2개인 칸이 총 16칸이니까 2^16개가 총 경우의 수가 주어진 2초내에 끊긴다 (2^16 = 65536)
    - 걍 쉽게 말해서 이 칸은 가로? 세로? 매번 16번 답변하면 경의 수가 저만치 나오고 그래서 비트마스킹 문제가 되는 것
- 내가 의문이 들었던 지점은 '이렇게 모든 경우의 수를 계산하면 직사각형이 아닌 ㄱ, ㄴ, ㅗ, ㅏ, ㅜ, ㅓ 같은 모양도 생기지 않나?' 였음
- 근데 좀만 더 생각해보면 가로 세로를 선택한 시점에서 서로 다르면 그것은 개별의 직사각형이 되는 셈이다
- 즉 가로 세로가 서로 이어질 일이 없음

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        // 입력 받기
        for (int row = 0; row < N; row++) {
            String input = br.readLine();
            for (int col = 0; col < M; col++) {
                map[row][col] = input.charAt(col) - '0';
            }
        }

        int maxSum = 0;
        int total = N * M;  // 모든 경우의 수 진행을 위함 (e.g. 4x4 상황에서 2진수로 변환해서 2^16까지 모두 진행하기 위함)

        // 모든 경우의 수 탐색 (1=가로, 0=세로)
        for (int mask = 0; mask < (1 << total); mask++) {
            int sum = 0;

            // 가로 조각 계산
            for (int row = 0; row < N; row++) {
                int curr = 0;

                for (int col = 0; col < M; col++) {
                    int idx = row * M + col;

                    // 지금의 경우의 수와 비교해서 현재 칸이 가로 칸(1)이면 계산
                    // 특히 이전 숫자와 연결되어있다면 그 값을 계속 가져가면서 10을 곱해서 자릿수를 올려주고, 현재의 숫자는 1의자리로 더하는 식으로 반복
                    // 이것을 반복하면 연결된 1, 9, 3이 있다고 가정하면 1 -> 19 -> 193이 될 수 있다
                    // 연결되어있지 않은 경우를 만나는 순간(세로가 끼어있음), 그간의 값은 sum에 합산, curr는 0으로 다시 초기화한다
                    if ((mask & (1 << idx)) != 0) curr = curr * 10 + map[row][col];
                    else {
                        sum += curr;
                        curr = 0;
                    }
                }

                sum += curr;
            }

            // 세로 조각 계산
            for (int col = 0; col < M; col++) {
                int curr = 0;
                
                for (int row = 0; row < N; row++) {
                    int idx = row * M + col;
                    
                    // 가로의 경우와 똑같다, 0인 경우의 수를 찾고 이전 값과 이어져있다면 자릿수는 10씩 늘려감
                    if ((mask & (1 << idx)) == 0) curr = curr * 10 + map[row][col];
                    else {
                        sum += curr;
                        curr = 0;
                    }
                }

                sum += curr;
            }

            // 하나의 경우의 수 끝날 때마다 max값 체크해서 값 갱신함
            maxSum = Math.max(maxSum, sum);
        }

        System.out.println(maxSum);
    }
}
