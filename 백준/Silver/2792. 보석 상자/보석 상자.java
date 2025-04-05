/*
[백준]
2792, 보석 상자

[문제파악]
- 보석 공장에서 보석 상자를 유치원에 기증했다.
- 각각의 보석은 M가지 서로 다른 색상 중 한 색상이다.
- 원장 선생님은 모든 보석을 N명의 학생들에게 나누어 주려고 한다.
- 이때, 보석을 받지 못하는 학생이 있어도 된다.
- 하지만, 학생은 항상 같은 색상의 보석만 가져간다.
- 한 아이가 너무 많은 보석을 가져가게 되면, 다른 아이들이 질투를 한다.
- 원장 선생님은 이런 질투심을 수치화하는데 성공했는데, 질투심은 가장 많은 보석을 가져간 학생이 가지고 있는 보석의 개수이다.
- 원장 선생님은 질투심이 최소가 되게 보석을 나누어 주려고 한다.
- 상자에 빨간 보석이 4개 (RRRR), 파란 보석이 7개 (BBBBBBB) 있었고, 이 보석을 5명의 아이들에게 나누어 주는 경우를 생각해보자.
- RR, RR, BB, BB, BBB로 보석을 나누어주면 질투심은 3이 되고, 이 값보다 작게 나누어 줄 수 없다.
- 상자 안의 보석 정보와 학생의 수가 주어졌을 때, 질투심이 최소가 되게 보석을 나누어주는 방법을 알아내는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 아이들의 수 N과 색상의 수 M이 주어진다. (1 ≤ N ≤ 109, 1 ≤ M ≤ 300,000, M ≤ N)
- 다음 M개 줄에는 구간 [1, 109]에 포함되는 양의 정수가 하나씩 주어진다.
- K번째 줄에 주어지는 숫자는 K번 색상 보석의 개수이다.

[출력]
- 첫째 줄에 질투심의 최솟값을 출력한다.

[구현방법]
- 일단 같은 색상밖에 가지지 못하고 또한 모든 보석들을 소진해야함
- 이러면 둘중에 적은 갯수를 가진 보석을 기준으로 이분 탐색을 진행햐야하는 것이 아닌가..?
- 일단 가장 중요한 것은 질투심은 가장 많은 갯수를 가지고 있는 친구라는 것.
- 즉, 최대한 공평한 갯수로 나눠줘야만 한다.
- 근데 적은 갯수 기준으로 하면, 전체 인원으로 계산하면 안되는 것이 같은 보석 색상만 가질 수 있기 때문에.. 어쩌면 인원도 그 비율에 따라 나눠야할지도?
- 이분탐색 진짜 모르겠다잉

[보완점]
- 이분탐색인지 판별하는 방법
    1) 정답이 특정한 수의 범위 내에서 최솟값 or 최댓값을 찾는 문제라면?!
    2) 결정문제 (YES/NO)로 바꿀 수 있다면?!
- 여기서 이분탐색으로 찾아나갈 것은 질투심이다
- 질투심 x를 정해놓고, x개 이하로 보석을 나눠줄 수 있는지를 판단한다
    - 이 떄 질투심의 범위는 1부터 '최대 보석 갯수'까지다
    - 즉, 질투심을 정하고, 해당 질투심으로 각 색의 분배 갯수를 나누면, 현재 질투심으로 나눌 수 있는 학생 수가 나옴
    - 이때 이 학생 수의 합이

(*) 뭔소리야?
- 이건 알고리즘에서 굉장히 자주 쓰는 방법이다 (물론 나는 요번에 처음 배웠다..)
- 정수 나눗셈에서 나머지로 남는 것들은 기본적으로 버려지는데, 1이라도 남는다면 몫 1이 될 수 있도록 더해주고 -1을 빼서 1만 모자라게 해주는 것이다
- 즉, ceil과 똑같다
- 즉 7을 3으로 나누면? 몫은 2, 나머지는 1이다
- 여기서 나누는 숫자인 3을 더해주고 1을 빼주면?
- 9 = (7 + 3) - 1가 되고, 몫이 3이 된다
- 알고리즘에서 자주 쓰이는 수법이라니.. 익혀두자 (난 그동안 몰랐지...)
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] jewels;

    // 현재 질투심 갯수로 나눌 수 있는지 없는지, 체크
    static boolean canDivide(int jealous) {
        int count = 0;

        for (int jewel : jewels) {
            count += (jewel + jealous - 1) / jealous; // 올림 나눗셈 (*)
        }

        return count <= N;  // 갯수가 학생 수 보다 작거나 크면 가능하다
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 학생 수
        M = Integer.parseInt(st.nextToken());  // 색상 수

        jewels = new int[M];
        int max = 0;  // 가장 많은 색상의 갯수

        // 색상의 수 입력 받기
        for (int i = 0; i < M; i++) {
            jewels[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, jewels[i]);
        }

        int left = 1;
        int right = max;
        int answer = max;  // 정답은 일단 보석의 최대 갯수를 질투심으로 가정한다

        while (left <= right) {
            int mid = (left + right) / 2;  // 중간 지점

            // 현재 질투심으로 보석 갯수를 나누고 모두에게 줄 수 있다면 기록한다
            if (canDivide(mid)) {
                answer = mid; // 더 좋은 (작은) 질투심이 가능하므로 저장
                right = mid - 1;
            } else {
                left = mid + 1; // 불가능하니 질투심을 더 높여야 함
            }
        }

        System.out.println(answer);
    }
}