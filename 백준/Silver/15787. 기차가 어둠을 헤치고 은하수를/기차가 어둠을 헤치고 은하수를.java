/*
[백준]
15787, 기차가 어둠을 헤치고 은하수를

[문제파악]
N개의 기차가 어둠을 헤치고 은하수를 건너려고 한다.
기차는 20개의 일렬로 된 좌석이 있고, 한 개의 좌석에는 한 명의 사람이 탈 수 있다.
기차의 번호를 1번부터 N번으로 매길 때, 어떠한 기차에 대하여 M개의 명령이 주어진다.
명령의 종류는 4가지로 다음과 같다.

1 i x : i번째 기차에(1 ≤ i ≤ N) x번째 좌석에(1 ≤ x ≤ 20) 사람을 태워라.
    - 이미 사람이 타있다면 , 아무런 행동을 하지 않는다.
2 i x : i번째 기차에 x번째 좌석에 앉은 사람은 하차한다.
    - 만약 아무도 그자리에 앉아있지 않았다면, 아무런 행동을 하지 않는다.
3 i : i번째 기차에 앉아있는 승객들이 모두 한칸씩 뒤로간다.
    - k번째 앉은 사람은 k+1번째로 이동하여 앉는다.
    - 만약 20번째 자리에 사람이 앉아있었다면 그 사람은 이 명령 후에 하차한다.
4 i : i번째 기차에 앉아있는 승객들이 모두 한칸씩 앞으로간다.
    - k번째 앉은 사람은 k-1 번째 자리로 이동하여 앉는다.
    - 만약 1번째 자리에 사람이 앉아있었다면 그 사람은 이 명령 후에 하차한다.
M번의 명령 후에 1번째 기차부터 순서대로 한 기차씩 은하수를 건너는데 조건이 있다.

기차는 순서대로 지나가며 기차가 지나갈 때 승객이 앉은 상태를 목록에 기록하며 이미 목록에 존재하는 기록이라면 해당 기차는 은하수를 건널 수 없다.

예를 들면, 다음 그림을 예로 들었을 때, 1번째 기차와 같이 승객이 앉은 상태는 기록되지 않았기 때문에 은하수를 건널 수있다.
2번째 기차와 같은 상태도 기록되지 않았기 때문에 2번째 기차도 은하수를 건널 수 있다.
3번째 기차는 1번째 기차와 승객이 앉은 상태가 같으므로 은하수를 건널 수 없다.
처음에 주어지는 기차에는 아무도 사람이 타지 않는다.
은하수를 건널 수 있는 기차의 수를 출력하시오.

[입력]
입력의 첫째 줄에 기차의 수 N(1 ≤ N ≤ 100000)과 명령의 수 M(1 ≤ M ≤ 100000)가 주어진다.
이후 두 번째 줄부터 M+1번째 줄까지 각 줄에 명령이 주어진다.

[출력]
은하수를 건널 수 있는 기차의 수를 출력하시오.

[구현방법]
- 비트마스킹으로 푼다
- 명령의 종류를 구분하고
- 명령 3, 4에서는 기차 번호만 주어지므로 좌석 번호를 파싱하지 않아야 함
- 명령 3에서는 왼쪽 시프트 후 20번째 비트를 제거해야 함
- 디버그용 출력문은 제거해야 함

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 기차의 수
        int M = Integer.parseInt(st.nextToken());  // 명령의 수
        int[] trains = new int[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int order = Integer.parseInt(st.nextToken());
            
            // 기차 사람 배치
            switch (order) {
                case 1:
                    // 1 i x : i번째 기차에(1 ≤ i ≤ N) x번째 좌석에(1 ≤ x ≤ 20) 사람을 태워라.
                    int trainIndex1 = Integer.parseInt(st.nextToken()) - 1;  // 기차 인덱스
                    int seatNum1 = Integer.parseInt(st.nextToken()) - 1;     // 좌석 번호도 인덱스로 계산해야하니까 -1
                    trains[trainIndex1] |= (1 << seatNum1);
                    break;
                case 2:
                    // 2 i x : i번째 기차에 x번째 좌석에 앉은 사람은 하차
                    int trainIndex2 = Integer.parseInt(st.nextToken()) - 1;  // 기차 인덱스
                    int seatNum2 = Integer.parseInt(st.nextToken()) - 1;
                    trains[trainIndex2] &= ~(1 << seatNum2);
                    break;
                case 3:
                    // 3 i : i번째 기차에 앉아있는 승객들이 모두 한칸씩 뒤로 간다.
                    int trainIndex3 = Integer.parseInt(st.nextToken()) - 1;  // 기차 인덱스
                    trains[trainIndex3] = (trains[trainIndex3] << 1) & ((1 << 20) - 1);  // 좌석 20개 넘어가는 비트 제거
                    break;
                case 4:
                    // 4 i : i번째 기차에 앉아있는 승객들이 모두 한칸씩 앞으로 간다.
                    int trainIndex4 = Integer.parseInt(st.nextToken()) - 1;  // 기차 인덱스
                    trains[trainIndex4] >>= 1;
                    break;
            }
        }

        // 중복 기차 있는지 체크
        // Set은 중복 제거를 해주니까, 그냥 있는지 뭔지 확인할 필요 없이 그냥 밀어넣으면 된다
        Set<Integer> passTrains = new HashSet<>();
        for (int temp : trains) {
            passTrains.add(temp);
        }

        System.out.println(passTrains.size());
    }
}