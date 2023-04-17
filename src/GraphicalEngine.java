import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GraphicalEngine {
    Maze maze;
    Camera camera;
    Group group;
    Player hero;

    public GraphicalEngine(Maze maze, Camera camera, Group group, Player hero) {
        this.maze = maze;
        this.camera = camera;
        this.group = group;
        this.hero = hero;
    }

    public void render(long time){
        Rectangle background = new Rectangle(0,0,400,160);
        background.setFill(Color.WHITE);
        group.getChildren().add(background);
        for (Tiles tiles : maze.listOfTiles){
            group.getChildren().add(new Rectangle(tiles.getXPosition()-camera.getxPosition(),
                    tiles.getYPosition()-camera.getyPosition(),20,20));
        }

        Rectangle heroRectangle =new Rectangle(hero.x-camera.getxPosition(),
                hero.y-camera.getyPosition(),20,20);
        heroRectangle.setFill(Color.BLUE);
        group.getChildren().add(heroRectangle);


    }
}
