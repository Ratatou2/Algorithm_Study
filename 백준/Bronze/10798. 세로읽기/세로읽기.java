/*
[백준]
10798, 세로 읽기

[문제파악]
- 칠판에 붙여진 단어들이 주어질 때, 영석이가 세로로 읽은 순서대로 글자들을 출력하시오.

[입력]
- 총 다섯줄의 입력이 주어진다.
- 각 줄에는 최소 1개, 최대 15개의 글자들이 빈칸 없이 연속으로 주어진다.
- 주어지는 글자는 영어 대문자 ‘A’부터 ‘Z’, 영어 소문자 ‘a’부터 ‘z’, 숫자 ‘0’부터 ‘9’ 중 하나이다.
- 각 줄의 시작과 마지막에 빈칸은 없다.

[출력]
- 영석이가 세로로 읽은 순서대로 글자들을 출력한다.
- 이때, 글자들을 공백 없이 연속해서 출력한다.

[구현방법]
- 흠.. 길이가 다른 문자열이 들어오기도 하는데...
- 리스트로 하는게 낫나 어차피 한번씩 다 읽게 될텐데 자료구조는 큰 의미가 없을테니..
- 근데 가로 세로로 입력할거면 index 처리가 제일 낫겠는데요
- 리스트로 해버리면 세로로 읽을 때 charAt()하면서 또 순차적으로 읽어서 찾아가야하잖아
- 최대 15글자니까 그냥 읽어버리는게 나을지도
- 아 뭐가 효율적일까... 뭐가 시간이 젤 적게 걸리지
- 근데 어줍짢은 것 보다 15*15 배열 완탐이 더 빠를수도 있겠단 생각...?! 해봅시다
- 문득 든 생각인데, StringBUilder를 15개 만들어두고 그냥 처음부터 입력 순서대로 잘라서 index에 해당하는 StringBuilder에 밀어넣으면... 되는거 아닌가?
- 냅다해봤는데 됐음! for문이 많은 것은 맘에 안드는데, 어차피 2차원 배열로 만들어도 순환 참조 해야하는데 이게 더 빠르지 않을까?
- 일단 제출하고 더 효율적인 코드가 있는지 살펴봅시다

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int maxLength = 15;  // 여러개, 같은 숫자로 자주 쓰이는 것은 변수로 통일
        StringBuilder[] words = new StringBuilder[maxLength];

        // StringBuilder 초기화
        for (int i = 0; i < maxLength; i++) {
            words[i] = new StringBuilder(); 
        }

        for (int i = 0; i < 5; i++) {
            String input = br.readLine();

            for (int j = 0; j < input.length(); j++) {
                words[j].append(input.charAt(j));
            }
        }

        for (int i = 0; i < maxLength; i++) {
            sb.append(words[i].toString());
        }

        System.out.println(sb);
    }
}