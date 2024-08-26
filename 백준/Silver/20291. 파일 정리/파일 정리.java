/*
[백준]


[문제파악]
(중략)
- 파일을 확장자 별로 정리해서 몇 개씩 있는지 알려줘
- 보기 편하게 확장자들을 사전 순으로 정렬해 줘
- 그럼 보물의 절반을 얻어내기 위해 얼른 스브러스의 노트북 파일 정리를 해줄 프로그램을 만들자!

[입력]
- 첫째 줄에 바탕화면에 있는 파일의 개수 N이 주어진다. (1 <= N <= 50,000)
- 둘째 줄부터 N개 줄에 바탕화면에 있는 파일의 이름이 주어진다.
- 파일의 이름은 알파벳 소문자와 점(.)으로만 구성되어 있다.
- 점은 정확히 한 번 등장하며, 파일 이름의 첫 글자 또는 마지막 글자로 오지 않는다.
- 각 파일의 이름의 길이는 최소 3, 최대 100이다.

[출력]
- 확장자의 이름과 그 확장자 파일의 개수를 한 줄에 하나씩 출력한다.
- 확장자가 여러 개 있는 경우 확장자 이름의 사전순으로 출력한다.

[구현방법]
- Map을 쓰면 될 듯한데, 일단 TreeMap은 순서를 줄 수 있으니까 이것 쓰면 될듯하다
- 근데 보통 값이 있다면, 거기서 +1을 하고 없으면 1을 넣어주는 코드를 아래와 같이 짜기 쉽다
    - extensions.put(input[1], extensions.getOrDefault(input[1], 0) + 1);
- 근데 사실 아래처럼 짜는게 더 깔끔하고 보기 좋다
    - extensions.compute(input[1], (key, value) -> (value == null) ? 1 : value + 1);
- 매번 까먹는데 이거 기억 좀 하자
- 사실 이해하면 그렇게 어려운 구조도 아니다
- 일단 넣을건데(compute) key, value 가지고 나와서 value가 null이면 1 넣어주고, 아니면 value + 1을 해주는 아주 합리적이고 논리적인 구조임...
- 속도 차이가 나려나? 그건 좀 궁금하다
- 심지어 Map에는 Foreach로 순환도 할 수 있다!! 완전 세상 개꿀!!
[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> extensions = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("\\.");
            extensions.compute(input[1], (key, value) -> (value == null) ? 1 : value + 1);
        }

        extensions.forEach((key, value) -> {
            sb.append(key).append(" ").append(value).append("\n");
        });

        System.out.println(sb);
    }
}