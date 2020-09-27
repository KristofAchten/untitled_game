package main;

import main.listeners.KeyboardInputHandler;
import org.jetbrains.annotations.NotNull;
import ui.Renderer;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable {
    @NotNull private static final String TITLE = "Untitled: the game";
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 1000;

    private static final int TARGET_FPS = 70;
    private static final double CONVERSION_FACTOR = Math.pow(10, 8) / TARGET_FPS;
    @NotNull private final GameMap map;
    @NotNull private final Renderer renderer;
    @NotNull private final KeyboardInputHandler keyboardListener;
    @NotNull private final List<Renderable> gameElements = new ArrayList<>();

    public Game() {
        final SpriteSheet spriteSheet = new SpriteSheet("/img/defaultSpriteSheet.png", new Dimension(16, 16));
        final JFrame gameFrame = new JFrame(Game.TITLE);

        prepareFrame(gameFrame);
        createBufferStrategy(3);

        this.renderer = new Renderer(gameFrame);

        this.map = MapGenerator.generate(spriteSheet);

        gameElements.add(new Player());

        keyboardListener = new KeyboardInputHandler();
        addKeyListener(keyboardListener);
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
                step();
                elapsedTimeInSeconds--;
            }

            prevTime = curTime;
            feedRenderer(bufferStrategy);
        }
    }

    private void feedRenderer(@NotNull final BufferStrategy bufferStrategy) {
        final Graphics drawGraphics = bufferStrategy.getDrawGraphics();

        map.render(getRenderer(), Config.DEFAULT_SCALE, Config.DEFAULT_SCALE);
        for (Renderable renderable : gameElements) {
            renderable.render(getRenderer(), Config.DEFAULT_SCALE, Config.DEFAULT_SCALE);
        }

        getRenderer().drawToScreen(drawGraphics);

        drawGraphics.dispose();
        bufferStrategy.show();
        getRenderer().clear();
    }

    private void step() {
        for (Renderable renderable : gameElements) {
            renderable.step(this);
        }
    }

    @NotNull
    public Renderer getRenderer() {
        return renderer;
    }

    @NotNull
    public KeyboardInputHandler getKeyboardListener() {
        return keyboardListener;
    }

    @NotNull
    public GameMap getMap() {
        return map;
    }

    public static void main(String[] args) {
        new Thread(new Game()).start();
    }

}
