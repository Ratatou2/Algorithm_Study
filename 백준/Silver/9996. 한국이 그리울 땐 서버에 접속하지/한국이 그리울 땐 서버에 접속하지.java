/*
[백준]
9996, 한국이 그리울 땐 서버에 접속하지

[문제파악]
요약 : 한국이 보고 싶은 선영이
...
- 매일 밤, 파일 이름을 보면서 파일 하나하나에 얽힌 사연을 기억하면서 한국을 생각하고 있었다.
- 어느 날이었다. 한국에 있는 서버가 망가졌고, 그 결과 특정 패턴과 일치하는 파일 이름을 적절히 출력하지 못하는 버그가 생겼다.
- 패턴은 알파벳 소문자 여러 개와 별표(*) 하나로 이루어진 문자열이다.
- 파일 이름이 패턴에 일치하려면, 패턴에 있는 별표를 알파벳 소문자로 이루어진 임의의 문자열로 변환해 파일 이름과 같게 만들 수 있어야 한다.
- 별표는 빈 문자열로 바꿀 수도 있다.
- 예를 들어, "abcd", "ad", "anestonestod"는 모두 패턴 "a*d"와 일치한다. 하지만, "bcd"는 일치하지 않는다.
- 패턴과 파일 이름이 모두 주어졌을 때, 각각의 파일 이름이 패턴과 일치하는지 아닌지를 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 파일의 개수 N이 주어진다. (1 ≤ N ≤ 100)
- 둘째 줄에는 패턴이 주어진다. 패턴은 알파벳 소문자와 별표(아스키값 42) 한 개로 이루어져 있다.
- 문자열의 길이는 100을 넘지 않으며, 별표는 문자열의 시작과 끝에 있지 않다.
- 다음 N개 줄에는 파일 이름이 주어진다.
- 파일 이름은 알파벳 소문자로만 이루어져 있고, 길이는 100을 넘지 않는다.

[출력]
- 총 N개의 줄에 걸쳐서, 입력으로 주어진 i번째 파일 이름이 패턴과 일치하면 "DA", 일치하지 않으면 "NE"를 출력한다.
- 참고로, "DA"는 크로아티어어로 "YES"를, "NE"는 "NO"를 의미한다

[구현방법]
- 입력 받은 조건의 첫글자와 끝글자만 비교하면 되는 것이 아닐까?
- 정확히는 * 기준으로 앞뒤로 나눈다
- 그리고 글자 수만큼 앞에서 비교, 뒤에서 비교 for문을 각각 돌려서 일치하는지 아닌지 체크하면 될듯하다
- 이렇게 구현하면 ab*cd 같은 식으로 여러 글자를 비교해야해도 충분히 할 수 있다

[보완점]
- 예외사항이 있다
- 아래 같은 예시는 DA로 출력하는... 전체 길이가 비교해야할 단어보다 짧다면 비교할 필요 없다
    1
    aaa*a
    aaa
    
    - 위 예시를 살펴보면 앞에 a가 3자리가 필요하고 뒤에 a가 한자리로 총 길이 4의 단어가 필요하지만 주어진 것은 3
    - 그러나 길이를 체크하는 로직이 없다면 이것의 답은 DA로 나온다 (틀렸음)

3
a*d
abcd
anestonestod
facebook


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    // 앞에서부터 비교
    static boolean sameFront(String orignal, String input) {
        for (int i = 0; i < orignal.length(); i++) {
            if (orignal.charAt(i) != input.charAt(i)) return false;
        }

        return true;
    }

    // 끝에서부터 비교
    static boolean sameBack(String orignal, String input) {
        for (int i = 0; i < orignal.length(); i++) {
            if (orignal.charAt(orignal.length() - 1 - i) != input.charAt(input.length() - 1 - i)) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String[] checkKey = br.readLine().split("\\*");

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            // 65%에서 틀렸던 예외 사항이다
            if (input.length() < checkKey[0].length() + checkKey[1].length()) {
                sb.append("NE").append("\n");
                continue;
            }

            if (sameFront(checkKey[0], input) && sameBack(checkKey[1], input)) {
                sb.append("DA").append("\n");
            } else sb.append("NE").append("\n");
        }

        System.out.println(sb);
    }
}