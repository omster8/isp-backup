package Main;

public class Vector2D {
    int x;
    int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void sub(Vector2D v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
