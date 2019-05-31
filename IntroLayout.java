package Main;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IntroLayout extends StackPane {
    private ImageView companyLogo;
    private ImageView gameLogo;
    private Main app;

    public IntroLayout() {
        loadResources();

        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        companyLogo.setPreserveRatio(true);
        companyLogo.setFitWidth(400);
        gameLogo.setPreserveRatio(true);
        gameLogo.setFitWidth(750);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(companyLogo, gameLogo);
        companyLogo.setOpacity(0);
        gameLogo.setOpacity(0);

        FadeTransition fade = new FadeTransition(Duration.millis(2000), companyLogo);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setAutoReverse(true);
        fade.setCycleCount(2);
        FadeTransition fade2 = new FadeTransition(Duration.millis(2000), gameLogo);
        fade2.setFromValue(0);
        fade2.setToValue(1);
        fade2.setAutoReverse(true);
        fade2.setCycleCount(2);

        fade.playFromStart();
        fade.setOnFinished(e -> fade2.playFromStart());
        fade2.setOnFinished(e -> app.setMainMenuScene());
    }

    public void setApp(Main app) {
        this.app = app;
    }

    private void loadResources() {
        try {
            companyLogo = new ImageView(new Image(new FileInputStream(new File("res/company_logo.png"))));
            gameLogo = new ImageView(new Image(new FileInputStream(new File("res/game_logo.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
