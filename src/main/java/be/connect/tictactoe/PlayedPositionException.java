package be.connect.tictactoe;

public class PlayedPositionException extends RuntimeException {
    public PlayedPositionException() {
        super();
    }

    public PlayedPositionException(String message) {
        super(message);
    }

    public PlayedPositionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayedPositionException(Throwable cause) {
        super(cause);
    }
}
