/*
[백준]
12919, A와 B 2

[문제파악]
- 수빈이는 A와 B로만 이루어진 영어 단어 존재한다는 사실에 놀랐다.
- 대표적인 예로 AB (Abdominal의 약자), BAA (양의 울음 소리), AA (용암의 종류), ABBA (스웨덴 팝 그룹)이 있다.
- 이런 사실에 놀란 수빈이는 간단한 게임을 만들기로 했다.
- 두 문자열 S와 T가 주어졌을 때, S를 T로 바꾸는 게임이다.
- 문자열을 바꿀 때는 다음과 같은 두 가지 연산만 가능하다.
- 문자열의 뒤에 A를 추가한다.
- 문자열의 뒤에 B를 추가하고 문자열을 뒤집는다.
- 주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 알아내는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 S가 둘째 줄에 T가 주어진다. (1 ≤ S의 길이 ≤ 49, 2 ≤ T의 길이 ≤ 50, S의 길이 < T의 길이)

[출력]
- S를 T로 바꿀 수 있으면 1을 없으면 0을 출력한다.

[구현방법]
- 이거 그냥 T에 조건을 갖다 붙여서 S로 바꿀 수 있는지 역으로 진행하면 되는거 아닌가?
- A로 시작하면 B까지 언제 도달할줄 알고..?
- 역으로 시작해서 문자열 길이가 똑같아질 때까지 반복

- 유의사항!! -> 문자열 비교 방법 차이
    S.compareTo(T) == 0	        내용 사전순 비교 (0이면 같음)	    정렬이나 정밀 비교	            문자열 값만 비교
    S.equals(T)	                내용 직접 비교	                "내용이 진짜 같은지" 확인	    문자열 값만 비교
    S == T	                    객체 참조(메모리 주소) 비교	        객체 자체가 같은지 확인	        주소까지 같아야 true
- StringBuilder 써가지고 문자열 취급이고, 문자열에서 '=='은 메모리 주솟값 비교하는건데 이걸 놓쳐서 같은 문자열임에도 false가 나왔음
- 그래서 equals 썼던건데..? 앞서 말했듯 나는 reverse를 쉽게하려고 StringBuilder를 썼다
- StringBuilder는 equals()를 오버라이드(재정의)하지 않아서, 기본적으로 ==처럼 동작한다고...\
    - 그래서 StringBuilder에서는 equals()를 쓰면 절대 안 되고, compareTo()로 내용 비교를 해야한다
- 아무튼 그러면? 결국 주솟값 다르니까 틀렸겠죠?
- 그래서 compareTo가 필요했던 것이다
- 내용 자체를 사전(dictionary)순으로 비교해서, 앞서거나 뒷서거나 체크하고, 동일한 단어면 0이 나오고!
- 그래서 compareTo해서 0이면 1을 출력하게 하면 되는 것
- StringBulider에서의 compareTo는 JAVA 9부터 나왔기 때문에, JAVA 8이라면 String으로 변환해서 해야하고, 그게 아니라면 JAVA 9 이상 버전을 쓰면 된다

[보완점]
- 그리고 역방향으로 풀어야하는 것 아니었나?
- 역방향으로 탐지해서 들어가는데 왜... T에다가 S와 동일한 조건을 적용하지

    while (S.length() != T.length()) {
        int beforeLength = T.length();

        if (T.charAt(0) == 'B') {  // 맨 앞의 문자가 'B'이면? B를 제거하고 문자열을 뒤집는다
            T.deleteCharAt(0);
            T.reverse();
        } else if (T.charAt(0) == 'A') {  // 맨 앞의 문자가 'A'이면? A를 제거한다
            T.deleteCharAt(0);
        }

        // 조건에 부합하지 않아서 변화가 없었으면 더 볼 것 없이 끝낸다
        if (beforeLength == T.length()) break;
    }

- 위의 내 코드처럼 접근하면 틀리는 이유!
- 그리디하게 풀어서, 그러니까 조건1, 조건2 둘다 충족시킬 수 있는 경우엔, 어느 한 쪽을 선택했을 때 정답이 아닌 쪽을 고를 수 있다
- 그렇게 되면 틀려버리니까 재귀가 필요한 것
- DFS를 쓰더라도 결과적으로는 백트래킹이니까 (완성된 문자열에서부터 정답을 향해 나아감) 안되는 조건들을 다 쳐내고 나면?
- 되는 것들만 남는다
- 가능한 가짓수를 모두 체크해봤는데도? 그중에 답이없다면 그냥 안되는 문제였단 것 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String S, T;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();

        dfs(T);
        System.out.println(result);
    }

    private static void dfs(String current) {
        // 현재 문자열이 S와 같다는 것은 정답일 가능성 이라는 것
        if (current.length() == S.length()) {
            // 만일 두 문자열이 같다면? S에 조건 2가지를 조합해 T를 만들 수 있다는 뜻이다
            if (current.equals(S)) result = 1;
            return;
        }
        
        // 만약 끝 부분이 'A'라면, 끝 문자 A를 제거하고 남은 문자열을 DFS 태운다 
        if (current.charAt(current.length() - 1) == 'A') {
            dfs(current.substring(0, current.length() - 1));
        }

        // 시작 문자가 B라면, B지우고 뒤집으면 된다
        if (current.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(current);  // reverse를 쓰기 위해 StringBuilder로 잠시 변환
            sb.deleteCharAt(0);  // B제거 
            sb.reverse();
            dfs(sb.toString());
        }
    }
}