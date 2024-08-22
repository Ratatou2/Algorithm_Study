/*
[백준]


[문제파악]
- 전화번호 목록이 주어진다.
- 이때, 이 목록이 일관성이 있는지 없는지를 구하는 프로그램을 작성하시오.
- 전화번호 목록이 일관성을 유지하려면, 한 번호가 다른 번호의 접두어인 경우가 없어야 한다.
- 예를 들어, 전화번호 목록이 아래와 같은 경우를 생각해보자
    - 긴급전화: 911
    - 상근: 97 625 999
    - 선영: 91 12 54 26
- 이 경우에 선영이에게 전화를 걸 수 있는 방법이 없다.
- 전화기를 들고 선영이 번호의 처음 세 자리를 누르는 순간 바로 긴급전화가 걸리기 때문이다.
- 따라서, 이 목록은 일관성이 없는 목록이다.

[입력]
- 첫째 줄에 테스트 케이스의 개수 t가 주어진다. (1 ≤ t ≤ 50)
- 각 테스트 케이스의 첫째 줄에는 전화번호의 수 n이 주어진다. (1 ≤ n ≤ 10000)
- 다음 n개의 줄에는 목록에 포함되어 있는 전화번호가 하나씩 주어진다.
- 전화번호의 길이는 길어야 10자리이며, 목록에 있는 두 전화번호가 같은 경우는 없다.

[출력]
- 각 테스트 케이스에 대해서, 일관성 있는 목록인 경우에는 YES, 아닌 경우에는 NO를 출력한다.

[구현방법]
- 인풋 다 받고 길이 짧은 순으로 정렬(= 사전순이면 될듯)해서 for문 돌면서 범위지정해가지고 단어가 일치하는지 확인하면 될듯
    - 아 근데 숫자니까 그냥 정렬하면 될듯하다
    - 대신 이렇게 하려면 배열은 int[] 여야함.
- 그리고 Integer.parseString 쓰는 것도 있지만 String.valueOf도 있음
    - String.valueOf는 그냥 boolean이든 뭐든 객체를 죄다 문자열로 바꿔줌

[보완점]
1. NumberErrorFormat 발생
    - "전화번호" 이기 때문에 숫자로 처리를 하는 경우 맨 앞의 0이 생략될 수 있습니다 라는 문구를 봄... 더헉!
2. Comparator와 compareTo 공부가 더 필요할듯하다 어디에 어떻게 적용해야할지 아직 잘 감을 못잡네...
3. 시간 초과
    - 단순 for문 반복 비교로는 시간 초과 흠...
    - 자바도 파이썬의 str[0:3]처럼 일부만 잘라낼 수 있다.
    - substring 쓰면됨!!! 자꾸 까먹는건 익숙지 않아서겠지 ㅠ
    - 그래도 여전히 시간 초과 뭐지...
    - 이미 검사한 길이만큼은 또 할 수 없으니까 그 부분은 버려야하나?
    - 근데 이건 큰 의미가 없을 것 같고 서로 하나씩 다 비교하는게 문제인 거 같은데
    - 테스트 케이스 갯수 잘 생각해보고 제한 시간 1초인거 생각해봤으면 이중 for문 첨부터 안 쓸 수도 있었음! 이젠 이런 부분도 고려해보기

    - 무튼 startsWith 이란 함수가 있단다... 이 단어로 시작하는지를 찾아줄 수 있다고 ㅋㅋㅋ 어이가 없네 진짜
    - 좋은게 있어도 모르면 못 써먹는 ㅠ
    - 그리고 사실 length로 정렬 안해도 된다.
    - String 상태로 그냥 정렬하면 서로간의 연관성이 짙은 놈들끼리 묶여 있는데 이 상태로 검색하는게 더 효율적이다. 맞는 말이야
        - ex)
            - 1, 11, 111111, 222, 22345, 33 (비교하기 더 효율적)
            - 1, 11, 33, 222, 22345, 111111 (비교하기 비효율적)
    - 내가 참고했던 사람은 정말 똑똑한 것이 사전순으로 정렬하면 연관성이 짙은건 바로 다음번 순서에 있을 확률이 제일 크다
    - 그러니 모든 숫자들을 서로 비교(이중 for문)하는 것이 아니라 현재 숫자를 바로 다음 숫자와만 비교(단일 for문)하는 식으로 구현했다
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static boolean includeNumber(String mainNumber, String targetNumber) {
        // 짧은 숫자의 길이만큼 잘라와서 String으로 만들고 둘이 같은지 비교한다
        return targetNumber.startsWith(mainNumber);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            String[] phoneNumbers = new String[N];

            // 입력값 받기
            for (int i = 0; i < N; i++) {
                phoneNumbers[i] = br.readLine();
            }

            // 정렬한다
            Arrays.sort(phoneNumbers);

            boolean isInclude = false;
            for (int i = 0; i < N - 1; i++) {
                // 짧은 문자열이 긴 문자열의 초반부에 포함된다면 이 관계는 일관성이 없는 것이다.
                // (이 문제에서의) 일관성이 없다 : 한 번호가 다른 번호의 접두가 되는 경우가 있는 것을 의미함.
                isInclude = includeNumber(phoneNumbers[i], phoneNumbers[i + 1]);

                if (isInclude) {
                    sb.append("NO").append("\n");
                    break;
                }
            }

            if (!isInclude) {
                sb.append("YES").append("\n");
            }
        }

        System.out.println(sb);
    }
}