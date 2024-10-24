/*
[백준]
10102, 개표

[문제파악]
- A와 B가 한 오디션 프로의 결승전에 진출했다.
- 결승전의 승자는 심사위원의 투표로 결정된다.
- 심사위원의 투표 결과가 주어졌을 때, 어떤 사람이 우승하는지 구하는 프로그램을 작성하시오.

[입력]
- 입력은 총 두 줄로 이루어져 있다.
- 첫째 줄에는 심사위원의 수 V (1 ≤  V ≤  15)가 주어지고, 둘째 줄에는 각 심사위원이 누구에게 투표했는지가 주어진다.
- A와 B는 각각 그 참가자를 나타낸다.

[출력]
- A가 받은 표가 B보다 많은 경우에는 A / B가 받은 표가 A보다 많은 경우에는 B / 같은 경우에는 Tie 를 출력한다.

[구현방법]
- String을 for문 돌려서 한글자씩 비교해보면 될듯하다

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        br.readLine();  // N은 사실 필요 없으니까 날린다
        String vote = br.readLine();

        int countA = 0;
        int countB = 0;

        for (char c : vote.toCharArray()) {
            if (c == 'A') countA++;
            else if (c == 'B') countB++;
        }

        if (countA == countB) sb.append("Tie");
        else if (countA < countB) sb.append("B");
        else sb.append("A");

        System.out.println(sb);
    }
}