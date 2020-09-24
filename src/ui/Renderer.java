package ui;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Renderer {
    private static final int ALPHA = 0xFFFF00DC;

    @NotNull private final BufferedImage view;
    @NotNull private final Camera camera;
    private final int[] pixels;

    public Renderer(@NotNull final JFrame frame) {
        this.view = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.camera = new Camera(frame);
        this.pixels = bufferedImageToPixels(view);
    }

    public void drawToScreen(@NotNull final Graphics drawGraphics) {
        drawGraphics.drawImage(view, 0, 0, getWidth(), getHeight(), null);
    }

    public void renderImage(@NotNull final BufferedImage image, final int xPos, final int yPos, final int widthScale,
                            final int heightScale) {
        renderPixelArray(bufferedImageToPixels(image), xPos, yPos, image.getWidth(), image.getHeight(), widthScale, heightScale);
    }

    public void renderSprite(@NotNull final Sprite sprite, final int xPos, final int yPos, final int widthScale,
                             final int heightScale) {
        renderPixelArray(sprite.getPixels(), xPos, yPos, sprite.getWidth(), sprite.getHeight(), widthScale, heightScale);
    }


    public void renderRectangle(@NotNull final Rectangle rectangle, final int xScale, final int yScale) {
        final int[] rectPixels = new int[rectangle.getWidth() * rectangle.getHeight()];
        for (int i = 0; i < rectPixels.length; i++) {
            rectPixels[i] = 0x76EEC6;
        }
        renderPixelArray(rectPixels, rectangle.getxPos(), rectangle.getyPos(), rectangle.getWidth(), rectangle.getHeight(), xScale, yScale);
    }

    public void renderPixelArray(@NotNull final int[] pixels, final int xPos, final int yPos, final int width,
                                 final int height, final int widthScale, final int heightScale) {
        for (int y = 0; y < height * widthScale; y++) {
            for (int x = 0; x < width * heightScale; x++) {
                safeSetPixel(pixels[(x / heightScale) + (y / widthScale) * width], x + xPos, y + yPos);
            }
        }
    }

    private void safeSetPixel(final int pixel,
                              final int xPos,
                              final int yPos) {
        final Camera camera = getCamera();
        if (camera.coordinatesInside(xPos, yPos) && pixel != Renderer.ALPHA) {
            pixels[(xPos - camera.getxPos()) + (yPos - camera.getyPos()) * getWidth()] = pixel;
        }
    }

    private int[] bufferedImageToPixels(@NotNull BufferedImage image) {
        return ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public int getWidth() {
        return view.getWidth();
    }

    public int getHeight() {
        return view.getHeight();
    }

    @NotNull
    public Camera getCamera() {
        return camera;
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
}

