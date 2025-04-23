/*
[백준]
1135, 뉴스 전하기

[문제파악]
- 민식이는 회사의 매니저이다.
- 그리고, 민식이는 회사의 중요한 뉴스를 모든 직원에게 빠르게 전달하려고 한다.
- 민식이의 회사는 트리 구조이다.
- 모든 직원은 정확하게 한 명의 직속 상사가 있다.
- 자기자신은 그들 자기 자신의 직접 또는 간접 상사가 아니고, 모든 직원은 민식이의 직접 또는 간접적인 부하이다.
- 민식이는 일단 자기 자신의 직속 부하에게 한 번에 한 사람씩 전화를 한다.
- 뉴스를 들은 후에, 각 부하는 그의 직속 부하에게 한 번에 한 사람씩 전화를 한다.
- 이 것은 모든 직원이 뉴스를 들을 때 까지 계속된다.
- 모든 사람은 자신의 직속 부하에게만 전화를 걸 수 있고, 전화는 정확하게 1분 걸린다.
- 이때 모든 직원이 소식을 듣는데 걸리는 시간의 최솟값을 구하는 프로그램을 작성하시오.
- 오민식의 사원 번호는 0이고, 다른 사원의 번호는 1부터 시작한다.

[입력]
- 첫째 줄에 직원의 수 N이 주어진다.
- 둘째 줄에는 0번 직원부터 그들의 상사의 번호가 주어진다.
- 0번 직원 (오민식)은 상사가 없기 때문에 -1이고, 나머지 직원 i의 상사 번호는 i보다 작거나 같은 음이 아닌 정수이다.
- N은 50보다 작거나 같은 자연수이다.

[출력]
- 첫째 줄에 모든 소식을 전하는데 걸리는 시간의 최솟값을 출력한다.

[구현방법]
- 트리에서의 DP 또 도전..!
- 흠... 이것도 BFS로 할 수 있을 것 같지만 DP니까 누적합해야겠죠?
- Top-Down인 것 같은데..

- 와 이거 놓친게 있는데, 한번에 부하 한명에게만 전화할 수 있다
- 즉, 하위에 부하가 많은 애부터 전화해야 걔가 최대한 빨리 전화하고, 전체적으로 더 빨리 끝낼 수 있다
- 다시 말해, 부하가 많은 애들을 정렬해줄 무언가가 필요함
- 아니 엊그제부터 자꾸 복습하려고 비슷한 문제 고르면 같은 카테고리에서 전혀 다른 유형만 잔뜩이네;;
- 이건 포스팅도 해야겠다

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer>[] tree;
    static int[] DP;

    static void DFS(int node) {
        List<Integer> times = new ArrayList<>();  // 전화 시간 기록하기

        for (int employee : tree[node]) {
            DFS(employee);  // 리프부터 계산
            times.add(DP[employee]);
        }

        // 가장 오래 걸리는 사원한테 먼저 전화해야 전체적인 시간이 줄어듦 (그러기 위한 역정렬)
        times.sort(Collections.reverseOrder());

        int maxTime = 0;

        // 전화 순서대로 시간 부여
        for (int i = 0; i < times.size(); i++) {
            int timeToFinish = times.get(i) + (i + 1);  // 전화한 시점 + 자식이 다 돌리는데 걸리는 시간
            maxTime = Math.max(maxTime, timeToFinish);  // 전체 중 가장 오래 걸리는 시간 저장
        }

        DP[node] = maxTime;  // 이 노드가 전체 자식에게 뉴스를 다 전달하는데 걸리는 시간
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[] whoIsTheBoss = new int[N];
        tree = new ArrayList[N];
        DP = new int[N];

        // 트리 초기화
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        // 상사 입력 받고 그 내용으로 tree 구성하기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            whoIsTheBoss[i] = Integer.parseInt(st.nextToken());

            // 상사 없는 직원(= 오민식, 시작점)이 아닌 이상, 트리 구조에 등록한다
            if (whoIsTheBoss[i] != -1) {
                tree[whoIsTheBoss[i]].add(i);
            }
        }

        DFS(0);  // 루트에서부터 탐색 시작
        System.out.println(DP[0]);  // 루트가 전체에 전달 완료되는 시간
    }
}