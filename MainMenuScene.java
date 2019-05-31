package Main;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.util.Duration;

public class MainMenuScene extends Scene {
    private static final MainMenuLayout mainMenuLayout = new MainMenuLayout();

    public MainMenuScene(int windowWidth, int windowHeight, Main app) {
        super(mainMenuLayout, windowWidth, windowHeight);
        mainMenuLayout.setApp(app);
    }

    public void fadeIn() {
        for (Node n : mainMenuLayout.getChildren()) {
            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), n);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.playFromStart();
        }
    }
}
