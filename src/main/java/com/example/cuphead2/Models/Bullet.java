package com.example.cuphead2.Models;

import com.almasb.fxgl.core.collection.Array;
import com.example.cuphead2.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Bullet extends ImageView {
    private final int movementVariable = 3;
    private static final ArrayList<Bullet> bulletArray = new ArrayList<>();

    public static ArrayList<Bullet> getBulletArray() {
        return bulletArray;
    }

    public Bullet() {
        super();
        super.setImage(new Image(Main.class.getResource("Bullet.png").toExternalForm()));
        super.setFitHeight(60);
        super.setFitWidth(60);
        super.setLayoutX(Plane.getInstance().getLayoutX() + Plane.getInstance().getFitWidth() - 20);
        super.setLayoutY(Plane.getInstance().getLayoutY() + Plane.getInstance().getFitHeight() / 2 - 20);
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

    public boolean Out() {
        return hitFloor() || hitLeftWall() || hitTopWall() || hitRightWall();
    }


    public void goUp() {
        this.setLayoutY(this.getLayoutY() - movementVariable);
    }

    public void goDown() {
        this.setLayoutY(this.getLayoutY() + movementVariable);
    }


    public void goLeft() {
        this.setLayoutX(this.getLayoutX() - movementVariable);
    }

    public void goRight() {
        this.setLayoutX(this.getLayoutX() + movementVariable);
    }
}
