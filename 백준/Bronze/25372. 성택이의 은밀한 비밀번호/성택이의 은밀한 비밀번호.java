/*
[백준]
25372, 성택이의 은밀한 비밀번호

[문제파악]
- 부산사이버대학교 학생 성택이는 엄마의 의뢰를 받아 주어진 문자열이 현관문 비밀번호에 사용 가능한지 알아내야 한다.
- 성택이는 공부해야 하므로 우리가 도와주자!
- 사용할 수 있는 비밀번호의 규칙은 다음과 같다.
- 비밀번호는 6자리 이상 9자리 이하여야 한다.
- 예를 들어, 123124는 올바른 비밀번호이고, 1202727161은 잘못된 비밀번호이다.
- 문자열이 주어졌을 때 현관문 비밀번호로 사용할 수 있는지 판단하자.

[입력]
- 첫째 줄에 문자열의 총개수 N이 주어진다.
- 둘째 줄부터 N개의 줄에 걸쳐 숫자, 영어 대소문자로만 구성된 문자열이 주어진다. (1 <= N <= 1,000)
- 문자열의 길이는 1자리 이상 20자리 이하이다.

[출력]
- 줄마다 사용할 수 있는 비밀번호면 yes, 그렇지 않으면 no를 출력한다.

[구현방법]
- 길이를 재서 조건에 부합하는지만 체크하면 될듯하다

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
            int inputLength = br.readLine().length();

            if (6 <= inputLength && inputLength <= 9) sb.append("yes").append("\n");
            else sb.append("no").append("\n");
        }

        System.out.println(sb);
    }
}