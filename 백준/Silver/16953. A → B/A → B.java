/*
[백준]
16953, A -> B

[문제파악]
- 정수 A를 B로 바꾸려고 한다
- 가능한 연산은 두가지이다
	- 2를 곱한다
	- 1을 수의 가장 오른쪽에 추가한다
- A를 B로 바꾸는데 필요한 연산의 최솟값을 구해보자

[입력]
- 첫째줄에 A, B (1 <= A, B <= 1,000,000,000)

[출력]
- A를 B로 바꾸는데 필요한 연산의 최솟값에 1을 더한 값을 출력한다
- 만들 수 없는 경우에는 -1을 출력한다

[구현방법]
- 이건 수식이라 해야하나 규칙을 찾는 것이 관건인 문제
- 팁은 BFS 해보거나, 큰 수에서 작은 수로 (B -> A) 접근 해보는 것

[보완점]
- 1일 때 지우는 건, 10으로 나눠서 나머지로 구해도 되고, String으로 바꿔서 char 끝 한 글자만 가져와도 된다
- 방법은 여러가지가 있음
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int calculate(int A, int B) {
        int count = 1;

        while (true) {
            if (A == B) break;

            if (B % 10 == 1 && 1 < Integer.toString(B).length()) {  // 끝자리가 1인 경우
                String temp = Integer.toString(B);
                B = Integer.parseInt(temp.substring(0, temp.length() - 1));
                count += 1;
            } else if (B % 2 == 0) {  // 2로 나뉘어지는 경우
                B = B / 2;
                count += 1;
            } else {
                count = -1;
                break;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int A = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);

        System.out.println(calculate(A, B));
    }
}