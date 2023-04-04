import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Riddle extends VBox {
    private String question;
    private String[] answer = new String[4];
    private int rightAnswer;

    Label questionLabel = new Label();

    List<Label> answerLabels = new ArrayList<>();

    public Riddle() {
        super();
        initLayout();
    }

    public Riddle(String question, String[] answer, int rightAnswer) {
        this.question = question;
        this.answer = answer;
        this.rightAnswer = rightAnswer;

        questionLabel.setText(question);
        questionLabel.setFont(Font.font(24));

        for (int i = 0; i < this.answer.length; i++) {
            Label label = new Label();
            this.answerLabels.add(label);
            label.setText(answer[i]);
            label.setFont(Font.font(18));
        }

        initLayout();
    }

    private void initLayout() {
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
                if (index == rightAnswer) {
                    // create new label to display "CORRECT!"
                    Label resultLabel = new Label("CORRECT!");
                    resultLabel.setFont(Font.font(24));
                    resultLabel.setVisible(false);
                    this.getChildren().add(resultLabel);

                    // hide the question and answer labels and show the result label
                    questionLabel.setVisible(false);
                    answerBox.setVisible(false);
                    resultLabel.setVisible(true);
                } else {
                    // create new label to display "INCORRECT!"
                    Label resultLabel = new Label("INCORRECT!");
                    resultLabel.setFont(Font.font(24));
                    resultLabel.setVisible(false);
                    this.getChildren().add(resultLabel);

                    // hide the question and answer labels and show the result label
                    questionLabel.setVisible(false);
                    answerBox.setVisible(false);
                    resultLabel.setVisible(true);
                }
            });
        }

        answerBox.getChildren().addAll(answerLabels);

        this.getChildren().add(questionLabel);
        this.getChildren().add(answerBox);
    }


}

