/*
[SWEA]
B형 01, 새로운 불면증 치료법

[문제파악]
- N의 배수번호인 양을 셀거다
    - 첫번째엔 N번 양을 세고, 두번째는 2*N번 양을 세고, K번째에는 K*N번 양을 센다
- 이전에 셌던 번호들의 각 자리수에서 0에서 9까지의 모든 숫자를 보는 것은 최소 몇 번 양을 센 시점일까?

[입력]
- 첫째줄에 테스트케이스 T
    - 각 테스트 케이스의 첫번째 줄에는 N이 주어진다

[출력]
- 각 케이스마다 #[테스트 케이스 번호]를 출력하고, 최소 몇번 양을 세었을 때 이전에 봤던 숫자들의 자릿수에서 0에서 9까지의 모든 숫자를 보게 되는지 출력한다

[구현방법]
- 이거 그냥 Map 써가지고 첨본것만 출력하면 되는거 아닌가?
- 출력값은 마지막으로 본 양의 순서인 i번쨰가 아닌, 그 양이 몇번 양이냐는 의미

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static boolean findAllNums(int[] numsCheck) {
        int count = 0;

        for (int i = 0; i < numsCheck.length; i++) {
            if (numsCheck[i] == 1) count++;
        }

        return count == 10;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test <= T; test++) {
            int N = Integer.parseInt(br.readLine());

            int[] numsCheck = new int[10];
            int i = 0;
            while (!findAllNums(numsCheck)) {
                i++;

                String[] currentNums = Integer.toString(N * i).split("");

                for (String tempString : currentNums) {
                    int tempInt = Integer.parseInt(tempString);

                    if (numsCheck[tempInt] == 1) continue;
                    else numsCheck[tempInt] = 1;
                }
            }

            System.out.println("#" + test + " " + i * N);
        }
    }
}