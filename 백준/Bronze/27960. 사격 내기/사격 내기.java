/*
[백준]
27960, 사격내기

[문제파악]
- 과녁별로 각각 1점 / 2점 / 4점 / 8점 / 16점 / 32점 / 64점 / 128점 / 256점 / 512점을 얻을 수 있다
- A와 B의 득점을 보고 둘 중 한명만 맞힌 표적은 C도 맞췄고, 둘다 맞히거나 못 맞힌건 C는 못맞췄다
- A, B의 접수로 C의 점수를 구하라

[입력]
- 첫 번째 줄에 A와 B의 영점 사격 총합 점수인 정수 S가 주어진다.
- A와 B의 점수를 과녁의 점수 합으로 나타낼 수 없는 경우는 주어지지 않는다.

[출력]
- C의 점수를 출력

[구현방법]
- 배열로 2진수 표기하고, 하나씩 돌면서 XOR 비트마스킹 연산해야하는 것 아닌가..?
- 웬지 답은 나올 것 같지만, 이 방식보다 더 쉬운게 있을 것 같은 느낌... ㅠ

[보완점]


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] score;

    static int[] findHitTargetList(int num, int[] input) {

        // for문을 돌며 큰 숫자부터 뺄 수 있으면 차감해가며 맞춘 과녁을 찾는다
        for (int i = 0; i < score.length; i++) {
            if (0 <= num - score[i]) {
                num -= score[i];  // 총점에서 맞춘 과녁 차감
                input[i] = 1;  // 해당 과녁 맞춤 처리
            }
        }

        return input;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        score = new int[]{512, 256, 128, 64, 32, 16, 8, 4, 2, 1};

        int A_score = Integer.parseInt(st.nextToken());
        int B_score = Integer.parseInt(st.nextToken());

        // 둘이 맞힌 과녁을 찾는다
        int[] A_targetList = findHitTargetList(A_score, new int[10]);
        int[] B_targetList = findHitTargetList(B_score, new int[10]);

        int result = 0;
        for (int i = 0; i < score.length; i++) {
            int currentA = A_targetList[i];
            int currentB = B_targetList[i];

            // A와 B가 맞춘 상태가 서로 다르면 C가 맞춘 과녁이다
            if (currentA != currentB) result += score[i];
        }

        System.out.println(result);
    }
}