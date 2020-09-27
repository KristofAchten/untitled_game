package ui.tiles;

public class BarrelTile extends Tile {
    public BarrelTile() {
        super(4, 2);
    }

    @Override
    public boolean canPass() {
        return false;
    }
}
