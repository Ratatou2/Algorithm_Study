

/*
[백준]
7567, 그릇

[문제파악]
그릇을 바닥에 놓았을 때 그 높이는 10cm 이다. 그런데 두 개의 그릇을 같은 방향으로 포개면 그 높이는 5cm만 증가된다.
만일 그릇이 서로 반대방향으로 쌓이면 높이는 그릇만큼, 즉 10cm 늘어난다.
그릇을 괄호 기호로 나타내어 설명해보자.
편의상 그릇이 쌓여지는 방향은 왼쪽에서 오른쪽이라고 가정한다.
그림에서 ‘(’은 그릇이 바닥에 바로 놓인 상태를 나타내며, ‘)’은 그릇이 거꾸로 놓인 상태를 나타낸다.
만일 그릇이 포개진 모양이 ((((와 같다면 전체의 높이는 25cm가 된다.
왜냐하면 처음 바닥에 있는 그릇의 높이가 10cm이고 이후 같은 방향으로 3개의 그릇이 포개져 있으므로 늘어난 높이는 5+5+5=15 이기 때문이다.
()()와 같은 경우라면 그 높이는 10*4=40cm가 된다.
여러분은 입력에 주어진 모양대로 그릇을 쌓을 때 최종의 전체 그릇 높이를 계산해서 출력해야 한다.
즉 처음 입력으로 주어진 각 그릇의 방향은 바꿀 수 없다.

[입력]
첫 줄에는 괄호문자로만 이루어진 문자열이 주어진다.
입력 문자열에서 열린 괄호 ‘(’은 바로 놓인 그릇, 닫힌 괄호 ‘)’은 거꾸로 놓인 그릇을 나타난다.
문자열의 길이는 3이상 50 이하이다.

[출력]
여러분은 그릇 방향이 괄호 문자로 표시된 문자열을 읽어서 그 최종의 높이를 정수로 출력해야 한다.

[구현방법]
- toCharArray 해서 반복문으로 이전과 같으면 +5, 다르면 +10 하면 될듯하다

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int totalHeight = 0;

        char prev = '-';  // 임의 값으로 세팅
        for (char curr : input.toCharArray()) {
            if (totalHeight == 0) totalHeight += 10;    // 첫번째 그릇은 +10하고 시작이다
            else {
                if (prev != curr) totalHeight += 10;    // 이전과 다른 모양이면 +10
                else totalHeight += 5;                  // 이전과 같은 모양이면 +5
            }

            prev = curr;                                // 이전 그릇을 현재의 그릇으로 갱신해준다 (다음에 비교하기 위해서)
        }

        System.out.println(totalHeight);
    }
}
