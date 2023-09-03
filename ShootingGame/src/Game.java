import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;


public class Game extends Application {
    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Shooting Game");

        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root);
        stage.setScene(mainScene);
        Canvas canvas = new javafx.scene.canvas.Canvas(1280, 720);
        GraphicsContext context = canvas.getGraphicsContext2D();
        root.setCenter(canvas);

        Player player = new Player();
        player.setImage("player.png");
        player.setPosition(300,100);


        AnimationTimer game = new AnimationTimer() {
            @Override
            public void handle(long l) {
                mainScene.setOnKeyPressed(
                        event -> {
                            if (event.getCode().toString().equals("LEFT")) {
                                player.changeRotation(-5);
                            }
                            if (event.getCode().toString().equals("RIGHT")) {
                                player.changeRotation(5);
                            }
                            if (event.getCode().toString().equals("UP")) {
                                player.updateVelocity(400);
                            }
                            if (event.getCode().toString().equals("DOWN")) {
                                player.updateVelocity(-200);
                            }


                            player.update(1 / 60.0);
                        }
                );
                mainScene.setOnKeyReleased(
                        event -> {
                            if (event.getCode().toString().equals("UP")) {
                                player.updateVelocity(0);
                            }
                            if (event.getCode().toString().equals("DOWN")) {
                                player.updateVelocity(0);
                            }
                            player.update(1 / 60.0);
                        }
                );
                context.setFill(Color.WHITE);
                context.fillRect(0, 0, 1280, 720);

                player.render(context);
            }
        };
        game.start();

        stage.show();
    }
}
