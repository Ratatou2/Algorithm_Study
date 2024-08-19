/*
[백준]
2754, 학점계산

[문제파악]
- 어떤 사람의 C언어 성적이 주어졌을 때, 평점은 몇 점인지 출력하는 프로그램을 작성하시오.

A+: 4.3, A0: 4.0, A-: 3.7
B+: 3.3, B0: 3.0, B-: 2.7
C+: 2.3, C0: 2.0, C-: 1.7
D+: 1.3, D0: 1.0, D-: 0.7
F: 0.0

[입력]
- 첫째 줄에 C언어 성적이 주어진다.
- 성적은 문제에서 설명한 13가지 중 하나이다.

[출력]
- 첫째 줄에 C언어 평점을 출력한다.

[구현방법]
- 직접 때려넣는거 말고 뭐 방법이 있나...?
- 더 우아한 방법이 안떠오르는ㄷ...

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, String> scoreCalculator = new HashMap<>();

        scoreCalculator.put("A+", "4.3");
        scoreCalculator.put("A0", "4.0");
        scoreCalculator.put("A-", "3.7");

        scoreCalculator.put("B+", "3.3");
        scoreCalculator.put("B0", "3.0");
        scoreCalculator.put("B-", "2.7");

        scoreCalculator.put("C+", "2.3");
        scoreCalculator.put("C0", "2.0");
        scoreCalculator.put("C-", "1.7");

        scoreCalculator.put("D+", "1.3");
        scoreCalculator.put("D0", "1.0");
        scoreCalculator.put("D-", "0.7");

        scoreCalculator.put("F", "0.0");

        String input = br.readLine();

        System.out.println(scoreCalculator.get(input));
    }
}