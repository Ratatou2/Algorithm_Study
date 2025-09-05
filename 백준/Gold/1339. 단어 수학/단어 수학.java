

/*
[백준]
1339, 단어수학

[문제파악]
민식이는 수학학원에서 단어 수학 문제를 푸는 숙제를 받았다.
단어 수학 문제는 N개의 단어로 이루어져 있으며, 각 단어는 알파벳 대문자로만 이루어져 있다.
이때, 각 알파벳 대문자를 0부터 9까지의 숫자 중 하나로 바꿔서 N개의 수를 합하는 문제이다.
같은 알파벳은 같은 숫자로 바꿔야 하며, 두 개 이상의 알파벳이 같은 숫자로 바뀌어지면 안 된다.
예를 들어, GCF + ACDEB를 계산한다고 할 때, A = 9, B = 4, C = 8, D = 6, E = 5, F = 3, G = 7로 결정한다면, 두 수의 합은 99437이 되어서 최대가 될 것이다.
N개의 단어가 주어졌을 때, 그 수의 합을 최대로 만드는 프로그램을 작성하시오.

[입력]
첫째 줄에 단어의 개수 N(1 ≤ N ≤ 10)이 주어진다.
둘째 줄부터 N개의 줄에 단어가 한 줄에 하나씩 주어진다.
단어는 알파벳 대문자로만 이루어져있다.
모든 단어에 포함되어 있는 알파벳은 최대 10개이고, 수의 최대 길이는 8이다.
서로 다른 문자는 서로 다른 숫자를 나타낸다.

[출력]
첫째 줄에 주어진 단어의 합의 최댓값을 출력한다.

[구현방법]
- 일단 인풋을 전부 저장한다
- 그리고 저장한 인풋을 문자열의 길이대로 내림차순 정렬을 한다
- 그러면 가장 앞에 있는 문자열의 길이가 가장 길 것이고 그 맨앞에 있는 문자열이 큰 숫자일수록 전체적인 합산은 커질 것이다

- 내 아이디어에 문제가 하나 있었다
    - 바로 자릿수가 같은 경우엔 우선순위 기준을 잡기 어렵다는 것 (ABC + DEF)
- 내 생각보다 더 좋은 아이디어가 있었다
- 바로 가중치를 두는 것! 그냥 별거 없이 각 자릿수에 대해 *10 하고 계산하면? 훨씬 더 낫다

[보완점]
*/

import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        char key;
        int value;

        Node (char key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return o.value - this.value;
        }

//        @Override
//        public String toString (){
//            return "key : " + key + " / value : " + value;
//        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] weights = new int[26];  // 가중치 계산
        int[] values = new int[26];  // 점수 계산

        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        // 문자열 입력 받기
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            words[i] = input;

            // 각 단어의 가중치 계산
            for (int c = 0; c < input.length(); c++) {
                char curr = input.charAt(c);
                int index = curr - 'A';

                // 자릿수에 따라 10의 제곱수로 계산
                weights[index] += (int) Math.pow(10, (input.length() - c));
            }
        }

        // 가중치에 따라 정렬
        List<Node> orderByWeight = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (weights[i] == 0) continue;  // 등장한적 없는 문자열이면 패스
            
            orderByWeight.add(new Node((char) ('A' + i), weights[i]));
        }

        // 가중치가 큰 순서대로 정렬
        Collections.sort(orderByWeight);

        // 가중치에 따라 9부터 숫자 부여
        int num = 9;
        for (Node curr : orderByWeight) {
            values[curr.key - 'A'] = num;
            if (0 < num) num--;
        }

        // 가중치로 숫자 계산
        int sum = 0;
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(values[word.charAt(i) - 'A']);
            }

            sum += Integer.parseInt(sb.toString());
        }

        System.out.println(sum);
    }
}
