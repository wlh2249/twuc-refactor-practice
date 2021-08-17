package com.twu.refactoring;

public class South implements CurrentDirection{
    @Override
    public Direction turnRight() {
        return new Direction('W');
    }

    @Override
    public Direction turnLeft() {
        return new Direction('E');
    }
}
