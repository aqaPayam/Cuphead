package com.example.cuphead2.Models;

import javafx.animation.Transition;

public class BulletAnimation extends Transition {
    private final Bullet bullet;

    public BulletAnimation(Bullet bullet) {
        this.bullet = bullet;
    }

    @Override
    protected void interpolate(double v) {
        double dx = 1;
        bullet.setLayoutX(bullet.getLayoutX() + dx);
        System.out.println("mao");
        if (bullet.Out())
            Bullet.getBulletArray().remove(bullet);
        if (bullet.hasCollision(BossFight.getInstance()))
            System.out.println("seks");
    }
}
