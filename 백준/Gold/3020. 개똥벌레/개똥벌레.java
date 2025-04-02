/*
[백준]
3020, 개똥벌레

[문제파악]
- 개똥벌레 한 마리가 장애물(석순과 종유석)로 가득찬 동굴에 들어갔다.
- 동굴의 길이는 N미터이고, 높이는 H미터이다. (N은 짝수)
- 첫 번째 장애물은 항상 석순이고, 그 다음에는 종유석과 석순이 번갈아가면서 등장한다.
- 아래 그림은 길이가 14미터이고 높이가 5미터인 동굴이다. (예제 그림)
- 이 개똥벌레는 장애물을 피하지 않는다.
- 자신이 지나갈 구간을 정한 다음 일직선으로 지나가면서 만나는 모든 장애물을 파괴한다.
- 위의 그림에서 4번째 구간으로 개똥벌레가 날아간다면 파괴해야하는 장애물의 수는 총 여덟개이다. (4번째 구간은 길이가 3인 석순과 길이가 4인 석순의 중간지점을 말한다)
- 하지만, 첫 번째 구간이나 다섯 번째 구간으로 날아간다면 개똥벌레는 장애물 일곱개만 파괴하면 된다.
- 동굴의 크기와 높이, 모든 장애물의 크기가 주어진다.
- 이때, 개똥벌레가 파괴해야하는 장애물의 최솟값과 그러한 구간이 총 몇 개 있는지 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 N과 H가 주어진다.
- N은 항상 짝수이다. (2 ≤ N ≤ 200,000, 2 ≤ H ≤ 500,000)
- 다음 N개 줄에는 장애물의 크기가 순서대로 주어진다.
- 장애물의 크기는 H보다 작은 양수이다.

[출력]
- 첫째 줄에 개똥벌레가 파괴해야 하는 장애물의 최솟값과 그러한 구간의 수를 공백으로 구분하여 출력한다.

[구현방법]
- 감을.. 못잡겠는데?
- 정렬해서 주는거랑 뭔상관임...? 구간별로 누적해서 구하는건 이해하겠는데 이분탐색이 뭔 의미임..?
- 그냥 누적합하긴했는데... 터질수도...? 맞더라도 이분탐색으론 어떻게 푸는지 찾아봐야겠다
- 음 역시 골드답게 단순 누적합으론 시간 초과! 더 효율적인 방법이 있다는 뜻이다 (물론 난 그걸 몰라서 못품 -> 참고해야겠지? + 고민한지 40분 초과)

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, H;
    static int[] ceiling, bottom;

    // 이분 탐색을 사용하여 특정 높이 이상인 장애물 개수 찾기
    private static int countObstacles(int h, int[] arr) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;  // 중간 지점 index 찾아주고

            if (h <= arr[mid]) {
                right = mid;  // 찾고 있는 현재 높이보다, 크거나 같은 친구를 발견하면 right를 mid로 옮긴다
                // left가 아닌 right를 옮기는 이유는, 우리가 찾는건 h이상인 값이 처음 나오는 위치를 찾고 있기 때문이다
                // 이 말인 즉, 이분탐색이기 때문에 mid를 right로 만들어서 다시 왼쪽값에서부터 탐색하는 것이다
                // 그렇게 해야만, h보다 작은 부분의 시작점을 알게되고 그것을 전체의 배열의 길이에서 빼면, h보다 큰 것들이 몇개나 있는지를 알 수 있다
            } else left = mid + 1;
        }

        return arr.length - right;  // h 이상인 장애물 개수 반환
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 동굴의 길이
        H = Integer.parseInt(st.nextToken());  // 동굴의 높이

        // 홀짝 입력으로 들어오기 때문에, 배열 크기도 절반이면 된다
        ceiling = new int[N / 2];  // 종유석 배열
        bottom = new int[N / 2];  // 석순 배열

        // 입력 받기
        for (int i = 0; i < N / 2; i++) {
            bottom[i] = Integer.parseInt(br.readLine());
            ceiling[i] = Integer.parseInt(br.readLine());
        }

        // 정렬 (이분 탐색을 위해 필요)
        Arrays.sort(ceiling);
        Arrays.sort(bottom);

        int minObstacles = Integer.MAX_VALUE;  // 최소 장애물 개수
        int count = 0;  // 최소 장애물이 나타나는 높이 개수

        // 높이 1부터 H까지 모든 경우 체크
        for (int h = 1; h <= H; h++) {
            // 바닥에서 재는건 그냥 개똥벌레가 날아다닐 h로 재면 되지만,
            // 천장에서 재는건 최대 높이에서 빼야하니까 H - h + 1로 잰다
            // 어쩌면 너무 당연한 소리인데, 둘다 오름차순 정렬해뒀으니까 헷갈릴 수 있음...
            int obstacles = countObstacles(h, bottom) + countObstacles(H - h + 1, ceiling);

            // 최솟값 갱신을 해야한다면, 기존의 있는 값을 대체하고, 해당 길이로 다시 count할 것이다
            if (obstacles < minObstacles) {
                minObstacles = obstacles;
                count = 1;
            } else if (obstacles == minObstacles) {
                count++;  // 같은 갯수를 찾았으면, count + 1
            }
        }

        System.out.println(minObstacles + " " + count);
    }
}