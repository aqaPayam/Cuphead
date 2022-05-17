package com.example.cuphead2.SceneControllers;

import com.example.cuphead2.Models.*;
import javafx.animation.*;

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class GameSceneController implements Initializable {

    private final BooleanProperty wPressed = new SimpleBooleanProperty();
    private final BooleanProperty aPressed = new SimpleBooleanProperty();
    private final BooleanProperty sPressed = new SimpleBooleanProperty();
    private final BooleanProperty dPressed = new SimpleBooleanProperty();

    private final BooleanProperty shiftPressed = new SimpleBooleanProperty();

    private final BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed).or(shiftPressed);
    @FXML
    public Button button;

    private final Plane plane = Plane.getInstance();

    private final BossFight bossFight = BossFight.getInstance();

    private final ArrayList<Bullet> planeBullet = Bullet.getBulletArray();

    @FXML
    private AnchorPane pane;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            if (shiftPressed.get()) {
                fire();
            }

            if (wPressed.get() && !plane.hitTopWall()) {
                plane.goUp();
            }

            if (sPressed.get() && !plane.hitFloor()) {
                plane.goDown();
            }

            if (aPressed.get() && !plane.hitLeftWall()) {
                plane.goLeft();
            }

            if (dPressed.get() && !plane.hitRightWall()) {
                plane.goRight();
            }
        }
    };

    AnimationTimer collisionTimer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (plane.hasCollision(bossFight))
                System.out.println("kir");
            for (int i = 0; i < planeBullet.size(); i++) {
                Bullet bullet = planeBullet.get(i);
                if (bullet.hasCollision(bossFight)) {
                    Bullet.getBulletArray().remove(bullet);
                    pane.getChildren().remove(bullet);
                    i--;
                    continue;
                }
                if (bullet.Out()) {
                    planeBullet.remove(bullet);
                    pane.getChildren().remove(bullet);
                }
            }
            for (int i = 0; i < Egg.getEggArray().size(); i++) {
                Egg bullet = Egg.getEggArray().get(i);
                if (bullet.hasCollision(plane)) {
                    Egg.getEggArray().remove(bullet);
                    pane.getChildren().remove(bullet);
                    i--;
                    continue;
                }
                if (bullet.Out()) {
                    Egg.getEggArray().remove(bullet);
                    pane.getChildren().remove(bullet);
                }
            }
        }
    };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.getChildren().add(plane);
        pane.getChildren().add(bossFight);
        generateEggSet();
        movementSetup();
        playBossFightAnimation();
        collisionTimer.start();
        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if (!aBoolean) {
                timer.start();
            } else {
                timer.stop();
            }
        }));
    }

    public void movementSetup() {
        pane.setOnKeyPressed(e -> {
            System.out.println(e.getCode());
            if (e.getCode() == KeyCode.SHIFT) {
                shiftPressed.set(true);
            }
            if (e.getCode() == KeyCode.W) {
                wPressed.set(true);
            }

            if (e.getCode() == KeyCode.A) {
                aPressed.set(true);
            }

            if (e.getCode() == KeyCode.S) {
                sPressed.set(true);
            }

            if (e.getCode() == KeyCode.D) {
                dPressed.set(true);
            }
        });

        pane.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SHIFT) {
                shiftPressed.set(false);
            }
            if (e.getCode() == KeyCode.W) {
                wPressed.set(false);
            }

            if (e.getCode() == KeyCode.A) {
                aPressed.set(false);
            }

            if (e.getCode() == KeyCode.S) {
                sPressed.set(false);
            }

            if (e.getCode() == KeyCode.D) {
                dPressed.set(false);
            }
        });
    }

    public void fire() {
        Bullet bullet = new Bullet();
        Bullet.getBulletArray().add(bullet);
        pane.getChildren().add(bullet);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.7));
        transition.setNode(bullet);
        transition.setByX(2000);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();
    }

    private void playBossFightAnimation() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
        transition.setNode(bossFight);
        transition.setByY(600);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setCycleCount(Animation.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();
    }


    private void generateEggSet() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    fireEgg();
                });
            }
        }, 200, 600);
    }

    private void fireEgg() {
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
        System.out.println(pane.getChildren().size());
    }
}


