/*
[백준]
1343, 폴리오미노

[문제파악]
- 폴리오미노 2종류 보유중
- AAAA, BB
- X와 .로 이루어진 보드판이 주어짐
- 겹침없이 X를 폴리오미노로 덮으려고 함
- .는 폴리오미노로 덮으면 안됨
- 폴리오미노로 모두 덮은 보드판을 출력하는 프로그램을 작성할 것

[입력]
- 첫줄에 보드판이 주어짐
- 보드판의 최대 크기는 50

[출력]
- 첫줄에 사전순으로 가장 앞서는 답을 출력
- 덮을 수 없으면 -1 출력

[구현방법]
- 사전순이면 일단 A를 최대한 많이 덮는 것이 좋음
- A부터 진행하고 안되면 이전으로 돌아가는 식으로 구현해야할듯

[보완점]
- 감이 안잡힘
- AAAA는 BB의 배수이기 때문에 항상 최소한의 교체로 최적의 해를 가질 수 있다고
- 내가 고려하지 못했던 부분은 사실 문제에 대한 경험 부족인 것 같은데 초기 아이디어는 for문 돌리면서 진행
- .나올때까지 count하며 돌다가 . 마주했을 때 count 갯수가 4개면 AAAA, 2개면 BB로 치환하려 했음
- 이제 문제는 이제 홀수일 때 어디까지 돌아가서 (재귀) 어떻게 나머지를 덮냐를 고민했던 것이고 여기서 실마리를 못참음
- 근데 잘 생각해보면 AAAA, BB 둘로 못 덮는 경우엔, 그냥 답이 존재하지 않음 (2배수만 덮을 수 있기 떄문에)
- 그러니까 그냥 그럴 필요 없이 replace해버리면 됨... (AAAA로 덮을 수 있는거 먼저 다 덮고, BB로 덮으면 됨)
- 너무 어렵게 생각하지 말자 ㅠ

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String board = br.readLine();

        board = board.replaceAll("XXXX", "AAAA");
        board = board.replaceAll("XX", "BB");

        int count_X = (int) board.chars()
                            .filter(ch -> ch == 'X')
                            .count();

        if (0 < count_X) {
            System.out.println(-1);
        } else {
            System.out.println(board);
        }
    }
}