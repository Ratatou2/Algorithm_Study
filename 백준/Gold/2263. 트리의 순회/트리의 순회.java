/*
[백준]
2263, 트리의 순회

[문제파악]
- n개의 정점을 갖는 이진 트리의 정점에 1부터 n까지의 번호가 중복 없이 매겨져 있다.
- 이와 같은 이진 트리의 인오더와 포스트오더가 주어졌을 때, 프리오더를 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 n(1 ≤ n ≤ 100,000)이 주어진다.
- 다음 줄에는 인오더를 나타내는 n개의 자연수가 주어지고, 그 다음 줄에는 같은 식으로 포스트오더가 주어진다.

[출력]
- 첫째 줄에 프리오더를 출력한다.

[구현방법]
- 트리구조... 트리의 읽어오는 순서를 2가지 알려주고 그것으로 트리 구조를 계산, preorder를 출력하도록 세팅해두었음
- 근데 나는 순수하게 트리를 짜본 적은 아직 없음.. 이진 트리니까 tree를 위한 class를 만들고 left, right 따로 지정해줘서 순서를 유도하는 것은 이해했음
- 트리 구조는 아래 순서로 탐색
    preorder    (전위 순회)     root - left - right
    inorder     (중위 순회)     left - root - right
    postorder   (후위 순회)     left - right - root

- 이런 식이면 post로 후위, 즉 root 노드를 알 수 있고, in으로 왼쪽, 오른쪽 트리를 알 수 있다
- 이진 트리 공부 좀 해봤는데 이건 그냥 외워도 될 듯하다. 외우는게 이해를 더 도울 것 같은 형식임.
- 어렵다 어려워 무슨 깜지마냥 코드 전부에 주석처리해가며 한 줄씩 씹어먹었음.. ㅠ
- 그래도 기본에 충실한 문제라 여러번에 걸쳐 천천히 소화시켜야할 문제인 것 같음

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] inorder, inIdx, postorder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());  // N 최대 (100,000)

        // 중위 순회 입력 받기
        inorder = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N + 1; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        // 후위 순회 입력 받기
        postorder = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N + 1; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        // inorder 인덱스
        // inIdx[x]는 중위 순회에서 값 x가 위치한 인덱스를 의미함
        inIdx = new int[N + 1];
        for(int i = 1; i < N + 1; i++) {
            inIdx[inorder[i]] = i;
        }

        getPreorder(1, N,1, N);

        System.out.println(sb.toString());
    }

    // 전위 순회로 트리를 복원하고 결과를 저장함
    // inorderStartIdx, inEndIdx : 중위 순회의 시작 & 끝 인덱스.
    // preStartIdx, preEndIdx : 후위 순회의 시작 & 끝 인덱스.
    static void getPreorder(int inStartIdx, int inEndIdx, int preStartIdx, int preEndIdx) {
        // 부분 트리가 없을 때, return
        if(inEndIdx < inStartIdx || preEndIdx < preStartIdx) return;
        int root = postorder[preEndIdx];  // 후위순회에서 가장 마지막은 root 노드 이다 (공식)
        int rootIdx = inIdx[root];  // 그러면 root 노드가 무엇인지 아니까 중위 순회에서 root 노드의 index를 알 수 있음
        // root index를 찾았다는 것의 의미는 left, right 트리를 구분지을 수 있다는 의미이다
        // 이 말인 즉, 새로운 기준을 잡고 타고 내려가면서 트리의 구조를 확실히 알 수 있다는 의미이기도 함

        sb.append(root).append(" ");

        int leftTreeSize = rootIdx - inStartIdx;  // left 트리 길이

        // 그러므로 중위 순회의 왼쪽 트리는 초기에 받은 시작 지점부터, root로 찾은 index - 1까지다
        // 이 말인 즉, 후위 순회에서는 시작 지점부터, 왼쪽 트리의 크기에서 -1을 빼준 값이 왼쪽 트리가 되는 것이다
        // 이 것은 후위 순회의 정의와도 긴밀한 관련이 있는데 후위 순회는 왼쪽 서브트리 -> 오른쪽 서브트리 -> 루트로 방문하기 때문
        // 결과적으로 이것은 이해가 아닌 암기에 가까운 수학적(?!) 정의에 가깝다 (사실상 두가지 순회의 특성에 기반한 논리적 결과 -> 외우세용 ㅠ)
        getPreorder(inStartIdx, rootIdx - 1, preStartIdx, preStartIdx + leftTreeSize - 1);  // left 트리

        // 이제 같은 맥락으로 후위 순회는 이해하기 쉬울 것이다
        // 중위 순회 기준으로는 루트 기준 오른쪽 index부터 끝까지가 오른쪽 서브트리 범위이며
        // 후위 순회 기준으로는 왼쪽 트리를 배제한 지점(왼쪽 트리 크기를 더한 index)부터 root 노드인 제일 마지막을 뺀 지점까지이다
        getPreorder(rootIdx + 1, inEndIdx, preStartIdx + leftTreeSize, preEndIdx - 1);  // right 트리
    }
}