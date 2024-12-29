/*
[백준]


[문제파악]
- 문자열 S가 주어졌을 때, 이 문자열에서 단어만 뒤집으려고 한다.
- 먼저, 문자열 S는 아래와과 같은 규칙을 지킨다.
    알파벳 소문자('a'-'z'), 숫자('0'-'9'), 공백(' '), 특수 문자('<', '>')로만 이루어져 있다.
    문자열의 시작과 끝은 공백이 아니다.
    '<'와 '>'가 문자열에 있는 경우 번갈아가면서 등장하며, '<'이 먼저 등장한다. 또, 두 문자의 개수는 같다.
- 태그는 '<'로 시작해서 '>'로 끝나는 길이가 3 이상인 부분 문자열이고, '<'와 '>' 사이에는 알파벳 소문자와 공백만 있다.
- 단어는 알파벳 소문자와 숫자로 이루어진 부분 문자열이고, 연속하는 두 단어는 공백 하나로 구분한다.
- 태그는 단어가 아니며, 태그와 단어 사이에는 공백이 없다.

[입력]
- 첫째 줄에 문자열 S가 주어진다.
- S의 길이는 100,000 이하이다.

[출력]
- 첫째 줄에 문자열 S의 단어를 뒤집어서 출력한다.

[구현방법]
- 일단 stack을 하나 만들어서 줄줄줄 집어넣고 마지막에 pop을 줄줄줄 해서 print 해야할 것 같다
- 그리고 출력을 하다가 >를 만나면 <를 만날 때까진 따로 문자열을 만들고 그것을 뒤집어서 추가해줘야할 것 같다
- 시간 단축까진... 모르겠네 사실 시간 단축할거면 그냥 규칙 찾아서 stack말고 알고리즘처럼 공식 만들어서 풀어버리는게 젤 빠를듯
- 그러나 여기에선 stack을 써보겠다!

[보완점]
- 아 이거 분기처리 잘해줘야한다
- 귀찮네 여러 단계라... 일단 일반적으로 4단계로 처리해야한다
    1) 열림 꺾쇠 만남 - 이후 단어들은 >를 만날 때까지 정방향으로 넣는다 (뒤집기 X)
    2) 꺾쇠 내부 단어 - 이 단어들은 >를 만날 때까지 정방향으로 넣는다 (뒤집기 X)
    3) 닫힘 꺾쇠 만남 - 여기서부턴 stack에 넣고 뒤집어 넣을 준비를 한다
    4) 공백 만남 - stack에 들어있는 것들 뒤집어서 넣고, 공백도 추가한다
    4) 일반 단어 - stack에 넣는다 (공백이나 <를 만날 때까지는 밀어넣는다. 뒤집기 위함)
        - 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        String input = br.readLine();
        Stack<Character> stack = new Stack<>();
        boolean isTag = false;  // 뒤집지 않아도 되는 태그 true, false 구분용

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '<') {  // 태그 시작처리
                // 꺾쇠를 만나면 스택에 남아있는 단어를 결과에 추가(stack에서 pop이니까 뒤집어져서 들어감)하고 태그 시작 처리
                while (!stack.isEmpty()) result.append(stack.pop());
                isTag = true;  // 태그 만났으니 true 처리 (이때부터 뒤집지 않고 고대로 들어감)
                result.append(c);  // 열림 꺾쇠도 결과에 추가
            } else if (c == '>') {  // 태그 끝 처리
                isTag = false;  // 태그 종료
                result.append(c);  // 닫힘 꺾쇠 추가
            } else if (isTag) {  // 태그를 만난 내부의 단어들이면 그대로 추가
                result.append(c);
            } else {  // 단어 처리
                if (c == ' ') {
                    // 단어 구분 공백을 만나면 스택에 있는 단어를 뒤집어서 추가
                    while (!stack.isEmpty()) result.append(stack.pop());
                    result.append(c);
                } else {  // 일반 단어 문자는 스택에 추가
                    stack.push(c);
                }
            }
        }

        // 마지막에 스택에 남아있는 단어 처리
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        System.out.println(result);
    }
}