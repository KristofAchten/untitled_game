package ui;

import org.jetbrains.annotations.NotNull;

public class Rectangle {
    private int xPos;
    private int yPos;
    private final int width;
    private final int height;
    private final int[] pixels;

    public Rectangle(final int xPos,
                     final int yPos,
                     final int width,
                     final int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.pixels = generatePixels();

    }

    @NotNull
    private int[] generatePixels() {
        final int[] rectPixels = new int[getWidth() * getHeight()];
        for (int i = 0; i < rectPixels.length; i++) {
            rectPixels[i] = 0x76EEC6;
        }
        return rectPixels;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean coordinatesInside(final int xCoordinate,
                                     final int yCoordinate) {
        return xCoordinate >= getXPos()
                && xCoordinate < getXPos() + getWidth()
                && yCoordinate >= getYPos()
                && yCoordinate < getYPos() + getHeight();
    }
}
