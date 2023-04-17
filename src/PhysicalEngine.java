public class PhysicalEngine {
    Player hero;
    Maze maze;

    public PhysicalEngine(Player hero, Maze maze) {
        this.hero = hero;
        this.maze = maze;
    }

    public void update(long time){
        hero.y+=1;
    }
}
