package ui;

import org.jetbrains.annotations.NotNull;
import main.Game;

public interface Renderable {

    public void render(@NotNull final Renderer renderer, final int xScale, final int yScale);

    public void step(@NotNull final Game game);
}
