package misc;

import java.util.ArrayList;
import java.util.List;

/**
 * There are a number of plants in a garden. Each of the plants has been treated with some amount of pesticide.
 * After each day, if any plant has more pesticide than the plant on its left, being weaker than the left one, it dies.
 * <p>
 * You are given the initial values of the pesticide in each of the plants. Determine the number of days after which no
 * plant dies, i.e. the time after which there is no plant with more pesticide content than the plant to its left.
 * <p>
 * Constraints
 * 1 <= N <= 10^5
 * 0 <= p[i] <= 10^9
 *
 * @see <a href="https://www.hackerrank.com/contests/hacker-rank-problems-01/challenges/poisonous-plants">poinsonus-plants</a>
 */
public class Plants {

    static class Plant {
        int pesticide;
        boolean died;

        public Plant(int pesticide) {
            this.pesticide = pesticide;
        }
    }

    public int poisonousPlants(int[] p) {
        List<Plant> plants = new ArrayList<>(p.length);
        for (int i : p) {
            plants.add(new Plant(i));
        }

        boolean died;
        int days = 0;
        do {
            for (int i = 1; i < plants.size(); i++) {
                if (plants.get(i - 1).pesticide < plants.get(i).pesticide) {
                    plants.get(i).died = true;
                }
            }

            died = plants.stream().anyMatch((plant) -> plant.died);
            if (died) {
                plants = plants.stream()
                        .filter((plant) -> !plant.died)
                        .toList();

                ++days;
            }
        } while (died);

        return days;
    }
}
