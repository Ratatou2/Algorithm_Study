/*
[백준]
13701, 중복 제거

[문제파악]
- N개의 정수를 읽고 이들 중에서 반복되는 수를 제외하고 남은 N개의 수를 입력된 순서대로 출력하시오
- 이때 0 <= A_i < 2^25 = 33554432 (i=1,2,…,N.)
- 입력의 개수 N은 1이상 500만 이하이다.

[입력]
- 첫째 줄에 A1, A2, ..., AN이 주어짐.

[출력]
- B1, B2, ..., BN’을 출력.

[구현방법]
- 그냥 무식하게 구현하는거... 되긴 함 시간초과 ㅋ
- 33554432개 짜리 Boolean 배열을 만들어서 각 위치 index가 이전에 있었나 없었나 체크하면 훨씬 빠르겠지!.. 만...
- 그렇게 할 경우, 다 좋지만 순서를 알 수가 없음.
- 어디다가 비트마스킹을 적용해야하는지...

[보완점]
- BitSet이란게 있단다. 심지어 배열 크기조차 정해줄 필요없다고... (뭐 이딴게 다있ㅇ..읍읍)

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        BitSet isExist = new BitSet();

        int i = 1;
        while (st.hasMoreTokens()) {
            int number = Integer.parseInt(st.nextToken());

            // 이전에 없었던 번호면 BitSet에 표기 + 답에 추가
            if (!isExist.get(number)) {
                isExist.set(number);
                sb.append(number).append(" ");
            }
        }

        System.out.println(sb);
    }
}