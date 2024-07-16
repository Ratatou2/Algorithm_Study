/*
[백준]
20365, 블로그2

[문제파악]
- 매일 밤 문제에 대하여 아래와 같이 색칠
	- 해결한 경우 파란색
	- 해결하지 못한 경우 빨간색
- 연속된 임의의 문제들을 선택
- 선택된 문제들을 전부 원하는 같은 색으로 색칠
- 매일 500,000 문제까지 시도
- 색칠을 최소한의 작업 횟수를 구하는 프로그램 작성할 것

[입력]
- 첫째줄에 색을 칠해야하는 문제의 수 N
- 둘째줄에 N개의 문제가 공백없이 순서대로 주어짐
	- 각 문자는 i번째 문제를 어떤 색으로 칠해야하는지를 의미
	- R은 빨간색, B는 파란색을 나타냄 (그 외 문자는 주어지지 않는다)

[출력]
- 색을 칠하는 최소 횟수 출력

[구현방법]
- 하나의 색으로 전부 다 칠하고, 다른 색이 나오면 작업 횟수 +1이 된다

[보완점]
- 다른 색이 나오더라도 연속되어 있다면, 그것은 한번에 칠할 수 있다!는 점을 주의할 것
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] paint = br.readLine().split("");

        int sum = 1;
        String first = paint[0];  // 첫번째 색을 기억 (첫번째 색으로 전부 다 칠한 것과 같은 상황
        String prevColor = "";
        for (int i = 1; i < N; i++) {
            // 첫번째 색과 다르면 다른 색을 칠한 것과 같으므로 작업 횟수는 1회 증가한다
            // 다만, 첫번째 색과 같더라도 이전 색과 같으면 증가할 필요없다. (한번의 붓질로 색칠 가능하니까)
            if (!first.equals(paint[i]) && !prevColor.equals(paint[i])) {
                sum += 1;
            }
            
            prevColor = paint[i];
        }

        System.out.println(sum);
    }
}