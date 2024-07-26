/*
[백준]
2441, 별찍기4

[문제파악]
- 별을 찍되 오른쪽 정렬할 것

[입력]
- 첫째줄에 N (1 <= N <= 100)

[출력]
- 별찍어야지 뭐...

[구현방법]
- 그냥 뭐 공백부터 찍어주고 그 뒤에 별 찍으면 되지

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = N; 0 < i; i--) {
            for (int j = 0; j < N-i; j++) {
                sb.append(" ");
            }
            for (int j = 0; j < i; j++) {
                sb.append("*");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
}