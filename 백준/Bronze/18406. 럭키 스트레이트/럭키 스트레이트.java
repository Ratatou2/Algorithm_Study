/*
[백준]
18406, 럭키 스트레이트

[문제파악]
- 어떤 게임의 아웃복서 캐릭터에게는 럭키 스트레이트라는 기술이 존재한다.
- 이 기술은 매우 강력한 대신에 항상 사용할 수는 없으며, 현재 게임 내에서 점수가 특정 조건을 만족할 때만 사용할 수 있다.
- 특정 조건이란 현재 캐릭터의 점수를 N이라고 할 때 점수 N을 자릿수를 기준으로 반으로 나누어 왼쪽 부분의 각 자릿수의 합과 오른쪽 부분의 각 자릿수의 합을 더한 값이 동일한 상황을 의미한다.
- 예를 들어 현재 점수가 123,402라면 왼쪽 부분의 각 자릿수의 합은 1+2+3, 오른쪽 부분의 각 자릿수의 합은 4+0+2이므로 두 합이 6으로 동일하여 럭키 스트레이트를 사용할 수 있다.
- 현재 점수 N이 주어졌을 때, 럭키 스트레이트를 사용할 수 있는 상태인지 아닌지를 알려주는 프로그램을 작성하시오. 럭키 스트레이트를 사용할 수 있다면 "LUCKY"를, 사용할 수 없다면 "READY"라는 단어를 출력한다. 또한 점수 N의 자릿수는 항상 짝수 형태로만 주어진다. 예를 들어 자릿수가 5인 12,345와 같은 수는 입력으로 들어오지 않는다.


[입력]
- 첫째 줄에 점수 N이 정수로 주어진다. (10 ≤ N ≤ 99,999,999)
- 단, 점수 N의 자릿수는 항상 짝수 형태로만 주어진다.

[출력]
- 첫째 줄에 럭키 스트레이트를 사용할 수 있다면 "LUCKY"를, 사용할 수 없다면 "READY"라는 단어를 출력한다.

[구현방법]
- 이거 문자열로 받고 charAt()으로 하나씩 쪼개서 읽어오면 될듯하다
- 어떤 자료구조를 쓰기보단 저런식으로 받아온다음 For문 돌려서 더하면 문제없을듯
- 문자열 길이의 절반만큼만 left에 더하고, 뒷부분은 right에 더해서 같은지 비교하면 될듯

[보완점]
- 와! Integer.parseInt만 써야한다고 생각했는데!!!
- charAt()으로 문자 상태인 숫자에서 문자인 '0'을 빼주면 해당 문자의 실질적인 숫자값이 나온다
    - 이유는 아스키 코드 숫자이긴해도 '0'을 뺐으니까!
    - 예를 들면 3의 아스키 코드는 51이다
    - 0의 아스키코드는 48
    - 둘을 빼면 3이나오고 이것은 int형 취급이 가능하다
    - 그러면 그냥 Integer.parseInt 쓸 필요도 없이 left += input.charAt(i) - '0';하면 끝나버리는 것이다;;;
- 진짜 사람들 똑똑하네...
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int left = 0;
        int right = 0;

        for (int i = 0; i < input.length(); i++) {
            if (i < input.length() / 2) {
                left += input.charAt(i) - '0';
            } else {
                right += input.charAt(i) - '0';
            }
        }

        System.out.println(left == right ? "LUCKY" : "READY");
    }
}