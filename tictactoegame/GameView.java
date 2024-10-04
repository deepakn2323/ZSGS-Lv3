package tictactoegame;

import java.util.Arrays;
import java.util.Scanner;

public class GameView {
    private Scanner scanner;
    private TicTacToe ticTacToe;
    GameView(){
        ticTacToe = new TicTacToe(createBoard());
        this.scanner = new Scanner(System.in);
    }
    public static void main(String[] args) {
        new GameView().startGame();
    }
    private void startGame(){
        char player;
        for(int i=0;i<(3*3); i++){
            if(i%2 == 0) player = 'X';
            else player = 'O';
            ticTacToe.displayBoard();
            System.out.println("Player("+player+"), enter your move (row and column)");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            while(notValid(row,col)){
                System.out.println("Enter valid row and col");
                row = scanner.nextInt();
                col = scanner.nextInt();
            }
            if(ticTacToe.placeInBoard(row, col, player)){
                if(ticTacToe.checkWin(player, row, col)){
                    ticTacToe.displayBoard();
                    System.out.println("Player ("+player+") Wins!");
                    break;
                }else if(ticTacToe.draw()){
                    ticTacToe.displayBoard();
                    System.out.println("Match draw");
                    break;
                }
            }else{
                System.out.println("The cell is already occupied");
                i--;
            }


        }
    }

    private boolean notValid(int row, int col) {
        return row<0 || col<0 || row>=3 || col>=3;
    }

    private char[][] createBoard() {
        char[][] board = new char[3][3];
        for(char[] r : board){
            Arrays.fill(r, '_');
        }
        return board;
    }
}
