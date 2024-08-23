/*
[백준]
1034, 명령프롬프트

[문제파악]
- 이 문제는 검색 결과가 먼저 주어졌을 때, 패턴으로 뭘 쳐야 그 결과가 나오는지를 출력하는 문제이다.
- 패턴에는 알파벳과 "." 그리고 "?"만 넣을 수 있다. 가능하면 ?을 적게 써야 한다.
- 그 디렉토리에는 검색 결과에 나온 파일만 있다고 가정하고, 파일 이름의 길이는 모두 같다.

[입력]
- 첫째 줄에 파일 이름의 개수 N이 주어진다.
- 둘째 줄부터 N개의 줄에는 파일 이름이 주어진다.
- N은 50보다 작거나 같은 자연수이고 파일 이름의 길이는 모두 같고 길이는 최대 50이다.
- 파일이름은 알파벳 소문자와 '.' 로만 이루어져 있다.

[출력]
- 첫째 줄에 패턴을 출력하면 된다.

[구현방법]
- 이거 이중 for문 도는 것 외엔 잘 모르겠다
- 음.. 일단 charAt()으로 각 자리를 비교해야하는 것은 필수니까
- 근데 N이 매번 달라지니까 모든 철자를 비교하려면 이중 for문 해야할듯하다...
- 더 좋은 방법이 있을까? 정렬은 딱히 도움 안 될 것 같은게 다른게 제일 마지막에 나오면 답이 없...

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static String[] outputs;

    static String checkSameString(int index) {
        char temp = outputs[0].charAt(index);

        for (int i = 0; i < N; i++) {
            if (temp == outputs[i].charAt(index)) continue;
            else return "?";
        }

        return String.valueOf(temp);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        outputs = new String[N];

        // 결과값 입력받기
        for (int i = 0; i < N; i++) outputs[i] = br.readLine();

        // 각 index에 일치하는 철자들이 같은지 확인
        for (int i = 0; i < outputs[0].length(); i++) {
            sb.append(checkSameString(i));
        }

        System.out.println(sb);
    }
}