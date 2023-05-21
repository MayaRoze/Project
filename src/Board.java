import java.util.Arrays;

public class Board {
    private Tile[][] board;
    private int[] emptyTileIndex;

    public Board(Tile[][] board, int[] emptyTileIndex) {
        this.board = board;
        this.emptyTileIndex = emptyTileIndex;
    }

    public Board(String boardStr) {
        boolean foundEmpty = false;
        String[] rows = boardStr.split("\\|");
        int colNum = rows[0].split(" ").length;
        int rowNum = rows.length;
        board = new Tile[rowNum][colNum];

        for (int i = 0; i < rowNum; i++) {
            String[] components = rows[i].split(" ");
            for (int j = 0; j < colNum; j++) {
                if (!foundEmpty && components[j].equals("_")) {
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

    public int getRowSize() {
        return board.length;
    }

    public int getColSize() {
        return board[0].length;
    }

    public Board getBoardCopy() {
        Tile[][] boardCopy = new Tile[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }
        return new Board(boardCopy, new int[]{emptyTileIndex[0], emptyTileIndex[1]});
    }

    public int[] getEmptyTileIndex() {
        return emptyTileIndex;
    }

    public Tile getTile(int row, int col) {
        //if (row >= board.length) return board[row - 1][col];
        //if (col >= board[0].length) return board[row][col - 1];
        //if (row < 0) return board[row + 1][col];
        //if (col < 0) return board[row][col + 1];
        return board[row][col];
    }

    public void setTile(int row, int col, Tile tile) {
        //if (row >= board.length) board[row - 1][col] = tile;
        //else if (col >= board[0].length) board[row][col - 1] = tile;
        //else if (row < 0) board[row + 1][col] = tile;
        //else if (col < 0) board[row][col + 1] = tile;
        board[row][col] = tile; //add else if uncomment
        if (tile.getTileNumber() == 0) this.emptyTileIndex = new int[]{row, col};
    }

    public Placement getEmptyTilePlacement() {
        if (board.length == 1 && board[0].length == 1) return Placement.ONE_ON_ONE;
        if (board.length == 1) {
            if (emptyTileIndex[1] == 0) return Placement.LEFT_ONE_ROW;
            if (emptyTileIndex[1] == board[0].length - 1) return Placement.RIGHT_ONE_ROW;
            return Placement.INTERNAL_ONE_ROW;
        }
        if (board[0].length == 1) {
            if (emptyTileIndex[0] == 0) return Placement.BOTTOM_ONE_COL;
            if (emptyTileIndex[0] == board.length - 1) return Placement.TOP_ONE_COL;
            return Placement.INTERNAL_ONE_COL;
        }

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
        int x = emptyTileIndex[0];
        int y = emptyTileIndex[1];
        switch (dir) {
            case UP:
                temp = getTile(x + 1, y);
                setTile(x + 1, y, new Tile(0));
                setTile(x, y, temp);
                break;
            case DOWN:
                temp = getTile(x - 1, y);
                setTile(x - 1, y, new Tile(0));
                setTile(x, y, temp);
                break;
            case LEFT:
                temp = getTile(x, y + 1);
                setTile(x, y + 1, new Tile(0));
                setTile(x, y, temp);
                break;
            case RIGHT:
                temp = getTile(x, y - 1);
                setTile(x, y - 1, new Tile(0));
                setTile(x, y, temp);
                break;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(this.board, board.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
}
