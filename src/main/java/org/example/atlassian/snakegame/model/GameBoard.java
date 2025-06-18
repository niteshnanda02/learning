package org.example.atlassian.snakegame.model;

public class GameBoard {
    private final int row;
    private final int col;
    private final int[][] food;
    private int foodIndex;


    public GameBoard(int row, int col, int[][] food) {
        this.row = row;
        this.col = col;
        this.food = food;
        this.foodIndex = 0;
    }


    public int getIndex(int row, int col) {
        return row * this.col + col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int[][] getFood() {
        return food;
    }

    public int getFoodIndex() {
        return foodIndex;
    }

    public boolean isOutOfBound(int newRow, int newCol) {
        return newRow < 0 || newRow >= row || newCol < 0 || newCol >= col;
    }

    public boolean isFood(int newRow, int newCol) {
        return foodIndex < food.length && food[foodIndex][0] == newRow && food[foodIndex][1] == newCol;
    }

    public void consumeFood() {
        foodIndex++;
    }

    public boolean hasNextFood() {
        return foodIndex<food.length;
    }

    public int[] peekNextFood() {
        if (!hasNextFood()) {
            return null;
        }
        return food[foodIndex];
    }
}
