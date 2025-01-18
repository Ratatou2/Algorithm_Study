/*
[백준]
2292, 벌집

[문제파악]
- 위의 그림과 같이 육각형으로 이루어진 벌집이 있다.
- 그림에서 보는 바와 같이 중앙의 방 1부터 시작해서 이웃하는 방에 돌아가면서 1씩 증가하는 번호를 주소로 매길 수 있다.
- 숫자 N이 주어졌을 때, 벌집의 중앙 1에서 N번 방까지 최소 개수의 방을 지나서 갈 때 몇 개의 방을 지나가는지(시작과 끝을 포함하여)를 계산하는 프로그램을 작성하시오.
- 예를 들면, 13까지는 3개, 58까지는 5개를 지난다.

[입력]
- 첫째 줄에 N(1 ≤ N ≤ 1,000,000,000)이 주어진다.

[출력]
- 입력으로 주어진 방까지 최소 개수의 방을 지나서 갈 때 몇 개의 방을 지나는지 출력한다.

[구현방법]
- 단순한 수학 계산 문제
- 6배수로 방이 증가한다
- 방이 증가되는 갯수와 배수를 따로 두고 관리해야 정확한 값을 구할 수 있다
- N이 1인 경우 예외처리를 잊지 말도록 하자

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int count = 1;
        int roomCount = 1;

        if (N == 1) System.out.println(1);
        else {
            while (true) {
                roomCount += count * 6;
                if (N <= roomCount) {
                    System.out.println(count + 1);
                    break;
                }

                count++;
            }
        }
    }
}