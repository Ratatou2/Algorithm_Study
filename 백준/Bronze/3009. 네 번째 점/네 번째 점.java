
/*
[백준]
3009, 네번째 점

[보완점]
- Set으로 배열 집어넣어서 contain으로 중복되는거 제하고 없는 것만 찾으려고 했는데... 배열은 참조값으로 비교하는 것
    - 즉, 메모리 주소만 다르면 같은 값이어도 다른 것으로 인지한다 (fail 지점)
- 더군다나 이 문제는 XOR로 쉽게 풀 수 있다 (비트 계산)
- 쉽게 말해 수학적 원리로 XOR을 하면 되는 것
- 각 좌표들은 전부 2번씩 나와야한다 (그야 서로 직사각형이니까)
- 즉, x좌표 따로, y좌표 따로 모으면 모든 숫자는 총 2번씩 출현한다
- 그렇게 XOR 연산을 하면 같은 숫자일 경우 0이되므로, 결과는 0이되어야한다
- 이 말을 반대로하면? 3자리 좌표만 건네받으면, XOR 연산을 통해 현재 없는 좌표를 도출해낼 수 있다는 것임
- 비트 연산 & 비트마스킹 문제도 병행해야겠다
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int x = 0, y = 0;
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x ^= Integer.parseInt(st.nextToken());
            y ^= Integer.parseInt(st.nextToken());
        }

        System.out.println(x + " " + y);
    }
}
