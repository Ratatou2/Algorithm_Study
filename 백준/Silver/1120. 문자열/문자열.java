
/*
[백준]
1120, 문자열

[문제파악]
길이가 N으로 같은 문자열 X와 Y가 있을 때, 두 문자열 X와 Y의 차이는 X[i] ≠ Y[i]인 i의 개수이다.
예를 들어, X=”jimin”, Y=”minji”이면, 둘의 차이는 4이다.

두 문자열 A와 B가 주어진다. 이때, A의 길이는 B의 길이보다 작거나 같다.
이제 A의 길이가 B의 길이와 같아질 때 까지 다음과 같은 연산을 할 수 있다.

A의 앞에 아무 알파벳이나 추가한다.
A의 뒤에 아무 알파벳이나 추가한다.
이때, A와 B의 길이가 같으면서, A와 B의 차이를 최소로 하는 프로그램을 작성하시오.

[입력]
첫째 줄에 A와 B가 주어진다.
A와 B의 길이는 최대 50이고, A의 길이는 B의 길이보다 작거나 같고, 알파벳 소문자로만 이루어져 있다.

[출력]
A와 B의 길이가 같으면서, A와 B의 차이를 최소가 되도록 했을 때, 그 차이를 출력하시오.

[구현방법]
- 아니 문제를 봤는데 도저히 감이 안잡히는 것임
- 알고보니 그냥 문자열 길이 범위를 넘치지 않을 때까지 한칸씩 이동해가며 직접 다 비교하는 것이었음...
- 왜 이런 단순한게 퍼뜩 떠오르지 않는겨..

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int minDiff = Integer.MAX_VALUE;

        // 첫번째 반복문은 A가 B보다 작으니 길이를 넘지 않으면서 한칸씩 이동해서 비교할 수 있도록 한다
        for (int move = 0; move < input[1].length() - input[0].length() + 1; move++) {
            int diff = 0;

            // 두번째 반복문은 문자열에서 문자를 하나씩 비교해나간다 (이때 문자열이 같거나 긴 B쪽에 한칸씩 이동한 갯수(move)를 더해줘야한다)
            for (int i = 0; i < input[0].length(); i++) {
                if (input[0].charAt(i) != input[1].charAt(i + move)) diff++;  // 문자가 다를 때마다 +1
            }

            minDiff = Math.min(diff, minDiff);  // 최솟값 갱신
        }

        System.out.println(minDiff);
    }
}
