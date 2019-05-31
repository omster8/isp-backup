package Main;

import javafx.scene.Scene;

public class IntroScene extends Scene {
    private static final IntroLayout introLayout = new IntroLayout();

    public IntroScene(int windowWidth, int windowHeight, Main app) {
        super(introLayout, windowWidth, windowHeight);
        introLayout.setApp(app);
    }
}
