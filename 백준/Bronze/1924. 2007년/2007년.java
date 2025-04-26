/*
[백준]
1924, 2007년

[문제파악]
오늘은 2007년 1월 1일 월요일이다.
그렇다면 2007년 x월 y일은 무슨 요일일까?
이를 알아내는 프로그램을 작성하시오.

[입력]
첫째 줄에 빈 칸을 사이에 두고 x(1 ≤ x ≤ 12)와 y(1 ≤ y ≤ 31)이 주어진다.
참고로 2007년에는 1, 3, 5, 7, 8, 10, 12월은 31일까지, 4, 6, 9, 11월은 30일까지, 2월은 28일까지 있다.

[출력]
첫째 줄에 x월 y일이 무슨 요일인지에 따라 SUN, MON, TUE, WED, THU, FRI, SAT중 하나를 출력한다.

[구현방법]
- 날짜 계산하는걸 잘 짜야할듯하다
- 7일차인 일요일은 나머지가 0임을 주의하자

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 요일을 index에 맞춰서 미리 세팅해둔다 (일요일은 7일째라서 7로 나누면 나머지가 0이다 - 주의할 것)
        String[] days = new String[] {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        Map<Integer, Integer> months = new HashMap<>();

        // 월별 일자 입력
        for (int i = 1; i <= 12; i++) {
            int temp = 0;

            if (i == 4 || i == 6 || i == 9 || i == 11) temp = 30;
            else if (i == 2) temp = 28;
            else temp = 31;

            months.put(i, temp);
        }

        // 월 & 일 입력받기
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        // 날짜 계산
        int count = 0;
        for (int i = 1; i < x; i++) {
            count += months.get(i);
        }

        // 7로 나눈 나머지로 요일을 구한다
        System.out.println(days[(count + y) % 7]);
    }
}