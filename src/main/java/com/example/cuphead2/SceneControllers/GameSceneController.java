package com.example.cuphead2.SceneControllers;

import com.example.cuphead2.Main;
import com.example.cuphead2.Models.*;
import javafx.animation.*;

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.css.Match;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameSceneController implements Initializable {

    private final BooleanProperty wPressed = new SimpleBooleanProperty();
    private final BooleanProperty aPressed = new SimpleBooleanProperty();
    private final BooleanProperty sPressed = new SimpleBooleanProperty();
    private final BooleanProperty dPressed = new SimpleBooleanProperty();

    private final BooleanProperty shiftPressed = new SimpleBooleanProperty();

    private final BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed).or(shiftPressed);


    private final Plane plane = Plane.getInstance();

    private final BossFight bossFight = BossFight.getInstance();

    private final ArrayList<Bullet> planeBullet = Bullet.getBulletArray();
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
    private Button button;

    @FXML
    private AnchorPane pane;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            if (wPressed.get() && !plane.hitTopWall()) {
                plane.goUp();
            }

            if (sPressed.get() && !plane.hitFloor()) {
                plane.goDown();
            }

            if (aPressed.get() && !plane.hitLeftWall()) {
                plane.goLeft();
            }

            if (dPressed.get() && !plane.hitRightWall()) {
                plane.goRight();
            }
        }
    };

    AnimationTimer collisionTimer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (plane.hasCollision(bossFight))
                planeInjured();
            for (int i = 0; i < planeBullet.size(); i++) {
                Bullet bullet = planeBullet.get(i);
                if (bullet.hasCollision(bossFight)) {
                    bossFight.setHealth(bossFight.getHealth() - 5);
                    bossFightHealth.setProgress(bossFight.getHealth() / (double) 5000);
                    healthLabel.setText(String.valueOf(bossFight.getHealth()));
                    Bullet.getBulletArray().remove(bullet);
                    pane.getChildren().remove(bullet);
                    i--;
                }
            }
            for (int i = 0; i < Egg.getEggArray().size(); i++) {
                Egg bullet = Egg.getEggArray().get(i);
                if (bullet.hasCollision(plane)) {
                    Egg.getEggArray().remove(bullet);
                    pane.getChildren().remove(bullet);
                    i--;
                    continue;
                }
            }
            for (int i = 0; i < MiniBoss.getMiniBosses().size(); i++) {
                MiniBoss miniBoss = MiniBoss.getMiniBosses().get(i);
                if (miniBoss.hasCollision(plane)) {
                    MiniBoss.getMiniBosses().remove(miniBoss);
                    pane.getChildren().remove(miniBoss);
                    i--;
                }
                for (int j = 0; j < planeBullet.size(); j++) {
                    Bullet bullet = planeBullet.get(j);
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
    };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startBackgroundAnimation();
        pane.getChildren().add(plane);
        pane.getChildren().add(bossFight);
        generateEggSet();
        generateMiniBoss();
        movementSetup();
        playBossFightAnimation();
        collisionTimer.start();
        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if (!aBoolean) {
                timer.start();
            } else {
                timer.stop();
            }
        }));
    }

    public void movementSetup() {
        pane.setOnKeyPressed(e -> {
            System.out.println(e.getCode());
            if (e.getCode() == KeyCode.SHIFT) {

                Timer timer = Bullet.getTimeline();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            fire();
                        });
                    }
                }, 0, 100);
                shiftPressed.set(true);
            }
            if (e.getCode() == KeyCode.W) {
                wPressed.set(true);
            }

            if (e.getCode() == KeyCode.A) {
                aPressed.set(true);
            }

            if (e.getCode() == KeyCode.S) {
                sPressed.set(true);
            }

            if (e.getCode() == KeyCode.D) {
                dPressed.set(true);
            }
        });

        pane.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SHIFT) {
                Bullet.getTimeline().cancel();
                Bullet.setTimeline(new Timer());
                shiftPressed.set(false);
            }
            if (e.getCode() == KeyCode.W) {
                wPressed.set(false);
            }

            if (e.getCode() == KeyCode.A) {
                aPressed.set(false);
            }

            if (e.getCode() == KeyCode.S) {
                sPressed.set(false);
            }

            if (e.getCode() == KeyCode.D) {
                dPressed.set(false);
            }
        });
    }

    public void fire() {
        Bullet bullet = new Bullet();
        Bullet.getBulletArray().add(bullet);
        pane.getChildren().add(bullet);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
        transition.setNode(bullet);
        transition.setByX(2000);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Egg.getEggArray().remove(bullet);
                pane.getChildren().remove(bullet);
            }
        });
    }

    private void playBossFightAnimation() {
        ImageView imageView = new ImageView();
        imageView.setLayoutX(BossFight.getInstance().getLayoutX() - 150);
        imageView.setLayoutY(BossFight.getInstance().getLayoutY() + 100);
        pane.getChildren().add(imageView);
        TranslateTransition transition5 = new TranslateTransition(Duration.seconds(3));
        transition5.setNode(imageView);
        transition5.setByY(600);
        transition5.setInterpolator(Interpolator.LINEAR);
        transition5.setCycleCount(Animation.INDEFINITE);
        transition5.setAutoReverse(true);
        transition5.play();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    String frame = BossFight.getFrameString();
                    BossFight.getInstance().setImage(
                            new Image(Main.class.
                                    getResource("Phase 1/House/bird_idle_house_00" + frame + ".png")
                                    .toExternalForm()));
                    imageView.setImage(new Image(Main.class.
                            getResource("Phase 1/Barf/bird_barf_head_00" + frame + ".png")
                            .toExternalForm()));
                });
            }
        }, 200, 50);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(3));
        transition.setNode(bossFight);
        transition.setByY(600);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setCycleCount(Animation.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();
    }


    private void generateEggSet() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    fireEgg();
                });
            }
        }, 0, 800);
    }

    private void fireEgg() {
        Egg egg = new Egg();
        Egg.getEggArray().add(egg);
        pane.getChildren().add(egg);
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(egg);
        rotateTransition.setByAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.play();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(5));
        transition.setNode(egg);
        transition.setByX(-2000);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Egg.getEggArray().remove(egg);
                pane.getChildren().remove(egg);
            }
        });
        System.out.println(pane.getChildren().size());
    }


    private void generateMiniBoss() {
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

    private void fireMiniBoss() {
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

    private void miniBossAnimation(MiniBoss miniBoss) {
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

    public void startBackgroundAnimation() {
        DoubleProperty xPosition = new SimpleDoubleProperty(0);
        xPosition.addListener((observable, oldValue, newValue) -> setBackgroundPositions(pane, xPosition.get()));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(xPosition, 0)),
                new KeyFrame(Duration.seconds(100), new KeyValue(xPosition, -15000))
        );
        timeline.play();
    }

    void setBackgroundPositions(Region region, double xPosition) {
        String style = "-fx-background-position: " +
                "left " + xPosition / 6 + "px bottom," +
                "left " + xPosition / 5 + "px bottom," +
                "left " + xPosition / 4 + "px bottom," +
                "left " + xPosition / 3 + "px bottom," +
                "left " + xPosition / 2 + "px bottom," +
                "left " + xPosition + "px bottom;";

        region.setStyle(style);
    }

    private void planeInjured() {
        int seconds = 4000;
        if (plane.isInInjured())
            return;
        plane.setHealth(plane.getHealth() - 1);
        pane.getChildren().remove(getHearts().get(plane.getHealth()));
        plane.setInInjured(true);
        plane.setOpacity(0.4);
        Timer timer = new Timer();
        for (int i = 1; i <= 7; i++) {
            int finalI = i;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (finalI % 2 == 1)
                        plane.setOpacity(0.2);
                    else
                        plane.setOpacity(1);
                }
            }, seconds / i);
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                plane.setInInjured(false);
                plane.setOpacity(1);
            }
        }, 4 * 500);
    }

    public ArrayList<ImageView> getHearts() {
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
        return imageViews;
    }
}


