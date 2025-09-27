

/*
[백준]
16923, 다음 다양한 단어

[문제파악]
다양한 단어란 모두 다른 알파벳 소문자로만 이루어진 단어를 의미한다.
예를 들어, "codeplus", "coding", "algorithm"은 다양한 단어, "baekjoon", "startlink"는 다양한 단어가 아니다.
다양한 단어 S가 주어졌을 때, 사전 순으로 S의 바로 다음에 오는 다양한 단어를 구해보자.

[입력]
첫째 줄에 길이가 26보다 작거나 같은 다양한 단어 S가 주어진다.

[출력]
사전 순으로 S의 바로 다음에 오는 다양한 단어를 출력한다. 바로 다음에 오는 단어가 없는 경우에는 -1을 출력한다.

[구현방법]
- 일단 철자들을 하나씩 검사해서 사용하지 않은 철자가 있으면 앞에서부터 시작해서 맨뒤에 가져다 붙인다
- 예외사항은 모든 철자를 쓴 경우이다
- 해당 경우 자리위치를 비교해가며 자리를 바꿔 사전순으로 바로 뒤에오는 것을 찾아야 함

- 해결 못한 부분은 이제 모든 알파벳을 사용한 경우!
- 이땐 그냥 추가할 수 없으므로 뒤에서부터 탐색하며 이전 글자보다 현재 글자가 우선순위가 더 낮으면 자리를 교체하면서 답을 구할 수 있다
- 만약 이렇게 끝까지 도달했는데도 못찾았다? 그럼 답이 없는 것이니 -1 출력하면 됨

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        boolean[] isUsed = new boolean[26];
        StringBuilder sb = new StringBuilder();

        for (char temp : input.toCharArray()) {
            isUsed[temp - 'a'] = true;
        }

        if (input.length() < 26) {
            sb.append(input);
            for (int i = 0; i < 26; i++) {
                if (!isUsed[i]) {
                    sb.append(Character.toChars('a' + i));
                    break;
                }
            }
        } else {
            // 길이 == 26 (모든 알파벳 사용)
            char[] arr = input.toCharArray();

            // 뒤에서부터 탐색 (가장 오른쪽에서 바꿀 수 있는 자리를 찾는 과정)
            for (int i = 25; i > 0; i--) {
                if (arr[i] > arr[i - 1]) {
                    // arr[i-1]보다 큰 문자 중 최소를 찾아야 함
                    char minBigger = 'z' + 1;  // z보다 1 큰 것으로 갱신해서 Integer.MAX같은 것을 만들어준 것임
                    for (int j = i; j < 26; j++) {
                        // 피벗문자(arr[i-1])보다 크면서 지금까지 찾은 것(minBigger)보다 작은 문자 찾기
                        if (arr[j] > arr[i - 1] && arr[j] < minBigger) {
                            minBigger = arr[j];
                        }
                    }
                    // 결과(원본보다 크면서 가능한 가장 작은 문자열) = arr[0..i-2] + minBigger
                    for (int k = 0; k < i - 1; k++) sb.append(arr[k]);
                    sb.append(minBigger);
                    System.out.println(sb);
                    return;
                }
            }
            // 못 찾은 경우 (이미 마지막 단어)
            System.out.println(-1);
            return;
        }

        System.out.println(sb);
    }
}
