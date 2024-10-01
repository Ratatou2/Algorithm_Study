/*
[백준]
1100, 하얀 칸

[문제파악]
- 체스판은 8×8크기이고, 검정 칸과 하얀 칸이 번갈아가면서 색칠되어 있다.
- 가장 왼쪽 위칸 (0,0)은 하얀색이다.
- 체스판의 상태가 주어졌을 때, 하얀 칸 위에 말이 몇 개 있는지 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄부터 8개의 줄에 체스판의 상태가 주어진다.
- ‘.’은 빈 칸이고, ‘F’는 위에 말이 있는 칸이다.

[출력]
- 첫째 줄에 문제의 정답을 출력한다.

[구현방법]
- 규칙을 잘 찾아야한다
- 첫번째 하얀칸은 (1, 1), 즉 첫번째 행의 첫번째 열이다 (인덱스로는 0, 0이다)
- 일단 행이 홀수 일 때(1, 3, 5 ~)는 열도 홀수일 때 하얀칸이다
- 행이 짝수 일 때(2, 4, 6 ~)는 열도 짝수 일 때 하얀칸이다
- 다만 행과 열은 인덱스로 처리할 땐 0번째부터 시작이니까 위 룰이 정반대임을 주의하자

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int countChessmen = 0;

        for (int row = 0; row < 8; row++) {
            input = br.readLine();

            for (int col = 0; col < 8; col++) {
                char cur = input.charAt(col);

                if (cur != 'F') continue;

                // row가 홀수 일 때는 col이 짝수 일 때, 하얀칸이다 (인덱스로는 반대이다)
                if (row % 2 == 0 && col % 2 == 0) countChessmen++;
                // row가 짝수 일 때는 col이 홀수 일 때, 하얀칸이다 (인덱스로는 반대이다)
                else if (row % 2 == 1 && col % 2 == 1) countChessmen++;
            }
        }

        System.out.println(countChessmen);
    }
}