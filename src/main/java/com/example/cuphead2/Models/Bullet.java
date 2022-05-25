package com.example.cuphead2.Models;

import com.example.cuphead2.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Timer;

public class Bullet extends ImageView {
    private static Timer timeline = new Timer();

    public static Timer getTimeline() {
        return timeline;
    }

    public static void setTimeline(Timer timeline) {
        Bullet.timeline = timeline;
    }

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

    public boolean hasCollision(ImageView block) {
        return block.getBoundsInParent().intersects(this.getBoundsInParent());
    }

}
