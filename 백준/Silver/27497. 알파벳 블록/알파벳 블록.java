

/*
[백준]
27497, 알파벳 블록

[문제파악]
스타는 알파벳 블록을 일렬로 조립하여 문자열을 만드는 게임을 만들었다.
각 블록에는 문자 하나가 적혀 있으며 게임에는 각각 다음 기능을 수행하는 세 개의 버튼이 있다.

1) 문자열 맨 뒤에 블록 추가
2) 문자열 맨 앞에 블록 추가
3) 문자열을 구성하는 블록 중 가장 나중에 추가된 블록 제거
게임은 처음에 빈 문자열로 시작하며 빈 문자열일 때 문자열을 구성하는 블록 중 가장 나중에 추가된 블록을 제거하는 버튼을 누를 경우 아무런 동작도 하지 않는다.
버튼을 누른 횟수와 누른 버튼이 순서대로 주어질 때 완성된 문자열을 구하여라.

[입력]
첫째 줄에 버튼을 누른 횟수N이 주어진다.(1 \leq N \leq 1\,000\,000)
둘째 줄부터N개의 줄에는 버튼을 누른 순서대로 누른 버튼에 대한 정보를 주며 아래와 같은 형식으로 주어진다.

1 c : 문자열 맨 뒤에 c가 적힌 블록 추가
2 c : 문자열 맨 앞에 c가 적힌 블록 추가
3 : 문자열을 구성하는 블록 중 가장 나중에 추가된 블록 제거
c는 알파벳 대문자 또는 소문자로 주어진다.

[출력]
완성된 문자열을 출력한다. 
완성된 문자열이 빈 문자열인 경우 0을 출력한다.

[구현방법]
- 입력 케이스에 따라 처리하는 분기를 나눈다
- 특히 마지막에 무엇이 입력되었는지를 파악하려면 stack 자료구조가 있어야 편하다
    - stack이 비어있을 수 있으니 주의할 것

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Stack<Character> inputSeq = new Stack<>();      // 입력된 순서를 기록
        Deque<Character> block = new ArrayDeque<>();    // 완성된 문자열 기록

        // 분기에 따라 문자 처리
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "1":
                    // 맨 뒤에 삽입
                    inputSeq.push('B');
                    block.addLast(st.nextToken().charAt(0));
                    break;
                case "2":
                    // 맨 앞에 삽입
                    inputSeq.push('F');
                    block.addFirst(st.nextToken().charAt(0));
                    break;
                case "3":
                    // 문자열이 없는 상태라면 패스
                    if (inputSeq.isEmpty()) break;

                    if (inputSeq.pop() == 'F') block.removeFirst();
                    else block.removeLast();
                    break;
            }
        }

        // 정답 문자열 출력
        StringBuilder sb = new StringBuilder();
        
        if (block.isEmpty()) sb.append(0);
        else {
            for (char temp : block) {
                sb.append(temp);
            }
        }

        System.out.println(sb);
    }
}
