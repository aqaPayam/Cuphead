package com.example.cuphead2.SceneControllers;

import com.example.cuphead2.Models.BossAnimation;
import com.example.cuphead2.Models.BossFight;
import com.example.cuphead2.Models.Plane;
import javafx.animation.*;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameSceneController implements Initializable {

    private final BooleanProperty wPressed = new SimpleBooleanProperty();
    private final BooleanProperty aPressed = new SimpleBooleanProperty();
    private final BooleanProperty sPressed = new SimpleBooleanProperty();
    private final BooleanProperty dPressed = new SimpleBooleanProperty();

    private final BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);
    @FXML
    public Button button;

    private final Plane plane = Plane.getInstance();

    private final BossFight bossFight = BossFight.getInstance();

    @FXML
    private AnchorPane pane;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            int movementVariable = 1;
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
        }
    };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.getChildren().add(plane);
        pane.getChildren().add(bossFight);
        movementSetup();
        BossAnimation bossAnimation = new BossAnimation(plane, bossFight);
        bossAnimation.play();
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

    public void start(ActionEvent event) {
        plane.setLayoutX(0);
        plane.setLayoutY(0);
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
}


