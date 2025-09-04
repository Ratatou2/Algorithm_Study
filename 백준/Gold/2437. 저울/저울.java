
/*
[백준]
2437, 저울

[문제파악]
하나의 양팔 저울을 이용하여 물건의 무게를 측정하려고 한다.
이 저울의 양 팔의 끝에는 물건이나 추를 올려놓는 접시가 달려 있고, 양팔의 길이는 같다.
또한, 저울의 한쪽에는 저울추들만 놓을 수 있고, 다른 쪽에는 무게를 측정하려는 물건만 올려놓을 수 있다.
무게가 양의 정수인 N개의 저울추가 주어질 때, 이 추들을 사용하여 측정할 수 없는 양의 정수 무게 중 최솟값을 구하는 프로그램을 작성하시오.
예를 들어, 무게가 각각 3, 1, 6, 2, 7, 30, 1인 7개의 저울추가 주어졌을 때, 이 추들로 측정할 수 없는 양의 정수 무게 중 최솟값은 21이다.

[입력]
첫 째 줄에는 저울추의 개수를 나타내는 양의 정수 N이 주어진다.
N은 1 이상 1,000 이하이다.
둘째 줄에는 저울추의 무게를 나타내는 N개의 양의 정수가 빈칸을 사이에 두고 주어진다.
각 추의 무게는 1이상 1,000,000 이하이다.


[출력]
첫째 줄에 주어진 추들로 측정할 수 없는 양의 정수 무게 중 최솟값을 출력한다.

[구현방법]
- 무게 추들로 만들 수 있는 모든 조합을 구한다
- 문제 조건에 추는 한쪽에만 올릴 수 있다하였으니 양쪽 각각에 올리는 조합을 구할 필요는 없다
- 이 상태에서 bool[(1,000 * 1,000,000) + 1]을 만들어 해당 무게를 만들면 true로 변경한다
- 그리고 이후에 모든 조합이 끝나면 bool[]를 index 1부터 for문을 돌며 false가 되는 제일 첫번째 지점을 찾아 출력한다
- 근데 이게 웃긴게... 수학적 해로 풀 수 있는 문제였다;;
    1) 추를 오름차순으로 정렬한다
    2) 오름차순의 추들을 하나씩 더해간다 (sum)
    3) 그런데 그 다음 추의 무게가 sum(여지껏 더한 추의 무게)을 초과하면? sum + 1이란 무게는 만들 수 없다
    4) 반면에 그 다음 추의 무게가 sum(여지껏 더한 추의 무게)보다 작거나 같으면? sum + 그 다음 추의 무게 까지 모든 무게를 만들 수 있다는 의미다

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st= new StringTokenizer(br.readLine());
        int[] weights = new int[N];

        // 입력 받은 무게 집어넣기
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        // 무게 오름차순 정렬
        Arrays.sort(weights);

        int sum = 0;
        for (int i = 0; i < N; i++) {
            // 현재 무게가 (sum+1)을 초과하면 sum+1을 만들 수 없다는 의미이다 (=정렬되어있는 상태니 정답의 조건을 찾은셈)
            if (sum + 1 < weights[i]) break;
            
            sum += weights[i];
        }

        System.out.println(sum + 1);
    }
}
