import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 20000303;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();

        int result = 0;
        for (int i = 0; i < N.length(); i++) {
            result = (int)((result * 10L + (N.charAt(i) - '0')) % MOD);
        }

        System.out.println(result);
    }
}