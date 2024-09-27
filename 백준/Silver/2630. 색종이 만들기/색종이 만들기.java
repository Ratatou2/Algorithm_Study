/*
[백준]
2630, 색종이만들기

[문제파악]
- 아래 <그림 1>과 같이 여러개의 정사각형칸들로 이루어진 정사각형 모양의 종이가 주어져 있고,
- 각 정사각형들은 하얀색으로 칠해져 있거나 파란색으로 칠해져 있다.
- 주어진 종이를 일정한 규칙에 따라 잘라서 다양한 크기를 가진 정사각형 모양의 하얀색 또는 파란색 색종이를 만들려고 한다.
- 전체 종이의 크기가 N×N(N=2k, k는 1 이상 7 이하의 자연수) 이라면 종이를 자르는 규칙은 다음과 같다.
- 전체 종이가 모두 같은 색으로 칠해져 있지 않으면 가로와 세로로 중간 부분을 잘라서 <그림 2>의 I, II, III, IV와 같이 똑같은 크기의 네 개의 N/2 × N/2색종이로 나눈다.
- 나누어진 종이 I, II, III, IV 각각에 대해서도 앞에서와 마찬가지로 모두 같은 색으로 칠해져 있지 않으면 같은 방법으로 똑같은 크기의 네 개의 색종이로 나눈다.
- 이와 같은 과정을 잘라진 종이가 모두 하얀색 또는 모두 파란색으로 칠해져 있거나, 하나의 정사각형 칸이 되어 더 이상 자를 수 없을 때까지 반복한다.
- 위와 같은 규칙에 따라 잘랐을 때 <그림 3>은 <그림 1>의 종이를 처음 나눈 후의 상태를, <그림 4>는 두 번째 나눈 후의 상태를, <그림 5>는 최종적으로 만들어진 다양한 크기의 9장의 하얀색 색종이와 7장의 파란색 색종이를 보여주고 있다.
- 입력으로 주어진 종이의 한 변의 길이 N과 각 정사각형칸의 색(하얀색 또는 파란색)이 주어질 때 잘라진 하얀색 색종이와 파란색 색종이의 개수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에는 전체 종이의 한 변의 길이 N이 주어져 있다. N은 2, 4, 8, 16, 32, 64, 128 중 하나이다. 색종이의 각 가로줄의 정사각형칸들의 색이 윗줄부터 차례로 둘째 줄부터 마지막 줄까지 주어진다. 하얀색으로 칠해진 칸은 0, 파란색으로 칠해진 칸은 1로 주어지며, 각 숫자 사이에는 빈칸이 하나씩 있다.

[출력]
첫째 줄에는 잘라진 햐얀색 색종이의 개수를 출력하고, 둘째 줄에는 파란색 색종이의 개수를 출력한다.

[구현방법]
- 분할정복하면 된다
- 그래 방법은 알겠는데 이거 뭐 어떻게 구현하라고...
- 일단 범위를 지정해줘야하고, 자른다면.. 감이 안잡히네 그게 제일 문제임 자르고 나면 배열의 범위를 어떻게 던져줄건지
- 아 일단 한번 쪼개면 무조건 4개가 나온다 범위도 그에 맞춰 지정해주면 된다!
- 근데 그럴거면 그냥 X의 스타트와 Y의 스타트 범위도 건네줘야할거 같은디
- 그치 탐색할 사이즈의 범위, X, Y를 건네줘야 얼만큼 쪼갰는지를 알 수 있네
- 아잇!!! 문제 똑바로 읽어야지 흰색도 세야한단다 아오

[보완점]
- 개선한다면 색상을 두개를 각각 세는게 아니라 첫번째 칸을 기준으로 잡고 해당 색과 다르면 break;해도 된다

8
1 1 0 0 0 0 1 1
1 1 0 0 0 0 1 1
0 0 0 0 1 1 0 0
0 0 0 0 1 1 0 0
1 0 0 0 1 1 1 1
0 1 0 0 1 1 1 1
0 0 1 1 1 1 1 1
0 0 1 1 1 1 1 1


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, countWhitePaper, countBluePaper;
    static boolean[][] paper;

    static void splitPaper(int x, int y, int N) {
        // 한 칸이면 무슨 색인지 확인만 하면 된다
        if(N == 1) {
            if(paper[x][y]) countBluePaper++;
            else countWhitePaper++;

            return;
        }

        // 현재 종이의 색상을 확인한다
        boolean mainColor = paper[x][y];
        boolean isAllSame = true;
        for (int i = x; i < x + N; i++) {
            for (int j = y; j < y + N; j++) {
                if (mainColor != paper[i][j]) {
                    isAllSame = false;
                    break;
                }
            }
        }

        // 칠해진 색종이 확인
        if (isAllSame) {
            if (mainColor) countBluePaper++;
            else countWhitePaper++;
            
            return;
        }

        // 색종이를 다시 4등분 한다
        int halfN = N / 2;
        
        splitPaper(x, y, halfN);
        splitPaper(x, y + halfN, halfN);
        splitPaper(x + halfN, y, halfN);
        splitPaper(x + halfN, y + halfN, halfN);
    }

    static void printPaper(int x, int y, int N) {
        StringBuilder sb = new StringBuilder();

        // 출력 기능 part
        for (int i = x; i < x + N; i++) {
            for (int j = y; j < y + N; j++) {
                sb.append(paper[i][j] ? "■ " : "□ ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        paper = new boolean[N][N];
        countBluePaper = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                paper[i][j] = st.nextToken().equals("1");
            }
        }

        splitPaper(0, 0, N);

        System.out.println(countWhitePaper + "\n" + countBluePaper);
    }
}