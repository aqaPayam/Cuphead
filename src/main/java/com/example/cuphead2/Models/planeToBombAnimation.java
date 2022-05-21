package com.example.cuphead2.Models;

import com.example.cuphead2.Main;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class planeToBombAnimation {
    private int f = 0;

    public void run(Plane plane) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
        transition.setNode(plane);
        transition.setByX(1000);
        transition.setInterpolator(Interpolator.LINEAR);
        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                transition.play();
            }
        }, 1000);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    String frame;
                    f++;
                    if (f > 9)
                        frame = String.valueOf(f);
                    else
                        frame = "0" + f;
                    if (f >= 17)
                    {
                        timer.cancel();
                        timer.purge();
                    }
                    System.out.println(frame);
                    Plane.getInstance().setImage(
                            new Image(Main.class.
                                    getResource("bomb/mm_shmup_super_intro_00" + frame + ".png")
                                    .toExternalForm()));
                });
            }
        }, 200, 50);

    }
}
