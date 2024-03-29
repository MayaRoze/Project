public class State {

    private Board board;

    public State(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    boolean isGoal() {
        Tile[][] tiles = board.getBoard();
        int rowNum = tiles.length;
        int colNum = tiles[0].length;
        if (rowNum == 1 & colNum == 1) return true;
        if (tiles[rowNum - 1][colNum - 1].getTileNumber() != 0) return false;

        int currentNum = 1;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                Tile tile = tiles[i][j];
                int tileNum = tile.getTileNumber();
                if (i == rowNum - 1 && j == colNum - 1) continue;
                else if (tileNum == currentNum) currentNum++;
                else return false;
            }
        }
        return true;
    }

    public Action[] actions() {
        int[] emptyTileIndex = board.getEmptyTileIndex();
        int x = emptyTileIndex[0];
        int y = emptyTileIndex[1];
        switch (board.getEmptyTilePlacement()) {
            case ONE_ON_ONE:
                return new Action[]{};
            case TOP_ONE_COL:
                return new Action[]{new Action(board.getTile(x + 1, y), Direction.UP)};
            case BOTTOM_ONE_COL:
                return new Action[]{new Action(board.getTile(x - 1, y), Direction.DOWN)};
            case INTERNAL_ONE_COL:
                return new Action[]{new Action(board.getTile(x + 1, y), Direction.UP),
                        new Action(board.getTile(x - 1, y), Direction.DOWN)};
            case LEFT_ONE_ROW:
                return new Action[]{new Action(board.getTile(x, y + 1), Direction.LEFT)};
            case RIGHT_ONE_ROW:
                return new Action[]{new Action(board.getTile(x, y - 1), Direction.RIGHT)};
            case INTERNAL_ONE_ROW:
                return new Action[]{new Action(board.getTile(x, y - 1), Direction.RIGHT),
                        new Action(board.getTile(x, y + 1), Direction.LEFT)};
            case TOP_LEFT:
                return new Action[]{new Action(board.getTile(x + 1, y), Direction.UP),
                        new Action(board.getTile(x, y + 1), Direction.LEFT)};
            case BOTTOM_LEFT:
                return new Action[]{new Action(board.getTile(x - 1, y), Direction.DOWN),
                        new Action(board.getTile(x, y + 1), Direction.LEFT)};
            case TOP_RIGHT:
                return new Action[]{new Action(board.getTile(x + 1, y), Direction.UP),
                        new Action(board.getTile(x, y - 1), Direction.RIGHT)};
            case BOTTOM_RIGHT:
                return new Action[]{new Action(board.getTile(x - 1, y), Direction.DOWN),
                        new Action(board.getTile(x, y - 1), Direction.RIGHT)};
            case LEFT_WALL:
                return new Action[]{new Action(board.getTile(x + 1, y), Direction.UP),
                        new Action(board.getTile(x - 1, y), Direction.DOWN),
                        new Action(board.getTile(x, y + 1), Direction.LEFT)};
            case TOP_WALL:
                return new Action[]{new Action(board.getTile(x + 1, y), Direction.UP),
                        new Action(board.getTile(x, y - 1), Direction.RIGHT),
                        new Action(board.getTile(x, y + 1), Direction.LEFT)};
            case RIGHT_WALL:
                return new Action[]{new Action(board.getTile(x + 1, y), Direction.UP),
                        new Action(board.getTile(x - 1, y), Direction.DOWN),
                        new Action(board.getTile(x, y - 1), Direction.RIGHT)};
            case BOTTOM_WALL:
                return new Action[]{new Action(board.getTile(x - 1, y), Direction.DOWN),
                        new Action(board.getTile(x, y - 1), Direction.RIGHT),
                        new Action(board.getTile(x, y + 1), Direction.LEFT)};
            case INTERNAL:
                return new Action[]{new Action(board.getTile(x + 1, y), Direction.UP),
                        new Action(board.getTile(x - 1, y), Direction.DOWN),
                        new Action(board.getTile(x, y - 1), Direction.RIGHT),
                        new Action(board.getTile(x, y + 1), Direction.LEFT)};
        }
        return null;
    }

    public State result(Action action) {
        State result = new State(this.board.getBoardCopy());
        Board resultBoard = result.board;
        resultBoard.swapWithEmpty(action.dir);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
