/*
[백준]
15000, CAPS

[문제파악]

[입력]

[출력]

[구현방법]
- 아기상어 풀다가 시간이 벌써 이렇게 됐네 ㅠㅠㅠ
- 1일 1알고리즘인디... 일단 이거 풀자
- 얘는 그냥 대문자 만들면 된다. 그리고 String엔 toUpperCase()라는 기능이 있지!

[보완점]


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(br.readLine().toUpperCase());
    }
}