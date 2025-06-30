/*
[백준]
14889, 스타트와 링크

[문제파악]
축구를 하기 위해 모인 사람은 총 N명이고 신기하게도 N은 짝수이다.
이제 N/2명으로 이루어진 스타트 팀과 링크 팀으로 사람들을 나눠야 한다.

BOJ를 운영하는 회사답게 사람에게 번호를 1부터 N까지로 배정했고, 아래와 같은 능력치를 조사했다.
능력치 Sij는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치이다.
팀의 능력치는 팀에 속한 모든 쌍의 능력치 Sij의 합이다.
Sij는 Sji와 다를 수도 있으며, i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 Sij와 Sji이다.

N=4이고, S가 아래와 같은 경우를 살펴보자.

i\j	1	2	3	4
1	 	1	2	3
2	4	 	5	6
3	7	1	 	2
4	3	4	5
예를 들어, 1, 2번이 스타트 팀, 3, 4번이 링크 팀에 속한 경우에 두 팀의 능력치는 아래와 같다.

스타트 팀: S12 + S21 = 1 + 4 = 5
링크 팀: S34 + S43 = 2 + 5 = 7
1, 3번이 스타트 팀, 2, 4번이 링크 팀에 속하면, 두 팀의 능력치는 아래와 같다.

스타트 팀: S13 + S31 = 2 + 7 = 9
링크 팀: S24 + S42 = 6 + 4 = 10
축구를 재미있게 하기 위해서 스타트 팀의 능력치와 링크 팀의 능력치의 차이를 최소로 하려고 한다.
위의 예제와 같은 경우에는 1, 4번이 스타트 팀, 2, 3번 팀이 링크 팀에 속하면 스타트 팀의 능력치는 6, 링크 팀의 능력치는 6이 되어서 차이가 0이 되고 이 값이 최소이다.

[입력]
첫째 줄에 N(4 ≤ N ≤ 20, N은 짝수)이 주어진다.
둘째 줄부터 N개의 줄에 S가 주어진다.
각 줄은 N개의 수로 이루어져 있고, i번 줄의 j번째 수는 Sij 이다.
Sii는 항상 0이고, 나머지 Sij는 1보다 크거나 같고, 100보다 작거나 같은 정수이다.

[출력]
- 첫째 줄에 스타트 팀과 링크 팀의 능력치의 차이의 최솟값을 출력한다.

[구현방법]
- 이거는 내가 이전에 한 선택을 전부 기억하고 있다가 다음 계산을 할 때 염두에 두고 진행해야하는 것 같다
- 실제로 분류도 백트래킹임... 근데 너무 오랜만에 알고리즘을 다시 풀게되어 그런가 하나도 기억나질 않음 ㅠㅠ


[보완점]
백트래킹의 정의 : 백트래킹(backtracking)은 가능한 모든 경우를 탐색하되, 불필요한(또는 조건을 만족하지 않는) 경로는 미리 차단하고 되돌아가는 방식

- 이해하고보면 굉장히 쉬운문제 (거기까지 도달하지 못해서 그럴뿐 ㅠ, 다시 차차 풀어가며 감을 잡자)
- 조합(combination) 코드는 그냥 기본 코드 그자체이다
- 모든 조합을 생성해내고 있음
- 현재 depth에서 하나를 고르고 그 하나에서 갈 수 있는 모든 것을 true로 놓고 계산한다
- 그렇게 전체 depth의 절반에 도달하면 계산을한다
- 계산한을 할 땐, teamStart와 teamLink를 나눠 계산한다
- 즉, 둘다 방문처리가 되어있다면 start 팀인 것이고, 둘다 방문처리가 안되어있다면 다른 팀인 Link팀인 것이다
*/

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] S;
    static boolean[] isVisited;
    static int minDiff = Integer.MAX_VALUE;

    // 조합 만들기
    static void combination(int depth, int start) {
        // depth == N / 2 (= 한 팀을 다 골랐다면, 나머지는 자동으로 링크 팀이 된다)
        if (depth == N / 2) {
            calculate();
            return;
        }

        // 백트래킹 구현 부분
        for (int i = start; i < N; i++) {
            isVisited[i] = true;
            combination(depth + 1, i + 1);
            isVisited[i] = false;
        }
    }

    // 팀 능력치 차이 계산
    static void calculate() {
        int teamStart = 0;
        int teamLink = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isVisited[i] && isVisited[j]) teamStart += S[i][j] + S[j][i];
                else if (!isVisited[i] && !isVisited[j]) teamLink += S[i][j] + S[j][i];
            }
        }

        minDiff = Math.min(minDiff, Math.abs(teamStart - teamLink));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        isVisited = new boolean[N];

        // 입력받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 조합짜기 (여기서 depth는 '선택한 사람 수'이다)
        combination(0, 0);

        System.out.println(minDiff);
    }
}
