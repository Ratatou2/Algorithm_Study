/*
[백준]
2558, A + B - 2

[문제파악]
두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.

[입력]
첫째 줄에 A, 둘째 줄에 B가 주어진다. (0 < A, B < 10)

[출력]
첫째 줄에 A+B를 출력한다.

[구현방법]
- 냅다 더해

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(Integer.parseInt(br.readLine()) + Integer.parseInt(br.readLine()));
    }
}