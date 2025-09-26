/*
[백준]
32343, 드랍 더 비트

[문제파악]
비트를 가지고 노는 래퍼 H.Arc는 작곡이 잘 되지 않던 어느 날, 두 개의 N자리 이진수 x, y를 떠올렸다. 그러고는 다음과 같은 메모를 남겼다.
x는 a개의 자리가 1이다.
y는 b개의 자리가 1이다.
두 수 모두 맨 앞자리가 0이 될 수도 있다.
이 메모를 본 당신은, 가능한 모든 x, y 조합에 대해 x XOR y의 최댓값이 얼마일지 궁금해졌다. 이 값을 구해보자.

[입력]
첫째 줄에 N이 주어진다. (1 ≤ N ≤ 10)
둘째 줄에 a, b가 공백으로 구분되어 주어진다. (0 ≤ a, b ≤ N)

[출력]
x XOR y의 최댓값을 출력한다.

[구현방법]
- 이 문제의 핵심은 둘이 1이 되는 자리가 겹치지 않았을떄 가장 큰 수가 될 수 있다는 것이다 (=XOR)
- 값 자체를 키우려면 이 r개의 1을 가장 높은 비트들에 몰아넣으면 끝!

- 이 때, x는 a개의 1, y는 b개의 1을 가짐.
- 만약 a+b ≤ N 이라면, 둘의 1을 전혀 겹치지 않고 배치 가능하다. 따라서 XOR에서 1이 되는 자리 = a+b.
- 만약 a+b > N 이라면, 자리가 모자라므로 최소 (a+b-N)개는 겹칠 수밖에 없다.

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int r = Math.min(a + b, 2 * N - (a + b)); // XOR에서 1이 되는 최대 자리 수
        if (r <= 0) System.out.println(0);
        else System.out.println(((1L << r) - 1) << (N - r));
    }
}
