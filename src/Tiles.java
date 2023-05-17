import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

public class Tiles {
    private final int xPosition;
    private final int yPosition;
    private final Color color;

    public Tiles(int xPosition, int yPosition, Color color) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public Color getColor() {
        return color;
    }

    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(xPosition, yPosition, 20, 20);
    }
}
