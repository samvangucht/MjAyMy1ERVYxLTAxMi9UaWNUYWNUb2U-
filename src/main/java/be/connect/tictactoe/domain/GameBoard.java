package be.connect.tictactoe.domain;

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

    public int getFieldValue(int position) {
        int[] coordinates = calculateCoordinates(position);
        return board[coordinates[0]][coordinates[1]];
    }

    public int getPlayedPositionCount() {
        int playedPositionCount = 0;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (board[row][column] != 0) {
                    playedPositionCount++;
                }
            }
        }
        return playedPositionCount;
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

    public boolean isThreeInRow() {
        // HORIZONTAL
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != 0
                    && board[row][0] == board[row][1]
                    && board[row][1] == board[row][2]) {
                return true;
            }
        }

        // VERTICAL
        for (int column = 0; column < 3; column++) {
            if (board[0][column] != 0
                    && board[0][column] == board[1][column]
                    && board[1][column] == board[2][column]
            ) {
                return true;
            }
        }

        //DIAGONAL: top left to bottom right
        if (board[0][0] != 0
                && board[0][0] == board[1][1]
                && board[1][1] == board[2][2]) {
            return true;
        }

        //DIAGONAL: bottom left to top right
        if (board[2][0] != 0
                && board[2][0] == board[1][1]
                && board[1][1] == board[0][2]) {
            return true;
        }

        return false;
    }

    public boolean isFull() {
        return getPlayedPositionCount() == 9;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    sb.append(" ");
                } else if (board[row][col] == 1) {
                    sb.append("X");
                } else if (board[row][col] == 2) {
                    sb.append("O");
                }
                if (col < 2) {
                    sb.append("|");
                }
            }
            sb.append("\n");
            if (row < 2) {
                sb.append("-+-+-\n");
            }
        }
        return sb.toString();
    }
}
