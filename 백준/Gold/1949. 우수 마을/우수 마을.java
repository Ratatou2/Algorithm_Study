/*
[백준]
1949, 우수 마을

[문제파악]
- N개의 마을로 이루어진 나라가 있다.
- 편의상 마을에는 1부터 N까지 번호가 붙어 있다고 하자.
- 이 나라는 트리(Tree) 구조로 이루어져 있다.
- 즉 마을과 마을 사이를 직접 잇는 N-1개의 길이 있으며, 각 길은 방향성이 없어서 A번 마을에서 B번 마을로 갈 수 있다면 B번 마을에서 A번 마을로 갈 수 있다.
- 또, 모든 마을은 연결되어 있다.
- 두 마을 사이에 직접 잇는 길이 있을 때, 두 마을이 인접해 있다고 한다.

- 이 나라의 주민들에게 성취감을 높여 주기 위해, 다음 세 가지 조건을 만족하면서 N개의 마을 중 몇 개의 마을을 '우수 마을'로 선정하려고 한다.
- '우수 마을'로 선정된 마을 주민 수의 총 합을 최대로 해야 한다.
- 마을 사이의 충돌을 방지하기 위해서, 만일 두 마을이 인접해 있으면 두 마을을 모두 '우수 마을'로 선정할 수는 없다.
- 즉 '우수 마을'끼리는 서로 인접해 있을 수 없다.
- 선정되지 못한 마을에 경각심을 불러일으키기 위해서, '우수 마을'로 선정되지 못한 마을은 적어도 하나의 '우수 마을'과는 인접해 있어야 한다.
- 각 마을 주민 수와 마을 사이의 길에 대한 정보가 주어졌을 때, 주어진 조건을 만족하도록 '우수 마을'을 선정하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 정수 N이 주어진다. (1 ≤ N ≤ 10,000)
- 둘째 줄에는 마을 주민 수를 나타내는 N개의 자연수가 빈칸을 사이에 두고 주어진다.
- 1번 마을부터 N번 마을까지 순서대로 주어지며, 주민 수는 10,000 이하이다.
- 셋째 줄부터 N-1개 줄에 걸쳐서 인접한 두 마을의 번호가 빈칸을 사이에 두고 주어진다.

[출력]
- 첫째 줄에 '우수 마을'의 주민 수의 총 합을 출력한다.

[구현방법]
- 오늘 컨셉은 눈 마주친 문제 풀기
- 근데 고르고 보니..? 뭔가 쎄해... 그냥 트리는 아닌거 같아
- 가만보니 우수마을 선정하고 인접한 애들은 우수마을이 안되고... 그 와중에 인구수는 제일 많아야해?
- DP...네??? 전에 푼 2533, 사회망 서비스 문제랑 한끗차이 ㅠ DP 싫어요!

- 자식부터 bottom-up으로 접근
- 내 부모가 우수 마을 이거나 아니거나 -> 2가지 DP 저장
- 계속 타고 올라가면 된다 (재귀)
- 여전히 한번에 혼자 다 짜진 못했음 ㅠ 그래도 전보단 낫다

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] cities;
    static boolean[] isVisited;
    static ArrayList<Integer>[] tree;
    static int[][] DP;

    static void DFS(int node) {
        // 일단 방문 처리
        isVisited[node] = true;
        DP[node][0] = 0;  // 자기 자신이 우수 마을이 아니면 더해줄 것이 없다
        DP[node][1] = cities[node];  // 자기 자신이 우수 마을이면, 자기 자신의 인구수를 더하면 된다

        for (int curr : tree[node]) {
            if (isVisited[curr]) continue;  // 방문한 곳이면 더 확인할 필요 없다
            DFS(curr);
            
            DP[node][0] += Math.max(DP[curr][0], DP[curr][1]);  // 내가 간과한 부분, 내가 우수마을이 아니더라도 자식이 우수마을일 필요는 없다 (자식이 또 다른 노드와 연결되어있고 그 노드가 우수 마을일 경우, 현재의 부모, 자식은 우수 마을일 필요가 없음)
            DP[node][1] += DP[curr][0];  // 내가 우수 마을이면 자식은 우수마을이면 안된다
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        DP = new int[N + 1][2];
        tree = new ArrayList[N + 1];
        cities = new int[N + 1];
        isVisited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            cities[i] = Integer.parseInt(st.nextToken());  // 도시 인구 수 입력
            tree[i] = new ArrayList<>();  // 트리 초기화
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 양방향 입력
            tree[a].add(b);
            tree[b].add(a);
        }

        DFS(1);  // 루트 노드는 아니지만 결국 다 연결되어있는 tree라서 1부터 시작해도 괜찮다

        System.out.println(Math.max(DP[1][0], DP[1][1]));

    }
}