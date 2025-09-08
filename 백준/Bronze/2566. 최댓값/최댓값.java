

/*
[백준]
2566, 최댓값

[문제파악]
- 81개의 숫자 중에 가장 큰 숫자와 좌표 저장

*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxValue = Integer.MIN_VALUE;
        int[] location = new int[2];

        for (int row = 0; row < 9; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int col = 0; col < 9; col++) {
                int curr = Integer.parseInt(st.nextToken());

                if (curr <= maxValue) continue;  // 값이 같거나 작으면 갱신없이 패스

                maxValue = curr;
                location[0] = row;
                location[1] = col;
            }
        }

        System.out.println(maxValue);
        System.out.println((location[0] + 1) + " " + (location[1] + 1));
    }
}
