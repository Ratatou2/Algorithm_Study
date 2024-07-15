/*
[백준]
20115, 에너지 드링크

[문제파악]
- 에너지 드링크 합칠거고 그 방법은 아래와 같다
- 임의의 서로 다른 두 에너지 드링크를 고른다
- 한쪽의 에너지 드링크를 반대쪽에 모두 붓는다
- 단, 붓는 과정에서 원래 양의 절반은 바닥에 흘리게 된다
- 다 붓고 남은 빈 에너지 드링크는 버린다
- 위 과정을 에너지 드링크가 하나만 남을 때까지 반복한다
- 합쳐진 에너지 드링크의 양을 최대로 하려고 한다

[입력]
- 첫째 줄에 에너지 드링크의 수 N (2 <= N <= 10^5)
- 둘째 줄에 각 에너지 드링크의 양이 공백으로 주어짐
	- i번째 정수 x, (1 <= x <= 10^9)는 에너지 드링크의 i의 양이 x_i임을 의미

[출력]
- 최대로 만들 수 있는 에너지 드링크의 양을 출력
- 절대/상대 오차는 10^-5까지 허용

[구현방법]
- 다 붓고 남은 빈 것은 버린다는게 해당 캔은 더이상 사용하지 않는다 (거기에 붓지 않는다는 의미였음)
- 가장 max 캔에다가 에다가 min 짜리를 계속 붓는 방법이 베스트인듯
- 붓는 쪽은 계속 절반을 버려야하기 때문

[보완점]
- 소숫점 유무에 따른 결과 출력이 달라야 함을 조심할 것 (절대/상대 오차는 10-5까지 허용한다 -> 크게 상관없을듯)
- xi의 최대값은 1,000,000,000이고 n의 최대값은 100,000이라 float는 32비트만 나타낼 수 있어서 오버플로가 발생함.
- 이런 놓치기 쉬운 부분 디테일 신경쓰자 ㅠ
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        double[] cans = new double[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            cans[i] = Integer.parseInt(st.nextToken());
        }

//        System.out.println(Arrays.toString(cans));
        Arrays.sort(cans);  // 오름차순 정렬
//        System.out.println(Arrays.toString(cans));

        double totalAmount = cans[N-1];  // 정렬상태에선 가장 마지막이 큰 사이즈의 캔
//        System.out.println(totalAmount);
        for (int i = 0; i < N - 1; i++) {
            double half = cans[i]/2;
//            System.out.println(half);
            totalAmount += half;
        }

        System.out.println(totalAmount);
    }
}