package fun.challenge;

import fun.challenge.constant.MoveResult;
import fun.challenge.controller.GameConsole;

/**
 * ConnectFour main class is starting point
 * and retrieve input from users (1 vs 1)
 */
public class ConnectFourMain {
    public static void main(String[] args) {
        // Start Game - init Board and display
        System.out.println("Game start [input 'x' for exit]");
        var game = new GameConsole();
        game.newGame();
        game.printBoard();

        var quitting = false;
        MoveResult result;
        // Enter Input phase
        while (!quitting) {
            // Player 1
            game.move('R');
            game.printBoard();
            result = game.isWin('R');
            if (result == MoveResult.WIN) {
                System.out.println("Player 1 [ RED ] wins!");
                quitting = true;
                continue;
            }

            // Player 2
            game.move('G');
            game.printBoard();
            result = game.isWin('G');
            if (result == MoveResult.WIN) {
                System.out.println("Player 2 [GREEN] wins!");
                quitting = true;
                continue;
            }

            // draw would happen at #42 move
            if (result == MoveResult.DRAW) {
                System.out.println("Game draw!");
                quitting = true;
            }
        }
    }
}
