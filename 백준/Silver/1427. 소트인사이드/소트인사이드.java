/*
[백준]
1427, 소트 인사이드

[문제파악]
- 배열을 정렬하는 것은 쉽다. 수가 주어지면, 그 수의 각 자리수를 내림차순으로 정렬해보자.

[입력]
- 첫째 줄에 정렬하려고 하는 수 N이 주어진다.
- N은 1,000,000,000보다 작거나 같은 자연수이다.

[출력]
- 첫째 줄에 자리수를 내림차순으로 정렬한 수를 출력한다.

[구현방법]
- 이런 공백이 없는 문자열은 charAt(index)로 자르면 된다는 것을 기억하자
- 매번 헷갈려 왤케 까먹냐;;
- 근데 그냥 split하는 것보다 빠를까? 궁금
- 시도 해보고픈게 생겼다
    1. String을 .split("") 써서 배열로 자르고, 냅다 for문 돌려서 출력하기
        - 메모리 : 14280kb, 시간 : 104ms
    2. String을 .split("") 써서 배열로 자르고, String.join문 써서 붙여 출력하기
        - 메모리 : 14300kb, 시간 : 100ms
    3. String을 .charAt()을 써서 하나씩 배열에 넣고, 냅다 for문 돌려서 출력하기
        - 메모리 : 14412kb, 시간 : 100ms
    4. String을 .charAt()을 써서 하나씩 char 배열에 넣고, new String() 써서 하나의 문자열로 출력하기
        - order가 필요하고 그러면 3번이랑 다를게 없음

[보완점]
- 아참 너무 당연하게도 int[], char[] 같은 배열에는 Arrays.sort()가 맞는 것이다
- Collections.sort()는 리스트와 같은 클래스 친구들을 정렬하는데 쓰인다
- 와, char[]는 new String()으로 감싸버리면 하나의 문자열로 토해낸다;;; ㄷㄷ 너무 신기해
- 현 시점에선 charAt()을 쓰되, 정렬을 쓰고 역순 출력이 제일 효율적인 것 같다
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1427_소트인사이드 {
    // 최종본
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        char[] needSort = input.toCharArray();

        Arrays.sort(needSort);

        int length = needSort.length;
        for (int i = 0; i < length; i++) {
            sb.append(needSort[length - 1 - i]);
        }

        System.out.println(sb);
    }


    // 1. String을 .split("") 써서 배열로 자르고, 냅다 for문 돌려서 출력하기
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//
//        String[] input = br.readLine().split("");
//        Arrays.sort(input, Comparator.reverseOrder());
//
//        for (String temp : input) {
//            sb.append(temp);
//        }
//
//        System.out.println(sb);
//    }

    // 2. String을 .split("") 써서 배열로 자르고, String.join문 써서 붙여 출력하기
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String[] input = br.readLine().split("");
//        Arrays.sort(input, Comparator.reverseOrder());
//
//        System.out.println(String.join("", input));
//    }

    // 3. String을 .charAt()을 써서 하나씩 char 배열에 넣고, 냅다 for문 돌려서 출력하기
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//
//        String input = br.readLine();
//        char[] needSort = new char[input.length()];
//        for (int i = 0; i < input.length(); i++) {
//            needSort[i] = input.charAt(i);
//        }
//
//        Arrays.sort(needSort);
//
//        for (int i = 0; i < needSort.length; i++) {
//            sb.append(needSort[needSort.length - 1 - i]);
//        }
//
//        System.out.println(sb);
//    }
}
