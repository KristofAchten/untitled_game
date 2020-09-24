package ui;

import main.Config;
import main.Game;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

public class Player extends Rectangle implements Renderable {

    private static final int UP_KEY = Config.USE_AZERTY ? KeyEvent.VK_Z : KeyEvent.VK_W;
    private static final int DOWN_KEY = KeyEvent.VK_S;
    private static final int LEFT_KEY = Config.USE_AZERTY ? KeyEvent.VK_Q : KeyEvent.VK_A;
    private static final int RIGHT_KEY = KeyEvent.VK_D;
    private static final int UP_KEY_ALTERNATIVE = KeyEvent.VK_UP;
    private static final int DOWN_KEY_ALTERNATIVE = KeyEvent.VK_DOWN;
    private static final int LEFT_KEY_ALTERNATIVE = KeyEvent.VK_LEFT;
    private static final int RIGHT_KEY_ALTERNATIVE = KeyEvent.VK_RIGHT;

    private static final int MOVEMENT_SPEED = 1;

    public Player() {
        super(0, 0, 15, 30);
    }

    @Override
    public void render(@NotNull final Renderer renderer, final int xScale, final int yScale) {
        renderer.renderRectangle(this, xScale, yScale);
    }

    @Override
    public void step(@NotNull final Game game) {
        final Map<Integer, Boolean> pressedKeys = game.getKeyboardListener().getPressedKeys();

        if (pressedKeys.getOrDefault(UP_KEY, false) || pressedKeys.getOrDefault(UP_KEY_ALTERNATIVE, false)) {
            move(Direction.UP);
        }
        if (pressedKeys.getOrDefault(DOWN_KEY, false) || pressedKeys.getOrDefault(DOWN_KEY_ALTERNATIVE, false)) {
            move(Direction.DOWN);
        }
        if (pressedKeys.getOrDefault(LEFT_KEY, false) || pressedKeys.getOrDefault(LEFT_KEY_ALTERNATIVE, false)) {
            move(Direction.LEFT);
        }
        if (pressedKeys.getOrDefault(RIGHT_KEY, false) || pressedKeys.getOrDefault(RIGHT_KEY_ALTERNATIVE, false)) {
            move(Direction.RIGHT);
        }
        updateCamera(game.getRenderer().getCamera());
    }

    private void updateCamera(@NotNull final Camera camera) {
        camera.setXPos((getxPos() + (getWidth() * Config.DEFAULT_SCALE) / 2) - (camera.getWidth() / 2));
        camera.setYPos((getyPos() + (getHeight() * Config.DEFAULT_SCALE) / 2) - (camera.getHeight() / 2));
    }

    public void move(@NotNull final Direction dir) {
        switch (dir) {
            case LEFT:
                setXPos(getxPos() - MOVEMENT_SPEED);
                break;
            case RIGHT:
                setXPos(getxPos() + MOVEMENT_SPEED);
                break;
            case UP:
                setYPos(getyPos() - MOVEMENT_SPEED);
                break;
            case DOWN:
                setYPos(getyPos() + MOVEMENT_SPEED);
                break;
        }
    }
}
