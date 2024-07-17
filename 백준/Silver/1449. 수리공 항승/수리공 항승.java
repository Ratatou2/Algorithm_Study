/*
[백준]
1449, 수리공 항승

[문제파악]
- 파이프에서 물이 새는 곳은 가장 왼쪽에서 정수만큼 떨어진 거리만 물이 샌다
- 길이가 L인 테이프를 무한개 가지고 있음
- 물이 새는 곳을 막을 때 좌우 0.5만큼 같이 막아야 한다
- 필요한 테이프의 최소 갯수를 구하라
- 테이프는 자를 수 없고, 테이프를 겹쳐서 붙이는 것도 가능하다

[입력]
- 첫째줄에 물이 새는 곳의 갯수 N, 테이프의 길이 L이 주어짐 (1 <= N, L <= 1,000 (자연수))
- 둘째줄에 물이 새는 곳의 위치가 주어짐 (1 <= 새는 곳의 위치 <= 1,000

[출력]
- 필요한 테이프의 최소 갯수

[구현방법]
- 0.5 간격을 주라는게 뭔 소린지 모르겠네 사실상 필요없는 문구 같은데
- 그냥 앞뒤로 0.5까지 덮으라는건데 이게 무슨 의미가 있는지?
- 3, 4, 5 터져있으면 3.5 ~ 5.5까지 덮으면 되는건가 3에 터졌는데 0.5 뒤인 3.5 감는게 무슨 의미가 있지? 

[보완점]
- 0.5 간격을 주라는건 내가 의문을 품었던 부분이 맞았다 앞자리 숫자에 닿기만 하면 덮은 것으로 취급하는듯...
- 문제를 나만 이해 못한걸까.. 
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int L = Integer.parseInt(input[1]);
        int tapeCount = 0;

        String[] pipesStr = br.readLine().split(" ");
        int[] pipes = new int[pipesStr.length];

        for (int i = 0; i < pipesStr.length; i++) {
            pipes[i] = Integer.parseInt(pipesStr[i]);
        }

        Arrays.sort(pipes);
//        System.out.println(Arrays.toString(pipes));

       // 첫 번째 물이 샌 곳에 테이프를 붙였을 때, 테이프가 덮는 범위 계산
        int tapeRange = (int) (pipes[0] - 0.5 + L);
        tapeCount++;

        // 배열을 정렬하여 순서대로 확인하며 테이프를 사용하는 경우를 계산
        Arrays.sort(pipes);

        for (int i = 1; i < pipes.length; i++) {
            // 현재 테이프가 덮지 못하는 위치라면 새로운 테이프 사용
            if (tapeRange < (int) (pipes[i] + 0.5)) {
                tapeRange = (int) (pipes[i] - 0.5 + L);   // 새로운 테이프로 덮을 수 있는 범위 설정
                tapeCount++;  // 필요한 테이프 개수 증가
            }
        }

        System.out.println(tapeCount);    // 필요한 최소 테이프 개수 출력
    }
}