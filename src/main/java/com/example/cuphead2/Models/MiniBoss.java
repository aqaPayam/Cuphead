package com.example.cuphead2.Models;

import com.example.cuphead2.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class MiniBoss extends ImageView {
    private static final ArrayList<MiniBoss> miniBosses = new ArrayList<>();

    private int frame = 0;

    public static ArrayList<MiniBoss> getMiniBosses() {
        return miniBosses;
    }

    public MiniBoss() {
        super();
        super.setImage(new Image(Main.class.getResource("Phase 1/Flappy Birds/Pink/Fly/flappy_bird_fly_pink_0001.png").toExternalForm()));
        super.setFitHeight(80);
        super.setFitWidth(80);
        Random random = new Random();
        double d = BossFight.getInstance().getScene().getWidth();
        super.setLayoutX(d);
        d = random.nextDouble(BossFight.getInstance().getScene().getHeight() + 1);
        super.setLayoutY(d);
    }


    public boolean hasCollision(ImageView block) {
        return block.getBoundsInParent().intersects(this.getBoundsInParent());
    }

    public int getFrame() {
        if (frame > 16)
            frame = 0;
        int a = frame;
        frame++;
        return a + 1;
    }

    public String getFrameString() {
        int a = getFrame();
        if (a > 9)
            return String.valueOf(a);
        return "0" + a;
    }

}
