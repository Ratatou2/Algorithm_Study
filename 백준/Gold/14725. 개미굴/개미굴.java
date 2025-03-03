import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {  // 클래스 이름을 Main으로 변경
    static class TrieNode {
        // 자식 노드를 저장하는 맵 (사전 순서 유지를 위해 TreeMap 사용)
        Map<String, TrieNode> children = new TreeMap<>();
    }

    static class Trie {
        TrieNode root = new TrieNode();

        // 경로 삽입 메서드
        void insert(String[] path) {
            TrieNode node = root;
            for (String food : path) {
                // 자식이 없으면 새로 추가
                node.children.putIfAbsent(food, new TrieNode());
                node = node.children.get(food);
            }
        }

        // 트라이를 탐색하며 시각화
        void print() {
            dfs(root, 0);
        }

        // 깊이 우선 탐색 (DFS)
        private void dfs(TrieNode node, int depth) {
            for (String key : node.children.keySet()) {
                // 깊이에 맞게 "--" 출력
                System.out.println("--".repeat(depth) + key);
                // 자식 노드를 재귀적으로 탐색
                dfs(node.children.get(key), depth + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int K = Integer.parseInt(input[0]);
            String[] path = Arrays.copyOfRange(input, 1, K + 1);
            trie.insert(path);
        }

        // 개미굴 구조 출력
        trie.print();
    }
}