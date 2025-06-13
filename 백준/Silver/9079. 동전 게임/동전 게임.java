/*
[백준]
9097, 동전게임

[문제파악]
상우는 재미있는 게임을 생각해냈다.
동전 9개를 아래 그림과 같이 3행 3열로 놓는다.
H는 앞면, T는 뒷면을 의미한다.

H T T
H T T
T H H
게임의 목적은 이 동전의 모양을 모두 같은 면(H나 T)이 보이도록 하는 것이다.
단, 하나의 동전만을 뒤집을 수는 없고, 한 행의 모든 동전, 한 열의 모든 동전 또는 하나의 대각선 상의 모든 동전을 한 번에 뒤집어야 한다.
그런 식으로 세 개의 동전을 뒤집는 것을 '한 번의 연산'으로 센다.
상우는 이것을 최소 횟수의 연산으로 실행하고 싶어한다.
오랜 시간 생각한 끝에 위의 경우는 두 번의 연산으로 가능하다는 것을 알아냈고, 또, 이것이 최소인 것도 알아내었다.

H T T   T T T   T T T
H T T → T T T → T T T
T H H   H H H   T T T
또한, 모두 같은 면으로 만드는 것이 불가능한 모양이 있다는 것도 알아내었다. 다음이 그 예이다.

T H H
H H H
H H H
상우를 도울 수 있는 프로그램을 작성하시오.

[입력]
입력의 첫 줄에는 테스트 케이스의 개수 T(1 ≤ T ≤ 10)가 주어진다.
각 테스트 케이스는 세 줄로 이루어지며, 한 줄에 세 개의 동전모양이 주어지는데, 각각의 동전 표시 사이에는 하나의 공백이 주어진다.

[출력]
각 테스트 케이스에 대해서, 모두 같은 면이 보이도록 만들기 위한 최소 연산 횟수를 출력하고, 불가능한 경우에는 -1을 출력한다.

[구현방법]
- 이거 규칙을 찾아서 풀어야할 거 같은데 (당연한소리;;)
- 근데 뒤집는걸 어떻게 알지? H가 있는 쪽을, 즉 반대면이 모여있는 쪽으로 뒤집어야 하나?
- 그럼 무엇을 뒤집을지는 input을 받을 때 갯수가 적은 쪽을 뒤집는게 낫겠지?

- 이 문제는 비트마스킹 완전탐색 문제의 정석 같은 문제란다... (몰라 그런거 ㅠㅠ)
- 일단 총 9칸이니까 뒤집고 안뒤집고 즉 2^9 = 512로 완탐이 가능한 것을 이용한 문제인 것 같다
- 그리고 진짜 충격적인게 그냥 flip이 가능한 조건 (행, 열, 대각선)을 모두 인덱스로써 기록해두고 그걸 그냥 BFS 돌려가며 답 나올 때까지 찾는 것이다..!!
- 진짜 무시무시한 방법이 따로 없음
- 내가 계속 헷갈렸던 부분은 정수를 일종의 방문처리 적용된 배열로 쓴다는 점이다
- 이 부분이 계속 헷갈려서 헤맸던듯
- 비트마스킹 많이 풀어봐야곘다 이거는 확실히 전체적인 코드 속도 줄이는 것에도 굉장히 도움될 것 같음
- 굉장히 작은 데이터 사이즈로 많은 것을 체크하고 기록해둘 수 있음... 최고...

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    // 뒤집을 떄 같이 돌아가는 세트를 한 개의 패키지로 묶어버림...
    static int[][] reverseSets = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},   // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},   // cols
            {0, 4, 8}, {2, 4, 6}               // diagonals
    };

    // 뒤집는 함수
    static int flip(int state, int[] reverseSet) {
        // 뒤집을 한 세트 뒤집어버리기
        for (int location : reverseSet){
            state ^= (1 << location); // toggle bit
        }

        return state;
    }

    // BFS 탐색
    // Q. BFS 탐색은 어떻게 이뤄지는가? 왜 방문처리는 512개만큼 하는가?
    static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[512];  // 완탐 가능성
        q.add(start);
        visited[start] = true;
        int depth = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int curr = q.poll();

                // 모두가 같은 면일 때 (전부 H이거나, T일 때)
                // state를 정수로 사용해서 9칸을 계산하고 있음
                // 같은 의미로 curr는 512까지 계산할 것임 (모든 경우의 수)
                // 그러면 전부 0이거나 모든 1의 합산인 511이라면? 모든 면이 같은 숫자라는 소리임
                // 여기에 도달하면 return 하는 이유
                // 511인 이유는 111 111 111로 9칸을 표기한 숫자가 511이기 때문인 것이다
                // 정수를 2진수로 바꾼 것을 방문 배열로 쓰기 때문에 헷갈릴 수 있음
                if (curr == 0 || curr == 511) {
                    return depth;
                }

                for (int[] oneSet : reverseSets) {
                    int next = flip(curr, oneSet);

                    // 현재 계산으로 도달한 곳이 있다면 000 001 010이 있다면, 이를 정수로 바꾼 숫자는 10이다
                    // 그렇다면 인덱스를 10로 잡고, visited = new boolean[512] 중 인덱스 10번 위치에 방문했다고 true로 바꿔주는 것임
                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }

            depth++;
        }

        return -1;  // 여기까지 왔다는 것은 정답이 없다는 의미이다
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int state = 0;  // 현재 동전판의 상태를 9비트 이진수로 압축해 하나의 정수로 표현하기 위한 정수
            for (int i = 0; i < 3; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < 3; j++) {
                    String s = st.nextToken();
                    // Q. 왜 T랑 비교하는고 1을 i * 3 + j만큼 밀어넣는가?
                    // A. i * 3 + j (= i * (열의 개수) + j)는 2차원 배열을 1차원 배열로 계산하는 클래식한 방법임
                    // 그리고 그냥 T일 때 1로 처리하기 위해서 |(or) 처리해둔 것임 (H는 그냥 0으로 냅둘 예정)
                    if (s.equals("T")) {
                        state |= (1 << (i * 3 + j)); // T → 1
                    }
                }
            }

            sb.append(bfs(state)).append("\n");
        }

        System.out.println(sb);
    }
}