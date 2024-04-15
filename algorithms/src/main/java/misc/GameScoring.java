package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Game Scoring
 * Imagine a game where the player can score 1, 2, or 3 points depending on the move they make.
 * Write a function or functions, that for a given final score computes every combination of
 * points that a player could score to achieve the specified score in the game.
 * <p><b>Signature</b>
 * <code>
 * int[][] gameScoring(int score)
 * </code>
 * </p>
 * <p><b>Input</b></p>
 * <p>Integer score representing the desired score</p>
 * <p><b>Output</b></p>
 * <p>Array of sorted integer arrays demonstrating the combinations of points that can sum to the target score.</p>
 */
public class GameScoring {
    private final static int[] points = {1, 2, 3};
    
    public int[][] gameScoring(int score) {
        if (score <= 0) {
            return new int[0][0];
        }

        List<List<Integer>> results = new ArrayList<>();

        Stack<Integer> combo = new Stack<>();
        for (int point : points) {
            if (point == score) {
                results.add(Collections.singletonList(point));
            }
            else {
                combo.push(point);
                scoreRecursive(combo, score, point, results);
                combo.pop();
                assert combo.isEmpty();
            }
        }

        int[][] answer = new int[results.size()][];
        for (int i = 0; i < results.size(); i++) {
            answer[i] = results.get(i).stream().mapToInt(Integer::intValue).toArray();
        }

        return answer;
    }

    void scoreRecursive(Stack<Integer> combo, int targetScore, int currentScore, List<List<Integer>> results) {
        if (currentScore == targetScore) {
            results.add(new ArrayList<>(combo));
            return;
        }

        for (int point : points) {
            if (currentScore < targetScore) {
                combo.push(point);
                scoreRecursive(combo, targetScore, currentScore + point, results);
                combo.pop();
            }
        }
    }
}
