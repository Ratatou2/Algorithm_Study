/*
[백준]
5622, 다이얼

[문제파악]
- 상근이의 할머니는 아래 그림과 같이 오래된 다이얼 전화기를 사용한다.
- 전화를 걸고 싶은 번호가 있다면, 숫자를 하나를 누른 다음에 금속 핀이 있는 곳 까지 시계방향으로 돌려야 한다.
- 숫자를 하나 누르면 다이얼이 처음 위치로 돌아가고, 다음 숫자를 누르려면 다이얼을 처음 위치에서 다시 돌려야 한다.
- 숫자 1을 걸려면 총 2초가 필요하다. 1보다 큰 수를 거는데 걸리는 시간은 이보다 더 걸리며, 한 칸 옆에 있는 숫자를 걸기 위해선 1초씩 더 걸린다.
- 상근이의 할머니는 전화 번호를 각 숫자에 해당하는 문자로 외운다.
- 즉, 어떤 단어를 걸 때, 각 알파벳에 해당하는 숫자를 걸면 된다. 예를 들어, UNUCIC는 868242와 같다.
- 할머니가 외운 단어가 주어졌을 때, 이 전화를 걸기 위해서 필요한 최소 시간을 구하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 알파벳 대문자로 이루어진 단어가 주어진다.
- 단어의 길이는 2보다 크거나 같고, 15보다 작거나 같다.

[출력]
- 첫째 줄에 다이얼을 걸기 위해서 필요한 최소 시간을 출력한다.

[구현방법]
- 이거 뭔가 대단한 방식이라던가 구현이 있는줄 알았는데 그런거 없다 ㅋㅋ
- 그냥 진짜 정직하게 하나하나 다 조건문 걸면 되는 문제였음..
- 나만 어렵게 생각한걸까...

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 단어 받기
        String word = br.readLine();
        int totalTime = 0;

        // 알파벳에 따른 다이얼 숫자 매핑 계산
        for (char c : word.toCharArray()) {
            if (c >= 'A' && c <= 'C') totalTime += 3;  // 2
            else if (c >= 'D' && c <= 'F') totalTime += 4;  // 3
            else if (c >= 'G' && c <= 'I') totalTime += 5;  // 4
            else if (c >= 'J' && c <= 'L') totalTime += 6;  // 5
            else if (c >= 'M' && c <= 'O') totalTime += 7;  // 6
            else if (c >= 'P' && c <= 'S') totalTime += 8;  // 7
            else if (c >= 'T' && c <= 'V') totalTime += 9;  // 8
            else if (c >= 'W' && c <= 'Z') totalTime += 10; // 9
        }

        // 결과 출력
        System.out.println(totalTime);
    }
}