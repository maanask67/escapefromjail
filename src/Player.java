import com.sun.javafx.geom.Rectangle;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Player {
    double x;
    double y;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        x -= 15; // Move player to the left
    }

    public void moveRight() {
        x += 15; // Move player to the right
    }

    public void jump() {
        y -= 30;

    }


    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(x, y, 20, 20);
    }
}
