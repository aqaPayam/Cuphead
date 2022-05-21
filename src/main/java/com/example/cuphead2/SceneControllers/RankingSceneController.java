package com.example.cuphead2.SceneControllers;

import com.example.cuphead2.Main;
import com.example.cuphead2.Models.UserDatabase;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class RankingSceneController implements Initializable {

    @FXML
    private ScrollPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startBackgroundAnimation();
    }

    public void startBackgroundAnimation() {
        DoubleProperty xPosition = new SimpleDoubleProperty(0);
        xPosition.addListener((observable, oldValue, newValue) -> setBackgroundPositions(pane, xPosition.get()));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(xPosition, 0)),
                new KeyFrame(Duration.seconds(100), new KeyValue(xPosition, -15000))
        );
        timeline.play();
    }

    void setBackgroundPositions(Region region, double xPosition) {
        String style = "-fx-background-position: " +
                "left " + xPosition / 6 + "px bottom," +
                "left " + xPosition / 5 + "px bottom," +
                "left " + xPosition / 4 + "px bottom," +
                "left " + xPosition / 3 + "px bottom," +
                "left " + xPosition / 2 + "px bottom," +
                "left " + xPosition + "px bottom;";

        region.setStyle(style);
    }

}
