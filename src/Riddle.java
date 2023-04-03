import javafx.scene.control.Label;
import javafx.scene.layout.Pane;



public class Riddle extends Pane {
    private String question;
    private String[] answer = new String[4];
    private int rightAnswer;

    Label questionLabel = new Label();
    public Riddle() {
        super();
    }

    public Riddle(String question, String[] answer, int rightAnswer) {
        this.question = question;
        this.answer = answer;
        this.rightAnswer = rightAnswer;
        questionLabel.setText(question);
        this.getChildren().add(questionLabel);
    }
}
