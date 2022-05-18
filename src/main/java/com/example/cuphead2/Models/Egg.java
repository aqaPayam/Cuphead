package com.example.cuphead2.Models;

import com.example.cuphead2.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Egg extends ImageView {
    private final int movementVariable = 3;
    private static final ArrayList<Egg> eggArray = new ArrayList<>();

    public static ArrayList<Egg> getEggArray() {
        return eggArray;
    }

    public Egg() {
        super();
        super.setImage(new Image(Main.class.getResource("egg.png").toExternalForm()));
        super.setFitHeight(100);
        super.setFitWidth(100);
        super.setLayoutX(BossFight.getInstance().getLayoutX() - 110);
        super.setLayoutY(BossFight.getInstance().getTranslateY() + 140);
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
