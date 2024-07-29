/*
[백준]
21968, 선린의 터를

[문제파악]
- 학교를 지을만한 터를 골라라
- 각 터는 서로 다른 자연수를 번호로 갖고 있다
- 선린의 터의 번호는 3^k 꼴의 자연수가 최대 한번씩 더해진 자연수이다 (0 <= K)
- 즉, 선린의 터의 번호는
	- 3^0 = 1
	- 3^1 = 3
	- 3^2 = 9
	- 3^3 = 27
	- 3^4 = 81
	- 3^2 + 3^4 = 90

[입력]
- 첫번째 줄에 선린의 터의 개수 T
- 두번째 줄부터 T줄에 걸쳐 찾아야 하는 선린의 터에 대한 정보 N이 주어진다.

[출력]
- 선린의 터의 번호를 한 줄에 하나씩 차례대로 출력

[구현방법]
- 이게 대체 뭔 소릴 하는건가 몇번을 읽었는지 모르겠다
- 예시를 들어줄거면 1, 3 , 4, 9, 10, 12... 이런식으로 전부 가능한 조합을 처음부터 전부 얘기해줘야지 대뜸 3^4까지 잘 가다가 거기서만 3^2 + 3^3 해버리니까 내가 잘 이해를 못하나 싶었음;;;
- [1740, 거듭제곱] 먼저 풀고 공부해보니 비트마스킹을 써서, 비트 연산자를 써서 쉽게 구하는 방법이 있었다
- 바로 N을 1과 and 연산을 하고 그 값이 1인지 확인하는 것이다.
- 이게 무슨 의미냐면 1101이 있다고 가정할 때, 2진수인 1(0001)과 and 연산을 하면 끝자리 하나만 나온다.
- 그렇게되면 1101의 끝자리가 1인지 아닌지에 따라 현 2진수 위치의 0, 1 여부를 알 수 있는 것이다.
- 그러고나면 >> 연산을 통해 오른쪽으로 한칸 이동하여서 110을 만들고 이 작업을 1101의 마지막까지 확인하는 작업을 반복하면 된다.

[보완점]
- 모든 형은 통일해주자 threePower를 int형으로 해놨다가 답이 안나온 이슈가 있었음...
- StringBuilder 쓰면 시간 많이 줄어드려나 경우의 수 많을수록 빨라져서 유의마할 거 같은데 (아차차 줄바꿈..)
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            long N = Long.parseLong(br.readLine());
            long threePower = 1;
            long result = 0;

            while (0 < N) {
                if ((N & 1) == 1) result += threePower;
                threePower *= 3;
                N = N >> 1;
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}