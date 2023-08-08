package Algorithm_0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_2563_색종이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] paperZone = new int[101][101];
        int paperCount = Integer.parseInt(br.readLine());
        int totalArea = 100 * paperCount;

        // 색종이 붙이기
        for (int i = 0; i < paperCount; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int leftSpace = temp[0];
            int bottomSpace = 101 - temp[1] - 10;

            // 행렬이 헷갈릴 수 있음 주의할 것
            for (int row = bottomSpace; row < bottomSpace + 10; row++) {
                for (int column = leftSpace; column < leftSpace + 10; column++) {
                    if (paperZone[row][column] == 1) totalArea--;  // 1이 겹칠 때 최대넓이에서 -1
                    if (paperZone[row][column] != 1) paperZone[row][column] = 1;
                }
            }
        }

        System.out.println(totalArea);
    }
}