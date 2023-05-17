import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUI extends Application {
    Maze maze = new Maze("./ressources/level1.txt");
    Player hero = new Player(200, 10);
    Camera camera = new Camera(150, 0, hero);
    Stage questionStage = new Stage();
    GraphicalEngine graphicalEngine;
    PhysicalEngine physicalEngine;

    int[] timerValueRef = {60}; // Initial timer value
    Label timerLabel = new Label();
    private Label gameOverLabel = new Label("GAME OVER!");

    private Pane pane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello world");
        Group root = new Group();
        Group questionGroup = new Group();
        Scene questionScene = new Scene(questionGroup);
        graphicalEngine = new GraphicalEngine(maze, camera, root, hero);
        physicalEngine = new PhysicalEngine(hero, maze, questionStage);
        pane = new Pane(root);
        Scene theScene = new Scene(pane, 400, 160, true);
        primaryStage.setScene(theScene);

        String[] start = {"YES", "NO", "MAYBE", "BRUH"};


        questionGroup.getChildren().add(new Riddle("Are you ready to start?", start, 0, timerValueRef));
        questionStage.setScene(questionScene);
        primaryStage.show();

        // Start the game loop
        unAutreJour.start();

        // Add the timer label to the scene
        pane.getChildren().add(timerLabel);
        timerLabel.setLayoutX(10);
        timerLabel.setLayoutY(10);
        timerLabel.setText("Timer: " + timerValueRef[0]);

        // Create a timeline to update the timer every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

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

    // Game loop
    AnimationTimer unAutreJour = new AnimationTimer() {
        @Override
        public void handle(long l) {
            camera.update(l);
            graphicalEngine.render(l);
            physicalEngine.update(l);
        }
    };

    // Update the timer label
    private void updateTimer() {
        timerValueRef[0]--; // Update the global timer value through the reference
        timerLabel.setText("Timer: " + timerValueRef[0]);
        timerLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        if (timerValueRef[0] == 0) {
            // Game over, stop the game loop
            unAutreJour.stop();
            // Display "GAME OVER!" and close the game after a second
            displayGameOver();
        }
    }

    // Display "GAME OVER!" and close the game after a second
// Display "GAME OVER!" and remove everything else from the screen
    private void displayGameOver() {
        pane.getChildren().clear();

        Label gameOverLabel = new Label("GAME OVER!");
        gameOverLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        gameOverLabel.setTextFill(Color.BLACK);

        pane.getChildren().add(gameOverLabel);
        gameOverLabel.setLayoutX(20);
        gameOverLabel.setLayoutY(20);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
        }));
        timeline.play();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
