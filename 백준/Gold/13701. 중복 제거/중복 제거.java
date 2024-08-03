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
- 비트 마스킹 풀이는 이해하는데 좀 어려웠다
- 일단 int는 32bit이다 그러니까 int 배열을 만들어서 이걸 bitset처럼 쓰겠다는 의미 (대단한 발상...)
- 32는 2^5니까 주어진 조건인 2^25를 해소하려면 int 배열은 2^20만큼 필요하게 되는것이고 그렇기에 int[] number = new int[1<<20];가 되는 것이다.
- a / 32는 배열에서의 index를 의미
- a % 32는 배열 index를 내부의 bit를 의미
- 그렇게해서 이전에 등록된 적이 있으면 넘어가고, 아니면 등록하는 로직
- 이런 아이디어는 대체 어떻게 생각해내는 것인지 참... 신기...
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        BitSet isExist = new BitSet();

//        while (st.hasMoreTokens()) {
//            int number = Integer.parseInt(st.nextToken());
//
//            // 이전에 없었던 번호면 BitSet에 표기 + 답에 추가
//            if (!isExist.get(number)) {
//                isExist.set(number);
//                sb.append(number).append(" ");
//            }
//        }

        // 비트마스크 풀이
        int[] number = new int[1 << 20];
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            int a = Integer.parseInt(st.nextToken());
            int x = a / 32;
            int y = a % 32;

            // 해당 몫과 나머지가 기존에 있었는지 체크
            if((number[x] & (1 << y)) != (1 << y)){
                
                // 해당 몫 위치에 나머지 원소 저장
                number[x] |= (1 << y);
                sb.append(a).append(" ");
            }
        }

        System.out.println(sb);
    }
}