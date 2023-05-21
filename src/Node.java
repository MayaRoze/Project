public class Node {
    private State state;
    private Node parent;
    private Action action;

    public State getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public Action getAction() {
        return action;
    }

    public Node(State state, Node parent, Action action) {
        this.state = state;
        this.parent = parent;
        this.action = action;
    }

    public Node[] expand() {
        Action[] actions = state.actions();
        Node[] expansion = new Node[actions.length];
        for (int i = 0; i < actions.length; i++) {
            State newState = state.result(actions[i]);
            expansion[i] = new Node(newState, this, actions[i]);
        }
        return expansion;
    }

    public int heuristicValue() {
        return manhattanDistance() + linearConflict();
    }

    private int getGoalRow(int tileNumber, int colSize) {
        return tileNumber / colSize;
    }

    private int getGoalCol(int tileNumber, int colSize) {
        int goalCol = tileNumber % colSize;
        if (goalCol == 0) goalCol = colSize - 1;
        else goalCol -= 1;
        return goalCol;
    }

    private int manhattanDistance() {
        int manhattanDistance = 0;
        Board board = state.getBoard();
        int colSize = board.getColSize();
        int rowSize = board.getRowSize();

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                Tile tile = board.getTile(i, j);
                if (tile.getTileNumber() == 0) continue;
                int goalRow = getGoalRow(tile.getTileNumber(), colSize);
                int goalCol = getGoalCol(tile.getTileNumber(), colSize);
                manhattanDistance += Math.abs(i - goalRow) + Math.abs(j - goalCol);
            }
        }
        return manhattanDistance;
    }

    private int linearConflict() {
        int linearConflict = 0;
        Board board = state.getBoard();
        int colSize = board.getColSize();
        int rowSize = board.getRowSize();

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                Tile tile = board.getTile(i, j);
                if (tile.getTileNumber() == 0) continue;
                int goalRow = getGoalRow(tile.getTileNumber(), colSize);
                int goalCol = getGoalCol(tile.getTileNumber(), colSize);

                if (goalRow == i) {
                    for (int k = j + 1; k < colSize; k++) {
                        Tile otherTile = board.getTile(i, k);
                        if (otherTile.getTileNumber() == 0) continue;
                        int otherGoalRow = getGoalRow(otherTile.getTileNumber(), colSize);
                        int otherGoalCol = getGoalCol(otherTile.getTileNumber(), colSize);
                        if (otherGoalRow == i && goalCol > otherGoalCol) linearConflict += 2;
                    }
                }

                if (goalCol == j) {
                    for (int k = i + 1; k < rowSize; k++) {
                        Tile otherTile = board.getTile(k, j);
                        if (otherTile.getTileNumber() == 0) continue;
                        int otherGoalRow = getGoalRow(otherTile.getTileNumber(), colSize);
                        int otherGoalCol = getGoalCol(otherTile.getTileNumber(), colSize);
                        if (otherGoalCol == j && goalRow > otherGoalRow) linearConflict += 2;
                    }
                }
            }
        }
        return linearConflict;
    }
}
