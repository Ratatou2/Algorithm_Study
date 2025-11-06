/*
[백준]
22856, 트리 순회

[문제파악]
노드가 N개인 이진 트리가 있다. 트리를 중위 순회와 유사하게 순회하려고 한다.
이를 유사 중위 순회라고 하자.
순회의 시작은 트리의 루트이고 순회의 끝은 중위 순회할 때 마ㅠ막 노드이다.
이때 루트 노드는 항상 1번 노드이다.
유사 중위 순회는 루트 노드에서 시작하며, 다음과 같이 진행된다.
현재 위치한 노드의 왼쪽 자식 노드가 존재하고 아직 방문하지 않았다면, 왼쪽 자식 노드로 이동한다.그렇지 않고 현재 위치한 노드의 오른쪽 자식 노드가 존재하고 아직 방문하지 않았다면, 오른쪽 자식 노드로 이동한다.
그렇지 않고 현재 노드가 유사 중위 순회의 끝이라면, 유사 중위 순회를 종료한다.그렇지 않고 부모 노드가 존재한다면, 부모 노드로 이동한다.
유사 중위 순회를 종료할 때까지 1 ~ 4를 반복한다.

위 그림에 있는 트리에서 중위 순회를 한다면 4 -> 2 -> 5 -> 1 -> 6 -> 3 -> 7 순으로 순회를 한다.
따라서, 유사 중위 순회의 끝은 노드 7이 된다.

유사 중위 순회는 위 그림과 같이 루트인 노드 1에서 시작하여 노드 7에서 끝나고 1 -> 2 -> 4 -> 2 -> 5 -> 2 -> 1 -> 3 -> 6 -> 3 -> 7 이와 같은 순서로 순회를 진행한다.
유사 중위 순회를 진행하면서 총 10번 이동하였다.
여기서 이동이라는 것은 하나의 노드에서 다른 노드로 한번 움직이는 것을 의미한다.
예를 들면, 노드 1에서 노드 2로 가는 것을 한번 이동하였다고 한다.
유사 중위 순회를 하면서 이동한 횟수를 구하려고 한다.

[입력]
첫 번째 줄에 트리를 구성하는 노드의 개수 N이 주어진다.
두 번째 줄부터 N + 1 번째 줄까지 현재 노드 a, 현재 노드의 왼쪽 자식 노드 b, 현재 노드의 오른쪽 자식 노드 c가 공백으로 구분되어 주어진다.
만약 자식 노드의 번호가 -1인 경우 자식 노드가 없다는 것을 의미한다.

[출력]
유사 중위 순회를 하면서 이동한 총 횟수를 출력한다.

[구현방법]
- 재귀로 구현해야할듯
- if (왼쪽 노드가 있고 && 방문을 안했으면?) 왼쪽 노드로 내려간다
- if (오른쪽 노드가 있고 && 방문을 안했으면? 오른쪽 노드로 내려간다
- 이동 횟수만 구하는거라 이동한 순서를 계산할 필요는 없다
- 또한 내가 놓친게 몇개 있음
    1) 부모로 되돌아가는 로직이 필요하며 이것도 +1 카운트할 요소이다
    2) 중위 순회의 마지막 노드 (가장 오른쪽의 노드)에 도달하면 종료해야한다

[보완점]
- 예제는 맞췄는데 큰 틀은 틀렸음
    1) 일단 가장 오른쪽 리프 노드를 미리 찾아둬야 한다는 것
    2) 이동할 때마다 1증가 로직 제대로 넣고
    3) 왼쪽 탐색 종료 후 부모 노드로 돌아오는 이동도 카운트로 넣은게 없음

- 분기처리를 더 잘해줘야 했었네... 어렵..
*/

import java.io.*;
import java.util.*;

public class Main {
    static Node[] tree;
    static long count;
    static int N, lastRight;
    static boolean isFinished; // 종료 여부 플래그

    static class Node {
        int left, right;

        Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    // void 형태의 재귀 시뮬레이션 함수
    static void DFS(int curr) {
        // 끝난 상태거나, -1(종점)을 만난 경우면 더 진행할 것도 없음
        if (curr == -1 || isFinished) return;

        Node node = tree[curr];

        // 왼쪽 탐색
        if (node.left != -1) {
            count++;                    // 왼쪽 자식으로 이동
            DFS(node.left);
            if (isFinished) return;     // 탐색이 끝났으면 즉시 복귀
            count++;                    // 부모 노드로 복귀
        }

        // 현재 노드가 마지막 노드이면 종료
        // (왼쪽 - 본인 - 오른쪽 순서기 때문에 여기(왼쪽 DFS와 오른쪽 DFS 사이)에 위치해 있는 것)
        if (curr == lastRight) {
            isFinished = true;
            return;
        }

        // 오른쪽 탐색
        if (node.right != -1) {
            count++;                    // 오른쪽 자식으로 이동
            DFS(node.right);
            if (isFinished) return;     // 탐색이 끝났으면 즉시 복귀
            count++;                    // 부모 노드로 복귀
        }
    }

    // 가장 오른쪽 노드(= 마지막 노드)를 찾는 함수
    static int findLastRight(int root) {
        if (root == -1 || tree[root].right == -1) return root;
        return findLastRight(tree[root].right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        count = 0;
        tree = new Node[N + 1];

        // 배열을 사용하여 안정적으로 트리 구성
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            tree[parent] = new Node(left, right);
        }

        if (0 < N) {
            lastRight = findLastRight(1);  // 중위 순회의 마지막 노드 찾기
            DFS(1);  // 루트 노드(1)에서 시뮬레이션 시작
        }

        System.out.println(count);
    }
}
