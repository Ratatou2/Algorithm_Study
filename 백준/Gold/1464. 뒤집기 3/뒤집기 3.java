

/*
[백준]
1464, 뒤집기 3

[문제파악]
세준이는 어떤 문자열 S를 뒤집으려고 한다. 문자열을 뒤집는 방법은 문자열의 길이를 N이라고 하자.
i만큼을 뒤집는다는 소리는 그 문자열의 처음부터 정확하게 i개의 문자를 역순으로 뒤집는 것이다.
세준이는 1부터 N까지 수를 차례대로 생각한다.
그리고, 뒤집을지 안 뒤집을지 선택할 수 있다.
예를 들어, S="BCDAF" 이고, 세준이가 길이 1만큼을 뒤집지 않고, 길이 2만큼도 뒤집지 않고 세준이가 길이 3만큼을 뒤집는다고 하면 문자열은 DCBAF가 된다.
다시 여기서 4만큼 뒤집으면 ABCDF가 된다.
그리고, 마지막으로 길이를 5만큼 뒤집지 않으면 주어진 문자열 S를 사전순으로 가장 앞서게 만들 수 있다.
문자열 S가 주어졌을 때, 위와같은 뒤집기 방법으로 만들 수 있는 문자열 중 사전순으로 제일 앞서는 것을 출력하시오.

[입력]
첫째 줄에 문자열 S가 주어진다.
문자열의 길이는 최대 50이다.
알파벳 대문자만 들어온다.

[출력]
첫째 줄에 사전순으로 가장 앞서는 정답을 출력한다.

[구현방법]
- 한번씩 다 뒤집어 보는 것만이 답이긴한데...
- 그럼 모든 경우의 수를 다 뒤집어?
- 근데 말이 되긴하는게 최대 길이가 50이니까 2초면 될 것 같은데
- 일단 틀렸고, 덱 문제 답게 앞뒤에 부이면 되는 문제였음
- 다만, 도대체 왜 앞, 뒤에 갖다 붙이는게 뒤집는 것과 같은 의미인가? 싶었음
    - 이때 너무 어렵게 생각하면 안되는게, 뒤집은 상황에서 앞의 문자열들이 다 뒤바뀌는 것까지 고려하면 안됨
    - 예를들어 BCD이고 그 다음 철자가 A라면 BCD에 A를 붙이고 뒤집으면 사전순으로 제일 앞서는 ABCD가 아닌 ADCB가 된다
    - 이때 BCD를 미리 한번 뒤집어서 DCB로 만든 다음 A를 붙여 DCBA를 만들고 뒤집는다면?
    - 사전순으로 가장 앞서있는 ABCD가 된다
    - 즉, 우리는 철자를 갖다 붙이 앞의 '단어'는 이미 최적의 형태로 준비되어있다고 가정해야한다
    - 그 상태에서 그 다음 철자를 '앞'에 붙일지, '뒤'에 붙일지만 결정하면 되는 것이다
    - 그렇기 때문에 이 문제는 Deque로 간단히 풀 수 있다

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Deque<Character> dq = new ArrayDeque<>();
        dq.add(input.charAt(0));

        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c <= dq.peekFirst()) {
                dq.addFirst(c); // 앞에 붙이는 게 사전순으로 더 작으면
            } else {
                dq.addLast(c);  // 뒤에 붙임
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) sb.append(dq.pollFirst());

        System.out.println(sb);
    }
}
