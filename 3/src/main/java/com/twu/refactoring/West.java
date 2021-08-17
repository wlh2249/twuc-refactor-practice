package com.twu.refactoring;

public class West implements CurrentDirection{
    @Override
    public Direction turnRight() {
        return new Direction('N');
    }

    @Override
    public Direction turnLeft() {
        return new Direction('S');
    }
}
