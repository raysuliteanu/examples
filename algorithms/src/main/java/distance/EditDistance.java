package distance;

public class EditDistance {
    public static boolean OneEditApart(String s1, String s2) {
        return editDistance(s1, s2) == 1;
    }

    public static int editDistance(String one, String two) {
        int diffs = Math.abs(one.length() - two.length());
        if (diffs > 0) {
            return diffs;
        }

        for (int i = 0; i < one.length(); i++) {
            if (one.charAt(i) != two.charAt(i)) {
                ++diffs;
            }
        }

        return diffs;
    }
}
