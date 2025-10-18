

/*
[백준]
21756, 지우개

[문제파악]
N개의 칸에 1부터 N까지의 수들이 왼쪽부터 순서대로 저장되어 있다.
또, 각 칸은 왼쪽부터 1 부터 N까지 순서대로 번호가 붙어 있다.
즉, 처음에는 각 칸의 번호와 각 칸에 저장된 수가 같다.

아래 그림은 N = 7일 때의 예이다.

다음 작업을 수가 정확히 하나가 남을 때 까지 반복한다.
(A) 홀수번 칸의 수들을 모두 지운다 (B) 남은 수들을 왼쪽으로 모은다.
제일 첫 작업의 (A) 단계가 끝나면 칸들의 상태는 다음과 같을 것이다.
(B) 단계가 끝나면 다음과 같을 것이다.
두번째 작업이 진행되면 칸들은 아래 두 그림과 같이 바뀔 것이다.
이제 수가 하나 남았으므로 작업은 더 이상 진행되지 않는다.
N을 입력으로 받아 위와 같이 작업을 진행했을 때 마지막으로 남는 수를 계산하는 프로그램을 작성하라.

[입력]
첫 번째 줄에 정수 N이 주어진다.

[출력]
마지막으로 남는 수를 한 줄에 출력한다.

[구현방법]
- 수학적 계산으로 풀기 싫으니까 시뮬레이션으로 풀어보겠
- 처음 생각했을 떈 지울 생각을 했는데, 사실 지우면서 하건, 기록을 해뒀다가 나중에 한번에 지우건 문제가 생긴다
- 인덱스가 계속 바뀌기 때문에 잘못 지우기 쉽다는 것
- 이 부분에서 정말 오랜 고민을 했는데, 생각의 전환이 필요헀다
- 맨날 메모리 아끼려고 새로 생성하는 것을 기피하곤 했는데, 이럴 땐 그냥 매번 새로 리스트를 만들어가며 추가하는 것이 훨씬 더 효율적이란 것을 배웠다
- 아.. 이게 머리가 이케 굳을 일인가 ㅋㅋㅋ

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;
        List<Integer> nums = new ArrayList<>();

        // 숫자가 한개 뿐이면 그대로 종료
        if (N == 1) {
            System.out.println(1);
            System.exit(0);
        }
        
        // 초기값 입력
        for (int i = 2; i <= N; i = i+2) {
            nums.add(i);
        }

        // 반복하며 홀수 index 제거
        while (1 < nums.size()) {
            List<Integer> temp = new ArrayList<>();  // 짝수 index만 저장할 임시 List

            int index = 1;
            for (int i : nums) {
                if (index % 2 == 0) temp.add(i);
                index++;
            }

            nums = temp;  // List를 갱신
        }

        System.out.println(nums.get(0));
    }
}
