/*
[백준]
2776, 암기왕

[문제파악]
- 수첩1 : 하루동안 본 정수들을 적어둠
- 수첩2 : 봤다고 주장하는 정수들을 적어둠
- 수첩2에 적혀있는 순서대로 각각의 수가 수첩1에 있으면 1, 없으면 0을 출력하라

[입력]
- 첫째줄에 테스트 케이스 갯수 T
- 둘째줄에 수첩1에 적어둔 정수의 갯수 N (1 <= N <= 1,000,000)
- 셋째줄에 수첩1에 적혀있는 정수들 N개
- 넷째줄에 수첩2에 적어둔 정수의 갯수 M (1 <= M <= 1,000,000)
- 다섯째줄에 수첩 2에 적어둔 정수들 M
- 모든 정수들의 범위는 int

[출력]
- 수첩 2에 적혀있는 M개의 숫자 순서대로 수첩1에 있으면 1, 없으면 0 출력

[구현방법]
- 이거 1920, 수찾기랑 똑같은 문제네
- 동일하게 수첩1은 정렬하고 이분탐색으로 진행

[보완점]
- 입력받는거 깔끔하게 하고 싶어서 기능분리 했는데 시간 초과... 이슈....
- 이상한건 전과 동일하게 짰는데 시간 초과... 뭐지..?
- 전 코드 돌려도 터짐, 방식을 바꿔보란거 같은데 Hashset이란걸 써보겠다!
- Hashset을 사용하면, 평균적으로 O(1) 시간복잡도로 요소의 존재 여부를 확인할 수 있다
- 이 문제는 사실 상 정렬이 필요없고 수첩1에 수첩2 숫자가 포함되는지만 확인하면 되기 떄문에 이렇게 풀었다
- 아니 근데 이분탐색으로 어케 푸는거지.. 다른 코드 찾아봐야할듯
- 설마 sout 그냥 한 것 때문에...? StringBuilder로 변경
- 이것도 아니고 숫자가 중복으로 나오거나 하나도 불려지지 않았을 경우의 케이스가 없었다는게 정론인듯 하다
- 모든 테케가 끝나고 출력해야하나? 매번 하는게 아니라? 테케 왜 넣어둔거야..
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static int findNumberInArray(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;  // 중간값

            if (arr[mid] == target) return 1;
            if (target < arr[mid]) end = mid - 1;
            else if (arr[mid] < target) start = mid + 1;
        }

        return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test = 0; test < T; test++) {

            int N = Integer.parseInt(br.readLine());
            int[] Array_N = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Array_N[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            int[] Array_M = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                Array_M[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(Array_N);

            for (int i = 0; i < M; i++) {
                sb.append(findNumberInArray(Array_N, Array_M[i])).append("\n");
            }
        }
        System.out.println(sb);
    }

//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//
//        int T = Integer.parseInt(br.readLine());
//
//        for (int testCase = 0; testCase < T; testCase++) {
//            int memo1 = Integer.parseInt(br.readLine());
//            HashSet<Integer> memo1List = new HashSet<>();
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            for (int i = 0; i < memo1; i++) {
//                memo1List.add(Integer.parseInt(st.nextToken()));
//            }
//
//            int memo2 = Integer.parseInt(br.readLine());
//            st = new StringTokenizer(br.readLine());
//            for (int i = 0; i < memo2; i++) {
//                int memo2Num = Integer.parseInt(st.nextToken());
//
//                sb.append(memo1List.contains(memo2Num) ? 1: 0).append("\n");
//            }
//
//            System.out.println(sb);
//        }
//    }
}