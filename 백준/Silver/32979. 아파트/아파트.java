

/*
[백준]
32979, 아파트

[문제파악]
연말을 맞아 아주대학교 소프트웨어학과 알고리즘 동아리 ANSI는 다 같이 모여 아파트 게임을 T회 진행하고자 한다.
ANSI는 진행자 교선이와 N명의 참가자로 구성되어 있다.
각 참가자에게는 1번부터 N번까지 번호가 부여되어 있고, 참가자는 각자의 두 손을 층층이 쌓아 올려 손 아파트를 만들어 준비한다.
밑에서부터 i번째 위치에 번호가 a_i인 참가자의 손이 자리한다.
j번째 아파트 게임의 진행 과정은 다음과 같다.

교선이가 한 양의 정수 b_j를 부른다.참가자들이 1부터 b_j까지 수를 센다.
수를 셀 때마다 손 아파트의 맨 아래에 있는 손을 빼서 손 아파트의 맨 위에 올린다.
참가자들이 교선이가 부른 수 b_j를 셀 때 맨 아래에 손을 둔 참가자가 패배한다.
패배한 참가자는 손을 빼지 않고 그대로 둔다.예를 들어, 손이 6개 있을 때 4를 부르면 아래에서 4번째에 손을 둔 참가자가 패배한다.
교선이는 손 아파트를 이루는 손의 총 개수보다 큰 수를 부를 수 있음에 유의하자.

ANSI는 게임의 연속성을 위해, 다음 아파트 게임을 진행할 때 이전 게임이 종료된 상태에서 손 아파트의 위치를 바꾸지 않고 새로운 게임을 시작한다.
T번의 게임에서 가장 많이 패배하는 참가자는 벌칙을 받아야 하므로, 각 게임마다 패배하는 참가자를 알아내 보자.

[입력]
첫 번째 줄에 참가자의 수 N이 주어진다. (1 \leq N \leq 40)
두 번째 줄에 아파트 게임의 횟수 T가 주어진다. (1 \leq T \leq 5\,000)
세 번째 줄에 손 아파트에서 손의 위치에 대한 2N개의 수가 공백으로 구분되어 주어진다.
a_i는 밑에서 i번째에 손을 둔 참가자의 번호이다.
모든 참가자의 번호는 각각 두 번씩 등장한다. (1 \leq a_i \leq N)
네 번째 줄에 교선이가 부르는 T개의 양의 정수 b_j가 공백으로 구분되어 주어진다.
b_j는 j번째 게임에서 교선이가 부른 수를 의미한다. (1 \leq b_j \leq 1\,000)

[출력]
첫 번째 게임부터 패배하는 참가자의 번호를 순서대로 공백으로 구분하여 출력한다.

[구현방법]


[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());  // 참가자 수
        int T = Integer.parseInt(br.readLine());  // 게임 횟수

        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Integer> hands = new ArrayDeque<>();

        // 손 순서 입력 받기
        for (int i = 0; i < 2 * N; i++) {
            hands.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            // 쌓인 손보다 큰 수를 부르는 경우 굳이 여러 바퀴 다 돌 필요 없다
            int num = Integer.parseInt(st.nextToken());

            // N번째 손바닥은 빼내는 것이 아니기 때문에 밑바닥에 그대로 있어야한다 (그러기 위한 num - 1임)
            for (int i = 0; i < num - 1; i++) {
                hands.addLast(hands.pollFirst());
            }

            sb.append(hands.getFirst()).append(" ");
        }

        System.out.println(sb);
    }
}
