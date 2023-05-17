import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Riddle extends VBox {
    private String question;
    private String[] answer = new String[4];
    private int rightAnswer;
    private int selectedAnswer = 1; // currently selected answer

    private int[] timerValueRef;

    Label questionLabel = new Label();

    Label correctLabel = new Label("CORRECT!");

    Label incorrectLabel = new Label("INCORRECT!");

    Label timerLabel = new Label();

    List<Label> answerLabels = new ArrayList<>();

    public Riddle() {
        super();
        initLayout();
        setFocusTraversable(true); // enable focus traversal for the VBox
        setOnKeyPressed(event -> handleKeyPress(event.getCode())); // handle key presses
    }

    public Riddle(String question, String[] answer, int rightAnswer, int[] timerValueRef) {
        this.question = question;
        this.answer = answer;
        this.rightAnswer = rightAnswer;
        this.timerLabel = timerLabel;
        this.timerValueRef = timerValueRef;

        questionLabel.setText(question);
        questionLabel.setFont(Font.font(24));

        for (int i = 0; i < this.answer.length; i++) {
            Label label = new Label();
            this.answerLabels.add(label);
            label.setText(answer[i]);
            label.setFont(Font.font(18));

            // add hover effect using CSS background color
            label.setOnMouseEntered(e -> {
                if (label != answerLabels.get(selectedAnswer)) {
                    label.setStyle("-fx-background-color: #E0E0E0;");
                }
            });

            label.setOnMouseExited(e -> {
                if (label != answerLabels.get(selectedAnswer)) {
                    label.setStyle("-fx-background-color: transparent;");
                }
            });
        }

        initLayout();
        setFocusTraversable(true); // enable focus traversal for the VBox
        setOnKeyPressed(event -> handleKeyPress(event.getCode())); // handle key presses
    }

    private void initLayout() {
        correctLabel.setVisible(false);
        incorrectLabel.setVisible(false);
        // create HBox layout manager for answer labels
        HBox answerBox = new HBox();
        answerBox.setSpacing(10);

        // set padding around answer labels
        answerBox.setPadding(new Insets(10));

        // set HBox.setHgrow() for each answer label
        for (Label label : answerLabels) {
            HBox.setHgrow(label, Priority.ALWAYS);

            // add event handler to label
            label.setOnMouseClicked(event -> {
                int index = answerLabels.indexOf(label);
                processAnswer(index);
            });

            // add label to answerBox
            answerBox.getChildren().add(label);
        }

        this.getChildren().add(correctLabel);
        this.getChildren().add(incorrectLabel);
        this.getChildren().add(questionLabel);
        this.getChildren().add(answerBox);
    }

    private void handleKeyPress(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT) {
            // move selection up
            if (selectedAnswer > 0) {
                answerLabels.get(selectedAnswer).setStyle("-fx-background-color: transparent;");
                selectedAnswer--;
                updateSelectedAnswer();
            }
        } else if (keyCode == KeyCode.RIGHT) {
            // move selection down
            if (selectedAnswer < answerLabels.size() - 1) {
                answerLabels.get(selectedAnswer).setStyle("-fx-background-color: transparent;");
                selectedAnswer++;
                updateSelectedAnswer();
            }
        } else if (keyCode == KeyCode.ENTER) {
            // submit selected answer
            if (selectedAnswer >= 0) {
                processAnswer(selectedAnswer);
            }
        }
    }

    private void updateSelectedAnswer() {
        // update the style of the selected answer label
        answerLabels.get(selectedAnswer).setStyle("-fx-background-color: #E0E0E0;");
    }

    private void processAnswer(int index) {
        // process the selected answer
        if (index == rightAnswer) {

            // Remove the question and answer labels
            questionLabel.setVisible(false);
            answerLabels.forEach(label -> label.setVisible(false));
            correctLabel.setFont(Font.font(32));
            correctLabel.setTextFill(Color.GREEN);
            correctLabel.setVisible(true);



            // Close the riddle Pane after a delay
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(.5), event -> {
                Stage stage = (Stage) getScene().getWindow();
                stage.close();
            }));
            timeline.setDelay(Duration.seconds(.50));
            timeline.play();
        }
        else {
            questionLabel.setVisible(false);
            answerLabels.forEach(label -> label.setVisible(false));
            incorrectLabel.setFont(Font.font(32));
            incorrectLabel.setTextFill(Color.RED);
            incorrectLabel.setVisible(true);

            timerValueRef[0] -= 10;

            timerLabel.setText("Timer: " + timerValueRef[0]);


            // Close the riddle Pane after a delay
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(.5), event -> {
                Stage stage = (Stage) getScene().getWindow();
                stage.close();
            }));
            timeline.setDelay(Duration.seconds(.5));
            timeline.play();
        }
    }
}


