/*
[백준]
1920, 수 찾기

[문제파악]
- N개의 정수가 주어진다 (A[1], A[2], …, A[N])
- 이 안에서 X라는 정수가 존재하는지 알아내라

[입력]
- 첫째줄에 자연수 N (1 <= N <= 100,000)
- 둘째줄에 N개의 정수 (A[1], A[2], …, A[N])
- 셋째줄에 자연수 M
- 넷째줄에 M개의 수, 이 수들이 A 안에 존재하는지 알아내면 된다

[출력]
- M개의 줄에 답을 출력한다.
- 존재하면 1, 존재하지 않으면 0

[구현방법]
- 정렬한다음 중간 값 기준으로 나눠가며 찾는 방식이 제일 무난할듯하다

[보완점]
- 이분 탐색할거니까 N은 정려해도 되는데 M은 하면 안된다! 이걸 깜빡..
- 시간초과!
- 일단 범위 설정들이 잘못되었음
- mid로 찾는다는 생각으로 배열을 탐색하면 더 빠르게 시간을 단축하고, 코드도 간결해진다
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static boolean findNumberInArray(int[] arr, int target) {
        boolean found = false;

        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;  // 중간값

            if (arr[mid] == target) return true;
            if (target < arr[mid]) end = mid - 1;
            if (arr[mid] < target) start = mid + 1;
        }

        return found;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
            boolean result = findNumberInArray(Array_N, Array_M[i]);

            if (result) System.out.println("1");
            else System.out.println("0");

        }
    }
}