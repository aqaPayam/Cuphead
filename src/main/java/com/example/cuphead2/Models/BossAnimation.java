package com.example.cuphead2.Models;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class BossAnimation extends Transition {
    private final BossFight bossFight;
    private final Plane plane;
    private double dx = 0;
    private double dy = 1;

    public BossAnimation(Plane plane, BossFight bossFight) {
        this.bossFight = bossFight;
        this.plane = plane;
    }

    @Override
    protected void interpolate(double v) {
    }
}
