/*
[백준]
9655, 돌게임

[문제파악]
- 탁자 위에 N개의 돌게임
- 번갈아가며 돈을 가져가며, 돌은 1개 or 3개 가져갈 수 있다
- 마지막 돌을 가져가는 사람이 게임을 이기게 된다
- 두 사람이 완벽하게 게임을 했 을 때 이기는 사람을 구하라
- 상근이가 먼저 시작한다

[입력]
- 첫째줄에 N (1 <= N <= 1,000)

[출력]
- 상근이가 게임을 이기면 SK를, 창영이가 게임을 이기면 CY를 출력

[구현방법]
- 늘 느끼는 것이지만 DP는 그 규칙을 찾는 것이 중요한 것 같다.
- 규칙 한번만 찾으면 나머지는 곧잘 쉽게 풀리곤 함
- 이 문제의 규칙은 돌이 5개 있을 때 까지 구해보고 이해했다.
- 돌을 가져갈 수 있는 방법은 1 or 3개 뿐이므로 하나씩 그려보는게 제일 빠르고 쉽다
- 이 DP의 규칙은 이전 게임의 승자가 현재 게임의 패자가 된다는 것
    - 1개일 땐  SK 승리
    - 2개일 땐, SK는 무조건 한개만 가져갈 수 있으므로 CY 승리
    - 3개일 땐, SK가 한번에 1개를 가져가든, 3개를 한번에 다 가져가든 마지막 돌은 결국 SK가 가져가게 되므로 SK 승리
    - 4개일 땐, SK가 1를 가져가든, 3개를 가져가든 마지막 돌은 CY이 가져가게 되니 CY 승리
    - 위처럼 SK와 CY의 승리가 순차적으로 반복된다. 이후는 쉬움
- 로직을 이해했으니 나머지(% 2)로 값을 구해도 되지만 DP답게 풀어봅시당

[보완점]
- 성능 개선을 위해선 1000까지 만들어둘게 아니라 그냥 N까지만 만들어두면 되겠죠?
- 또한 단순히 배열을 N+1로만 해두면 배열의 첫번째, 두번째에 값을 할당하는 코드가 Array의 범위를 넘어서 터질테니까 이 부분도 신경써줘야한다
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] rocks = new String[Math.max(N + 1, 3)];  

        // 인덱스는 게임 숫자만큼 있어야 하니 0부터 시작이 아닌 1부터 시작임을 유의
        // 아님 항상 N-1로 진행하도록 세팅해두어야 한다
        rocks[1] = "SK";
        rocks[2] = "CY";

        // 같은 이유로 반복문 역시 N까지 포함해야함을 잊지말자
        for (int i = 3; i <= N; i++) {
            if (rocks[i-1].equals("SK")) rocks[i] = "CY";
            else if (rocks[i-1].equals("CY")) rocks[i] = "SK";
        }

        System.out.println(rocks[N]);
    }
}