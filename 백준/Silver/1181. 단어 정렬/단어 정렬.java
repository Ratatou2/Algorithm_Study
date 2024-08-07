/*
[백준]


[문제파악]
- 알파벳 소문자로 이루어진 N개의 단어가 들어오면 아래와 같은 조건에 따라 정렬하는 프로그램을 작성하시오.
- 길이가 짧은 것부터, 길이가 같으면 사전 순으로 정렬
- 단, 중복된 단어는 하나만 남기고 제거해야 한다.

[입력]
- 첫째 줄에 단어의 개수 N이 주어진다. (1 ≤ N ≤ 20,000)
- 둘째 줄부터 N개의 줄에 걸쳐 알파벳 소문자로 이루어진 단어가 한 줄에 하나씩 주어진다.
	- 주어지는 문자열의 길이는 50을 넘지 않는다.

[출력]
- 조건에 따라 정렬하여 단어들을 출력한다.

[구현방법]
- Comparator를 커스텀 할줄 알아야 쉽다..!!!
- 중복제거도 염두에 두어야 한다!!

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 문자열 길이 및 알파벳 순서로 정렬하는 Comparator
    static class CustomeSortComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            int length = Integer.compare(str1.length(), str2.length());

            if (length == 0) return str1.compareTo(str2);

            return length;
        }
    }

    // 메인 클래스
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Set<String> input = new HashSet<>();  // 중복 제거를 위해 Set 사용

        for (int i = 0; i < N; i++) {
            input.add(br.readLine());
        }

        // 길이 우선, 길이가 같으면 알파벳 순으로 정렬
        String[] temp = input.toArray(new String[input.size()]);
        Arrays.sort(temp, new CustomeSortComparator());

        for (int i = 0; i < input.size(); i++) {
            sb.append(temp[i]).append("\n");
        }

        System.out.println(sb);
    }
}