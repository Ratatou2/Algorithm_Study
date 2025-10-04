/*
[백준]
1476, 날짜 계산

[문제파악]
준규가 사는 나라는 우리가 사용하는 연도와 다른 방식을 이용한다.
준규가 사는 나라에서는 수 3개를 이용해서 연도를 나타낸다.
각각의 수는 지구, 태양, 그리고 달을 나타낸다.
지구를 나타내는 수를 E, 태양을 나타내는 수를 S, 달을 나타내는 수를 M이라고 했을 때, 이 세 수는 서로 다른 범위를 가진다. (1 ≤ E ≤ 15, 1 ≤ S ≤ 28, 1 ≤ M ≤ 19)
우리가 알고있는 1년은 준규가 살고있는 나라에서는 1 1 1로 나타낼 수 있다.
1년이 지날 때마다, 세 수는 모두 1씩 증가한다.
만약, 어떤 수가 범위를 넘어가는 경우에는 1이 된다.
예를 들어, 15년은 15 15 15로 나타낼 수 있다.
하지만, 1년이 지나서 16년이 되면 16 16 16이 아니라 1 16 16이 된다.
이유는 1 ≤ E ≤ 15 라서 범위를 넘어가기 때문이다.
E, S, M이 주어졌고, 1년이 준규가 사는 나라에서 1 1 1일때, 준규가 사는 나라에서 E S M이 우리가 알고 있는 연도로 몇 년인지 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 세 수 E, S, M이 주어진다. 문제에 나와있는 범위를 지키는 입력만 주어진다.

[출력]
첫째 줄에 E S M으로 표시되는 가장 빠른 연도를 출력한다. 1 1 1은 항상 1이기 때문에, 정답이 음수가 나오는 경우는 없다.

[구현방법]
- 난 대체 이게 뭔 문제인가.. 어떻게 풀라는건가 생각했는데... 그냥 +1 계속하면 된다..
- 그러니까 뭔말이냐면 세개의 E, S, M이 주기가 다르니까 계속 셋에 +1을 하면서 Input 값을 만족시키는 순간을 찾으면 된다는 것!
- 알고나니 쉽네..

[보완점]
*/


import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MAX_E = 15;
        final int MAX_S = 28;
        final int MAX_M = 19;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int target_E = Integer.parseInt(st.nextToken());
        int target_S = Integer.parseInt(st.nextToken());
        int target_M = Integer.parseInt(st.nextToken());

        int E = 1;
        int S = 1;
        int M = 1;

        int count = 1;
        while (true) {
            // 세 숫자가 일치하는 순간 중단
            if (E == target_E && S == target_S && M == target_M) break;

            E = E % MAX_E + 1;
            S = S % MAX_S + 1;
            M = M % MAX_M + 1;

            count++;
        }

        System.out.println(count);
    }
}
