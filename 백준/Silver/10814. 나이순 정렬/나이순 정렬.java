/*
[백준]
10814, 나이순 정렬

[문제파악]
- 온라인 저지에 가입한 사람들의 나이와 이름이 가입한 순서대로 주어진다.
- 이때, 회원들을 나이가 증가하는 순으로, 나이가 같으면 먼저 가입한 사람이 앞에 오는 순서로 정렬하는 프로그램을 작성하시오.

[입력]
- 첫째 줄에 온라인 저지 회원의 수 N이 주어진다. (1 ≤ N ≤ 100,000)
- 둘째 줄부터 N개의 줄에는 각 회원의 나이와 이름이 공백으로 구분되어 주어진다.
- 나이는 1보다 크거나 같으며, 200보다 작거나 같은 정수이고, 이름은 알파벳 대소문자로 이루어져 있고, 길이가 100보다 작거나 같은 문자열이다.
- 입력은 가입한 순서로 주어진다.

[출력]
- 첫째 줄부터 총 N개의 줄에 걸쳐 온라인 저지 회원을 나이 순, 나이가 같으면 가입한 순으로 한 줄에 한 명씩 나이와 이름을 공백으로 구분해 출력한다.

[구현방법]
- map을 써서 key값으로 정렬하고 그대로 출력하면 될듯하다
- 아 맵을 쓰면 key 값인 21이 중복된다면 못 넣네..
- 그냥 클래스를 만드는게 제일 나을 것 같다
- compareTo 쓰는 법 다 까먹진 않았네... 그나마 다행
- 이번엔 정렬하는걸 빼먹었는데 compareTo 짜줬으면 정렬을 해야 해당 기준으로 정렬한다
- 너무 당연한 소린데 이걸 깜빡함..

[보완점]

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Member implements Comparable<Member> {
        int num;
        int age;
        String name;

        Member(int num, int age, String name) {
            this.num = num;
            this.age = age;
            this.name = name;
        }

        public int compareTo(Member o) {
            if (this.age == o.age) return this.num - o.num;  // 나이가 같으면 가입 입력 순서로 우선순위를 나눈다
            return this.age - o.age;  // 나이가 다르면 나이순으로 우선순위를 나눈다
        }

        @Override
        public String toString() {
            return this.age + " " + this.name;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        ArrayList<Member> members = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            members.add(new Member(i, Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        Collections.sort(members);  // 정렬을 해줘야 compareTo로 짜놓은 기준으로 정렬이 되겠죠

        for (Member member : members) {
            sb.append(member).append("\n");
        }

        System.out.println(sb);
    }
}