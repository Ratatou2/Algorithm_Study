package Algorithm_0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_15650_NandM_2 {
    static int N, M;
    static int[] pick;
    static int[] isUsed;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pick = new int[M];
        isUsed = new int[N];
        sb = new StringBuilder();

        recursive(0, 0);

    }
    private static void recursive(int index, int start) {
        if (index == M) {
            printForMe();
            return;
        }

        for (int i = start; i < N; i++) {
            pick[index] = i + 1;
            recursive(index + 1, i + 1);

        }
    }

    private static void printForMe() {
        for (int temp : pick) sb.append(temp).append(" ");
        System.out.println(sb);
        sb = new StringBuilder();
    }
}