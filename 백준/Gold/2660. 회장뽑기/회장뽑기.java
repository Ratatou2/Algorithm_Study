/*
[백준]
2660, 회장뽑기

[문제파악]
- 월드컵 축구의 응원을 위한 모임에서 회장을 선출하려고 한다.
- 이 모임은 만들어진지 얼마 되지 않았기 때문에 회원 사이에 서로 모르는 사람도 있지만, 몇 사람을 통하면 모두가 서로 알 수 있다.
- 각 회원은 다른 회원들과 가까운 정도에 따라 점수를 받게 된다.
- 예를 들어 어느 회원이 다른 모든 회원과 친구이면, 이 회원의 점수는 1점이다.
- 어느 회원의 점수가 2점이면, 다른 모든 회원이 친구이거나 친구의 친구임을 말한다.
- 또한 어느 회원의 점수가 3점이면, 다른 모든 회원이 친구이거나, 친구의 친구이거나, 친구의 친구의 친구임을 말한다.
- 4점, 5점 등은 같은 방법으로 정해진다.
- 각 회원의 점수를 정할 때 주의할 점은 어떤 두 회원이 친구사이이면서 동시에 친구의 친구사이이면, 이 두사람은 친구사이라고 본다.
- 회장은 회원들 중에서 점수가 가장 작은 사람이 된다.
- 회장의 점수와 회장이 될 수 있는 모든 사람을 찾는 프로그램을 작성하시오.

[입력]
- 입력의 첫째 줄에는 회원의 수가 있다.
- 단, 회원의 수는 50명을 넘지 않는다.
- 둘째 줄 이후로는 한 줄에 두 개의 회원번호가 있는데, 이것은 두 회원이 서로 친구임을 나타낸다.
- 회원번호는 1부터 회원의 수만큼 붙어 있다.
- 마지막 줄에는 -1이 두 개 들어있다.

[출력]
- 첫째 줄에는 회장 후보의 점수와 후보의 수를 출력하고, 두 번째 줄에는 회장 후보를 오름차순으로 모두 출력한다.

[구현방법]
- 최대 50명이면 플로이드 워셜 쓸만함
- 친구를 타고 넘어가는 관계일 때마다 1이 증가함 (누적합)

[보완점]
- 바보 같았던게 입력값은 50이상일 수가 있다 (그건 어차피 서로 연결된 간선을 의미하는 것이니까)
- 그런데 노드갯수만 생각해서 간선 입력을 50까지만 제한둬서 틀렸던 것임
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int INF = Integer.MAX_VALUE / 2;
    static int[][] connection;

//    static void print() {
//        // connection 출력
//        StringBuilder sb = new StringBuilder();
//        for (int row = 1; row <= N; row++) {
//            for (int col = 1; col <= N; col++) {
//                sb.append(connection[row][col] == INF ? 0 : connection[row][col]).append(" ");
//            }
//            sb.append("\n");
//        }
//
//        System.out.println(sb);
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        connection = new int[N + 1][N + 1];

        // connection 초기화
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (row == col) continue;
                connection[row][col] = INF;
            }
        }

        // 입력 받기
        while(true) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 종료 조건 (두 입력값이 -1일 때)
            if (a == b && a == -1) break;

            // 친구는 양방향 관계
            connection[a][b] = 1;
            connection[b][a] = 1;
        }

        // 플로이드 워셜
        for (int mid = 1; mid <= N; mid++) {
            for (int start = 1; start <= N; start++) {
                for (int end = 1; end <= N; end++) {
                    // 친구 사이 연관이 없거나, 자기 자신이면 패스
                    if (connection[start][mid] == INF || connection[end][mid] == INF) continue;

                    connection[start][end] = Math.min(connection[start][end], connection[start][mid] + connection[mid][end]);
                }
            }
        }

        // 회장 후보 구하기
        int[] whoIsChairman = new int[N + 1];
        int candidateScore = Integer.MAX_VALUE;
        for (int row = 1; row <= N; row++) {
            int currMax = 0;

            for (int col = 1; col <= N; col++) {
                if (row == col) continue;

                currMax = Math.max(connection[row][col], currMax);
            }

            candidateScore = Math.min(candidateScore, currMax);  // 가장 작은 점수
            whoIsChairman[row] = currMax;  // 현 후보의 점수 기록
        }

        // 정답 찾고 출력하기
        int countCandidate = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < whoIsChairman.length; i++) {
            if (whoIsChairman[i] != candidateScore) continue;
            sb.append(i).append(" ");
            countCandidate++;
        }

        System.out.println(candidateScore + " " + countCandidate + "\n" + sb);
    }
}
