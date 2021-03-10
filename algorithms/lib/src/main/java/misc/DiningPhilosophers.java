package misc;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class DiningPhilosophers {

    public static final int DEFAULT_BOUND = 500;
    public static final int DEFAULT_EXECUTION_DURATION = 10_000;

    private final int randomSleepBound;
    private final int executionDuration;

    public DiningPhilosophers(final int randomSleepBound, final int executionDuration) {
        this.randomSleepBound = randomSleepBound;
        this.executionDuration = executionDuration;
    }

    private static void log(String message) {
        final long time = System.currentTimeMillis();
        final String thread = Thread.currentThread().getName();
        System.out.println(time + " : " + thread + " : " + message);
    }

    public static void main(String[] args) {
        int bound = args.length == 1 ? Integer.parseInt(args[0]) : DEFAULT_BOUND;
        int executionDuration = args.length == 2 ? Integer.parseInt(args[1]) : DEFAULT_EXECUTION_DURATION;
        new DiningPhilosophers(bound, executionDuration).party();
    }

    public void party() {
        final Philosopher[] philosophers = new Philosopher[9];

        log("party started");

        prepareForDinner(philosophers);
        haveDinner(philosophers);
        goHome(philosophers);

        log("party over");
    }

    private void prepareForDinner(final Philosopher[] philosophers) {
        final Fork[] forks = putOutTheSilverware();
        for (int i = 0; i < 9; i++) {
            final Philosopher philosopher = new Philosopher(i, forks[i], forks[(i + 1) % 9]);
            philosophers[i] = philosopher;
            philosopher.start();
        }
    }

    private void haveDinner(final Philosopher[] philosophers) {
        try {
            Thread.sleep(executionDuration);
            log("party winding down");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (final Philosopher philosopher : philosophers) {
            philosopher.full();
        }
    }

    private void goHome(final Philosopher[] philosophers) {
        for (Philosopher philosopher : philosophers) {
            try {
                philosopher.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Fork[] putOutTheSilverware() {
        final Fork[] forks = new Fork[9];
        for (int i = 0; i < 9; i++) {
            forks[i] = new Fork(i);
        }
        return forks;
    }

    class Philosopher extends Thread {
        private final int number;
        private final Fork[] forks;
        private volatile boolean full;
        private final ForkStrategy forkStrategy = new CoordinatedStrategy();
//        private final ForkStrategy forkStrategy = new RandomStrategy();

        Philosopher(final int number, final Fork left, final Fork right) {
            super("Philosopher-" + number);
            this.number = number;
            forks = new Fork[]{left, right};
        }

        @Override
        public void run() {
            while (!full) {
                think();
                eat(forks);
            }

            log("full");
        }

        public void full() {
            log("getting full");
            full = true;
        }

        @Override
        public String toString() {
            return String.valueOf(number);
        }

        private void eat(Fork[] forks) {
            forkStrategy.pickUp(forks);

            log("eating");

            randomWait(randomSleepBound);

            forkStrategy.putDown(forks);
        }

        private void think() {
            randomWait(randomSleepBound);
        }

        private void randomWait(final int bound) {
            log("thinking");
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(bound));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Fork {
        // just to have print statements look nice
        final int number;

        // each fork can only be used by one philosopher at a time
        final Semaphore semaphore = new Semaphore(1);

        Fork(final int number) {
            this.number = number;
        }

        void pickUp() {
            try {
                semaphore.acquire();
                log("picked up fork " + number);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        void putDown() {
            semaphore.release();
            log("put down fork " + number);
        }
    }

    interface ForkStrategy {
        void pickUp(Fork[] forks);

        void putDown(Fork[] forks);
    }

    static class CoordinatedStrategy implements ForkStrategy {

        @Override
        public void pickUp(final Fork[] forks) {
            forks[0].pickUp();
            forks[1].pickUp();
        }

        @Override
        public void putDown(final Fork[] forks) {
            forks[1].putDown();
            forks[0].putDown();
        }
    }

    static class RandomStrategy implements ForkStrategy {

        private final Random random = new SecureRandom();

        @Override
        public void pickUp(final Fork[] forks) {
            if (random.nextInt() % 2 == 0) {
                forks[0].pickUp();
                forks[1].pickUp();
            }
            else {
                forks[1].pickUp();
                forks[0].pickUp();
            }
        }

        @Override
        public void putDown(final Fork[] forks) {
            if (random.nextInt() % 2 == 0) {
                forks[0].putDown();
                forks[1].putDown();
            }
            else {
                forks[1].putDown();
                forks[0].putDown();
            }
        }
    }
}
