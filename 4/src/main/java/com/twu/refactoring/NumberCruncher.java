package com.twu.refactoring;

import java.util.Arrays;

public class NumberCruncher {
    private final int[] numbers;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public int countEven() {
        return evenOddHelper(0);
    }

    public int countOdd() {
        return evenOddHelper(1);
    }

    private int evenOddHelper(int i) {
        return (int) Arrays.stream(numbers).filter(num -> num % 2 == i).count();
    }

    public int countPositive() {
        int count = 0;
        for (int number : numbers) {
            if (number >= 0) count++;
        }
        return count;
    }

    public int countNegative() {
        int count = 0;
        for (int number : numbers) {
            if (number < 0) count++;
        }
        return count;
    }
}
