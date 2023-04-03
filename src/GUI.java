import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI extends Application {
        AnimationTimer unAutreJour = new AnimationTimer() {
            @Override
            public void handle(long l) {
            }
        };

        @Override
        public void start(Stage primaryStage) throws Exception{
            primaryStage.setTitle("Hello world");
            Group root = new Group();
            Pane pane = new Pane(root);
            Scene theScene = new Scene(pane, 600, 400,true);
            primaryStage.setScene(theScene);

            String[] test={"42","fejiw","@@","dwqew"};

            root.getChildren().add(new Riddle("What's the answer ?",test,0));
            primaryStage.show();
            unAutreJour.start();

        }


        public static void main(String[] args) {
            launch(args);
        }
    }
