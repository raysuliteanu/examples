package misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ArrayManipulation {
    static long arrayManipulation(int n, int[][] queries) {
        TreeSet<Section> set = new TreeSet<>();
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int start = query[0] - 1;
            int end = query[1] - 1;
            int value = query[2];
            final Section section = new Section();
            section.id = i;
            section.start = start;
            section.end = end;
            section.value = value;
            set.add(section);
        }

        List<Set<Section>> overlaps = new ArrayList<>(queries.length);

        for (final Section section : set) {
            Set<Section> overlapping = new HashSet<>();
            overlapping.add(section);
            overlaps.add(overlapping);
            for (final Section section1 : set) {
                if (section.id != section1.id) {
                    if (section.start <= section1.start && section.end > section1.start) {
                        overlapping.add(section1);
                    }
                }
            }
        }

        int index = 0;
        int max = 0;
        for (int i = 0; i < overlaps.size(); i++) {
            Set<Section> temp = overlaps.get(i);
            if (temp.size() > max) {
                max = temp.size();
                index = i;
            }
        }

        Set<Section> target = overlaps.get(index);
        return target.stream().mapToLong(value -> value.value).sum();
    }

    private static class Section implements Comparable<Section> {
        int id;
        Integer start;
        Integer end;
        int value;

        @Override
        public int compareTo(final Section o) {
            return start.compareTo(o.start);
        }
    }

    public static void main(String[] args) {
        int[][] input = new int[4][3];
        input[0] = new int[]{2, 6, 8};
        input[1] = new int[]{3, 5, 7};
        input[2] = new int[]{1, 8, 1};
        input[3] = new int[]{5, 9, 15};
        System.out.println(arrayManipulation(10, input));
    }
}
