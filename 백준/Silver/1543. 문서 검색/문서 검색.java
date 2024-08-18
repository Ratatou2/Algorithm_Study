/*
[백준]
1543, 문서검색

[문제파악]
- 세준이는 영어로만 이루어진 어떤 문서를 검색하는 함수를 만들려고 한다.
- 이 함수는 어떤 단어가 총 몇 번 등장하는지 세려고 한다.
- 그러나, 세준이의 함수는 중복되어 세는 것은 빼고 세야 한다.
- 예를 들어, 문서가 abababa이고, 그리고 찾으려는 단어가 ababa라면, 세준이의 이 함수는 이 단어를 0번부터 찾을 수 있고, 2번부터도 찾을 수 있다.
- 그러나 동시에 셀 수는 없다.
- 세준이는 문서와 검색하려는 단어가 주어졌을 때, 그 단어가 최대 몇 번 중복되지 않게 등장하는지 구하는 프로그램을 작성하시오.


[입력]
- 첫째 줄에 문서가 주어진다.
- 문서의 길이는 최대 2500이다.
- 둘째 줄에 검색하고 싶은 단어가 주어진다.
- 이 길이는 최대 50이다.
- 문서와 단어는 알파벳 소문자와 공백으로 이루어져 있다.

[출력]
- 첫째 줄에 중복되지 않게 최대 몇 번 등장하는지 출력한다.

[구현방법]
- 하나씩 위치 옮겨가며 카운트해야하나...?
- 너무 비효율적인..?
- split 쓰면 딱 갯수만큼 쪼개지긴하는데 그걸로는 갯수를 셀수가... (파이썬이 보고 싶어요 흑흑...)
- 근데 가만 생각해보면 파이썬으로 할 땐, 하도 라이브러리로만 써가지고 이렇게 구현해본적이 없구나... 분발해야겠네

[보완점]
- 이런 비교 문제는 인덱스 항상 조심하자
- 마지막에 해당하는 인덱스도 범위에 포함되어야하는지 아닌지를 잘 살펴봐야 틀리지 않음

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb;
    static String originalString, compareString;
    static boolean[] isUse;

    // startIndex를 받으면 거기서부터 비교 글자 수만큼 비교해서 문자열을 만들어서 return
    static String makeWord(int startIndex, int repeat) {
        sb = new StringBuilder();

        for (int i = startIndex; i < repeat + startIndex; i++) {
            sb.append(originalString.charAt(i));
        }

        return sb.toString();
    }

    // 비교했는데 일치하면 사용처리
    static void turnOnUse(int startIndex, int repeat) {
        for (int i = 0; i < repeat; i++) {
            isUse[startIndex + i] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        originalString = br.readLine();
        compareString = br.readLine();
        int originalLength = originalString.length();
        int compareLength = compareString.length();

        int count = 0;
        isUse = new boolean[originalLength];

        for (int i = 0; i <= originalLength - compareLength; i++) {
            if (isUse[i]) continue;  // 이미 단어를 만드는데 사용했으면 pass~
            // 단어가 일치하면 사용처리하고 count + 1
            String temp = makeWord(i, compareLength);
            if (compareString.equals(temp)) {
                turnOnUse(i, compareLength);
                count++;
            }
        }

        System.out.println(count);
    }
}