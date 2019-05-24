package Main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Main extends Application {
    private boolean jump = false;
    private boolean pause = false;
    private boolean returnToGame = false;
    private boolean respawn = false;
    private Group root = new Group();
    private Canvas canvas = new Canvas(800, 600);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private static Image image;
    private static ImageView imageView;
    private double[] xVals = {435, 455, 445};
    private double[] yVals = {600, 600, 570};
    private double[] xVals1 = {455, 475, 465};
    private double[] xVals2 = {475, 495, 485};
    private double[] xVals3 = {365, 375, 370};
    private double[] yVals3 = {530, 530, 520};
    private double[] xVals4 = {460, 470, 465};
    private double[] yVals4 = {470, 470, 460};
    private double[] xVals5 = {555, 565, 560};
    private double[] yVals5 = {410, 410, 400};
    private double[] xVals6 = {265, 275, 270};
    private double[] yVals6 = {575, 575, 565};

    public static void main(String[] args) {
        instantiateImages();
        launch(args);
    }

    private static void instantiateImages() {
        try {
            image = new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\rotating_blades\\blade_1.png"));
        } catch (Exception e) {}
        imageView = new ImageView(image);
    }

    private void drawWorld() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800, 600);
        gc.setFill(Color.RED);
        gc.fillRect(200, 575, 75, 25);
        gc.fillRect(295, 530, 80, 5);
        gc.fillRect(390, 470, 80, 5);
        gc.fillRect(485, 410, 80, 5);
        gc.fillPolygon(xVals, yVals, 3);
        gc.fillPolygon(xVals1, yVals, 3);
        gc.fillPolygon(xVals2, yVals, 3);
        gc.fillPolygon(xVals3, yVals3, 3);
        gc.fillPolygon(xVals4, yVals4, 3);
        gc.fillPolygon(xVals5, yVals5, 3);
        gc.fillPolygon(xVals6, yVals6, 3);
        imageView.setX(175);
        imageView.setY(575);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
    }

    public void start(Stage stage) {
        Scene scene = new Scene(root);
        ArrayList<Integer[]> platforms = new ArrayList<Integer[]>(); //format: y, start x, end x
        ArrayList<Integer[]> obstacles = new ArrayList<Integer[]>(); //format: start x, start y, end x, end y
        Sprite p = new Sprite();

        p.setPos(50, 500);
        p.render(gc);

        root.getChildren().add(canvas);
        root.getChildren().add(imageView);

        drawWorld();

        stage.setTitle("Friend Racer");
        stage.setScene(scene);

        platforms.add(new Integer[]{575, 200, 275});
        platforms.add(new Integer[]{530, 295, 375});
        platforms.add(new Integer[]{470, 390, 470});
        platforms.add(new Integer[]{410, 485, 565});
        platforms.add(new Integer[]{601, 0, 199}); // the ground
        platforms.add(new Integer[]{601, 276, 800}); // the ground

        obstacles.add(new Integer[]{435, 570, 495, 600});
        obstacles.add(new Integer[]{365, 520, 375, 530});
        obstacles.add(new Integer[]{460, 460, 470, 470});
        obstacles.add(new Integer[]{555, 400, 565, 410});
        obstacles.add(new Integer[]{265, 565, 275, 575});
        obstacles.add(new Integer[]{175, 575, 200, 600});

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case R:
                    returnToGame = true;
                    break;
                case I:
                    pause = true;
                    break;
                case SPACE:
                    if (!respawn) {
                        jump = true;
                    }
                    break;
            }
        });

        stage.show();

        new AnimationTimer() {
            long startTime = -1;
            long startGravityTime = -1;
            long startRespawnDelay = -1;
            long startReturnCountdown = -1;

            public int[] playerPlatformStatus() { // 0: on platform, -1: above platform, 1: below platform
                for (int i = 0; i < platforms.size(); i++) {
                    Integer[] platform = platforms.get(i);
                    if (p.getPos().y + p.getHeight() + 1 == platform[0] && (p.getPos().x + p.getWidth() >= platform[1] && p.getPos().x <= platform[2]))
                        return new int[]{0, i};
                    if (p.getPos().y + p.getHeight() >= platform[0]  && (p.getPos().x + p.getWidth() >= platform[1] && p.getPos().x <= platform[2]))
                        return new int[]{1, i};
                }
                return new int[]{-1, -1};
            }

            public boolean hitObstacle() {
                for (Integer[] obstacle : obstacles) {
                    if (((obstacle[0] > p.getPos().x && obstacle[0] < p.getPos().x + p.getWidth()) || (obstacle[2] > p.getPos().x && obstacle[2] < p.getPos().x + p.getWidth())) && ((obstacle[1] > p.getPos().y && obstacle[1] < p.getPos().y + p.getHeight()) || (obstacle[3] > p.getPos().y && obstacle[3] < p.getPos().y + p.getHeight())))
                        return true;
                }
                return false;
            }

            public void respawn (double time) {
                p.setPos(50, 500);
                p.setVel(p.getVel().x, 0);
                if (time <= 0.25 || (time > 0.5 && time <= 0.75) || (time > 1 && time <= 1.25) || (time > 1.5 && time <= 1.75)) {
                    p.render(gc);
                } else {
                    p.erase(gc);
                }
            }

            public void pause() {
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, 800, 600);
                gc.setFill(Color.GREEN);
                gc.fillText("GAME IS PAUSED, PRESS \'r\' TO CONTINUE", 200, 200);
                gc.setFill(Color.RED);
                imageView.setImage(null);
            }

            @Override
            public void handle(long currentTime) {
                if (!pause && !respawn) {
                    imageView.setRotate(imageView.getRotate() + 4);
                }
                if (pause) {
                    pause();
                }
                if (returnToGame) {
                    if (startReturnCountdown == -1) {
                        startReturnCountdown = currentTime;
                    }
                    imageView.setImage(image);
                    drawWorld();
                    p.render(gc);
                    gc.setFill(Color.GREEN);
                    if ((currentTime-startReturnCountdown)/1000000000.0 <= 1) {
                        gc.fillText("3", 50, 50);
                    } else if ((currentTime-startReturnCountdown)/1000000000.0 <= 2) {
                        gc.fillText("2", 50, 50);
                    } else if ((currentTime-startReturnCountdown)/1000000000.0 <= 3) {
                        gc.fillText("1", 50, 50);
                    } else {
                        pause = false;
                        returnToGame = false;
                        startReturnCountdown = -1;
                    }
                }
                if (!pause) {
                    if (respawn && (currentTime-startRespawnDelay)/1000000000.0 <= 2) {
                        respawn((currentTime-startRespawnDelay)/1000000000.0);
                    } else {
                        if (respawn) {
                            respawn = false;
                        }
                        if (startTime == -1)
                            startTime = currentTime;
                        p.erase(gc);
                        if (jump && playerPlatformStatus()[0] == -1) {
                            jump = false;
                        }
                        if (jump && playerPlatformStatus()[0] == 0) {
                            p.addVel(0, -15);
                            jump = false;
                            startGravityTime = currentTime;
                        }
                        if (startTime == currentTime) {
                            p.addVel(3, 0);
                        }
                        if (playerPlatformStatus()[0] < 0) {
                            p.addVel(0, (int) Math.round(9.8 * ((currentTime - startGravityTime) / 1000000000.0)));
                        }
                        p.update();
                        if (playerPlatformStatus()[0] == 1) {
                            p.setPos(p.getPos().x, platforms.get(playerPlatformStatus()[1])[0] - p.getHeight() - 1);
                            p.setVel(p.getVel().x, 0);
                            drawWorld();
                        }
                        p.render(gc);
                        if (hitObstacle()) {
                            respawn = true;
                            startRespawnDelay = currentTime;
                            p.erase(gc);
                            drawWorld();
                        }
                    }
                }
            }
        }.start();
    }
}
