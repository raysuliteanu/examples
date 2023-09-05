package misc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.currentThread;

class H2O {
    private final Molecule molecule = new Molecule();

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        final String name = currentThread().getName();

        molecule.addH();

        if (molecule.isComplete()) {
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        final String name = currentThread().getName();

        molecule.addO();

        if (molecule.isComplete()) {
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseOxygen.run();
        }

    }

    private static class Molecule {
        AtomicInteger countH = new AtomicInteger(0);
        AtomicInteger countO = new AtomicInteger(0);

        public void addH() {
            countH.incrementAndGet();
        }

        public void addO() {
            countO.incrementAndGet();
        }

        public synchronized boolean isComplete() {
            return countH.get() == 2 && countO.get() == 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        String input = "OOHHHH";
        String input = "HHHHHHOOO";
        final H2O h2O = new H2O();
        List<Thread> threads = new ArrayList<>(input.length());
        int hCount = 1;
        int oCount = 1;
        for (char c : input.toCharArray()) {
            if (c == 'H') {
                threads.add(new Thread(() -> {
                    try {
                        h2O.hydrogen(() -> System.out.println("H"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, "H" + hCount++));
            } else if (c == 'O') {
                threads.add(new Thread(() -> {
                    try {
                        h2O.oxygen(() -> System.out.println("O"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, "O" + oCount++));
            }
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
    }
}