/*
[백준]
14425, 문자열 집합

[문제파악]
- N개의 문자열로 이루어진 집합 S
- M개의 문자열 중에서 집합 S에 포함되어 있는 것은 총 몇개?

[입력]
- 첫째줄에 문자열의 갯수 N과 M (1 <= N, M <= 10,000)
- 둘째줄에 집합 S에 포함되어있는 문자열
- 셋째줄부터 M개의 줄에는 검사해야하는 문자열들이 주어짐
	- 문자열은 소문자로만 이뤄져 있으며 길이는 500을 넘지 않는다
	- 같은 문자열이 여러번 주어지느 경우는 없다

[출력]
- 첫째줄에 M개의 문자열 중에 총 몇개가 집합 S에 포함되는지 출력

[구현방법]
- 해시 쪽이 약해서 당분간은 이쪽 문제만 진행 예정

[보완점]
- N개의 문자열이 집합 S고, 그 뒤로 나오는 M개의 문자열이 확인 대상이다 요놈아...
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        Map<String, Integer> S = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            S.put(word, S.getOrDefault(word, 0) + 1);
        }

        int result = 0;
        for (int i = 0; i < M; i++) {
            if (S.containsKey(br.readLine())) result++;
        }

        System.out.println(result);
    }
}