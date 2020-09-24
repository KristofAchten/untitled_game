package main.listeners;

import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyboardInputHandler extends KeyAdapter {

    @NotNull private final Map<Integer, Boolean> pressedKeys = new HashMap<>();

    @Override
    public void keyPressed(@NotNull final KeyEvent e) {
        getPressedKeys().put(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(@NotNull final KeyEvent e) {
        getPressedKeys().put(e.getKeyCode(), false);

    }

    @NotNull
    public Map<Integer, Boolean> getPressedKeys() {
        return pressedKeys;
    }
}
