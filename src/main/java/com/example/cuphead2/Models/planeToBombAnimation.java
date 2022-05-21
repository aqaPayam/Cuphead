package com.example.cuphead2.Models;

import com.example.cuphead2.Main;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class planeToBombAnimation {


    private int f = 0;
    private int ff = 0;

    public void run(Plane plane, BossFight bossFight, AnimationTimer collisionTimer) {
        collisionTimer.stop();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                plane.setOpacity(1);
                if (plane.hasCollision(bossFight)) {
                    boom((Pane) plane.getParent());
                    bossFight.setHealth(bossFight.getHealth() - 500);
                    plane.setImage(new Image(Main.class.getResource("Cuphead.png").toExternalForm()));
                    plane.setTranslateX(0);
                    plane.setLayoutX(227);
                    plane.setLayoutY(113);
                    collisionTimer.start();
                    stop();
                }
            }
        };

        animationTimer.start();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
        transition.setNode(plane);
        System.out.println(bossFight.getLayoutX() - plane.getLayoutX());
        transition.setByX(bossFight.getLayoutX() - plane.getLayoutX());
        transition.setInterpolator(Interpolator.LINEAR);
        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                transition.play();
            }
        }, 100);
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                plane.setImage(new Image(Main.class.getResource("Cuphead.png").toExternalForm()));
                plane.setTranslateX(0);
                plane.setLayoutX(227);
                plane.setLayoutY(113);
                collisionTimer.start();
                animationTimer.stop();
            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    String frame;
                    f++;
                    if (f > 9)
                        frame = String.valueOf(f);
                    else
                        frame = "0" + f;
                    if (f >= 17) {
                        timer.cancel();
                        timer.purge();
                    }
                    Plane.getInstance().setImage(
                            new Image(Main.class.
                                    getResource("bomb/mm_shmup_super_intro_00" + frame + ".png")
                                    .toExternalForm()));
                });
            }
        }, 200, 50);

    }

    private void boom(Pane pane) {
        ImageView imageView = new ImageView();
        imageView.setLayoutX(BossFight.getInstance().getLayoutX()+BossFight.getInstance().getTranslateX());
        imageView.setLayoutY(BossFight.getInstance().getLayoutY()+BossFight.getInstance().getTranslateY());
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        pane.getChildren().add(imageView);
        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    String frame;
                    ff++;
                    if (ff >= 6) {
                        ff = 6;
                        timer1.cancel();
                        timer1.purge();
                        pane.getChildren().remove(imageView);
                    }
                    frame = String.valueOf(ff);
                    imageView.setImage(
                            new Image(Main.class.
                                    getResource("explosion/" + frame + ".png")
                                    .toExternalForm()));
                });
            }
        }, 0, 500);

    }
}
