/*
[백준]
1213, 팰린드롬 만들기

[문제파악]
- 임한수와 임문빈은 서로 사랑하는 사이이다.
- 임한수는 세상에서 팰린드롬인 문자열을 너무 좋아하기 때문에, 둘의 백일을 기념해서 임문빈은 팰린드롬을 선물해주려고 한다.
- 임문빈은 임한수의 영어 이름으로 팰린드롬을 만들려고 하는데, 임한수의 영어 이름의 알파벳 순서를 적절히 바꿔서 팰린드롬을 만들려고 한다.
- 임문빈을 도와 임한수의 영어 이름을 팰린드롬으로 바꾸는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 임한수의 영어 이름이 있다.
- 알파벳 대문자로만 된 최대 50글자이다.

[출력]
- 첫째 줄에 문제의 정답을 출력한다.
- 만약 불가능할 때는 "I'm Sorry Hansoo"를 출력한다.
- 정답이 여러 개일 경우에는 사전순으로 앞서는 것을 출력한다.

[구현방법]
- 일단 팰린드롬이 되려면 조건이 몇 개 필요하다
- 전체 길이가 짝수면 모든 문자열이 짝이 있는지, 전체 길이가 홀수면 하나를 제외한 모든 문자열이 짝수인지를 체크해야한다
- 그 와중에 사전 순으로 우선적으로 오려면 앞의 문자열부터 소모해야 한다 즉, 우선순위별로 체크해야한다는 말
- 또한 절반까지만 만들면, 나머지는 만들어둔 문자열 뒤집어버리면 된다 (StringBuilder의 reverse 기능쓰면 될듯!!)

[보완점]
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    static String repeatChar(Character c, int repeat) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < repeat; i++) {
            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Map<Character, Integer> map = new TreeMap<Character, Integer>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            map.compute (c, (k, v) -> v == null ? 1 : v + 1);  // 없으면 넣어주고, 있으면 +1 해서 저장한다 (외워야 함)
        }

        int odd = 0;  // 문자열 갯수 홀수인지 체크
        char mid = '-';
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> temp : map.entrySet()) {
            int currCharCount = temp.getValue();

            // 문자 갯수가 짝수면? 배치하면 된다
            if (currCharCount % 2 == 0) {
                sb.append(repeatChar(temp.getKey(), (currCharCount / 2)));
            } else {
                // 홀수인 경우, 이전에도 홀수였던 적이 있는지 체크가 필요하다
                // 이전에도 한 문자가 홀수 갯수였던 적이 있으면, 팰린드롬 성립 불가
                if (odd == 1) {
                    sb = new StringBuilder();
                    sb.append("I'm Sorry Hansoo");
                    break;
                } else {
                    odd++;
                    mid = temp.getKey();
                    sb.append(repeatChar(temp.getKey(), ((currCharCount / 2))));
                }
            }
        }


        StringBuilder reverse_sb = new StringBuilder(sb).reverse();

        // 팰린드롬이 성립한다면, 아래 조건들에 따라 추가적으로 답을 더해줘야 함
        if (!sb.toString().equals("I'm Sorry Hansoo")) {
            if (mid != '-') sb.append(mid);  // 중앙값이 초기값인 '-'가 아니면 추가해주자

            sb.append(reverse_sb);
        }

        System.out.println(sb);
    }
}