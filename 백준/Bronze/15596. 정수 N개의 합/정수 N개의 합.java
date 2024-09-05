public class Test {
    long sum(int[] a) {
        long ans = 0;
        
        for (int temp : a) {
            ans += temp;
        }
        
        return ans;
    }
}