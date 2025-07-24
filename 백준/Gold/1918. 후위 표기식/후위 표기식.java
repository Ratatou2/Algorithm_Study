/*
[백준]
1918, 후위 표기식

[문제파악]
수식은 일반적으로 3가지 표기법으로 표현할 수 있다.
연산자가 피연산자 가운데 위치하는 중위 표기법(일반적으로 우리가 쓰는 방법이다),
연산자가 피연산자 앞에 위치하는 전위 표기법(prefix notation),
연산자가 피연산자 뒤에 위치하는 후위 표기법(postfix notation)이 그것이다.

예를 들어 중위 표기법으로 표현된 a+b는 전위 표기법으로는 +ab이고, 후위 표기법으로는 ab+가 된다.
이 문제에서 우리가 다룰 표기법은 후위 표기법이다.

후위 표기법은 위에서 말한 법과 같이 연산자가 피연산자 뒤에 위치하는 방법이다.
이 방법의 장점은 다음과 같다.
우리가 흔히 쓰는 중위 표기식 같은 경우에는 덧셈과 곱셈의 우선순위에 차이가 있어 왼쪽부터 차례로 계산할 수 없지만 후위 표기식을 사용하면 순서를 적절히 조절하여 순서를 정해줄 수 있다.
또한 같은 방법으로 괄호 등도 필요 없게 된다.

예를 들어 a+b*c를 후위 표기식으로 바꾸면 abc*+가 된다.
중위 표기식을 후위 표기식으로 바꾸는 방법을 간단히 설명하면 이렇다.
우선 주어진 중위 표기식을 연산자의 우선순위에 따라 괄호로 묶어준다.
그런 다음에 괄호 안의 연산자를 괄호의 오른쪽으로 옮겨주면 된다.
예를 들어 a+b*c는 (a+(b*c))의 식과 같게 된다.
그 다음에 안에 있는 괄호의 연산자 *를 괄호 밖으로 꺼내게 되면 (a+bc*)가 된다.
마지막으로 또 +를 괄호의 오른쪽으로 고치면 abc*+가 되게 된다.
다른 예를 들어 그림으로 표현하면 A+B*C-D/E를 완전하게 괄호로 묶고 연산자를 이동시킬 장소를 표시하면 다음과 같이 된다.

결과: ABC*+DE/-
이러한 사실을 알고 중위 표기식이 주어졌을 때 후위 표기식으로 고치는 프로그램을 작성하시오

[입력]
첫째 줄에 중위 표기식이 주어진다.
단 이 수식의 피연산자는 알파벳 대문자로 이루어지며 수식에서 한 번씩만 등장한다.
그리고 -A+B와 같이 -가 가장 앞에 오거나 AB와 같이 *가 생략되는 등의 수식은 주어지지 않는다.
표기식은 알파벳 대문자와 +, -, *, /, (, )로만 이루어져 있으며, 길이는 100을 넘지 않는다.

[출력]
첫째 줄에 후위 표기식으로 바뀐 식을 출력하시오

[구현방법]
- 일단 스택을 쓰는 것까진 OK
- 그렇다면 문자열과 수식을 구분해야한다
- 그리고 수식에서도 우선순위를 구분해야함 ( *와 /는 +와 -보다 우선순위가 높다)
- 거기에 괄호까지 처리해야함
- 그렇다면? 순차적으로 수식을 탐색해가며 괄호의 위치부터 탐색하는 것은?
    - 길이가 최대 100에 제한 시간은 2초이기 때문에 충분할 것으로 보임
    - 순차적으로 처음부터 끝까지 탐색하며 괄호부터 처리하는 것
    - 근데 그럼 스택에는 어떻게 디밀어넣지..? (그냥 통쨰로?)
- 그리고 문자열과 수식을 관리하는 각각의 스택이 필요할듯함

[보완점]
- 뭔가 이상해서 구현 전에 확인해보니, '괄호부터 우선적으로 처리할 필요는 없음'이다
    - 순차적으로 왼쪽부터 탐색하면서, 괄호가 나오면 스택으로 처리하면 됨
- 또한, 스택이 두개일 필요는 없다. 오로지 수식 스택 하나만 있으면 됨
    - 지금 집어넣으려는 수식이 스택 최상단의 수식보다 우선순위가 높다면?
    - 그 땐, 그냥 스택에 push한다
    - 낮다면? 동일한 우선순위가 낮을 때까지 뽑아냄
    - 괄호라면? 어렵게 생각할 것 없다 똑같다
    - 다만 '('를 넣고 나면 그 뒤에 들어오는 수식들은 똑같지만, ')' 순서가 오는 순간 '('를 만날 때까지 전부 pop하는 것이다
    - 또한 우선순위가 동일할 경우에도 이미 있던 수식이 선행되어야 하기 때문에 계속 뽑아내면 된다
*/

import java.io.*;
import java.util.*;

public class Main {
    private static final Set<Character> EXPRESSION = new HashSet<Character> (Arrays.asList('*', '/', '+', '-', '(', ')'));

    // 수식 우선 순위 판단
    static int priority(char expression) {
        switch (expression) {
            case '*': return 2;
            case '/': return 2;
            case '+': return 1;
            case '-': return 1;
        }

        return 0;
    }

    static void printStack(Stack<Character> temp) {
        StringBuilder sb = new StringBuilder();

        for (char what : temp) {
            sb.append(what).append("");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = new String(br.readLine());
        StringBuilder sb = new StringBuilder();

        Stack<Character> expressionStack = new Stack<>();
        int flag = 0;  // 괄호 체크하는 플래그

        // 수식 순차적으로 탐색
        input:for (char c : input.toCharArray()) {

            // 알파벳이라면? 그대로 추가
            if (!EXPRESSION.contains(c)) {
                sb.append(c);
            } else {  // 수식이라면?
                // 괄호인지 우선적으로 체크
                if ('(' == c) {  // 여는 괄호면, 스택에 추가하고 다음 수식 진행
                    expressionStack.add(c);
                    continue;
                }

                while (true) {
                    // 닫힌 괄호를 만났다면, 열린 괄호를 만날 때까지 pop
                    if (')' == c) {
                        while (true) {
                            char temp = expressionStack.pop();

                            // 열린 괄호 만났으면 중단
                            if (temp == '(') continue input;
                            sb.append(temp);
                        }
                    }

                    // 현재 수식이 닫힌 괄호가 아니고, 스택이 비어있다면? 그대로 추가
                    if (expressionStack.empty()) {
                        expressionStack.add(c);
                        break;
                    } else {  // 스택이 비어있지 않다면 제일 위에 있는 수식과 비교해야 한다

                        char topExpression = expressionStack.peek();
                        int topPriority = priority(topExpression);
                        int currentPriority = priority(c);

                        // 현재 수식의 우선순위가 스택의 최상단 수식보다 크면 밀어넣는다
                        // 그리고 수식 무한루프 탈출
                        if (topPriority < currentPriority) {
                            expressionStack.add(c);
                            break;
                        } else {  // 반대로 최상단의 수식이 우선순위가 더 크거나, 같다면? 더 작을 때까지 계속 뽑아내며 추가한다
                            sb.append(expressionStack.pop());
                        }
                    }
                }
            }
        }

        // 스택에 들어있는게 있다면 다 토해내기
        while (!expressionStack.empty()) {
            sb.append(expressionStack.pop());
        }

        System.out.println(sb);
    }
}
