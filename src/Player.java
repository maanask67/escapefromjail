import com.sun.javafx.geom.Rectangle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
//Player class, just an idea currently, not complete
public class Player {
    private final double speed = 5;
    double x;
    double y;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /* private final Rectangle playerRect;

    public Player(double x, double y, double width, double height) {
        playerRect = new Rectangle((int)x,(int) y, (int)width, (int) height);
    }

    public Rectangle getNode() {
        return playerRect;
    }

    public void addEventHandlers(Scene scene) {
        scene.setOnKeyPressed(this::handleKeyPressed);
        scene.setOnKeyReleased(this::handleKeyReleased);
    }

    private void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                playerRect.setY(playerRect.getY() - speed);
                break;
            case A:
                playerRect.setX(playerRect.getX() - speed);
                break;
            case S:
                playerRect.setY(playerRect.getY() + speed);
                break;
            case D:
                playerRect.setX(playerRect.getX() + speed);
                break;
            default:
                break;
        }
    }

    private void handleKeyReleased(KeyEvent event) {
        // Handle key released events if needed
    }*/
}


