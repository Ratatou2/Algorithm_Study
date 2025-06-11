/*
[백준]
9375, 패션왕 신해빈

[문제파악]
해빈이는 패션에 매우 민감해서 한번 입었던 옷들의 조합을 절대 다시 입지 않는다.
예를 들어 오늘 해빈이가 안경, 코트, 상의, 신발을 입었다면, 다음날은 바지를 추가로 입거나 안경대신 렌즈를 착용하거나 해야한다.
해빈이가 가진 의상들이 주어졌을때 과연 해빈이는 알몸이 아닌 상태로 며칠동안 밖에 돌아다닐 수 있을까?

[입력]
첫째 줄에 테스트 케이스가 주어진다.
테스트 케이스는 최대 100이다.
각 테스트 케이스의 첫째 줄에는 해빈이가 가진 의상의 수 n(0 ≤ n ≤ 30)이 주어진다.
다음 n개에는 해빈이가 가진 의상의 이름과 의상의 종류가 공백으로 구분되어 주어진다.
같은 종류의 의상은 하나만 입을 수 있다.
모든 문자열은 1이상 20이하의 알파벳 소문자로 이루어져있으며 같은 이름을 가진 의상은 존재하지 않는다.

[출력]
각 테스트 케이스에 대해 해빈이가 알몸이 아닌 상태로 의상을 입을 수 있는 경우를 출력하시오.

[구현방법]
- Map<String, List<String>> 구조로 짜서 입력을 받아야할 것 같다
- 그리고 나머지들이랑 조합할 수 있는 수식 만들어서 코드 짜는거지
- 아 이거 그냥 조합식 구하는 거잖아 (= 단순 수식 계산)
- 그럼 그냥 Map<String, Integer>로 구해도 된다. 무슨 옷이 있고 어떤걸 입는게 중요한게 아니고 안겹치게 입는게 중요하니까
- 그냥 단순 수식 계산으로 해결해도 된다는 의미이다

- 그리고 조합을 구하는건 단순한 수학이다
    - 상의 3벌
    - 하의 2벌
    - 안경 2개
    와 같은 경우 조합의 갯수는? 3 * 2 * 2이다 상의 1개당 하의에서 2개, 안경에서 2개를 고를 수 있고 조합이 바지별로 존재하니까
    결국 총 12개인데 이 경우 '모두 안입는다'라는 경우의 수, 즉 문제에서 피해야하는 '알몸'상태가 있으니까 마지막에 -1을 해주는 것이다 

[보완점]
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            int N = Integer.parseInt(br.readLine());  // 의상 갯수
            Map<String, Integer> clothesMap = new HashMap<>();

            for (int i = 0; i < N; i++) {
                String[] clothe = br.readLine().split(" ");
                clothesMap.put(clothe[1], clothesMap.getOrDefault(clothe[1], 0) + 1);  // 있으면 가져오고, 없으면 1을 밀어넣음
            }

            int result = 1;
            for (int count : clothesMap.values()) {
                result *= (count + 1);  // 해당 종류 옷을 안입는 경우 포함
            }

            sb.append(result - 1).append("\n");  // 알몸인 상태(=아무것도 고르지 않은 케이스)는 제외
        }

        System.out.println(sb);
    }
}