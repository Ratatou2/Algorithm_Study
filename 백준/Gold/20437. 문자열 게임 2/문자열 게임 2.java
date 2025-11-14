

/*
[백준]
20437, 문자열 게임 2

[문제파악]
작년에 이어 새로운 문자열 게임이 있다. 게임의 진행 방식은 아래와 같다.
    - 알파벳 소문자로 이루어진 문자열 W가 주어진다.
    - 양의 정수 K가 주어진다.
    - 어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이를 구한다.
    - 어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구한다.
위와 같은 방식으로 게임을 T회 진행한다.

[입력]
문자열 게임의 수 T가 주어진다. (1 ≤ T ≤ 100)
다음 줄부터 2개의 줄 동안 문자열 W와 정수 K가 주어진다. (1 ≤ K ≤ |W| ≤ 10,000)

[출력]
T개의 줄 동안 문자열 게임의 3번과 4번에서 구한 연속 문자열의 길이를 공백을 사이에 두고 출력한다.
만약 만족하는 연속 문자열이 없을 시 -1을 출력한다.

[구현방법]
- 일단 특정 길이 탐색은... 투포인터로 해야하나? (양 끝에서 하나씩 줄여나가는 방식)
- 확실한건 map을 써서 단어 갯수를 카운트 하는게 나을 것 같다는 것 -> 오히려 알파벳[26]으로 갯수세는게 더 빠를지도
- 그런 식으로 K개가 나오면 현재 max 문자열 길이를 매번 갱신하는 로직 추가하면 될 것 같다

- 완.전.틀.림
- 걍 슬라이딩 윈도웅임 (뭔 투포인터여;;)
- 재밌는건 문자열이 K개만 있으면 되기 때문에 문자별로 K개 포함한 리스트를 만들고, 그 길이를 재면 된다는 것
- 즉, 예를들어 K=2에서 a가 3개인데 인덱스 위치가 0, 2, 5, 8라면, 0~2길이가 제일 짧다
- 그러면 이 다음엔 K개 이상만큼 문자열을 가지고 있는 알파벳에서만 위 과정을 반복하며 길이를 갱신하면 됨

[보완점]

*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            String input = br.readLine();
            int K = Integer.parseInt(br.readLine());

            // 각 알파벳의 위치를 넣을 리스트와 초기화 과정
            List<Integer>[] location = new ArrayList[26];
            for (int i = 0; i < 26; i++) location[i] = new ArrayList<>();

            // 문자열 갯수 집어넣기
            for (int i = 0; i < input.length(); i++) {
                location[input.charAt(i) - 'a'].add(i);
            }

            int minLength = Integer.MAX_VALUE;
            int maxLength = -1;

            for (int alphabet = 0; alphabet < 26; alphabet++) {
                List<Integer> temp = location[alphabet];

                // K갯수보다 적으면 조건 미만인 알파벳이다 (= 더 볼 것 없이 패스)
                if (temp.size() < K) continue;

                // K개가 포함되어야하기 때문에 범위를 잘 조절해야한다 (즉, size까지 포함하되 K를 셀 수 있는 범주 내까지만!)
                for (int i = 0; i <= temp.size() - K; i++) {
                    int start = temp.get(i);
                    int end = temp.get(i + K - 1);
                    int currLength = end - start + 1;

                    // 아래처럼 값을 갱신하면 문제 조건인 3, 4를 알아서 만족하게 된다 (*)
                    minLength = Math.min(minLength, currLength);
                    maxLength = Math.max(maxLength, currLength);
                }
            }

            // 답이 없으면 -1 그대로 출력하고 끝
            sb.append(maxLength == - 1 ? maxLength : minLength + " " + maxLength).append("\n");
        }

        System.out.println(sb);
    }
}

/*
<문제의 조건 범위는 왜 이렇게 나왔나?>
int start = temp.get(i);
int end = temp.get(i + K - 1);
int currLength = end - start + 1;

temp 리스트 = [2, 5, 8, 11] 일 때, K = 2라고 가정하면?

K개의 묶음은:
temp[0], temp[1] → i=0
temp[1], temp[2] → i=1
temp[2], temp[3] → i=2

즉, 원하는 범위를 가지기 위해서는 end에 i + K 해주고 -1을 해줘야 함

같은 맥락으로 currLength는 0으로 시작하는 인덱스 배열이기 때문에 +1을 해줘야한다
start = 5
end = 7
라고 가정한다면 5, 6, 7로 총 3개지만, 7 - 5 = 2로 오답이 나오기 때문 (걍 수학적인 계산법으로 당연한 맥락)

*/