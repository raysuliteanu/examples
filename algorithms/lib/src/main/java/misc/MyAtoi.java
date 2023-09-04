package misc;

public class MyAtoi {
    public static int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        int i = 0;
        char[] chars = s.toCharArray();
        for (; i < chars.length; i++) {
            if (chars[i] != ' ') break;
        }

        if (i == chars.length) return 0;

        boolean isPositive = true;

        if (chars[i] == '-' || chars[i] == '+') {
            if (chars[i] == '-') isPositive = false;

            ++i;
        }

        int value = 0;

        for (; i < chars.length; i++) {
            if (!Character.isDigit(chars[i])) break;

            if (value > Integer.MAX_VALUE / 10 || value == Integer.MAX_VALUE / 10 && chars[i] - '0' > Integer.MAX_VALUE % 10) {
                return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            value = value * 10 + (chars[i] - '0');
        }

        return isPositive ? value : -value;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("-2147483647"));
        System.out.println(myAtoi("  0000000000012345678"));
        System.out.println(myAtoi("1"));
        System.out.println(myAtoi("47"));
        System.out.println(myAtoi(Integer.MAX_VALUE + "1"));
        System.out.println(myAtoi(Integer.MIN_VALUE + "1"));
        System.out.println(myAtoi("-2147483647"));
    }
}
