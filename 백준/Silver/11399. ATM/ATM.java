/*
[백준]
11399, ATM

[문제파악]
- ATM 앞에 N명의 사람이 줄을 서는데, 사람은 1번부터 N번까지 번호가 메겨져 있다
- i번 사람이 돈을 인출하는데 걸리는 시간은 p_i분이다
- 사람들이 줄을 서는 순서에 따라 돈을 인출하는데 필요한 시간의 합이 달라진다.
- 사람마다 소요되는 시간 : [3, 1, 4, 3, 2]
- 사람 순서 : [1, 2, 3, 4, 5]
- 위와 같이 진행될 경우 1번은 3분이 걸려 2번은 총 4분을 기다려야 돈을 뽑을 수 있다
- 이와 같은 식으로 총 39분 소요된다
- 줄을 [2, 5, 1, 4, 3] 순으로 세우면 32분이 소요된다

- 이처럼 줄을 서 있는 사람의 수 N명이 주어지고, 돈을 인출하는데 걸리는 시간 P_i가 주어졌을 때, 각 사람이 돈을 인출하는데 필요한 시간의 합의 최솟값을 구하는 프로그램을 작성하자

[입력]
- 첫째줄에 사람의 수 N (1<=N<=1,000)
- 둘째줄에 각 사람이 돈을 인출하는데 걸리는 시간 P_i (1<=P<=1,000)

[출력]
- 각 사람이 돈을 인출하는데 필요한 시간의 합의 최솟값을 출력!

[구현방법]
- 이것도 가장 적게 드는 시간부터 정렬해가지고 더해야할듯?

[보완점]

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
        int[] times = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 시
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(times);

//        System.out.println(Arrays.toString(times));

        int totalTime = times[0];
        int prevTime = times[0];
        for (int i = 1; i < N; i++) {
            prevTime += times[i];  // 이전 값과 더한 값이 더해야할 값 (이대로 누적)
            totalTime += prevTime;  // 더해야할 값을 전부다 더한 것이 정답
        }

        System.out.println(totalTime);
    }
}