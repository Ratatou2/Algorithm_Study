

/*
[백준]
16987, 계란으로 바위치기

[문제파악]
문제를 소개하기 전, 계란으로 계란을 치게 될 경우 어떤 일이 벌어지는지를 먼저 이해하고 가자.
각 계란에는 내구도와 무게가 정해져있다.
계란으로 계란을 치게 되면 각 계란의 내구도는 상대 계란의 무게만큼 깎이게 된다.
그리고 내구도가 0 이하가 되는 순간 계란은 깨지게 된다.

예를 들어 계란 1의 내구도가 7, 무게가 5이고 계란 2의 내구도가 3, 무게가 4라고 해보자.
계란 1으로 계란 2를 치게 되면 계란 1의 내구도는 4만큼 감소해 3이 되고 계란 2의 내구도는 5만큼 감소해 -2가 된다.
충돌 결과 계란 1은 아직 깨지지 않았고 계란 2는 깨졌다.

유현이가 인범이에게 알려준 퍼즐은 일렬로 놓여있는 계란에 대해 왼쪽부터 차례로 들어서 한 번씩만 다른 계란을 쳐 최대한 많은 계란을 깨는 문제였다.
구체적으로 계란을 치는 과정을 설명하면 아래와 같다.

가장 왼쪽의 계란을 든다.
    1. 손에 들고 있는 계란으로 깨지지 않은 다른 계란 중에서 하나를 친다.
    2. 단, 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다. 이후 손에 든 계란을 원래 자리에 내려놓고 3번 과정을 진행한다.
    3. 가장 최근에 든 계란의 한 칸 오른쪽 계란을 손에 들고 2번 과정을 다시 진행한다.
단, 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 계란을 치는 과정을 종료한다.
이 과정을 통해 최대한 많은 계란을 깨는 것이 앞으로 인범이가 매일 아침마다 풀게 될 퍼즐이다.
그리고 유현이는 인범이가 찾은 답이 정답이 맞는지 확인해주려고 한다.
일렬로 놓인 계란들의 내구도와 무게가 차례대로 주어졌을 때 최대 몇 개의 계란을 깰 수 있는지 알아맞춰보자.

[입력]
첫째 줄에 계란의 수를 나타내는 N(1 ≤ N ≤ 8)가 주어진다.
그 다음 N개의 줄에는 계란의 내구도와 무게에 대한 정보가 주어진다.
i+1번째 줄에는 왼쪽에서 i번째에 위치한 계란의 내구도 Si(1 ≤ Si ≤ 300)와 무게 Wi(1 ≤ Wi ≤ 300)가 한 칸의 빈칸을 사이에 두고 주어진다.

[출력]
첫째 줄에 인범이가 깰 수 있는 계란의 최대 개수를 출력한다.

[구현방법]
- 왼쪽에서부터 한번씩 계란을 들거고 안깨져 있으면, 안깨져 있는 계란들을 내려칠 수 있다
- 이 때 계산해야하는 것은 현재의 계란으로 낼 수 있는 효율의 최대치를 상정해야한다는 것이다
- 왜냐하면 각 계란은 한번만 내려칠 수 있으므로

- 위 방법은 아예 틀렸다;;
- 최적의 해를 찾아 시도하는 그리디가 아니라, 모든 것을 다해보고 되돌아감을 기록하는 백트래킹이 필요하다
- 최적의 효율을 내지 않아도 미래 분기점에선 그것이 최악의 경우가 될 수 있기 때문이다
- 또한 계란 갯수도 최대 8개밖에 안됨 (다해봐도 된다는 소리)
- 그럼 분기를 쪼개면서 계속 내려가면서 끝에 도달했을 때 값을 갱신할 수 있는 DFS가 적절하겠다

[보완점]
- DFS를 다시금 좀 더 풀어봐야할 것 같다
*/

import javax.swing.text.AsyncBoxView;
import java.io.*;
import java.util.*;

public class Main {
    static int eggCount, maxCrushCount;
    static int[][] eggs;

    static void DFS(int index) {
        // 마지막 계란까지 끝냈으면, 값 갱신 하고 종료
        if (index == eggCount) {
            int count = 0;

            // 내구도가 0이하면 깨진 것으로 판단한다
            for (int[] temp : eggs) {
                if (temp[0] <= 0) count++;
            }

            // 최댓값 갱신 후 종료
            maxCrushCount = Math.max(maxCrushCount, count);
            return;
        }
        
        // 근데 손에 들고 있는 계란이 깨져 있으면 다음 계란으로 패스 해야함
        if (eggs[index][0] <= 0) {
            DFS(index + 1);
            return;
        }

        // 쳤는지 여부 판단하는 플래그
        boolean isHit = false;

        // 안꺠진 계란이 2, 3, 4가 있다면 2, 3, 4를 공평하게 한번씩 먼저 깨보기 위한 for문
        for (int target = 0; target < eggCount; target++) {
            // 깨려는 계란이 본인 자신이거나, 이미 깨져있으면 패스
            if (index == target || eggs[target][0] <= 0) continue;

            isHit = true;  // 내려칠거니까 true로 변경

            // 내구도는 무게만큼 빼준다
            eggs[index][0] -= eggs[target][1];
            eggs[target][0] -= eggs[index][1];

            DFS(index + 1);

            // 다음 계산을 위해 내구도 복구
            eggs[index][0] += eggs[target][1];
            eggs[target][0] += eggs[index][1];
        }

        // 칠 대상이 없었으면 다음 계란으로 패스
        if(!isHit) DFS(index + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        eggCount = Integer.parseInt(br.readLine());
        eggs = new int[eggCount][2];
        maxCrushCount = 0;

        // 계란 정보 입력 받기
        for (int i = 0; i < eggCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());  // 계란 내구도
            eggs[i][1] = Integer.parseInt(st.nextToken());  // 계란 무게
        }

        // 계란 깨치기! (들어올릴 계란)
        DFS(0);

        System.out.println(maxCrushCount);
    }
}
