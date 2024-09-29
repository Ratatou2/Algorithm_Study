/*
[백준]
2711, 오타맨 고창영

[문제파악]
- 고창영은 맨날 오타를 낸다.
- 창영이가 오타를 낸 문장과 오타를 낸 위치가 주어졌을 때, 오타를 지운 문자열을 출력하는 프로그램을 작성하시오.
- 창영이는 오타를 반드시 1개만 낸다.

[입력]
- 첫째 줄에 테스트 케이스의 개수 T(1<=T<=1,000)가 주어진다.
- 각 테스트 케이스는 한 줄로 구성되어 있다. 첫 숫자는 창영이가 오타를 낸 위치이고, 두 번째 문자열은 창영이가 친 문자열이다.
- 문자열의 가장 첫 문자는 1번째 문자이고, 문자열의 길이는 80을 넘지 않고, 대문자로만 이루어져 있다.
- 오타를 낸 위치는 문자열 길이보다 작거나 같다.

[출력]
- 각 테스트 케이스에 대해 오타를 지운 문자열을 출력한다.

[구현방법]
- 참 파이썬이 그리운 문제다
- 뭐 어케든 짧게, 혹은 빠르게 작성하고 싶지만 내부적으로 죄다 for문을 쓴다면 어쩌면 for문이 제일 빠르지 않을까?
- 일단 for문으로 구현해보고 더 좋은 방법이 있으면 얼마나 치아가 나나 측정해봅시다
- index니까 N번째를 구하라고 하면 항상 N-1번째를 살펴봐야함을 잊지말자
[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < N; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int index = Integer.parseInt(st.nextToken()) - 1;
            String word = st.nextToken();

            for (int i = 0; i < word.length(); i++) {
                if (index != i) sb.append(word.charAt(i));
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}