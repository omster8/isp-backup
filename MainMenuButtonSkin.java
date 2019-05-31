package Main;

import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.util.Duration;
import javafx.scene.text.Font;

import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class MainMenuButtonSkin extends ButtonSkin {
    public MainMenuButtonSkin(Button btn) {
        super(btn);

        // Styling //
        btn.setStyle("" +
                "-fx-background-color: rgba(0,0,0,0);" +
                "-fx-text-fill: #00008B;"
        );

        try {
            Font f = Font.loadFont(new FileInputStream(new File("res/pepsi_font.ttf")), 35);
            btn.setFont(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        btn.setPadding(new Insets(20));

        // Animations //
        btn.setOnMouseEntered(e -> {
            TranslateTransition moveRight = new TranslateTransition(Duration.millis(100), btn);
            moveRight.setByX(20);
            moveRight.playFromStart();
        });
        btn.setOnMouseExited(e -> {
            TranslateTransition moveLeft = new TranslateTransition(Duration.millis(100), btn);
            moveLeft.setByX(-btn.getTranslateX());
            moveLeft.playFromStart();
        });
    }
}
