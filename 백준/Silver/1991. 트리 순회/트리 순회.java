/*
[백준]
1991, 트리 순회

[문제파악]
- 이진 트리를 입력받아 전위 순회(preorder traversal), 중위 순회(inorder traversal), 후위 순회(postorder traversal)한 결과를 출력하는 프로그램을 작성하시오.

- 전위 순회한 결과 : ABDCEFG // (루트) (왼쪽 자식) (오른쪽 자식)
- 중위 순회한 결과 : DBAECFG // (왼쪽 자식) (루트) (오른쪽 자식)
- 후위 순회한 결과 : DBEGFCA // (왼쪽 자식) (오른쪽 자식) (루트)

[입력]
- 첫째 줄에는 이진 트리의 노드의 개수 N(1 ≤ N ≤ 26)이 주어진다. 
- 둘째 줄부터 N개의 줄에 걸쳐 각 노드와 그의 왼쪽 자식 노드, 오른쪽 자식 노드가 주어진다. 
	- 노드의 이름은 A부터 차례대로 알파벳 대문자로 매겨지며, 항상 A가 루트 노드가 된다. 
	- 자식 노드가 없는 경우에는 .으로 표현한다.


[출력]
- 첫째 줄에 전위 순회, 둘째 줄에 중위 순회, 셋째 줄에 후위 순회한 결과를 출력한다. 
- 각 줄에 N개의 알파벳을 공백 없이 출력하면 된다.


[구현방법]
- 오늘부턴 트리 공부하려고 하는데 이거 너무 어렵다이..
- 트리 공부 열시미 해야겠따..

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        char value;
        Node left;
        Node right;

        Node (char value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    static void insertNode(Node node, char root, char left, char right) {
        // 현재 노드가 루트 노드인 경우엔 하위 노드 생성
        if (node.value == root) {
            // 자식 노드가 . 이면 null로 세팅, 아니면 새로운 노드를 마들어준다
            node.left = (left == '.' ? null : new Node(left, null, null));
            node.right = (right == '.' ? null : new Node(right, null, null));
        } else {  // left, right가 존재하는 경우는 기존에 생성되어 있는 노드가 있단 소리
            // 그 노드를 root 삼아서 하위에 노드를 넣어준다
            if (node.left != null) insertNode(node.left, root, left, right);
            if (node.right != null) insertNode(node.right, root, left, right);
        }
    }

    // 전위 순회 (root 먼저 탐색)
    static void preorder(Node node) {
        if (node == null) return;

        // root 먼저 추가
        sb.append(node.value);
        preorder(node.left);  // 그 다음엔 왼쪽부터
        preorder(node.right);
    }

    // 중위 순회 (왼쪽부터 방문 후 root 탐색)
    static void inorder(Node node) {
        if (node == null) return;

        // 왼쪽으로 최대한 내려감
        inorder(node.left);
        sb.append(node.value);
        inorder(node.right);
    }

    // 후위 순회 (하위부터 방문 후 root 방문)
    static void postorder(Node node) {
        if (node == null) return;

        // 하위 모든 노드 방문 후에 추가
        postorder(node.left);
        postorder(node.right);
        sb.append(node.value);
    }

    static Node head = new Node('A', null, null);
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            insertNode(head, root, left, right);
        }

        sb = new StringBuilder();
        preorder(head);
        sb.append("\n");

        inorder(head);
        sb.append("\n");

        postorder(head);

        System.out.println(sb);
    }
}