package ui;

import org.jetbrains.annotations.NotNull;
import ui.tiles.Tile;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

public class GameMap {

    @NotNull private static final Logger LOGGER = Logger.getLogger(GameMap.class.getName());

    @NotNull private final SpriteSheet spriteSheet;
    @NotNull private final Map<Point, List<Tile>> mapTiles = new HashMap<>();

    public GameMap(@NotNull final SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public void registerTile(@NotNull final Tile tile, @NotNull final Point coords) {
        final List<Tile> tilesForCoords = getMapTiles().getOrDefault(coords, new ArrayList<>());
        tilesForCoords.add(tile);
        getMapTiles().put(coords,  tilesForCoords);
    }

    public void render(@NotNull final Renderer renderer, final int xScale, final int yScale) {
        final int tileWidth = getSpriteSheet().getSpriteWidth() * xScale;
        final int tileHeight = getSpriteSheet().getSpriteHeight() * yScale;
        final Camera camera = renderer.getCamera();

        for (Map.Entry<Point, List<Tile>> entry : getMapTiles().entrySet()) {
            final Point coords = entry.getKey();
            final List<Tile> tiles = entry.getValue();

            tiles.forEach(tile -> tile.render(renderer, getSpriteSheet(), ((int) coords.getX()) * tileWidth, ((int) coords.getY()) * tileHeight, xScale, yScale));
        }
    }

    @NotNull
    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    @NotNull
    public Map<Point, List<Tile>> getMapTiles() {
        return mapTiles;
    }
}
