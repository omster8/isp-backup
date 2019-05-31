package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MainMenuLayout extends StackPane /*VBox*/ {
    private Main app;

    public MainMenuLayout() {
        VBox menuBtns = new VBox(10);
        Button startBtn = new Button("Start");
        Button instructionsBtn = new Button("How to Play");
        Button settingsBtn = new Button("Settings");
        Button exitBtn = new Button("Exit");
        startBtn.setSkin(new MainMenuButtonSkin(startBtn));
        instructionsBtn.setSkin(new MainMenuButtonSkin(instructionsBtn));
        settingsBtn.setSkin(new MainMenuButtonSkin(settingsBtn));
        exitBtn.setSkin(new MainMenuButtonSkin(exitBtn));

        StackPane title = new StackPane();

        ImageView titleImg = new ImageView("game_logo.png");
        titleImg.setPreserveRatio(true);
        titleImg.setFitWidth(700);

        title.setPadding(new Insets(20,10,10,10));
        title.getChildren().add(titleImg);
        title.setAlignment(Pos.TOP_CENTER);

        // Event Listeners //
        startBtn.setOnAction(e -> app.setGameScene());
        exitBtn.setOnAction(e -> app.stage.close());

        menuBtns.setAlignment(Pos.CENTER_LEFT);
        menuBtns.getChildren().addAll(startBtn,instructionsBtn,settingsBtn,exitBtn);
        this.getChildren().addAll(title, menuBtns);

        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        for (Node n : this.getChildren()) {
            n.setOpacity(0);
        }
    }

    public void setApp(Main app) {
        this.app = app;
    }
}
