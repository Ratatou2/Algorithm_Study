package Algorithm_0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_2012_Rank {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int students = Integer.parseInt(br.readLine());
        int[] rank = new int[students];
        long totalDissatisfy = 0;

        // 예상 등수 입력 받기
        for (int i = 0; i < students; i++) rank[i] = Integer.parseInt(br.readLine());

        Arrays.sort(rank);  // 정렬하기

        // 현재 등수 - 예상등수 빼서 차이 만큼 불만도 총합에 더하기
        for (int i = 0; i < students; i++) totalDissatisfy += Math.abs((i + 1) - rank[i]);

        System.out.println(totalDissatisfy);
    }
}

// 이 문제 주의해야 하는게 ttoalDissatisfy가 int면 터질 수 있음
// 예시가 max 500,000명까지 들어올 수 있는데 이놈들이 죄다 1등을 희망하면 바로 터짐
// 파이썬이 보고 싶네...