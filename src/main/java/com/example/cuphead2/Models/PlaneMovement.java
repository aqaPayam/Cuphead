package com.example.cuphead2.Models;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.Timer;
import java.util.TimerTask;

public class PlaneMovement {
    private static PlaneMovement instance;

    public static PlaneMovement getInstance() {
        if (instance == null)
            instance = new PlaneMovement();
        return instance;
    }

    private PlaneMovement() {
    }

    private final BooleanProperty wPressed = new SimpleBooleanProperty();
    private final BooleanProperty aPressed = new SimpleBooleanProperty();
    private final BooleanProperty sPressed = new SimpleBooleanProperty();
    private final BooleanProperty dPressed = new SimpleBooleanProperty();

    private final BooleanProperty shiftPressed = new SimpleBooleanProperty();

    private final BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed).or(shiftPressed);


    public void run() {
        movementSetup();
        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if (!aBoolean) {
                timer.start();
            } else {
                timer.stop();
            }
        }));
    }

    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            if (wPressed.get() && ! Plane.getInstance().hitTopWall()) {
                Plane.getInstance().goUp();
            }

            if (sPressed.get() && ! Plane.getInstance().hitFloor()) {
                 Plane.getInstance().goDown();
            }

            if (aPressed.get() && ! Plane.getInstance().hitLeftWall()) {
                 Plane.getInstance().goLeft();
            }

            if (dPressed.get() && ! Plane.getInstance().hitRightWall()) {
                 Plane.getInstance().goRight();
            }
        }
    };


    private void movementSetup() {
        Pane pane= (Pane) Plane.getInstance().getParent();
        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SHIFT) {

                Timer timer = Bullet.getTimeline();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            Plane.getInstance().fire();
                        });
                    }
                }, 0, 100);
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
                Bullet.getTimeline().cancel();
                Bullet.setTimeline(new Timer());
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


}
