public class Camera {
    private double xPosition;
    private double yPosition;

    private Player hero;

    public Camera(double xPosition, double yPosition, Player hero) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.hero = hero;
    }

    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public void update(long time){
        this.xPosition= hero.x - 70;
    }
}
