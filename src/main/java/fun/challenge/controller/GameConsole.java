package fun.challenge.controller;

import fun.challenge.constant.MoveResult;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Game Console - to be a board controller for the game.
 *
 * Connect Four board dimension is 6x7 (row x column)
 */
public class GameConsole {
    private List<Deque<Character>> stackList;
    private Character[][] board;

    /**
     * for print board with stack of moves
     */
    public void printBoard() {
        for (var row : board) {
            System.out.printf("|%s|%s|%s|%s|%s|%s|%s|%n",
                    row[0],
                    row[1],
                    row[2],
                    row[3],
                    row[4],
                    row[5],
                    row[6]);
        }
    }

    /**
     * for reset board and stack to init state
     */
    public void newGame() {
        // use ArrayDeque as Sonarqube mentioned to replace Stack with Deque type
        stackList = List.of(
                new ArrayDeque<>(),
                new ArrayDeque<>(),
                new ArrayDeque<>(),
                new ArrayDeque<>(),
                new ArrayDeque<>(),
                new ArrayDeque<>(),
                new ArrayDeque<>());

        // board row -> column
        board = new Character[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };
    }

    /**
     * for push the move into the board and stack
     *
     * @param piece of move in each turn, R - represent RED, G - Represent GREEN
     */
    public void move(Character piece) {
        // read input value from user
        if (piece == 'R') {
            System.out.print("Player 1 [ RED ] - choose column (1-7): ");
        } else {
            System.out.print("Player 2 [GREEN] - choose column (1-7): ");
        }
        // initialize console input reader
        var scanner = new Scanner(System.in);
        var move = scanner.nextLine();

        // validate input and convert to stack number
        // in the case redo the input, make sure with 'void return' to cut out from recursive to go further when back
        var columnNo = checkMove(move);
        // transform move number into position of stack
        var position = columnNo - 1;
        if (columnNo == -1) {
            System.out.println("Early exited (x)");
            System.exit(0);
        } else if (columnNo == 0) {
            System.out.println("Invalid, input must be number between 1 - 7");
            move(piece);
            return;
        } else if (stackList.get(position).size() >= 6) {
            System.out.println("Selected column is full, try again...");
            move(piece);
            return;
        }
        // push picked up piece into stack
        stackList.get(position).push(piece);
        // pick same column of stack from board,
        // the board is rendered from above to bottom, then place piece from bottom
        // to replace 'empty' with piece
        board[6 - stackList.get(position).size()][position] = piece;
    }

    /**
     * Determine the move is properly or request exit
     *
     * @param move represent selected number, or 'x' if quit game early
     * @return move number, or else handling number 0/1
     */
    private Integer checkMove(String move) {
        if ("x".equalsIgnoreCase(move)) {
            return -1;
        } else if (move.matches("[1-7]")) {
            return Integer.parseInt(move);
        } else {
            return 0;
        }
    }

    /**
     * Check the move result, does it make player victory
     *
     * @param piece presenter of move for check on its turn
     * @return Result of move, WIN or board full and DRAW
     */
    public MoveResult isWin(Character piece) {
        // calculate the win
        // horizontal
        for (var row = 0; row < 6; row++) {
            for (var column = 0; column < 4; column++) {
                if (Objects.equals(piece, board[row][column]) &&
                    Objects.equals(piece, board[row][column + 1]) &&
                    Objects.equals(piece, board[row][column + 2]) &&
                    Objects.equals(piece, board[row][column + 3])) {
                    return MoveResult.WIN;
                }
            }
        }

        // vertical
        for (var column = 0; column < 7; column++) {
            for (var row = 0; row < 3; row++) {
                if (Objects.equals(piece, board[row][column]) &&
                    Objects.equals(piece, board[row + 1][column]) &&
                    Objects.equals(piece, board[row + 2][column]) &&
                    Objects.equals(piece, board[row + 3][column])) {
                    return MoveResult.WIN;
                }
            }
        }

        // diagonal
        // - diagonal top-left to bottom-right
        // - diagonal top-right to bottom-left
        for (var column = 0; column < 4; column++) {
            for (var row = 0; row < 3; row++) {
                if ((Objects.equals(piece, board[row][column]) &&
                     Objects.equals(piece, board[row + 1][column + 1]) &&
                     Objects.equals(piece, board[row + 2][column + 2]) &&
                     Objects.equals(piece, board[row + 3][column + 3]))
                ||
                    (Objects.equals(piece, board[row + 3][column]) &&
                     Objects.equals(piece, board[row + 2][column + 1]) &&
                     Objects.equals(piece, board[row + 3][column + 2]) &&
                     Objects.equals(piece, board[row][column + 3]))) {
                    return MoveResult.WIN;
                }
            }
        }

        // till now, check if game is draw?
        if (stackList.get(0).size() + stackList.get(1).size() + stackList.get(2).size() + stackList.get(3).size() +
                stackList.get(4).size() + stackList.get(5).size() + stackList.get(6).size() == 42) {
            return MoveResult.DRAW;
        }

        // if game is not judged, continue
        return MoveResult.NOT_WIN;
    }
}
