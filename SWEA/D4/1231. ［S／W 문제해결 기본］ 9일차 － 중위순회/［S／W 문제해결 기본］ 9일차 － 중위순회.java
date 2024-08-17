/*
[SWEA]
- 07, 중위순회

[문제파악]
- 위 트리를 in-order 형식으로 순회할 경우 SOFTWARE 라는 단어를 읽을 수 있다.
- 주어진 트리를 in-order 형식으로 순회했을때 나오는 단어를 출력하라.

[입력]
- 총 10개의 테스트 케이스가 주어진다. (총 테스트 케이스의 개수는 입력으로 주어지지 않는다)
- 각 테스트 케이스의 첫 줄에는 트리가 갖는 정점의 총 수 N(1≤N≤100)이 주어진다.
- 그 다음 N줄에 걸쳐 각각의 정점 정보가 주어진다.
- 정점 정보는 해당 정점의 문자, 해당 정점의 왼쪽 자식, 오른쪽 자식의 정점 번호 순서로 주어진다.
- 정점 번호는 1부터 N까지의 정수로 구분된다.
- 정점 번호를 매기는 규칙은 위 와 같으며, 루트 정점의 번호는 항상 1이다.
- 위의 예시에서, 알파벳 ‘F’가 2번 정점에 해당하고 두 자식이 각각 알파벳 ‘O’인 4번 정점과 알파벳 ‘T’인 5번 정점이므로 “2 F 4 5”로 주어진다.
- 알파벳 S는 8번 정점에 해당하므로 “8 S” 로 주어진다.

[출력]
- # 부호와 함께 테스트 케이스의 번호를 출력하고, 공백 문자 후 테스트 케이스의 답을 출력한다.

[구현방법]
- 일단 어떨 때는 입력값이 4개고 때론 3개고 때론 2개인데 이걸 어떻게 구분해야할까?
    - 쉬운건 그냥 배열에 때려넣고 길이 구분하는건데 너무 비효율적인데요... 근데 StringTokenzier로 지저분하게 읽느니 그냥 split() 한번 쓰는게 나을 것 같긴하다...
- 그래서 일단은 split으로 나눠주고, 배열 length 재서 있으면 넣어주고 없으면 null 처리해줬다
- DFS로 풀면될듯!
- DFS로 해서 왼쪽부터 탐색하고 null이면 그제서야 sout하고 오른쪽 마저 탐색하는 식으로
- 와!! 트리 + DFS 탐색 어려워하던 내가 답 안보고 풀었다 ㅠㅠ 감개무량..!!! 

[보완점]

8
1 W 2 3
2 F 4 5
3 R 6 7
4 O 8
5 T
6 A
7 E
8 S


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    static Map<Integer, Node> tree;
    static boolean[] isVisited;
    static StringBuilder sb = new StringBuilder();

    static class Node {
        String num, left, right;
        String value;

        Node(String num, String value, String left, String right) {
            this.num = num;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return num + "(" + value + ") : " + left + ", " + right;
        }
    }

    static void DFS(String index) {
        int temp = Integer.parseInt(index);
        if (isVisited[temp]) return;
        isVisited[temp] = true;

        Node node = tree.get(temp);
        if (node.left != null) DFS(node.left);
        sb.append(node.value);
        if (node.right != null) DFS(node.right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            int N = Integer.parseInt(br.readLine());
            isVisited = new boolean[N + 1];
            tree = new HashMap<>();

            sb.append("#").append(tc).append(" ");

            for (int i = 1; i <= N; i++) {
                String[] input = br.readLine().split(" ");

                String num = input[0];
                String value = input[1];
                String left = 3 <= input.length ? input[2] : null;
                String right = 4 <= input.length ? input[3] : null;
                tree.put(Integer.parseInt(num), new Node(num, value, left, right));
            }

            DFS("1");

            sb.append("\n");
        }

        System.out.println(sb);
    }
}