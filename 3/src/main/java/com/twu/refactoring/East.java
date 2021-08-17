package com.twu.refactoring;

public class East implements CurrentDirection{
    @Override
    public Direction turnRight() {
        return new Direction('S');
    }

    @Override
    public Direction turnLeft() {
        return new Direction('N');
    }
}
