/*
[백준]
2947, 나무조각

[문제파악]
동혁이는 나무 조각을 5개 가지고 있다. 
나무 조각에는 1부터 5까지 숫자 중 하나가 쓰여져 있다. 
또, 모든 숫자는 다섯 조각 중 하나에만 쓰여 있다.

동혁이는 나무 조각을 다음과 같은 과정을 거쳐서 1, 2, 3, 4, 5 순서로 만들려고 한다.

첫 번째 조각의 수가 두 번째 수보다 크다면, 둘의 위치를 서로 바꾼다.
두 번째 조각의 수가 세 번째 수보다 크다면, 둘의 위치를 서로 바꾼다.
세 번째 조각의 수가 네 번째 수보다 크다면, 둘의 위치를 서로 바꾼다.
네 번째 조각의 수가 다섯 번째 수보다 크다면, 둘의 위치를 서로 바꾼다.
만약 순서가 1, 2, 3, 4, 5 순서가 아니라면 1 단계로 다시 간다.
처음 조각의 순서가 주어졌을 때, 위치를 바꿀 때 마다 조각의 순서를 출력하는 프로그램을 작성하시오.


[입력]
첫째 줄에 조각에 쓰여 있는 수가 순서대로 주어진다. 
숫자는 1보다 크거나 같고, 5보다 작거나 같으며, 중복되지 않는다. 
처음 순서는 1, 2, 3, 4, 5가 아니다.


[출력]
두 조각의 순서가 바뀔때 마다 조각의 순서를 출력한다.

[구현방법]


[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    static int[] woods;
    static StringBuilder result;

    static void changeWood (int indexA, int indexB) {
        int temp = woods[indexA];
        woods[indexA] = woods[indexB];
        woods[indexB] = temp;
    }

    static void addCurrWoods() {
        for (int temp : woods) {
            result.append(temp).append(" ");
        }

        result.append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        result = new StringBuilder();

        woods = new int[5];
        for (int i = 0; i < 5; i++) {
            woods[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = {1, 2, 3, 4, 5};
        while(!Arrays.equals(woods, answer)) {
            if (woods[1] < woods[0]) {
                changeWood(1, 0);
                addCurrWoods();
            }

            if (woods[2] < woods[1]) {
                changeWood(2, 1);
                addCurrWoods();
            }

            if (woods[3] < woods[2]) {
                changeWood(3, 2);
                addCurrWoods();
            }

            if (woods[4] < woods[3]) {
                changeWood(4, 3);
                addCurrWoods();
            }
        }

        System.out.println(result);
    }
}
