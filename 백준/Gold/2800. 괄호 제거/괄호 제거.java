
/*
[백준]
2800, 괄호제거

[문제파악]
어떤 수식이 주어졌을 때, 괄호를 제거해서 나올 수 있는 서로 다른 식의 개수를 계산하는 프로그램을 작성하시오.
이 수식은 괄호가 올바르게 쳐져 있다.
예를 들면, 1+2, (3+4), (3+4*(5+6))와 같은 식은 괄호가 서로 쌍이 맞으므로 올바른 식이다.
하지만, 1+(2*3, ((2+3)*4 와 같은 식은 쌍이 맞지 않는 괄호가 있으므로 올바른 식이 아니다.
괄호를 제거할 때는, 항상 쌍이 되는 괄호끼리 제거해야 한다.
예를들어 (2+(2*2)+2)에서 괄호를 제거하면, (2+2*2+2), 2+(2*2)+2, 2+2*2+2를 만들 수 있다.
하지만, (2+2*2)+2와 2+(2*2+2)는 만들 수 없다. 그 이유는 쌍이 되지 않는 괄호를 제거했기 때문이다.
어떤 식을 여러 쌍의 괄호가 감쌀 수 있다.

[입력]
첫째 줄에 음이 아닌 정수로 이루어진 수식이 주어진다.
이 수식은 괄호가 올바르게 쳐져있다.
숫자, '+', '*', '-', '/', '(', ')'로만 이루어져 있다.
수식의 길이는 최대 200이고, 괄호 쌍은 적어도 1개, 많아야 10개이다.

[출력]
올바른 괄호 쌍을 제거해서 나올 수 있는 서로 다른 식을 사전 순으로 출력한다.

[구현방법]
- 우선 쌍끼리만 제거해야하니까 어느쌍을 지웠는지, 지울 것인지 말 것인지 '조합'을 짜야할 것 같다
- 가능한 모든 조합의 수를 구하고 그 쌍이 순서가 되면 지워서 ArrayList에 추가하고 (조합으로 인해 전체 갯수를 아니까)
- 그렇게 ArrayList에 넣고 나선 정렬해버리면 사전순 쨘!
- 쌍의 기준은 '(' 시작점을 기준으로 잡고 갯수를 세면서 Stack으로 계산하면 될 것 같다

[보완점]
- 오.. DFS나 비트마스킹으로 푸는게 제일 효율적이었다
- 껐다 켰다 조합 수 구하는 전구 문제처럼 이번에 끌 것인지 킬 것인지 분기처리하면 됨
- 괄호쌍이 최대 10개니까 2^10=1024안에 끝남

- 비트마스킹.. 참 친해지기 어려운 친구...
- 찬찬히 보자 다 할 수 이따

// (2) 모든 조합의 수를 다 구하는 경우
for (int bit = 1; bit < (1 << n); bit++) {
    boolean[] isRemoved = new boolean[input.length()];

    // (1) 일단 i번째 괄호쌍을 보기 위한 for문
    for (int i = 0; i < n; i++) {
        // (3) 현재 조합에서 제거한 위치이면, 해당 쌍의 start, end 모두를 삭제처리(isRemoved = true)한다
        if ((bit & (1 << i)) != 0) {
            int[] temp = location.get(i);
            int start = temp[0];
            int end = temp[1];

            // 삭제 처리 (여기서 직접 지워버리면 인덱스가 꼬임 주의!)
            isRemoved[start] = true;
            isRemoved[end] = true;
        }
    }
    

- (2) 부분이 매번 깜빡하고 놓쳐서 헷갈려하는 부분
- 비트마스킹의 for문의 범위는 숫자이지만 2진수로 변환하면 '꺼둔 부분, 켜둔 부분'이 전부 구분되어 보인다는 것
- 아래 같은 의미이다 
    | bit(10진수)| bit(2진수) | 의미            |
    | :-------: | :------: | :------------:  |
    |     1     |    001   | 0번 괄호쌍만 제거    |
    |     2     |    010   | 1번 괄호쌍만 제거    |
    |     3     |    011   | 0,1번 괄호쌍 제거   |
    |     4     |    100   | 2번 괄호쌍만 제거    |
    |     5     |    101   | 0,2번 괄호쌍 제거   |
    |     6     |    110   | 1,2번 괄호쌍 제거   |
    |     7     |    111   | 0,1,2번 괄호쌍 제거 |
- 결국 모든 조합의 수를 다 구할 수 있다
- 이 부분만 이해하면 나머지는 '비교적' 수월하게 이해할 수 있음

*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Set<String> result = new TreeSet<>();  // 중복 제거 및 사전순 자동 정렬

        // 괄호 쌍을 파악하기 위해, charAt의 순서를 따기 위한 stack과 List이다 ('('의 index를 넣어줄 예정)
        Stack<Integer> stack = new Stack<>();
        List<int[]> location = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') stack.push(i);  // '('를 만나면 index를 저장해뒀다가
            else if (c == ')') location.add(new int[] {stack.pop(), i});  // ')'를 만나면 stack의 가장 최근 것(현재 짝의 인덱스)을 꺼내고, 현재 i와 같이 한 쌍으로 저장한다
        }

        // 비트마스크로 모든 조합의 경우를 계산한다
        int n = location.size();

        for (int bit = 1; bit < (1 << n); bit++) {
            boolean[] isRemoved = new boolean[input.length()];

            for (int i = 0; i < n; i++) {
                if ((bit & (1 << i)) != 0) {
                    int[] temp = location.get(i);
                    int start = temp[0];
                    int end = temp[1];

                    // 삭제 처리 (여기서 직접 지워버리면 인덱스가 꼬임 주의!)
                    isRemoved[start] = true;
                    isRemoved[end] = true;
                }
            }

            // 제거된 부분 건너뛴 문자열 만들기
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                if (!isRemoved[i]) sb.append(input.charAt(i));
            }

            result.add(sb.toString());
        }

        // 정렬된 결과 출력
        StringBuilder sb = new StringBuilder();
        for (String temp : result) {
            sb.append(temp).append("\n");
        }

        System.out.println(sb);
    }
}
