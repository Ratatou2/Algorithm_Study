/*
[백준]
2428, 표절

[문제파악]
- 세계적인 석유 재벌이 1등 상품으로 페라리를 걸고 프로그래밍 대회를 개최했다.
- 이 대회의 참석자는 총 N명이고 각각 솔루션 파일 1개를 제출했다.
- 이 솔루션 파일을 F1, F2, ..., Fn이라고 한다.
- 채점 결과를 발표하기 전에, 남의 것을 배낀 사람이 있는지 찾아내려고 한다.
- 이 대회의 주최측은 두 파일을 비교해서 너무 비슷한지 아닌지 판별하는 프로그램이 있다.
- 하지만, 제출한 파일의 개수가 너무 많아서, 모든 쌍을 검사한다면, 2012년 지구가 멸망할 때 까지도 검사를 해야할 판이다.
- 따라서, 파일 크기가 너무 다른 경우에는 그러한 쌍을 검사하지 않고 넘어가기로 했다.
- 좀더 정확하게 하기 위해서, 대회의 심판들은 두 파일이 있을 때, 작은 파일의 크기가 큰 파일 크기의 90%보다도 작을 때는, 이러한 쌍은 검사하지 않고 넘어가기로 했다.
- 따라서, (Fi, Fj) 쌍을 검사해야 하는데, 이때, i≠j이고, size(Fi) ≤ size(Fj)이면서, size(Fi) ≥ 0.9 × size(Fj)인 쌍만 검사하려고 한다.
- 몇 개의 쌍을 검사해야 하는 지 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 제출한 솔루션의 개수 N이 주어진다.
- 둘째 줄에는 각 솔루션 파일의 크기 size(F1), size(F2), ..., size(FN)이 주어진다. (1 ≤ N ≤ 100,000, 1 ≤ size(Fi) ≤ 100,000,000)
- 솔루션 파일의 크기는 정수이다.

[출력]
- 첫째 줄에 검사해야 하는 파일의 개수를 출력한다.

[구현방법]
- Lower Bound를 찾는 문제라는데 그게 대체 뭔지.. 싶었다가 공부를 하자 싶었음
- 알고보니 별게 없음 그냥 찾던 조건이 처음으로 나오는 위치인 셈
    < Lower Bound란? >
    - 찾고자 하는 값 이상이 처음으로 나타나는 위치를 말함
    - 즉, target보다 크거나 같은 값이 처음 나오는 인덱스를 찾는 것

    좀 더 확장하면 아래처럼 나눠 쓸 수 있다
    🔵 경계 찾기 (lower/upper bound)	최초로 조건 만족하는 인덱스, 범위 개수 세기 등	    left < right	right = mid
    🔴 특정 값 찾기 / 조건만족 여부 판단	target 찾기, 존재 유무 확인 등	                left <= right	right = mid - 1

- 그러니까 이 문제는 투포인터 풀이도 되고 더 빠르겠지만, 나는 이분탐색을 연습할거니까...
- 이분탐색으로 할 시, 문제 조건에 맞는 지점을 이분탐색으로, lower bound를 찾아서 해당 인덱스까지의 범위를 더해주면 된다
- 깨닫고 정리하니까 말은 쉽지 왤케 처음 접하면 어디다가 이분탐색 적용해야할질 모르겠냐...
- 근데 이건 그냥 문제 많이 풀면 해결된 문제긴함..

- 경우의 수가 int 형을 넘는다네..
[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] files;

    // 조건을 만족하는 첫 위치 찾는 함수
    static int findLowerBound(double value) {
        int left = 0;
        int right = files.length;

        while (left < right) {
            int mid = (left + right) / 2;

            // files[mid]가 검사 대상의 하한선(value)보다 작으면,
            // 해당 파일은 조건 (큰 파일의 90% <= 작은 파일 크기)을 만족하지 못하므로 제외
            // → 그보다 큰 값에서 다시 탐색
            if (files[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;  // 조건에 부합하지 않는, 제외해야하는 index를 알게 됐다
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        files = new int[N];

        // 값 입력 받기
        for (int i = 0; i < N; i++) {
            files[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(files);

        long count = 0;
        for (int i = 0; i < N; i++) {
            // 현재 위치에서 조건에 부합하지 않는 index(즉, 갯수)를 빼버리면 됨
            count += i - findLowerBound(files[i] * 0.9);
        }

        System.out.println(count);
    }
}