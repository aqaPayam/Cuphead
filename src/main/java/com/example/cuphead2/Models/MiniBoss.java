package com.example.cuphead2.Models;

import com.example.cuphead2.Main;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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


    public static void generateMiniBoss() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    fireMiniBoss();
                });
            }
        }, 0, 1000);
    }

    private static void fireMiniBoss() {
        Pane pane= (Pane) BossFight.getInstance().getParent();
        MiniBoss miniBoss = new MiniBoss();
        miniBossAnimation(miniBoss);
        MiniBoss.getMiniBosses().add(miniBoss);
        pane.getChildren().add(miniBoss);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(5));
        transition.setNode(miniBoss);
        transition.setByX(-2000);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MiniBoss.getMiniBosses().remove(miniBoss);
                pane.getChildren().remove(miniBoss);
            }
        });
    }

    private static void miniBossAnimation(MiniBoss miniBoss) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    String frame = miniBoss.getFrameString();
                    miniBoss.setImage(
                            new Image(Main.class.
                                    getResource("Phase 1/Flappy Birds/Pink/Fly/flappy_bird_fly_pink_00" + frame + ".png")
                                    .toExternalForm()));
                });
            }
        }, 200, 50);

    }
}
