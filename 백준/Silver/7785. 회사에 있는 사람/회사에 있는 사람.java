/*
[백준]
7785, 회사에 있는 사람

[문제파악]
- 상근이는 세계적인 소프트웨어 회사 기글에서 일한다.
- 이 회사의 가장 큰 특징은 자유로운 출퇴근 시간이다.
- 따라서, 직원들은 반드시 9시부터 6시까지 회사에 있지 않아도 된다.
- 각 직원은 자기가 원할 때 출근할 수 있고, 아무때나 퇴근할 수 있다.
- 상근이는 모든 사람의 출입카드 시스템의 로그를 가지고 있다.
- 이 로그는 어떤 사람이 회사에 들어왔는지, 나갔는지가 기록되어져 있다.
- 로그가 주어졌을 때, 현재 회사에 있는 모든 사람을 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 로그에 기록된 출입 기록의 수 n이 주어진다. (2 ≤ n ≤ 106)
- 다음 n개의 줄에는 출입 기록이 순서대로 주어지며, 각 사람의 이름이 주어지고 "enter"나 "leave"가 주어진다. "enter"인 경우는 출근, "leave"인 경우는 퇴근이다.
- 회사에는 동명이인이 없으며, 대소문자가 다른 경우에는 다른 이름이다.
- 사람들의 이름은 알파벳 대소문자로 구성된 5글자 이하의 문자열이다.

[출력]
- 현재 회사에 있는 사람의 이름을 사전 순의 역순으로 한 줄에 한 명씩 출력한다.

[구현방법]
- 사실 map을 써서 <이름, 회사 존재 여부>를 저장하면 된다
- 근데 더 편하게 할거면 그냥 Set을 써서 목록에 있는지 없는지 여부만 체크하면 된다
- 그렇게 하면 굳이 존재 여부를 따로 저장할 필요조차 없음
- 역사전 순으로 출력은 그냥 TreeSet 만들 때부터 실행하면 됨

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        TreeSet<String> peopleInTheBuilding = new TreeSet<>(Comparator.reverseOrder());  // 역 사전 순으로 출력하기 위해 reverse

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();

            // 빌딩 안에 있는 사람이면 삭제, 아니면 더하기
            if(peopleInTheBuilding.contains(name)) peopleInTheBuilding.remove(name);
            else peopleInTheBuilding.add(name);
        }

        for (String name : peopleInTheBuilding) {
            sb.append(name).append("\n");
        }

        System.out.println(sb);
    }
}