package ui;

public class Rectangle {
    private int xPos;
    private int yPos;
    private final int width;
    private final int height;

    public Rectangle(final int xPos,
                     final int yPos,
                     final int width,
                     final int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean coordinatesInside(final int xCoordinate,
                                     final int yCoordinate) {
        return xCoordinate >= getxPos()
                && xCoordinate <= getxPos() + getWidth()
                && yCoordinate >= getyPos()
                && yCoordinate <= getyPos() + getHeight();
    }
}
