package com.example.cuphead2.Models;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

//background from http://edencoding.com/scene-background/#images
public class BackgroundController {
    private static BackgroundController instance;
    Pane pane;

    public static BackgroundController getInstance() {
        if (instance == null)
            instance = new BackgroundController();
        return instance;
    }

    private BackgroundController() {
        pane = (Pane) Plane.getInstance().getParent();
        System.out.println(pane);
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

    private void setBackgroundPositions(Region region, double xPosition) {
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
