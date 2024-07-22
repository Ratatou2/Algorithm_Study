/*
[백준]
1654, 랜선 자르기

[문제파악]
- K개의 랜선을 가지고 있다 (단, 길이는 제각각)
- 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶어 K개의 랜선을 잘라서 만들어야 한다
	ex) 300cm 랜선에서 140cm 랜선을 두개 잘라내면 20cm는 버려야한다 (이미 자른 랜선은 붙일 수 없다)
- 자를거나 만들 때 손실되는 길이가 없다면, 기존의 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다
- 자를 때는 항상 cm 단위로 정수 길이만큼 자른다
- N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다
- 이때, 만들 수 있는 최대 랜선의 길이를 구하라

[입력]
- 첫째줄에는 랜선의 갯수 K, 필요한 랜선의 갯수 N
	- 1 <= K <= 10,000, 정수
	- 항상 K <= N
- 둘째줄부터 K줄에 걸쳐 이미 가지고 있는 랜선의 길이가 cm 단위의 정수로 입력됨
	- 1 <= 랜선의 길이 <= 2^31-1, 자연수

[출력]
- 첫째줄에 N개를 만들 수 있는 랜선의 최대 길이를 cm 단위 정수로 출력

[구현방법]
- 이것도 min, max 두가지 조여가며 이진 트리로 최댓값 구해야할듯
- 중간에 기능 넣는 것도 이번엔 곧잘 해냈음
- 용돈관리랑 비슷한 문제

[보완점]
- 가장 작은 길이가 정답과 관련이 없을 수도 있다는 것이 내가 놓친 부분
- 무슨 의미냐면 주어진 랜선 길이들이 300, 600, 700, 800일 때, 최대 길이 3개를 원한다면 그 정답은 600이다
- 그렇다면 Lan의 길이는 현재 랜선의 최솟값이 아닌 현재 랜선의 최댓값이어야 함!
- 또한 시간을 단축하려면 min을 ++로 한개씩 늘려갈게 아니라 mid를 할당하는 로직으로 변경 필요
- 랜선의 최대 길이를 조심해야한다 (최대 길이는 2^31-1인데 두개 다 더하면 int 범위 초과)
- 런타임에러 (by zero) -> 랜선은 자연수범위다, 그 말인 즉, 최소값인 min은 1에서 시작해야한다
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] lans;

    static long countLan(long length) {
        long count = 0;

        for (int lan : lans) {
            count += (int) (lan / length);
        }

        return count;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        lans = new int[K];
        long min = 1;
        long max = Integer.MIN_VALUE;

        for (int i = 0; i < K; i++) {
            lans[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, lans[i]);
        }

        while (min <= max) {
            long mid = (min + max) / 2;  // 랜선의 최대 길이를 조심해야한다 (최대 길이는 2^31-1인데 두개 다 더하면 int 범위 초과)

            // 조건을 만족하면 min값을 늘린다 (평균값을 높여 최대 길이 Lan을 구하기 위함)
            if (N <= countLan(mid)) { // N개보다 많이 만드는 것도 N개를 만드는 것에 포함이기 때문에 '<=' 임
                min = mid + 1;
            } else {  // 조건을 만족하지 못하면, 평균 길이를 줄여야 함 (= 최대길이를 줄여서 평균을 줄인다)
                max = mid - 1;
            }
        }

        System.out.println(max);
    }
}