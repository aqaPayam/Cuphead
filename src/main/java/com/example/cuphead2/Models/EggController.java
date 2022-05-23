package com.example.cuphead2.Models;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class EggController {
    private static EggController instance;

    public static EggController getInstance() {
        if (instance == null)
            instance = new EggController();
        return instance;
    }

    public Timer timer = new Timer();

    private EggController() {
    }

    public void generateEggSet() {

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    fireEgg();
                });
            }
        }, 0, 800);
    }

    private void fireEgg() {
        Pane pane = (Pane) BossFight.getInstance().getParent();
        Egg egg = new Egg();
        Egg.getEggArray().add(egg);
        pane.getChildren().add(egg);
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(egg);
        rotateTransition.setByAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.play();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(5));
        transition.setNode(egg);
        transition.setByX(-2000);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();
        Music.getInstance().playOughtMusic();
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Egg.getEggArray().remove(egg);
                pane.getChildren().remove(egg);
            }
        });
    }


}
