/*
[백준]
17609, 회문

[문제파악]
- 회문(回文) 또는 팰린드롬(palindrome)은 앞 뒤 방향으로 볼 때 같은 순서의 문자로 구성된 문자열을 말한다.
- 예를 들어 ‘abba’ ‘kayak’, ‘reviver’, ‘madam’은 모두 회문이다.
- 만일 그 자체는 회문이 아니지만 한 문자를 삭제하여 회문으로 만들 수 있는 문자열이라면 우리는 이런 문자열을 “유사회문”(pseudo palindrome)이라고 부른다.
- 예를 들어 ‘summuus’는 5번째나 혹은 6번째 문자 ‘u’를 제거하여 ‘summus’인 회문이 되므로 유사회문이다.
- 여러분은 제시된 문자열을 분석하여 그것이 그 자체로 회문인지, 또는 한 문자를 삭제하면 회문이 되는 “유사회문”인지, 아니면 회문이나 유사회문도 아닌 일반 문자열인지를 판단해야 한다.
- 만일 문자열 그 자체로 회문이면 0, 유사회문이면 1, 그 외는 2를 출력해야 한다.

[입력]
- 입력의 첫 줄에는 주어지는 문자열의 개수를 나타내는 정수 T(1 ≤ T ≤ 30)가 주어진다.
- 다음 줄부터 T개의 줄에 걸쳐 한 줄에 하나의 문자열이 입력으로 주어진다.
- 주어지는 문자열의 길이는 3 이상 100,000 이하이고, 영문 알파벳 소문자로만 이루어져 있다.

[출력]
- 각 문자열이 회문인지, 유사 회문인지, 둘 모두 해당되지 않는지를 판단하여 회문이면 0, 유사 회문이면 1, 둘 모두 아니면 2를 순서대로 한 줄에 하나씩 출력한다.

[구현방법]
- 흠.. 일단 쉽게 생각나는건 입력받은 즉시 회문 체크
- false가 뜨면 문자열 중 철자를 하나씩 지워보며 회문 체크...
- 근데 이러면 모든 경우의 수를 따져보게 되므로 입력의 문자열 길이(3 이상 100,000 이하) 조건 상 1초 안에 안 끝날듯하다...'
- 뭔가 회문의 싹수가 안보인다~ 싶으면 더 체크 안하는게 나은데
- 그러면 Map을 만들어서 a ~ z 를 회문 체크할 때 미리 세보고, 홀수 갯수인 철자가 두 개 이상이면 회문 체크 안하도록 하면?
- 홀수인 철자가 두개 이상이라는 것은 유사 회문 및 회문 의 가망성이 없다는 것이다. 유사 회문은 문자열을 하나까지만 지울 수 있으니까
- 이 방식이 괜찮을 것 같다

[보완점]
- 이거 단독 철자가 한개만 있는 경우까지 생각했는데 3개 짜리일 수도 있고, 그럴 경우 지우는 위치도 중요하다!!
- 아 나 이거 너무 복잡하게 생각했네
- 첫번째로 회문 체크 for문 안돌리고 걍 sb.reverse.toString()해서 equal 연산하면 편하다
- 두번째로 포인터 이동 방식 알제? 그걸로 유사 회문을 체크하면 된다

- 또한 StringBuilder의 .reverse()는 원본을 변경하는 것이니 new StringBuilder(delLeft).reverse() 이런 식으로 해야만 원본을 훼손하지 않을 수 있다. 유의할 것
- .equals가 아닌 for문을 사용한 charAt()으로 비교할까도 했으나 기본적으로 .equals가 훨씬 더 빠르다 (아래 테스트 코드있음)

- 자꾸 시간초과가 나는데 .reverse()말고는 떠오르는게 없다 .reverse()는 O(N)이기 때문임.
- 이러면 for 루프 돌려서 String.length()의 절반까지만 하게 해보자
- 아니 근데 .reverse() 내부구조 뜯어봤는데 결국 절반길이까지 좌우대칭으로 진행하는데 이게 시간 단축이 되나?
- 아 근데 equals를 할 때, 길이 100,000짜리의 문자열 전체를 비교하는 것과 절반까지만 비교하는 것은 시간적으로 벌써 2배차이니까 이건 해볼법하다
- for문으로 짜면 최악의 경우, O(N)이지만, 첫번째 charAt(0) == charAt(length())에서 틀려버려서 return 하면 O(1)일 수 있으니

7
abba
summuus
xabba
xabbay
comcom
comwwmoc
comwwtmoc


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) return false;
        }

        return true;
    }

    static boolean isSimilarPalindrome(String s) {
        int leftIndex = 0;
        int rightIndex = s.length() - 1;

        while(leftIndex < rightIndex) {
            if (s.charAt(leftIndex) != s.charAt(rightIndex)) {
                // 일치 안한 자리를 지워본다 (왼쪽, 오른쪽 각각)
                String delLeft = new StringBuilder(s).deleteCharAt(leftIndex).toString();
                String delRight = new StringBuilder(s).deleteCharAt(rightIndex).toString();

                // 각각의 회문을 비교한다
                if (isPalindrome(delLeft) || isPalindrome(delRight)) {
                    return true;
                }
                
                return false;
            }

            leftIndex++;
            rightIndex--;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            // 회문 체크
            if (isPalindrome(input)) {
                sb.append(0).append("\n");
            } else {
                if (isSimilarPalindrome(input)) sb.append(1).append("\n");
                else sb.append(2).append("\n");
            }
        }

        System.out.println(sb);
    }

    // equals와 for loop 비교 테스트 코드
    public static void compareEqualsAndForLoop(String[] args) {
        String str1 = "HelloWorld";
        String str2 = "HelloWorld";

        // Using equals()
        long startTime = System.nanoTime();
        boolean isEqual1 = str1.equals(str2);
        long endTime = System.nanoTime();
        System.out.println("Using equals(): " + isEqual1 + " Time: " + (endTime - startTime) + " ns");

        // Using for loop and charAt()
        startTime = System.nanoTime();
        boolean isEqual2 = true;
        if (str1.length() == str2.length()) {
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    isEqual2 = false;
                    break;
                }
            }
        } else {
            isEqual2 = false;
        }
        endTime = System.nanoTime();
        System.out.println("Using for loop: " + isEqual2 + " Time: " + (endTime - startTime) + " ns");
    }
}