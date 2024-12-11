/*
[백준]
5597, 과제 안 내신 분..?

[문제파악]
- X대학 M교수님은 프로그래밍 수업을 맡고 있다.
- 교실엔 학생이 30명이 있는데, 학생 명부엔 각 학생별로 1번부터 30번까지 출석번호가 붙어 있다.
- 교수님이 내준 특별과제를 28명이 제출했는데, 그 중에서 제출 안 한 학생 2명의 출석번호를 구하는 프로그램을 작성하시오.

[입력]
- 입력은 총 28줄로 각 제출자(학생)의 출석번호 n(1 ≤ n ≤ 30)가 한 줄에 하나씩 주어진다.
- 출석번호에 중복은 없다.

[출력]
- 출력은 2줄이다.
- 1번째 줄엔 제출하지 않은 학생의 출석번호 중 가장 작은 것을 출력하고, 2번째 줄에선 그 다음 출석번호를 출력한다.

[구현방법]
- 30명의 학생을 체크하는 boolean[] 배열을 만든 뒤 과제 낸 사람은 체크
- 입력을 다 받고 나면 내지 않은 사람들을 체크

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        boolean[] isSubmit = new boolean[30];

        for (int i = 0; i < 28; i++) {
            isSubmit[Integer.parseInt(br.readLine()) - 1] = true;
        }

        // 주의할 점은 두번쨰 반복문은 답을 찾는 것이니 30명 전부 다 탐색해야한다 (조건의 범위를 주의할 것)
        for (int i = 0; i < 30; i++) {
            if (!isSubmit[i]) sb.append(i + 1).append("\n");
        }

        System.out.println(sb);
    }
}