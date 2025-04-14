/*
[백준]
17266, 어두운 굴다리

[문제파악]
- 인하대학교 후문 뒤쪽에는 어두운 굴다리가 있다.
- 겁쟁이 상빈이는 길이 조금이라도 어둡다면 가지 않는다.
- 따라서 굴다리로 가면 최단거리로 집까지 갈수 있지만, 굴다리는 어둡기 때문에 빙빙 돌아서 집으로 간다.
- 안타깝게 여긴 인식이는 굴다리 모든 길 0~N을 밝히게 가로등을 설치해 달라고 인천광역시에 민원을 넣었다.
- 인천광역시에서 가로등을 설치할 개수 M과 각 가로등의 위치 x들의 결정을 끝냈다.
- 그리고 각 가로등은 높이만큼 주위를 비출 수 있다.
- 하지만 갑자기 예산이 부족해진 인천광역시는 가로등의 높이가 높을수록 가격이 비싸지기 때문에 최소한의 높이로 굴다리 모든 길 0~N을 밝히고자 한다.
- 최소한의 예산이 들 높이를 구하자.
- 단 가로등은 모두 높이가 같아야 하고, 정수이다.
- 다음 그림을 보자.
- 가로등의 높이가 H라면 왼쪽으로 H, 오른쪽으로 H만큼 주위를 비춘다.
- 다음 그림은 예제1에 대한 설명이다.
- 가로등의 높이가 1일 경우 0~1사이의 길이 어둡기 때문에 상빈이는 지나가지 못한다.
- 아래 그림처럼 높이가 2일 경우 0~5의 모든 길이 밝기 때문에 상빈이는 지나갈 수 있다.

[입력]
- 첫 번째 줄에 굴다리의 길이 N 이 주어진다. (1 ≤ N ≤ 100,000)
- 두 번째 줄에 가로등의 개수 M 이 주어진다. (1 ≤ M ≤ N)
- 다음 줄에 M 개의 설치할 수 있는 가로등의 위치 x 가 주어진다. (0 ≤ x ≤ N)
- 가로등의 위치 x는 오름차순으로 입력받으며 가로등의 위치는 중복되지 않으며, 정수이다.

[출력]
- 굴다리의 길이 N을 모두 비추기 위한 가로등의 최소 높이를 출력한다.

[구현방법]
- 이분탐색을 해야하는 것은... 아무래도 가로등의 높이
- 그리고 감안해야하는 것은 가로등이 2개이고 둘 사이의 거리가 6이면, 높이가 3이면 충분하다는 것
- 굴다리 길이가 100,000인데, 가로등 갯수(M)도 최악의 경우 100,000이다 -> 100,000,000 (1초..?)

- 계속 의심했던 지점은 높이를 임의로 지정하고 그 높이에서 가로등들이 얼마나 밝힐 수 있는지에 대한 체크가 필요한 지점
- 근데 이거 그냥 현재 가로등 높이가 h일 때, 가로등 지점으로부터 어디깢 ㅣ밝힐 수 있는지만 체크하면 되는 것이었음
- 그 말인 즉, 2번에 서있는 가로등이 5까지 밝힐 수 있을 때, 2에서 5까지 true로 바꾸는 것이 아닌 그냥 5를 들고 있으면 된다

[보완점]
left <= right: 조건을 만족하는 최소값/최댓값을 찾을 때 사용하는 정석적인 이분탐색 패턴
이 문제는 조건을 만족하는 가장 작은 H를 구하는 문제이므로 Lower Bound를 구하는 방식이 맞다

left <= right	Lower Bound or Upper Bound	값을 정확히 찾거나, 최소/최대 만족 값을 찾을 때
left < right	불변식 유지 (예: 무한루프 방지)	경계 탐색용 or 특수한 경우

while (left < right) → mid는 버리지 않음 → right = mid
while (left <= right) → mid는 처리 후 버릴 수도 있음 → right = mid - 1

2주 남짓한 시간동안 이분탐색만 풀었고 하나 확실하게 가져가는게 있다면 아래 두가지 내용
left <= right	right = mid - 1 → mid 이미 썼으니까 버리자 (이미 result에 갱신해 뒀음)
left < right	right = mid → mid도 정답 가능성 있으니 남기자 (이떈 보통 left 또는 right가 정답임)
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] lamps;

    // 적절한 높이의 가로등 높이 찾기 (이분탐색)
    static int findMinHeight() {
        int result = 0;

        int left = 0;
        int right = N;

        while (left <= right) {
            int mid = (left + right) / 2;

            // 이분탐색 - 현재 높이로 시뮬레이션해서 모든 굴다리를 밝힐 수 있으면 mid를 result에 갱신
            if (simulateHeight(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    // 현재 가로등 높이로 굴다리를 다 밝힐 수 있는지 체크
    static boolean simulateHeight(int h) {
        int lastBrightPosition = 0;  // 가장 왼쪼갂지 밝힌 지점 기록 초기화

        for (int position : lamps) {
            int left = position - h;  // 현재 가로등 위치에서 왼쪽으로 밝힐 수 있는 거리
            int right = position + h;  // 현재 가로등 위치에서 오른쪽으로 밝힐 수 있는 거리

            // 현재 가로등의 높이로 가장 마지막 밝은 지점까지 밝힐 수 없으면 이번 높이는 더 볼 것 없다
            if (lastBrightPosition < left) return false;

            // right가 여지껏 가장 많이 밝혀진 왼쪽이니까 다시 갱신
            lastBrightPosition = right;
        }

        return N <= lastBrightPosition;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());  // 굴다리 길이
        M = Integer.parseInt(br.readLine());  // 가로등 갯수
        StringTokenizer st = new StringTokenizer(br.readLine());
        lamps = new int[M];

        // 가로등 좌표 기로갷두기
        for (int i = 0; i < M; i++) {
            lamps[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(findMinHeight());
    }
}