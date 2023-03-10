package be.connect.tictactoe.domain;

import be.connect.tictactoe.exception.PlayedPositionException;

public class Game {
    private Player activePlayer;
    private Player winner;
    private GameState state;
    private GameBoard gameBoard;

    public Game() {
        this(new GameBoard());
    }

    public Game(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        initializeGame();
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Player getWinner() {
        if (state == GameState.FINISHED) {
            return winner;
        } else {
            return Player.NONE;
        }
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void start() {
        setState(GameState.STARTED);
    }

    public void pickNextPosition(int position) throws PlayedPositionException {
        if (gameBoard.getFieldValue(position) != 0) {
            throw new PlayedPositionException();
        }

        gameBoard.setFieldValue(position, activePlayer.getValue());
        checkForWinner();
        if (state != GameState.FINISHED) {
            switchPlayer();
        }
    }

    private void initializeGame() {
        this.activePlayer = this.gameBoard.getPlayedPositionCount() % 2 == 0 ? Player.X : Player.O;
        setState(GameState.CREATED);
    }

    private void switchPlayer() {
        if (activePlayer == Player.X) {
            activePlayer = Player.O;
        } else {
            activePlayer = Player.X;
        }
    }

    private void checkForWinner() {
        if (gameBoard.isThreeInRow()) {
            winner = activePlayer;
            setState(GameState.FINISHED);
        } else if (gameBoard.isFull()) {
            winner = Player.NONE;
            setState(GameState.FINISHED);
        }
    }
}
