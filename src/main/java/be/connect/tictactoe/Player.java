package be.connect.tictactoe;

public enum Player {
    NONE(0),
    X(1),
    O(2);

    private int value;

    Player(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
