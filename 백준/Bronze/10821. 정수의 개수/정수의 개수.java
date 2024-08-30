/*
[백준]
10821, 정수의 갯수

[문제파악]
- 숫자와 콤마로만 이루어진 문자열 S가 주어진다.
- 이때, S에 포함되어있는 정수의 개수를 구하는 프로그램을 작성하시오.
- S의 첫 문자와 마지막 문자는 항상 숫자이고, 콤마는 연속해서 주어지지 않는다.
- 또, 0으로 시작하는 정수는 주어지지 않는다.

[입력]
- 첫째 줄에 문자열 S가 주어진다.
- S의 길이는 최대 100이다.

[출력]
- 문자열 S에 포함되어 있는 정수의 개수를 출력한다.

[구현방법]
- 이거 그냥 , 기준으로 자르고 나온 배열의 길이 재면 끝인뎅?

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(br.readLine().split(",").length);
    }
}