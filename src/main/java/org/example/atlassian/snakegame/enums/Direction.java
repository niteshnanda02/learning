package org.example.atlassian.snakegame.enums;

public enum Direction {
    U(-1,0), D(1,0), R(0,1), L(0,-1);
    final int dx, dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction fromString(String directionStr) {
        try {
            return Direction.valueOf(directionStr);

        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid direction: " + directionStr);
        }
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
