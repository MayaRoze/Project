import java.util.Arrays;

public class Board {
    private Tile[][] board;
    private int[] emptyTileIndex;

    public Board(String boardStr) {
        boolean foundEmpty = false;
        String[] rows = boardStr.split("\\|");
        int colNum = rows[0].split(" ").length;
        int rowNum = rows.length;
        board = new Tile[rowNum][colNum];

        for (int i = 0; i < rowNum; i++) {
            String[] components = rows[i].split(" ");
            for (int j = 0; j < colNum; j++) {
                if (!foundEmpty && components[j] == "_") {
                    foundEmpty = true;
                    board[i][j] = new Tile(0);
                    emptyTileIndex = new int[]{i, j};
                } else {
                    board[i][j] = new Tile(Integer.parseInt(components[j]));
                }
            }
        }
    }

    public Tile[][] getBoard() {
        return board;
    }

    public int[] getEmptyTileIndex() {
        return emptyTileIndex;
    }

    public Tile getTile(int row, int col) {
        return board[row][col];
    }

    public Placement getEmptyTilePlacement() {
        if (emptyTileIndex[0] == 0 && emptyTileIndex[1] == 0) return Placement.TOP_LEFT;
        if (emptyTileIndex[0] == 0 && emptyTileIndex[1] == board[0].length - 1) return Placement.TOP_RIGHT;
        if (emptyTileIndex[0] == board.length - 1 && emptyTileIndex[1] == 0) return Placement.BOTTOM_LEFT;
        if (emptyTileIndex[0] == board.length - 1 && emptyTileIndex[1] == board[0].length - 1)
            return Placement.BOTTOM_RIGHT;
        if (emptyTileIndex[0] == 0) return Placement.TOP_WALL;
        if (emptyTileIndex[0] == board.length - 1) return Placement.BOTTOM_WALL;
        if (emptyTileIndex[1] == 0) return Placement.LEFT_WALL;
        if (emptyTileIndex[1] == board[0].length - 1) return Placement.RIGHT_WALL;
        return Placement.INTERNAL;
    }

    public void swapWithEmpty(Direction dir) {
        Tile temp;
        switch (dir) {
            case UP:
                temp = board[emptyTileIndex[0] + 1][emptyTileIndex[1]];
                board[emptyTileIndex[0] + 1][emptyTileIndex[1]] = new Tile(0);
                board[emptyTileIndex[0]][emptyTileIndex[1]] = temp;
            case DOWN:
                temp = board[emptyTileIndex[0] - 1][emptyTileIndex[1]];
                board[emptyTileIndex[0] - 1][emptyTileIndex[1]] = new Tile(0);
                board[emptyTileIndex[0]][emptyTileIndex[1]] = temp;
            case LEFT:
                temp = board[emptyTileIndex[0]][emptyTileIndex[1] - 1];
                board[emptyTileIndex[0]][emptyTileIndex[1] - 1] = new Tile(0);
                board[emptyTileIndex[0]][emptyTileIndex[1]] = temp;
            case RIGHT:
                temp = board[emptyTileIndex[0]][emptyTileIndex[1] + 1];
                board[emptyTileIndex[0]][emptyTileIndex[1] + 1] = new Tile(0);
                board[emptyTileIndex[0]][emptyTileIndex[1]] = temp;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}
