package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UncommonWords {
    public String[] uncommonFromSentences(String A, String B) {
        List<String> uncommon = new ArrayList<>();

        List<String> aWords = Arrays.asList(A.split(" "));
        List<String> bWords = Arrays.asList(B.split(" "));
        for (String word : aWords) {
            if (!uncommon.contains(word)) {
                if (!bWords.contains(word)) {
                    uncommon.add(word);
                }
            } else {
                uncommon.remove(word);
            }
        }

        for (String word : bWords) {
            if (!uncommon.contains(word)) {
                if (!aWords.contains(word)) {
                    uncommon.add(word);
                }
            } else {
                uncommon.remove(word);
            }
        }

        return uncommon.toArray(String[]::new);
    }


    public static void main(String[] args) {
        final UncommonWords uncommonWords = new UncommonWords();
        String[] uncommon = uncommonWords.uncommonFromSentences("s z eft", "s z z z s");
        System.out.println(Arrays.toString(uncommon));
        uncommon = uncommonWords.uncommonFromSentences("s z z z s", "s z eft");
        System.out.println(Arrays.toString(uncommon));
        uncommon = uncommonWords.uncommonFromSentences("apple apple", "banana");
        System.out.println(Arrays.toString(uncommon));
        uncommon = uncommonWords.uncommonFromSentences("banana", "apple apple");
        System.out.println(Arrays.toString(uncommon));
        uncommon = uncommonWords.uncommonFromSentences(
                "xfj vcahm vcahm frkqt oibcc jko oibcc frkqt ft tr",
                "lv ktx ktx tr x xfj xfj frkqt ktx ta tr yynk vcahm");
        System.out.println(Arrays.toString(uncommon));
    }
}
