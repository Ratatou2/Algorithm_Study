/*
[백준]
2738, 행렬 덧셈

[문제파악]
N*M크기의 두 행렬 A와 B가 주어졌을 때, 두 행렬을 더하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 행렬의 크기 N 과 M이 주어진다.
- 둘째 줄부터 N개의 줄에 행렬 A의 원소 M개가 차례대로 주어진다.
- 이어서 N개의 줄에 행렬 B의 원소 M개가 차례대로 주어진다.
- N과 M은 100보다 작거나 같고, 행렬의 원소는 절댓값이 100보다 작거나 같은 정수이다.

[출력]
- 첫째 줄부터 N개의 줄에 행렬 A와 B를 더한 행렬을 출력한다.
- 행렬의 각 원소는 공백으로 구분한다.

[구현방법]
for문 돌면서 그냥 제 위치와 같은 상대를 더하는 것이 답!

[보완점]
- BufferedReader를 두번 열면 안되는 것을 배웠다...
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] inputMatrix(BufferedReader br, int N, int M) throws IOException {
        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                temp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        return temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 두 행렬 A와 B를 입력 받는다
        int[][] A = inputMatrix(br, N, M);
        int[][] B = inputMatrix(br, N, M);

        // 둘의 같은 위치를 더함
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(A[i][j] + B[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}