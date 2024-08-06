/*
[백준]
1764, 듣보잡

[문제파악]
- 듣도 못한 사람의 명단과, 보도 못한 사람의 명단이 주어질 때, 듣도 보도 못한 사람의 명단을 구하는 프로그램을 작성할 것

[입력]
- 첫째 줄에 듣도 못한 사람의 수 N, 보도 못한 사람의 수 M이 주어진다. (N, M은 500,000 이하의 자연수)
- 이어서 둘째 줄부터 N개의 줄에 걸쳐 듣도 못한 사람의 이름과, N+2째 줄부터 보도 못한 사람의 이름이 순서대로 주어진다.
	- 이름은 띄어쓰기 없이 알파벳 소문자로만 이루어지며, 그 길이는 20 이하이다.
- 듣도 못한 사람의 명단에는 중복되는 이름이 없으며, 보도 못한 사람의 명단도 마찬가지이다.

[출력]
- 듣보잡의 수와 그 명단을 사전순으로 출력한다.


[구현방법]
- 그냥 뭐... Set에 밀어넣고 중복되는거 있나 보고 없으면 정답에 추가하고
- 나중에 정렬하기

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

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<String> neverHeard = new HashSet<String>();
        List<String> neverheardNeverSeen = new ArrayList<String>();

        for (int i = 1; i <= N; i++) {
            neverHeard.add(br.readLine());
        }

        for (int i = 1; i <= M; i++) {
            String temp = br.readLine();

            if (!neverHeard.contains(temp)) continue;
            neverheardNeverSeen.add(temp);
        }

        Collections.sort(neverheardNeverSeen);

        System.out.println(neverheardNeverSeen.size());
        for (String temp : neverheardNeverSeen) {
            System.out.println(temp);
        }
    }
}