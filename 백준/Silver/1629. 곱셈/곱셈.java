/*
[백준]
1629, 곱셈

[문제파악]
- 자연수 A를 B번 곱한 수를 알고 싶다.
- 단 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 A, B, C가 빈 칸을 사이에 두고 순서대로 주어진다.
- A, B, C는 모두 2,147,483,647 이하의 자연수이다.

[출력]
- 첫째 줄에 A를 B번 곱한 수를 C로 나눈 나머지를 출력한다.

[구현방법]
- 이거는 아무래도 규칙성이나 방법(정론)을 찾아야하는디
- 분할정복해서 곱한 것끼리 곱한걸 찾고 해야할거 같은데 DP마냥 저장해서..?
- 그래서 그걸 어떻게 하는데
- 야 이거 수학 모르면 못푸는거였네!!!!
- 일단 지수를 반으로 쪼개는 방법 (분할 정복까진 ㅇㅋ)
- 근데 이거 모듈러 공식도 필요하다 허허...
- 아무튼 일단 분할 정복을 적용한 방법에 대해 설명을 좀 정리해보자
- 분할정복이란, 큰 문제를 작게 쪼개서 해결해서 중복 계산 방법을 줄이는 방식이다
- 이 문제의 경우
    - (A x A x A x A x ...) % C의 경우는 아래와 같이 표기 가능하다
    - (A % C) x (A % C) x (A % C) x (A % C) ...
- 이 말인 즉, A % C를 구하면 계산식을 중복되는걸 계산안하고 있는걸 갖다 써서 처리할 수 있단 소리다 (마치 DP처럼!!)
- 자세한건 코드에 주석으로 대체

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long C;

    static long pow(long A, long B) {
        // 제곱수(=지수)가 1일 경우, A 자신이니 C로 나눈 나머지를 구한다 (최소 단위에 도착한 것이기 때문에)
        if(B == 1) {
            return A % C;
        }

        // 최소 단위에 접근하기 위해 재귀로 접근, 이때 제곱수는 /2로 반 토막낸다
        long temp = pow(A, B / 2);

        // 지수가 홀수면  A % C를 한번 더 곱해줘야 한다
        // 예시 생각해보면 너무 당연한 이야기인데 그냥 수학공식이니까...
        // A^5 = A^2 * A^2 * A
        return B % 2 == 1 ? (temp * temp % C) * A % C : temp * temp % C;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A  = Integer.parseInt(st.nextToken());
        long B  = Integer.parseInt(st.nextToken());
        C  = Integer.parseInt(st.nextToken());

        System.out.println(pow(A, B));
    }
}