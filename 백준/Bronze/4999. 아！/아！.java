/*
[백준]
4999, 아!

[문제파악]
- 사설이 일단 너무 길고 재환이가 의사가 요구하는 것보다 길게 낼 수 있는지를 알아내라고 함

[입력]
- 입력은 두 줄로 이루어져 있다.
- 첫째 줄은 재환이가 가장 길게 낼 수 있는 "aaah"이다.
- 둘째 줄은 의사가 듣기를 원하는 "aah"이다.
- 두 문자열은 모두 a와 h로만 이루어져 있다.
- a의 개수는 0보다 크거나 같고, 999보다 작거나 같으며, 항상 h는 마지막에 하나만 주어진다.

[출력]
- 재환이가 그 병원에 가야하면 "go"를, 아니면 "no"를 출력한다.

[구현방법]
- 그냥 길이 비교하면 그만이다

[보완점]

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int J = br.readLine().length();
        int doctor = br.readLine().length();

        System.out.println(J < doctor ? "no" : "go");
    }
}