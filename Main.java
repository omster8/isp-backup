package Main;

import javafx.application.Application;
import javafx.stage.Stage;

//TODO: change the little logo on the top left of the run screen to our company logo
//TODO: convert all image backgrounds to transparent (PNG) instead of white
//TODO: make a legit ground that's a sprite
public class Main extends Application {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    public Stage stage;
    private MainMenuScene mainMenuScene;

    public void start(Stage stage) {
        mainMenuScene = new MainMenuScene(WINDOW_WIDTH, WINDOW_HEIGHT, this);
        IntroScene introScene = new IntroScene(WINDOW_WIDTH, WINDOW_HEIGHT, this);

        this.stage = stage;

        stage.setTitle("Friend Racer");
        //setMainMenuScene();
        stage.setScene(introScene);
        stage.show();
    }

    public void setGameScene() {
        GameScene gameScene = new GameScene(WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(gameScene);
    }

    public void setMainMenuScene() {
        stage.setScene(mainMenuScene);
        mainMenuScene.fadeIn();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
