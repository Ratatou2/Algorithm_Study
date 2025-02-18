/*
[백준]
1446, 지름길

[문제파악]
- 매일 아침, 세준이는 학교에 가기 위해서 차를 타고 D킬로미터 길이의 고속도로를 지난다.
- 이 고속도로는 심각하게 커브가 많아서 정말 운전하기도 힘들다.
- 어느 날, 세준이는 이 고속도로에 지름길이 존재한다는 것을 알게 되었다.
- 모든 지름길은 일방통행이고, 고속도로를 역주행할 수는 없다.
- 세준이가 운전해야 하는 거리의 최솟값을 출력하시오.

[입력]
- 첫째 줄에 지름길의 개수 N과 고속도로의 길이 D가 주어진다.
- N은 12 이하인 양의 정수이고, D는 10,000보다 작거나 같은 자연수이다.
- 다음 N개의 줄에 지름길의 시작 위치, 도착 위치, 지름길의 길이가 주어진다.
- 모든 위치와 길이는 10,000보다 작거나 같은 음이 아닌 정수이다.
- 지름길의 시작 위치는 도착 위치보다 작다.

[출력]
- 세준이가 운전해야하는 거리의 최솟값을 출력하시오.

[구현방법]
- 우선 정렬해야하는 기준을 잘 정해야할 것 같다
- 이것의 요지는 지름길을 잘 쓰는 것인데 지름길이란 원래 가야하는 dis보다 지름길의 dis가 훨씬 더 짧은 것이 좋다
- 이 말인 즉, end-start 보다 dis가 압도적으로 작은 것 위주로 PQ로 정렬해서 써먹어야 한다는 의미
- List는 인접 리스트까진 필요 없겠고, 하나의 List에 정렬 기준을 잘 세워야할 것 같다

[보완점]
- 예제 3번이 좀 어려웠다... 1, 2까지는 단순히 효율을 compare 기준을 짜주는 것으로 해결했는데 단순히 효율만으로는...
- 그래서 결국 답안을 참조했는데 이게 왠걸 나는 1부터 D까지 하나씩 ArrayList 넣는게 이상할까봐 (비효율적일까봐) 그냥 지름길 갯수만큼만 넣었는데 여긴 다넣는다...
- 그리고는 어제 풀었던 다익스트라 문제랑 똑같이 진행됨...
- 그러니까 결국 각 노드에서 최적의 효율값으로 진행되는 것임
- 차라리 DP보다는 쉬운...데(?!) 여전히 어려워서 일단 비슷한 문제 더 도전해야할듯 ㅠ
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, D;  // N (지름길 갯수), D (고속도로 길이)
    static List<ShortCut> shortcuts;

    static class ShortCut implements Comparable<ShortCut> {
        int location, distance;

        public ShortCut(int location, int distance) {
            this.location = location;
            this.distance = distance;
        }

        @Override
        public int compareTo(ShortCut other) {
            return Integer.compare(this.distance, other.distance);
        }

        @Override
        public String toString() {
            return  "======================================" + "\n"
                    + "시작지점 : "  + location + "\n"
                    + "도착지점 : " + distance + "\n";
        }
    }

    public static void main(String[] args) throws Exception {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        // 0부터 D까지 각 위치를 하나의 노드로 생각하며 인접 리스트 초기화
        List<ShortCut>[] graph = new ArrayList[D + 1];
        for (int i = 0; i <= D; i++) {
            graph[i] = new ArrayList<>();
        }

        // 기본 도로: i에서 i+1로 이동 (비용 1)
        for (int i = 0; i < D; i++) {
            graph[i].add(new ShortCut(i + 1, 1));
        }

        // 지름길 정보 추가: s -> e (비용 d)
        // 단, 도착 위치가 D 이내이고 (e <= D) 일반 도로 이동보다 유리한 경우 (e-s > d)만 추가
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            
            // 이동해야할 거리를 넘지 않고, 효율적이면 그래프에 넣는다
            // 효율적의 의미 : 실제로 이동해야할 거리보다 지름길이 더 짧은 경우만
            if (end <= D && distance < (end - start)) {
                graph[start].add(new ShortCut(end, distance));
            }
        }

        // 다익스트라 알고리즘으로 0부터 D까지 최소 비용 계산
        int[] distance = new int[D + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;  // 시작 지점은 거리가 0 (당연하게도)

        PriorityQueue<ShortCut> pq = new PriorityQueue<>();
        pq.offer(new ShortCut(0, 0));

        while (!pq.isEmpty()) {
            ShortCut cur = pq.poll();
            int curLoc = cur.location;
            int curDis = cur.distance;

            // 이미 더 작은 비용이 계산된 경우 패스
            if (distance[curLoc] < curDis) continue;

            for (ShortCut next : graph[curLoc]) {
                int nextLoc = next.location;
                int nextDis = curDis + next.distance;

                // 도착 지점을 넘지 않고, 더 작은 비용이 있다면
                if (nextLoc <= D && nextDis < distance[nextLoc]) {
                    distance[nextLoc] = nextDis;  // 작은 비용으로 갱신
                    pq.offer(new ShortCut(nextLoc, nextDis));  // PQ에 넣음
                }
            }
        }

        // 결과 출력: 0부터 D까지 도달하는 최소 비용
        System.out.println(distance[D]);
    }
}