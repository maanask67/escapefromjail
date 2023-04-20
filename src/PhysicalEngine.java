import javafx.stage.Stage;

public class PhysicalEngine {

    boolean oldIntersect = false;
    Player hero;
    Maze maze;
    Stage question;
    public PhysicalEngine(Player hero, Maze maze, Stage question) {
        this.hero = hero;
        this.maze = maze;
        this.question = question;
    }

    public void update(long time){
        boolean intersect=false;
        for(Tiles t : maze.getListOfTiles())
        {if (t.getBoundingBox().intersects(hero.getBoundingBox())){
            intersect=true;
        }}
        if (!oldIntersect && intersect) {
            if(!question.isShowing()){question.show();}
        }
        oldIntersect=intersect;
        hero.y=intersect? hero.y : hero.y+1;
    }
}