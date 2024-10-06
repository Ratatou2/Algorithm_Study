/*
[백준]
2605, 줄세우기

[문제파악]
- 학생들이 한 줄로 줄을 선 후, 첫 번째 학생부터 차례로 번호를 뽑는다.
- 첫 번째로 줄을 선 학생은 무조건 0번 번호를 받아 제일 앞에 줄을 선다.
- 두 번째로 줄을 선 학생은 0번 또는 1번 둘 중 하나의 번호를 뽑는다.
- 0번을 뽑으면 그 자리에 그대로 있고, 1번을 뽑으면 바로 앞의 학생 앞으로 가서 줄을 선다.
- 세 번째로 줄을 선 학생은 0, 1 또는 2 중 하나의 번호를 뽑는다.
- 그리고 뽑은 번호만큼 앞자리로 가서 줄을 선다.
- 마지막에 줄을 선 학생까지 이와 같은 방식으로 뽑은 번호만큼 앞으로 가서 줄을 서게 된다.
- 각자 뽑은 번호는 자신이 처음에 선 순서보다는 작은 수이다.

- 예를 들어 5명의 학생이 줄을 서고, 첫 번째로 줄을 선 학생부터 다섯 번째로 줄을 선 학생까지 차례로 0, 1, 1, 3, 2번의 번호를 뽑았다고 하자,
- 첫 번째 학생부터 다섯 번째 학생까지 1부터 5로 표시하면 학생들이 줄을 선 순서는 다음과 같이 된다.

    첫 번째 학생이 번호를 뽑은 후 : 1
    두 번째 학생이 번호를 뽑은 후 : 2 1
    세 번째 학생이 번호를 뽑은 후 : 2 3 1
    네 번째 학생이 번호를 뽑은 후 : 4 2 3 1
    다섯 번째 학생이 번호를 뽑은 후 : 4 2 5 3 1
    따라서 최종적으로 학생들이 줄을 선 순서는 4, 2, 5, 3, 1이 된다.

- 줄을 선 학생들이 차례로 뽑은 번호가 주어질 때 학생들이 최종적으로 줄을 선 순서를 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에는 학생의 수가 주어지고 둘째 줄에는 줄을 선 차례대로 학생들이 뽑은 번호가 주어진다.
- 학생의 수가 100 이하이고, 학생들이 뽑는 번호는 0 또는 자연수이며 학생들이 뽑은 번호 사이에는 빈 칸이 하나씩 있다.

[출력]
- 학생들이 처음에 줄을 선 순서대로 1번부터 번호를 매길 때, 첫째 줄에 학생들이 최종적으로 줄을 선 순서를 그 번호로 출력한다.
- 학생 번호 사이에는 한 칸의 공백을 출력한다.

[구현방법]
이거 그냥 ArrayList로 구현해서 뽑은 학생 index를 중간에 끼워넣으면 될듯하다

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> seq = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int curr = Integer.parseInt(st.nextToken());
            seq.add(curr, i + 1);
        }

        for (int i = 0; i < N; i++) {
            sb.append(seq.get(N - 1 - i)).append(" ");
        }

        System.out.println(sb);
    }
}