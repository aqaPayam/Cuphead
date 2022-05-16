package com.example.cuphead2.Models;

import com.example.cuphead2.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BossFight extends ImageView {
    private static BossFight instance;

    public static BossFight getInstance() {
        if (instance == null)
            instance = new BossFight();
        return instance;
    }

    private BossFight() {
        super();
        super.setImage(new Image(Main.class.getResource("Boss.png").toExternalForm()));
        super.setFitHeight((double) 421 / 5 * 4);
        super.setFitWidth((double) 593 / 5 * 4);
        super.setLayoutX(1100);
        super.setLayoutY(0);
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


}
