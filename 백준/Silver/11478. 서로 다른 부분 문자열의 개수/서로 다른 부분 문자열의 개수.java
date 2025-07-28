
/*
[백준]
11478, 서로 다른 부분 문자열의 개수

[문제파악]
문자열 S가 주어졌을 때, S의 서로 다른 부분 문자열의 개수를 구하는 프로그램을 작성하시오.
부분 문자열은 S에서 연속된 일부분을 말하며, 길이가 1보다 크거나 같아야 한다.
예를 들어, ababc의 부분 문자열은 a, b, a, b, c, ab, ba, ab, bc, aba, bab, abc, abab, babc, ababc가 있고, 서로 다른것의 개수는 12개이다.

[입력]
첫째 줄에 문자열 S가 주어진다.
S는 알파벳 소문자로만 이루어져 있고, 길이는 1,000 이하이다.

[출력]
첫째 줄에 S의 서로 다른 부분 문자열의 개수를 출력한다.

[구현방법]
- 이거 그냥 조합으로 각 자릿수를 껐다 키는 방식으로 구하고 set에 전부 넣어서 중복 제거하고 count하는게 제일 효율적일 것 같다

[보완점]
- ㅎㅎ 문제를 똑바로 읽자 일단 '연속된 부분을 구해야한다
- 그러면? 문자의 시작부분과 끝부분을 이동해가며 subString으로 잘라내는게 베스트!
- 주의할 점은 배열과 달리 subString은 끝자락, 즉 length() - 1 이 아닌 length()까지 다 쓸 수 있다는 것을 주의하자
- s=abcdef 가 있고, s.subString(0, 1)이면 0번째인 a는 포함하고, 1번째 직전까지 자른다는 의미이다
- 즉, 1인 b는 포함되지 않는다는 의미
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        HashSet<String> words = new HashSet<>();

        for (int start = 0; start < input.length(); start++) {
            for (int end = start + 1; end <= input.length(); end++) {
                words.add(input.substring(start, end));
            }
        }

        System.out.println(words.size());
    }
}

/*
# 부분 문자열로 구현하고 싶으면 아래처럼!

public class Main {
    final static String INPUT = "ababc";
    static HashSet<String> partial = new HashSet<>();

    static void recursive(int index, boolean[] isUse) {
        // 끝에 도달한 경우 HashSet에 추가
        if (index == INPUT.length()) {
            StringBuilder sb = new StringBuilder();
            char[] tempInput = INPUT.toCharArray();

            // 사용 체크한 문자만 추가하기
            for (int i = 0; i < INPUT.length(); i++) {
                if (!isUse[i]) continue;
                sb.append(tempInput[i]);
            }

            // 공백 문자가 아니라면 부분 문자열에 추가
            if (!sb.toString().trim().isEmpty()) partial.add(sb.toString());

            return;
        }

        // 현재 인덱스 선택하지 않음
        isUse[index] = false;
        recursive(index + 1, isUse);

        // 현재 인덱스 선택함
        isUse[index] = true;
        recursive(index + 1, isUse);
    }

    public static void main(String[] args) {
        boolean[] isUse = new boolean[INPUT.length()];

        recursive(0, isUse);

        System.out.println(partial);
    }
}
* */