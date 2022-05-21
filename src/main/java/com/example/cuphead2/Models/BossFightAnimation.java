package com.example.cuphead2.Models;

import com.example.cuphead2.Main;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class BossFightAnimation {
    private static BossFightAnimation instance;

    public static BossFightAnimation getInstance() {
        if (instance == null)
            instance = new BossFightAnimation();
        return instance;
    }

    public void BossFightAnimationTimerSet() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (BossFight.getInstance().getHealth() <= 2500 ) {
                    enterDevilMode();
                    stop();
                }
            }
        };
        timer.start();
    }

    private BossFightAnimation() {
    }

    public void playBossFightAnimation() {
        BossFight bossFight = BossFight.getInstance();
        Pane pane = (Pane) bossFight.getParent();
        ImageView imageView = new ImageView();
        imageView.setLayoutX(BossFight.getInstance().getLayoutX() - 150);
        imageView.setLayoutY(BossFight.getInstance().getLayoutY() + 100);
        pane.getChildren().add(imageView);
        TranslateTransition transition5 = new TranslateTransition(Duration.seconds(1));
        transition5.setNode(imageView);
        transition5.setByY(600);
        transition5.setInterpolator(Interpolator.LINEAR);
        transition5.setCycleCount(Animation.INDEFINITE);
        transition5.setAutoReverse(true);
        transition5.play();

        TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
        transition.setNode(bossFight);
        transition.setByY(600);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setCycleCount(Animation.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    String frame = BossFight.getFrameString();
                    if (bossFight.getHealth() > 2500) {
                        BossFight.getInstance().setImage(
                                new Image(Main.class.
                                        getResource("Phase 1/House/bird_idle_house_00" + frame + ".png")
                                        .toExternalForm()));
                        imageView.setImage(new Image(Main.class.
                                getResource("Phase 1/Barf/bird_barf_head_00" + frame + ".png")
                                .toExternalForm()));
                    }
                    if (bossFight.getHealth() <= 2500) {
                        transition5.stop();
                        transition.stop();
                        pane.getChildren().remove(imageView);
                        BossFight.getInstance().setImage(
                                new Image(Main.class.
                                        getResource("devilBoss/egghead_shoot_00" + frame + ".png")
                                        .toExternalForm()));
                        BossFight.getInstance().setFitWidth(200);
                        BossFight.getInstance().setFitHeight(200);
                    }
                });
            }
        }, 200, 50);
    }


    private void enterDevilMode() {
        setRandomTransition();
    }

    private void setRandomTransition() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TranslateTransition transition5 = new TranslateTransition(Duration.seconds(2));
                transition5.setNode(BossFight.getInstance());
                transition5.setByX(-BossFight.getInstance().getTranslateX() - BossFight.getInstance().getLayoutX() +
                        Plane.getInstance().getLayoutX());
                transition5.setByY(-BossFight.getInstance().getTranslateY() - BossFight.getInstance().getLayoutY() +
                        Plane.getInstance().getLayoutY());
                transition5.setCycleCount(1);
                transition5.setInterpolator(Interpolator.LINEAR);
                transition5.play();
            }
        }, 0, 3000);
    }


}
