/*
[백준]
1969, DNA

[문제파악]
DNA란 어떤 유전물질을 구성하는 분자이다.
이 DNA는 서로 다른 4가지의 뉴클레오티드로 이루어져 있다(Adenine, Thymine, Guanine, Cytosine).
우리는 어떤 DNA의 물질을 표현할 때, 이 DNA를 이루는 뉴클레오티드의 첫글자를 따서 표현한다.
만약에 Thymine-Adenine-Adenine-Cytosine-Thymine-Guanine-Cytosine-Cytosine-Guanine-Adenine-Thymine로 이루어진 DNA가 있다고 하면, “TAACTGCCGAT”로 표현할 수 있다.
그리고 Hamming Distance란 길이가 같은 두 DNA가 있을 때, 각 위치의 뉴클오티드 문자가 다른 것의 개수이다.
만약에 “AGCAT"와 ”GGAAT"는 첫 번째 글자와 세 번째 글자가 다르므로 Hamming Distance는 2이다.

우리가 할 일은 다음과 같다.
N개의 길이 M인 DNA s1, s2, ..., sn가 주어져 있을 때 Hamming Distance의 합이 가장 작은 DNA s를 구하는 것이다.
즉, s와 s1의 Hamming Distance + s와 s2의 Hamming Distance + s와 s3의 Hamming Distance ... 의 합이 최소가 된다는 의미이다.

[입력]
첫 줄에 DNA의 수 N과 문자열의 길이 M이 주어진다. 그리고 둘째 줄부터 N+1번째 줄까지 N개의 DNA가 주어진다. N은 1,000보다 작거나 같은 자연수이고, M은 50보다 작거나 같은 자연수이다.

[출력]
첫째 줄에 Hamming Distance의 합이 가장 작은 DNA 를 출력하고, 둘째 줄에는 그 Hamming Distance의 합을 출력하시오. 그러한 DNA가 여러 개 있을 때에는 사전순으로 가장 앞서는 것을 출력한다.

[구현방법]
- 이건 각 문자열의 자릿수마다 다른 문자열이 최대한 '적은' 문자열을 하나 만들면된다
- 그리고 다를 때마다 count하면 빠르게 셀 수 있음

[보완점]
- 한줄 처리 가능
    count.putIfAbsent(currChar, 0);
    count.put(currChar, count.get(currChar) + 1);

    를 아래처럼 한줄로 가능

    count.put(currChar, count.getOrDefault(currChar, 0) + 1);
    
- 2%에서 계속 틀리던건 사전순을 실수해서 그랬음..
*/


import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int length = Integer.parseInt(st.nextToken());

        // 단어들 입력 받기
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }


        StringBuilder sb = new StringBuilder();
        int HD = 0;

        // 철자 하나씩 비교하면서 count
        for (int i = 0; i < length; i++) {
            Map<Character, Integer> count = new HashMap<>();  // 각 철자들이 몇번이나 나왔는지 count해줄 Map

            for (int w = 0; w < N; w++) {
                char currChar = words[w].charAt(i);  // 각 단어의 현재 철자
                count.put(currChar, count.getOrDefault(currChar, 0) + 1);  // putIfAbsent + put 조합보다 한줄로 처리할 수도 있음
            }

            // 이제 혹시나 count가 같은 경우는 사전순으로 처리하기 위한 작업
            Character mostChar = 'A';
            int maxCount = 0;
            for (Map.Entry<Character, Integer> temp : count.entrySet()) {
                if (maxCount < temp.getValue()) {  // 기존의 maxCount보다 큰 값이 있다는 것은 더 많은 빈도수로 나왔다는 것이니까 값 갱신해줌
                    mostChar = temp.getKey();
                    maxCount = temp.getValue();
                } else if (maxCount == temp.getValue() && temp.getKey() < mostChar) {  // 단 maxCount가 같은 경우는 사전순으로 비교하기 위해 철자 비교함
                    mostChar = temp.getKey();
                }
            }

            sb.append(mostChar);
            HD += N - maxCount;
        }

        System.out.println(sb + "\n" + HD);
    }
}
