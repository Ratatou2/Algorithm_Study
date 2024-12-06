/*
[백준]
2644, 촌수계산

[문제파악]
- 우리 나라는 가족 혹은 친척들 사이의 관계를 촌수라는 단위로 표현하는 독특한 문화를 가지고 있다.
- 이러한 촌수는 다음과 같은 방식으로 계산된다.
- 기본적으로 부모와 자식 사이를 1촌으로 정의하고 이로부터 사람들 간의 촌수를 계산한다.
- 예를 들면 나와 아버지, 아버지와 할아버지는 각각 1촌으로 나와 할아버지는 2촌이 되고, 아버지 형제들과 할아버지는 1촌, 나와 아버지 형제들과는 3촌이 된다.
- 여러 사람들에 대한 부모 자식들 간의 관계가 주어졌을 때, 주어진 두 사람의 촌수를 계산하는 프로그램을 작성하시오.

[입력]
- 사람들은 1, 2, 3, …, n (1 ≤ n ≤ 100)의 연속된 번호로 각각 표시된다.
- 입력 파일의 첫째 줄에는 전체 사람의 수 n이 주어지고, 둘째 줄에는 촌수를 계산해야 하는 서로 다른 두 사람의 번호가 주어진다.
- 그리고 셋째 줄에는 부모 자식들 간의 관계의 개수 m이 주어진다.
- 넷째 줄부터는 부모 자식간의 관계를 나타내는 두 번호 x,y가 각 줄에 나온다.
- 이때 앞에 나오는 번호 x는 뒤에 나오는 정수 y의 부모 번호를 나타낸다.
- 각 사람의 부모는 최대 한 명만 주어진다.

[출력]
- 입력에서 요구한 두 사람의 촌수를 나타내는 정수를 출력한다.
- 어떤 경우에는 두 사람의 친척 관계가 전혀 없어 촌수를 계산할 수 없을 때가 있다.
- 이때에는 -1을 출력해야 한다.

[구현방법]
- DFS로 풀어보려고 하는데 가물가물하다...
- 이게 진짜 자주 안쓰니까 기억이 안나네
- 풀긴 풀었는데 이 방식이 맞나 싶고...

[보완점]


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, count;
    static boolean[] isVisited;
    static boolean[][] isConnected;

    static void toString(boolean[][] input) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < input.length; i++) {
            for (int j = 1; j < input[i].length; j++) {
                if (input[i][j]) sb.append("⬜️");
                else sb.append("⬛️");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void DFS(int startNum, int endNum) {
        if (startNum == endNum) {
            System.out.println(count);
            System.exit(0);
        }

        for (int i = 1; i <= N; i++) {
            // 방문 했었거나, 연결된 사람 아니면ㄲㄲ 패스
            if (isVisited[i] || !isConnected[startNum][i]) continue;

            // 방문하지 않았던 사람이면 방문처리 + 촌수 +1 하고 거기서부터 DFS 시작
            isVisited[i] = true;
            count++;
            DFS(i, endNum);
            isVisited[i] = false;
            count--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());  // 전체 인원 수
        isVisited = new boolean[N + 1];
        count = 0;  // 촌수 계산

        StringTokenizer st = new StringTokenizer(br.readLine());
        int startNum = Integer.parseInt(st.nextToken());
        int endNum = Integer.parseInt(st.nextToken());

        int relationCount = Integer.parseInt(br.readLine());
        isConnected = new boolean[N+1][N+1];


        for (int i = 1; i <= relationCount; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            // 촌수 계산이다 보니 양쪽으로 계산할 수 있으므로 양쪽 다 연결 처리해준다
            isConnected[row][col] = true;
            isConnected[col][row] = true;
        }

        //toString(isConnected);

        isVisited[startNum] = true;
        DFS(startNum, endNum);

        // DFS 내부에 값을 찾으면 System.exit(0)으로 종료되도록 해두었다
        // 때문에 여기까지 왔다는 것은 답을 찾지 못했음을 의미한다 (=> -1 출력)
        System.out.println(-1); 
    }
}