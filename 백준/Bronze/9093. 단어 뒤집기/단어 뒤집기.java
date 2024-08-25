/*
[백준]


[문제파악]
- 문장이 주어졌을 때, 단어를 모두 뒤집어서 출력하는 프로그램을 작성하시오.
- 단, 단어의 순서는 바꿀 수 없다.
- 단어는 영어 알파벳으로만 이루어져 있다.

[입력]
- 첫째 줄에 테스트 케이스의 개수 T가 주어진다.
- 각 테스트 케이스는 한 줄로 이루어져 있으며, 문장이 하나 주어진다.
- 단어의 길이는 최대 20, 문장의 길이는 최대 1000이다.
- 단어와 단어 사이에는 공백이 하나 있다.

[출력]
- 각 테스트 케이스에 대해서, 입력으로 주어진 문장의 단어를 모두 뒤집어 출력한다.

[구현방법]
- 내가 StringBuilder의 reverse를 배웠기 떄문에! 굉장히 쉽게할 수 있을 것으로 보인다 흐흐
- 아 StringBuffer도 가능하다!

[보완점]
- StringBuffer와 StringBuilder의 차이
    1) StringBuffer
        - StringBuffer는 스레드 안전(thread-safe)함
        - 이 덕분에 멀티 쓰레드 환경에서도 아주 유익하게 활용 가능
    2) StringBuilder
        - StringBuilder는 스레드 안전하지 않음.
        - 멀티 스레드 환경에서 위험함
    - 무튼 동기화라는 기능이 추가되었기 때문에 StringBuffer가 조금 더 느린 편
    - 알고리즘에선 StringBulider가 좀 더 적합하겠죠?
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder result = new StringBuilder();
        StringBuilder temp;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()) {
                temp = new StringBuilder(st.nextToken()).reverse();  // 문자열 입력받고 뒤집어주기!
                result.append(temp).append(" ");
            }

            result.append("\n");
        }

        System.out.println(result);
    }
}