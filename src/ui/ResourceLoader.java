package ui;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResourceLoader {
    @NotNull private static final Logger LOGGER = Logger.getLogger(ResourceLoader.class.getName());

    @Nullable
    public static BufferedImage loadImage(@NotNull final String path) {
        try {
            final BufferedImage readImage = ImageIO.read(ResourceLoader.class.getResource(path));
            return formatImage(readImage);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Could not load image at path " + path + ": " + e.getMessage());
            return null;
        }
    }

    @Nullable
    private static BufferedImage formatImage(@Nullable final BufferedImage readImage) {
        if (readImage == null) {
            return null;
        }

        final BufferedImage formattedImage = new BufferedImage(readImage.getWidth(), readImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        formattedImage.getGraphics().drawImage(readImage, 0, 0, null);

        return formattedImage;
    }
}
