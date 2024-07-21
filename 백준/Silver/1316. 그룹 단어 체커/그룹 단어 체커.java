/*
[백준]
1316, 그룹 단어 체커

[문제파악]
- 그룹 단어 여부를 파악하라
- 그룹 단어란 문자가 연속해서 나오는 경우만을 의미한다
- 단어 N개를 입력으로 받아 그룹 단어의 갯수를 출력하는 프로그램을 작성하시오

[입력]
- 첫째줄에 단어 갯수 N (1 <= N <= 100, 자연수)
- 둘째줄에 N개의 줄에 단어

[출력]
- 그룹 단어의 갯수를 출력

[구현방법]
- map 만들어서 이전에 있던 값이면 연속되지 않는다는 의미로 해석하면 될듯

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int checkWord(String[] wordList) {
        Map<String, Integer> countMap = new HashMap();

        String prevString = wordList[0];
        countMap.put(prevString, 1);
        for (int i = 1; i < wordList.length; i++) {
            String currentString = wordList[i];

            // 이전값과 같다면 continue
            if (prevString.equals(currentString)) continue;
            else {
                // 1) 이전과 다르다면, map에 있는지부터 체크
                if (countMap.containsKey(currentString)) {
                    // 1-1) 있다면, 연속되지 않으니 그대로 return 0
                    return 0;
                } else {
                    // 1-2) 없다면, 연속되는 것이니 map에 추가
                    countMap.put(currentString, 1);
                    // 이전값을 변경
                    prevString = wordList[i];
                }
            }
        }

        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int result = 0;

        for (int i = 0; i < N; i++) {
            String[] wordList = br.readLine().split("");
            result += checkWord(wordList);
        }

        System.out.println(result);
    }
}