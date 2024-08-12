import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static int[] input;
    static List<ArrayList<Integer>> tree;

    static void search(int start, int end, int depth) {
        if (depth == K) return;

        int mid = (start + end) / 2;

        tree.get(depth).add(input[mid]);

        search(start, mid - 1, depth + 1);
        search(mid + 1, end, depth + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        input = new int[(int) (Math.pow(2, K) - 1)];

        int index = 0;
        while (st.hasMoreTokens()) {
            input[index++] = Integer.parseInt (st.nextToken());
        }

        tree = new ArrayList<>();
        for (int i = 0; i < K; i++) tree.add(new ArrayList<>());

        search(0, input.length - 1, 0);

        for (ArrayList<Integer> depth : tree) {
            for (int node : depth) sb.append(node).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);
    }
}