package be.connect.tictactoe.ui;

import be.connect.tictactoe.domain.Game;
import be.connect.tictactoe.domain.GameState;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("play")
public class GameRunner implements CommandLineRunner {
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
