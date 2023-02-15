package be.connect.tictactoe;

public class GameBoard {
    int[][] board;

    public GameBoard() {
        this(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        });
    }
    public GameBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getFieldValue(int position) {
        int[] coordinates = calculateCoordinates(position);
        return board[coordinates[0]][coordinates[1]];
    }

    public void setFieldValue(int position, int value) {
        int[] coordinates = calculateCoordinates(position);
        board[coordinates[0]][coordinates[1]] = value;
    }

    private int[] calculateCoordinates(int position) {
        int row = (position - 1) / 3;
        int column = (position - 1) % 3;
        return new int[]{row, column};
    }
}
