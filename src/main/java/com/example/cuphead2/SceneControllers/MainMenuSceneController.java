package com.example.cuphead2.SceneControllers;

import com.example.cuphead2.MainSceneController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuSceneController {
    public Button profileMenuButton;
    public Button newGameButton;
    public Button ranking;
    public Button savedGameButton;
    public Button exit;

    public void playSavedGame(ActionEvent actionEvent) throws IOException {
        MainSceneController.get().GameMenu((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
    }

    public void enterRanking(ActionEvent actionEvent) throws IOException {
        MainSceneController.get().RankingMenu((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
    }

    public void playNewGame(ActionEvent actionEvent) throws IOException {
        MainSceneController.get().GameMenu((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
    }

    public void enterProfileMenu(ActionEvent actionEvent) throws IOException {
        MainSceneController.get().ProfileMenu((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        MainSceneController.get().LoginMenu((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
    }
}
