/*
[백준]
11000, 강의실 배정

[문제파악]
- 수강신청의 마스터 김종혜 선생님에게 새로운 과제가 주어졌다.
- 김종혜 선생님한테는 Si에 시작해서 Ti에 끝나는 N개의 수업이 주어지는데, 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다.
- 참고로, 수업이 끝난 직후에 다음 수업을 시작할 수 있다. (즉, Ti ≤ Sj 일 경우 i 수업과 j 수업은 같이 들을 수 있다.)
- 수강신청 대충한 게 찔리면, 선생님을 도와드리자!

[입력]
- 첫 번째 줄에 N이 주어진다. (1 ≤ N ≤ 200,000)
- 이후 N개의 줄에 Si, Ti가 주어진다. (0 ≤ Si < Ti ≤ 109)

[출력]
- 강의실의 개수를 출력하라.

[구현방법]
- PQ로 정렬을 잘해야 하는 문제
- 이게 함정이 있는게 시작시간으로 나열하면, 바로 터진다 1~7까지 고르는 와중에 1수업이 1~7 수업뿐이라면 전체 시간을 점유하게 되어 1개밖에 못함
- 그래서 빨리 끝나는 것들을 우선적으로 정렬해야 한다
- 문제가 한번에 많이 들을 수 있는 강의 갯수가 아니라 '모든 수업을 수강하는데 필요한 강의실 갯수'이다
- 문제 좀 잘 읽자
- PQ에서 꺼내서 한세트를 만들고 강의실 갯수는 셀 수 있겠는데 PQ에서 빼둔걸 어디다 저장하지..?
- PQ 두개 만들고 쓴 거 안쓴 거 구분해야 할듯하다

- 응 시간 초과;;
- 인접 행렬 만들어서 각 행렬 마지막 친구 비교하고 넣어주고 반복해야하나...? 진짜 이게 맞아?
- 아 PQ를 '수업 끝나는 시간을 저장'하는 자료구조로 써서 해결하는 거구나
- 이런 아이디어는 대체 누구 머리에서 나온거지 미띤
- 주석에도 달아두겠지만 위처럼 구현하면 끝나는 시간만 PQ에서 확인하고, 수업을 밀어넣을 수 있으면 꺼낸 뒤, 밀어넣은 수업의 end 시간으로 갱신하면 된다 

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class classInfo {
        int start, end;

        classInfo(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "현 수업 Info - " + start + " - " + end;
        }
    }

    static void printPQ(PriorityQueue<classInfo> PQ) {
        PriorityQueue<classInfo> pq = new PriorityQueue<classInfo>(PQ);

        StringBuilder sb = new StringBuilder();
        sb.append("++++++++++++++++++++" + "\n");
        while (!pq.isEmpty()) {
            sb.append(pq.poll() + "\n");
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());  // 수업 갯수
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        classInfo[] infos = new classInfo[N];

        // 수업 리스트 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            infos[i] = new classInfo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(infos, Comparator.comparingInt(classInfo -> classInfo.start));  // 시작 시간을 기준으로 정렬한다
        endTimes.add(infos[0].end);  // 첫번째 수업의 끝나는 시간을 추가

        for (int i = 1; i < N; i++) {
            // 가장 먼저 끝나는 수업이 현재 수업 시간보다 앞선 시간이라면, 즉, 앞선 수업(endTimes.peek()) 직후에 바로 현재 수업(infos[i].start)을 들을 수 있다면
            // 가장 먼저 끝나는 수업을 빼버린다 (왜냐면, 그 수업 이후에 현재 수업(infos[i].start)를 들은 셈이니까)
            // 그리고 현재 수업(infos[i].start)를 넣어주면 시간표 갱신 완료
            if (endTimes.peek() <= infos[i].start) endTimes.poll();

            // 제일 빨리 끝나는 수업 이후에 들을 수 있으면, 앞에서 이미 빼버렸을 것이고
            // 제일 빨리 끝나는 수업 뒤에 들을 수 없다면, 새로운 강의실을 할당하는 개념으로 추가하면 된다
            endTimes.add(infos[i].end);
        }

        // 그러면 현재 PQ에 남은 숫자의 갯수가 필요한 강의실 갯수이다
        // 중첩되지 못한 친구들만 남아서 각자 강의실을 점유중일테니 말이다
        System.out.println(endTimes.size());
    }
}
