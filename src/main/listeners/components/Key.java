package main.listeners.components;

public class Key {
    private boolean isPressed = false;

    public void toggle(final boolean isPressed) {
        setPressed(isPressed);
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(final boolean pressed) {
        isPressed = pressed;
    }
}
