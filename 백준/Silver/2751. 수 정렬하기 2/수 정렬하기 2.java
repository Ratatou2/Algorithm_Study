/*
[백준]
2751, 수 정렬하기 2

[문제파악]
- N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다.
- 둘째 줄부터 N개의 줄에는 수가 주어진다.
- 이 수는 절댓값이 1,000,000보다 작거나 같은 정수이다.
- 수는 중복되지 않는다.


[출력]
- 첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.

[구현방법]


[보완점]
- 변환하는 시간 아까워서 String으로 했는데 그래서 틀렸다
- 1, 2, 3, 10, 100 정렬시키면 1, 10, 100, 2, 3 으로 정렬하기 때문
- 수많은 앱에서 정렬을 이름 순으로 하면 전부 텍스트 처리해서 이렇게 정렬되는 거였구나 이제 이해했다
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] needOrder = new int[N];

        for (int i = 0; i < N; i++) {
            needOrder[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(needOrder);

        for (int str : needOrder) {
            sb.append(str).append("\n");
        }

        System.out.println(sb);
    }
}