import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GUI extends Application {
    Maze maze=new Maze("./ressources/level1.txt");
    Camera camera = new Camera(150,0);
    GraphicalEngine graphicalEngine;
    Player hero = new Player(200,10);
    PhysicalEngine physicalEngine;

    AnimationTimer unAutreJour = new AnimationTimer() {
            @Override
            public void handle(long l) {
                graphicalEngine.render(l);
                physicalEngine.update(l);
            }
        };

        @Override
        public void start(Stage primaryStage) throws Exception{
            primaryStage.setTitle("Hello world");
            Group root = new Group();
            graphicalEngine = new GraphicalEngine(maze,camera,root,hero);
            physicalEngine = new PhysicalEngine(hero,maze);
            Pane pane = new Pane(root);
            Scene theScene = new Scene(pane, 400, 160,true);
            primaryStage.setScene(theScene);

            String[] test={"42","fejiw","@@","dwqew"};

            //root.getChildren().add(new Riddle("What's the answer ?",test,0));
            Popup letsTest=new Popup();
            letsTest.getContent().add(new Riddle("What ?", test,0));
            primaryStage.show();
            letsTest.show(primaryStage);
            unAutreJour.start();

        }


        public static void main(String[] args) {
            launch(args);
        }
    }
