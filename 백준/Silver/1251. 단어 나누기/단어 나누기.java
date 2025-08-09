

/*
[백준]
1251, 단어 나누기

[문제파악]
알파벳 소문자로 이루어진 단어를 가지고 아래와 같은 과정을 해 보려고 한다.
먼저 단어에서 임의의 두 부분을 골라서 단어를 쪼갠다.
즉, 주어진 단어를 세 개의 더 작은 단어로 나누는 것이다.
각각은 적어도 길이가 1 이상인 단어여야 한다.
이제 이렇게 나눈 세 개의 작은 단어들을 앞뒤를 뒤집고, 이를 다시 원래의 순서대로 합친다.
예를 들어,

단어 : arrested
세 단어로 나누기 : ar / rest / ed
각각 뒤집기 : ra / tser / de
합치기 : ratserde
단어가 주어지면, 이렇게 만들 수 있는 단어 중에서 사전순으로 가장 앞서는 단어를 출력하는 프로그램을 작성하시오.

[입력]
첫째 줄에 영어 소문자로 된 단어가 주어진다. 길이는 3 이상 50 이하이다.

[출력]
첫째 줄에 구하고자 하는 단어를 출력하면 된다.

[구현방법]
- 왜 난 이런게 더 어렵게 느껴지지... (역시 문제 티어는 상대적인 것..)
- 임의로 모든 범위를 잘라놓고 로직대로 구현해서 단어 하나를 만든 뒤에 set에 추가해서 정렬 한번 하고 가장 앞에 있는 것 출력하면 될 듯
    - 근데 구현 직전까지를 어떻게 구현해야할지...
    - 걍 진짜 무식하게 자를 곳 이동하면서...

[보완점]
- 15분동안 감을 못잡아서, 계속해서 자를 두가지 지점을 투포인터마냥 하나씩 이동해가며 하는게 맞는지, 그렇게 지저분하게 나오는게 맞나 의심만하다 힌트를 봤다
- 내 생각만큼 그렇게 지저분하지도 않았으면 되려 굉장히 깔끔헀다...
- 너무 어렵게만 생각하는 것인가 싶기도 하고, 이건 참 긴장이 탁 풀렸달까... 포스팅해둬야겠다
+ 추가로 은근 기초적인건 잘 모르거나 헷갈려해서 더 못 푸는듯? 기초 좀 다지십쇼...?? (e.g. compareTo를 쓰면 문자열 비교가 쉽고 그러면 set을 쓸 필요가 없다던지...)
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int n = input.length();
        
        String result = null;

        // 범위값이 왜 1부터 시작이고 n-2, n-1까지로 제한되어있는지는 문제 조건을 생각해보면 이해가 쉽다
        // 우리는 무조건 '3등분'하는 것이 목표이다
        // 위와 같은 범위가 아니라면 0번째를 자르거나, n번째를 자르는 순간 2등분이 될 것이고 그러면 3등분이 아니므로 조건에 틀리기 때문이다
        for (int fst = 1; fst < n - 1; fst++) {
            for (int sec = fst + 1; sec < n; sec++) {
                String part1 = new StringBuilder(input.substring(0, fst)).reverse().toString();         // [0 ~ 첫번째 부분]까지 분리
                String part2 = new StringBuilder(input.substring(fst, sec)).reverse().toString();       // [첫번째 부분 ~ 두번째 부분]까지 분리
                String part3 = new StringBuilder(input.substring(sec)).reverse().toString();   // [두번쨰 부분 ~ 끝]까지 분리

                String temp = part1 + part2 + part3;

                // 왼쪽문자열.compareTo(오른쪽문자열)의 결과값이 0보다 작다면 왼쪽의 문자열이 사전순으로 앞이라는 의미이다
                if (result == null || temp.compareTo(result) < 0) {
                    result = temp;
                }
            }
        }

        System.out.println(result);
    }
}
