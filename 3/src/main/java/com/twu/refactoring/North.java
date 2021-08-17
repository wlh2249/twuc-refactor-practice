package com.twu.refactoring;

public class North implements CurrentDirection{
    @Override
    public Direction turnRight() {
        return new Direction('E');
    }

    @Override
    public Direction turnLeft() {
        return new Direction('W');
    }
}
