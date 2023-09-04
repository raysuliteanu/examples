package projecteuler;

public class Problem14 {
    public static void main(String[] args) {
        long maxChainLength = 0;
        long value = 1_000_000L - 1;
        long valueWithLongestChain = 0;
        while (value > 0) {
            long n = value;
            long chainLength = 1;
            while (n > 1) {
                ++chainLength;
                n = n % 2 == 0 ? n / 2 : (3 * n) + 1;
            }

            if (chainLength > maxChainLength) {
                maxChainLength = chainLength;
                valueWithLongestChain = value;
            }

            --value;
        }

        System.out.println("value with max chain length: " + valueWithLongestChain);
    }
}
