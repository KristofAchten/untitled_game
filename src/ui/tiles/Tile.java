package ui.tiles;

import main.Config;
import org.jetbrains.annotations.NotNull;
import ui.Camera;
import ui.Renderer;
import ui.SpriteSheet;

public class Tile {

    private final int spriteX;
    private final int spriteY;

    public Tile(final int spriteX, final int spriteY) {
        this.spriteX = spriteX;
        this.spriteY = spriteY;
    }

    public int getSpriteX() {
        return spriteX;
    }

    public int getSpriteY() {
        return spriteY;
    }

    public void render(@NotNull final Renderer renderer, @NotNull final SpriteSheet spriteSheet, final int xPos, final int yPos, final int xScale, final int yScale) {
        final Camera camera = renderer.getCamera();

        final boolean shouldRender = camera.coordinatesInside(xPos, yPos)
                || camera.coordinatesInside(xPos, yPos + (spriteSheet.getSpriteHeight() * yScale))
                || camera.coordinatesInside(xPos + (spriteSheet.getSpriteWidth() * xScale), yPos)
                || camera.coordinatesInside(xPos + (spriteSheet.getSpriteWidth() * xScale), yPos + (spriteSheet.getSpriteHeight() * yScale));

        if (shouldRender) {
            renderer.renderSprite(spriteSheet.getSprite(getSpriteX(), getSpriteY()), xPos, yPos, xScale, yScale);
        }
    }

    public boolean canPass() {
        return true;
    }
}
