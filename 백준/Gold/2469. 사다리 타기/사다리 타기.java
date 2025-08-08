

/*
[백준]
2469, 사다리타기

[문제파악]
k명의 참가자들이 사다리 타기를 통하여 어떤 순서를 결정한다.
참가자들은 알파벳 대문자 첫 k개로 표현되며, 사다리 타기를 시작할 때의 순서는 아래 그림과 같이 항상 알파벳 순서대로이다.
k=10 인 예를 들어 보자.
10명의 A, B, C, D, E, F, G, H, I, J 참가자들이 사다리 타기를 준비한다.
아래 그림은 10개의 세로 줄과 5개의 가로 줄을 가지고 있는 사다리의 한 예를 보여주고 있다.
이 사다리에서 점선은 가로 막대가 없음을, 굵은 가로 실선은 옆으로 건너갈 수 있는 가로 막대가 있음을 나타내고 있다.
따라서 위에 제시된 사다리를 타면 그 최종 도달된 순서는 왼쪽으로부터 A, C, G, B, E, D, J, F, I, H 가 된다.
사다리 타기는 세로 막대를 타고 내려오는 중에 가로막대를 만나면 그 쪽으로 옮겨 가면서 끝까지 내려가는 과정이다.
따라서 사다리 타기의 규칙 특성상 아래 그림과 같이 두 가로 막대가 직접 연결될 수는 없으므로 이 상황은 이 문제에서 고려할 필요가 없다.
우리는 하나의 가로 줄이 감추어진 사다리를 받아서 그 줄의 각 칸에 가로 막대를 적절히 넣어서 참가자들의 최종 순서가 원하는 순서대로 나오도록 만들려고 한다.

입력에서 사다리의 전체 모양은 각 줄에 있는 가로 막대의 유무로 표현된다.
각 줄에서 가로 막대가 없는 경우에는 ‘*’(별)문자, 있을 경우에는 ‘-’(빼기) 문자로 표시된다.
그리고 감추어진 특정 가로 줄은 길이 k-1인 ‘?’ (물음표) 문자열로 표시되어 있다.

[입력]
첫 줄에는 참가한 사람의 수 k가 나온다(3 ≤ k ≤ 26).
그 다음 줄에는 가로 막대가 놓일 전체 가로 줄의 수를 나타내는 n이 나온다(3 ≤ n ≤ 1,000).
그리고 세 번째 줄에는 사다리를 타고 난 후 결정된 참가자들의 최종 순서가 길이 k인 대문자 문자열로 들어온다.
k와 n, 그리고 도착순서 문자열이 나타난 다음, 이어지는 n개의 줄에는 앞서 설명한 바와 같이 ‘*’와 ‘-’ 문자로 이루어진 길이 k-1인 문자열이 주어진다.
그 중 감추어진 가로 줄은 길이가 k-1인 ‘?’ 문자열로 표시되어 있다.

[출력]
입력 파일 세 번째 줄에서 지정한 도착순서가 해당 사다리에서 만들어질 수 있도록 감추어진 가로 줄을 구성해야 한다.
여러분은 감추어진 가로 줄의 상태를 재구성하여 이를 ‘*’(별) 문자와 ‘-’(빼기) 문자로 구성된 길이 k-1인 문자열로 만들어 출력하면 된다.
그런데 어떤 경우에는 그 감추어진 가로 줄을 어떻게 구성해도 원하는 순서를 얻을 수 없는 경우도 있다.
이 경우에는  ‘x’(소문자 엑스)로 구성된 길이 k-1인 문자열을 출력해야 한다.

[구현방법]
- 이건 아무리 봐도 그래프 탐색 문제에 더 적합한거 아닌가? DFS나 BFS?
- 힌트를 좀 살펴봤는데 이건 그냥 문자열 '스왑'으로 해결할 수 있는 문제라서 문자열 문제였던 것이다
- ?~? 줄 까지 두가지를 준비한다
    1) 위에서 아래로 swap 하며 내려온 결과
    2) 아래에서 위로 swap 하며 올라온 결과
- 둘을 비교해가며 ?~?를 추론하면 됨
- 아니 근데 이걸 모든 문자열이 동시에 진행하나? 하긴 스왑이면 그게 맞긴한데...

[보완점]
- 문제를 한번에 풀지 못한 이유는 큰 문제를 작게 나눌줄 몰라서, 나누더라도 어떻게 나눠야할지 몰라서이다
- 경험의 부재인듯 더 다양한 문제풀면 됨 ㅇㅇ

- char[] 같은 경우는 그냥 new String(<char[] 배열>) 넣으면 알아서 한줄 string으로 만들어준다! 겁나 편함;
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        char[] line = new char[K];
        for (int i = 0; i < K; i++) {
            line[i] = (char) ('A' + i);
        }

        char[] result = br.readLine().toCharArray();
        char[][] ladderMap = new char[N][K-1];
        int hiddenRow = -1;

        // 사다리 입력 받기
        for (int row = 0; row < N; row++) {
            char[] temp = br.readLine().toCharArray();

            if (temp[0] == '?') hiddenRow = row;  // ?가 있는 행번호 기록
            for (int col = 0; col < K - 1; col++) {
                ladderMap[row][col] = temp[col];
            }
        }

        // 스왑 비교를 위한 배열 두개
        char[] topArray = Arrays.copyOf(line, line.length);
        char[] bottomArray = Arrays.copyOf(result, result.length);

        // 위에서 아래로 swap
        for (int row = 0; row < hiddenRow; row++) {
            for (int col = 0; col < K - 1; col++) {
                if (ladderMap[row][col] != '-') continue;  // 이동하는 사다리가 아니라면 패스

                char temp = topArray[col + 1];
                topArray[col + 1] = topArray[col];  // 현재 위치의 것을 옆으로 한칸 이동
                topArray[col] = temp;  // 옆칸에 있던 것은 현재 위치로 이동
            }
        }

        // 아래에서 위로 swap (역순으로 탐지해야함을 잊지 말자)
        for (int row = N - 1; hiddenRow < row; row--) {
            for (int col = 0; col < K - 1; col++) {
                if (ladderMap[row][col] != '-') continue;

                char temp = bottomArray[col + 1];
                bottomArray[col + 1] = bottomArray[col];
                bottomArray[col] = temp;
            }
        }

        char[] answer = new char[K - 1];
        Arrays.fill(answer, '*');

        // topArray와 bottomArray 비교해서 ? 채워나가기
        // 동일하면 *인 것이고, 한칸 옆에 있다면 좌,우 따져서 -로 바꿔주고, 그 이상 차이가 난다면 불가능한 사다리 세팅인 것이다!
        for (int col = 0; col < K - 1; col++) {
            // 동일한지 체크
            if (topArray[col] == bottomArray[col]) continue;

            // 현재 칸과 한칸 뒤에 있는 칸을 스왑했을 때 서로가 같은지 체크
            if (topArray[col] == bottomArray[col + 1] && bottomArray[col] == topArray[col + 1]) {
                answer[col] = '-';

                // 스왑하면 된다는 것을 알았으니 뒤의 수식을 위해서라도 자리를 스왑
                char temp = topArray[col + 1];
                topArray[col + 1] = topArray[col];
                topArray[col] = temp;
            } else {
                // 불가능한 케이스는 더 볼 것없이 x로 채우고 종료
                Arrays.fill(answer, 'x');
                break;
            }
        }

        System.out.println(new String(answer));
    }
}
