import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class PhysicalEngine {

    boolean greenintersect = false;
    boolean oldIntersect = false;

    boolean showRiddle = false;

    String [][] riddleOptions = {};

    String [] questions = {};

    int [] rightAnswers = {};

    int [] timerValueRef;
    Player hero;
    Maze maze;
    Stage question;
    public PhysicalEngine(Player hero, Maze maze, Stage question, int[] timerValueRef) {
        this.hero = hero;
        this.maze = maze;
        this.question = question;
        this.timerValueRef = timerValueRef;

        this.questions = new String[]{"Are you ready to play?", "Who is the President of the United States",
        "What are the colors of the American flag?"};

        this.riddleOptions = new String[][] {
                {"YES", "NO", "MAYBE", "BRUH"},
                {"OBAMA", "BIDEN", "LEBRON", "TRUMP"},
                {"RED, WHITE, BLUE", "BLUE, WHITE, RED"}
        };

        this.rightAnswers = new int[] {0, 1, 0};
    }

    public void update(long time){
        showRiddle = false;
        boolean intersect=false;
        for(Tiles t : maze.getListOfTiles())
        if (t.getBoundingBox().intersects(hero.getBoundingBox()) && t.getColor() != Color.GREEN){
            intersect=true;
        }
        else if (t.getBoundingBox().intersects(hero.getBoundingBox()) && t.getColor() == Color.GREEN){
            {
            greenintersect = true;
        }
        }
        if (!oldIntersect && intersect) {
            if(!question.isShowing()) {
                showRiddle = true;
                showNewRiddle();}
        }
        oldIntersect=intersect;
        hero.y=intersect? hero.y : hero.y+1;
    }

    private void showNewRiddle() {
        Random random = new Random();
        int num = random.nextInt(3);
        //String[] riddleOptions = {"Option 1", "Option 2", "Option 3"};
        Riddle riddle = new Riddle(questions[num], riddleOptions[num], rightAnswers[num], timerValueRef);
        Scene questionScene = new Scene(new Group(riddle), 400, 200);
        question.setScene(questionScene);
        question.show();
    }

}