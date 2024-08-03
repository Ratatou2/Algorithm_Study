/*
[SWEA]
B형 05 동아리실 관리하기

[문제파악]
- 동아리에 부원 네명 A, B, C, D 존재
- N일간의 활동 일정을 정해야 함
- 각 부원은 하루의 활동에 참여할지 말지를 결정하며, 어떤 부원이 참여하는지의 경우의 수는 하루에 총 16가지
	- But, 아무도 참여하지 않는 것은 안되므로 15가지 존재
- 동아리실 열쇠는 단 한개, 활동이 끝나면 잠가야하므로 열쇠를 가진사람은 무조건 활동에 참여해야 한다
- 오늘 활동에 참여하는 사람 중에 내일 활동에도 참여하는 사람이 있다면 열쇠를 넘겨줄 수 있다
- N일 동안 각 날마다 활동의 책임자가 있음. 이 책임자는 무조건 활동에 참여해야한다
- 첫째 날에 A가 열쇠를 가지고 있다
- 모든 활동이 끝나면 누가 가지고 있든 상관없다
- N일 동안의 동아리 활동을 할 수 있는 경우의 수를 출력하라
- 열쇠를 누구에게 건네주는지는 중요하지 않고 어떤 사람이 활동을 하는지 안하는지에 따라 경우의 수를 세어야 함


[입력]
- 첫번째 줄에 테스트 케이스의 수 T
- 각 테스트 케이스마다 길이 10,000 이하인 하나의 문자열이 주어짐
- 이 문자열은 A, B, C, D로 이루어져 있으며 i번째 문자는 i번째 날의 책임자가 누구인지를 나타낸다

[출력]
- 각 테스트 케이스마다 N일 동안의 동아리 활동을 할 수 있는 경우의 수를 출력하는 프로그램을 작성하라
- 이 수는 너무 커질 수 있으므로 1,000,000,007로 나눈 나머지를 출력한다

[구현방법]
- 도저히 모르겠음
- 참여하고 안하고를 비트로 표기해서 조합을 구할 수는 있겠지만, 대체 어떻게 모든 경우의 수를 계산해줘야하는지는 이해가 안감

[보완점]
- 오늘은 이 문제를 공부하고 추후에 다시 풀어보자
- 일단 DP와 비트마스킹을 사용하면 된다
- N-1일의 결과를 N일 계산하는데 사용하는 것
- 첫날에 A가 열쇠를 가지고 있다는 것도 주의할 점
- 내가 제일 어렵게 생각했던 것이, 어떻게 모든 조합의 경우를 구할 수 있느냐 하는 부분이었음.
    - 첫날에 A, B가 있다면, 둘쩃날엔 A나 B 둘중 하나는 있어야 하는데 이러면 각각의 조합으로 구하다 보면 분명 겹치는 순간이 생길 것임 (A, B, C 같은 조합)
    - 이걸 어떻게 다 예외처리를 해주지? 어떻게 카운트하지? Set을 써야하나?

- 결과적으로 이 부분은 그냥 각 조합에 대해서 비트마스킹을 진행하면 된다
- 첫쨋날 인원이 포함되어 있으면서 둘쨋날의 책임자까지 같이 존재하는

if (0 < (첫째날의 비트 & 두번째날의 비트) && 0 < (두번쨰날의 비트 & 두번째날의 담당자)) {
    그러면 두번째날의 배열에 경우의 수를 추가한다
}

- 참고해도 어렵네...
- 다시 풀자 꼭!


[입력]
2
BC
ADCBBACDCBCBACBDCABDCBA

[출력]
#1 29
#2 88253169
*/


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int divide = 1_000_000_007;

        for (int test = 1; test <= T; test++) {
            String line = br.readLine();
            // 날마다의 책임자 조합을 저장할 DP 배열 생성
            // dp[day][combination]은 day일에 해당 combination으로 가능한 경우의 수를 저장
            int[][] dp = new int[line.length()][16];
            int sum = 0;

            // 입력 받은 책임자 길이가 N일이다
            for (int day = 0 ; day < line.length(); day++) {
                int todayIncharge = 1 << (line.charAt(day) - 'A') ;  // 오늘 책임자 (ASCII 코드 값에서 'A'의 ASCII 코드 값을 뺀 결과를 계산하여서 비트마스킹)

                // 인원이 4명이니 조합 식으로, 경우의 수를 전부 다 구한다
                for (int combination = 0; combination < (1 << 4); combination++) {
                    if (day == 0) {  // 첫날일 때
                        if (0 < (combination & todayIncharge) && (combination & 1) == 1) {  // A가 키를 가지고있고, (키를 건네줄) 다른 책임자도 참여하는 경우의 수일 때
                            dp[day][combination] = 1; // 그 테스트 케이스는 유효하며 하나의 경우의 수로 저장
                        }
                    } else {  // 첫날이 아닐 때
                        if (dp[day - 1][combination] == 0) continue;  // 현 조합으로 전날 체크해서 구할 수 있는 경우의 수가 없으면 패스

                        // 현재 날에 대해 가능한 모든 조합을 탐색
                        for (int t2_case = 0; t2_case < (1 << 4); t2_case++) {
                            // 현재 책임자가 포함된 경우와 이전 날의 조합이 일치하는 경우의 수를 갱신
                            if ((0 < (todayIncharge & t2_case)) && 0 < (combination & t2_case)) {
                                // 경우의 수를 갱신하고 나머지 연산을 적용
                                dp[day][t2_case] = (dp[day - 1][combination] + dp[day][t2_case]) % divide;
                            }
                        }
                    }
                }
            }

            // DP니까 첫번째 배열만 살피면 모든 경우의 수가 다 저장되어 있다.
            for(int k = 0; k < dp[0].length; k++) {
                sum += dp[line.length() - 1][k];
                sum %= divide;
            }

            sb.append("#").append(test).append(" ").append(sum).append("\n");
        }

        System.out.println(sb);
    }
}