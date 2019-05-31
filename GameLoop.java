package Main;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private GameCanvas canvas;
    static boolean jump = false;
    static boolean respawning = true;
    private long startGravityTime = -1;


    public GameLoop(GameCanvas canvas) {
        this.start();
        this.canvas = canvas;
    }

    @Override
    public void handle(long currentTime) {
        if (!canvas.pause) {
            //TODO: Scroll world
            //TODO: make it so that if you press space while respawning, it doesn't jump after respawning

            //Run first frame only
            if (startGravityTime == -1) {
                startGravityTime = currentTime;
                canvas.player.respawn(canvas.gc, this);
                //canvas.player.addVel(3, 0);
            }

            canvas.player.erase(canvas.gc);

            //Run if player is floating above a platform
            if (canvas.player.playerPlatformStatus(canvas.platforms)[0] == -1) {
                canvas.player.applyGravity(currentTime, startGravityTime);
            }

            //Rotate all spinning sprites
            canvas.rotateSpinningSprites();

            //Update player
            canvas.player.update();

            //Collision detection for platforms
            for (Sprite platform : canvas.platforms) {
                if (canvas.player.intersects(platform)) {
                    canvas.player.setPos(canvas.player.getPos().x, platform.getPos().y - canvas.player.getHeight());
                    canvas.player.setVel(3, 0);
                    break;
                }
            }

            //More collision detection. Yay! For obstacles
            for (Sprite obstacle : canvas.obstacles) {
                if (canvas.player.intersects(obstacle)) {
                    canvas.player.respawn(canvas.gc, this);
                    respawning = true;
                    jump = false;
                    break;
                }
            }

            //Run on each jump
            if (jump && !respawning) {
                startGravityTime = currentTime;
                canvas.player.jump();
                jump = false;
            }

            //Draw world
            canvas.drawWorld();

            //Render player
            canvas.player.render(canvas.gc);
        }
    }
}
