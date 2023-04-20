import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GUI extends Application {
    Maze maze=new Maze("./ressources/level1.txt");
    Player hero = new Player(200,10);
    Camera camera = new Camera(150,0, hero);
    Stage questionStage = new Stage();
    GraphicalEngine graphicalEngine;
    PhysicalEngine physicalEngine;


    AnimationTimer unAutreJour = new AnimationTimer() {
            @Override
            public void handle(long l) {
                camera.update(l);
                graphicalEngine.render(l);
                physicalEngine.update(l);
            }
        };

        @Override
        public void start(Stage primaryStage) throws Exception{
            primaryStage.setTitle("Hello world");
            Group root = new Group();
            Group questionGroup = new Group();
            Scene questionScene = new Scene(questionGroup);
            graphicalEngine = new GraphicalEngine(maze,camera,root,hero);
            physicalEngine = new PhysicalEngine(hero,maze,questionStage);
            Pane pane = new Pane(root);
            Scene theScene = new Scene(pane, 400, 160,true);
            primaryStage.setScene(theScene);

            String[] test={"42","fejiw","@@","dwqew"};

            questionGroup.getChildren().add(new Riddle("What's the answer ?",test,0));
            questionStage.setScene(questionScene);
            primaryStage.show();

            unAutreJour.start();

            theScene.setOnKeyPressed(event -> {
                KeyCode keyCode = event.getCode();
                if (keyCode == KeyCode.A) {
                    hero.moveLeft();
                } else if (keyCode == KeyCode.D) {
                    hero.moveRight();
                } else if (keyCode == KeyCode.SPACE) {
                    hero.jump();
                }
            });

        }


        public static void main(String[] args) {
            launch(args);
        }
    }
