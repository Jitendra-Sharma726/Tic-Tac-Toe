import java.util.*;

public class TicTacToeAI {

    static char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("🎮 Tic Tac Toe (You vs AI)");
        printBoard();

        while (true) {
            // Player move
            System.out.print("Enter position (1-9): ");
            int move = sc.nextInt() - 1;

            if (move < 0 || move > 8 || board[move] != ' ') {
                System.out.println("❌ Invalid move!");
                continue;
            }

            board[move] = 'X';

            if (checkWin('X')) {
                printBoard();
                System.out.println("🎉 You Win!");
                break;
            }

            if (isDraw()) {
                printBoard();
                System.out.println("🤝 Draw!");
                break;
            }

            // AI move
            int aiMove = getAIMove();
            board[aiMove] = 'O';
            System.out.println("🤖 AI chose: " + (aiMove + 1));

            if (checkWin('O')) {
                printBoard();
                System.out.println("💀 AI Wins!");
                break;
            }

            if (isDraw()) {
                printBoard();
                System.out.println("🤝 Draw!");
                break;
            }

            printBoard();
        }

        sc.close();
    }

    static void printBoard() {
        System.out.println("\n");
        for (int i = 0; i < 9; i += 3) {
            System.out.println(" " + board[i] + " | " + board[i+1] + " | " + board[i+2]);
            if (i < 6) System.out.println("---+---+---");
        }
        System.out.println("\n");
    }

    static boolean checkWin(char player) {
        int[][] winPatterns = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };

        for (int[] pattern : winPatterns) {
            if (board[pattern[0]] == player &&
                board[pattern[1]] == player &&
                board[pattern[2]] == player) {
                return true;
            }
        }
        return false;
    }

    static boolean isDraw() {
        for (char c : board) {
            if (c == ' ') return false;
        }
        return true;
    }

    static int getAIMove() {
        Random rand = new Random();
        int move;
        do {
            move = rand.nextInt(9);
        } while (board[move] != ' ');
        return move;
    }
}
