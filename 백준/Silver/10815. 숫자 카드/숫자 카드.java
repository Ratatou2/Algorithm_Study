/*
[백준]
10815, 숫자카드

[문제파악]
- 숫자 카드 N개를 가지고 있다.
- 정수 M개가 주어졌을 때, 이 수가 적혀있는 숫자 카드를 가지고 있는지 아닌지를 구하라

[입력]
- 첫째 줄에 가지고 있는 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)이 주어진다.
- 둘째 줄에는 숫자 카드에 적혀있는 정수가 주어진다.
- 숫자 카드에 적혀있는 수는 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.
- 두 숫자 카드에 같은 수가 적혀있는 경우는 없다.
- 셋째 줄에는 M(1 ≤ M ≤ 500,000)이 주어진다.
- 넷째 줄에는 가지고 있는 숫자 카드인지 아닌지를 구해야 할 M개의 정수가 주어진다
	- 이 수는 공백으로 구분되어져 있다.
	- 이 수도 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다

[출력]
- 첫째줄에 입력으로 주어진 M개의 수에 대해서, 각 수가 적힌 숫자 카드를 가지고 있으면 1을, 아니면 0을 공백으로 구분해 출력한다.

[구현방법]
- 그냥 Set에다가 문자열로 저장하고 있으면(contains) 1, 없으면 0을 출력한다

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<String> cards = new HashSet<>();

        for (int i = 0; i < N; i++) {
            cards.add(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            if (cards.contains(st.nextToken())) sb.append("1");
            else sb.append("0");

            sb.append(" ");
        }

        System.out.println(sb);
    }
}