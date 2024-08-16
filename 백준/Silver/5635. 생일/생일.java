/*
[백준]
5635, 생일

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
- 일, 월, 년도 순서의 입력이다 주의할 것
- 간단히 말하면
    - Comparable은 자기자신과의 비교다
    - Comparator는 두 객체간의 비교이다

- Comparable
    - 객체 자신의 자연 순서를 정의하는 데 사용됨
    - 클래스 내에서 단 하나의 정렬 기준만 가질 수 있음
    - `compareTo()` 메서드를 구현해야 함
- Comparator
    - 외부에서 객체를 비교하는 다양한 방법을 제공할 수 있음
    - 여러 개의 `Comparator`를 만들어 다양한 정렬 기준을 가질 수 있음
    - `compare()` 메서드를 구현해야 함

- `Comparable`을 사용하면 기본 정렬 기준이 하나로 고정됨
- `Comparator`를 사용하면 다양한 기준으로 유연하게 객체를 정렬할 수 있음
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
            return this.name + " / "
                    + this.year + ":"
                    + this.month + ":"
                    + this.date;
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
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        ArrayList<Info> people = new ArrayList<>();

        // 사람들 정보 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int date = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());
            people.add(new Info(name, year, month, date));
        }

        // 기존에 셋팅해둔 기준으로 정렬 진행
        Collections.sort(people);

        sb.append(people.get(N-1).name).append("\n").append(people.get(0).name);
        System.out.println(sb);
    }
}