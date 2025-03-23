/*
[백준]
19598, 최소 회의실 개수

[문제파악]
- 서준이는 아빠로부터 N개의 회의를 모두 진행할 수 있는 최소 회의실 개수를 구하라는 미션을 받았다.
- 각 회의는 시작 시간과 끝나는 시간이 주어지고 한 회의실에서 동시에 두 개 이상의 회의가 진행될 수 없다.
- 단, 회의는 한번 시작되면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다.
- 회의의 시작 시간은 끝나는 시간보다 항상 작다.
- N이 너무 커서 괴로워 하는 우리 서준이를 도와주자.

[입력]
- 첫째 줄에 배열의 크기 N(1 ≤ N ≤ 100,000)이 주어진다.
- 둘째 줄부터 N+1 줄까지 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다.
- 시작 시간과 끝나는 시간은 231−1보다 작거나 같은 자연수 또는 0이다.

[출력]
- 첫째 줄에 최소 회의실 개수를 출력한다.

[구현방법]
- 일단 PQ 문제인데, 우선 회의실을 빨리 끝나는 순으로 배치한다 (PQ 역시 빨리 끝나는 순으로 정렬한다 (오름차순))
- 그리고 PC의 첫번째 값이 내가 시작하는 시간보다 먼저 끝나는 강의가 있으면 그 값을 꺼내고 내가 끝나는 시간을 밀어넣음
- 근데 그게 아니라면 그냥 내 시간을 밀어넣으면 됨
- 마지막에 PC에 남은 갯수가 필요한 최소 강의 수다

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
    static class Lecture implements Comparable<Lecture> {
        int start, end;

        Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            if (this.start == o.start) return this.end - o.end;
            return this.start - o.start;
        }

        @Override
        public String toString() {
            return "현 강의는 " + this.start + " ~ " + this.end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Lecture[] lectures = new Lecture[N];

        // 강의 정보 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 강의가 빠르게 시작하는 순으로 정렬 해두기
        Arrays.sort(lectures);

        PriorityQueue<Integer> classSchedule = new PriorityQueue<>();
        int lecIndex = 0;
        while (lecIndex < N) {
            // 강의들이 끝나는 시간을 기록해둘 PQ가 비어있지 않고, 첫번째 강의시간이 끝나는 시간보다, 현 강의의 시작시간이 더 크거나 같으면 직후에 넣을 수 있다
            if (!classSchedule.isEmpty() && classSchedule.peek() <= lectures[lecIndex].start) {
                classSchedule.poll();  // 맨 앞 강의실 종료시간을 꺼냄 (현 강의실 끝나는 시간을 업데이트하기 위해)
            }

            // 끝나는 시간을 현재의 강의로 추가하고, 강의 index 증가
            classSchedule.add(lectures[lecIndex].end);
            lecIndex++;
        }

        System.out.println(classSchedule.size());
    }
}
