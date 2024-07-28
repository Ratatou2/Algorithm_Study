/*
[SWEA]
B형 03 암호문3

[문제파악]
- 0 ~ 999999 사이의 수로 표현되는 암호문이 있고, 이 암호문을 N개 모아 놓은 암호문 뭉치가 있음.
- 처리기는 다음과 같이 3개의 명령어로 제어한다.

- I(삽입) x, y, s :
    - 앞에서부터 x번째 암호문 바로 다음에 y개의 암호문을 삽입한다. s는 덧붙일 암호문들이다.
    0 [ ex) I 3 2 123152 487651 ]

- D(삭제) x, y
    - 앞에서부터 x번째 암호문 바로 다음부터 y개의 암호문을 삭제한다.
    - [ ex) D 4 4 ]

- A(추가) y, s
    - 암호문 뭉치 맨 뒤에 y개의 암호문을 덧붙인다. s는 덧붙일 암호문들이다.
    - [ ex) A 2 421257 796813 ]

- 위의 규칙에 맞게 작성된 명령어를 나열하여 만든 문자열이 주어졌을 때
    - 암호문 뭉치를 수정하고
    - 수정된 결과의 처음 10개 암호문을 출력하라.

[입력]
- 첫번째 줄 : 원본 암호문 뭉치 속 암호문의 개수 N ( 2000 ≤ N ≤ 4000 의 정수)
- 두번째 줄 : 원본 암호문 뭉치
- 세번째 줄 : 명령어의 개수 ( 250 ≤ M ≤ 500 의 정수)
- 네번째 줄 : 명령어

위와 같은 네 줄이 한 개의 테스트 케이스이며, 총 10개의 테스트 케이스가 주어진다.

[출력]
- 기호와 함께 테스트 케이스의 번호를 출력하고, 공백 문자 후 수정된 암호문 뭉치의 앞 10개 암호문을 출력한다.

[구현방법]
- 단순해보이지만 양이 많고, 추가하고 삭제하고 붙이는 과정에서 전체 인덱스는 변경된다
- 그로 인해일정 갯수별로 소분해 나눠두는 전략은 크게 쓸모가 없을 것 같음
- 그냥 냅다 구현하기 시간이 오래 걸릴 것 같고 정확도도 보장 불가 (한번만 꼬여도...)
- 어떻게 구현해야하지...? 감이 안잡히는데
- 아이디어 참고만 했는데 그냥 내가 생각한대로 구현하는데...? 다만 그냥 StringToken에 담아가지고 List로 하는 정도...?
- 해보지 뭐
[보완점]
- 초기에 List.of 써가지고 readline으로 한큐에 받도록 구현했는데 List.of는 불변의 리스트라서 이거 쓰면 안됨...

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static List<String> orderInsert(List<String> secreteCode, StringTokenizer st) {
        int targetIndex = Integer.parseInt(st.nextToken());
        int repeat = Integer.parseInt(st.nextToken());

        for (int i = targetIndex; i < targetIndex + repeat; i++) {
            secreteCode.add(i, st.nextToken());
        }

        return secreteCode;
    }

    static List<String> orderAdd(List<String> secreteCode, StringTokenizer st) {
        int repeat = Integer.parseInt(st.nextToken());

        for (int i = 0; i < repeat; i++) {
            secreteCode.add(st.nextToken());
        }

        return secreteCode;
    }

    static List<String> orderDelete(List<String> secreteCode, StringTokenizer st) {
        int targetIndex = Integer.parseInt(st.nextToken());
        int repeat = Integer.parseInt(st.nextToken());

        for (int i = targetIndex; i < targetIndex + repeat; i++) {
            secreteCode.remove(targetIndex);  // 타겟 인덱스를 지우면 하나씩 앞으로 당겨지니까 계속 같은 인덱스를 지워야 함 (주의!)
        }

        return secreteCode;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        for (int tc = 1; tc <= 10; tc++) {
            StringBuilder sb = new StringBuilder();

            int N = Integer.parseInt(br.readLine());
            List<String> secretCode = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                secretCode.add(st.nextToken());
            }

            int orderCount  = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()) {
                String order = st.nextToken();

                if (order.equals("I")) {
                    secretCode = orderInsert(secretCode, st);
                } else if (order.equals("A")) {
                    secretCode = orderAdd(secretCode, st);
                } else if (order.equals("D")) {
                    secretCode = orderDelete(secretCode, st);
                }
            }

            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < 10; i++) {
                sb.append(secretCode.get(i)).append(" ");
            }

            System.out.println(sb);
        }
    }
}