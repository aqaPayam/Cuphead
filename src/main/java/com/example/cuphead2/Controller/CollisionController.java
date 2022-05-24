package com.example.cuphead2.Controller;

import com.example.cuphead2.Controller.Animations.BossFightAnimation;
import com.example.cuphead2.Models.*;
import javafx.scene.layout.Pane;

public class CollisionController {
    private static CollisionController instance;
    Plane plane;
    BossFight bossFight;
    Pane pane;

    public static CollisionController getInstance() {
        if (instance == null)
            instance = new CollisionController();
        return instance;
    }

    public void run() {
        planeBossFightCollision();
        bulletBossFightCollision();
        EggCollision();
        EggSecondBossCollision();
        MiniBossCollision();
    }

    private CollisionController() {
        bossFight = BossFight.getInstance();
        plane = Plane.getInstance();
        pane = (Pane) plane.getParent();
    }

    private void planeBossFightCollision() {
        if (plane.hasCollision(bossFight))
            plane.planeInjured();
    }


    private void bulletBossFightCollision() {

        for (int i = 0; i < Bullet.getBulletArray().size(); i++) {
            Bullet bullet = Bullet.getBulletArray().get(i);
            if (bullet.hasCollision(bossFight)) {
                bossFight.setHealth(bossFight.getHealth() - 50);
                Bullet.getBulletArray().remove(bullet);
                pane.getChildren().remove(bullet);
                i--;
            }
        }
    }


    private void EggCollision() {
        for (int i = 0; i < Egg.getEggArray().size(); i++) {
            Egg bullet = Egg.getEggArray().get(i);
            if (bullet.hasCollision(plane)) {
                plane.planeInjured();
                Egg.getEggArray().remove(bullet);
                pane.getChildren().remove(bullet);
                i--;
            }
        }
    }

    private void EggSecondBossCollision() {
        for (int i = 0; i < BossFightAnimation.getInstance().eggs.size(); i++) {
            Egg imageView = BossFightAnimation.getInstance().eggs.get(i);
            if (imageView.hasCollision(plane)) {
                plane.planeInjured();
            }
        }
    }

    private void MiniBossCollision() {
        for (int i = 0; i < MiniBoss.getMiniBosses().size(); i++) {
            MiniBoss miniBoss = MiniBoss.getMiniBosses().get(i);
            if (miniBoss.hasCollision(plane)) {
                plane.planeInjured();
                MiniBoss.getMiniBosses().remove(miniBoss);
                pane.getChildren().remove(miniBoss);
                i--;
            }
            ///
            for (int j = 0; j < Bullet.getBulletArray().size(); j++) {
                Bullet bullet = Bullet.getBulletArray().get(j);
                if (miniBoss.hasCollision(bullet)) {
                    Bullet.getBulletArray().remove(bullet);
                    pane.getChildren().remove(bullet);
                    MiniBoss.getMiniBosses().remove(miniBoss);
                    pane.getChildren().remove(miniBoss);
                    i--;
                    break;

                }
            }

        }
    }
}
