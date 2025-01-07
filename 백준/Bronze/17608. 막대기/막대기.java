/*
[백준]
17608, 막대기

[문제파악]
- 아래 그림처럼 높이만 다르고 (같은 높이의 막대기가 있을 수 있음) 모양이 같은 막대기를 일렬로 세운 후, 왼쪽부터 차례로 번호를 붙인다.
- 각 막대기의 높이는 그림에서 보인 것처럼 순서대로 6, 9, 7, 6, 4, 6 이다.
- 일렬로 세워진 막대기를 오른쪽에서 보면 보이는 막대기가 있고 보이지 않는 막대기가 있다.
- 즉, 지금 보이는 막대기보다 뒤에 있고 높이가 높은 것이 보이게 된다.
- 예를 들어, 그림과 같은 경우엔 3개(6번, 3번, 2번)의 막대기가 보인다.

[입력]
- 첫 번째 줄에는 막대기의 개수를 나타내는 정수 N (2 ≤ N ≤ 100,000)이 주어지고 이어지는 N줄 각각에는 막대기의 높이를 나타내는 정수 h(1 ≤ h ≤ 100,000)가 주어진다.
- N개의 막대기에 대한 높이 정보가 주어질 때, 오른쪽에서 보아서 몇 개가 보이는지를 알아내는 프로그램을 작성하려고 한다.

[출력]
- 오른쪽에서 N개의 막대기를 보았을 때, 보이는 막대기의 개수를 출력한다.

[구현방법]
- stack에 전부 넣고, 마지막 막대기보다 큰 것들을 count하면 될듯하다
- 문제에 대한 답을 보니 '보이는 막대기'의 조건에 자기자신도 포함해야하는듯 (사실 이 역시 문제를 잘 읽어봤더라면 알 수 있는 부분)

[보완점]
- 와 내가 간과하고 있던게 있는데, 마지막 막대보다 높은 막대가 나오면 막대를 갱신해야한다는 점이다
- 무슨 말이냐면 막대가 3 5 4 9 1 이 있다고 가정하자
- 이 경우 가장 오른쪽 막대는 1이지만, 그 다음 막대가 9이므로 실질적으로 오른쪽에서 볼 수 있는 막대는 총 2개 뿐이다(9, 1)
- 그런데 내가 짠 코드는 전부 맨 오른쪽 막대 기준이므로 3, 4, 5, 9를 전부 볼 수 있다고 카운트할 것이고 그럼 오답이 되는 것이다
- 따라서 가장 오른쪽 막대부터 시작해서 최장 길이가 갱신될 때마다 업데이트 해주어야한다
- 문제 난이도가 쉽건 어렵건간에 문제의 취지를 너무 속단하지말고 잘 파악하도록 해야겠다
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> sticks = new Stack<>();
        int count = 0;

        // 스택에 막대기 전부 삽입
        for (int i = 0; i < N; i++) sticks.push(Integer.parseInt(br.readLine()));

        int lastStick = sticks.pop();  // 마지막 막대기가 기준

        while(!sticks.empty()) {
            int currentStick = sticks.pop();
            if (lastStick < currentStick) {
                count++;
                lastStick = currentStick;  // 최장 길이 막대 갱신
            }
        }

        System.out.println(count + 1);  // 자기 자신 +1
    }
}