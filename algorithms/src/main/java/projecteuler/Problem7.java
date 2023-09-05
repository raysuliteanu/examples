package projecteuler;

public class Problem7 {
    public static void main(String[] args) {
        int count = 1;
        long number = 1;
        while (count <= 10_001) {
            ++number;
            if (isPrime(number)) {
                System.out.println(count + " - a prime: " + number);
                ++count;
            }
        }
        System.out.println(number);
    }

    static boolean isPrime(long value) {
        if (value < 2) {
            return false;
        }

        if (value < 4) {
            return true;
        }

        if (value % 2 == 0) {
            return false;
        }

        if (value < 9) {
            return true;
        }

        if (value % 3 == 0) {
            return false;
        }

        int r = (int) Math.floor(Math.sqrt((double) value));
        int f = 5;
        while (f <= r) {
            if (value % f == 0) {
                return false;
            }

            if (value % (f + 2) == 0) {
                return false;
            }

            f = f + 6;
        }

        return true;
    }
}
