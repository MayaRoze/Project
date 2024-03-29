public class Tile {
    final int tileNumber;

    public Tile(int tileNumber) {
        this.tileNumber = tileNumber;
    }

    public int getTileNumber() {
        return tileNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return tileNumber == tile.tileNumber;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(tileNumber);
    }
}