import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GraphicalEngine {
    private final Maze maze;
    private final Camera camera;
    private final Group group;
    private final Player hero;

    public GraphicalEngine(Maze maze, Camera camera, Group group, Player hero) {
        this.maze = maze;
        this.camera = camera;
        this.group = group;
        this.hero = hero;
    }

    public void render(long time) {
        group.getChildren().clear();

        ImageView backgroundImage = new ImageView(new Image("file:./ressources/jail.jpg"));
        backgroundImage.setFitWidth(400);
        backgroundImage.setFitHeight(160);
        group.getChildren().add(backgroundImage);

        for (Tiles tile : maze.getListOfTiles()) {
            Rectangle tileRect = new Rectangle(
                    tile.getXPosition() - camera.getxPosition(),
                    tile.getYPosition() - camera.getyPosition(),
                    20, 20);
            tileRect.setFill(tile.getColor());
            group.getChildren().add(tileRect);
        }

        ImageView heroImage = new ImageView(new Image("file:./ressources/prisoner.png"));
        heroImage.setPreserveRatio(false); // Set preserveRatio to false
        heroImage.setSmooth(true); // Set smooth rendering to true
        heroImage.setX(hero.x - camera.getxPosition());
        heroImage.setY(hero.y - camera.getyPosition());
        heroImage.setFitWidth(20);
        heroImage.setFitHeight(20);
        group.getChildren().add(heroImage);

    }
}
