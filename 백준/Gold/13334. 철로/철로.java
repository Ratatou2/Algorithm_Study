import java.io.*;
import java.util.*;

public class Main {
    static class Person {
        int start, end;
        Person(int a, int b) {
            this.start = Math.min(a, b);
            this.end = Math.max(a, b);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Person> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            list.add(new Person(h, o));
        }

        int d = Integer.parseInt(br.readLine());

        // end 기준으로 오름차순 정렬
        list.sort(Comparator.comparingInt(p -> p.end));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int max = 0;

        for (Person p : list) {
            // 현재 끝점을 기준으로 d 거리 내에서만 시작점 허용
            pq.add(p.start);
            while (!pq.isEmpty() && pq.peek() < p.end - d) {
                pq.poll();
            }
            max = Math.max(max, pq.size());
        }

        System.out.println(max);
    }
}