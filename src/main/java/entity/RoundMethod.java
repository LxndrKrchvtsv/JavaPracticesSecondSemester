package entity;

public enum RoundMethod {
    FLOOR {
        @Override
        double roundFunction(double value) {
            return Math.floor(value);
        }
    },
    ROUND {
        @Override
        double roundFunction(double value) {
            return Math.round(value);
        }
    },
    CEIL {
        @Override
        double roundFunction(double value) {
            return Math.ceil(value);
        }
    };

    abstract double roundFunction(double value);

    public int round(double roundedValue, int d) {
        int tenPow = tenPow(d);
        int result = (int) roundFunction(roundedValue / tenPow) * tenPow;
        return result;
    }

    private static int tenPow(int d) {
        int[] powersOfTen = {1, 10, 100, 1000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000};
        return powersOfTen[d];
    }
}