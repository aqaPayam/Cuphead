package com.example.cuphead2.SceneControllers;

import com.example.cuphead2.Controller.ThreadsController;
import com.example.cuphead2.Models.Music;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoseSceneController implements Initializable {
    public Label point;
    public Label time;
    public Button button;

    public void enterMain(ActionEvent actionEvent) throws IOException {
        Music.getInstance().mainMediaPlayer.pause();
        MainSceneController.get().MainMenu((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        point.setText(String.valueOf(ThreadsController.point));
        time.setText(String.valueOf(ThreadsController.time));
    }
}
