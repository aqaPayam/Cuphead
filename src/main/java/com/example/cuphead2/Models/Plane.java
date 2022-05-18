package com.example.cuphead2.Models;

import com.example.cuphead2.Main;
import com.example.cuphead2.SceneControllers.GameSceneController;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class Plane extends ImageView {
    private final int movementVariable =8;
    private static Plane instance;
    private int health=10;

    public static Plane getInstance() {
        if (instance == null)
            instance = new Plane();
        return instance;
    }

    private Plane() {
        super();
        super.setImage(new Image(Main.class.getResource("Cuphead.png").toExternalForm()));
        super.setFitHeight(150);
        super.setFitWidth(200);
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
}
