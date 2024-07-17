/*
[백준]
1789, 수들의 합

[문제파악]
- 서로 다른 N개의 자연수의 합이 S라고 한다
- S를 알 때, 자연수 N의 최댓값을 구하라

[입력]
- 첫째줄에 자연수 (1 <= S <= 4,294,967,295)가 주어진다

[출력]
- 첫째줄에 자연수 N의 최댓값을 출력한다

[구현방법]
- 갯수가 최대려면 작은 것부터 빼가면서 갯수를 구하면 된다.

[보완점]
- S에 도달하는데는 빼가는 방식도, 더해가는 방식도 존재한다.
 
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long S = Long.parseLong(br.readLine());

        int start = 1;
        int count = 0;
        while (start <= S) {
            S -= start;
            count++;
            start++;
        }

        System.out.println(count);
    }
}