

/*
[백준]
14719, 빗물

[문제파악]
2차원 세계에 블록이 쌓여있다. 비가 오면 블록 사이에 빗물이 고인다.
비는 충분히 많이 온다. 고이는 빗물의 총량은 얼마일까?

[입력]
첫 번째 줄에는 2차원 세계의 세로 길이 H과 2차원 세계의 가로 길이 W가 주어진다. (1 ≤ H, W ≤ 500)
두 번째 줄에는 블록이 쌓인 높이를 의미하는 0이상 H이하의 정수가 2차원 세계의 맨 왼쪽 위치부터 차례대로 W개 주어진다.
따라서 블록 내부의 빈 공간이 생길 수 없다.
또 2차원 세계의 바닥은 항상 막혀있다고 가정하여도 좋다.

[출력]
2차원 세계에서는 한 칸의 용량은 1이다.
고이는 빗물의 총량을 출력하여라.
빗물이 전혀 고이지 않을 경우 0을 출력하여라.

[구현방법]
- 현재 왼쪽에서부터 최대 높이를 갱신해가며 자기 값보다 낮으면 높이를 갱신한다
- 그러다가 높이가 같거나, 높으면 높이를 갱신함
- 핵심은 높이가 갱신된 시점에서 그 다음 나보다 큰 벽까지 탐색해야한다는 것이다
- 두 벽 중 가장 낮은 높이가 그 사이에서 고일 수 있는 값이기 때문이다
- 근데 하나 갱신하고 다음 값 갱신할 때까지 찾은 다음 현재 인덱스까지 다시 탐색하며 빗물 채우는거 말고 더 우아한 방식이 있었던 것 같은데...
    - 심지어 이렇게 구하면 큰 문제가 있음...
    - 바로 마지막까지 내가 가장 큰 벽일 경우, 빗물 계산 로직에 안걸린다는 것... 이 방법은 폐기 다른 방법이 필요하다
- 다만 예외는 있는 것이 높이를 갱신한 이후로 갱신할 벽을 만나지 못했다면 (고일 곳이 없으므로) 고인 빗물은 0이다

[보완점]
- 이거 그냥 각 지점에서 왼쪽, 오른쪽 가장 높은 지점을 미리 기록해두면 된다
- 그게 더 적게 탐색하고 효율적인 방법임
- 떄론 가장 단순한게 가장 효율적인듯 ㅠ
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        // 지도 입력 받기
        int[] height = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int[] leftMax = new int[W];
        int[] rightMax = new int[W];

        // 각 좌표에서 왼쪽 최댓값 찾기
        leftMax[0] = height[0];
        for (int i = 1; i < W; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // 각 좌표에서 오른쪽 최댓값 찾기
        rightMax[W - 1] = height[W - 1];
        for (int i = W - 2; 0 <= i; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // 이제 현 위치에 고이는 빗물 계산
        int fillWater = 0;
        for (int i = 0; i < W; i++) {
            int minHeight = Math.min(rightMax[i], leftMax[i]);
            if (height[i] < minHeight) fillWater += minHeight - height[i];
        }

        System.out.println(fillWater);
    }
}
