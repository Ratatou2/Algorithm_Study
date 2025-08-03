

/*
[백준]
2250, 트리의 높이와 너비

[문제파악]
이진트리를 다음의 규칙에 따라 행과 열에 번호가 붙어있는 격자 모양의 틀 속에 그리려고 한다.
이때 다음의 규칙에 따라 그리려고 한다.
    이진트리에서 같은 레벨(level)에 있는 노드는 같은 행에 위치한다.
    한 열에는 한 노드만 존재한다.
    임의의 노드의 왼쪽 부트리(left subtree)에 있는 노드들은 해당 노드보다 왼쪽의 열에 위치하고, 오른쪽 부트리(right subtree)에 있는 노드들은 해당 노드보다 오른쪽의 열에 위치한다.
    노드가 배치된 가장 왼쪽 열과 오른쪽 열 사이엔 아무 노드도 없이 비어있는 열은 없다.
    이와 같은 규칙에 따라 이진트리를 그릴 때 각 레벨의 너비는 그 레벨에 할당된 노드 중 가장 오른쪽에 위치한 노드의 열 번호에서 가장 왼쪽에 위치한 노드의 열 번호를 뺀 값 더하기 1로 정의한다.
    트리의 레벨은 가장 위쪽에 있는 루트 노드가 1이고 아래로 1씩 증가한다.
아래 그림은 어떤 이진트리를 위의 규칙에 따라 그려 본 것이다.
첫 번째 레벨의 너비는 1, 두 번째 레벨의 너비는 13, 3번째, 4번째 레벨의 너비는 각각 18이고, 5번째 레벨의 너비는 13이며, 그리고 6번째 레벨의 너비는 12이다.

우리는 주어진 이진트리를 위의 규칙에 따라 그릴 때에 너비가 가장 넓은 레벨과 그 레벨의 너비를 계산하려고 한다.
위의 그림의 예에서 너비가 가장 넓은 레벨은 3번째와 4번째로 그 너비는 18이다.
너비가 가장 넓은 레벨이 두 개 이상 있을 때는 번호가 작은 레벨을 답으로 한다.
그러므로 이 예에 대한 답은 레벨은 3이고, 너비는 18이다.
임의의 이진트리가 입력으로 주어질 때 너비가 가장 넓은 레벨과 그 레벨의 너비를 출력하는 프로그램을 작성하시오

[입력]
첫째 줄에 노드의 개수를 나타내는 정수 N(1 ≤ N ≤ 10,000)이 주어진다.
다음 N개의 줄에는 각 줄마다 노드 번호와 해당 노드의 왼쪽 자식 노드와 오른쪽 자식 노드의 번호가 순서대로 주어진다.
노드들의 번호는 1부터 N까지이며, 자식이 없는 경우에는 자식 노드의 번호에 -1이 주어진다.

[출력]
첫째 줄에 너비가 가장 넓은 레벨과 그 레벨의 너비를 순서대로 출력한다.
너비가 가장 넓은 레벨이 두 개 이상 있을 때에는 번호가 작은 레벨을 출력한다.

[구현방법]
- 맵 구조를 짜놓고 노드를 배치하는 방식으로는 머리 터져서 절대 구현 못한다 (더군다나 메모리도 128로 작은 편)
- 그러면 트리의 각 노드에서 재귀적으로 타고 내려가며 해당 노드에 노드가 총 몇개인지 파악한다
- 어느 한 노드의 너비는 해당 노드의 자식 수와 관련이 있다
- 정확히는 같은 레벨의 A와 B 노드의 너비를 구해야한다면, 두 노드의 거리는 다음과 같다
    = A 오른쪽 노드 너비 + B 왼쪽의 노드 너비 + 두 노드 사이 노드들의 최대 거리
- 다만 이 때 놓쳐서는 안될 것이 있다
- 바로 노드들은 한 열씩만 차지한다는 것
- 즉, 부모 노드의 위치도 신경써야한다는 점이다
- 대체 누가 이런 악마같은 문제를 만든겨... ㄷ

[보완점]
- 루트 노드를 찾는 방법
    - 다른 노드들의 하위 노드가 되지 않는 노드가 한개 있을 것이다
    - boolean[] 배열 만들어서 마지막까지 등장하지 않은, 노드가 있다면 그 노드가 루트 노드이다

- 결국 제 힘으로 온전히 풀진 못했는데 살펴보니 로직 순서는 초기 구상안과 크게 다르지 않다
- 입력 받고, 트리 구조 탐색하고, 탐색한 결과들로 너비를 재는것
- 근데 내가 생각했던 것과 달랐던 것은 그냥 중위순회로 탐색해가며 너비를 계속 갱신시켰다는 점..

- 그러니까 기본적으로는 중위 순열이니까  left -> right 순으로 재귀를 타고 내려간다
- 그리고 각 위치에서, 현재의 레벨에서 가장 먼 것과 가까운 것을 갱신한다
- 그 뒤에 열 번호를 증가해서 각 노드들이 적절한 열 번호를 하나씩 가질 수 있도록 하는 것이다
- 어쩌면 내가 중위 순열을 똑바로, 100% 이해하고 있었다면 절반 정도는 맞췄을지도 모르겠다...

- 아, 중위순회를 쓴 이유는 왼쪽 노드부터 열 Numbereing이 필요하기 때문이다
- 그러기 위해서는 필연적으로 왼쪽 - 중앙 - 오른쪽 순서로 이어지는 중위순회가 필수
- 다른 순회는?
    - 전위 순회 (현재 → 왼쪽 → 오른쪽): 현재 노드가 너무 먼저 열 번호를 차지함
	- 후위 순회 (왼쪽 → 오른쪽 → 현재): 현재 노드가 자식보다 뒤에 밀려버림
- 핵심은 왼쪽이 가장 작은 숫자(열 번호)를 가지고, 오른쪽이 가장 큰 숫자(열 번호)를 가지게 될 것이란 것이다
- 가운데 지점의 부모 노드들도 왼쪽 -> 부모 -> 오른쪽 순이니까 충분히 적당한 번호를 받을 수 있음
- 결국 이 문제는 map의 칸칸 속에 들어가 있는 노드 번호가 표기된 그림이 우리를 헷갈리게 할뿐 왼쪽부터 번호를 할당한 것과 다름이 없다
    - 이 말인 즉, 결국 중위순회로 마주할 수 있는, 순서대로 번호를 할당하며 각 level 별로 따져보면 답이 나오는... 의외로 간단한(?!) 문제 였다 (응 근데 못풀었죠 ㅠ)
- '중위 순회에 열 번호를 부여하면 위치가 해결된다' 를 알고 있었다면 쉬웠을 문제..
*/

import java.io.*;
import java.util.*;

public class Main {
    static Tree[] tree;
    static int[] minX;         // 각 레벨별 최소 열 번호
    static int[] maxX;         // 각 레벨별 최대 열 번호
    static int col = 1;      // 열 번호 (중위 순회 순서대로 증가)
    
    static class Tree {
        int left, right;

        Tree (int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "왼쪽, 오른쪽 = " + left + ", " + right;
        }
    }
    
    // 중위 순회: 왼쪽 → 현재 → 오른쪽
    static void inorder(int node, int level) {
        // 노드가 없다면 더 볼 것 없다
        if (node == -1) return;

        inorder(tree[node].left, level + 1);    // 왼쪽 자식 재귀 탐색

        minX[level] = Math.min(minX[level], col);  // 현재 레벨 최소 열 갱신
        maxX[level] = Math.max(maxX[level], col);  // 현재 레벨 최대 열 갱신
        col++; // 다음 열 번호로 증가

        inorder(tree[node].right, level + 1);   // 오른쪽 자식 재귀 탐색
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());    // 노드 수 입력
        tree = new Tree[N + 1];
        minX = new int[N + 1];
        maxX = new int[N + 1];
        Arrays.fill(minX, Integer.MAX_VALUE);       // 최소값 초기화

        boolean[] isChild = new boolean[N + 1];      // 루트 찾기용 배열

        // 트리 구조 입력 받고 기록
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            tree[parent] = new Tree(left, right);

            if (left != -1) isChild[left] = true;
            if (right != -1) isChild[right] = true;
        }

        // 루트 노드 찾기 (자식이 아닌 노드)
        int root = 0;
        for (int i = 1; i <= N; i++) {
            // 노드들 중에서 한번이라도 다른 노드의 하위 노드가 아니었던 노드는 루트 노드 뿐이다
            if (!isChild[i]) {
                root = i;
                break;
            }
        }

        // 중위 순회로 열 번호 배정 + 레벨별 열 기록
        inorder(root, 1);

        // 결과 계산 (level 1 값(=루트)으로 갱신해두기)
        int maxLevel = 1;
        int maxWidth = maxX[1] - minX[1] + 1;

        // 낮은 레벨부터 순차적으로 진행하면, 현 최대 너비보다 더 큰 경우가 아니면 갱신을 하지 않아도 된다
        // (maxWidth < width) 이 말인 즉, 문제 조건인, 너비가 같은 경우 더 작은 레벨을 출력해야하는 문제 조건을 자연스럽게 만족할 수 있다
        for (int i = 2; i <= N; i++) {
            if (minX[i] == Integer.MAX_VALUE) continue; // 해당 레벨 없음

            int width = maxX[i] - minX[i] + 1;
            if (maxWidth < width) {
                maxWidth = width;
                maxLevel = i;
            }
        }

        System.out.println(maxLevel + " " + maxWidth);
    }
}