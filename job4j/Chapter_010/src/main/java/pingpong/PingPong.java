package pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PingPong extends Application {
    private static final String NAME = "Ping-Pong";

    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rectangle = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rectangle);
        Thread move = new Thread(new RectangleMove(rectangle));
        move.start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(NAME);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(event -> move.interrupt());
    }
}
