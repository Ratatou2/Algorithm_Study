/*
[백준]
16401, 과자 나눠주기

[문제파악]
- 모든 조카에게 같은 길이의 막대 과자를 나눠줘야 함
- M명의 조카가 있고 N개의 과자가 있을 때, 조카 1명에게 줄 수 있는 막대과자의 최대 길이를 구하라
- 막대 과자는 길이와 상관없이 여러조각으로 나눌 수 있지만, 하나로 합칠 수는 없다

[입력]
- 첫째줄에 조카의 수 M (1 <= M <= 1,000,000), 과자의 수 N (1 <= N <= 1,000,000)
- 둘째줄에 과자 N개의 길이들이 공백으로 구분되어 주어짐
	- 과자의 길이는 1 <= 길이 <= 1,000,000,000을 만족함

[출력]
- 첫째줄에 조카 1명에게 줄 수 있는 막대 과자의 최대 길이를 출력
- 모든 조카에게 같은 길이의 과자를 나눠줄 수 없다면 0을 출력

[구현방법]
- 0 출력하는 조건 제외하고는 이분탐색해서 빠르게 줄여나가며 찾는게 베스트일듯
- 크기를 하나 정하고 배열에서 그 크기를 만족하는 것들을 count해서 찾아야 함
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

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] cookies = new int[N];

        st = new StringTokenizer(br.readLine());
        int max = 0;
        int min = 1;
        for (int i = 0; i < N; i++) {
            cookies[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, cookies[i]);
        }

        while (min <= max) {
            int mid = (max + min) / 2;
            int count = 0;

            // 쿠키 순회하며 조건 만족하는 경우 최대 몇개까지 구할 수 있는지 count
            for (int cookie : cookies) {
                if (mid <= cookie) {
                    count += cookie / mid;  // 9 길이 과자를 구하는데 18짜리 들어오면 2개나 추가할 수 있기에 '/' 로 처리
                }
            }

            if (M <= count) min = mid + 1;  // 조건 만족하면 최솟값 증가(= 평균값 증가)하여 더 긴 과자 길이를 구하자
            else max = mid - 1;  // 조건 불만족시 최댓값 감소(= 평균값 감소)하여 더 짧은 과자 길이를 구하자
        }

        System.out.println(max);
    }
}