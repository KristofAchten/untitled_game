package ui;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class Camera extends Rectangle {

    public Camera(@NotNull final JFrame frame) {
        super(0, 0, frame.getWidth(), frame.getHeight());
    }

}
