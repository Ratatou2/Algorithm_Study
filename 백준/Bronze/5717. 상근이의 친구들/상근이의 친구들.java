/*
[백준]
5717, 상근이의 친구들

[문제파악]
상근이의 남자 친구의 수와 여자 친구의 수가 주어졌을 때, 친구는 총 몇 명인지 구하는 프로그램을 작성하시오.

[입력]
입력은 여러 개의 테스트 케이스로 이루어져 있다.
각 테스트 케이스는 두 정수 M과 F로 이루어져 있으며, 각각은 상근이의 남자 친구의 수와 여자 친구의 수이다. (1 ≤ M, F ≤ 5)
입력의 마지막 줄에는 0이 두 개 주어진다.

[출력]
각 테스트 케이스마다 상근이의 친구의 수를 출력한다.

[구현방법]


[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (A == 0 && B == 0) break;

            sb.append(A + B).append("\n");
        }

        System.out.println(sb);
    }
}