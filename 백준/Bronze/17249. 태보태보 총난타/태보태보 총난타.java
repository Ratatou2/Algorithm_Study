/*
[백준]
17249, 태보태보 총난타

[문제파악]
- 얼굴 기준 좌, 우의 @ 갯수를 세란다

[입력]
- 문자열의 길이는 1,000을 넘지 않는다.

[출력]
- 첫째 줄에 왼손의 잔상의 수와 오른손의 잔상의 수를 출력한다.

[구현방법]
- 일일이 다 체크할 필요없이 @ 갯수만 세는데 ')'이 나오는 순간 right로 세면 될듯하다

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        boolean isRight = false;  // 왼쪽, 오른쪽 구분 기준
        int leftCount = 0;
        int rightCount = 0;
        
        for (char c : input.toCharArray()) {
            if (c == ')') isRight = true;

            if (c == '@') {
                if (isRight) rightCount++;
                else leftCount++;
            }
        }

        System.out.println(leftCount + " " + rightCount);
    }
}