package Main;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Sprite {
    private Image image;
    private Image erase;
    private Vector2D pos;
    private Vector2D vel;
    private int width;
    private int height;

    public Sprite(Image image, int width, Image erase) {
        this.image = image;
        this.erase = erase;
        pos = new Vector2D(0,0);
        vel = new Vector2D(0,0);
        this.width = width;
        this.height = (int)Math.round(image.getHeight()/(image.getWidth()/width));
    }

    public void setPos(int x, int y) {
        pos.set(x,y);
    }

    public void addVel(int x, int y) {
        vel.add(new Vector2D(x,y));
    }

    public void setVel(int x, int y) {
        vel.set(x,y);
    }

    public Vector2D getVel() {return vel;}

    public void update()
    {
        pos.add(vel);
    }

    public Vector2D getPos() {return pos;}

    public int getHeight() {return height;}

    public int getWidth() {return width;}

    public void render(GraphicsContext gc)
    {
        gc.drawImage(image, pos.x, pos.y, width, height);
    }

    public void erase(GraphicsContext gc)
    {
        gc.drawImage(erase, pos.x, pos.y, width, height);
    }

    private Rectangle2D getBoundary()
    {
        return new Rectangle2D(pos.x, pos.y, width, height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects(this.getBoundary());
    }
}
