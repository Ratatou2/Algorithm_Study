/*
[백준]
11005, 진법변환

[문제파악]
- 10진법 수 N이 주어진다.
- 이 수를 B진법으로 바꿔 출력하는 프로그램을 작성하시오.
- 10진법을 넘어가는 진법은 숫자로 표시할 수 없는 자리가 있다.
- 이런 경우에는 다음과 같이 알파벳 대문자를 사용한다.
- A: 10, B: 11, ..., F: 15, ..., Y: 34, Z: 35

[입력]
- 첫째 줄에 N과 B가 주어진다. (2 ≤ B ≤ 36) 
- N은 10억보다 작거나 같은 자연수이다.

[출력]
- 첫째 줄에 10진법 수 N을 B진법으로 출력한다.

[구현방법]
- 진법 변환 뭐 어떻게 하란건가 싶었는데 이런 내용이었구나..

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 10진법 수 N
        int B = Integer.parseInt(st.nextToken()); // 진법 B

        // 숫자가 0보다 크면 나눈다 (진법으로 변환해야하니까)
        while (0 < N) {
            int remainder = N % B;  // N을 B로 나눈 나머지
            
            if (remainder < 10) sb.append(remainder);  // 나머지가 10 미만이면, 숫자로 추가
            else sb.append((char) ('A' + remainder - 10));  // 나머지가 10 이상이면, 알파벳으로 추가 (대문자 A를 기준으로 더해서 대문자 문자열을 추가한다)
            
            N /= B;  // N을 B로 나누기
        }
        
        System.out.println(sb.reverse().toString());
    }
}