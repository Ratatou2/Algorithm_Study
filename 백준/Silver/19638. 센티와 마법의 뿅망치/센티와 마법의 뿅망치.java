/*
[백준]
19638, 센티와 마법의 뿅망치

[문제파악]
- 센티는 마법 도구들을 지니고 여행을 떠나는 것이 취미인 악당이다.
- 거인의 나라에 도착한 센티는 자신보다 키가 크거나 같은 거인들이 있다는 사실이 마음에 들지 않았다.
- 센티가 꺼내 들은 마법 도구는 바로 마법의 뿅망치로, 이 뿅망치에 맞은 사람의 키가 ⌊ 뿅망치에 맞은 사람의 키 / 2 ⌋로 변하는 마법 도구이다.
- 단, 키가 1인 경우 더 줄어들 수가 없어 뿅망치의 영향을 받지 않는다.
- 하지만 마법의 뿅망치는 횟수 제한이 있다.
- 그래서 센티는 마법의 뿅망치를 효율적으로 사용하기 위한 전략을 수립했다.
- 바로 매번 가장 키가 큰 거인 가운데 하나를 때리는 것이다.
- 과연 센티가 수립한 전략에 맞게 마법의 뿅망치를 이용한다면 거인의 나라의 모든 거인이 센티보다 키가 작도록 할 수 있을까?

[입력]
- 첫 번째 줄에는 센티를 제외한 거인의 나라의 인구수 N (1 ≤ N ≤ 105)과 센티의 키를 나타내는 정수 Hcenti (1 ≤ Hcenti ≤ 2 × 109), 마법의 뿅망치의 횟수 제한 T (1 ≤ T ≤ 105)가 빈칸을 사이에 두고 주어진다.
- 두 번째 줄부터 N개의 줄에 각 거인의 키를 나타내는 정수 H (1 ≤ H ≤ 2 × 109)가 주어진다.

[출력]
- 마법의 뿅망치를 센티의 전략대로 이용하여 거인의 나라의 모든 거인이 센티보다 키가 작도록 할 수 있는 경우,
    첫 번째 줄에 YES를 출력하고,
    두 번째 줄에 마법의 뿅망치를 최소로 사용한 횟수를 출력한다.
- 마법의 뿅망치를 센티의 전략대로 남은 횟수 전부 이용하고도 거인의 나라에서 센티보다 키가 크거나 같은 거인이 있는 경우,
    첫 번째 줄에 NO를 출력하고,
    두 번째 줄에 마법의 뿅망치 사용 이후 거인의 나라에서 키가 가장 큰 거인의 키를 출력한다.

[구현방법]
- PQ를 역정렬로 만들어서 거인 다 때려넣고 뿅망치 횟수만큼 꺼내서 1/2로 만들고 다시 PQ에 넣는다
- 이 상태를 반복할 경우 PQ 맨 앞엔 항상 거인 나라에서 제일 큰놈만 있게 되고, 마지막에 성공여부 체크도 첫번째 거인만 꺼내서 확인하면 된다

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 거인 인구수
        int H = Integer.parseInt(st.nextToken());  // 센티의 키
        int T = Integer.parseInt(st.nextToken());  // 뿅망치 횟수 제한

        // 거인 입력 받기
        PriorityQueue<Integer> giants = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            giants.add(Integer.parseInt(br.readLine()));
        }

        // 뿅망치 휘두르기
        int swingCount = 0;
        for (int i = 0; i < T; i++) {
            int tallestGiant = giants.peek();

            // 혹시나 제일 큰 거인 키가 1이거나, 센티보다 작아졌다면 더 볼 것 없다
            if (tallestGiant == 1 || tallestGiant < H) break;

            giants.add(giants.poll() / 2);
            swingCount++;
        }

        StringBuilder sb = new StringBuilder();
        String answer;
        int result;
        if (giants.peek() < H) {
            answer = "YES\n";
            result = swingCount;
        } else {
            answer = "NO\n";
            result = giants.peek();
        }

        sb.append(answer);
        sb.append(result);
        System.out.println(sb);
    }
}