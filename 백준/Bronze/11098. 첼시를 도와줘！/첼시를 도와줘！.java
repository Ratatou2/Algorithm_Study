/*
[백준]
11098, 첼시를 도와줘!

[문제파악]
- 새로운 선수를 찾는 방법은 단순히 구단들에게 전화를 걸어 그들의 가장 비싼 선수를 사는게 되었다.
- 당신의 임무는 첼시가 리스트에서 가장 비싼 선수를 찾아낼 수 있도록 돕는 것이다.

[입력]
- 첫 번째 줄에는 테스트 케이스의 개수 n이 주어진다 (n≤100).
- 각 테스트 케이스의 첫 번째 줄 p는 고려해야될 선수의 수이다 (1≤p≤100).
- 그 아래 p개의 줄에는 선수의 정보가 표시된다.
- 각각의 줄은 선수의 가격 C 와 이름을 입력한다 (C<2*109).
- 모든 선수의 가격은 서로 다르다.
- 선수의 이름은 20자 이하여야 하며, 사이에 공백이 있어서는 안 된다.

[출력]
- 각각의 테스트 케이스에서 가장 비싼 선수의 이름을 출력해야한다.

[구현방법]


[보완점]
2
3
10 Iversen
1000000 Nannskog
2000000 Ronaldinho
2
1000000 Maradona
999999 Batistuta


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int maxCost = 0;
            String name = "";
            int N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int cost = Integer.parseInt(st.nextToken());
                
                // 입력 받은 금액을 최대 금액과 비교하여 갱신한다
                if (maxCost < cost) {
                    maxCost = cost;
                    name = st.nextToken();
                }
            }

            sb.append(name).append("\n");
        }

        System.out.println(sb);
    }
}