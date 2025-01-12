/*
[백준]
2810, 컵홀더

[문제파악]
극장의 한 줄에는 자리가 N개가 있다.
서로 인접한 좌석 사이에는 컵홀더가 하나씩 있고, 양 끝 좌석에는 컵홀더가 하나씩 더 있다.
또, 이 극장에는 커플석이 있다. 커플석 사이에는 컵홀더가 없다.
극장의 한 줄의 정보가 주어진다.
이때, 이 줄에 사람들이 모두 앉았을 때, 컵홀더에 컵을 꽂을 수 있는 최대 사람의 수를 구하는 프로그램을 작성하시오.
모든 사람은 컵을 한 개만 들고 있고, 자신의 좌석의 양 옆에 있는 컵홀더에만 컵을 꽂을 수 있다.
S는 일반 좌석, L은 커플석을 의미하며, L은 항상 두개씩 쌍으로 주어진다.
어떤 좌석의 배치가 SLLLLSSLL일때, 컵홀더를 *로 표시하면 아래와 같다.
*S*LL*LL*S*S*LL*
위의 예에서 적어도 두 명은 컵홀더를 사용할 수 없다.

[입력]
- 첫째 줄에 좌석의 수 N이 주어진다. (1 ≤ N ≤ 50)
- 둘째 줄에는 좌석의 정보가 주어진다.

[출력]
- 컵을 컵홀더에 놓을 수 있는 최대 사람의 수를 출력한다.

[구현방법]
- 가끔 너무 복잡하게 생각해서 못풀길래 최대한 간단하게 생각해봤다
- 일단 시작할 때 컵홀더를 하나 주고 시작한다 S가 오든 LL이 오든 일단 하나는 있으니까
- 그 뒤엔 S면 바로 *(컵홀더)를 넣어주고 LL이면 L 두개를 건너뛰고 나서 *(컵홀더)을 카운트 해준다
- 물론 진행하면서 L과 S의 문자열 갯수를 카운트하면서 진행한다
- 마지막에 총 좌석 갯수에서 *(컵홀더) 갯수를 빼면, 컵홀더를 쓰지 못하는 인원 수를 구할 수 있다

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int people = Integer.parseInt(br.readLine());
        String seat = br.readLine();

        int cupholderCount = 1;  // 시작할 때 컵홀더 +1
        int index = 0;
        while (index < people) {
            char curr = seat.charAt(index);

            // 현 좌석이 L이면, L끼리는 붙어있으므로 index를 +2하고 컵홀더 추가
            // 현 좌석이 S라면, index 하나 늘려주고 컵홀더 추가
            if (curr == 'L') {
                index = index + 2;
                cupholderCount++;
            } else {
                index++;
                cupholderCount++;
            }
        }

        System.out.println(Math.min(people, cupholderCount));
    }
}