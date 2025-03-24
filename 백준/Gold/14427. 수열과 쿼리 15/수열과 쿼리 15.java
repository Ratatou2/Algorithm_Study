/*
[백준]
14427, 수열과 쿼리 15

[문제파악]
- 길이가 N인 수열 A1, A2, ..., AN이 주어진다.
- 이때, 다음 쿼리를 수행하는 프로그램을 작성하시오.
    1 i v : Ai를 v로 바꾼다. (1 ≤ i ≤ N, 1 ≤ v ≤ 109)
    2 : 수열에서 크기가 가장 작은 값의 인덱스를 출력한다. 그러한 값이 여러개인 경우에는 인덱스가 작은 것을 출력한다.
- 수열의 인덱스는 1부터 시작한다.

[입력]
- 첫째 줄에 수열의 크기 N이 주어진다. (1 ≤ N ≤ 100,000)
- 둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 109)
- 셋째 줄에는 쿼리의 개수 M이 주어진다. (1 ≤ M ≤ 100,000)
- 넷째 줄부터 M개의 줄에는 쿼리가 주어진다.

[출력]
- 2번 쿼리에 대해서 정답을 한 줄에 하나씩 순서대로 출력한다.

[구현방법]
- 흠.. 일단 i를 받아서 값을 V로 바꾸려면, 인덱싱이 되어있어야 한다.
- 즉 인덱스에 매칭되는 값을 저장하는 자료구조가 하나 필요하단 말씀 -> 이거는 배열이나 Map을 쓸 수 잇겠지 (= O(1))
- 그러면... PQ를 하나 만들어서 얘는.. 값을 저장해야할 것 같은데 흠
- 글면 값을 저장해두고 업데이트하는 배열 하나랑, 조건에 따라 정렬해둘 PQ 만들면 되겠다
- PQ에서 꺼냈는데 저장 및 업데이트된 내역이랑 다르면 그냥 꺼내서 버리면 되니까
    - 아 이부분을 틀렸는데 꺼내서 버리는게 아니다. 꺼내서 확인을 하는 것이다 조심!!
    - 저장 & 업데이트 내역과 다를 경우, 그때 버리면 된다

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class NumInfo implements Comparable<NumInfo>{
        int index, value;

        NumInfo(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return "NumInfo [index=" + index + ", value=" + value + "]";
        }

        @Override
        public int compareTo(NumInfo o) {
            if (this.value == o.value) return this.index - o.index;
            return this.value - o.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N + 1];
        PriorityQueue<NumInfo> pq = new PriorityQueue<>();

        // 초기 입력값 세팅
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            pq.add(new NumInfo(i, nums[i]));
        }

        // 쿼리 갯수
        int M = Integer.parseInt(br.readLine());

        // 쿼리문 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            // 1번이 들어오면 index의 값을 바꿔준다
            if (command == 1) {
                int index = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());

                nums[index] = num;  // 점수 교체
                pq.add(new NumInfo(index, num));  // PQ에도 업데이트된 값 추가
            } else {
                // 가장 작은 값의 인덱스를 출력하는 명령어 (= 2)
                while (!pq.isEmpty()) {
                    NumInfo temp = pq.peek();  // 가장 첫번째 값 꺼냄

                    // PQ에 들어있는 값이 업데이트 되어있을 값과 다르면, 예전 값이란 소리니까 패스하고 다시 꺼내서 체크한다
                    if (nums[temp.index] != temp.value) {
                        pq.poll();
                        continue;
                    }

                    sb.append(temp.index + "\n");
                    break;
                }
            }
        }

        System.out.println(sb);
    }
}