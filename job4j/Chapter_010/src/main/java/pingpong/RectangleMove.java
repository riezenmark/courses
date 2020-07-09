package pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rectangle;
    private boolean right = true;

    public RectangleMove(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    private void setSide() {
        if (this.rectangle.getX() == 290) {
            this.right = false;
        }
        if (this.rectangle.getX() == 0) {
            this.right = true;
        }
    }

    private void move() {
        setSide();
        if (this.right) {
            this.rectangle.setX(this.rectangle.getX() + 1);
        } else {
            this.rectangle.setX(this.rectangle.getX() - 1);
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            move();
            try {
                Thread.sleep(51);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
