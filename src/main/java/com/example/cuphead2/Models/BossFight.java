package com.example.cuphead2.Models;

import com.example.cuphead2.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BossFight extends ImageView {
    private static int frame = 0;
    private static BossFight instance;

    public BossFight(int ChangeToDevilMode) {
        super();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    private int health = 7000;

    public static BossFight getInstance() {
        if (instance == null)
            instance = new BossFight();
        return instance;
    }

    private BossFight() {
        super();
        super.setImage(new Image(Main.class.getResource("Phase 1/House/bird_idle_house_0001.png").toExternalForm()));
        super.setFitHeight((double) 421 / 5 * 4);
        super.setFitWidth((double) 593 / 5 * 4);
        super.setLayoutX(1100);
        super.setLayoutY(0);
    }

    public static int getFrame() {
        if (frame > 16)
            frame = 0;
        int a = frame;
        frame++;
        return a + 1;
    }

    public static String getFrameString() {
        int a = getFrame();
        if (a > 9)
            return String.valueOf(a);
        return "0" + a;
    }

    public boolean isBossFightDead() {
        return getHealth() < 0;
    }


}
