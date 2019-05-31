package Main;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    public Image image;
    public Vector2D pos;
    public Vector2D vel;
    public int width;
    public int height;

    public Sprite(Image image, int width, int height) {
        this.image = image;
        pos = new Vector2D(0, 0);
        vel = new Vector2D(0, 0);
        this.width = width;
        this.height = height;
    }

    public Sprite(Image image, int width) {
        this(image, width, (int) Math.round(image.getHeight() / (image.getWidth() / width)));
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

    public void update() {
        pos.add(vel);
    }

    public Vector2D getPos() {
        return pos;
    }

    public int getHeight() {
        return height;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, pos.x, pos.y, width, height);
    }

    public void erase(GraphicsContext gc) {
        gc.clearRect(pos.x,pos.y,width,height);
    }

    private Rectangle2D getBoundary() {
        return new Rectangle2D(pos.x, pos.y, width, height);
    }

    public boolean intersects(Sprite s) {
        return s.getBoundary().intersects(this.getBoundary());
    }
}
