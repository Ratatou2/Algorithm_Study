/*

[백준]
25192, 인사성 밝은 곰곰이

[문제파악]
- 알고리즘 입문방 오픈 채팅방에서는 새로운 분들이 입장을 할 때마다 곰곰티콘을 사용해 인사함
- 곰곰티콘이 사용된 횟수를 구해 보자
- ENTER는 새로운 사람이 채팅방에 입장했음을 나타냄.
- 그 외는 채팅을 입력한 유저의 닉네임을 나타낸다.
    - 닉네임은 숫자 또는 영문 대소문자로 구성되어 있다.
- 새로운 사람이 입장한 이후 처음 채팅을 입력하는 사람은 반드시 곰곰티콘으로 인사를 한다.
- 그 외의 기록은 곰곰티콘을 쓰지 않은 평범한 채팅 기록이다.
- 채팅 기록 중 곰곰티콘이 사용된 횟수를 구해보자!

[입력]
- 첫 번째 줄에는 채팅방의 기록 수를 나타내는 정수 N (1 <= N <= 100,000)
- 두 번째 줄부터 N개의 줄에 걸쳐 새로운 사람의 입장을 나타내는 ENTER or 채팅을 입력한 유저의 닉네임이 문자열로 주어진다.
    - 문자열 길이 1 <= 문자열 길이 <= 20
- 첫 번째 주어지는 문자열은 무조건 ENTER

[출력]
- 채팅 기록 중 곰곰티콘이 사용된 횟수를 출력

[구현방법]
- 일단은 Enter 이후로 들어온 계정들은 Set에다가 넣으며 카운트한다. (중복이면 카운트 X)
- 그리고 Enter가 입력으로 들어올 때마다 set을 초기화하면 됨
    - 문제를 제대로 이해한게 맞다면, 새로운 사람이 들어오면 이전에 이모티콘을 썼던 사람도 이모티콘으로 인사하게 됨
    - 문제는 이모티콘을 '몇 번 썼느냐'니까 이 이모티콘까지 카운트 해야하는 것으로 이해함

[보완점]

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        HashSet<String> chatRecord = new HashSet<>();
        int count = 0;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            // ENTER 발생하면 이전 기록과 상관없이 또다시 이모티콘 인사 가능
            if (input.equals("ENTER")) {
                chatRecord = new HashSet<>();
            } else if (!chatRecord.contains(input)) {
                chatRecord.add(input);
                count++;
            }
        }

        System.out.println(count);
    }
}