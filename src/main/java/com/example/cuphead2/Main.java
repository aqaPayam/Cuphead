package com.example.cuphead2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainSceneController sceneController=new MainSceneController();
        stage.setTitle("CupHead");
        sceneController.LoginMenu(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}