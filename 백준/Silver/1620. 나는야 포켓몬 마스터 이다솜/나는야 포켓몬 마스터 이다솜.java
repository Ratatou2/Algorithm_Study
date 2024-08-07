/*
[백준]
1620, 나는야 포켓몬 마스터 이다솜

[문제파악]
- 컨셉에 충실한, 가독성은 개나줘버림

[입력]
- 첫째줄에 도감에 수록된 포켓몬 갯수 N, 맞춰야하는 문제 갯수 M이 주어진다 (1 <= N, M <= 100,000 (자연수))
- 둘째줄부터 N개의 포켓몬이 입력되며, 입력되는 순서대로 index이다 (1~ )
- 첫글자만 대문자, 나머지는 소문자, 일부는 마지막 글자만 대문자 일수도 있다
- 이름의 최대 길이는 20, 최소 길이는 2
- 그 이후로 M개의 줄에 걸쳐 맞춰야하는 문제다 (1 <= 입력 <=  N)
- 입력에 들어오는 포켓몬은 반드시 도감에 있다

[출력]
- 첫째줄부터 차례대로 문제에 대한 답을 말하면 된다
	- 문자라면 숫자
	- 숫자라면 문자

[구현방법]
- index용 배열 하나와 Map을 써서 풀어야할듯
- 입력값이 숫자냐 문자냐 판별하는 것은 try-catch문을 사용하였다
- 문자열을 숫자 변환 해보고 성공하면 숫자인 것이고, 실패하면 문자열인 것으로 판단함

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] pokemon = new String[N];
        Map<String, Integer> guideBook = new HashMap<String, Integer>();

        // 포켓몬 입력
        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            pokemon[i] = input;  // 배열은 index 자체니까, 고대로 포켓몬 이름 저장
            guideBook.put(input, i + 1);  // [이름:index]로 매칭하여 저장
        }

        for (int i = 0; i < M; i++) {
            String input = br.readLine();

            try {
                int NumberInput = Integer.parseInt(input);
                sb.append(pokemon[NumberInput - 1]);
            } catch (NumberFormatException e) {
                sb.append(guideBook.get(input));
            }
            
            sb.append("\n");
        }

        System.out.println(sb);
    }
}