

/*
[백준]
2442, 별찍기 5

[유의사항]
JAVA 8 이하는 반복문 / JAVA 11 부터는 (문자열).repeat()을 쓰면 편하다
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            String stars = "*".repeat(2 * i - 1);
            String space = " ".repeat(N - i);

            sb.append(space).append(stars).append("\n");
        }

        System.out.println(sb);
    }
}
