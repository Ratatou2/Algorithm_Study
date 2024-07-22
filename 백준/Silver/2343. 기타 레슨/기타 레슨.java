/*
[백준]
2343, 기타 레슨

[문제파악]
- 블루레이에는 총 N개의 강의가 들어간다 (강의 순서가 바뀌면 안된다)
- M개의 블루레이에 모든 영상을 녹화하기로 함
- 블루레이의 크기를 최소로하려고 한다
	- 단, M개의 블루레이는 모두 같은 크기여야 한다
- 각 강의의 길이가 분 단위(자연수)로 주어질 때, 가능한 블루레이의 크기 중 최소를 구하라

[입력]
- 첫째줄에 강의의 수 N (1 <= N <= 100,000)과 M (1 <= M <= N)
- 둘째줄에 강의의 길이가 강의 순서대로 분 단위(자연수)로 주어진다
	- 각 강의의 길이는 10,000분을 넘지 않는다

[출력]
- 첫째줄에 가능한 블루레이 크기 중 최소를 출력

[구현방법]
- 이거는 더하는 방식이 중요할 것 같은데...? (아, 순서는 바꿀 수 없구나 대충 읽는 습관을 유의하자)
- 그리고 이건 disk 갯수에 따라 Disk 용량이 max값을 초과할 수 있으니 while(min <= max)로 못할듯
- 최솟값이니까 조건 만족하자마자 끝내버리면 될 듯
- 아 그리고 max는 전체를 다 더한 크기로 잡으면 됨
- 이것도 mid 값이 있어야 시간초과가 나지 않음

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

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] lectures = new int[N];
        int min = 1;
        int max = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lectures[i] = Integer.parseInt(st.nextToken());
            min = Math.max(min, lectures[i]);
            max += lectures[i];
        }

        while (min <= max) {
            int mid = (max + min) / 2;  // 테스트해볼 Disk 사이즈
            int count = 1;  // 처음부터 Disk는 한개 시작임을 간과하지 말 것
            int tempMin = mid;

            for (int i : lectures) {
                if (tempMin - i < 0) {
                    tempMin = mid;
                    count++;
                }

                tempMin -= i;
            }

            if (count <= M) max = mid - 1;  // 갯수가 M보다 적게 사용되는 경우(count <= M), Disk 용량 크기를 줄인다 (더 많은 Disk가 사용되도록)
            else min = mid + 1;  // 갯수가 M보다 많게 사용되는 경우, Disk 용량 크기를 늘린다 (한 Disk에 더 많이 들어가도록, 갯수가 줄어들도록)
        }

        System.out.println(min);  // 최소 크기를 출력해야하니까 min 출력
    }
}