/*
[백준]
15681, 트리와 쿼리

[문제파악]
- 간선에 가중치와 방향성이 없는 임의의 루트 있는 트리가 주어졌을 때, 아래의 쿼리에 답해보도록 하자.
- 정점 U를 루트로 하는 서브트리에 속한 정점의 수를 출력한다.
- 만약 이 문제를 해결하는 데에 어려움이 있다면, 하단의 힌트에 첨부한 문서를 참고하자.

[입력]
- 트리의 정점의 수 N과 루트의 번호 R, 쿼리의 수 Q가 주어진다. (2 ≤ N ≤ 105, 1 ≤ R ≤ N, 1 ≤ Q ≤ 105)
- 이어 N-1줄에 걸쳐, U V의 형태로 트리에 속한 간선의 정보가 주어진다. (1 ≤ U, V ≤ N, U ≠ V)
- 이는 U와 V를 양 끝점으로 하는 간선이 트리에 속함을 의미한다.
- 이어 Q줄에 걸쳐, 문제에 설명한 U가 하나씩 주어진다. (1 ≤ U ≤ N)
- 입력으로 주어지는 트리는 항상 올바른 트리임이 보장된다.

[출력]
- Q줄에 걸쳐 각 쿼리의 답을 정수 하나로 출력한다.

[구현방법]
- 일단 input을 전부 ArrayList로 받고, TreeNode 클래스를 만들어서 Prarent를 기록하는 것을 만들어서 풀면 될 것 같다

[보완점]
- 우선 내가 접근한 방식은
    1) 인풋을 받아 연결상태를 저장해둔다
    2) 해당 내용을 기반으로 Tree 구조를 만든다
    3) 쿼리를 받으면 해당 노드 기준으로 서브 트리를 DFS로 탐색
- 위와 같은 방식을 생각한 이유는 일단 DP를 적용하지 않고 풀어보고 싶었음
- 막혀서 헤매던 곳은 구현방식이 조금 수정되면 좋을 지점
- 바로 부모를 등록하는 것은 그냥 int[] 배열로 해도 된다는 것
- ArrayList 조차 그저 연결된, input만 저장해두고 있으면 된다는 것
- 나머지는 재귀를 통해서 R 기준으로 타고 내려가 int[] 배열에 부모를 등록하면 됨
- 그리고 DP 없이 하는 것이니까 node를 하나 받으면 해당 node 기준으로 계속 타고 내려가면 되는 것이고
- 자식이면서 방문한 적 없다면 계속 타고 내려가 count 하면 서브 트리의 노드 갯수를 구할 수 있다

- 근데 이렇게 풀면? 메모리 초과가 난다
- DP는 필수적용이었나보다
- 확인해보면 아래와 같기 때문에 DP 코드에는 부모를 기록하지도 않으며 더 간결하고 효율적인 것이
    1) 어차피 R 기준으로 한번에 밑으로 쭉 탐색하며 DP에 저장해두기 때문에 부모 노드 기록해둘 필요 없음 (한번만 기록해두면 문제 조건을 만족하기 때문에 부모노드를 기록해둘 필요 없음)
    2) 더군다나 방문처리 되어있으니 부모노드는 갈 일이 없음
    3) 물론 특정 노드에서 갑자기 탐색하라고 하면 부모, 자식 관계를 기록해두지 않았기 때문에 탐색을 하나씩 해야함
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[] subtreeSize;     // 부모 노드 저장
    static boolean[] isVisited;   // 방문 체크
    static ArrayList<ArrayList<Integer>> tree;

    static int setSubtreeSize(int node) {
        isVisited[node] = true;  // 방문처리
        int count = 1;  // 자기 자신도 count

        // 자식 노드를 탐색하며 서브트리 크기를 계산
        for (int next : tree.get(node)) {
            if (!isVisited[next]) {
                count += setSubtreeSize(next);  // 자식의 서브트리 크기를 더함
            }
        }
        return subtreeSize[node] = count;       // DP 기록에 저장
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());  // 노드 갯수
        int R = Integer.parseInt(st.nextToken());  // 루트 번호
        int Q = Integer.parseInt(st.nextToken());  // 쿼리의 수

        // 트리 초기화
        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        // 간선 연결하기
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 양방햔 간선 입력
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        // DP와 방문 배열 초기화
        subtreeSize = new int[N + 1];
        isVisited = new boolean[N + 1];

        // 루트 노드에서 서브트리 크기 미리 계산 (DFS + DP)
        setSubtreeSize(R);

        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine());
            sb.append(subtreeSize[query]).append("\n");
        }

        System.out.println(sb);
    }
}