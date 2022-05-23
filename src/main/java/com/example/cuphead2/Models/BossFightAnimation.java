package com.example.cuphead2.Models;

import com.example.cuphead2.Main;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BossFightAnimation {
    private static BossFightAnimation instance;

    public static BossFightAnimation getInstance() {
        if (instance == null)
            instance = new BossFightAnimation();
        return instance;
    }

    public final ArrayList<Egg> eggs = new ArrayList<>();
    private final ArrayList<TranslateTransition> eggTransition = new ArrayList<>();

    public void BossFightAnimationTimerSet() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (BossFight.getInstance().getHealth() <= 2500) {
                    enterDevilMode();
                    EggController.getInstance().timer.cancel();
                    EggController.getInstance().timer.purge();
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

    private void addEggs() {
        for (int i = 0; i < 4; i++) {
            Egg imageView = new Egg();
            imageView.setImage(new Image(Main.class.
                    getResource("egg.png")
                    .toExternalForm()));
            imageView.setFitHeight(83);
            imageView.setFitWidth(64);
            TranslateTransition transition5 = new TranslateTransition(Duration.seconds(1));
            transition5.setNode(imageView);
            transition5.setInterpolator(Interpolator.LINEAR);
            transition5.setCycleCount(Animation.INDEFINITE);
            transition5.setAutoReverse(true);

            switch (i) {
                case 0 -> {
                    imageView.setLayoutX(BossFight.getInstance().getLayoutX() - 70);
                    imageView.setLayoutY(BossFight.getInstance().getLayoutY()
                            + BossFight.getInstance().getTranslateY() + 50);
                    transition5.setByX(-200);
                }
                case 1 -> {
                    imageView.setLayoutX(BossFight.getInstance().getLayoutX() + 220);
                    imageView.setLayoutY(BossFight.getInstance().getLayoutY()
                            + BossFight.getInstance().getTranslateY() + 50);
                    transition5.setByX(200);
                }
                case 2 -> {
                    imageView.setLayoutX(BossFight.getInstance().getLayoutX() + 90);
                    imageView.setLayoutY(BossFight.getInstance().getLayoutY() - 80
                            + BossFight.getInstance().getTranslateY());
                    transition5.setByY(-200);
                }
                case 3 -> {
                    imageView.setLayoutX(BossFight.getInstance().getLayoutX() + 90);
                    imageView.setLayoutY(BossFight.getInstance().getLayoutY() + 200
                            + BossFight.getInstance().getTranslateY());
                    transition5.setByY(200);
                }
            }
            transition5.play();
            ((Pane) BossFight.getInstance().getParent()).getChildren().add(imageView);
            RotateTransition rotateTransition = new RotateTransition();
            rotateTransition.setNode(imageView);
            rotateTransition.setByAngle(360);
            rotateTransition.setInterpolator(Interpolator.LINEAR);
            rotateTransition.setCycleCount(Animation.INDEFINITE);
            rotateTransition.play();
            eggs.add(imageView);
            eggTransition.add(transition5);
        }

    }

    private void enterDevilMode() {
        addEggs();
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
                for (int i = 0; i < 4; i++) {
                    ImageView egg=eggs.get(i);
                    eggTransition.get(i).stop();
                    TranslateTransition transition = new TranslateTransition(Duration.seconds(2));
                    transition.setNode(egg);
                    transition.setByX(-BossFight.getInstance().getTranslateX() - BossFight.getInstance().getLayoutX() +
                            Plane.getInstance().getLayoutX());
                    transition.setByY(-BossFight.getInstance().getTranslateY() - BossFight.getInstance().getLayoutY() +
                            Plane.getInstance().getLayoutY());
                    transition.setCycleCount(1);
                    transition.setInterpolator(Interpolator.LINEAR);
                    int finalI = i;
                    transition.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            eggTransition.get(finalI).play();
                        }
                    });
                    transition.play();
                }
            }
        }, 0, 6000);
    }


}
