import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String S, T;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();

        dfs(T);
        System.out.println(result);
    }

    private static void dfs(String current) {
        if (current.length() == S.length()) {
            if (current.equals(S)) {
                result = 1;
            }
            return;
        }

        if (current.length() == 0) return; // 혹시 몰라 방어코드

        if (current.charAt(current.length() - 1) == 'A') {
            dfs(current.substring(0, current.length() - 1));
        }

        if (current.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(current);
            sb.reverse();
            sb.deleteCharAt(sb.length() - 1);
            dfs(sb.toString());
        }
    }
}