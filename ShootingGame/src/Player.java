import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {
    private double[] position;
    private double[] velocity;
    private double rotation;
    private Image image;

    public Player() {
        this.position = new double[]{0,0};
        this.velocity = new double[]{0,0};
        this.rotation = 0;
    }

    public void update(double time) {
        position[0]+=this.velocity[0] * time;
        position[1]+=this.velocity[1] * time;
    }

    public void render(GraphicsContext context) {
        context.save();
        context.translate(this.position[0], this.position[1]);
        context.rotate(this.rotation);
        context.translate(-this.image.getWidth()/2,-this.image.getHeight()/2);
        context.drawImage(this.image,0,0);

        context.restore();
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double x, double y) {
        this.position[0] = x;
        this.position[1] = y;
    }

    public void updateVelocity(double v) {
        double currentSpeed = Math.sqrt(velocity[0]*velocity[0]+velocity[1]*velocity[1]);
        if (currentSpeed == 0) {
            velocity[0]=v;
            velocity[1]=0;
        } else {
            velocity[0]*=(v / currentSpeed);
            velocity[1]*=(v / currentSpeed);
        }
        double radians = Math.toRadians(this.rotation);
        double newSpeed = Math.sqrt(velocity[0]*velocity[0]+velocity[1]*velocity[1]);
        if(v>0){
            velocity[0] = newSpeed*Math.cos(radians);
            velocity[1] = newSpeed*Math.sin(radians);
        }else {
            velocity[0]= (-1)*newSpeed*Math.cos(radians);
            velocity[1]= (-1)*newSpeed*Math.sin(radians);
        }

    }

    public double getRotation() {
        return rotation;
    }

    public void changeRotation(double rotation) {
        this.rotation += rotation;
    }

    public void setImage(String path) {
        this.image = new Image(path);
    }
}
