/*
[백준]
12015, 가장 긴 증가하는 부분 수열 2

[문제파악]
- 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
- 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.

[입력]
- 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
- 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000,000)

[출력]
- 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

[구현방법]
- 이걸.. 어떻게 풀어야할까 아무래도, left & right 끝자락을 잡고 두 수보다 작거나 큰 것중에 min값을 찾아야할 것 같다
- 정렬하지 말고 이 상태로 진행해야할테고.. 근데 그러면 이분탐색을 어떻게 하지?
- 정렬해두고...?
- 이 부분은 이 유형을 풀어본적이 없어서 감이 안잡혀서 풀이 방식 힌트만 봤다 (이것역시 LIS, LCS 류... 공부하고 외울건 외워야겠지...?)
- 아무튼 요지는 실제로 LIS를 만드는게 아니라 길이만 유지하는 것을 찾으면 된단다
    - 새 숫자를 입력 받으면?
        1) 현재 숫자가 배열의 마지막보다 크면 append
        2) 작거나 같다면 해당 숫자가 들어갈 수 있는 위치를 이분탐색해서 교체
- 가만히 읽다보니 위 조건에 따르면 아래 같은 상황은 해결 불가능 아닌가? 싶었다
    - 10, 20, 60, 40, 50이 들어왔을 때, 60이 들어오면 10, 20, 60인 상태임
    - 이 때, 40, 50도 작거나 같다면 이분탐색해서 교체하니까 추가는 됨
    - 근데 배열 길이는... 안맞는거 아닌가?
    - ㄴㄴ 다 맞음
    - 보면 10, 20, 60 상태이지만, 40이 들어오면 동작하는 방식의 핵심은 '기존값과 교체'임
    - 즉 40이 들어갈 수 있는 위치는 20보다 크고 60보다 작으므로 60 위치에 교환하는 것임
    - 10, 20, 40이 될 것이고, 그러면 50이 들어올 때, 끝 값이 60일 때와 달리 50도 추가할 수 있다는 것
- 결국 문제없이 최대길이의 LIS를 구할 수 있다!

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Integer> LIS;

    static int findLowerBound(int num) {
        int result = - 1;

        int left = 0;
        int right = LIS.size();

        while (left < right) {
            int mid = (left + right) / 2;

            // 이제 num이 교체될 수 있는 가장 작은 값을 찾을 것이다
            if (num <= LIS.get(mid)) {
                right = mid;
                result = mid;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        LIS = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            int curr = Integer.parseInt(st.nextToken());

            // 리스트가 비어 있거나, 끝에 있는 숫자가 curr보다 작으면 그냥 입력한다
            if (LIS.isEmpty() || LIS.get(LIS.size() - 1) < curr) {
                LIS.add(curr);
            } else {  // 그게 아니면, 들어갈만한 위치 찾아서 교체
                int temp = findLowerBound(curr);
                if (temp != -1) LIS.set(temp, curr);
            }
        }

        System.out.println(LIS.size());
    }
}