/*
[백준]
1026, 보물

[문제파악]
- 길이가 N인 정수 배열 A와 B가 있다
- 함수 S를 계산한다 (S = A[0] × B[0] + ... + A[N-1] × B[N-1])
- S값을 가장 작게 만들기 위해 배열 A를 재배열한다
- 이때 배열 B는 재배열하지 않는다

[입력]
- 첫째줄에 N (1 <= N <= 50)
- 둘째줄에 A에 있는 N개의 수가 순서대로 주어진다
- 셋째줄에 B에 있는 N개의 수가 순서대로 주어진다
- 0 <= A, B <= 100, 정수

[출력]
-

[구현방법]
-

[보완점]


5
1 1 1 6 0
2 7 8 3 1
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer AStringToken = new StringTokenizer(br.readLine());
        StringTokenizer BStringToken = new StringTokenizer(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(AStringToken.nextToken());
            B[i] = Integer.parseInt(BStringToken.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);

        int S = 0;
        for (int i = 0; i < N; i++) {
            S += A[i] * B[N-1 - i];
        }

        System.out.println(S);
    }
}