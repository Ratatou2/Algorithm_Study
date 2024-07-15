/*
[백준]
19941, 햄버거 분배

[문제파악]
- 기다란 벤치 모양 식탁, 사람들과 햄버거가 혼재되여 놓여있다
- 사람들은 자신의 위치에서 거리가 K 이하인 햄버거를 먹을 수 있다
- 식탁의 길이 N, 햄버거 선택 가능 거리 K가 주어졌을 때 햄버거를 먹을 수 있는 사람의 최대수를 구하라

[입력]
- 첫째줄에 두 정수 N, K
- 둘째줄에 사람과 햄버거의 위치 가 주어짐
    - P(사람)와 H(햄버거)로 이루어지는 길이 N인 문자열로 주어진다

[출력]
- 햄버거를 먹을 수 있는 최대 사람의 수를 표기

[구현방법]
- 처음에 감을 잘 못 잡았는데 이 문제의 핵심은 가장 멀리있는 햄버거부터 집어 먹는 것이다.
- 특히 내 경우엔 왼쪽부터 탐색해야한다. (for문으로 왼쪽부터 탐색했기 때문임)
- 오른쪽으로 이동할수록 왼쪽에 있는 버거들이 먼저 소진되어야 함.
- 그 이유는 오른쪽부터 먹어치우면 뒤에 갈수록 먹을 수 있는 인원들이 줄어들기 때문이다.
- 그리고 P 기준 가장 멀리 뻗을 수 있는 지점부터 탐색을 해야 최대로 많은 인원들이 햄버거를 먹을 수 있다.

[보완점]
- 오른쪽 탐색할 땐, 가장 가까운 것부터 먹어야 함...
- 그래야 오른쪽 친구들이 왼쪽부터 탐색할 때 문제 없이 집어먹을 수 있음..!
- 이런 디테일은 진짜;; 많이 풀어보는 수밖에!
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static int N, K;
    public static String[] table;

    public static void printTableState() {
        System.out.println(Arrays.toString(table));
    }

    public static boolean findBurger(int index) {

        // 왼쪽부터 탐색
        for (int i = K; 1 <= i; i--) {
            if (index-i < 0) continue;  // 범위 초과면 패스
            if (table[index-i].equals("H")) {
                table[index-i] = "_";
                return true;
            }
        }

        // 오른쪽 탐색
        for (int i = 1; i <= K; i++) {
            if (N <= index+i) continue;
            if (table[index+i].equals("H")) {
                table[index+i] = "_";
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tempInput = br.readLine().split(" ");

        N = Integer.parseInt(tempInput[0]);
        K = Integer.parseInt(tempInput[1]);

        table = br.readLine().split("");

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (table[i].equals("P") && findBurger(i)) {
                count += 1;
            }
        }

        System.out.println(count);
    }
}