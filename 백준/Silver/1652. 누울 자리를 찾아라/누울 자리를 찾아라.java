

/*
[백준]
1652, 누울 자리를 찾아라

[문제파악]
일 년 동안 세계일주를 하던 영식이는 여행을 하다 너무 피곤해서 근처에 있는 코레스코 콘도에서 하룻밤 잠을 자기로 하고 방을 잡았다.
코레스코 콘도에 있는 방은 NxN의 정사각형모양으로 생겼다.
방 안에는 옮길 수 없는 짐들이 이것저것 많이 있어서 영식이의 누울 자리를 차지하고 있었다.
영식이는 이 열악한 환경에서 누울 수 있는 자리를 찾아야 한다.
영식이가 누울 수 있는 자리에는 조건이 있다.
똑바로 연속해서 2칸 이상의 빈 칸이 존재하면 그 곳에 몸을 양 옆으로 쭉 뻗으면서 누울 수 있다.
가로로 누울 수도 있고 세로로 누울 수도 있다.
누울 때는 무조건 몸을 쭉 뻗기 때문에 반드시 벽이나 짐에 닿게 된다. (중간에 어정쩡하게 눕는 경우가 없다.)
만약 방의 구조가 위의 그림처럼 생겼다면, 가로로 누울 수 있는 자리는 5개이고, 세로로 누울 수 있는 자리는 4개 이다.
방의 크기 N과 방의 구조가 주어졌을 때, 가로로 누울 수 있는 자리와 세로로 누울 수 있는 자리의 수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 방의 크기 N이 주어진다.
N은 1이상 100이하의 정수이다.
그 다음 N줄에 걸쳐 N개의 문자가 들어오는데 '.'은 아무것도 없는 곳을 의미하고,
'X'는 짐이 있는 곳을 의미한다.

[출력]
첫째 줄에 가로로 누울 수 있는 자리와 세로로 누울 수 있는 자리의 개수를 출력한다.

[구현방법]
- 그냥 가로 세로 for문 한번씩 돌리면서 연속적으로 2칸 이상 붙어있는지 체크하면 된다
- 심지어 가로 체크하는건 input을 받을 때 체크하면 그만임
- 더 효율적인 방법이 없을까...? 싶다가도 흠... N이 최대 100이면 최악의 경우의 수엔 10,000에다가 2초면 문제가 없다
- 그리고 애초에 2칸 이상 붙어있으면 끝이긴한데 x로 나뉘어 있는 경우 즉 ..x..인 경우는 2칸으로 세어야할까?
    - 근데 애초에 그렇게 따지면 ****x같은 경우엔 누울 공간이 총 3개가 있다고 해야하는데 그게 아닌거 보면 한개로 퉁쳐도 괜찮을 것 같다
    - 예시에서 설명하고 있진 않아서 시간 절약을 위해 2칸 이상이 확보되면 바로 다음 행/열을 탐색하는 로직을 넣어봐야겠다
    - 이렇게 해서 통과를 못하면 짐으로 나눠진 공간도 계산에 넣어야할 것이다
    - 짐으로 나뉜 공간도 체크해야 함
    - 아니 근데 뭔가 이상한게 .....는 한개로 세면서 ..X..는 공간을 두개로센다 뭔..

- 아 근데 볼수록 한번에 깔끔하게 할 수 있을 것 같은데...
- 잘못 생각했던게 prev를 둬서 비교할게 아니고 그냥 '.' 갯수를 계속 세다가 'X'를 만나는 지점에서 그 갯수를 카운트해주면 된다
- 카운트가 2개 이상이면 그 X에 도달하기 전까지 2칸 이상이라는 의미니까 말이다

[보완점]
*/

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[][] room = new String[N][N];

        int rowResult = 0;
        int colResult = 0;

        // 방 구조 입력 받기 & 가로 빈 공간 체크
        for (int row = 0; row < N; row++) {
            room[row] = br.readLine().split("");
        }

        // 행 & 열 체크
        for (int row = 0; row < N; row++) {
            int rowCount = 0;
            int colCount = 0;

            for (int col = 0; col < N; col++) {
                String currRow = room[row][col];
                String currCol = room[col][row];

                // 현재 '.'라면? (X가 나오기 전까진 '.' 갯수만 세도 된다 if (currRow.equals(prevRow)) 같은 조건은 필요 X
                if (currRow.equals(".")) {
                     rowCount++;
                } else {
                    if (2 <= rowCount) rowResult++;
                    rowCount = 0;
                }

                // 현재 '.'라면?
                if (currCol.equals(".")) {
                    colCount++;
                } else {
                    if (2 <= colCount) colResult++;
                    colCount = 0;
                }
            }

            // (놓친 부분) 한줄 체크 끝나고나면 확인도 해야한다
            if (2 <= rowCount) rowResult++;
            if (2 <= colCount) colResult++;
        }

        System.out.println(rowResult + " " + colResult);
    }
}
