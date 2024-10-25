/*
[백준]
2629, 양팔 저울

[문제파악]
- 양팔 저울과 몇 개의 추가 주어졌을 때, 이를 이용하여 입력으로 주어진 구슬의 무게를 확인할 수 있는지를 결정하려고 한다.
- 무게가 각각 1g과 4g인 두 개의 추가 있을 경우, 주어진 구슬과 1g 추 하나를 양팔 저울의 양쪽에 각각 올려놓아 수평을 이루면 구슬의 무게는 1g이다.
- 또 다른 구슬이 4g인지를 확인하려면 1g 추 대신 4g 추를 올려놓으면 된다.
- 구슬이 3g인 경우 아래 <그림 1>과 같이 구슬과 추를 올려놓으면 양팔 저울이 수평을 이루게 된다.
- 따라서 각각 1g과 4g인 추가 하나씩 있을 경우 주어진 구슬이 3g인지도 확인해 볼 수 있다.
- <그림 2>와 같은 방법을 사용하면 구슬이 5g인지도 확인할 수 있다.
- 구슬이 2g이면 주어진 추를 가지고는 확인할 수 없다.
- 추들의 무게와 확인할 구슬들의 무게가 입력되었을 때, 주어진 추만을 사용하여 구슬의 무게를 확인 할 수 있는지를 결정하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에는 추의 개수가 자연수로 주어진다.
- 추의 개수는 30 이하이다.
- 둘째 줄에는 추의 무게들이 자연수로 가벼운 것부터 차례로 주어진다.
- 같은 무게의 추가 여러 개 있을 수도 있다.
- 추의 무게는 500g이하이며, 입력되는 무게들 사이에는 빈칸이 하나씩 있다.
- 세 번째 줄에는 무게를 확인하고자 하는 구슬들의 개수가 주어진다.
- 확인할 구슬의 개수는 7이하이다.
- 네 번째 줄에는 확인하고자 하는 구슬들의 무게가 자연수로 주어지며, 입력되는 무게들 사이에는 빈 칸이 하나씩 있다.
- 확인하고자 하는 구슬의 무게는 40,000보다 작거나 같은 자연수이다.

[출력]
- 주어진 각 구슬의 무게에 대하여 확인이 가능하면 Y, 아니면 N 을 차례로 출력한다.
- 출력은 한 개의 줄로 이루어지며, 각 구슬에 대한 답 사이에는 빈칸을 하나씩 둔다.

[구현방법]
- 이 문제가 헷갈리는 지점은 두 가지인 것 같다
    1) 추 여러 개를 상대쪽에도 더할 수 있다는 것
    2) DP 기준을 뭐로 잡아야하지...?
- 추를 올리는 방식은 총 세가지가 있더라 (왼쪽에 구슬이 항상 고정이라는 가정 하에)
    1) 왼쪽에 추 올리기
    2) 오른쪽에 추 올리기
    3) 추 사용하지 않기
- 그리고 추를 사용하면 저장해야할 값은 그 무게 아니라 현재 조합으로 만들 수 있는 무게 조합이다
- 그리고 입력 받은 모든 추로 구할 수 있는 모든 무게를 구한 뒤에서야 구슬들을 하나씩 입력 받으며 해당 무게를 구현할 수 있는지 체크하면 된다
- 가만보면 그렇게 어려운 문제는 또 아니다.
- 문제의 유형과 풀어본 경험 차이에서 오는 문제 파악과 구현정도에 따른 문제인듯 하다
- 더 많이 접해보고 더 많이 풀자
- 갠적으로 요즘 DP 풀고, 이 문제를 풀면서 느낀 것은 이거 10일 붙잡으면 혼자 풀수도 있었을 수도 있다
- 다만 이렇게 간단한 사고 방식이나 구현으로 풀 수 있는 것들이면 15분 고민해도 감도 안잡히면 답을 보는게 나한텐 더 효율적인듯 하다

[보완점]


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 추 입력 받기
        int sinkerCount = Integer.parseInt(br.readLine());
        StringTokenizer stSinker = new StringTokenizer(br.readLine());

        // 구슬 입력 받기
        int beadCount = Integer.parseInt(br.readLine());
        StringTokenizer stBead = new StringTokenizer(br.readLine());

        // 구현 가능한지 체크할 배열
        boolean[] checkWeight = new boolean[15000 + 1];

        for (int i = 0; i < sinkerCount; i++) {
            int curSinker = Integer.parseInt(stSinker.nextToken());
            boolean[] temp_checkWeight = new boolean[15000 + 1];

            for (int j = 1; j <15001; j++) {
                // 현재 도달 가능한 무게라면
                if (checkWeight[j]) {
                    temp_checkWeight[j] = true;  // 현재 무게는 true로 계쏙 가져가기 위해 다시 true
                    temp_checkWeight[j + curSinker] = true;  // 구슬 없는 쪽에 무게 추가
                    temp_checkWeight[Math.abs(j - curSinker)] = true;  // 구슬 있는 쪽에 무게 추가
                }
            }

            temp_checkWeight[curSinker] = true;  // 현재 무게는 어쨌거나 도달 가능한 곳이 되었으니 true
            checkWeight = temp_checkWeight;  // 갱신된 값으로 업데이트
        }

        for (int i = 0; i < beadCount; i++) {
            int curBead = Integer.parseInt(stBead.nextToken());

            if (15000 < curBead) sb.append("N ");  // 추들의 최댓값인 15000을 넘으면 더 볼 것도 없이 N
            else if (!checkWeight[curBead]) sb.append("N ");  // 무게 구현 가능하지 않다면 이것도 N
            else sb.append("Y ");
        }

        System.out.println(sb);
    }
}