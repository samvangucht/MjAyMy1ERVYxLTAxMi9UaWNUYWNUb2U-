package be.connect.tictactoe;

import be.connect.tictactoe.domain.Game;
import be.connect.tictactoe.domain.GameState;
import be.connect.tictactoe.ui.UiController;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicTacToeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TicTacToeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Game game = new Game();
        UiController uiController = new UiController(game);

        uiController.startGame();

        while (game.getState() != GameState.FINISHED) {
            uiController.printBoard();
            uiController.play();
        }

        uiController.finishGame();
    }
}
