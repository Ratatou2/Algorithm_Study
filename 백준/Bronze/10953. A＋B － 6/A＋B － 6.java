/*
[백준]
https://www.acmicpc.net/problem/10953
10953, A+B-6

[문제파악]
- 두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 테스트 케이스의 개수 T가 주어진다.
- 각 테스트 케이스는 한 줄로 이루어져 있으며, 각 줄에 A와 B가 주어진다.
- A와 B는 콤마(,)로 구분되어 있다. (0 < A, B < 10)


[출력]
- 각 테스트 케이스마다 A+B를 출력한다.

[구현방법]
- 이거 인풋이 ,를 두고 구분되니 ,을 기준으로 둘을 나눠서 split하고 더하면 될듯!
- 이때 아직 문자열 상태일텐데 -'0'을 해볼까? (응 당연히 안되지 ㅋㅋ)
- 근데 문자가 아닌 문자열인데 되나? (안된다고 ㅋㅋ)
- 아니면 그냥 index 아니까 charAt()으로 해도 되는데...
- 둘다 해보지 뭐
- 결국엔 charAt() 안쓰고 그냥 char 배열로 받아가지고 '0' 문자 빼서 숫자로 더해버렸다
- char에는 toCharArray()가 있다는 것을 잊지말자!

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();

            sb.append((input[0] - '0') + (input[2] - '0')).append("\n");
        }

        System.out.println(sb);
    }
}