package ui;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameMap {

    @NotNull private static final Logger LOGGER = Logger.getLogger(GameMap.class.getName());
    @NotNull private static final String DELIMITER = ",";
    @NotNull private static final String COMMENT_SYNTAX = "--";

    @NotNull private final TileRepository repository;
    @Nullable private Tile backgroundTile = null;
    @NotNull private final Map<Point, String> mapTiles = new HashMap<>();

    public GameMap(@NotNull final String mapDefinitionPath, @NotNull final TileRepository repository) {
        this.repository = repository;
        try {
            parseMap(mapDefinitionPath);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.WARNING, "Could not load Map at path " + e.getMessage());
        }
    }

    private void parseMap(@NotNull final String mapDefinitionPath) throws FileNotFoundException {
        final File definition = new File(mapDefinitionPath);
        final Scanner fileScanner = new Scanner(definition);

        while (fileScanner.hasNextLine()) {
            final String line = fileScanner.nextLine();
            if (line.startsWith(COMMENT_SYNTAX) || line.isEmpty()) {
                continue;
            }

            final String[] parts = line.split(DELIMITER);
            if (parts.length == 2 && "background".equals(parts[0])) {
                backgroundTile = getRepository().getTile(parts[1]);
                continue;
            }

            if (parts.length != 3) {
                LOGGER.log(Level.WARNING, "Invalid map definition: could not load map");
            }

            final String identifier = parts[0];
            final int spritePosX = Integer.parseInt(parts[1]);
            final int spritePosY = Integer.parseInt(parts[2]);

            getMapTiles().put(new Point(spritePosX, spritePosY), identifier);
        }
    }

    public void render(@NotNull final Renderer renderer, final int xScale, final int yScale) {
        final int tileWidth = getRepository().getSpriteSheet().getSpriteWidth() * xScale;
        final int tileHeight = getRepository().getSpriteSheet().getSpriteHeight() * yScale;
        final Camera camera = renderer.getCamera();

        // Filling the background = filling the view visible through the camera.
        if (getBackgroundTile() != null) {
            for (int y = camera.getyPos(); y < camera.getyPos() + camera.getHeight(); y += tileHeight) {
                for (int x = camera.getxPos(); x < camera.getxPos() + camera.getWidth(); x += tileWidth) {
                    getRepository().renderTile(getBackgroundTile().getIdentifier(), renderer, x, y, xScale, yScale);
                }
            }
        }

        for (Map.Entry<Point, String> entry : getMapTiles().entrySet()) {
            final String identifier = entry.getValue();
            final Point coords = entry.getKey();

            getRepository().renderTile(identifier, renderer, ((int) coords.getX()) * tileWidth, ((int)coords.getY()) * tileHeight, xScale, yScale);
        }
    }

    @NotNull
    public TileRepository getRepository() {
        return repository;
    }

    @Nullable
    public Tile getBackgroundTile() {
        return backgroundTile;
    }

    public void setBackgroundTile(@NotNull final Tile backgroundTile) {
        this.backgroundTile = backgroundTile;
    }

    @NotNull
    public Map<Point, String> getMapTiles() {
        return mapTiles;
    }
}
