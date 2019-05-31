package Main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

public class SpinningSprite extends Sprite {
    private Affine rotate = new Affine();

    public SpinningSprite(Image image, int width) {
        super(image, width);
    }

    public void rotateImage(GraphicsContext gc, double speed) {
        rotateErase(gc);
        gc.save();
        rotate.appendRotation(speed, super.pos.x + super.width/2.0, super.pos.y + super.height/2.0);
        gc.setTransform(rotate);
        gc.drawImage(super.image, super.pos.x, super.pos.y, super.width, super.height);
        gc.restore();
    }

    private void rotateErase(GraphicsContext gc) {
        erase(gc);
        gc.clearRect( super.pos.x - 1, super.pos.y - 1, super.width + 2, super.height + 2);
    }

    public void render(GraphicsContext gc) {
        gc.save();
        gc.setTransform(rotate);
        gc.drawImage(image, pos.x, pos.y, width, height);
        gc.restore();
    }
}
