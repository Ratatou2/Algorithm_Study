package Algorithm_0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_11047_Coin0 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tempInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int totalMoney = tempInput[1];
        int[] moneyType = new int[tempInput[0]];
        int totalCount = 0;

        // 돈 종류 입력 받기
        for (int i = 0; i < tempInput[0]; i++) moneyType[i] = Integer.parseInt(br.readLine());

        while (0 < totalMoney) {
            for (int i = moneyType.length - 1; 0 <= i; i--) {
                if (totalMoney < moneyType[i]) continue;  // 총액보다 현재 나눌 moneyType이 더 크면 할필요 없음

                totalCount += totalMoney / moneyType[i];  // 돈으로 나눈 갯수만큼 totalCount에 더해주기
                totalMoney = totalMoney % moneyType[i];  // 잔액이 totalCount가 되고 다시 반복
            }
        }

        System.out.println(totalCount);
    }
}

// 구현하는데 대략 20분 소요