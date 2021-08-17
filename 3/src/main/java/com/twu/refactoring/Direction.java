package com.twu.refactoring;

public class Direction {
    private final char direction;
    private CurrentDirection currentDirection;

    public Direction(char direction) {
        this.direction = direction;
    }

    public void setCurrentDirection(CurrentDirection currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Direction turnRight() {
        return currentDirection.turnRight();
    }

    public Direction turnLeft() {
        return currentDirection.turnLeft();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction1 = (Direction) o;

        return direction == direction1.direction;
    }

    @Override
    public int hashCode() {
        return (int) direction;
    }

    @Override
    public String toString() {
        return "Direction{direction=" + direction + '}';
    }
}
