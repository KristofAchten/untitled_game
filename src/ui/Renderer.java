package ui;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Renderer {

    @NotNull private final BufferedImage view;
    @NotNull private final Camera camera;
    private final int[] pixels;

    public Renderer(@NotNull final JFrame frame) {
        this.view = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.camera = new Camera(frame);
        this.pixels = bufferedImageToPixels(view);
    }

    public void renderFullView(@NotNull final Graphics drawGraphics) {
        drawGraphics.drawImage(view, 0, 0, getWidth(), getHeight(), null);
    }

    public void renderImage(@NotNull final BufferedImage image,
                            final int xPos,
                            final int yPos,
                            final int widthScale,
                            final int heightScale) {
        final int[] imagePixels = bufferedImageToPixels(image);
        for (int y = 0; y < image.getHeight() * widthScale; y++) {
            for (int x = 0; x < image.getWidth() * heightScale; x++) {
                safeSetPixel(
                        imagePixels[(x/heightScale) + (y/widthScale) * image.getWidth()],
                        x + xPos,
                        y + yPos
                );
            }
        }
    }

    private void safeSetPixel(final int pixel,
                              final int xPos,
                              final int yPos) {
        final Camera camera = getCamera();
        if (camera.coordinatesInside(xPos, yPos)) {
            pixels[(xPos - camera.getxPos())  + (yPos - camera.getyPos()) * getWidth()] = pixel;
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
}
