public class State {

    private Board board;

    public State(Board board) {
        this.board = board;
    }


    boolean isGoal() {
        Tile[][] tiles = board.getBoard();
        int rowNum = tiles.length;
        int colNum = tiles[0].length;
        if (tiles[colNum - 1][rowNum - 1].getTileNumber() != 0) return false;

        int currentNum = 1;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                Tile tile = tiles[j][i];
                int tileNum = tile.getTileNumber();
                if (i == rowNum - 1 && j == colNum - 1) continue;
                else if (tileNum == currentNum) {
                    currentNum++;
                }
            }
        }
        return true;
    }

    public Action[] actions() {
        swtich(board.getEmptyTilePlacement()){
            case Placement.TOP_LEFT:
                return new Action[]{}
        }
    }

    public State result(Action action) {
        State result = new State(board);
        Board resultBoard = result.board;
        resultBoard.swapWithEmpty(action.dir);
        return result; //TODO: fix stuff with action (we wrote the opposite way)
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
