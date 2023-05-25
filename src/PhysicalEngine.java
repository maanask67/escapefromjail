import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhysicalEngine {

    boolean blueIntersect = false;
    boolean greenintersect = false;
    boolean oldIntersect = false;

    boolean showRiddle = false;

    String[][] riddleOptions = {};

    String[] questions = {};

    int[] rightAnswers = {};

    int[] timerValueRef;
    Player hero;
    Maze maze;
    Stage question;

    public PhysicalEngine(Player hero, Maze maze, Stage question, int[] timerValueRef) {
        this.hero = hero;
        this.maze = maze;
        this.question = question;
        this.timerValueRef = timerValueRef;

        loadRiddlesFromFile("./ressources/riddles.txt");
    }

    private void loadRiddlesFromFile(String filePath) {
        List<String[]> optionsList = new ArrayList<>();
        List<String> questionsList = new ArrayList<>();
        List<Integer> answersList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 4) {
                    String question = tokens[0].trim();
                    String[] options = new String[tokens.length - 1];
                    int correctAnswer = Integer.parseInt(tokens[tokens.length - 1].trim());

                    for (int i = 0; i < options.length; i++) {
                        options[i] = tokens[i + 1].trim();
                    }

                    optionsList.add(options);
                    questionsList.add(question);
                    answersList.add(correctAnswer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        riddleOptions = optionsList.toArray(new String[0][0]);
        questions = questionsList.toArray(new String[0]);
        rightAnswers = answersList.stream().mapToInt(Integer::intValue).toArray();
    }

    public void update(long time) {
        showRiddle = false;
        boolean intersect = false;
        for (Tiles t : maze.getListOfTiles()) {
            if (t.getBoundingBox().intersects(hero.getBoundingBox()) && t.getColor() != Color.GREEN) {
                intersect = true;
            } else if (t.getBoundingBox().intersects(hero.getBoundingBox()) && t.getColor() == Color.GREEN) {
                greenintersect = true;
            } else if (t.getBoundingBox().intersects(hero.getBoundingBox()) && t.getColor() == Color.BLUE) {
                blueIntersect = true;
            }
        }

        if (!oldIntersect && intersect) {
            if (!question.isShowing()) {
                showRiddle = true;
                showNewRiddle();
            }
        }
        oldIntersect = intersect;
        hero.y = intersect ? hero.y : hero.y + 1;
    }

    private void showNewRiddle() {
        Random random = new Random();
        int num = random.nextInt(questions.length);
        //String[] riddleOptions = {"Option 1", "Option 2", "Option 3"};
        Riddle riddle = new Riddle(questions[num], riddleOptions[num], rightAnswers[num], timerValueRef);
        Scene questionScene = new Scene(new Group(riddle), 400, 200);
        question.setScene(questionScene);
        question.show();
    }

}
