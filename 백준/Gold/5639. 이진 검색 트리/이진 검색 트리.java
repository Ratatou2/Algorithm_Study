

/*
[백준]
5639, 이진 검색 트리

[문제파악]
이진 검색 트리는 다음과 같은 세 가지 조건을 만족하는 이진 트리이다.

노드의 왼쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 작다.
노드의 오른쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 크다.
왼쪽, 오른쪽 서브트리도 이진 검색 트리이다.

전위 순회 (루트-왼쪽-오른쪽)은 루트를 방문하고, 왼쪽 서브트리, 오른쪽 서브 트리를 순서대로 방문하면서 노드의 키를 출력한다.
후위 순회 (왼쪽-오른쪽-루트)는 왼쪽 서브트리, 오른쪽 서브트리, 루트 노드 순서대로 키를 출력한다.
예를 들어, 위의 이진 검색 트리의 전위 순회 결과는 50 30 24 5 28 45 98 52 60 이고, 후위 순회 결과는 5 28 24 45 30 60 52 98 50 이다.
이진 검색 트리를 전위 순회한 결과가 주어졌을 때, 이 트리를 후위 순회한 결과를 구하는 프로그램을 작성하시오.

[입력]
트리를 전위 순회한 결과가 주어진다.
노드에 들어있는 키의 값은 106보다 작은 양의 정수이다.
모든 값은 한 줄에 하나씩 주어지며, 노드의 수는 10,000개 이하이다.
같은 키를 가지는 노드는 없다.

[출력]
입력으로 주어진 이진 검색 트리를 후위 순회한 결과를 한 줄에 하나씩 출력한다.

[구현방법]
- 일단 전위 순회면, 루트부터 등록하고 시작
- 부모가 있다면 숫자를 비교해서 왼쪽, 오른쪽을 정한다
- 예를들어 숫자가 작다면 왼쪽 자식으로 이동
- 왼쪽 자식이 없으면 그대로 왼쪽 자식으로 지정
- 왼쪽 자식이 있다면 해당 자식을 부모 기준으로 잡고, 부모보다 크면 오른쪽, 작으면 왼쪽
- 위 과정을 모든 input을 다 받아서 적용할 때까지 반복한다

- 단순히 insert()로 트리 구성 시 O(N²) 위험이 있다고...
- 입력 순서 자체가 전위 순회이므로, 배열 인덱스로 구간을 나누어, 재귀적으로 왼쪽/오른쪽 서브트리를 처리하면 더 효율적이란다 (트리의 특징인듯)
- 실제 트리 객체(Node) 생성 없이도 후위 순회 출력 가능
- 즉, 트리 구조체 안만들어도 된다!

[보완점]
- 진짜 트리 문제는 풀 때마다 수학이랑 다를바가 없다는(똑같긴 할것이다) 생각이든다....
- 아무튼 규칙을 정확히 알고 있어야 풀 수 있다 (전위 배열에서는 root를 기준으로 루트보다 큰 숫자가 '처음으로 등장'하는 지점이 오른쪽 서브트리의 시작이라는 것
- 역시 또 당분간은 트리를 집중해서 파야할 것 같다 너무 약해;;
*/

import java.io.*;
import java.util.*;

public class Main {
    static int[] preorder;
    static StringBuilder sb;

    static void postorder(int start, int end) {
        // 비어있는 구간이면 return
        if (end < start) return;

        int root = preorder[start];
        int index = start + 1;  // 오른쪽 서브트리 시작 지점 찾기 위한 포인터 (왼쪽 서브트리는 '루트 직후에' 나오는 연속된 작은값들이라 찾을 필요 X)

        // 오른쪽 서브트리의 시작점 찾기
        while (index <= end && preorder[index] < root) index++;

        // <후위 순회 규칙: 왼쪽 - 오른쪽 - 루트>
        // 왼쪽 서브트리
        postorder(start + 1, index - 1);
        // 오른쪽 서브트리
        postorder(index, end);
        // 루트 출력
        sb.append(root).append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = 0;  // 실제로 읽은 갯수
        sb = new StringBuilder();
        preorder = new int[10000];

        String line;
        // 읽은 문자열이 공백이 아니고, 비어있지 않으면 계속 추가한다
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            preorder[N++] = Integer.parseInt((line));
        }

        postorder(0, N - 1);
        System.out.println(sb);
    }
}
