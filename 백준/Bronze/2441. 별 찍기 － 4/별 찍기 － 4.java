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
- 오 JAVA 11부터는 [String].repeat() 이란걸 쓸 수 있다 넘 신기
- 그리고 나랑 똑같은 코드인데 72ms 나오는 사람은 뭐지..? JAVA 버전 차이인가? 함 해봐야지
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // repeat ver (JAVA 11부터 지원)
        for (int i = N; 0 < i; i--) {
            sb.append(" ".repeat(Math.max(0, N - i)));  // 공백부터 추가
            sb.append("*".repeat(i));  //
            sb.append("\n");
        }

          // 반복문 ver
//        for (int i = N; 0 < i; i--) {
//            for (int j = 0; j < N-i; j++) {
//                sb.append(" ");
//            }
//            for (int j = 0; j < i; j++) {
//                sb.append("*");
//            }
//            sb.append("\n");
//        }

        System.out.println(sb);
    }
}