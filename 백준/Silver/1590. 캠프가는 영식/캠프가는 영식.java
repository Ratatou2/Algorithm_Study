import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 버스 개수
        int T = Integer.parseInt(st.nextToken());  // 영식이 도착 시간

        int minWait = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());  // 시작 시간
            int I = Integer.parseInt(st.nextToken());  // 간격
            int C = Integer.parseInt(st.nextToken());  // 대수

            // T 이전이면 버스 못탐
            if (T <= S) {
                minWait = Math.min(minWait, S - T);
            } else {
                int k = (T - S + I - 1) / I;  // 올림 연산
                if (k < C) {
                    int nextBus = S + k * I;
                    minWait = Math.min(minWait, nextBus - T);
                }
            }
        }

        System.out.println(minWait == Integer.MAX_VALUE ? -1 : minWait);
    }
}