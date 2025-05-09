/*
[백준]
10811, 바구니 뒤집기

[문제파악]
- 도현이는 바구니를 총 N개 가지고 있고, 각각의 바구니에는 1번부터 N번까지 번호가 순서대로 적혀져 있다.
- 바구니는 일렬로 놓여져 있고, 가장 왼쪽 바구니를 1번째 바구니, 그 다음 바구니를 2번째 바구니, ..., 가장 오른쪽 바구니를 N번째 바구니라고 부른다.
- 도현이는 앞으로 M번 바구니의 순서를 역순으로 만들려고 한다.
- 도현이는 한 번 순서를 역순으로 바꿀 때, 순서를 역순으로 만들 범위를 정하고, 그 범위에 들어있는 바구니의 순서를 역순으로 만든다.
- 바구니의 순서를 어떻게 바꿀지 주어졌을 때, M번 바구니의 순서를 역순으로 만든 다음, 바구니에 적혀있는 번호를 가장 왼쪽 바구니부터 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 N (1 ≤ N ≤ 100)과 M (1 ≤ M ≤ 100)이 주어진다.
- 둘째 줄부터 M개의 줄에는 바구니의 순서를 역순으로 만드는 방법이 주어진다.
- 방법은 i j로 나타내고, 왼쪽으로부터 i번째 바구니부터 j번째 바구니의 순서를 역순으로 만든다는 뜻이다. (1 ≤ i ≤ j ≤ N)
- 도현이는 입력으로 주어진 순서대로 바구니의 순서를 바꾼다.

[출력]
- 모든 순서를 바꾼 다음에, 가장 왼쪽에 있는 바구니부터 바구니에 적혀있는 순서를 공백으로 구분해 출력한다.

[구현방법]
- 직접 for문 돌리는 방법 밖에 없을까?

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 공 입력 넣어두기
        List<Integer> baskets = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            baskets.add(i);
        }

        // 뒤집기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            // 인덱스니까 -1을 해서 맞춘다 (다만, end는 3번째까지라면 2번째까지 적용되므로, 1을 빼지 않는다)
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken());

            // subList로 범위 지정된 리스트를 만들고, reverse 써서 뒤집는다
            Collections.reverse(baskets.subList(start, end));
        }

        // 결과값 출력
        StringBuilder sb = new StringBuilder();
        for (int temp : baskets) {
            sb.append(temp).append(" ");
        }

        System.out.println(sb);
    }
}