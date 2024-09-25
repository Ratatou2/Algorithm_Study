/*
[백준]
10822, 더하기

[문제파악]
- 숫자와 콤마로만 이루어진 문자열 S가 주어진다.
- 이때, S에 포함되어있는 자연수의 합을 구하는 프로그램을 작성하시오.
- S의 첫 문자와 마지막 문자는 항상 숫자이고, 콤마는 연속해서 주어지지 않는다.
- 주어지는 수는 항상 자연수이다.


[입력]
- 첫째 줄에 문자열 S가 주어진다.
- S의 길이는 최대 100이다.
- 포함되어있는 정수는 1,000,000보다 작거나 같은 자연수이다.

[출력]
- 문자열 S에 포함되어 있는 자연수의 합을 출력한다.

[구현방법]
- StringToknizer에 구분자로 잘라주는 기능이 있다는걸 아시는가...?
- {1, 2, 3, 4} 가 입력으로 들어올 때 '{} ,' 들을 제거 하고 싶다면 아래와 같이 작성하면 된다
- StringTokenizer st = new StringTokenizer(br.readLine(), "{} ,");
- output : 1~4까지 분리된 형태로 잘 간직되어 있다! 신기!!

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), ",");
        int totalMoney = 0;

        while (st.hasMoreTokens()) {
            totalMoney += Integer.parseInt(st.nextToken());
        }

        System.out.println(totalMoney);
    }
}