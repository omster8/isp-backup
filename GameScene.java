package Main;

import javafx.scene.Group;
import javafx.scene.Scene;
import org.ietf.jgss.GSSManager;

public class GameScene extends Scene {
    private static GameCanvas gameCanvas;
    private static Group gameGroup = new Group();

    public GameScene(int windowWidth, int windowHeight) {
        super(gameGroup, windowWidth, windowHeight);
        gameCanvas = new GameCanvas(windowWidth, windowHeight);
        gameGroup.getChildren().add(gameCanvas);

        new GameLoop(gameCanvas);

        this.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ESCAPE:
                    if (!gameCanvas.pause) {
                        gameCanvas.pause = true;
                        gameCanvas.pause();
                    } else {
                        gameCanvas.returnToGame = true;
                        gameCanvas.returnToGame();
                    }
                    break;
                case SPACE:
                    if (!GameLoop.respawning && gameCanvas.player.playerPlatformStatus(gameCanvas.platforms)[0] == 0) {
                        GameLoop.jump = true;
                    }
                    break;
            }
        });
    }
}
