package com.example.cuphead2.SceneControllers;

import com.example.cuphead2.Models.Music;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class WinSceneController {


    public void enterMain(ActionEvent actionEvent) throws IOException {
        Music.getInstance().mainMediaPlayer.pause();
        MainSceneController.get().MainMenu((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
    }
}
