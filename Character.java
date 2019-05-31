package Main;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.ietf.jgss.GSSManager;

import java.util.ArrayList;

public class Character extends Sprite {
    public Character(Image image, int width) {
        super(image, width);
    }

    public Character(Character c, Vector2D pos) {
        super(c.image, c.width);
        super.pos = pos;
    }

    public Timeline createPauseTimerTimeline(AnimationTimer timer, Duration duration) {
        return new Timeline(
                new KeyFrame(Duration.ZERO, event -> timer.stop()),
                new KeyFrame(duration, event -> timer.start())
        );
    }

    public void respawn(GraphicsContext gc, AnimationTimer timer) {
        //TODO: make a flashing respawn - mario style!
        /*this.setPos(20, 601 - this.getHeight());
        this.setVel(this.getVel().x, 0);
        if (time <= 0.25 || (time > 0.5 && time <= 0.75) || (time > 1 && time <= 1.25) || (time > 1.5 && time <= 1.75)) {
            this.render(gc);
        } else {
            this.erase(gc);
        }*/
        GameLoop.respawning = true;
        Timeline delay = createPauseTimerTimeline(timer, new Duration(1000));

        this.setPos(20, 601 - this.getHeight());
        this.setVel(3, 0);
        this.update();
        this.render(gc);
        delay.playFromStart();
        this.erase(gc);
        delay.playFromStart();
        this.render(gc);
        delay.playFromStart();
        this.erase(gc);
        delay.playFromStart();
        this.render(gc);
        delay.playFromStart();
        this.erase(gc);
        delay.playFromStart();

        delay.setOnFinished(event -> GameLoop.respawning = false);
    }

    public int[] playerPlatformStatus(ArrayList<Sprite> platforms) {
        for (int i = 0; i < platforms.size(); i++) {
            if (platforms.get(i).intersects(this)) {
                return new int[]{1, i};
            }
            if ((new Character(this, new Vector2D(this.getPos().x, this.getPos().y + 1))).intersects(platforms.get(i))) {
                return new int[]{0, i};
            }
        }
        return new int[]{-1, -1};
    }

    public boolean hitObstacle(ArrayList<Sprite> obstacles) {
        for (Sprite s : obstacles) {
            if (this.intersects(s)) {
                return true;
            }
        }
        return false;
    }

    public void jump() { this.setVel(3, -15); }

    public void applyGravity(long currentTime, long startGravityTime) {
        this.addVel(0, (int) Math.round(9.8 * ((currentTime - startGravityTime) / 1000000000.0)));
    }
}
