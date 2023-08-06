package Algorithm_0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// IM 기출
public class 백준_1244_SwitchOnOFF {
    static int switchCount;
    static int[] switchStatus;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        switchCount = Integer.parseInt(br.readLine());
        switchStatus = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int studentCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < studentCount; i++) {
            int[] studentInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int gender = studentInfo[0];
            int switchNumber = studentInfo[1];

            // 남학생의 경우
            if (gender == 1) {
                // 남학생 조건 : 남학생은 받은 스위치의 배수가 있으면 바꿈
                // 배수를 쓸거니까 num은 1부터 시작 + 배수를 구해야하니까 switchCount를 포함하는 범주로 줘야함
                for (int num = 1; num <= switchCount; num++) {
                    // 배열이니까 배수를 구하려면 N 곱하고 -1 한 위치가 배수의 위치임
                    int switchLocation = (switchNumber * num) - 1;

                    // 배수의 범주가 인덱스 범주 안쪽이면 진행
                    if (checkSwitchIndex(switchLocation)) reverseSwitch(switchLocation);
                    else break;
                }


            }
            // 여학생의 경우
            else if (gender == 2) {
                // 여학생 조건 : 받은 스위치 기준으로 (본인 포함) 좌우대칭인 부분  전부다 반전
                int switchLocation = switchNumber - 1;
                reverseSwitch(switchLocation);

                // 배수를 쓸거니까 num은 1부터 시작 + 배수를 구해야하니까 switchCount를 포함하는 범주로 줘야함
                for (int num = 1; num <= switchCount; num++) {
                    int leftLocation = switchLocation - (1 * num);
                    int rightLocation = switchLocation + (1 * num);

                    // 현 스위치와 좌우의 인덱스가 모두 배열 범위에 포함되면 진행
                    if (checkSwitchIndex(switchLocation) &&
                            checkSwitchIndex(leftLocation) && checkSwitchIndex(rightLocation)) {
                        // 스위치가 대칭의 값을 가지고 있으면 반전
                        if (switchStatus[leftLocation] == switchStatus[rightLocation]) {
                            reverseSwitch(leftLocation);
                            reverseSwitch(rightLocation);
                        } else break;  // 더 이상 좌우 대칭 아니면 끝내기
                    }
                }
            }

        }

        for (int i = 1; i < switchCount + 1; i++) {
            System.out.print(switchStatus[i-1] + " ");
            if (i % 20 == 0) {
                System.out.println();
            }
        }

    }
    // 스위치 인덱스 범주 내인지 체크 기능 분리
    private static boolean checkSwitchIndex(int switchLocation) {
        boolean result = false;
        if (0 <= switchLocation && switchLocation < switchCount) result = true;

        return result;
    }


    // 스위치 반전 기능 분리
    private static void reverseSwitch(int switchLocation) {
        if (switchStatus[switchLocation] == 0) switchStatus[switchLocation] = 1;
        else if (switchStatus[switchLocation] == 1) switchStatus[switchLocation] = 0;
    }
}