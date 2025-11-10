

/*
[백준]
1744, 수묶기

[문제파악]
길이가 N인 수열이 주어졌을 때, 그 수열의 합을 구하려고 한다.
하지만, 그냥 그 수열의 합을 모두 더해서 구하는 것이 아니라, 수열의 두 수를 묶으려고 한다.
어떤 수를 묶으려고 할 때, 위치에 상관없이 묶을 수 있다.
하지만, 같은 위치에 있는 수(자기 자신)를 묶는 것은 불가능하다.
그리고 어떤 수를 묶게 되면, 수열의 합을 구할 때 묶은 수는 서로 곱한 후에 더한다.
예를 들면, 어떤 수열이 {0, 1, 2, 4, 3, 5}일 때, 그냥 이 수열의 합을 구하면 0+1+2+4+3+5 = 15이다.
하지만, 2와 3을 묶고, 4와 5를 묶게 되면, 0+1+(2*3)+(4*5) = 27이 되어 최대가 된다.
수열의 모든 수는 단 한번만 묶거나, 아니면 묶지 않아야한다.
수열이 주어졌을 때, 수열의 각 수를 적절히 묶었을 때, 그 합이 최대가 되게 하는 프로그램을 작성하시오.

[입력]
첫째 줄에 수열의 크기 N이 주어진다.
N은 50보다 작은 자연수이다.
둘째 줄부터 N개의 줄에 수열의 각 수가 주어진다.
수열의 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.

[출력]
수를 합이 최대가 나오게 묶었을 때 합을 출력한다.
정답은 항상 231보다 작다.

[구현방법]
- 그냥 그리디로 무조건 큰 두수끼리 짝맞춰 곱하고 하나라도 0보다 작으면 곱하지 않는 방식으로 풀면 빠르게 답을 구할 수 있을 것 같다
- 아 물론 -1이 2개 이상이면 끼리끼리 묶으면 될듯
- 결국 양쪽에서 포인터를 시작해서 두개씩 묶으면서 가운데로 수렴하다가 0이하인 숫자가 하나인 경우 곱하지 않고 그냥 두는 것으로 해야겠다
- 예외 사항은 마지막 숫자 2개가 0과 음수일 경우 곱하는 것으로 진행
- 오 쪼끔 더 나아가면 처음부터 숫자를 입력받을 때 3종류로 구분하면 더 좋을 것 같다

- List랑 PQ랑 속도차이 많이 날까 궁금해짐 (List는 get이 순차탐색이라 N만큼 걸리니까)

[보완점]
- 오... 1이 두개면 곱하는 것보단 더하는게 낫다
- 아 1은 하나라도 있으면 곱하면 1을 손해보니까 하나라도 1이면 그냥 더하는게 낫다
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> plus = new ArrayList<>();
        List<Integer> minus = new ArrayList<>();
        //PriorityQueue<Integer> plus = new PriorityQueue<>(Comparator.reverseOrder());

        int N = Integer.parseInt(br.readLine());

        // 숫자 입력 받기
        for (int i = 0; i < N; i++) {
            int curr = Integer.parseInt(br.readLine());

            if (0 < curr) plus.add(curr);
            else minus.add(curr);
        }

        // plus는 가장 큰 수부터, minus는 가장 작은 수부터 나오도록 세팅
        Collections.sort(plus, Collections.reverseOrder());
        Collections.sort(minus);

        int result = 0;
        for (int i = 0; i < plus.size() - 1; i = i + 2) {
            // 예외사항: 1이 두개면 그 둘은 더하는 것이 낫다
            int A = plus.get(i);
            int B = plus.get(i + 1);
            result += (A == 1 || B == 1) ? A + B : A * B;
        }

        // plus 갯수가 나누어 떨어지지 않는다면 맨 마지막 숫자는 그냥 더해준다
        if (plus.size() % 2 != 0) {
            result += plus.get(plus.size() - 1);
        }

        for (int i = 0; i < minus.size() - 1; i = i + 2) {
            result += minus.get(i) * minus.get(i + 1);
        }

        // plus 갯수가 나누어 떨어지지 않는다면 맨 마지막 숫자는 그냥 더해준다
        if (minus.size() % 2 != 0) {
            result += minus.get(minus.size() - 1);
        }

        System.out.println(result);
    }
}
