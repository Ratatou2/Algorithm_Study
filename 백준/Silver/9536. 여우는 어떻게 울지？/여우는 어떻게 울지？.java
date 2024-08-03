/*
[백준]
9536, 여우는 어떻게 울지?

[문제파악]
- 여우 울음소리 녹음했는데, 그 외 동물들 소리도 같이 녹음됐다
- 다른 동물들의 울음소리를 알려줄테니 제거해라

[입력]
- 첫 번째 줄에는 테스트케이스의 개수 T, 각 테스트케이스는 아래와 같이 구성되어 있다.
    - 테스트케이스의 첫 줄에는 몇 개의 단어로 이루어진 녹음된 소리가 입력
    - 단어는 알파벳 소문자로만 이루어져 있으며 공백으로 구분된다.
    -   단어의 길이는 최대 100글자이고, 단어의 개수는 최대 100개이다.
    - 다음 줄부터는 여우를 제외한 동물들의 울음소리가 <동물> goes <소리>의 형태로 입력된다.
        - 최소 1마리, 최대 100마리이며, 이름은 최대 100글자이고 실제로 존재하는 동물의 이름이다.
        - 여우를 제외한 동물의 울음소리는 한 단어이고 최대 100글자이다.
    - 마지막 줄에는 한신이가 알고 싶어하는 질문이 주어진다. what does the fox say?

[출력]
- 각 테스트케이스마다 여우의 울음소리를 한 줄씩, 녹음된 순서대로 출력한다.
- 여우의 울음소리가 녹음되어 있음이 보장된다.
    - 알려진 것과는 달리, 여우는 모스 부호로 의사소통하지 않는다.


[구현방법]
- 흠 일단 Set은 당연히 쓰면 안되고... Map으로 해서 그냥 값을 index(순서)로 주고 삭제해야하나
- 그게 나을듯
- 근데 map이라 순서 보장 안돼서 그냥 리스트 사용함

[보완점]


*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test <= T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<String> allSound = new ArrayList<>();
            Set<String> specificSound = new HashSet<>();

            // 현재 섞인 울음소리
            while (st.hasMoreTokens()) {
                allSound.add(st.nextToken());
            }

            // 각 동물들의 울음소리 저장
            for (int i = 0; i < 100; i++) {
                String temp = br.readLine();

                if (temp.equals("what does the fox say?")) break;
                String[] currentSound = temp.split(" ");

                specificSound.add(currentSound[2]);
            }

            // 다른 동물 울음소리이면 추가 X
            for (String currentSound : allSound) {
                if (!specificSound.contains(currentSound)) {
                    sb.append(currentSound).append(" ");
                }
            }
        }

        System.out.println(sb);
    }
}