/*
[백준]
11508, 2+1 세일

[문제파악]
- 유제품 3개 구매시, 그중에 가장 싼 것은 무료
- 한번에 3개 구매하지 않을 시, 정가를 지불해야 함
- 7개의 유제품(10, 9, 4, 2, 6, 4, 3)을 구매하는 경우, (10, 3, 2), (4, 6, 4), (9) 3번에 걸쳐 물건을 산다면 첫꾸러미에서는 13원을, 두번째 꾸러미에서는 10원을, 세번째 꾸러미에서는 9월을 지불해야한다.
- N개의 유제품을 구매하려할 때, 최소 비용으로 유제품을 구입할 수 있도록 도와주자

[입력]
- 첫번째 줄에 유제품의 수 N개 (1<=C<=100,000)
- 두번째 줄에 각 유제품의 가격 C (1<=C<=100,000)

[출력]
- N개의 유제품을 모두 살떄 필요한 최소비용을 출력
- 정답은 2^31 - 1 보다 작거나 같다

[구현방법]
- 일단 정답은 2^31 - 1보다 작거나 같다니까 int로 해결 가능
- 3개 묶어서 가장 싼게 무료이면 오름차순해서 걍 제일 비싼거 3개씩 묶으면 될듯

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Integer[] dairyProduct = new Integer[N];  // 역행 순으로 쉽게 만들기 위해 Integer 선언

        // 값 받기
        for (int i = 0; i < N; i++) {
            dairyProduct[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(dairyProduct, Comparator.reverseOrder());

        int totalSum = 0;
        for (int i = 0; i < N; i++) {
            if ((i + 1) % 3 == 0) continue;  // 3개 중 가장 싼건 무료니까 3배수에 cotinue
            totalSum += dairyProduct[i];
        }

        System.out.println(totalSum);
    }
}