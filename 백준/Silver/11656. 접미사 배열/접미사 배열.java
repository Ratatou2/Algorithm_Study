/*
[백준]
11656, 접미사

[문제파악]
- 접미사 배열은 문자열 S의 모든 접미사를 사전순으로 정렬해 놓은 배열이다.
- baekjoon의 접미사는 baekjoon, aekjoon, ekjoon, kjoon, joon, oon, on, n 으로 총 8가지가 있고, 이를 사전순으로 정렬하면, aekjoon, baekjoon, ekjoon, joon, kjoon, n, on, oon이 된다.
- 문자열 S가 주어졌을 때, 모든 접미사를 사전순으로 정렬한 다음 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 문자열 S가 주어진다.
- S는 알파벳 소문자로만 이루어져 있고, 길이는 1,000보다 작거나 같다.

[출력]
- 첫째 줄부터 S의 접미사를 사전순으로 한 줄에 하나씩 출력한다.

[구현방법]
- 이거 그냥 앞에서 한글자씩 자르면 되는...?
- 그렇다면 그냥 반복문 돌리면서 앞에서부터 한글자씩 잘라낸다음, 잘라낸 것을 자료구조에 담고 정렬해버리면 될 것 같다
- Set을 쓰는게 깔끔하지 않을까 함
- 아 특히 문제에 '정렬'이라고 조건이 들어갔으니까 TreeSet을 써야 한다
    - HashSet은 순서를 보장해주지도 않을 뿐더러, TreeSet은 내부적으로 이진 탐색 트리로 구현되어 있으며, 삽입 시 자동으로 정렬되기 때문임.

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Set<String> answer = new TreeSet<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            answer.add(input.substring(i));
        }

        for (String temp : answer) {
            sb.append(temp).append("\n");
        }

        System.out.println(sb);
    }
}