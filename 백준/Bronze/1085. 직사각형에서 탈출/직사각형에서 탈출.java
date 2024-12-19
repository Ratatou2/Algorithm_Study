/*
[백준]
1085, 직사각형에서 탈출

[문제파악]
- 한수는 지금 (x, y)에 있다.
- 직사각형은 각 변이 좌표축에 평행하고, 왼쪽 아래 꼭짓점은 (0, 0), 오른쪽 위 꼭짓점은 (w, h)에 있다.
- 직사각형의 경계선까지 가는 거리의 최솟값을 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 x, y, w, h가 주어진다.

[출력]
- 첫째 줄에 문제의 정답을 출력한다.

[구현방법]
- x, y에서 가장 가까운 경계선은 아래 4개중에 가장 짧은 것으로 비교하면 된다
    - x에서 0 까지의 거리
    - x에서 w 까지의 거리
    - y에서 0 까지의 거리
    - y에서 h 까지의 거리

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        // 가독성을 위해 줄바꿈 하긴했는데 구현 방법에 적힌 그대로이다
        // x에서 0까지의 거리와 x에서 w까지의 거리 중 가장 작은 값
        // y에서 0까지의 거리와 y에서 h까지의 거리 중 가장 작은 값
        // 두 가장 작은 값 중 최솟값을 구하면 우리가 구하는 정답이다
        // 추가로 x와 y의 입력 값은 무조건 1보다 크다는 조건을 받았기에 0부터 x까지의 거리와 0부터 y까지의 거리는 abs(절대값)처리 해줄 필요는 없다
        System.out.println(
                Math.min(
                        Math.min(x, Math.abs(x - w)),
                        Math.min(y, Math.abs(y - h))
                )
        );
    }
}