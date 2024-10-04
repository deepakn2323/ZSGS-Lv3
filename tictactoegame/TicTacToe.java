package tictactoegame;

public class TicTacToe {
    private char[][] board;

    TicTacToe(char[][] board) {
        this.board = board;
    }

    void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isValid(int row, int col) {
        return board[row][col] == '_';
    }

    public boolean placeInBoard(int row, int col, char player) {
        if (isValid(row, col)) {
            board[row][col] = player;
            return true;
        }
        return false;
    }

    boolean draw() {
        for (char[] r : board) {
            for (char ch : r) {
                if (ch == '_') return false;
            }
        }
        return true;
    }

    boolean checkWin(char player, int r, int c) {
        boolean won = true;
        for (int i = 0; i < 3; i++) {
            if (board[r][i] != player) {
                won = false;
                break;
            }
        }
        if (won) return true;

        won = true;
        for (int i = 0; i < 3; i++) {
            if (board[i][c] != player) {
                won = false;
                break;
            }
        }
        if (won) return true;

        if (r == c) {
            won = true;
            for (int i = 0; i < 3; i++) {
                if (board[i][i] != player) {
                    won = false;
                    break;
                }
            }
            if (won) return true;
        }

        if (r + c == 2) {
            won = true;
            for (int i = 0, j = 2; i < 3; i++, j--) {
                if (board[i][j] != player) {
                    won = false;
                    break;
                }
            }
            return won;
        }

        return false;
    }
}
