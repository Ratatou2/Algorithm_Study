

/*
[백준]
10610, 30

[문제파악]
어느 날, 미르코는 우연히 길거리에서 양수 N을 보았다.
미르코는 30이란 수를 존경하기 때문에, 그는 길거리에서 찾은 수에 포함된 숫자들을 섞어 30의 배수가 되는 가장 큰 수를 만들고 싶어한다.
미르코를 도와 그가 만들고 싶어하는 수를 계산하는 프로그램을 작성하라.

[입력]
N을 입력받는다. N는 최대 105개의 숫자로 구성되어 있으며, 0으로 시작하지 않는다.

[출력]
미르코가 만들고 싶어하는 수가 존재한다면 그 수를 출력하라. 그 수가 존재하지 않는다면, -1을 출력하라.

[구현방법]
- 입력 받은 숫자를 조합해서 30의 배수중 가장 큰 수를 완성하면 된다
- 일단 30의 배수니까 맨 끝에 0이 와야하고 그 뒤로는 3의 배수면 될 것 같다
- 그런식으로 30의 배수로 떨어지도록 준비하였으면 맨 앞에서부터는 가장 큰 수대로 나열하면 되는 것이다

- 아 그리고 이 문제를 푸는 핵심은 아래 명제를 알고 있어야만 가능하다
    '어떤 수가 3의 배수인지 알고 싶다면, 그 수의 모든 자릿수를 더해서 3으로 나눈 나머지를 보면 된다. 나머지가 0이면 그 수는 3의 배수다.'
    '마찬가지로 9의 경우에는, 자릿수의 합이 9의 배수인지를 보면 된다'

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] input= br.readLine().toCharArray();
        int[] count = new int[10];  // 각각의 숫자 요소들 카운트
        int result = -1;            // 결과값
        int sum = 0;                // 숫자들의 총 합산

        // 숫자 하나씩 잘라서 +1 (무슨 숫자가 얼만큼 있는지 파악하기 위함
        for (char temp : input) {
            int curr = temp - '0';
            count[curr]++;
            sum += curr;
        }

        // 30의 배수가 가능한지 체크 (0이 한개 이상 있고, 숫자들의 합산이 3의 배수일 경우)
        // 가능하다면? 가장 큰 수를 만들면 된다
        if (0 < count[0] && sum % 3 == 0) {
            // 큰 숫자부터 역순으로 출력
            for (int i = 9; 0 <= i; i--) {
                int repeat = count[i];

                // 숫자 갯수만큼 반복
                for (int temp = 0; temp < repeat; temp++) {
                    sb.append(i);
                }
            }
        } else sb.append(-1);

        System.out.println(sb);
    }
}
