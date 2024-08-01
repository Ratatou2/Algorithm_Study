/*
[백준]
2961, 도영이가 만든 맛있는 음식

[문제파악]
- 재료 N개
- 각 재료의 신맛 S와 쓴맛 B를 알고 있다
- 이런 재료로 요리할 때, 신맛은 사용한 재료의 신맛의 곱이고, 쓴맛은 합이다
- 요리의 신맛과 쓴맛의 차이를 작게 만드려고 한다
- 최소한 하나 이상의 재료를 사용해야한다
- 재료의 신맛과 쓴맛이 주어졌을 때, 신맛과 쓴맛의 차이가 가장 작은 요리를 만들어라

[입력]
- 첫째줄에 재료의 갯수 N (1 <= N <= 10)
- 둘째줄부터 신맛과 쓴맛이 공백으로 구분되어 주어짐
- 모든 재료를 사용해서 요리를 만들었을 때, 그 요리의 신맛과 쓴맛은 모두 1,000,000,000보다 작은 양의 정수

[출력]
- 첫째줄에 신맛과 쓴맛의 차이가 가장 작은 요리의 차이를 출력

[구현방법]
- 도영이가 만든 쿠키~
- 이것도 비트마스킹.. 이라면 그 방식이 있지
- 어떤 재료를 쓰고, 쓰지 않고
- 조합을 구해서 그 조합일 때의 신맛과 쓴맛은 어떻게 되는지!
- 재료의 갯수가 많아야 10개니까 리스트로 짜면 될것 같다

[보완점]
- 하하.. 쓴맛이 먼저가 아니라 신맛이 먼전뎅...
- JAVA11에서 128ms! JAVA8로 하면 많이 빨라지나...?
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Ingredient> ingredients;

    static class Ingredient {
        int sour;
        int bitter;

        public Ingredient(int sour, int bitter) {
            this.sour = sour;
            this.bitter = bitter;
        }

        @Override
        public String toString() {
            return "Ingredient{" +
                    "sour=" + sour +
                    ", bitter=" + bitter +
                    '}';
        }
    }

    static int calculateIngredient(int i) {
        int totalBiiter = 0;
        int totalSour = 1;

        int index = 0;
        while (0 < i) {
            if ((i & 1) == 1) {
                Ingredient currentIngredient = ingredients.get(index);
                totalSour *= currentIngredient.sour;
                totalBiiter += currentIngredient.bitter;
            }

            i >>= 1;
            index++;
        }

//        System.out.println("현재 쓴맛 " + totalBiiter + " / 현재 신맛 " + totalSour);
//        System.out.println("======================");

        return Math.abs(totalBiiter - totalSour);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ingredients = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            int tempSour = Integer.parseInt(temp[0]);
            int tempBitter = Integer.parseInt(temp[1]);
            ingredients.add(new Ingredient(tempSour, tempBitter));
        }

        int i = 1;
        int minCombination = Integer.MAX_VALUE;
        while (true) {
            // [주의] 이 반복문은 언제까지? i를 2진수로 만든 값이 N보다 커질 때까지!
            // 예를들어 N을 4로 입력 받았으면, 1111까진 해봐야한다. -> 즉 N보다 커질 때 멈춰야 함!!!!
            String currentIngredientUse = Integer.toBinaryString(i);
            if (N < currentIngredientUse.length()) break;

            // 이제 i를 2진수로 만들어서 1인 재료를 전부 계산해야 함
            minCombination = Math.min(minCombination, calculateIngredient(i));

            // 다음 사용 계산
            i++;
        }

        System.out.println(minCombination);
    }
}