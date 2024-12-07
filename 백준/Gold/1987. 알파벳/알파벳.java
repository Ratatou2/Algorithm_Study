/*
[백준]
1987, 알파벳

[문제파악]
- 세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다.
- 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.
- 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다.
- 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
- 좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오.
- 말이 지나는 칸은 좌측 상단의 칸도 포함된다.

[입력]
- 첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다. (1 ≤ R,C ≤ 20)
- 둘째 줄부터 R개의 줄에 걸쳐서 보드에 적혀 있는 C개의 대문자 알파벳들이 빈칸 없이 주어진다.

[출력]
- 첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.

[구현방법]
- 개인적으론 이런 문제는 우선 가로세로를 잘 구분해야 했음
- 또한 알파벳을 기록하는 것도 필요함 같은 곳은 못가게 해야하니까
- 중복제거가 되는 Set으로 해서 contain 인지 확인하면 편할듯
- 처음 시작할 때, 시작점인 [1][1] 지점도 중복 처리 및 카운트에 넣어야함을 잊지말자

[보완점]
1) 일단 Set으로 중복처리를 해도 좋지만, boolean[26]개로 계산하는게 더 효율적이다
    - Set은 해시로 처리돼서 보통 O(1)이지만, 배열의 index로의 접근은 확실하게 O(1)이 걸린다
        - Set은 해시 연산 + 충돌 가능성도 있어서 '평균적인 속도'가 O(1)일 뿐, 항상 O(1)은 아니다
            - 충돌 가능성이란?
                - 해시 함수는 무한한 입력값을 유한한 범위의 출력값(해시값)으로 변환한다
                - 이 말인 즉, 다른 인풋임에도 유한한 범위를 가지기 때문에 같은 출력값(해시값)이 존재할 수 있다는 의미이다
                - 이 때 해시 값은 같은데 출력값이 다른 경우를 해시 충돌이라고 부른다
                - 따라서 해시 충돌이 많아지면 해시 테이블에서 충돌을 해결하는 작업(예: 체이닝, 개방 주소법 등)이 추가로 필요해지므로 성능이 저하된다
                - 이로 인해 최악의 경우 O(N)까지 시간이 늘어날 수 있음
    - 또한, HashSet은 객체 저장과 해시 테이블 유지에 추가 메모리가 필요하다 (배열이 더 적은 리소스를 사용하는 이유)
        - 일례로 "A"를 Set에 저장할 때도 객체 참조 비용 + 문자열 객체의 고정 메모리가 추가됨
        - boolean 타입은 일반적으로 1비트 또는 1바이트를 차지하므로 메모리 사용량이 훨씬 작고 고정된 크기라 오버헤드도 없음
    - 더군다나 이런 식으로 중복처리라면, isVisited 같은 추가 방문처리 체크는 필요하지 않는다
        - 한 번 지나온 곳은 중복된 알파벳처리 되어 어차피 못갈테니까

2) String.valueOf()를 쓰는 것보다 char 상태에서 'A'를 빼버리면 필요한 index 값을 얻을 수 있다
    - 'C' - 'A'해버리면 2가 나오는 이치와 같음

3) (1, 1)에서 시작하라고는 했지만 굳이 배열을 [R+1][C+1]로 만들 필요는 없다
    - 직관적으로 0부터 시작하는게 더 깔끔해보인다
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] moveRow = {-1, 0, 1, 0};
    static int[] moveCol = {0, -1, 0, 1};

    static int R, C;
    static int maxCount = Integer.MIN_VALUE;
    static char[][] board;
    static boolean[] isVisited = new boolean[26]; // 알파벳 방문 여부 저장 (A~Z)

    static void toString (char[][] input) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < input.length; i++) {
            sb.append(Arrays.toString(input[i])).append("\n");
        }

        System.out.println(sb);
    }

    static void DFS(int row, int col, int count) {
        maxCount = Math.max(maxCount, count);

        // 4방탐색
        for (int i = 0; i < moveRow.length; i++) {
            int nextRow = row + moveRow[i];
            int nextCol = col + moveCol[i];

            // 범위 밖을 벗어났거나, 방문한 적이 있는 알파벳이라면 패스
            if (isOutOfBound(nextRow, nextCol) || isVisited[board[nextRow][nextCol] - 'A']) continue;

            // 현재 위치 알파벳
            char currentChar = board[nextRow][nextCol];

            isVisited[currentChar - 'A'] = true;  // 다음 DFS를 진행하기 전에, 중복 처리와 방문처리를 진행한다

            // System.out.println(pastAlphabet);
            DFS(nextRow, nextCol, count + 1);

            isVisited[currentChar - 'A'] = false;  // 한 조건의 DFS가 끝났으므로 방금 전에 진행했던 중복처리와 방문처리를 롤백한다
        }
    }

    static boolean isOutOfBound (int row, int col) {
        return row < 0 || R <= row || col < 0 || C <= col;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        for (int row = 0; row < R; row++) {
            String input = br.readLine();

            for (int col = 0; col < C; col++) {
                board[row][col] = input.charAt(col);  // charAt(index)는 0부터 시작이니까 1부터 시작하는 col은 -1로 계산해줘야한다
            }
        }

        // toString(board);

        // 처음 시작할 때, 시작점인 [0][0] 지점도 중복처리 및 카운트에 넣어야함을 잊지말자
        isVisited[board[0][0] - 'A'] = true;
        DFS(0, 0, 1);

        System.out.println(maxCount);
    }
}