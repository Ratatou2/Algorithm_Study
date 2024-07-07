/*
[백준]
2828, 사과담기 게임

[문제파악]
- 스크린이 N칸으로 나뉘어 있음
- 아래쪽에는 M칸을 차지하는 바구니 (M < N)
- 플레이어는 게임하는 중에 바구니를 왼쪽이나 오른쪽으로 이동 가능
- But, 바구니는 스크린의 경계를 넘어가면 안됨
- 가장 처음에 바구니는 왼쪽 M칸을 차지 중
- 스크린 위에서 사과가 떨어지고 사과는 N칸 중 한칸의 상단에서 떨어지기 시작
- 스크린의 바닥에 닿을 때까지 떨어진다
- 사과가 바닥에 닿는 즉시 다른 사과가 떨어지기 시작
- 바구니가 사과가 떨어지는 칸을 차지 하고 있다면, 바구니는 그 사과가 바닥에 닿을 때, 사과를 담을 수 있음
- 사과를 모두 다으려할 때 바구니의 이동거리의 최솟값을 구하는 프로그램을 작성할 것

[입력]
- N과 M이 주어짐 ( 1<= M < N <= 10)
- 떨어지는 사과의 갯수 J가 주어짐 (1 <= j <= 20)
- 사과가 떨어지는 위치가 순서대로 주어짐

[출력]
- 모든 사과를 담기위해 바구니가 이동해야하는 거리의 최솟값을 출력

[구현방법]
- 음 바구니의 왼쪽을 start, 오른쪽을 end로 잡고 가장 가까운 쪽이 이동하게 하는게 낫지 않나...?
- 근데 이 케이스엔 이후에 떨어지는 사과를 고려안하기 때문에 효율적이냐고 묻는다면 그건 또 아님

[보완점]
- 왼쪽으로 이동할 땐 -해줘야하는 것 잊지 말기
- 그렇다고 총 이동하는 거리에 더해줄 값이 마이너스이면 그것은 틀린 것임

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 인풋 받는 2가지 방법
        // part 1 -> 메모리와 시간이 더 들수도 있다네..? 근데 한줄 컷에 변환까지 편리하긴 함...
        int[] temp_input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        // part 2 -> 그냥 하나씩 하는거...
//        String temp_int = br.readLine();
//
//        // 문자열의 길이만큼의 배열 생성
//        int[] charArray = new int[temp_int.length()];
//
//        // 문자열의 각 문자를 배열에 저장
//        for (int i = 0; i < temp_int.length(); i++) {
//            charArray[i] = temp_int.charAt(i);
//        }

        int basket_left = 1;
        int basket_right = temp_input[1];
        int total_move = 0;

        int apple_count = Integer.parseInt(br.readLine());

        for (int i = 0; i < apple_count; i++) {
            int apple_location = Integer.parseInt(br.readLine());
            int move = 0;

            // 떨어지는 사과 위치가 바스켓 안쪽이면 이동할 필요 X
            if (basket_right < apple_location) {
                move = apple_location - basket_right;
                basket_left += move;
                basket_right += move;
//                System.out.println("오른쪽으로 이동 필요 : " + move);
            } else if (apple_location < basket_left) {
                move = apple_location - basket_left;
                basket_left += move;
                basket_right += move;
//                System.out.println("왼쪽으로 이동 필요 : " + move);
            }

            total_move += Math.abs(move);
//            System.out.println("========================");
        }

        System.out.println(total_move);
    }
}