package fun.challenge;

import java.util.List;
import java.util.Stack;

/**
 * Connect Four board dimension is 6x7 (row x column)
 */
public class Board {
    private List<Stack<Character>> stackList;

    // for print board with stack of moves
    public void printBoard(List<Stack<Character>> stackList) {
        for (var row : stackList) {
            for (var stack : row) {
            }
        }
    }

    // for reset board and moves stack to init state
    public void clearBoard() {
        // clear objects of Board
    }

    // for push the move
    public void move(Integer columnNo) {
        // push move to the stack of specific column
    }

    public void isWin() {
        // calculate the win
        // horizontal
        // vertical
        // diagonal
        // - diagonal top-left to bottom-right
        // - diagonal top-right to bottom-left
    }
}
