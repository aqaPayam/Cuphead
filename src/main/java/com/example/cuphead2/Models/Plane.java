package com.example.cuphead2.Models;

import com.example.cuphead2.Main;
import com.example.cuphead2.SceneControllers.GameSceneController;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Plane extends ImageView {
    private final int movementVariable = 20;
    private static Plane instance;
    private int health = 10;
    private boolean inInjured = false;
    public static ArrayList<ImageView> hearts;

    public static Plane getInstance() {
        if (instance == null)
            instance = new Plane();
        return instance;
    }

    private Plane() {
        super();
        super.setImage(new Image(Main.class.getResource("Cuphead.png").toExternalForm()));
        super.setFitHeight(120);
        super.setFitWidth(160);
        super.setLayoutX(227);
        super.setLayoutY(113);
        // super.setPreserveRatio(true);
        // super.setPickOnBounds(true);
    }

    public void move(double dx, double dy) {
        this.setLayoutX(this.getLayoutX() + dx);
        this.setLayoutY(this.getLayoutY() + dy);
    }

    public boolean hasCollision(ImageView block) {
        return block.getBoundsInParent().intersects(this.getBoundsInParent());
    }

    public boolean hitTopWall() {
        return this.getLayoutY() <= 0;
    }

    public boolean hitLeftWall() {
        return this.getLayoutX() <= 0;
    }

    public boolean hitRightWall() {
        return this.getLayoutX() + this.getFitWidth() >= this.getScene().getWindow().getWidth();
    }

    public boolean hitFloor() {
        return this.getLayoutY() + this.getFitHeight() >= this.getScene().getWindow().getHeight();
    }


    public void planeInjured() {
        Plane plane = this;
        Pane pane = (Pane) plane.getParent();

        int seconds = 4000;
        if (plane.isInInjured())
            return;
        plane.setHealth(plane.getHealth() - 1);
        pane.getChildren().remove(hearts.get(plane.getHealth()));
        plane.setInInjured(true);
        plane.setOpacity(0.4);
        Timer timer = new Timer();
        for (int i = 1; i <= 7; i++) {
            int finalI = i;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (finalI % 2 == 1)
                        plane.setOpacity(0.2);
                    else
                        plane.setOpacity(1);
                }
            }, seconds / 8 * i);
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                plane.setInInjured(false);
                plane.setOpacity(1);
            }
        }, seconds);
    }


    public void fire() {
        Pane pane = (Pane) this.getParent();
        Bullet bullet = new Bullet();
        Bullet.getBulletArray().add(bullet);
        pane.getChildren().add(bullet);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
        transition.setNode(bullet);
        transition.setByX(2000);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();
        Music.getInstance().playGunMusic();
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().remove(bullet);
            }
        });
    }


    public void goUp() {
        instance.setLayoutY(instance.getLayoutY() - movementVariable);
    }

    public void goDown() {
        instance.setLayoutY(instance.getLayoutY() + movementVariable);
    }


    public void goLeft() {
        instance.setLayoutX(instance.getLayoutX() - movementVariable);
    }

    public void goRight() {
        instance.setLayoutX(instance.getLayoutX() + movementVariable);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isInInjured() {
        return inInjured;
    }

    public void setInInjured(boolean inInjured) {
        this.inInjured = inInjured;
    }
}
