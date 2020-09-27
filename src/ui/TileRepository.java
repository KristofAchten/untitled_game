package ui;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TileRepository {
    @NotNull private static final Logger LOGGER = Logger.getLogger(TileRepository.class.getName());
    @NotNull private static final String DELIMITER = ",";
    @NotNull private static final String COMMENT_SYNTAX = "--";
    @NotNull private final Map<String, TileDep> tilesMap = new HashMap<>();
    @NotNull private final SpriteSheet spriteSheet;

    public TileRepository(@NotNull final String tileDefinitionPath,
                          @NotNull final SpriteSheet spriteSheet) {

        this.spriteSheet = spriteSheet;
        try {
            parseTileDefinition(tileDefinitionPath);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.WARNING, "Could not load Spritesheet at path " + e.getMessage());
        }
    }

    private void parseTileDefinition(@NotNull final String tileDefinitionPath) throws FileNotFoundException {
        final File definition = new File(tileDefinitionPath);
        final Scanner fileScanner = new Scanner(definition);

        while (fileScanner.hasNextLine()) {
            final String line = fileScanner.nextLine();
            if (line.startsWith(COMMENT_SYNTAX) || line.trim().isEmpty()) {
                continue;
            }

            final String[] parts = line.split(DELIMITER);
            if (parts.length != 3) {
                LOGGER.log(Level.WARNING, "Invalid tiles definition: could not define tiles");
            }

            final String identifier = parts[0];
            final int spritePosX = Integer.parseInt(parts[1]);
            final int spritePosY = Integer.parseInt(parts[2]);

            getTilesMap().put(identifier, new TileDep(identifier, getSpriteSheet().getSprite(spritePosX, spritePosY)));
        }
    }

    public void renderTile(@NotNull final String identifier, @NotNull final Renderer renderer, final int xPos,
                           final int yPos, final int xScale, final int yScale) {
        renderer.renderSprite(getTilesMap().get(identifier).getSprite(), xPos, yPos, xScale, yScale);
    }

    @Nullable
    public TileDep getTile(@NotNull final String id) {
        return getTilesMap().get(id);
    }

    @NotNull
    public Map<String, TileDep> getTilesMap() {
        return tilesMap;
    }

    @NotNull
    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

}
