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

    public boolean hasCollision(ImageView block) {
        return block.getBoundsInParent().intersects(this.getBoundsInParent());
    }

}
