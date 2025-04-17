/*
[백준]
14426, 접두사 찾기

[문제파악]
- 문자열 S의 접두사란 S의 가장 앞에서부터 부분 문자열을 의미한다.
- 예를 들어, S = "codeplus"의 접두사는 "code", "co", "codepl", "codeplus"가 있고, "plus", "s", "cude", "crud"는 접두사가 아니다.
- 총 N개의 문자열로 이루어진 집합 S가 주어진다.
- 입력으로 주어지는 M개의 문자열 중에서 집합 S에 포함되어 있는 문자열 중 적어도 하나의 접두사인 것의 개수를 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 문자열의 개수 N과 M (1 ≤ N ≤ 10,000, 1 ≤ M ≤ 10,000)이 주어진다.
- 다음 N개의 줄에는 집합 S에 포함되어 있는 문자열이 주어진다.
- 다음 M개의 줄에는 검사해야 하는 문자열이 주어진다.
- 입력으로 주어지는 문자열은 알파벳 소문자로만 이루어져 있으며, 길이는 500을 넘지 않는다.
- 집합 S에 같은 문자열이 여러 번 주어지는 경우는 없다.

[출력]
- 첫째 줄에 M개의 문자열 중에 총 몇 개가 포함되어 있는 문자열 중 적어도 하나의 접두사인지 출력한다.

[구현방법]
- 문자열을 트리 구조로 만든 다음에 (사전 탐색하듯) 찾아야할 것 같다
- 그럼 자료구조는 뭘 쓰지? LinkedList? -> 이 부분이 틀린 지점 (아 가능은한데, 문제에서 요구하는 수준의 난이도는 아니라는 것)
- Trie가 권장되는 문제이다 -> Trie를 안 쓰면 이분탐색으로 가능하겠지만, 사전검색, 접두사 찾기 등은 Trie를 쓰라고 만든 문제나 다름 없음.
- 나는 이전에 Trie 문제를 Hash로 풀다가 틀린 경험이 있고, Trie를 전혀 모르니 오늘은 Trie를 외우고 내것으로 만드는데 최선을 다할 예정

- Trie의 핵심은 문자를 쪼개서 tree 구조로 연결시키는 것이다
- 즉 bag이란 문자열이 있다면 처음은 b로 시작한다
- 그 다음에 b에 연결된 자식 노드들 중 a 문자가 있다면, a의 자식 노드들 중에 또 g를 찾는 식이다
- 이런 식으로 타고 내려가서, 접두사가 되는지 확인을 하면 된다 
- Trie 구조는 그냥 원리는 이정도로 이해하고(어차피 이게 전부임), 나머지는 그냥 코드 구조 암기하는게 더 효율적일 것 같다

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord = false;  // 단어의 끝이란 것을 표기하는 기능 (이것이 없으면 go, gone이 구분이 안 된다)
    }

    static class Trie {
        // 루트 노드 선언 (빈 TrieNode에서 시작)
        TrieNode root = new TrieNode();

        // 단어가 들어오면 이것을 Trie 구조로 입력
        public void insert(String word) {
            TrieNode node = root;  // 루트를 기점으로 타고 내려갈 것임

            for (char ch : word.toCharArray()) {
                int index = ch - 'a';  // '문자' a를 빼서 현 알파벳의 index를 구한다

                // 해당 문자 자식 노드가 없으면? 새로 만들어야지
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }

                // 다음 노드로 이동
                node = node.children[index];
            }

            node.isEndOfWord = true;  // 그리고 새로 등록한 노드가 끝임을 알린다
        }

        public boolean isPrefix(String word) {
            TrieNode node = root;

            for (char ch : word.toCharArray()) {
                int index = ch - 'a';  // 현 알파벳의 index

                // 해당 문자 자식 노드가 없다면? 이것은 더 볼 것도 없이 접두사가 아니다
                if (node.children[index] == null) {
                    return false;
                }

                node = node.children[index];  // 찾았으면 계속 타고 내려감 (그 다음 문자가 맞는지 확인해야하니까)
            }

            return true;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();

        // 문자열 입력 받기
        for (int i = 0; i < N; i++) {
            trie.insert(br.readLine());
        }

        // 접두사 몇 개인지 찾기
        int count = 0;
        for (int i = 0; i < M; i++) {
            // 접두사면 count + 1
            if (trie.isPrefix(br.readLine())) {
                count++;
            }
        }

        System.out.println(count);
    }
}