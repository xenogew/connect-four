package fun.challenge;

import java.util.Scanner;

/**
 * ConnectFour main class is starting point
 * and retrieve input from users (1 vs 1)
 */
public class ConnectFourMain {
    public static void main(String[] args) {
        // Console input Scanner
        var scanner = new Scanner(System.in);

        // Read input from user
        var move = scanner.nextLine();
        System.out.println("Selected column: " + move);
    }
}
