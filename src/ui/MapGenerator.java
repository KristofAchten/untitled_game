package ui;

import org.jetbrains.annotations.NotNull;
import ui.tiles.BarrelTile;
import ui.tiles.DirtTile;
import ui.tiles.GrassTile;

import java.awt.*;
import java.util.Random;

public class MapGenerator {
    @NotNull
    public static GameMap generate(@NotNull final SpriteSheet spriteSheet) {
        final GameMap gameMap = new GameMap(spriteSheet);
        final Random random = new Random();

        for (int y = -50; y < 50; y++) {
            for (int x = -50; x < 50; x++) {
                gameMap.registerTile(
                        random.nextDouble() < 0.75 ? new GrassTile() : new DirtTile(),
                        new Point(x, y)
                );
                if (random.nextDouble() < 0.002) {
                    gameMap.registerTile(new BarrelTile(), new Point(x, y));
                }
            }
        }
        return gameMap;
    }
}
