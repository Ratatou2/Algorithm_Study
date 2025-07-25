
/*
[백준]
18870, 좌표 압축

[문제파악]
수직선 위에 N개의 좌표 X1, X2, ..., XN이 있다.
이 좌표에 좌표 압축을 적용하려고 한다.
Xi를 좌표 압축한 결과 X'i의 값은 Xi > Xj를 만족하는 서로 다른 좌표 Xj의 개수와 같아야 한다.
X1, X2, ..., XN에 좌표 압축을 적용한 결과 X'1, X'2, ..., X'N를 출력해보자.

[입력]
첫째 줄에 N이 주어진다.
둘째 줄에는 공백 한 칸으로 구분된 X1, X2, ..., XN이 주어진다.

[출력]
첫째 줄에 X'1, X'2, ..., X'N을 공백 한 칸으로 구분해서 출력한다.

[구현방법]
- 대체 이게 뭔소린가 했다
- 이해하고 보니, 별것 아니지만, 불친절하긴하네..
- 일단 쉽게 말해서 원소들을 정렬하고, 각 숫자에 인덱스를 할당한 뒤, 원본 리스트를 각 숫자의 인덱스로 표현하라는 의미였다
- 그러니까 10, 2, 3, -1이 있으면?
    - -1, 2, 3, 10이 될거고
    -  0, 1, 2, 3 인덱스를 가질 것이다
    - 그럼 원본에선 3, 1, 2, 0이 되겠지
    - 각 숫자(좌표)간의 차이는 실제 숫자 크기와 상관없이 1씩 차이나게 될 것이므로 좌표 압축이란 소리였다..
- 구현하는 건 중복제거를 위한 Set / 정렬 / 인덱스에 해당하는 값을 한번에 찾기 위한 Map 정도가 있을 것 같다
    - 참고로 중복 제거 & 정렬도 해야하니 TreeSet이 좋을듯하다
[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        TreeSet<Integer> order = new TreeSet<>();

        // 요소들 입력 받기
        int[] location = new int[N];
        for (int i = 0; i < N; i++) {
            location[i] = Integer.parseInt(st.nextToken());
            order.add(location[i]);
        }

        // 좌표 압축해서 기록해두기 (Map에 각 숫자들의 index들을 기록해두기)
        Map<Integer, Integer> indexes = new HashMap<>();
        int index = 0;
        for (int temp : order) {
            indexes.put(temp, index++);
        }

        // 원본 좌표(숫자)들 순서대로 인덱스 기록 가져오기
        for (int temp : location) {
            sb.append(indexes.get(temp)).append(" ");
        }

        System.out.println(sb);
    }
}