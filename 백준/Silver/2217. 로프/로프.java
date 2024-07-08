/*
[백준]
2217, 로프

[문제파악]
- N개의 로프
- 그 굵기나 길이가 다름
- 그로인해 들 수 있는 물체의 중량도 다름
- K개의 로프를 사용, 중량이 W인 물체를 들어올릴 때, 각 로프에는 W/K만큼의 중량이 걸림
- 로프에 대한 정보가 주어졌을 때, 로프들을 이용하여 들어올릴 수 있는 물체의 최대 중량을 구할 것
- 모든 로프를 사용할 필요는 없으며, 임의로 몇개만 골라도 됨

[입력]
- 첫줄에 정수 N
- 다음 N개의 줄에는 각 로프가 버틸 수 있는 최대의 중량이 주어짐 (중량 < 10000)

[출력]
- 첫줄에 답 출력

[구현방법]
- 일단 입력값 다 받고 튼튼한 순서대로 정렬
- 그런 다음 하나부터 순차적으로 추가해서 어디까지 버틸 수 있는지 계산한다
- 그리고 해당 로프 구성중 최약체 * N개의 로프로 최대값과 비교
- 기록 갱신하면 갈아치우고 아니면 다음 스텝 밟는 식으로 진행하면 될듯

- 로프가 추가되면
    1) 작은 로프인지 체크
        - 작으면 최소 로프 교체
    2) 비교
        - 들 수 있는 총 무게 구하고, min_rope * N개랑 비교
        - 더 작은 값이 해당 로프 조합으로 들 수 있는 최대치 무게
        - 구했으면 앞서 구한 총 중량과 비교
        - 더 커?
            - 그럼 교체

- 문제를... 잘 못 이해했다
- 초기엔 문제를 로프의 평균값으로 이해했어서 위와 같이 구성했는데, 단순히 최솟값의 N개(조합된 로프의 갯수) 만큼이었다...
- 왜 문제를 이리 어렵게 생각했는지 몰겠다...
- 잘 읽고, 꼼꼼히 읽고 의도를 파악토록하자 ㅠ

[보완점]


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] ropes = new int[N];
        int max_weight = 0;
        int min_rope = 999999;

        // 일단 입력값을 전부 저장한다
        for (int i = 0; i < N; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }

        // 내림차순으로 정렬 하기 위함
        ropes = Arrays.stream(ropes)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

//        System.out.println(Arrays.toString(ropes));

        for (int r = 0; r < ropes.length; r++) {
            int current_rope = ropes[r];

            min_rope = Math.min(min_rope, current_rope);
            max_weight = Math.max(max_weight, min_rope * (r + 1));
        }

        System.out.println(max_weight);
    }
}