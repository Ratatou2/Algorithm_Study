/*
[백준]
16165, 걸그룹 마스터 준식이

[문제파악]
- 걸그룹 개인과 팀을 검색하여 외우게 하려고 함

[입력]
- 첫번째 줄에 총 입력받을 걸그룹의 수 N, 맞혀야할 문제의 수 M (0 < N, M < 100)
- 두번째 줄부터 각 걸그룹마다 팀 이름, 인원수, 멤버의 이름을 한줄씩 차레대로 입력받는다
	- 팀과 멤버의 이름은 최대 100글자
	- 모든 글자는 알파벳 소문자
	- 하나 or 서로 다른 걸그룹에 이름이 같은 두 멤버가 있는 경우는 없음
- 그다음부터는 M개의 퀴즈를 입력받음
	- 각각의 퀴즈는 두줄로 이뤄져 있음
	- 팀의 이름이나 멤버의 이름이 첫줄에 주어지고, 퀴즈의 종류를 나타내는 0 or 1이 두번째줄에 주어짐
	- 퀴즈의 종류가 0일 경우 팀의 이름이 주어지며, 1일 경우 멤버의 이름이 주어진다

[출력]
- 첫번째 줄부터 차례대로 퀴즈에 대한 답을 출력
	- 퀴즈의 종류가 0일 경우, 해당 팀에 속한 멤버의 이름을 사전순으로 한줄에 한명씩 출력
	- 퀴즈의 종류가 1이 경우, 해당 멤버가 속한 팀의 이름을 출력

[구현방법]
- Map을 두개 쓰면 될거 같은데
- 풀고나면 더 효율적인 방법이 있나 찾아봅시다

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());  // 걸그룹의 수
        int M = Integer.parseInt(st.nextToken());  // 맞춰야할 문제의 수

        Map<String, String[]> group = new HashMap<>();  // [걸그룹 이름] : [걸그룹에 속한 인원들]
        Map<String, String> whereIsMyTeam = new HashMap<>();  // [멤버] : [속한 걸그룹]

        // 걸그룹 + 구성원 input 받기
        for (int i = 0; i < N; i++) {
            String groupName = br.readLine();
            int memberCount = Integer.parseInt(br.readLine());
            String[] members = new String[memberCount];

            for (int j = 0; j < memberCount; j++) {
                String memberName = br.readLine();

                whereIsMyTeam.put(memberName, groupName);
                members[j] = memberName;
            }

            Arrays.sort(members);  // 사전 순으로 출력해야 하니까 정렬
            group.put(groupName, members);
        }

        // 문제 풀기
        for (int i = 0; i < M; i++) {
            String ask = br.readLine();
            int type = Integer.parseInt(br.readLine());

            // 0이면 멤버 이름 전체 사전순
            // 1이면 걸그룹 이름
            if (type == 0) {
                String[] members = group.get(ask);
                for (String temp : members)
                    sb.append(temp).append("\n");
            } else if (type == 1) {
                sb.append(whereIsMyTeam.get(ask)).append("\n");
            }
        }
        
        System.out.println(sb);
    }
}