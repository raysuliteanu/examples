package misc;

import java.util.Map;

public class ParkingGarage {

    enum CarType {
        Compact,
        Regular
    }

    final int totalCapacity;
    final int compactCapacity;

    int totalSize;
    int compactSize;

    public ParkingGarage(int totalCapacity, Map<CarType, Integer> capacity) {
        this.totalCapacity = totalCapacity;
        this.compactCapacity = capacity.get(CarType.Compact);
    }

    public synchronized boolean canPark(final CarType carType) {
        if (totalSize == totalCapacity) {
            return false;
        }

        if (carType == CarType.Regular) {
            return totalSize - compactCapacity > 0;
        }

        return true;
    }

    public synchronized boolean park(final CarType carType) {
        if (canPark(carType)) {
            switch (carType) {
                case Compact -> {
                    ++compactSize;
                    ++totalSize;
                }
                default -> ++totalSize;
            }

            assert compactSize <= compactCapacity : "invalid compactSize=" + compactSize + ";capacity=" + compactCapacity;
            assert totalSize <= totalCapacity : "invalid totalSize=" + totalSize + ";capacity=" + totalCapacity;

            return true;
        }

        return false;
    }

    public synchronized void unpark(final CarType carType) {
        if (totalSize != 0) {
            switch (carType) {
                case Compact -> {
                    --compactSize;
                    --totalSize;
                }
                default -> --totalSize;
            }
        }

        assert compactSize >= 0 : "invalid compactSize=" + compactSize;
        assert totalSize >= 0 : "invalid totalSize=" + totalSize;
    }

    public synchronized int available(final CarType carType) {
        int size = 0;
        switch (carType) {
            case Compact -> size = compactSize;
            case Regular -> size = totalSize - compactSize;
            default -> size = totalSize;
        }
        return size;
    }
}