/*
[백준]
7795, 먹을 것인가 먹힐 것인가

[문제파악]
- 심해에는 두 종류의 생명체 A와 B가 존재한다. A는 B를 먹는다.
- A는 자기보다 크기가 작은 먹이만 먹을 수 있다.
- 예를 들어, A의 크기가 {8, 1, 7, 3, 1}이고, B의 크기가 {3, 6, 1}인 경우에 A가 B를 먹을 수 있는 쌍의 개수는 7가지가 있다.
    8-3, 8-6, 8-1, 7-3, 7-6, 7-1, 3-1.
- 두 생명체 A와 B의 크기가 주어졌을 때, A의 크기가 B보다 큰 쌍이 몇 개나 있는지 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 테스트 케이스의 개수 T가 주어진다.
- 각 테스트 케이스의 첫째 줄에는 A의 수 N과 B의 수 M이 주어진다.
- 둘째 줄에는 A의 크기가 모두 주어지며, 셋째 줄에는 B의 크기가 모두 주어진다.
- 크기는 양의 정수이다. (1 ≤ N, M ≤ 20,000)

[출력]
- 각 테스트 케이스마다, A가 B보다 큰 쌍의 개수를 출력한다.

[구현방법]
- A, B 모두 정렬한다음, A 하나씩 잡고 B의 중앙값부터 탐색을 하는 것이지
- 그렇게 자기랑 같거나 큰 것을 만나는 지점까지 이동하고, 만나는 순간 해당 지점의 indexOf를 구해서 totalCount에 더하는 것이다

[보완점]
- 이분 탐색은 start <= end까지만 하면 된다 (가만 생각해보면 너무 당연하죠? 중간값으로 탐색하는 것이니까)
- 또한 start = mid + 1 / end = mid - 1이 되어야하는데 이는 start = end = mid로 해버리면 무한 루프에 빠질 수 있기 때문이다
    - start = 3 / end = 4이면 중앙값은 3으로, start가 3이 되면서 계속 무한루프에 빠진다 
 
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int count = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());

            int A_Count = Integer.parseInt(st.nextToken());
            int B_Count = Integer.parseInt(st.nextToken());

            int[] A = new int[A_Count];
            int[] B = new int[B_Count];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < A_Count; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < B_Count; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(A);
            Arrays.sort(B);

            // A랑 B 비교 시작
            for (int i = 0; i < A_Count; i++) {
                int start = 0;
                int end = B_Count - 1;
                int currA = A[i];

                // 이진 탐색을 통해 B에서 currA보다 작은 값의 개수 찾기
                while (start <= end) {
                    int mid = (start + end) / 2;

                    // 현재 값보다 중앙값이 크면 start를 mid보다 1크게 수정
                    if (B[mid] < currA) start = mid + 1;
                    else end = mid - 1;
                }

                // start는 currA보다 작은 B의 개수
                count += start;
            }

            System.out.println(count);
        }
    }
}