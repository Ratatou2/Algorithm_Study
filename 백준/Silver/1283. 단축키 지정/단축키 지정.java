/*
[백준]


[문제파악]
- 한글 프로그램의 메뉴에는 총 N개의 옵션이 있다.
- 각 옵션들은 한 개 또는 여러 개의 단어로 옵션의 기능을 설명하여 놓았다.
- 그리고 우리는 위에서부터 차례대로 각 옵션에 단축키를 의미하는 대표 알파벳을 지정하기로 하였다.
- 단축키를 지정하는 법은 아래의 순서를 따른다.
    1. 먼저 하나의 옵션에 대해 왼쪽에서부터 오른쪽 순서로 단어의 첫 글자가 이미 단축키로 지정되었는지 살펴본다.
    2. 만약 단축키로 아직 지정이 안 되어있다면 그 알파벳을 단축키로 지정한다.
    3. 만약 모든 단어의 첫 글자가 이미 지정이 되어있다면 왼쪽에서부터 차례대로 알파벳을 보면서 단축키로 지정 안 된 것이 있다면 단축키로 지정한다.
    4. 어떠한 것도 단축키로 지정할 수 없다면 그냥 놔두며 대소문자를 구분치 않는다.
    5. 위의 규칙을 첫 번째 옵션부터 N번째 옵션까지 차례대로 적용한다.

[입력]
- 첫째 줄에 옵션의 개수 N(1 ≤ N ≤ 30)이 주어진다.
- 둘째 줄부터 N+1번째 줄까지 각 줄에 옵션을 나타내는 문자열이 입력되는데, 하나의 옵션은 5개 이하의 단어로 표현되며, 각 단어 역시 10개 이하의 알파벳으로 표현된다.
- 단어는 공백 한 칸으로 구분되어져 있다.

[출력]
- N개의 줄에 각 옵션을 출력하는데 단축키로 지정된 알파벳은 좌우에 [] 괄호를 씌워서 표현한다.

[구현방법]
- 일단 stringTokenizer로 받을까 했는데, 사실 한번에 답이 나오는게 아니면 인풋을 계속 재사용 해야하기 때문에 배열로 받는게 나을듯하다
- 그리고 대문자 ~ 소문자 체크해줄 52 boolean 배열 하나 만들고 (대문자 A부터 소문자 z까지 사용 여부 확인용), 거기다가 체크하기
- .split()으로 쪼갠다음 String[]으로 저장해서 for문 돌린다
- 돌리고 나서 첫번째 글자에
    - 단축키가 사용 중이면, 다음 단어 체크
    - 사용 중이지 않으면, 그대로 앞뒤 [] 치고 출력
- 첫번째 검사로직에서 결과가
    - true이면, 더 할 필요 없고
    - false이면, 철자 검사 들어간다
        - 철자를 하나씩 검색해서
            - 안 쓴 단축키가 있으면, 등록하고 앞뒤 [] 치고 출력
            - 안 쓴 단축키가 없으면, 걍 for문 끝날 때까지 넘긴다
- 그렇게 나온 답 그대로 출력하면 됨

[보완점]
- 아 이거 대소문자 구분 안하네 아오 ㅡㅡ
    - '어떠한 것도 단축키로 지정할 수 없다면 그냥 놔두며 대소문자를 구분치 않는다.'
    - 이게 명시적이야? 예시를 봐야만 명확한 뜻을 이해할 수 있게 되는게 명시적이냐고!!! 캬악!!

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb;
    static boolean[] isUsedAlphabet;

    static String[] checkLowerCase (String[] input) {
        for (int j = 0; j < input.length; j++) {
            for (int k = 0; k < input[j].length(); k++) {
                if (!isRegistChar(input[j], k)) {
                    input[j] = addSquareBrackets(input[j], k);
                    return input;
                }
            }
        }

        return input;
    }

    // 배열에 있는 단어 전부 다 추가
    static void addWordArray(String[] input) {
        for (String s : input) {
            sb.append(s).append(" ");
        }

        sb.append("\n");
    }

    // 해당되는 단어를 찾으면 앞뒤로 [] 추가
    static String addSquareBrackets(String input, int index) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (i == index) {
                sb.append("[").append(input.charAt(i)).append("]");
            } else {
                sb.append(input.charAt(i));
            }
        }

        return sb.toString();
    }

    // 단축키 등록 여부 찾는 코드 (소문자로 치환)
    static boolean isRegistChar(String input, int index) {
        boolean result = true;
        String tempString = input.toLowerCase();

        char targetChar = tempString.charAt(index);
        int lowerIndex = targetChar - 'a';

        if (!isUsedAlphabet[lowerIndex])  {
            isUsedAlphabet[lowerIndex] = true;
            result = false;
        }

        return result;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        isUsedAlphabet = new boolean[26];
        int N = Integer.parseInt(br.readLine());

        for (int test = 0; test < N; test++) {
            String[] input = br.readLine().split(" ");
            boolean isRegistered = false;

            // (1) 첫글자 단축키 등록 여부 체크
            for (int i = 0; i < input.length; i++) {
                // 단어 중에 등록한 적이 없고, 사용한적도 없으면 단축키 등록
                if (!isRegistered && !isRegistChar(input[i], 0)) {
                    input[i] = addSquareBrackets(input[i], 0);
                    isRegistered = true;
                }
            }

            // (2) 첫번째 대문자 중에 단축키 등록된게 없다면, 이후 소문자들 체크 필
            if (isRegistered) addWordArray(input);
            else addWordArray(checkLowerCase(input));
        }

        System.out.println(sb);
    }
}