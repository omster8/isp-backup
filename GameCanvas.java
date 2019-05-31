package Main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.util.ArrayList;

public class GameCanvas extends Canvas {
    //TODO: make all instance variables private and make getters and setters
    public GraphicsContext gc;
    public boolean pause;
    public boolean returnToGame;
    public Character player;
    public ArrayList<Sprite> platforms = new ArrayList<Sprite>();
    public ArrayList<Sprite> obstacles = new ArrayList<Sprite>();
    private ArrayList<SpinningSprite> spinningObstacles = new ArrayList<SpinningSprite>();

    public GameCanvas(int width, int height) {
        super(width, height);
        gc = this.getGraphicsContext2D();
        loadResources();
        setSpritePositions();

        player.setPos(20, 600 - player.getHeight());
        player.render(gc);

        drawWorld();
    }

    private void loadResources() {
        try {
            player = new Character(new Image("./running_man.png"), 40);
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform_1.PNG"), 75));
            platforms.add(new Sprite(new Image("./platforms/platform_1.PNG"), 800, 1));

            obstacles.add(new Sprite(new Image("./spikes/single_spike.png"), 13));
            obstacles.add(new Sprite(new Image("./spikes/single_spike.png"), 13));
            obstacles.add(new Sprite(new Image("./spikes/single_spike.png"), 13));
            obstacles.add(new Sprite(new Image("./spikes/single_spike.png"), 13));

            spinningObstacles.add(new SpinningSprite(new Image("./rotating_blades/blade_1.png"), 25));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawWorld() {
        gc.clearRect(0,0,800,600);
        gc.setFill(Color.RED);

        for (Sprite s : platforms) {
            s.render(gc);
        }
        for (Sprite s : obstacles) {
            s.render(gc);
        }
        for (Sprite s : spinningObstacles) {
            s.render(gc);
        }
    }

    private void setSpritePositions() {
        platforms.get(0).setPos(295, 530);
        platforms.get(1).setPos(390, 470);
        platforms.get(2).setPos(485, 410);
        platforms.get(3).setPos(200, 575);
        platforms.get(4).setPos(0, 601);

        obstacles.get(0).setPos(362, 510);
        obstacles.get(1).setPos(457, 450);
        obstacles.get(2).setPos(552, 390);
        obstacles.get(3).setPos(262, 555);

        spinningObstacles.get(0).setPos(174, 575);
    }

    public void rotateSpinningSprites() {
        for (SpinningSprite s : spinningObstacles) {
            s.rotateImage(gc, 3);
        }
    }

    public void pause() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800, 600);
        gc.setFill(Color.GREEN);
        gc.fillText("GAME IS PAUSED, PRESS \'r\' TO CONTINUE", 200, 200);
        gc.setFill(Color.RED);
        //TODO: make a pause and countdown 3-2-1
    }

    public void returnToGame() {
        pause = false;
        returnToGame = false;
    }
}
