package main.listeners;

import main.listeners.components.Key;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInputHandler extends KeyAdapter {

    @NotNull public static final Key UP = new Key();
    @NotNull public static final Key DOWN = new Key();
    @NotNull public static final Key LEFT = new Key();
    @NotNull public static final Key RIGHT = new Key();

    @Override
    public void keyPressed(@NotNull final KeyEvent e) {
        updateKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(@NotNull final KeyEvent e) {
        updateKey(e.getKeyCode(), false);
    }

    private void updateKey(final int keyCode, final boolean pressed) {
        switch (keyCode) {
            case KeyEvent.VK_Z:
                UP.toggle(pressed);
                break;
            case KeyEvent.VK_S:
                DOWN.toggle(pressed);
                break;
            case KeyEvent.VK_Q:
                LEFT.toggle(pressed);
                break;
            case KeyEvent.VK_D:
                RIGHT.toggle(pressed);
                break;
            case KeyEvent.VK_UP:
                UP.toggle(pressed);
                break;
            case KeyEvent.VK_DOWN:
                DOWN.toggle(pressed);
                break;
            case KeyEvent.VK_LEFT:
                LEFT.toggle(pressed);
                break;
            case KeyEvent.VK_RIGHT:
                RIGHT.toggle(pressed);
                break;
            default:
                break;
        }
    }
}
