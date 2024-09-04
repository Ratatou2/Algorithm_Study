/*
[백준]


[문제파악]
- 어떤 수 X가 주어졌을 때, X의 모든 자리수가 역순이 된 수를 얻을 수 있다.
- Rev(X)를 X의 모든 자리수를 역순으로 만드는 함수라고 하자.
- 예를 들어, X=123일 때, Rev(X) = 321이다. 그리고, X=100일 때, Rev(X) = 1이다.
- 두 양의 정수 X와 Y가 주어졌을 때, Rev(Rev(X) + Rev(Y))를 구하는 프로그램을 작성하시오

[입력]
- 첫째 줄에 수 X와 Y가 주어진다.
- X와 Y는 1,000보다 작거나 같은 자연수이다.

[출력]
- 첫째 줄에 문제의 정답을 출력한다.

[구현방법]
- 처음엔 그냥 더하고 뒤바꾸면 되는 것 아닌가 하였는데 생각해보니 더한 후 자릿수를 올려야하는 경우가 문제다 (2 + 9 = 11)
- 아 근데 그럼 이거 뒤집고 int로 바꾸고 계산하고 이래야돼...? 세상 비효율 아니야?
- 근데 난 일단 StringBuilder에 reverse 기능이 있다는걸 11945(뜨거운 붕어빵) 문제를 풀며 알게되었지...
- 이것을 써서 꿀만 가져갈 수 있도록 해보겠다!
- 아 조심해야하는게 String으로 출력하면 01같은 결과도 나온다 Integer.parseInt는 반 필수!

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String X = new StringBuilder(st.nextToken()).reverse().toString();
        String Y = new StringBuilder(st.nextToken()).reverse().toString();

        StringBuilder result = new StringBuilder(String.valueOf(Integer.parseInt(X) + Integer.parseInt(Y))).reverse();

        System.out.println(Integer.parseInt(result.toString()));
    }
}