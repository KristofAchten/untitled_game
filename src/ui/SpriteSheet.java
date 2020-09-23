package ui;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
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

    public SpriteSheet(@NotNull final String spritesheetPath,
                       @NotNull final Dimension spriteDimension) {

        this.image = ResourceLoader.loadImage(spritesheetPath);
        if (image == null) {
            throw new IllegalStateException("Could not load Spritesheet at path " + spritesheetPath);
        }

        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pixels = new int[getWidth() * getHeight()];
        image.getRGB(0, 0, getWidth(), getHeight(), getPixels(), 0, getWidth());

        this.spriteWidth = (int) spriteDimension.getWidth();
        this.spriteHeight = (int) spriteDimension.getHeight();

        init();
    }


    public void init() {
        for (int y = 0; y < getHeight(); y += getSpriteHeight()) {
            for (int x = 0; x < getWidth(); x += getSpriteWidth()) {
                getSprites().add(loadSprite(x, y));
            }
        }
    }

    @Contract("_, _ -> new")
    private @NotNull Sprite loadSprite(final int xPos, final int yPos) {
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
