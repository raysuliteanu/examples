package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TopKWordsByFrequencyTest {
    final TopKWordsByFrequency wordsByFrequency = new TopKWordsByFrequency();

    @Test
    void testTopK() {
        assertEquals(asList("i", "love"), wordsByFrequency.topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        assertEquals(asList("the", "is", "sunny", "day"), wordsByFrequency.topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
        assertTrue(wordsByFrequency.topKFrequent(new String[]{}, 1).isEmpty());
    }

    @Test
    public void popularity() {
        String[] features = {"a", "aa", "b", "c"};
        String[] responses = {"a", "a aa", "a a a a a", "b a"};

        String[] result = wordsByFrequency.sortFeatures(features, responses);
        assertArrayEquals(new String[]{"a", "aa", "b", "c"}, result);

        features = new String[]{"cooler", "lock", "touch"};
        responses = new String[]{"i like cooler cooler", "lock touch cool", "locker like touch"};

        result = wordsByFrequency.sortFeatures(features, responses);
        assertArrayEquals(new String[]{"touch", "cooler", "lock"}, result);

        features = new String[]{"gm", "gueh", "pagukeo", "mjfmxkpt", "nxehzmy", "gupciaqa", "szpke", "m", "fafxokiwj", "vnolbwofn", "rpsjw", "vkaouipu", "xcqshtxzn", "rzsntyo", "crcevj", "oec", "gflhav", "uvgvrcaat"};
        responses = new String[]{"gyr crcevj oec mjfmxkpt kkecx lzfkaylnlv m rurltdf bbxhe nxehzmy", "wavzoewzs fcoxpmy vnolbwofn gm xcqshtxzn gm wvod szpke mjfmxkpt uvgvrcaat", "uvgvrcaat uvgvrcaat sc xft vkaouipu rpsjw gyusm bbwuvi pagukeo cgxoylhuva", "kv gzkd evlz uvgvrcaat oec mjfmxkpt bazhpl xcqshtxzn xz mjfmxkpt", "szpke vkaouipu gupciaqa fjvct qykhiiwxhi fvkg aa oec nxehzmy uinreddwh", "nxehzmy osu hnb urllg uvgvrcaat gm rpsjw muyza nxehzmy gvk", "gupciaqa meix m mmfkox m tq gupciaqa hhoes pagukeo yoegfotbrs", "xcqshtxzn sbzvqgxyd wgwyxkxl szpke pkdhgta laqpvfhoh crcevj jkdfwfvxa hrsqbjulr gm", "sjoitocx szpke qstsf gflhav vnolbwofn qnjaklkw zgzhaseu fafxokiwj vp fafxokiwj"};

        result = wordsByFrequency.sortFeatures(features, responses);
        String[] expected = {"szpke", "uvgvrcaat", "gm", "mjfmxkpt", "nxehzmy", "xcqshtxzn", "oec", "pagukeo", "gupciaqa", "m", "vnolbwofn", "rpsjw", "vkaouipu", "crcevj", "fafxokiwj", "gflhav", "gueh", "rzsntyo"};
        assertArrayEquals(expected, result);
    }

    static final Pattern pattern = Pattern.compile("\"([a-z ]+)\"");

    @Test
    public void popularityLargeData() throws IOException {
        processFile("/features1.txt");
        processFile("/features2.txt");
    }

    private void processFile(final String file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file)))) {
            String line = reader.readLine();
            String[] features = pattern.matcher(line).results().map(mr -> mr.group(1)).toArray(String[]::new);
            System.out.println("features length = " + features.length);

            line = reader.readLine();
            String[] responses = pattern.matcher(line).results().map(mr -> mr.group(1)).toArray(String[]::new);

            long start = System.currentTimeMillis();
            final String[] result = wordsByFrequency.sortFeatures(features, responses);
            long end = System.currentTimeMillis() - start;
            System.out.println("took " + end + "ms");
            System.out.println(Arrays.toString(result));
        }
    }
}