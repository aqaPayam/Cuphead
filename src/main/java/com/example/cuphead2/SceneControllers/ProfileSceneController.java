package com.example.cuphead2.SceneControllers;

import com.example.cuphead2.Main;
import com.example.cuphead2.Models.Music;
import com.example.cuphead2.Models.UserDatabase;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileSceneController implements Initializable {
    public Button logout;
    public Button deleteUser;
    public TextField newPassword;
    public TextField username;
    public Button setUser;
    public Button setNewPass;
    public Label label;
    public ImageView avatarImage;
    @FXML
    private Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startBackgroundAnimation();
        avatarImage.setImage(new Image(Main.class.getResource("Avatars/" + UserDatabase.getCurrentUser().getAvatarImage() + ".png").toExternalForm()));
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

    public void logout(ActionEvent actionEvent) throws IOException {
        Music.getInstance().mainMediaPlayer.pause();
        UserDatabase.setCurrentUser(null);
        MainSceneController.get().LoginMenu((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
    }

    public void deleteUser(ActionEvent actionEvent) throws IOException {
        Music.getInstance().mainMediaPlayer.pause();
        UserDatabase.getUsers().remove(UserDatabase.getCurrentUser());
        UserDatabase.setCurrentUser(null);
        MainSceneController.get().LoginMenu((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        UserDatabase.saveUsers();
    }

    public void setNewUsername(ActionEvent actionEvent) {
        if (username.getText().equals(UserDatabase.getCurrentUser().getUsername())) {
            label.setText("username do not change");
        } else {
            UserDatabase.getCurrentUser().setUsername(username.getText());
            label.setText("username changed");
        }
    }

    public void setNewPassword(ActionEvent actionEvent) {
        UserDatabase.getCurrentUser().setPassword(newPassword.getText());
        label.setText("password changed");
    }
}
