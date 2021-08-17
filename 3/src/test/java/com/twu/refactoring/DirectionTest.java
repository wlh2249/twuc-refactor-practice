package com.twu.refactoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DirectionTest {

    private final Direction north = new Direction('N');
    private final Direction east = new Direction('E');

    @BeforeEach
    public void setUp() {
        north.setCurrentDirection(new North());
        east.setCurrentDirection(new East());
    }

    @Test
    public void shouldTurnEastWhenTurnRightFromNorth() {
        Direction east = north.turnRight();
        assertThat(east, is(new Direction('E')));
    }

    @Test
    public void shouldTurnWestWhenTurnLeftFromNorth() {
        Direction west = north.turnLeft();
        assertThat(west, is(new Direction('W')));
    }

    @Test
    public void shouldTurnNorthWhenTurnLeftFromEast() {
        Direction north = east.turnLeft();
        assertThat(north, is(new Direction('N')));
    }
}
