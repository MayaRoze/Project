public class Action {
    Tile tile;
    Direction dir;

    public Action(Tile tile, Direction dir) {
        this.tile = tile;
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "Move " + tile.getTileNumber() + " " + dir.name().toLowerCase();
    }
}
