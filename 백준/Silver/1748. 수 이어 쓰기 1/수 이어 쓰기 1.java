

/*
[백준]
1748, 수 이어 쓰기 1

[문제파악]
1부터 N까지의 수를 이어서 쓰면 다음과 같이 새로운 하나의 수를 얻을 수 있다.
1234567891011121314151617181920212223...
이렇게 만들어진 새로운 수는 몇 자리 수일까? 이 수의 자릿수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 N(1 ≤ N ≤ 100,000,000)이 주어진다.

[출력]
첫째 줄에 새로운 수의 자릿수를 출력한다.

[구현방법]
- 흠.. 자릿수로 나눠서 (10^N) 계산을 하는게 제일 빠르지 않나
- 나누는거 아니야.. 단순하게 생각해서 그냥 각 자릿수 계산하는거다
    - 1의 자리 1~9 -> 9개
    - 10의 자리 10~99 -> 90개
    - 100의 자리 100~999 -> 900개
    ...
- 이런식으로 점진적으로 늘려가는거 계산하는거임

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int count = 0;
        int length = 1;  // 자릿수 계산
        int start = 1;

        while (start <= N) {
            int end = start * 10 - 1;  // 현 자릿수의 숫자 중 마지막 수 구하는 것
            
            if (N < end) end = N;  // N이 해당 구간보다 작으면 N으로 제한하는 것임 (3자리는 999이지만? N이 120이면 120으로 세팅)
            count += (end - start + 1) * length;  // 그리고 자릿수만큼 곰함
            length++;
            start *= 10;
        }

        System.out.println(count);
    }
}
