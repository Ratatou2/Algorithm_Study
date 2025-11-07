

/*
[백준]
6198, 옥상 정원 꾸미기

[문제파악]
도시에는 N개의 빌딩이 있다.
빌딩 관리인들은 매우 성실 하기 때문에, 다른 빌딩의 옥상 정원을 벤치마킹 하고 싶어한다.
i번째 빌딩의 키가 hi이고, 모든 빌딩은 일렬로 서 있고 오른쪽으로만 볼 수 있다.
i번째 빌딩 관리인이 볼 수 있는 다른 빌딩의 옥상 정원은 i+1, i+2, .... , N이다.
그런데 자신이 위치한 빌딩보다 높거나 같은 빌딩이 있으면 그 다음에 있는 모든 빌딩의 옥상은 보지 못한다.

예) N=6, H = {10, 3, 7, 4, 12, 2}인 경우

             =
 =           =
 =     -     =
 =     =     =        -> 관리인이 보는 방향
 =  -  =  =  =
 =  =  =  =  =  =
10  3  7  4  12 2     -> 빌딩의 높이
[1][2][3][4][5][6]    -> 빌딩의 번호
1번 관리인은 2, 3, 4번 빌딩의 옥상을 확인할 수 있다.
2번 관리인은 다른 빌딩의 옥상을 확인할 수 없다.
3번 관리인은 4번 빌딩의 옥상을 확인할 수 있다.
4번 관리인은 다른 빌딩의 옥상을 확인할 수 없다.
5번 관리인은 6번 빌딩의 옥상을 확인할 수 있다.
6번 관리인은 마지막이므로 다른 빌딩의 옥상을 확인할 수 없다.
따라서, 관리인들이 옥상정원을 확인할 수 있는 총 수는 3 + 0 + 1 + 0 + 1 + 0 = 5이다.

[입력]
첫 번째 줄에 빌딩의 개수 N이 입력된다.(1 ≤ N ≤ 80,000)
두 번째 줄 부터 N+1번째 줄까지 각 빌딩의 높이가 hi 입력된다. (1 ≤ hi ≤ 1,000,000,000)

[출력]
각 관리인들이 벤치마킹이 가능한 빌딩의 수의 합을 출력한다.

[구현방법]
- 이거 스택 2개로 한쪽으로 옮겨가며 체크하는건가..?
- N이 8만까지 있어서 이중 for문으로는 절대 못 풀텐데

- 음.. 일단 빌딩은 왼쪽에서부터 오른쪽으로 보면서 스택에 넣는다 (주어진 순서대로 넣으면 됨)
- 아.. 누적합으로 해야함
- 그러니까 쉽게 말해 미리 다 삽입하는게 아니라 스택에 오름차순으로 존재할 수 있도록 세팅해두는 것이다

# 예시: 10-3-7-4-12-2 #

[보완점]
- 처음에 실패한 이유인데, 80,000!이면 32억쯤 된다 (= count 터진단 소리)
- count를 long으로 바꿔주자
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        long count = 0;

        for (int num = 0; num < N; num++) {
            int curr = Integer.parseInt(br.readLine());

            // 스택이 비어있으면 계산 없이 밀어 넣음
            if (stack.isEmpty()) stack.push(curr);
            else {
                while (true) {
                    // 현재 값보다 작은 값들은 전부 제거 (스택이 전부 빌때까지만 반복)
                    if (!stack.isEmpty() && stack.peek() <= curr) stack.pop();
                    else {
                        // stack의 top이 현재값보다 크면, 현재 stack에 있는 모든 빌딩들은 '현재의 빌딩'을 볼 수 있다는 의미이다 (= stack size만큼 더해주기)
                        count += stack.size();
                        stack.push(curr);
                        break;
                    }
                }
            }
        }

        System.out.println(count);
    }
}
