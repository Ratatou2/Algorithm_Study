package Algorithm_0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_1931_ReservationMeetingRoom {
    // class 정의하고 써보기
    static class MeetingRoom {
        int startTime;
        int endTime;
        public MeetingRoom(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int count = 0;
        int prevEndTime = 0;
        MeetingRoom[] timeList = new MeetingRoom[N];

        // 예약 시간 입력 받기
        for (int i = 0; i < N; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            timeList[i] = new MeetingRoom(temp[0], temp[1]);
        }

        // 정렬하기 편집
        // 1. 기본적으로 종료 시간이 같으면 시작 시간이 짧은 것으로 비교 순서 정렬
        // 2. 그게 아니라면 종료시간이 우선인 것으로 정렬
        // 참고로 comparable 결과값에 따른 정렬은 아래와 같다 (결과값의 '부호'에 의해 위치가 결정됨)
        // 양수 : 자리 바꿈
        // 음수 : 자리 유지
        // 0 : 동일 위치
        Arrays.sort(timeList, (reservation1, reservation2) -> {
            if (reservation1.endTime == reservation2.endTime) return reservation1.startTime - reservation2.startTime;
            return reservation1.endTime - reservation2.endTime;
        });

        for (int i = 0; i < N; i++) {
            if (timeList[i].startTime < prevEndTime) continue;  // 시작 시간이 이전 종료시간보다 작으면 패스
            
            prevEndTime = timeList[i].endTime;
            count++;
        }

        System.out.println(count);
    }
}

// 회의실 문제는 종료시간으로 정렬해야 함
// 겹치는 것들은 배제
// 종료 시간이 같을 경우,  시작시간이 빨라야 가장 많이 담을 수 있음
// 아래와 같이 종료시간 기준으로 정렬하고 처음부터 for문 돌리면 됨
// 이전 종료 시간이 현재의 시작 시간보다 작으면 넣을 수 있다는 의미니 그런식으로 갱신하면 된다
//1 | 4
//3 | 5
//0 | 6
//5 | 7
//3 | 8
//5 | 9
//6 | 10
//8 | 11
//8 | 12
//2 | 13
//12 | 14

// 주의해야할 부분은 끝나는 시간과 시작 시간이 같으면 포함할 수 있다는 것