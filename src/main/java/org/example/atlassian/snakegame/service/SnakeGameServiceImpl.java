package org.example.atlassian.snakegame.service;

import org.example.atlassian.snakegame.enums.Direction;
import org.example.atlassian.snakegame.model.GameBoard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class SnakeGameServiceImpl implements SnakeGameService {
    private final GameBoard board;
    private final Deque<Integer> snakeBody;
    private final Set<Integer> occupied;
    private int score;

    public SnakeGameServiceImpl(int width, int height, int[][] food) {
        this.board = new GameBoard(height, width, food);
        this.snakeBody = new ArrayDeque<>();
        this.occupied = new HashSet<>();
        int startIndex = board.getIndex(0, 0);
        this.snakeBody.addLast(startIndex);
        this.occupied.add(startIndex);
        score = 0;
    }


    @Override
    public int move(String directionStr) {
        Direction direction = Direction.fromString(directionStr);
        int currentIndex = snakeBody.peekLast();
        int currentRow = currentIndex / board.getCol();
        int currentCol = currentIndex % board.getCol();

        int newRow = currentRow+direction.getDx();
        int newCol = currentCol+direction.getDy();

        if (board.isOutOfBound(newRow, newCol))
            return -1;

        int newIndex = board.getIndex(newRow, newCol);

        // move the tail first(until eating food)
        boolean isFood = board.isFood(newRow, newCol);
        if (!isFood){
            int tail = snakeBody.peekFirst();
            snakeBody.pollFirst();
            occupied.remove(tail);
        }



        // self collision check
        if (occupied.contains(newIndex)) {
            return -1;
        }

        // add new head
        snakeBody.addLast(newIndex);
        occupied.add(newIndex);

        if (isFood){
            score++;
            board.consumeFood();
        }

        return score;
    }

    @Override
    public void printBoard() {
        int row = board.getRow();
        int col = board.getCol();
        char[][] grid = new char[row][col];

        // fill with empty cell
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = '.';
            }
        }

        if (board.hasNextFood()){
            int[] food = board.peekNextFood();
            grid[food[0]][food[1]] = 'F'; // mark food
        }

        // make snake body
        for(int index : snakeBody) {
            int r = index / col;
            int c = index % col;
            grid[r][c] = 'S'; // mark snake body
        }

        // mark head differently
        int head = snakeBody.peekLast();
        int headRow = head / col;
        int headCol = head % col;
        grid[headRow][headCol] = 'H'; // mark snake head

        // print the grid
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
