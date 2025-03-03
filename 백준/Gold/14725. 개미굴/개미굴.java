/*
[백준]
14725, 개미굴

[문제파악]
- 우리의 천재 공학자 윤수는 이 개미들이 왜 행복한지 궁금해졌다.
- 행복의 비결이 개미가 사는 개미굴에 있다고 생각한 윤수는 개미굴의 구조를 알아보기 위해 로봇 개미를 만들었다.
- 로봇 개미는 센서가 있어 개미굴의 각 층에 먹이가 있는 방을 따라 내려가다 더 이상 내려갈 수 없으면 그 자리에서 움직이지 않고 신호를 보낸다.
- 이 신호로 로봇 개미는 개미굴 각 층을 따라 내려오면서 알게 된 각 방에 저장된 먹이 정보를 윤수한테 알려줄 수 있다.
- 로봇 개미 개발을 완료한 윤수는 개미굴 탐사를 앞두고 로봇 개미를 테스트 해보기 위해 위 그림의 개미굴에 로봇 개미를 투입했다.
- 로봇 개미의 수는 각 개미굴의 저장소를 모두 확인할 수 있을 만큼 넣는다.
- 다음은 로봇 개미들이 윤수에게 보내준 정보다.

    KIWI BANANA
    KIWI APPLE
    APPLE APPLE
    APPLE BANANA KIWI

- 공백을 기준으로 왼쪽부터 순서대로 로봇 개미가 각 층마다 지나온 방에 있는 먹이 이름을 뜻한다.
- 윤수는 로봇 개미들이 보내준 정보를 바탕으로 다음과 같이 개미굴의 구조를 손으로 그려봤다.

    APPLE
    --APPLE
    --BANANA
    ----KIWI
    KIWI
    --APPLE
    --BANANA

- 개미굴의 각 층은 "--" 로 구분을 하였다. 또 같은 층에 여러 개의 방이 있을 때에는 사전 순서가 앞서는 먹이 정보가 먼저 나온다.
- 우리의 천재 공학자 윤수는 복잡한 개미굴들을 일일이 손으로 그리기 힘들어 우리에게 그려달라고 부탁했다.
- 한치 앞도 모르는 험한 이세상 그렇지만 오늘도 행복한 개미들!
- 행복의 비결을 알기 위해 윤수를 도와 개미굴이 어떤 구조인지 확인해보자.

[입력]
- 첫 번째 줄은 로봇 개미가 각 층을 따라 내려오면서 알게 된 먹이의 정보 개수 N (1 ≤ N ≤ 1000)개가 주어진다.
- 두 번째 줄부터 N+1 번째 줄까지, 각 줄의 시작은 로봇 개미 한마리가 보내준 먹이 정보 개수 K (1 ≤ K ≤ 15)가 주어진다.
- 다음 K개의 입력은 로봇 개미가 왼쪽부터 순서대로 각 층마다 지나온 방에 있는 먹이 정보이며 먹이 이름 길이 t는 1 ≤ t ≤ 15를 만족한다.
- 먹이 정보는 알파벳 대문자로만 이루어져 있다.

[출력]
- 개미굴의 시각화된 구조를 출력하여라.
- 개미굴의 각 층을 "--" 로 구분하며, 같은 층에 여러개의 방이 있을 때에는 사전 순서가 앞서는 먹이 정보가 먼저 나온다.
- 최상위 굴을 포함하여 하나의 굴에서 개미굴이 여러개로 나뉠 때 먹이 종류별로 최대 한 번만 나올 수 있다.

[구현방법]
- 이 문제는 트라이 알고리즘 문제이다 (물론 다르게도 풀 수 있음)
- 트라이란?
    - 트라이(Trie)란 문자열을 효율적으로 저장하고 탐색하기 위한 트리 형태의 자료구조
    - 주로 사전(Dictionary) 또는 자동 완성, 검색 제안 기능을 구현할 때 사용됨
    - 트라이는 접두사(Prefix)를 공유하는 문자열들을 효율적으로 관리함
- 이전에 트라이 문제를 틀렸던, 크리티컬하게 작용했던 적이 있다... ㅂㄷㅂㄷ
- 오늘 반드시 꼭꼭 씹어 먹고 넘어가야지
- 어렵다 일단 기본적으로 내가 아는 사전 구조식 Trie는 철자별로 잘라서 넣는데 이건 검색이 아닌, 그냥 계층만 알면 되서 훨씬 간편화 됐다
- 둘째로 한번만 본다고 이해될 그런 알고리즘은 아닌듯하다. 그렇다고 이해를 포기할 것도 아니니 한번 더 봐야할듯 ㅠ
[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static StringBuilder sb;

    static class TrieNode {
        // 자식 노드를 저하기 위한 Map -  사전 순서 유지를 위해 TreeMap 사용
        Map<String, TrieNode> children = new TreeMap<>();
    }

    static class Trie {
        TrieNode root = new TrieNode();

        // 경로를 삽입하는 메서드
        void insert(String[] path) {
            TrieNode node = root;

            for (String food : path) {
                node.children.putIfAbsent(food, new TrieNode());  // 자식이 없으면 새로 추가할 것임
                node = node.children.get(food);  // 그리고 방금 입력한 food 자식으로 넣을 수 있게 갱신
            }
        }

        void print() {
            DFS(root, 0);  // 루트 노드부터 탐색
        }
    }

    // 타고 내려가면서 사전 순으로 출력 + 깊이에 따른 "--" 추가해서 출력할 수 있도록 세팅
    static void DFS(TrieNode node, int depth) {
        for (String key : node.children.keySet()) {
            // 깊이에 맞게 "--"를 덧붙여서 출력해야 함
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                temp.append("--");
            }

            // '--'를 depth만큼 (필요만큼) 덧붙이고, 실제로 출력해야하는 먹이(key)를 붙여서 출력
            sb.append(temp).append(key).append("\n");

            // 해당 노드 기준으로 다시 DFS를 할 것인데 이때, depth는 + 1해서 진행한다
            // 자식의 자식이 있으면 타고 내려가야하기 때문임
            DFS(node.children.get(key), depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Trie trie = new Trie();

        // 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            // 경로 기록
            String[] path = new String[K];
            for (int j = 0; j < K; j++) {
                path[j] = st.nextToken();
            }

            // 트리 구조에 삽입
            trie.insert(path);
        }

        trie.print();
        System.out.println(sb);
    }
}