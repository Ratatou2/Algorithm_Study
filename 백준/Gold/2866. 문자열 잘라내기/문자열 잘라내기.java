/*
[백준]
2866, 문자열 잘라내기

[문제파악]
- R개의 행과 C개의 열로 이루어진 테이블이 입력으로 주어짐
- 이 테이블의 원소는 알파벳 소문자이다.
- 각 테이블의 열을 위에서 아래로 읽어서 하나의 문자열을 만들 수 있다.
    - 예제 입력에서
        dobarz
        adatak
    - 이 주어지는 경우 "da", "od", "ba", "at", "ra", "zk"와 같이 6개의 문자열들이 만들어지게 된다.
    - 만약 가장 위의 행을 지워도 테이블의 열을 읽어서 문자열이 중복되지 않는다면,
        1) 가장 위의 행을 지움,
        2) count의 개수를 1 증가
        이 과정을 반복한다.
    - 만약 동일한 문자열이 발견되는 경우, 반복을 멈추고 count의 개수를 출력 후 프로그램을 종료한다.
    - 테이블이 주어질 경우 count의 값을 구해보자.


[입력]
- 첫 번째 줄에는 테이블의 행의 개수와 열의 개수인 R과 C가 주어진다. (2 ≤ R, C ≤ 1000)
- 이후 R줄에 걸쳐서 C개의 알파벳 소문자가 주어진다.
    - 가장 처음에 주어지는 테이블에는 열을 읽어서 문자열을 만들 때, 동일한 문자열이 존재하지 않는 입력만 주어진다.

[출력]
- 문제의 설명과 같이 count의 값을 출력

[구현방법]
- 문제가 이해가 안가서 10번을 읽었네;;;
- 세로로 읽어서 완성한 문자열 중에 중복이 있는 시점 직전까지 세면 된다
- 예를들어 아래와 같은 예시를 살펴보자
    3 4
    alfa
    beta
    zeta

    - 이걸 한줄 지우고 세로(열)로 읽으면
    bz
    ee
    tt
    aa
    가 된다

    - 겹치는 문자열 없으니 또 한줄 지우고 진행하면 (현재 count = 1)
    zeta
    이고 세로(열)로 읽으면

    z
    e
    t
    a

    - 역시 겹치는 것 없다 (count = 2)
    - 이제는 더 시도해볼 열이 없으니 기각

[보완점]
- 시간 초과 ㅋㅋㅋ 아오 ㅋㅋㅋㅋㅋ
- 매번 새롭게 단어 만들고 검사하고 쓸 필요 없음.
- 그냥 앞에서부터 잘라내면 됨.
- 또한 단어가 중복되는지 확인하는 것이니까 Map으로 할 필요 없이 그냥 Set으로 수정해두면 됨
- 아 이거 순차적으로 하면 할 수 있었는데 하기 싫었던걸까... 대체 왜 그거 하나를 못 해가지고 ㅠ
- 다시 풀어보자 추후에.. 그리고 이진탐색도 있던데 그건 뭐지..?
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        String[][] strings = new String[R][C];

        // 문자열 입력받기 (2차원 배열)
        for (int row = 0; row < R; row++) {
            strings[row] = br.readLine().split("");
        }

        Set<String> checkDuplicate;
        StringBuilder sb;
        String[] record = new String[C];
        int count = 0;

        // 각 열의 문자들을 이어붙여 하나의 문자열로 만들고 arr 배열에 저장
        for (int column = 0; column < C; column++) {
            sb = new StringBuilder();
            for (int row = 1; row < R; row++) { // 첫 번째 행은 중복될 일이 없으므로 1부터 탐색 (어차피 삭제할거니까)
                sb.append(strings[row][column]);
            }

            record[column] = sb.toString();  // 이어 붙인거 추가한다 (나중에 뒤에서 잘라줄거임)
        }


        // 각 행에 대해 중복된 부분 문자열이 있는지 검사
        sliceString:
        for (int row = 0; row < R; row++) {
            checkDuplicate = new HashSet<>();

            for (int c=0;c<C;c++) {
                String now = record[c].substring(row); // 현재 행부터 시작하는 부분 문자열 추출
                if (checkDuplicate.contains(now)) break sliceString; // 이미 존재하는 부분 문자열이면, 외부 반복문까지 탈출
                else checkDuplicate.add(now); // HashSet에 추가
            }
            count++; // 중복된 부분 문자열이 없으면 유효한 행으로 간주하고 카운트 증가
        }

        System.out.println(count);
    }
}