import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ui.Renderer;
import ui.ResourceLoader;
import ui.SpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
    @NotNull private static final String TITLE = "Untitled: the game";
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 1000;

    private static final int TARGET_FPS = 70;
    private static final double CONVERSION_FACTOR = Math.pow(10, 9) / TARGET_FPS;

    @Nullable private static final BufferedImage GRASS = ResourceLoader.loadImage("/img/Grass.png");
    @NotNull private final SpriteSheet spriteSheet;

    @NotNull private final Renderer renderer;


    public Game() {
        final JFrame gameFrame = new JFrame(Game.TITLE);
        prepareFrame(gameFrame);

        createBufferStrategy(3);

        this.renderer = new Renderer(gameFrame);

        final BufferedImage spriteImage = ResourceLoader.loadImage("/img/spritesheet.png");
        if (spriteImage == null) {
            throw new RuntimeException("Provided sprite sheet was null. Could not initialize game.");
        }
        this.spriteSheet = new SpriteSheet(spriteImage, 16, 16);
        this.spriteSheet.init();
    }

    private void prepareFrame(@NotNull final JFrame gameFrame) {
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        gameFrame.setBounds(0, 0, WIDTH, HEIGHT);
        gameFrame.add(this);
        gameFrame.setVisible(true);
    }

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        final BufferStrategy bufferStrategy = getBufferStrategy();

        long prevTime = System.nanoTime();
        double elapsedTimeInSeconds;

        // update consistently but render as much as possible.
        while (true) {
            long curTime = System.nanoTime();
            elapsedTimeInSeconds = (curTime - prevTime) / CONVERSION_FACTOR;
            while (elapsedTimeInSeconds >= 1) {
                runStep();
                elapsedTimeInSeconds--;
            }

            prevTime = curTime;
            feedRenderer(bufferStrategy);
        }
    }

    private void feedRenderer(@NotNull final BufferStrategy bufferStrategy) {
        final Graphics drawGraphics = bufferStrategy.getDrawGraphics();

        getRenderer().renderSprite(spriteSheet.getSprite(3, 4), 50, 50, 5, 5);
        getRenderer().drawToScreen(drawGraphics);

        drawGraphics.dispose();
        bufferStrategy.show();

    }

    private void runStep() {
    }

    @NotNull
    public Renderer getRenderer() {
        return renderer;
    }


    public static void main(String[] args) {
        new Thread(new Game()).start();
    }

}
