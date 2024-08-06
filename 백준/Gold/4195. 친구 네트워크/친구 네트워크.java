/*
[백준]
4195, 친구 네트워크

[문제파악]
- 어떤 사이트의 친구 관계가 생긴 순서대로 주어졌을 때, 두 사람의 친구 네트워크에 몇 명이 있는지 구하라.
- 친구 네트워크란 친구 관계만으로 이동할 수 있는 사이를 말한다.


[입력]
- 첫째 줄에 테스트 케이스의 개수
- 각 테스트 케이스의 첫째 줄에는 친구 관계의 수 F가 주어지고, 이 값은 100,000을 넘지 않는다.
- 다음 F개의 줄에는 친구 관계가 생긴 순서대로 주어진다.
- 친구 관계는 두 사용자의 아이디로 이루어져 있으며, 알파벳 대문자 또는 소문자로만 이루어진 길이 20 이하의 문자열이다.

[출력]
- 친구 관계가 생길 때마다, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.


[구현방법]
- Map으로 <Name, 친구관계 ArrayList 하면 될 것 같은디

[보완점]
- 이거.. Union-find 써야 풀 수 있음 ㅠ
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] parent; //유니온 집합셋
    private static int[] count; //친구 관계 수

    private static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }

    private static int union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (a < b) {
                parent[b] = a; //집합 union
                count[a] += count[b]; //친구수 union
                return count[a];
            } else {
                parent[a] = b;
                count[b] += count[a];
                return count[b];
            }
        }

        return count[a];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int F = Integer.parseInt(br.readLine());
            parent = new int[F * 2];
            count = new int[F * 2];

            for (int i = 0; i < F * 2; i++) {
                parent[i] = i;
            }

            Arrays.fill(count, 1); //최초 친구 수는 기본값으로 한명이다
            HashMap<String, Integer> connection = new HashMap<>(); // 이름, 인덱스(노드번호)
            int index = 0;
            
            for (int f = 0; f < F; f++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String friend1 = st.nextToken();
                String friend2 = st.nextToken();

                //해당 친구이름이 아직 없는 경우 인덱스 부여
                if (!connection.containsKey(friend1)) connection.put(friend1, index++);
                if (!connection.containsKey(friend2)) connection.put(friend2, index++);

                sb.append(union(connection.get(friend1), connection.get(friend2))).append("\n");
            }
        }

        System.out.println(sb);
    }
}