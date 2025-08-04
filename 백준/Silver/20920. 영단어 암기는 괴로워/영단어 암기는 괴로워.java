

/*
[백준]
20920, 영단어 암기는 괴로워

[문제파악]
화은이는 이번 영어 시험에서 틀린 문제를 바탕으로 영어 단어 암기를 하려고 한다.
그 과정에서 효율적으로 영어 단어를 외우기 위해 영어 단어장을 만들려 하고 있다.
화은이가 만들고자 하는 단어장의 단어 순서는 다음과 같은 우선순위를 차례로 적용하여 만들어진다.

0) 자주 나오는 단어일수록 앞에 배치한다.
1) 해당 단어의 길이가 길수록 앞에 배치한다.
2) 알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치한다
3) M보다 짧은 길이의 단어의 경우 읽는 것만으로도 외울 수 있기 때문에 길이가 M이상인 단어들만 외운다고 한다.
화은이가 괴로운 영단어 암기를 효율적으로 할 수 있도록 단어장을 만들어 주자.

[입력]
첫째 줄에는 영어 지문에 나오는 단어의 개수 N과 외울 단어의 길이 기준이 되는 M이 공백으로 구분되어 주어진다. (1 <= N <= 100,000, 1 <= M <= 10)
둘째 줄부터 N+1번째 줄까지 외울 단어를 입력받는다. 이때의 입력은 알파벳 소문자로만 주어지며 단어의 길이는 10을 넘지 않는다.
단어장에 단어가 반드시 1개 이상 존재하는 입력만 주어진다.

[출력]
화은이의 단어장에 들어 있는 단어를 단어장의 앞에 위치한 단어부터 한 줄에 한 단어씩 순서대로 출력한다.

[구현방법]
- 아니 횟수야 map으로 세면되고 예외처리는 continue 해버리면 된다지만... 자주 등장하는 단어와 길이를 어떻게 같이... 하지?
- 그냥 Comparator 따로 만들고 그 안에 분기처리하면 될 것 같기도

[보완점]
- 보편적으로 Map으로 카운트했으면, List로 전환하고 커스텀하는게 일반적이다
- putIfAbsent는 '없으면 집어넣는 것'이라는 것을 잊지말자...
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Integer> dic = new HashMap<>();

        // 단어 입력 받기
        for (int i = 0; i < N; i++) {
            String word = br.readLine();

            // 예외조건) 길이가 M 미만인 단어는 외우지 않음
            if (word.length() < M) continue;

            dic.put(word, dic.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> sortDic = new ArrayList<>(dic.entrySet());

        sortDic.sort((dic1, dic2) -> {
            // Objects.equals()는 a, b가 null이어도 Null Point Exception 발생 X

            // 조건1) 자주 나온 단어일수록 앞에 배치
            if (!Objects.equals(dic1.getValue(), dic2.getValue())) {
                return dic2.getValue() - dic1.getValue();
            }

            // 조건2) 단어의 길이가 길수록 앞에 배치
            if (!Objects.equals(dic1.getKey().length(), dic2.getKey().length())) {
                return dic2.getKey().length() - dic1.getKey().length();
            }

            // 조건3) 알바펫 사전 순으로 앞에 배치
            return dic1.getKey().compareTo(dic2.getKey());
        });

        // 정답 출력
        for (Map.Entry<String, Integer> temp : sortDic) {
            sb.append(temp.getKey()).append("\n");
        }

        System.out.println(sb);
    }
}
