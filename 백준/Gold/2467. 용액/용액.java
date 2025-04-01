/*
[백준]
2467, 용액

[문제파악]
- KOI 부설 과학연구소에서는 많은 종류의 산성 용액과 알칼리성 용액을 보유하고 있다.
- 각 용액에는 그 용액의 특성을 나타내는 하나의 정수가 주어져있다.
- 산성 용액의 특성값은 1부터 1,000,000,000까지의 양의 정수로 나타내고, 알칼리성 용액의 특성값은 -1부터 -1,000,000,000까지의 음의 정수로 나타낸다.
- 같은 양의 두 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의한다.
- 이 연구소에서는 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다.
- 예를 들어, 주어진 용액들의 특성값이 [-99, -2, -1, 4, 98]인 경우에는 특성값이 -99인 용액과 특성값이 98인 용액을 혼합하면 특성값이 -1인 용액을 만들 수 있고, 이 용액의 특성값이 0에 가장 가까운 용액이다.
- 참고로, 두 종류의 알칼리성 용액만으로나 혹은 두 종류의 산성 용액만으로 특성값이 0에 가장 가까운 혼합 용액을 만드는 경우도 존재할 수 있다.
- 산성 용액과 알칼리성 용액의 특성값이 정렬된 순서로 주어졌을 때, 이 중 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾는 프로그램을 작성하시오.

[입력]
- 첫째 줄에는 전체 용액의 수 N이 입력된다.
- N은 2 이상 100,000 이하의 정수이다.
- 둘째 줄에는 용액의 특성값을 나타내는 N개의 정수가 빈칸을 사이에 두고 오름차순으로 입력되며, 이 수들은 모두 -1,000,000,000 이상 1,000,000,000 이하이다.
- N개의 용액들의 특성값은 모두 서로 다르고, 산성 용액만으로나 알칼리성 용액만으로 입력이 주어지는 경우도 있을 수 있다.

[출력]
- 첫째 줄에 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값을 출력한다.
- 출력해야 하는 두 용액은 특성값의 오름차순으로 출력한다.
- 특성값이 0에 가장 가까운 용액을 만들어내는 경우가 두 개 이상일 경우에는 그 중 아무것이나 하나를 출력한다.

[구현방법]
- 풀기가 싫은건가 대체 어떻게 접근해야하는질 모르겠네 생각이 안남
- 투 포인터까진 알겠어 계속 값을 조여가야하니까 근데 그 둘을 어느 때 어느쪽을 움직여야할지를 모르겠음...
- 그리면서 해보니까 좀 알겠는게 (맞는진 모르겠지만..), 더해서 0보다 크면 오른쪽 포인터를 줄이고, 0보다 작으면 왼쪽 포인터를 키우면 됨

[보완점]
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] liquids = new int[N];

        // 입력값 저장하기
        for (int i = 0; i < N; i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }

        // 인덱스 포인터
        int start = 0;
        int end = N - 1;
        int min = Integer.MAX_VALUE;
        int[] record = new int[2];
        while (start < end) {
            int temp = liquids[start] + liquids[end];

            // 차이가 0에 도달했으면 더 볼 것 없다
            if (temp == 0) {
                record[0] = liquids[start];
                record[1] = liquids[end];
                break;
            }

            // 최솟값 갱신 및 저장
            // 현재 더 한 값의 절댓값이 min보다 작으면서 0보단 클 때, 값을 갱신한다
            // 0 == temp는 이미 위에서 처리했으니 여기까지 올 수가 없어서 0보다 큰 값으로 계산했음
            // 또한 -1 같은 경우도 있을 수 있겠지만 그런 경우를 미연에 방지하고자 위에서 abs로 절댓값 처리하였음.
            int tempAbs = Math.abs(temp);
            if (0 < tempAbs && tempAbs <= min) {
                record[0] = liquids[start];
                record[1] = liquids[end];
                min = tempAbs;
            }

            // 둘이 더한 값에 따라 포인터 이동
            // 0보다 크면, 큰 값을 작게(end-1) 만들고, 0보다 작으면, 작은 값을 크게(start+1) 만든다
            if (0 < temp) end = end - 1;
            else start = start + 1;
        }

        System.out.println(record[0] + " " + record[1]);
    }
}