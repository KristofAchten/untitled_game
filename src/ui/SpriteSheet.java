package ui;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {
    private final BufferedImage image;
    private final int width;
    private final int height;
    private final int spriteWidth;
    private final int spriteHeight;
    private final int[] pixels;

    @NotNull private final List<Sprite> sprites = new ArrayList<>();

    public SpriteSheet(@NotNull final BufferedImage image,
                       final int spriteWidth,
                       final int spriteHeight) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.pixels = new int[getWidth() * getHeight()];
        image.getRGB(0, 0, getWidth(), getHeight(), getPixels(), 0, getWidth());
    }

    public void init() {
        for (int y = 0; y < getHeight(); y += getSpriteHeight()) {
            for (int x = 0; x < getWidth(); x += getSpriteWidth()) {
                getSprites().add(loadSprite(x, y));
            }
        }
    }

    private Sprite loadSprite(final int xPos, final int yPos) {
        final int[] spritePixels = new int[getSpriteWidth() * getSpriteHeight()];
        getImage().getRGB(xPos, yPos, getSpriteWidth(), getSpriteHeight(), spritePixels, 0, getSpriteWidth());

        return new Sprite(getSpriteWidth(), getSpriteHeight(), spritePixels);
    }

    public Sprite getSprite(int xPos, int yPos) {
        return getSprites().get(xPos + yPos * (getWidth() / getSpriteWidth()));
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public int[] getPixels() {
        return pixels;
    }

    @NotNull
    public List<Sprite> getSprites() {
        return sprites;
    }

}
