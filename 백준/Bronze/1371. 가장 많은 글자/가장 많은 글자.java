import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/*
[백준]
1371, 가장 많은 글자

[문제파악]
- 어떤 글이 주어졌을 때, 가장 많이 나온 글자를 출력하는 프로그램을 작성하시오.

[입력]
- 첫째 줄부터 글이 주어진다.
- 글은 최대 50개의 줄로 이루어져 있고, 각 줄은 최대 50개의 글자로 이루어져 있다.
- 각 줄에는 공백과 알파벳 소문자만 있다.
- 글에 알파벳은 적어도 하나 이상 있다.

[출력]
- 첫째 줄에 가장 많이 나온 문자를 출력한다.
- 여러 개일 경우에는 알파벳 순으로 앞서는 것부터 모두 공백없이 출력한다.

[구현방법]
- 일단 매번 헷갈려하는데 TreeMap을 쓰면 일단 자동정렬해준다
- 아니 근데 key, value 형태면 결국 value로 정렬해야하는데...
- 이러면 흠...
- 별 짓 다해보다가 정직한게 젤 빠르지 않을까 하고 Max를 미리 찾아둔다음 a to z 검색하면서 max와 같은 걸 순차적으로 찾기로 했다
    - 근데 진짜 신기한건 char는 아스키코드랑 친밀해서 그런지 for문에 'a' - 'z'로 돌릴 수 있다...
    - 신기하다...

[보완점]
- while 문 조건에 중단 조건을 걸려면 아래와 같이 작성해서 넣어야한다
    - while ((input = br.readLine()) != null)



[입력 값]
english is a west germanic
language originating in england
and is the first language for
most people in the united
kingdom the united states
canada australia new zealand
ireland and the anglophone
caribbean it is used
extensively as a second
language and as an official
language throughout the world
especially in common wealth
countries and in many
international organizations


*/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> wordCount = new TreeMap<>();
        int maxCount = 0;


        String input;
        while ((input = br.readLine()) != null) {

            for (int i = 0; i < input.length(); i++) {
                char currentChar = input.charAt(i);
                if (currentChar == ' ') continue;
                wordCount.put(currentChar, wordCount.getOrDefault(currentChar, 0) + 1);
                maxCount = Math.max(maxCount, wordCount.get(currentChar));
            }
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (wordCount.getOrDefault(ch, 0) == maxCount) {
                sb.append(ch);
            }
        }

        System.out.println(sb);
    }
}