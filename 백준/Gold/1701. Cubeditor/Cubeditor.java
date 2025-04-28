/*
[백준]
1701, Cubeditor

[문제파악]
- Cubelover는 프로그래밍 언어 Whitespace의 코딩을 도와주는 언어인 Cubelang을 만들었다.
- Cubelang을 이용해 코딩을 하다보니, 점점 이 언어에 맞는 새로운 에디터가 필요하게 되었다.
- 오랜 시간 고생한 끝에 새로운 에디터를 만들게 되었고, 그 에디터의 이름은 Cubeditor이다.
- 텍스트 에디터는 찾기 기능을 지원한다. 대부분의 에디터는 찾으려고 하는 문자열이 단 한 번만 나와도 찾는다.
- Cubelover는 이 기능은 Cubelang에 부적합하다고 생각했다.
- Cubelang에서 필요한 기능은 어떤 문자열 내에서 부분 문자열이 두 번 이상 나오는 문자열을 찾는 기능이다.
- 이때, 두 부분 문자열은 겹쳐도 된다.
- 예를 들어, abcdabc에서 abc는 두 번 나오기 때문에 검색이 가능하지만, abcd는 한 번 나오기 때문에 검색이 되지를 않는다.
- 이렇게 어떤 문자열에서 두 번 이상 나오는 부분 문자열은 매우 많을 수도 있다.
- 이러한 부분 문자열 중에서 가장 길이가 긴 것을 구하는 프로그램을 작성하시오.
- 예를 들어, abcabcabc에서 abc는 세 번 나오기 때문에 검색할 수 있다.
- 또, abcabc도 두 번 나오기 때문에 검색할 수 있다.
- 하지만, abcabca는 한 번 나오기 때문에 검색할 수 없다.
- 따라서, 두 번 이상 나오는 부분 문자열 중에서 가장 긴 것은 abcabc이기 때문에, 이 문자열이 답이 된다.

[입력]
- 첫째 줄에 문자열이 주어진다.
- 문자열의 길이는 최대 5,000이고, 문자열은 모두 소문자로만 이루어져 있다.

[출력]
- 입력에서 주어진 문자열의 두 번이상 나오는 부분문자열 중에서 가장 긴 길이를 출력한다.

[구현방법]
- 이게 뭘...까...?
- 부분 문자열 트리 써야하나?
- KMP 알고리즘이라는데, 트리는 전혀 필요없단다. 이게 뭔ㄷ..
- KMP를 이용하면 어떤 접두사, 접미사가 일치하는 가장 긴 길이를 O(N)만에 구할 수 있단다 (= 이 알고리즘 모르면 못푸는 문제였단 소리...)
- KMP 알고리즘의 요지는 아래와 같다 (근데 난 전혀 뭔소린지 모르겠다..)
    1) 입력 문자열의 모든 접미사에 대해
    2) 각각 KMP 실패함수를 만들어보고
    3) 그 중에서 가장 큰 값을 찾는다 (pi 배열)
- pi 배열이란?
    - pi[i] = 0 ~ i까지의 문자열 중에, 접두사 == 접미사가 되는 가장 긴 길이를 뜻함

- KMP 알고리즘(Knuth Morris Pratt)에서는 문자열 매칭할 때, 매칭이 끊겼을 때 어디서부터 다시 비교를 시작할지를 미리 계산해둠
- 이걸 계산해둔 배열을 "failure function" 또는 pi 배열이라고 부른다
- 왜 pi 배열...? 왜 이름이 pi임???
    - 이상한데 꽂혀서 찾아봄...
    - 역사적으로 KMP 논문에서 "partial match table" (부분 일치 테이블)을 소개함
    - "partial match"를 짧게 p라고도 하고, 그 중 i번째까지 계산된 값을 의미해서 p(i) 또는 줄여서 pi라고 표기함
    - 그래서 pi 배열로 부르기도 함
    - 신기... 작은 지식이 또 늘었다

[보완점]
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // 가장 긴 반복 부분 문자열의 길이를 저장할 변수
        int maxLength = 0;

        // 문자열의 모든 접미사에 대해 하나씩 검사할 것이다
        for (int i = 0; i < str.length(); i++) {
            String sub = str.substring(i);  // i번째부터 끝까지 자른 접미사 (즉, 하나하나가 접미사(suffix), 부분 문자열이 아님!!!)
            maxLength = Math.max(maxLength, getMaxPi(sub));  // sub 문자열에 대해 '가장 긴 반복 접두사-접미사 길이(getMaxPi)를 구하고 지금까지 구한 놈이랑 비교해서 갱신함
        }

        System.out.println(maxLength);
    }

    // 실패 함수 구하는 부분
    static int getMaxPi(String s) {
        int[] pi = new int[s.length()];  // pi 배열 선언
        int j = 0;  // 매칭 길이 저장 변수
        int max = 0;

        // 문자열 s를 1번 index부터 탐색 시작한다
        for (int i = 1; i < s.length(); i++) {
            // 현재 문자 s[i]가 s[j]와 다르면? -> j를 줄여서 그 전에 가능한 최대 매칭 길이였던, 접두사 길이로 돌아간다
            // 즉, 이번건 안맞았으니까 맞았던 지점으로 돌아가겠단 소리
            while (0 < j && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }

            // 현재 문자 s[i]와 s[j]가 일치하면 매칭된다는 의미다 (너무 당연한 소리)
            if (s.charAt(i) == s.charAt(j)) {
                j++;  // 그러니 j를 하나 늘리고
                pi[i] = j;  // 현재 인덱스 i에 대해 매칭된 접두사-접미사 길이를 j에 저장한다
                max = Math.max(max, pi[i]);  // 여기서 최대 pi값 계속 갱신
            }
        }

        return max;
    }
}