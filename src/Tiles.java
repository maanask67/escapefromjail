import javafx.geometry.Rectangle2D;

public class Tiles {
    private final int XPosition;
    private final int YPosition;

    public Tiles(int XPosition, int YPosition) {
        this.XPosition = XPosition;
        this.YPosition = YPosition;
    }

    public int getXPosition() {
        return XPosition;
    }

    public int getYPosition() {
        return YPosition;
    }

    public Rectangle2D getBoundingBox(){
        return new Rectangle2D(XPosition,YPosition,20,20);
    }
}