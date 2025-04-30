/*
[백준]
1976, 여행 가자

[문제파악]
- 동혁이는 친구들과 함께 여행을 가려고 한다.
- 한국에는 도시가 N개 있고 임의의 두 도시 사이에 길이 있을 수도, 없을 수도 있다.
- 동혁이의 여행 일정이 주어졌을 때, 이 여행 경로가 가능한 것인지 알아보자.
- 물론 중간에 다른 도시를 경유해서 여행을 할 수도 있다.
- 예를 들어 도시가 5개 있고, A-B, B-C, A-D, B-D, E-A의 길이 있고, 동혁이의 여행 계획이 E C B C D 라면 E-A-B-C-B-C-B-D라는 여행경로를 통해 목적을 달성할 수 있다.
- 도시들의 개수와 도시들 간의 연결 여부가 주어져 있고, 동혁이의 여행 계획에 속한 도시들이 순서대로 주어졌을 때 가능한지 여부를 판별하는 프로그램을 작성하시오.
- 같은 도시를 여러 번 방문하는 것도 가능하다.

[입력]
- 첫 줄에 도시의 수 N이 주어진다. N은 200이하이다.
- 둘째 줄에 여행 계획에 속한 도시들의 수 M이 주어진다.
- M은 1000이하이다.
- 다음 N개의 줄에는 N개의 정수가 주어진다.
- i번째 줄의 j번째 수는 i번 도시와 j번 도시의 연결 정보를 의미한다.
- 1이면 연결된 것이고 0이면 연결이 되지 않은 것이다.
- A와 B가 연결되었으면 B와 A도 연결되어 있다.
- 마지막 줄에는 여행 계획이 주어진다.
- 도시의 번호는 1부터 N까지 차례대로 매겨져 있다.

[출력]
- 첫 줄에 가능하면 YES 불가능하면 NO를 출력한다.

[구현방법]
- 이것은 뭔가... 어떻게 푸나 싶었더니 Union-Find 문제였다
- 이렇게 모르는 알고리즘이 나오면 맨날 틀리고 맨날 안외운다 더이상 도망갈 곳은 없다 이해가 안되면 외우도록!
- 한번에 이해가 안되는데요...? 오늘 집중력이 떨어지는 걸수도..
- 일단 이것도 계속 꼽씹어야 겠다 ㅠ

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;  // 부모 배열 선언

    // 두 도시를 같은 그룹으로 묶는 함수 -> 서로 다른 그룹이면, 한 쪽의 루트를 다른 쪽에 붙인다
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }

    // 특정 도시의 root를 찾는 함수
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // 경로 압축 (find가 더 빠르게 동작하도록 하기 위함)
        }

        return parent[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 도시 수
        int M = Integer.parseInt(br.readLine());  // 여행 계획 수

        // 부모 배열 초기화 (초기엔 자기 자신을 부모로 세팅한다)
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        // 인접 행렬 입력
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                // 서로 연결되어 있다면? Union으로 같은 그룹으로 묶는다
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(i, j);
                }
            }
        }

        // 동혁이 여행 계획 입력 받기
        int[] plan = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        // 첫 도시의 루트를 기준으로 모두 같은지 확인
        String result = "YES";
        int root = find(plan[0]);

        for (int i = 1; i < M; i++) {
            if (find(plan[i]) != root) {
                result = "NO";
            }
        }

        System.out.println(result);
    }
}