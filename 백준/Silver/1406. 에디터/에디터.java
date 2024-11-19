/*
[백준]
1406, 에디터

[문제파악]
한 줄로 된 간단한 에디터를 구현하려고 한다. 이 편집기는 영어 소문자만을 기록할 수 있는 편집기로, 최대 600,000글자까지 입력할 수 있다.
이 편집기에는 '커서'라는 것이 있는데, 커서는 문장의 맨 앞(첫 번째 문자의 왼쪽), 문장의 맨 뒤(마지막 문자의 오른쪽), 또는 문장 중간 임의의 곳(모든 연속된 두 문자 사이)에 위치할 수 있다.
즉 길이가 L인 문자열이 현재 편집기에 입력되어 있으면, 커서가 위치할 수 있는 곳은 L+1가지 경우가 있다.

이 편집기가 지원하는 명령어는 다음과 같다.

L	커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
D	커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
B	커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만, 실제로 커서의 오른쪽에 있던 문자는 그대로임
P $	$라는 문자를 커서 왼쪽에 추가함
초기에 편집기에 입력되어 있는 문자열이 주어지고, 그 이후 입력한 명령어가 차례로 주어졌을 때, 모든 명령어를 수행하고 난 후 편집기에 입력되어 있는 문자열을 구하는 프로그램을 작성하시오.
단, 명령어가 수행되기 전에 커서는 문장의 맨 뒤에 위치하고 있다고 한다.

[입력]
- 첫째 줄에는 초기에 편집기에 입력되어 있는 문자열이 주어진다.
- 이 문자열은 길이가 N이고, 영어 소문자로만 이루어져 있으며, 길이는 100,000을 넘지 않는다.
- 둘째 줄에는 입력할 명령어의 개수를 나타내는 정수 M(1 ≤ M ≤ 500,000)이 주어진다.
- 셋째 줄부터 M개의 줄에 걸쳐 입력할 명령어가 순서대로 주어진다.
- 명령어는 위의 네 가지 중 하나의 형태로만 주어진다.

[출력]
- 첫째 줄에 모든 명령어를 수행하고 난 후 편집기에 입력되어 있는 문자열을 출력한다.

[구현방법]
- 삭제가 되니까 List로 구현해야할듯하다 배열은 삭제가 자유로이 안되니까
- 시작할 떈 커서가 맨 뒤에 있으니 주의
- 아 ArrayList하면 터지는게 remove 같은거 할 때 index 하면 순차적으로 탐색한 뒤에 한다
- 곧잘 터짐

[보완점]
- 어떤 연산이든 O(1) 밖에 안걸리는 stack을 써야 한다
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // 커서 기준 오른쪽, 왼쪽 스택 두개
        Stack<Character> rightStr = new Stack<>();
        Stack<Character> leftStr = new Stack<>();

        int N = Integer.parseInt(br.readLine());

        // 시작시, 마우스 커서가 오른쪽 끝이므로 왼쪽 스택에 전부 넣는다
        for (int i = 0; i < str.length(); i++) {
            leftStr.push(str.charAt(i));
        }

        for (int i = 0; i < N; i++) {
            String op = br.readLine();

            switch (op.charAt(0)){
                case 'L': {
                    if(!leftStr.isEmpty()) rightStr.push(leftStr.pop());  // 왼쪽으로 옮겼으니 커서의 왼쪽을 오른쪽 스택에 밀어넣는다
                    break;
                } case 'D': {
                    if(!rightStr.isEmpty()) leftStr.push(rightStr.pop());  // 오른쪽으로 옮겼으니 커서의 오른쪽을 왼쪽 스택에 밀어넣는다
                    break;
                } case 'B': {
                    if(!leftStr.isEmpty()) leftStr.pop();  // 커서 기준 왼쪽을 지우는 것이니 왼쪽 스택의 하나를 지운다
                    break;
                } case 'P': {
                    char c = op.charAt(2);  // 두번쨰 글자 왼쪽에 밀어넣는다
                    leftStr.push(c);
                    break;
                }
            }
        }

        // 왼쪽에 있으면 오른쪽에 밀어넣기 (스택이기 때문에) (더 효율적인 방법이 없을까..?)
        while (!leftStr.isEmpty()) {
            rightStr.push(leftStr.pop());
        }
        
        StringBuilder sb = new StringBuilder();
        while (!rightStr.isEmpty()) {
            sb.append(rightStr.pop());
        }

        System.out.println(sb);
    }
}