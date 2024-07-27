import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
[SWEA]
B형, 02, 이진수 표현

[문제파악]
- 정수 N, M 이 주어짐.
- M의 이진수 표현의 마지막 N 비트가 모두 1로 켜져 있는지 아닌지를 판별하여 출력

[입력]
- 첫번째 줄에 테스트 케이스의 수 TC
- 이후 TC개의 테스트 케이스가 새 줄로 구분되어 주어짐.
- 각 테스트 케이스는 다음과 같이 구성되었다.
- 첫 번째 줄에 정수 N, M이 주어진다. (1 ≤ N ≤ 30 , 0 ≤ M ≤ 108)

[출력]
- 각 테스트 케이스마다 한 줄씩 출력
- 마지막 N개의 비트가 모두 켜져 있다면 ON
- 아니면 OFF 를 출력하라.

[구현방법]
- 일단 입력 받은 숫자를 계속 2로 나눠가며 2진수로 구한다
- 나눌 때마다 나머지를 배열에 밀어넣고 그 배열은 N까지 반복하면서 확인한다
- 2진수의 마지막 값부터 배열에 밀어 넣는 것이니까 앞에서부터만 확인하면 된다

[보완점]
- 일단 직접 2진수로 만들어줄 필요도 없고(Integer.toBinaryString), 비트 마스킹이란걸 쓰면 편하게 할 수 있다고 함
- 애초에 비트마스킹 처음 접했을 때, 어려워서 기피했던 거 같은데 이 기회에 짚고 넘어가는게 좋겠다
*/
public class Solution {
    static boolean makeBinaryAndCheck(int N, int num) {
        StringBuilder binary = new StringBuilder();
        boolean result = false;

        String[] temp = Integer.toBinaryString(num).split("");

        // N개만큼 켜져 있는지 확인
        for (int i = 0; i < N; i++) {
            if (temp.length <= i) return false;
            if (Integer.parseInt(temp[temp.length - i - 1]) == 1) result = true;
            else return false;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test =1; test <= T; test++) {
            String[] input = br.readLine().split(" ");

            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);

            String result = makeBinaryAndCheck(N, M) ? "ON" : "OFF";
            System.out.println("#" + test + " " + result);
        }
    }
}