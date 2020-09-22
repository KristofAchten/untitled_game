package ui;

public class Sprite {
    private final int width;
    private final int height;
    private final int[] pixels;

    public Sprite(final int width, final int height, final int[] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
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
}
