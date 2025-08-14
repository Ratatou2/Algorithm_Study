

/*
[백준]
22862, 가장 긴 짝수 연속한 부분 수열 (large)

[문제파악]
길이가 N인 수열 S가 있다.
수열 S는 1 이상인 정수로 이루어져 있다.
수열 S에서 원하는 위치에 있는 수를 골라 최대 K번 삭제를 할 수 있다.
예를 들어, 수열 S가 다음과 같이 구성되어 있다고 가정하자.

수열 S : 1 2 3 4 5 6 7 8
수열 S에서 4번째에 있는 4를 지운다고 하면 아래와 같다.

수열 S : 1 2 3 5 6 7 8
수열 S에서 최대 K번 원소를 삭제한 수열에서 짝수로 이루어져 있는 연속한 부분 수열 중 가장 긴 길이를 구해보자.

[입력]
수열 S의 길이 N와 삭제할 수 있는 최대 횟수인 K가 공백으로 구분되어 주어진다.
두 번째 줄에는 수열 S를 구성하고 있는 N개의 수가 공백으로 구분되어 주어진다.

[출력]
수열 S에서 최대 K번 원소를 삭제한 수열에서 짝수로 이루어져 있는 연속한 부분 수열 중 가장 긴 길이를 출력한다.

[구현방법]
- 아니 근데 어디 부분이 짝수가 많은지 어떻게 알아?
- 막말로 1 2 3 4 6 8 10 11 12 13 14 이렇게 되어있으면 앞에서부터 홀수 2개 지우는 것보다 가운데 4 6 8 10 연결된 양옆을 지우는게 나을텐데..?
- 근데 이게 문제의 핵심인 것 같긴한데 도저히 아이디어가 안 떠오름..
- 힌트 : 슬라이딩 윈도우 (투포인터)
    1) 일단 L, R 두개가 있으면, 초기값은 둘다 0으로 세팅
    2) 처음엔 R부터 1씩 늘려가며 홀수가 있으면 갯수를 카운트한다
    3) 이때 홀수 갯수가 삭제 가능 횟수 K를 초과하면 (R - L + 1) - K = [연속된 짝수의 갯수]를 구한다
    4) 연속된 짝수의 갯수를 갱신해가며, 수열의 끝까지 도달할때까지 반복한다
    5) R이 수열의 끝에 도달했다면 중단된다 (반복문으로 인한 종료, 별도로 종료는 필요없음)

[보완점]
- 내가 간과한 것들
    1) oddCount == K 일떄 L을 땡기는 것이 아니다
        - K < oddCount 일때, oddCount <= K가 될 떄까지 L을 떙기는 것이다
    2) 길이 계산할 떄 (R - L + 1) - K가 아니다. (R - L + 1) - oddCount다
        - 지금처럼 if (oddCount == K) 때만 값을 갱신하면? 2,2,2로 홀수가 아예 없는 경우 값을 갱신할 수 없다
        - 즉, 매번 갱신을 해주는 것이 확실하다
    3) maxSeq는 INTEGER.MIN_VALUE하면 안됨. 0이어야 함
        - 모두 홀수인 경우, INTEGER.MIN_VALUE의 실제 값인 -2147483648가 되어버리기 떄문임
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];

        // (0) 수열 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // (1) 일단 L, R 두개가 있으면, 초기값은 둘다 0으로 세팅
        int L = 0;
        int oddCount = 0;  // 홀수 카운트 변수
        int maxSeq = 0;  // 최대 연속 짝수 갯수

        // (2) 반복문 시작, 처음엔 R부터 1씩 늘려가며 홀수가 있으면 갯수를 카운트한다
        for (int R = 0; R < N; R++) {
            // 인덱스 R이 가리키는 숫자가 홀수면, 홀수 갯수 카운트
            if (nums[R] % 2 != 0) oddCount++;

            // (3) 이때 R이 삭제 가능 횟수 K에 도달하면 (R - L + 1) - oddCount = [연속된 짝수의 갯수]를 구한다
            // (4) 연속된 짝수의 갯수를 갱신해가며, 수열의 끝까지 도달할때까지 반복한다
            while (K < oddCount) {
                // 인덱스 L이 가리키는 숫자가 홀수이면, oddCoount - 1
                if (nums[L] % 2 != 0) oddCount--;
                L++;
            }

            maxSeq = Math.max((R - L + 1) - oddCount, maxSeq);
        }

        System.out.println(maxSeq);
    }
}
