/*
[백준]
16493, 최대 페이지 수

[문제파악]
- 철수는 한양대학교 도서관에서 책을 빌려놓고 까먹고 있다가 며칠 후 책을 반납해야 한다는 사실을 깨달았다.
- 남은 기간 동안 최대한 많은 페이지를 읽고 연체없이 반납하고 싶다.
- 빌린 책은 여러 챕터로 구성된 에세이집인데 챕터들은 서로 독립적이다.
- 즉, 어느 챕터를 읽기 위해 다른 챕터를 먼저 읽어야 할 필요가 없다.
- 철수는 중간에 관두는 것을 못견디는 성격이라, 한 챕터를 읽기 시작하면 끝까지 봐야한다.
- 남은 기간 N일과, 책의 각 챕터 당 그 챕터를 전부 읽는데 소요되는 일 수와 페이지 수가 주어질 때, N일간 읽을 수 있는 최대 페이지 수를 구하는 프로그램을 작성하라.

[입력]
- 첫째 줄에 N(1 ≤ N ≤ 200)과 챕터의 수 M(1 ≤ M ≤ 20)이 주어진다.
- 둘째 줄부터 각 챕터 당 읽는데 소요되는 일 수와 페이지 수가 주어진다.
- 소요되는 일 수는 20보다 작거나 같은 자연수이고, 페이지 수는 300보다 작거나 같은 자연수이다.

[출력]
- 읽을 수 있는 최대 페이지 수를 출력한다.

[구현방법]
- 요소 하나마다 이전의 조건으로 만들 수 있는 최대 합과 비교해야만 함
- 아직 익숙지 않아서 맨날 Top-Down으로 해야하는지, Down-Top으로 해야하는지 헷갈림..
- 이거 Top-Down인게 밑에서부터 채우면 중복돼서 말도 안되는 값이 나옴
- 같은 아이템을 여러 번 사용할 수 없기 때문에 이미 계산한 값을 덮어쓰면 안 됨. 
- 이를 방지하기 위해 뒤에서부터 채우는 방식을 사용한다는 것은 인지했는데 참.. 체화하기 어렵네

[보완점]

7 7
3 10
5 20
1 10
1 20
2 15
4 40
2 200


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 남은 기간 N일
        int M = Integer.parseInt(st.nextToken());  // 챕터 수

        int[] dp = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int spendDay = Integer.parseInt(st.nextToken());
            int page = Integer.parseInt(st.nextToken());

            for (int j = N; spendDay <= j; j--) {
                dp[j] = Math.max(dp[j], dp[j - spendDay] + page);
            }
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[N]);
    }
}