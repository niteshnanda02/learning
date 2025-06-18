package org.example.atlassian.snakegame;

import org.example.atlassian.snakegame.service.SnakeGameService;
import org.example.atlassian.snakegame.service.SnakeGameServiceImpl;

public class Main {
    public static void main(String[] args) {
//        SnakeGameService snakeGameService = new SnakeGameServiceImpl(3, 2, new int[][]{{1, 2}, {0, 1}});
//        snakeGameService.printBoard();
//        System.out.println(snakeGameService.move("R")); // 0
//        snakeGameService.printBoard();
//        System.out.println(snakeGameService.move("D")); // 0
//        snakeGameService.printBoard();
//        System.out.println(snakeGameService.move("R")); // 1
//        snakeGameService.printBoard();
//        System.out.println(snakeGameService.move("U")); // 1
//        snakeGameService.printBoard();
//        System.out.println(snakeGameService.move("L")); // 2
//        snakeGameService.printBoard();
//        System.out.println(snakeGameService.move("U")); // -1

        SnakeGameService snakeGameService = new SnakeGameServiceImpl(5,7, new int[][]{{0,3},{2,3},{3,1},{1,0},{5,3},{6,0}});
        snakeGameService.printBoard();
        System.out.println("R " +snakeGameService.move("R"));
        snakeGameService.printBoard();
        System.out.println("R " +snakeGameService.move("R"));
        snakeGameService.printBoard();
        System.out.println("D " +snakeGameService.move("D"));
        snakeGameService.printBoard();
        System.out.println("R " +snakeGameService.move("R"));
        snakeGameService.printBoard();
        System.out.println("U " +snakeGameService.move("U"));
        snakeGameService.printBoard();
        System.out.println("R " +snakeGameService.move("R"));
        snakeGameService.printBoard();
        System.out.println("D " +snakeGameService.move("D"));
        snakeGameService.printBoard();
        System.out.println("D " +snakeGameService.move("D"));
        snakeGameService.printBoard();
        System.out.println("L " +snakeGameService.move("L"));
        snakeGameService.printBoard();
        System.out.println("L " +snakeGameService.move("L"));
        snakeGameService.printBoard();
        System.out.println("L " +snakeGameService.move("L"));
        snakeGameService.printBoard();
        System.out.println("D " +snakeGameService.move("D"));
        snakeGameService.printBoard();
        System.out.println("L " +snakeGameService.move("L"));
        snakeGameService.printBoard();
        System.out.println("U " +snakeGameService.move("U"));
        snakeGameService.printBoard();
        System.out.println("U " +snakeGameService.move("U"));
        snakeGameService.printBoard();


    }
}
