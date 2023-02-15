package be.connect.tictactoe;

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

    public Player getWinner() {
        if (state == GameState.FINISHED) {
            return winner;
        } else {
            return Player.NONE;
        }
    }

    public void start() {
        state = GameState.STARTED;
    }

    public void pickNextPosition(int position) throws PlayedPositionException {
        if (gameBoard.getFieldValue(position) != 0) {
            throw new PlayedPositionException();
        }

        gameBoard.setFieldValue(position, activePlayer.getValue());
        checkForWinner();
        switchPlayer();
    }

    private void initializeGame() {
        this.activePlayer = this.gameBoard.getPlayedPositionCount() % 2 == 0 ? Player.X : Player.O;
        this.state = GameState.CREATED;
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
            state = GameState.FINISHED;
            winner = activePlayer;
        }

        if (gameBoard.isFull()) {
            state = GameState.FINISHED;
            winner = Player.NONE;
        }
    }
}
