
/*
[백준]
23300, 웹 브라우저 2

[문제파악]
우리는 웹 페이지에 접속할 때 '웹 브라우저'를 사용한다.
웹 브라우저에는 크게 뒤로 가기(Backward), 앞으로 가기(Frontward), 웹페이지 접속(Access) 기능이 있다.

사용자가 웹 사이트에 접속하면 컴퓨터의 캐시(cache)공간에 웹페이지 정보가 저장된다.
그리고 뒤로 가기 또는 앞으로 가기 기능을 사용하면 캐시 공간에 저장되어 있던 페이지의 정보를 불러오게 된다.
여기에 주헌이가 개발한 웹 브라우저에는 신기한 기능이 있는데, 바로 압축(Compress)이라는 기능이다.
압축 기능은 뒤로 가기 공간에 같은 번호의 페이지가 연속해서 들어있을 때, 이를 하나로 줄일 수 있는 기능이다.

각 기능의 작동방식은 각각 다음과 같다.

뒤로 가기를 실행할 경우
뒤로 가기 공간에 1개 이상의 페이지가 저장되어 있을 때만 2,3번 과정이 실행된다. 0개일 때 이 작업은 무시된다.
현재 보고 있던 웹페이지를 앞으로 가기 공간에 저장한다.
뒤로 가기 공간에서 방문한지 가장 최근의 페이지에 접속한다. 그리고 해당 페이지는 뒤로 가기 공간에서 삭제된다.
앞으로 가기를 실행할 경우
앞으로 가기 공간에 1개 이상의 페이지가 저장되어 있을 때만 2,3번 과정이 실행된다. 0개일 때 이 작업은 무시된다.
현재 보고 있던 페이지를 뒤로 가기 공간에 저장한다.
앞으로 가기 공간에서 방문한지 가장 최근의 페이지에 접속한다. 그리고 해당 페이지는 앞으로 가기 공간에서 삭제된다.
웹 페이지에 접속할 경우
앞으로 가기 공간에 저장된 페이지가 모두 삭제된다.
현재 페이지를 뒤로 가기 공간에 추가하고, 다음에 접속할 페이지가 현재 페이지로 갱신된다. 단, 처음으로 웹페이지에 접속하는 경우라면 현재 페이지를 뒤로 가기 공간에 추가하지 않는다.
압축을 실행할 경우
뒤로 가기 공간에서 같은 번호의 페이지가 연속해서 2개 이상 등장할 경우, 가장 최근의 페이지 하나만 남기고 나머지는 모두 삭제한다.
Q개의 작업을 모두 마친 뒤에 현재 접속 중인 페이지와 뒤로 가기 공간, 앞으로 가기 공간에 저장되어 있는 페이지의 번호를 구하여라.

초기 상태에는 뒤로가기 공간, 앞으로 가기 공간이 모두 비어있으며 어떤 페이지에도 접속해있지 않는 상태이다. 또한 같은 번호의 페이지에 여러번 접속할 수 있으며, 그럴 경우 같은 번호의 페이지이라도 방문 순서는 각기 다르게 취급된다. 이 문제에서 컴퓨터의 캐시 용량은 충분히 크다고 가정한다.

[입력]
첫째 줄에 접속할 수 있는 웹페이지의 종류의 수 N, 사용자가 수행하는 작업의 개수 Q 가 각각 주어진다.(1 ≤ N, Q ≤ 2,000)

둘째 줄부터는 Q개의 작업이 주어지며, 각 작업이 의미하는 바는 다음과 같다.

B : 뒤로 가기를 실행한다.
F : 앞으로 가기를 실행한다.
A i : i 번 웹페이지에 접속한다.
C : 압축을 실행한다.
A(접속)작업이 적어도 한 번은 등장한다.

[출력]
3줄에 걸쳐서 출력한다.
첫째 줄에는 현재 접속 중인 페이지의 번호를 출력한다.
둘째 줄에는 뒤로 가기 공간에서 가장 최근에 방문한 순서대로 페이지의 번호를 출력한다. 저장된 페이지가 없다면 -1 을 출력한다.
셋째 줄에는 앞으로 가기 공간에서 가장 최근에 방문한 순서대로 페이지의 번호를 출력한다. 저장된 페이지가 없다면 -1 을 출력한다.

[구현방법]
- 구현문제인 것 같은데 stack을 잘 써야할 것 같다

[보완점]
- 주의해야하는 것이 '뒤로가기' 자료구조는 stack으로만 하면 압축(C) 과정할 때 pop하면 최신 것이 가장 먼저 나온다
    - 즉, 최신순서 -> 오래된 순서으로 재갱신 하게 된다는 말
- 이를 방지하기 위해서라도 Deque로 진행해서 역순 탐색을 통해 오래된 순서 -> 최신 순서로 갱신해야한다

- deque의 push는 queue와 달리 맨 앞에 추가함을 주의하자 (나는 그냥 Queue랑 똑같은 줄 알았더니...)
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 웹 페이지 종류 갯수
        int Q = Integer.parseInt(st.nextToken());  // 수행하는 작업의 갯수

        Stack<Integer> frontHistory = new Stack<>();
        Deque<Integer> backHistory = new ArrayDeque<>();
        int currPage = 0;

        for (int task = 0; task < Q; task++) {
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "B":
                    // 뒤로 가기 실행 (비어있을 경우 실행 X)
                    if (backHistory.isEmpty()) break;
                    frontHistory.push(currPage);
                    currPage = backHistory.pop();
                    break;
                case "F":
                    // 앞으로 가기 실행 (비어있을 경우 실행 X)
                    if (frontHistory.isEmpty()) break;
                    backHistory.push(currPage);
                    currPage = frontHistory.pop();
                    break;
                case "A":
                    // 웹 페이지에 접속 (앞으로 가기 전부 삭제)
                    frontHistory.clear();                               // (1) 앞으로 가기 비우기
                    if (currPage != 0) backHistory.push(currPage);      // (2) 첫 접속이 아니면 뒤로가기에 추가
                    currPage = Integer.parseInt(st.nextToken());        // (3) 현재 페이지 갱신
                    break;
                case "C":
                    // 압축 실행 (뒤로가기, 중복 있을 시 마지막 하나 남기고 전부 삭제)

                    if (backHistory.isEmpty()) break;       // 뒤로가기 비어있으면 실행 X

                    Deque<Integer> temp = new ArrayDeque<>();
                    int prev = backHistory.pollFirst();           // 이전 값 기록 변수
                    while (!backHistory.isEmpty()) {        // 뒤로가기가 텅 빌때까지 반복
                        int curr = backHistory.pollFirst();

                        if (prev == curr) continue;         // 중복되는 값이 나오면 값을 갱신

                        temp.addLast(prev);                    // 이전 값을 임시 stack에 밀어넣고
                        prev = curr;                        // 현재 값을 갱신
                    }

                    temp.addLast(prev);                        // 마지막으로 나온 이전 값은 비교할 대상이 없으니 무조건 밀어넣고
                    backHistory = temp;                     // 스택은 새로운 것으로 교체
                    break;
            }
        }

        // (1) 현재 접속중인 페이지
        sb.append(currPage).append("\n");

        // (2) 뒤로 가기 출력
        if (backHistory.isEmpty()) sb.append(-1).append("\n");
        else {
            while (!backHistory.isEmpty()) {
                sb.append(backHistory.pop()).append(" ");
            }

            sb.append("\n");
        }

        // (3) 앞으로 가기 출력
        if (frontHistory.isEmpty()) sb.append(-1).append("\n");
        else {
            while (!frontHistory.isEmpty()) {
                sb.append(frontHistory.pop()).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
