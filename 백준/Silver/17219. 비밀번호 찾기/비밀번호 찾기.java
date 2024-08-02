/*
[백준]
17219, 비밀번호 찾기

[문제파악]
- 메모장에 사이트와 비밀번호가 저장되어있다
- 사이트 입력이 들어오면 비밀번호를 찾자

[입력]
- 첫째줄에 저장된 사이트 주소의 수와 비밀번호를 찾으련느 사이트 주소의 수 M가 주어진다 (1 <= N <= 100,000)
- 둘째줄부터 N줄에 걸쳐 사이트 주소와 비밀번호가 공백으로 구분되어 주어진다
	- 사이트 주소는 소문자, 대문자, 대시, 마침표로 이뤄져있고 중복되지 않는다
	- 비밀번호는 알파벳 대문자로만 이뤄져 있으며 모두 길이는 최대 20자이다
- N+2번째 줄부터 M개의 줄에 걸쳐 비밀번호를 찾으려는 사이트 주소가 한줄에 하나씩 입력된다
	- 이때, 반드시 이미 저장된 사이트 주소가 입력된다


[출력]
- 비밀번호 찾으려는 사이트의 비밀번호를 한줄에 하나씩 출력

[구현방법]
- 문자열 집합과 동일한 문제
- 사이트-비번 한쌍이니까 Map 써야할듯
- 오히려 문자열 집합을 Set으로 풀었어야 했는데...

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        Map<String, String> siteNpassword = new HashMap<>();

        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            siteNpassword.put(temp[0], temp[1]);
        }

        for (int i = 0; i < M; i++) {
            String siteLink = br.readLine();
            sb.append(siteNpassword.get(siteLink)).append("\n");
        }

        System.out.println(sb);
    }
}