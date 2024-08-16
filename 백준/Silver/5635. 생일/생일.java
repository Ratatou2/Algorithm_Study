/*
[백준]


[문제파악]
- 어떤 반에 있는 학생들의 생일이 주어졌을 때, 가장 나이가 적은 사람과 가장 많은 사람을 구하시오.

[입력]
- 첫째 줄에 반에 있는 학생의 수 n이 주어진다. (1 ≤ n ≤ 100)
- 다음 n개 줄에는 각 학생의 이름과 생일이 "이름 dd mm yyyy"와 같은 형식으로 주어진다.
- 이름은 그 학생의 이름이며, 최대 15글자로 이루어져 있다.
- dd mm yyyy는 생일 일, 월, 연도이다. (1990 ≤ yyyy ≤ 2010, 1 ≤ mm ≤ 12, 1 ≤ dd ≤ 31)
- 주어지는 생일은 올바른 날짜이며, 연, 월 일은 0으로 시작하지 않는다.
- 이름이 같거나, 생일이 같은 사람은 없다.

[출력]
- 첫째 줄에 가장 나이가 적은 사람의 이름, 둘째 줄에 가장 나이가 많은 사람 이름을 출력한다.

[구현방법]
- min, max 하나씩 잡고 String 자르고 나이에 해당하는거 비교해가며 해야할듯

[보완점]
- 일일이 변수에 넣고 비교하는게 제일 빠르긴한데, 다른 방식으로 풀어보고 싶었음.
- Comparator와 Comparable 차이를 잘 구분하고 구현하면 된다
-

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Info implements Comparable<Info> {
        String name;
        int year, month, date;

        public Info(String name, int year, int month, int date) {
            this.name = name;
            this.year = year;
            this.month = month;
            this.date = date;
        }

        @Override
        public String toString() {
            return this.name + " / " + this.year + ":" + this.month + ":" + this.date;
        }

        @Override
        public int compareTo(Info other) {
            if (this.year != other.year) return this.year - other.year;
            if (this.month != other.month) return this.month - other.month;

            return this.date - other.date;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<Info> people = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int date = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());
            people.add(new Info(name, year, month, date));
        }

        Collections.sort(people);

//        System.out.println(people.size());
//        for (Info temp : people) {
//            System.out.println(temp.toString());
//        }

        System.out.println(people.get(N-1).name);  // 나이가 어리다는 것은 태어난지 얼마 안됨 (= 년도가 높음)
        System.out.println(people.get(0).name);
    }
}