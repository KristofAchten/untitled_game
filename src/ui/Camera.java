package ui;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class Camera extends Rectangle {

    public enum Direction {
        LEFT, RIGHT, UP, DOWN;
    }

    public Camera(@NotNull final JFrame frame) {
        super(0, 0, frame.getWidth(), frame.getHeight());
    }

    public void move(@NotNull final Direction dir) {
        switch (dir) {
            case LEFT:
                setxPos(getxPos() - 1);
                break;
            case RIGHT:
                setxPos(getxPos() + 1);
                break;
            case UP:
                setyPos(getyPos() - 1);
                break;
            case DOWN:
                setyPos(getyPos() + 1);
                break;
        }
    }

}
