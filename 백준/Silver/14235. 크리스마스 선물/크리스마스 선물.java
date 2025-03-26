/*
[백준]
14235, 크리스마스 선물

[문제파악]
- 크리스마스에는 산타가 착한 아이들에게 선물을 나눠준다.
- 올해도 산타는 선물을 나눠주기 위해 많은 노력을 하고 있는데, 전세계를 돌아댕기며 착한 아이들에게 선물을 나눠줄 것이다.
- 하지만 산타의 썰매는 그렇게 크지 않기 때문에, 세계 곳곳에 거점들을 세워 그 곳을 방문하며 선물을 충전해 나갈 것이다.
- 또한, 착한 아이들을 만날 때마다 자신이 들고있는 가장 가치가 큰 선물 하나를 선물해 줄 것이다.
- 이제 산타가 선물을 나눠줄 것이다.
- 차례대로 방문한 아이들과 거점지의 정보들이 주어졌을 때, 아이들이 준 선물들의 가치들을 출력하시오.
- 만약 아이들에게 줄 선물이 없다면 -1을 출력하시오.

[입력]
- 첫 번째 줄에서는 아이들과 거점지를 방문한 횟수 n이 주어진다.(1≤n≤5,000)
- 다음 n줄에는 a가 들어오고, 그 다음 a개의 숫자가 들어온다.
- 이는 거점지에서 a개의 선물을 충전하는 것이고, 그 숫자들이 선물의 가치이다.
- 만약 a가 0이라면 거점지가 아닌 아이들을 만난 것이다.
- 선물의 가치는 100,000보다 작은 양의 정수이다.(1≤a≤100)

[출력]
- a가 0일 때마다, 아이들에게 준 선물의 가치를 출력하시오.
- 만약 줄 선물이 없다면 -1을 출력하라.
- 적어도 하나의 출력이 있음을 보장한다.

[구현방법]
- 이거는 그냥 조건 잘 세우면 된다
- PQ를 일단 역정렬로 만든다, 현재 자신이 들고 있는 선물 중 가장 큰 가치의 선물을 줄 것이니까
- 그리고 나면 PQ가 비어 있느냐 아니냐에 따라 선물을 주거나 -1을 출력하면 된다
- 또 a가 0이 아니면, a 갯수만큼 선물을 가지고 왔다는 의미니까 PQ에 넣으면 된다

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
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());

            // 애들을 만났다면
            if (a == 0) {
                if (pq.isEmpty()) sb.append(-1).append("\n");
                else sb.append(pq.poll()).append("\n");
            } else {
                for (int j = 0; j < a; j++) {
                    pq.add(Integer.parseInt(st.nextToken()));
                }
            }
        }

        System.out.println(sb);
    }
}