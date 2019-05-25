package Main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.util.ArrayList;

//TODO: change the little logo on the top left of the run screen to our company logo
//TODO: convert all image backgrounds to transparent (PNG) instead of white
public class Main extends Application {
    private boolean jump = false;
    private boolean pause = false;
    private boolean returnToGame = false;
    private boolean respawn = false;
    private Group root = new Group();
    private Canvas canvas = new Canvas(800, 600);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Sprite player = null;
    private Sprite rotating_blade = null;
    private double[] xVals = {435, 455, 445};
    private double[] yVals = {600, 600, 570};
    private double[] xVals1 = {455, 475, 465};
    private double[] xVals2 = {475, 495, 485};
    private Sprite platform1 = null;
    private Sprite platform2 = null;
    private Sprite platform3 = null;
    private Sprite platform4 = null;
    private Sprite spike1 = null;
    private Sprite spike2 = null;
    private Sprite spike3 = null;
    private Sprite spike4 = null;

    public static void main(String[] args) {
        launch(args);
    }

    private void drawWorld() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800, 600);
        gc.setFill(Color.RED);

        platform1.render(gc);
        platform2.render(gc);
        platform3.render(gc);
        platform4.render(gc);

        spike1.render(gc);
        spike2.render(gc);
        spike3.render(gc);
        spike4.render(gc);

        rotating_blade.render(gc);

        gc.fillPolygon(xVals, yVals, 3);
        gc.fillPolygon(xVals1, yVals, 3);
        gc.fillPolygon(xVals2, yVals, 3);
    }

    public void start(Stage stage) {
        Scene scene = new Scene(root);
        ArrayList<Integer[]> platforms = new ArrayList<Integer[]>(); //format: y, start x, end x
        ArrayList<Integer[]> obstacles = new ArrayList<Integer[]>(); //format: start x, start y, end x, end y
        try {
            player = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\running_man.png")), 40, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\running_man - erase.png")));
            platform1 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform.PNG")), 80, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform - erase.png")));
            platform2 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform.PNG")), 80, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform - erase.PNG")));
            platform3 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform.PNG")), 80, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform - erase.PNG")));
            platform4 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform_1.PNG")), 75, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform_1 - erase.PNG")));
            spike1 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike.png")), 13, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike - erase.png")));
            spike2 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike.png")), 13, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike - erase.png")));
            spike3 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike.png")), 13, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike - erase.png")));
            spike4 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike.png")), 13, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike - erase.png")));
            rotating_blade = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\rotating_blades\\blade_1.png")), 25, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\rotating_blades\\blade_1 - erase.png")));
        } catch (Exception e) {
        }

        player.setPos(20, 601 - player.getHeight());
        player.render(gc);

        platform1.setPos(295, 530);
        platform2.setPos(390, 470);
        platform3.setPos(485, 410);
        platform4.setPos(200, 575);

        spike1.setPos(362, 510);
        spike2.setPos(457, 450);
        spike3.setPos(552, 390);
        spike4.setPos(262, 555);

        rotating_blade.setPos(175, 575);

        root.getChildren().add(canvas);

        drawWorld();

        stage.setTitle("Friend Racer");
        stage.setScene(scene);

        platforms.add(new Integer[]{575, 200, 275});
        platforms.add(new Integer[]{530, 295, 375});
        platforms.add(new Integer[]{470, 390, 470});
        platforms.add(new Integer[]{410, 485, 565});
        platforms.add(new Integer[]{601, 0, 800}); // the ground

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
                    if (player.getPos().y + player.getHeight() + 1 == platform[0] && (player.getPos().x + player.getWidth() >= platform[1] && player.getPos().x <= platform[2]))
                        return new int[]{0, i};
                    if (player.getPos().y + player.getHeight() >= platform[0] && (player.getPos().x + player.getWidth() >= platform[1] && player.getPos().x <= platform[2]))
                        return new int[]{1, i};
                }
                return new int[]{-1, -1};
            }

            public boolean hitObstacle() {
                for (Integer[] obstacle : obstacles) {
                    if (((obstacle[0] > player.getPos().x && obstacle[0] < player.getPos().x + player.getWidth()) || (obstacle[2] > player.getPos().x && obstacle[2] < player.getPos().x + player.getWidth())) && ((obstacle[1] > player.getPos().y && obstacle[1] < player.getPos().y + player.getHeight()) || (obstacle[3] > player.getPos().y && obstacle[3] < player.getPos().y + player.getHeight())))
                        return true;
                }
                return false;
            }

            public void respawn(double time) {
                player.setPos(20, 601 - player.getHeight());
                player.setVel(player.getVel().x, 0);
                if (time <= 0.25 || (time > 0.5 && time <= 0.75) || (time > 1 && time <= 1.25) || (time > 1.5 && time <= 1.75)) {
                    player.render(gc);
                } else {
                    player.erase(gc);
                }
            }

            public void pause() {
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, 800, 600);
                gc.setFill(Color.GREEN);
                gc.fillText("GAME IS PAUSED, PRESS \'r\' TO CONTINUE", 200, 200);
                gc.setFill(Color.RED);
            }

            @Override
            public void handle(long currentTime) {
                if (!pause && !respawn) {
                    rotating_blade.rotateImage(gc, 4);
                }
                if (pause) {
                    pause();
                }
                if (returnToGame) {
                    if (startReturnCountdown == -1) {
                        startReturnCountdown = currentTime;
                    }
                    drawWorld();
                    player.render(gc);
                    gc.setFill(Color.GREEN);
                    if ((currentTime - startReturnCountdown) / 1000000000.0 <= 1) {
                        gc.fillText("3", 50, 50);
                    } else if ((currentTime - startReturnCountdown) / 1000000000.0 <= 2) {
                        gc.fillText("2", 50, 50);
                    } else if ((currentTime - startReturnCountdown) / 1000000000.0 <= 3) {
                        gc.fillText("1", 50, 50);
                    } else {
                        pause = false;
                        returnToGame = false;
                        startReturnCountdown = -1;
                    }
                }
                if (!pause) {
                    if (respawn && (currentTime - startRespawnDelay) / 1000000000.0 <= 2) {
                        respawn((currentTime - startRespawnDelay) / 1000000000.0);
                    } else {
                        if (respawn) {
                            respawn = false;
                        }
                        if (startTime == -1)
                            startTime = currentTime;
                        player.erase(gc);
                        if (jump && playerPlatformStatus()[0] == -1) {
                            jump = false;
                        }
                        if (jump && playerPlatformStatus()[0] == 0) {
                            player.addVel(0, -15);
                            jump = false;
                            startGravityTime = currentTime;
                        }
                        if (startTime == currentTime) {
                            player.addVel(3, 0);
                        }
                        if (playerPlatformStatus()[0] < 0) {
                            player.addVel(0, (int) Math.round(9.8 * ((currentTime - startGravityTime) / 1000000000.0)));
                        }
                        player.update();
                        if (playerPlatformStatus()[0] == 1) {
                            player.setPos(player.getPos().x, platforms.get(playerPlatformStatus()[1])[0] - player.getHeight() - 1);
                            player.setVel(player.getVel().x, 0);
                            drawWorld();
                        }
                        player.render(gc);
                        if (hitObstacle()) {
                            respawn = true;
                            startRespawnDelay = currentTime;
                            player.erase(gc);
                            drawWorld();
                        }
                    }
                }
            }
        }.start();
    }
}
