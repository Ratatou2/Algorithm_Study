/*
[백준]
11365, 밀비급일

[문제파악]
- 당신은 길을 가다가 이상한 쪽지를 발견했다. 
- 그 쪽지에는 암호가 적혀 있었는데, 똑똑한 당신은 암호가 뒤집으면 해독된다는 것을 발견했다.
- 이 암호를 해독하는 프로그램을 작성하시오.

[입력]
- 한 줄에 하나의 암호가 주어진다.
- 암호의 길이는 500을 넘지 않는다.
- 마지막 줄에는 "END"가 주어진다. (END는 해독하지 않는다.)

[출력]
- 각 암호가 해독된 것을 한 줄에 하나씩 출력한다.

[구현방법]
- 이거 for문 돌려서 역행 출력 해도 되긴함 (String.length() - 1 -i 해서 출력) 
- 근데 사실 StringBuilder에 .reverse() 기능이 있으니 그거 쓰면 된다

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String input = br.readLine();

            if (input.equals("END")) break;

            StringBuilder temp = new StringBuilder(input).reverse();
            sb.append(temp).append("\n");
        }

        System.out.println(sb);
    }
}