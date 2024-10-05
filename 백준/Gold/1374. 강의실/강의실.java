/*
[백준]
1374, 강의실

[문제파악]
- N개의 강의가 있다.
- 우리는 모든 강의의 시작하는 시간과 끝나는 시간을 알고 있다.
- 이때, 우리는 최대한 적은 수의 강의실을 사용하여 모든 강의가 이루어지게 하고 싶다.
- 물론, 한 강의실에서는 동시에 2개 이상의 강의를 진행할 수 없고, 한 강의의 종료시간과 다른 강의의 시작시간이 겹치는 것은 상관없다.
- 필요한 최소 강의실의 수를 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 강의의 개수 N(1 ≤ N ≤ 100,000)이 주어진다.
- 둘째 줄부터 N개의 줄에 걸쳐 각 줄마다 세 개의 정수가 주어지는데, 순서대로 강의 번호, 강의 시작 시간, 강의 종료 시간을 의미한다.
- 강의 번호는 1부터 N까지 붙어 있으며, 입력에서 꼭 순서대로 주어지지 않을 수 있으나 한 번씩만 주어진다.
- 강의 시작 시간과 강의 종료 시간은 0 이상 10억 이하의 정수이고, 시작 시간은 종료 시간보다 작다.

[출력]
- 첫째 줄에 필요한 최소 강의실 개수를 출력한다.

[구현방법]
- 이거 푸는 방법 거의 고착화 되어있었는데...
- 일단, 이거 정렬해야한다. 시작 시간 순으로, 그리고 하나씩 보면서 어딘가에 끼워넣을 수 있으면 끼워넣고, 불가능 하면 강의실을 하나 더 빌리는 식으로
- 이렇게 반복해서 강의실 갯수를 최소화한다 가 당장 생각나는 방법

[보완점]
- 문제는 이해했고, 그래서 강의도 시작 시간 순으로 정렬까지 해놨는데 강의실을 배정을 어떻게 해줘야하는지를 감을 못잡았다
- 고민이 30분을 넘어가서 확인해보니 이거 그냥 우선순위 큐 쓰란다
    - 강의실은 강의가 끝나는 시간을 기준으로 우선순위 큐를 사용

- 그러니까 이 문제는 우선순위 큐로 강의실을 관리한다
- 기본적으로 시작 시간을 기준으로 정렬을 해뒀기 때문에 가장 먼저 시작하는 강의들이 앞에 있다
- 이 상태에서 강의를 하나씩 꺼내 강의실을 배정해준다면, 가장 빠르게 시작할 수 있는 강의들부터 오기 때문에 문제 될 것이 없다.
- 그렇게 새로운 강의들의 시작 시간을 비교하기 위해서, 우선순위 큐에 넣는 것은 강의 종료 시간인 셈이다.

- 내가 의문을 가졌던 부분
    - 근데 오로지 가장 빨리 끝나는 강의실만 체크해도 되는가?
    - 예를들어 pq에 들어있는게 [8, 14]일 때, [start : 15, end 26, num 3] 짜리가 들어오면 두번째인 14에 갱신하는게 이득 아닌가?
    - 근데 현 코드에서는 peek() 한번만해서 무조건 8을 가져옴 (뒤에 있는 것들을 살펴볼, 더 타이트하게 쓸 수 있는, 좋은 기회비용을 따져볼 여지가 없다는 의미)

- 이에 대한 설명 : 왜 "가장 빨리 끝나는 강의실"만 체크해도 되는지 설명:
    - 강의실 재사용을 결정하는 기준은 현재 강의의 시작 시간보다 먼저 끝나는 강의실이어야 한다는 것
    - 가장 먼저 끝나는 강의실을 확인했는데 그 강의실을 쓸 수 없다면, 당연히 그 이후에 끝나는 강의실들은 더 늦게 끝나므로 재사용할 수 없다
    - 따라서, 강의실을 재사용하려면 가장 빨리 끝나는 강의실을 확인하는 것이 가장 합리적인 판단
    - 이게 무슨 의미냐면 내가 제시한 예시처럼 [8, 14]일 때야 [start : 15, end 26, num 3]라서 시작시간이 15니까 14에도 가능하다는걸 알 수 있다
    - 반대로 [8, 15]라면? 굳이 또 다음 것까지 확인해서 안된다는 것을 판단하고 다시 돌아와서 8을 갱신하는게 또 다른 기회비용일 수 있다는 것이다
    - 흠... 나름 납득간다 시간 시간 우선순위 정렬, 종료시간 우선순위 정렬 조합이라 내가 생각한 예시같은 경우는 정말 드물겠지
    - 왜냐면 시작 시간 기준으로 정렬하면 대부분 시작 시간이 비슷하게 모여있을텐데 14까지 들어갔다쳐도 그 다음이 15~26이 들어오기란 참 어려운 것이다
        - 무슨 말이냐면 1~8, 4~14 조건으로 들어왔다면 그 다음에 올 강의의 시작 시간은 4~14 사이일 가능성이 훨 높다 (강의들은 시작시간 우선순위 정렬이니까)
        - 갑자기 15가 튀어나올 확률은 지극히 적다는 소리

- 여러 문제를 풀어보고 사고의 다양성을 늘려야겠다 난이도를 올려야할 때인듯
- 메모리 초과.. 대체 무슨 일이야...

8
6 15 21
7 20 25
1 3 8
3 2 14
8 6 27
2 7 13
4 12 18
5 6 20


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Lecture implements Comparable<Lecture> {
        int start, end, num;

        public Lecture (int num, int start, int end) {
            this.start = start;
            this.end = end;
            this.num = num;
        }

        @Override
        public int compareTo(Lecture o) {
            return start - o.start;
        }

//        @Override
//        public String toString() {
//            return "num : " + num + "\n"
//                    + "start : " + start + "\n"
//                    + "end : " + end + "\n";
//        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Lecture> lectures = new ArrayList<>();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            lectures.add(new Lecture(Integer.parseInt(st.nextToken()), 
                                        Integer.parseInt(st.nextToken()), 
                                        Integer.parseInt(st.nextToken())));
        }

        Collections.sort(lectures);

        // 종료 시간을 기준으로 우선순위 큐를 사용해 강의실 관리
        PriorityQueue<Integer> classRoom = new PriorityQueue<>();

        // 첫 번째 강의의 종료 시간을 넣음 (첫 번째 강의실 배정)
        classRoom.add(lectures.get(0).end);

        // 나머지 강의에 대해 강의실 배정
        for (int i = 1; i < N; i++) {
            Lecture curr = lectures.get(i);

            // 현재 강의의 시작 시간이 가장 빨리 끝나는 강의보다 늦으면 그 강의실을 재사용
            if (classRoom.peek() <= curr.start) {
                classRoom.poll();  // 끝난 강의실을 제거
            }

            // 현재 강의 종료 시간을 우선순위 큐에 추가
            // 앞서 제거했다면 업데이트(기존 강의실 재사용)인 것이고, 지금 새로 추가하는 것이라면 강의실 +1
            classRoom.add(curr.end);
        }

        // 우선순위 큐에 남은 강의실 수가 필요한 최소 강의실 개수
        System.out.println(classRoom.size());
    }
}