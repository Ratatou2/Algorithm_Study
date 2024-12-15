/*
[백준]
12904, A와 B

[문제파악]
- 수빈이는 A와 B로만 이루어진 영어 단어가 존재한다는 사실에 놀랐다.
- 대표적인 예로 AB (Abdominal의 약자), BAA (양의 울음 소리), AA (용암의 종류), ABBA (스웨덴 팝 그룹)이 있다.
- 이런 사실에 놀란 수빈이는 간단한 게임을 만들기로 했다.
- 두 문자열 S와 T가 주어졌을 때, S를 T로 바꾸는 게임이다.
- 문자열을 바꿀 때는 다음과 같은 두 가지 연산만 가능하다.
    - 문자열의 뒤에 A를 추가한다.
    - 문자열을 뒤집고 뒤에 B를 추가한다.
- 주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 알아내는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 S가 둘째 줄에 T가 주어진다. (1 ≤ S의 길이 ≤ 999, 2 ≤ T의 길이 ≤ 1000, S의 길이 < T의 길이)

[출력]
- S를 T로 바꿀 수 있으면 1을 없으면 0을 출력한다.

[구현방법]
- 문제를 너무 어렵게 생각한 문제였다
- 두가지 연산을 꼭 문자를 입력받고 문자열을 만들어가며 진행할 필요는 없다
- 그냥 입력값에서 조건에 맞춰 한글자씩 줄여나가며 봐도된다ㅏ
- 위 조건 두개를 거꾸로 진행하면 아래와 같다
    - 문자열 끝이 A이면, 문자열 뒤의 A를 제거한다
    - 문자열 끝이 B이면, 문자열 뒤의 B를 제거하고 문자열을 뒤집는다
- 위와 같은 식으로 진행하면 최단 시간 내에 해답을 구할 수 있다
- 구하려는 단어와 입력 받은 단어의 길이가 같아질 때까지 반복하는 조건도 체크하기 쉬움
- 고민할 땐 그렇게 모르겠더니, 알고보니 이렇게 쉬울줄이야...

[보완점]
- String을 reverse 하기 쉽도록 StringBuilder로 진행하면 좋다
- 또한 deleteCharAt를 쓰면 문자 하나를 쉽게 지울 수 있음
- 추가로 가변객체인 StringBuilder는 메모리 주소값으로 비교하기 때문에 불변 객체인 String으로 변환해서 비교해야한다
    - 그렇지 않으면 똑같은 StringBuilder인 "temp", "temp"가 있어도 둘의 메모리 주솟값이 다르면 false로 나온다
    - 유의할 것!!!
- 역시 배워도 배워도 배울게 정말 많아
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder S = new StringBuilder(br.readLine());
        StringBuilder T = new StringBuilder(br.readLine());

        while (S.length() < T.length()) {
            // if : 끝 문자가 A이면, 끝 문자를 하나를 지운다
            // else if : 끝 문자가 B이면, 끝 문자를 하나 지우고 reverse 한다
            if ('A' == T.charAt(T.length() - 1)) {
                T.deleteCharAt(T.length() - 1);
            } else if ('B' == T.charAt(T.length() - 1)) {
                T.deleteCharAt(T.length() - 1);
                T.reverse();
            }
        }

        System.out.println(S.toString().equals(T.toString()) ? "1" : "0");
    }
}