package com.example.cuphead2.SceneControllers;

import com.example.cuphead2.Controller.Animations.BossFightAnimation;
import com.example.cuphead2.Controller.Animations.planeToBombAnimation;
import com.example.cuphead2.Controller.BackgroundController;
import com.example.cuphead2.Controller.CollisionController;
import com.example.cuphead2.Controller.EggController;
import com.example.cuphead2.Models.*;
import javafx.animation.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.*;

public class GameSceneController implements Initializable {
    private final Plane plane = Plane.getInstance();
    public Button bombButton;
    public ProgressBar BombBar;
    private final BossFight bossFight = BossFight.getInstance();
    @FXML
    private Label healthLabel;
    @FXML
    public ImageView heart1;
    @FXML
    public ImageView heart2;
    @FXML
    public ImageView heart3;
    @FXML
    public ImageView heart4;
    @FXML
    public ImageView heart5;
    @FXML
    public ImageView heart6;
    @FXML
    public ImageView heart7;
    @FXML
    public ImageView heart8;
    @FXML
    public ImageView heart9;
    @FXML
    public ImageView heart10;
    @FXML
    private ProgressBar bossFightHealth;
    @FXML
    private AnchorPane pane;


    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            bossFightHealth.setProgress(bossFight.getHealth() / (double) 5000);
            healthLabel.setText(String.valueOf(bossFight.getHealth()));
            CollisionController.getInstance().run();
//            for (Node child : pane.getChildren()) {
//                ColorAdjust colorAdjust = new ColorAdjust();
//                colorAdjust.setBrightness(-1);
//                child.setEffect(colorAdjust);
//            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Music.getInstance().playGameMusic();
        pane.getChildren().add(plane);
        BackgroundController.getInstance().startBackgroundAnimation();
        setHeartsToPlane();
        pane.getChildren().add(bossFight);
        EggController.getInstance().generateEggSet();
        MiniBoss.generateMiniBoss();
        PlaneMovement.getInstance().run();
        setBombBar();
        BossFightAnimation.getInstance().BossFightAnimationTimerSet();
        BossFightAnimation.getInstance().playBossFightAnimation();
        timer.start();
    }

    private void setBombBar() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (BombBar.getProgress() < 1)
                    BombBar.setProgress(BombBar.getProgress() + 0.001);
                if (BombBar.getProgress() >= 1)
                    BombBar.setProgress(1);

            }
        }, 0, 10);
    }

    public void setHeartsToPlane() {
        ArrayList<ImageView> imageViews = new ArrayList<>();
        imageViews.add(heart1);
        imageViews.add(heart2);
        imageViews.add(heart3);
        imageViews.add(heart4);
        imageViews.add(heart5);
        imageViews.add(heart6);
        imageViews.add(heart7);
        imageViews.add(heart8);
        imageViews.add(heart9);
        imageViews.add(heart10);
        Plane.hearts = imageViews;
    }

    public void fireBomb(ActionEvent actionEvent) {
        System.out.println(BombBar.getProgress());
        if (BombBar.getProgress() == 1) {
            new planeToBombAnimation().run(plane, bossFight, timer);
            BombBar.setProgress(0);
        }
    }

}


