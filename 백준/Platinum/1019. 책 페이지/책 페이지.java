import java.io.*;

public class Main {
    static long[] count = new long[10];

    private static void addDigitCount(int num, long digit) {
        while (0 < num) {
            int d = num % 10;
            count[d] += digit;
            num /= 10;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int start = 1;
        int end = N;
        long digit = 1;

        while (start <= end) {
            // 1. start 오른쪽 정렬 (1의 자리를 0으로)
            while (start % 10 != 0 && start <= end) {
                addDigitCount(start, digit);
                start++;
            }

            // 2. end 오른쪽 정렬 (1의 자리를 9로)
            while (end % 10 != 9 && start <= end) {
                addDigitCount(end, digit);
                end--;
            }

            // 3. 한 덩어리 계산 (start ~ end가 0~9 정렬됨)
            if (end < start) break;
            long countRange = (end / 10 - start / 10 + 1);

            for (int i = 0; i < 10; i++) {
                count[i] += countRange * digit;
            }

            start /= 10;
            end /= 10;
            digit *= 10;
        }

        for (int i = 0; i < 10; i++) {
            sb.append(count[i]).append(" ");
        }

        System.out.println(sb);
    }
}