/*
[백준]
4312, 3의 제곱

[문제파악]
- 3의 제곱수가 들어있는 집합
	- S = {1, 3, 9, 27, 81, ...}
- S의 모든 부분집합을 구한 뒤, 이 집합을 가지고 있는 원소값의 합으로 오름차순 정렬
- n번째 집합을 구하라

[입력]
- 입력은 여러개의 줄로 구성되어있고, 각 줄에는 n이 주어진다
- n은 19자리를 넘지 않는 양의 정수이다 (1,000,000,000,000,000,000)
- 입력의 마지막 줄에는 0이 있다

[출력]
각 입력에 대해서, n번째 집합을 에제 출력 형식과 같게 출력할 것

[구현방법]
- 이것도 조합을 2진수로 짜면 됨
- 조합에 사용했고 안했고, 그리고 제곱수 구할 땐 비트마스킹 써서 구하고
- N은 -1을 해줘야한다. 주어지는 것은 0을 제외한 순서이고, 내가 구하는 것은 index이기 때문이다

[보완점]
- 이거 굉장히 큰 수라서 BigInteger를 써야함.
- 그리고 BigInteger는 일반적으로 int, long 쓰듯이 쓰는게 아니라 메서드가 다 따로있음 (multiply 같은 것들)
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static StringBuilder makeBinarySet(long N) {
        StringBuilder sb = new StringBuilder();
        BigInteger threePower = BigInteger.ONE;  // BigInteger로 초기화
        sb.append("{ ");

        while (N > 0) {
            boolean checkEnd = false;

            // N의 가장 낮은 비트가 1인지 확인
            if ((N & 1) == 1) {
                sb.append(threePower);
                checkEnd = true;
            }

            // threePower를 3으로 곱함
            threePower = threePower.multiply(BigInteger.valueOf(3));
            // N을 오른쪽으로 한 비트 이동 (2로 나누기)
            N = N >> 1;

            String endString = N > 0 ? ", " : " ";
            if (checkEnd) sb.append(endString);
        }

        sb.append("}");

        return sb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int threePower = 1;

        while (true) {
            StringBuilder oneLine = new StringBuilder();
            String temp = br.readLine();
            long N = Long.parseUnsignedLong(temp);
            if (temp.isEmpty() | temp.equals("0") | N == 0) break;

            oneLine = makeBinarySet(N-1);

            answer.append(oneLine).append("\n");
        }

        System.out.println(answer);
    }
}