package ui;

import org.jetbrains.annotations.NotNull;

public class Tile {

    @NotNull private final String identifier;
    @NotNull private final Sprite sprite;

    public Tile(@NotNull final String identifier,
                @NotNull final Sprite sprite) {
        this.identifier = identifier;
        this.sprite = sprite;
    }

    @NotNull
    public String getIdentifier() {
        return identifier;
    }

    @NotNull
    public Sprite getSprite() {
        return sprite;
    }
}
