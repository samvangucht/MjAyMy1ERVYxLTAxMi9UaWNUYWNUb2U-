package be.connect.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GameTest {
    Game game;

    @BeforeEach
    void setup() {
        game = new Game();
        game.startGame();
    }

    //    X always goes first.
    @Test
    void startGame_XAlwaysGoesFirst() {
        assertTrue(game.getState() == GameState.STARTED);
        assertTrue(game.getActivePlayer() == Player.X);
    }

    //    Players cannot play on a played position.
    @Test
    void pickNextPosition_OnPlayedPosition_ThrowsPlayedPositionException() {
        game = new Game(new Field[][]{
                [[1],[2],[0]],
                [[0],[0],[0]],
                [[0],[0],[0]]
        });

        assertTrue(game.getState() == GameState.STARTED);
        assertTrue(game.getActivePlayer() == Player.X);
        assertThrows(PlayedPositionException.class, () -> game.pickNextPosition(1));
    }

    //    Players alternate placing X’s and O’s on the board until either:
    //    One player has three in a row, horizontally, vertically or diagonally
    @Test
    void pickNextPosition_AlternatePlayers() {
        game.pickNextPosition(1);
        assertTrue(game.getActivePlayer() == Player.O);
        game.pickNextPosition(2);
        assertTrue(game.getActivePlayer() == Player.X);
    }


    @Test
    void pickNextPosition_ThreeInRow_Horizontal() {
        game = new Game(new Field[][]{
                [[1],[1],[1]],
                [[2],[2],[0]],
                [[0],[0],[0]]
        });

        assertTrue(game.getState() == GameState.FINISHED);
        assertTrue(game.getWinner() == Player.X);
    }

    @Test
    void pickNextPosition_ThreeInRow_Vertical() {
        game = new Game(new Field[][]{
                [[1],[2],[2]],
                [[1],[0],[0]],
                [[1],[0],[0]]
        });

        assertTrue(game.getState() == GameState.FINISHED);
        assertTrue(game.getWinner() == Player.X);
    }

    @Test
    void pickNextPosition_ThreeInRow_Diagonal() {
        game = new Game(new Field[][]{
                [[1],[2],[2]],
                [[0],[1],[0]],
                [[0],[0],[1]]
        });

        assertTrue(game.getState() == GameState.FINISHED);
        assertTrue(game.getWinner() == Player.X);
    }

    //    All nine squares are filled
    //    If all nine squares are filled and neither player has three in a row, the game is a draw.
    @Test
    void pickNextPosition_AllNineSquaresFilled() {
        game = new Game(new Field[][]{
                [[1],[2],[1]],
                [[2],[1],[2]],
                [[2],[1],[2]]
        });

        assertTrue(game.getState() == GameState.FINISHED);
        assertTrue(game.getWinner() == Player.NONE);
    }

}
