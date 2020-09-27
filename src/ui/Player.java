package ui;

import main.Config;
import main.Game;
import main.listeners.KeyboardInputHandler;
import org.jetbrains.annotations.NotNull;
import ui.tiles.Tile;

import java.awt.*;

public class Player extends Rectangle implements Renderable {

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
        move(game);
        updateCamera(game.getRenderer().getCamera());
    }

    private void updateCamera(@NotNull final Camera camera) {
        camera.setXPos((getXPos() + (getWidth() * Config.DEFAULT_SCALE) / 2) - (camera.getWidth() / 2));
        camera.setYPos((getYPos() + (getHeight() * Config.DEFAULT_SCALE) / 2) - (camera.getHeight() / 2));
    }

    public void move(@NotNull final Game game) {
        if (!canMove(game)) {
            return;
        }

        if (KeyboardInputHandler.LEFT.isPressed()) {
            setXPos(getXPos() - MOVEMENT_SPEED);
        }
        if (KeyboardInputHandler.RIGHT.isPressed()) {
            setXPos(getXPos() + MOVEMENT_SPEED);
        }
        if (KeyboardInputHandler.UP.isPressed()) {
            setYPos(getYPos() - MOVEMENT_SPEED);
        }
        if (KeyboardInputHandler.DOWN.isPressed()) {
            setYPos(getYPos() + MOVEMENT_SPEED);
        }
    }

    private boolean canMove(@NotNull final Game game) {
        final Point currentTilePos = getTileAtCurrentPosition();

        if (KeyboardInputHandler.LEFT.isPressed()) {
            return canMoveInDirection(game, currentTilePos, 0, -1);
        }
        if (KeyboardInputHandler.RIGHT.isPressed()) {
            return canMoveInDirection(game, currentTilePos, 0, 1);
        }
        if (KeyboardInputHandler.UP.isPressed()) {
            return canMoveInDirection(game, currentTilePos, -1, 0);
        }
        if (KeyboardInputHandler.DOWN.isPressed()) {
            return canMoveInDirection(game, currentTilePos, 1, 0);
        }
        return true;
    }

    private boolean canMoveInDirection(@NotNull final Game game, @NotNull final Point currentPos, final int xdiff, final int ydiff) {
        return game.getMap()
                .getMapTiles()
                .get(new Point(currentPos.x + xdiff, currentPos.y + ydiff))
                .stream().allMatch(Tile::canPass);
    }

    private Point getTileAtCurrentPosition() {
        final int x = getXPos();
        final int y  = getYPos();
        final int scaleFactor = 16 * Config.DEFAULT_SCALE;

        return new Point (x / scaleFactor, y / scaleFactor);
    }
}
