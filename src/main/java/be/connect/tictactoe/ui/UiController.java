package be.connect.tictactoe.ui;

import be.connect.tictactoe.domain.Game;
import be.connect.tictactoe.exception.PlayedPositionException;

import java.util.Scanner;

public class UiController {
    private Game game;
    private Scanner scanner = new Scanner(System.in);

    public UiController(Game game) {
        this.game = game;
    }

    public void startGame() {
        PrintUtil.print("""
                Welcome to Tic Tac Toe!
                Player 1 is X and Player 2 is O.
                
                The board is numbered as follows:
                1 | 2 | 3
                ---------
                4 | 5 | 6
                ---------
                7 | 8 | 9
                
                Let's start!
                """);

        game.start();
    }

    public void printBoard(){
        PrintUtil.print(game.getGameBoard().toString());
    }

    public void play() {
        PrintUtil.printf("Player %s, please pick a position: %n", game.getActivePlayer());
        int position = InputUtil.readInt(scanner);
        try {
            game.pickNextPosition(position);
        } catch (PlayedPositionException e) {
            PrintUtil.print("Position already played. Please pick another position.\n");
            play();
        }
    }

    public void finishGame() {
        PrintUtil.print(game.getGameBoard().toString());
        PrintUtil.printf("Player %s has won the game!\n", game.getWinner());
        PrintUtil.print("Thanks for playing!");
        scanner.close();
    }
}
