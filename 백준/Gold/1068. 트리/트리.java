/*
[백준]
1068, 트리

[문제파악]
- 트리에서 리프 노드란, 자식의 개수가 0인 노드를 말한다.
- 트리가 주어졌을 때, 노드 하나를 지울 것이다.
- 그 때, 남은 트리에서 리프 노드의 개수를 구하는 프로그램을 작성하시오.
- 노드를 지우면 그 노드와 노드의 모든 자손이 트리에서 제거된다.
- 예를 들어, 다음과 같은 트리가 있다고 하자.
- 현재 리프 노드의 개수는 3개이다. (초록색 색칠된 노드)
- 이때, 1번을 지우면, 다음과 같이 변한다.
- 검정색으로 색칠된 노드가 트리에서 제거된 노드이다.
- 이제 리프 노드의 개수는 1개이다.

[입력]
- 첫째 줄에 트리의 노드의 개수 N이 주어진다.
- N은 50보다 작거나 같은 자연수이다.
- 둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다.
- 만약 부모가 없다면 (루트) -1이 주어진다.
- 셋째 줄에는 지울 노드의 번호가 주어진다.

[출력]
- 첫째 줄에 입력으로 주어진 트리에서 입력으로 주어진 노드를 지웠을 때, 리프 노드의 개수를 출력한다.

[구현방법]2
-1 0
1
- 리프 노드라함은 자식 노드가 없는 노드이다
- 이 말인 즉, DFS로 끝까지 탐색했을 때 더 이상 갈 곳이 없는 노드임
- 더군다나 부모를 알려주는 아주 친절한 문제이므로, ArrayList로 추가하면 양방향 중복도 없이, 트리 구조 그대로의 리스트를 구성할 수 있다
- 지운 노드는 임의값인 -100으로 세팅해서 -100을 만난 순간 해당 지점부터의 모든 가능성(=경우의 수)은 탐색하지 않도록 세팅하면 된다

[보완점]
- 내가 처리 못한 반례는 두가지
    1) 루트 노드를 지우는 경우, 0을 출력해줘야 한다 (심지어 루트 노드가 삭제되더라도 하위 트리 구조가 생길 순 있다)
    2) 하위 노드를 삭제해서 내가 리프 노드가 되는 경우의 수도 있음
- 애초에 내가 넘 어렵게 생각함 + 넘 쉽게 풀려고 함의 조합 이슈였던듯...
- 트리 구조니까 양방향으로 다 등록해줘야 했다
- 심지어 어차피 그런식으로 진행할 것이라면 방문처리도 할 것이라서 누가 부모인지 기록할 필요가 없었음... ㅠ
- 위에서 아래로 내려가니까 isVisited에 등록되어있다면 더 탐색할 필요없다
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int eraseNode, totalCount;
    static ArrayList<ArrayList<Integer>> tree;
    static boolean[] isVisited;

    static void DFS(int node) {
        isVisited[node] = true;  // 현재 노드 방문처리
        int count = 0;  // 자식 노드 갯수 카운트

        // 자식 노드 탐색
        for (int next : tree.get(node)) {
            // 방문한 적 없고, 지운 노드가 아니라면 자식노드부터 DFS 탐색
            if (!isVisited[next] && next != eraseNode) {
                count++;
                DFS(next);
            }
        }

        if (count == 0) totalCount++;  // 자식 노드가 없으면 리프 노드란 의미
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        tree = new ArrayList<>();
        totalCount = 0;
        isVisited = new boolean[N];

        // 트리 초기화
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        // 부모 입력 받기 + 트리에 등록
        int root = -1;
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if (temp == -1) root = i;  // -1은 루트 노드란 의미
            else {
                // 양방향 연결
                tree.get(temp).add(i);
                tree.get(i).add(temp);
            }
        }

        eraseNode = Integer.parseInt(br.readLine());

        // 지운 노드가 루트 노드이면 0을 출력하고 끝낸다
        if (eraseNode == root) {
            System.out.println(0);
            return;
        }

        DFS(root);// 루트노드에서부터 탐색

        System.out.println(totalCount);
    }
}
