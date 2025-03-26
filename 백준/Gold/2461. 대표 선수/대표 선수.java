/*
[백준]
2461, 대표 선수

[문제파악]
- KOI 중학교에는 N개의 학급이 있으며, 각 학급의 학생 수는 모두 M명으로 구성된다. 이 중학교에서는 체육대회에 새로운 종목의 경기를 추가하였다.
- 이 경기에 대해 모든 학생들은 저마다의 능력을 나타내는 능력치를 가지고 있으며, 이 능력치는 모든 학생이 서로 다르다.
- 이 경기는 한반에서 한 명의 대표선수를 선발하여 치른다.
- 경기의 형평성을 위하여, 각각의 반에서 대표로 선발된 모든 학생들의 능력치 중 최댓값과 최솟값의 차이가 최소가 되도록 선수를 선발하려고 한다.
- 예를 들어, N=3, M=4인 경우 학생들의 능력치가
    1반=[12, 16, 67, 43],
    2반=[7, 17, 68, 48],
    3반=[14, 15, 77, 54]로 주어질 때,
- 각 학급으로부터 능력치 16, 17, 15를 가진 학생을 각각 선택하면, 최댓값과 최솟값의 차이가 17-15=2로 최소가 된다.
- 대표로 선발된 모든 학생들 능력치의 최댓값과 최솟값 차이가 최소가 되는 경우의 값을 출력하는 프로그램을 작성하시오.

[입력]
- 입력의 첫 번째 줄에는 학급의 수를 나타내는 N과 각 학급의 학생의 수를 나타내는 M이 하나의 빈칸을 사이에 두고 주어진다.
- 단, 1 ≤ N, M ≤ 1,000이다.
- 두 번째 줄부터 N개의 줄에는 각 줄마다 한 학급 학생들의 능력치를 나타내는 M개의 양의 정수가 하나의 빈칸을 사이에 두고 주어진다.
- 능력치는 0이상 109이하이다.

[출력]
- 대표로 선발된 모든 학생들 능력치의 최댓값과 최솟값 차이가 최소가 되는 경우의 값을 하나의 정수로 출력한다.

[구현방법]
- 이거 그냥 중앙값 구하면 된다
- 중앙값 구하는 함수 짜서 각 입력에 대해 중앙값 하나씩 return 하고 거기서 최대, 최소 구해서 빼면 될듯
- 아 근데 이 아이디어에 구멍이 있는게, [3, 4, 5, 6], [12, 15, 17, 19], [30, 32, 37, 39]가 있으면 각 학급의 중앙값을 찾으면 안됨..
- 이럴 땐 최소학급에서의 최댓값인 6, 최대학급에서의 최솟값인 30을 가져와야 한다
- 흠... 지금 생각나는 방법은 일단 입력을 받으면 array에 넣고, 정렬하고 중앙값을 찾는 것이다
- 그리고 그 중앙값을 찾고 각 학급에서 그것과 가장 유사한 친구를 찾는 것이지.. 이게되나
- 내가 구현하려는 방식으로 풀이는 되는데 정답에서 아예 벗어나 있음
- 이런 경우는 그냥 접근 방식 자체가 잘못된거임... (열에 아홉 유형을 접해보지 못해 생기는 이슈)

[보완점]
- 이 방식은 PQ를 활용한 슬라이딩 윈도우 형식이라고..
- 어렵다 어려워...
- 그나마 이해하는데 제일 도움됐던건 다름아닌 GPT의 코드 분석..
    처음에는 각 학급의 첫 번째 학생들을 우선순위 큐에 넣습니다.
    우선순위 큐: [5, 10, 15]
    → 5가 가장 작은 값이므로 minStudent는 5가 됩니다.

    minStudent (5)를 꺼내고, 해당 학급(학급 3)의 다음 학생(10)을 우선순위 큐에 넣습니다.
    우선순위 큐: [10, 15, 20]
    → 10이 가장 작은 값이므로 minStudent는 10이 됩니다.

    minStudent (10)를 꺼내고, 해당 학급(학급 1)의 다음 학생(20)을 우선순위 큐에 넣습니다.
    우선순위 큐: [15, 20, 25]
    → 15가 가장 작은 값이므로 minStudent는 15가 됩니다.

    minStudent (15)를 꺼내고, 해당 학급(학급 2)의 다음 학생(25)을 우선순위 큐에 넣습니다.
    우선순위 큐: [20, 25, 30]
    → 20이 가장 작은 값이므로 minStudent는 20이 됩니다.

    minStudent (20)를 꺼내고, 해당 학급(학급 1)의 다음 학생(30)을 우선순위 큐에 넣습니다.
    우선순위 큐: [25, 30, 35]
    → 25가 가장 작은 값이므로 minStudent는 25가 됩니다.
- 그러니까 각 학급에서 최솟값들이 PQ에 하나씩 들어있으니 계속해서 다음 학급의 차수를 PQ에 계속 집어넣을 기회는 생긴다
- 그럼 이제 학급 (1, 2, 3)가 순차적으로 세트로 반복되면서 들어올텐데,
    - 거기서 최댓값은 집어넣은 값으로 갱신(= 최댓값(maxVal)은 큐에서 새로 들어간 학생의 값으로 갱신),
    - 다음 루프 진행할 때 뽑은 친구랑 빼서,
    - 현재 최소 비교 값이랑 비교하면 된다
- 이게 되는 이유는 모든 학급이 순차적으로 계속 들어오기 때문에 한세트로 비교할 수 있다
    - 각 학급에서 하나씩 학생을 뽑는 방식이므로, 학급 순서대로 진행되면서 학생들의 값들이 비교 가능
- 어려워 어려워 다시 풀어보자 이건

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Student implements Comparable<Student> {
        int value, classIndex, studentIndex;

        public Student(int value, int classIndex, int studentIndex) {
            this.value = value;
            this.classIndex = classIndex;
            this.studentIndex = studentIndex;
        }

        @Override
        public int compareTo(Student o) {
            return this.value - o.value;  // 최소 힙(오름차순 정렬)
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 학급 수
        int M = Integer.parseInt(st.nextToken());  // 각 학급의 학생 수

        int[][] students = new int[N][M];   // N번째 학급의 M번째 학생 능력치
        PriorityQueue<Student> pq = new PriorityQueue<>();

        int maxVal = 0;  // 현재 선택된 학생들 중 최댓값
        int minDiff = Integer.MAX_VALUE;  // 최솟값과 최댓값 차이 최소값

        // 입력 받기 및 정렬
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                students[i][j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(students[i]);  // 각 학급 능력치 오름차순 정렬
            pq.add(new Student(students[i][0], i, 0));  // 각 학급에서 최소값을 우선 삽입
            maxVal = Math.max(maxVal, students[i][0]);  // 초기 최댓값 설정
//            System.out.println("현재 : " + i + "  - " + Arrays.toString(students[i]) + " // " + maxVal);
        }

        while (true) {
            Student minStudent = pq.poll();  // 현재 최솟값을 가진 학생 꺼내기
//            System.out.println("꺼냈슈: " + minStudent.value + "?????????" + minDiff);
            int minVal = minStudent.value;
            minDiff = Math.min(minDiff, maxVal - minVal);  // 최소 차이 갱신

            // 현재 학급에서 다음 학생이 없으면 종료
            if (M <= minStudent.studentIndex + 1) break;

            // 다음 학생을 우선순위 큐에 추가
            int nextVal = students[minStudent.classIndex][minStudent.studentIndex + 1];
            pq.add(new Student(nextVal, minStudent.classIndex, minStudent.studentIndex + 1));
            maxVal = Math.max(maxVal, nextVal);  // 최댓값 갱신
        }

        System.out.println(minDiff);
    }
}