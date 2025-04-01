/*
[백준]
2110, 공유기 설치

[문제파악]
- 도현이의 집 N개가 수직선 위에 있다.
- 각각의 집의 좌표는 x1, ..., xN이고, 집 여러개가 같은 좌표를 가지는 일은 없다.
- 도현이는 언제 어디서나 와이파이를 즐기기 위해서 집에 공유기 C개를 설치하려고 한다.
- 최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에, 한 집에는 공유기를 하나만 설치할 수 있고, 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.
- C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 집의 개수 N (2 ≤ N ≤ 200,000)과 공유기의 개수 C (2 ≤ C ≤ N)이 하나 이상의 빈 칸을 사이에 두고 주어진다.
- 둘째 줄부터 N개의 줄에는 집의 좌표를 나타내는 xi (0 ≤ xi ≤ 1,000,000,000)가 한 줄에 하나씩 주어진다.

[출력]
- 첫째 줄에 가장 인접한 두 공유기 사이의 최대 거리를 출력한다.

[구현방법]
- 집이 연달아서 N개..? 일단 개부럽구요?
- 오늘부터 내가 약한 부분인 이분탐색 시작인데 미안한데 감이 1도 안잡힌다
- 그래도 생각해보자면 아마 1부터 집의 최대 갯수인 N까지 시도해볼 수 있겠지
- 그러면 그 중간값에서부터 시작해서 +-1씩 해가는 것이 합리적일 것이다
    - 1개와 N개까지 설치 가능하면 중간 값부터 시작해서 다시 그 중간값을 탐색하는...
    - 이 아이디어가 맞고 푸는데 문제가 없다면 더 약한 트리구조를 풀어야겠다
- 근데 중간값이 생겼다 한들, 공유기가 여러 개인데 어떻게 다.. 배치해보지?
- 아... 그냥 최적화 필요 없이 갭 차이 두고 설치할 수 있는지만 체크하면 될 것 같다...? 좌표 맥시멈이 10억 이니까 가능할듯
- 왜냐면 집의 갯수는 최대가 20만개라서 ㅇㅇ
- 시도해볼법하다 풀어보고 틀리면 그때 힌트보든 ㄱㄱ
- 일단 모든 공유기를 써야 함 (C개의 공유기를 N개의 집에 적절히 설치하라고 했음)

[보완점]
- 뭔가 알듯말듯 하다 결국 틀렸음
- 이분 탐색을 풀긴해야할듯 ㅠ

- 내가 고민했던 것은 첫 집에 설치를 어떻게 해주고 그 집 위치값을 어떻게 계속 계산해주느냐 였는데 넘 어렵게만 생각했던걸까..
- 그냥 while문 시작할 때 1개를 카운트 한 상태로 진행하고, 마지막에 공유기 설치한 집을 기록해주는 변수를 만듦으로써, 거리 계산을 지속적으로 할 수 있게 하면 됐다
- 즉, 계속 이동하면서 현재 집과 마지막 공유기를 설치한 집까지의 거리가 mid보다 크다면, 거리가 충분히 벌려진 것이기 때문에 그때, 또 하나 설치하면 되는 것
- 똑같은 유형 바로 하나 더 풀자 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 집의 갯수
        int C = Integer.parseInt(st.nextToken());  // 공유기 갯수

        int[] houses = new int[N];

        // 집 정보 입력 받기
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        // 거리 순으로 정렬
        Arrays.sort(houses);

        // 중앙값은 거리로 잰다
        int start = 1;  // 최소 거리
        int end = houses[N - 1] - houses[0];  // 가장 먼 집끼리 뺀 최댓값이 end이다
        int result = 0;

        // 둘이 같아질 때까지 반복한다
        while (start <= end) {
            int mid = (start + end) / 2;  // 현재 최소 거리 후보
            int count = 1;  // 첫번째 집에 일단 냅다 공유기 설치
            int lastLocation = houses[0];  // 마지막으로 공유기를 설치한 집으로 첫번째 집 세팅

            // 공유기 배치 (두번째 집부터 탐색)
            for (int i = 1; i < N; i++) {
                // mid보다 현재 집에서 마지막 위치보다 크면, 즉 현재 체크하는 거리에서 충분히 멀어졌다면, 현 위치에 공유기를 설치한다
                if (mid <= houses[i] - lastLocation) {
                    count++;  // 설치한 공유기 갯수 추가
                    lastLocation = houses[i];  // 현재 집으로 마지막 설치한 집 위치 변경
                }
            }

            if (C <= count) {  // 설치할 수 있는 공유기보다 많이 설치했으면, 거리를 늘려도 된다
                result = mid;  // 일단 거리 늘렸다가 터질 수 있으니 거리 갱신해두기
                start = mid + 1;
            } else {  // 설치할 수 있는 공유기보다 적게 설치했으면, 거리를 줄여야 한다
                end = mid - 1;
            }
        }

        System.out.println(result);
    }
}