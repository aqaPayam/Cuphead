package com.example.cuphead2.Models;

import com.example.cuphead2.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DevilBoss extends BossFight{

    private static int frame = 0;
    private static DevilBoss instance;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    private int health=2500;

    public static DevilBoss getInstance() {
        if (instance == null)
            instance = new DevilBoss();
        return instance;
    }

    private DevilBoss() {
        super(0);
        super.setImage(new Image(Main.class.getResource("devilBoss/egghead_shoot_0001.png").toExternalForm()));
        super.setFitHeight((double) 421 / 5 * 4);
        super.setFitWidth((double) 593 / 5 * 4);
        super.setLayoutX(1100);
        super.setLayoutY(0);
    }

    public static int getFrame() {
        if (frame > 20)
            frame = 0;
        int a = frame;
        frame++;
        return a+1;
    }
}
