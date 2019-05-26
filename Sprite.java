package Main;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.SnapshotParameters;
import javafx.scene.transform.Affine;

public class Sprite {
    private Image image;
    private Image erase;
    private Vector2D pos;
    private Vector2D vel;
    private int width;
    private int height;
    private ImageView imgView;
    Affine rotate = new Affine();

    public Sprite(Image image, int width, Image erase) {
        this.image = image;
        this.erase = erase;
        pos = new Vector2D(0, 0);
        vel = new Vector2D(0, 0);
        this.width = width;
        this.height = (int) Math.round(image.getHeight() / (image.getWidth() / width));
        imgView = new ImageView(image);
    }

    public void setPos(int x, int y) {
        pos.set(x, y);
    }

    public void addVel(int x, int y) {
        vel.add(new Vector2D(x, y));
    }

    public void setVel(int x, int y) {
        vel.set(x, y);
    }

    public Vector2D getVel() {
        return vel;
    }

    public void update() {
        pos.add(vel);
    }

    public Vector2D getPos() {
        return pos;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void render(GraphicsContext gc) {
        gc.setTransform(rotate);
        gc.drawImage(image, pos.x, pos.y, width, height);
    }

    public void rotateImage(GraphicsContext gc, double speed) {
        rotateErase(gc);
        gc.save();
        rotate.appendRotation(speed, pos.x + width/2.0, pos.y + height/2.0);
        gc.setTransform(rotate);
        gc.drawImage(image, pos.x, pos.y, width, height);
        gc.restore();
    }

    private void rotateErase(GraphicsContext gc) { gc.drawImage(erase, pos.x - 1, pos.y - 1, width + 2, height + 2); }

    public void erase(GraphicsContext gc) {
        gc.drawImage(erase, pos.x, pos.y, width, height);
    }

    private Rectangle2D getBoundary() {
        return new Rectangle2D(pos.x, pos.y, width, height);
    }

    public boolean intersects(Sprite s) {
        return s.getBoundary().intersects(this.getBoundary());
    }
}
