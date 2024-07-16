package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 백준_11286_AbsoluteHeap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 정렬조건 직접 편집해서 줘야함
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int absO1 = Math.abs(o1);
                int absO2 = Math.abs(o2);

                // 절대값으로 순서 구분 (먼젓번 절대값이 더 크면 자리 바꿈(양수))
                if (absO2 < absO1) return absO1 - absO2;
                else if (absO1 == absO2) {  // 절대값 같으면 값 자체가 더 작은 값이 먼저 (= 음수 앞으로)
                    return o1 - o2;  // (양수 - 음수) => 양수 출력될테니 자리 바꿈
                }
                return -1;  // 음수나 0이면 위치 안바꿀거임
            }
        });

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x != 0) minHeap.add(x);
            else {
                if (minHeap.peek() != null) System.out.println(minHeap.poll());
                else {
                    System.out.println(0);
                    minHeap.poll();
                }
            }
        }
    }
}

// 실수한 부분
// 비교하고 정렬하는건 기본적으로 PriorityQueue에 다 구현되어있음
// 근데 이제 우리 조건은 두가지가 존재함
// 1. 절대값 비교
// 2. 절대값이 같은 값있을 경우엔 부호 확인
// 이 두 조건은 전에 배운대로 compare 수정해서 해야함
// 정렬 기준 만드는건 이미 정해져있고 그 안에 조건을 수정해야함

// 한번 더 짚고 넘어가는 comapare
// 음수나 0이면 위치 안바꿀거임
// 양수일 때만 두가지 위치 바꾸는 경우