package com.example.cuphead2.SceneControllers;

import com.example.cuphead2.Controller.LoginController;
import com.example.cuphead2.MainSceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static java.time.Duration.ofSeconds;


public class LoginSceneController {
    private final LoginController controller = new LoginController();
    @FXML
    public Button guestLabel;
    @FXML
    private Label label;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private TextField registerUsername;
    @FXML
    private PasswordField registerPassword;
    @FXML
    private TextField loginUsername;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;


    public void register(ActionEvent e) throws InterruptedException {
        System.out.println(guestLabel);
        String pass = registerPassword.getText();
        String username = registerUsername.getText();
        if (controller.isRegisterValid(username, pass)) {
            controller.register(username, pass);
            label.setText("Register successful");
        } else {
            label.setText("you cant use this username");
        }
    }

    public void login(ActionEvent e) throws Exception {
        String pass = loginPassword.getText();
        String username = loginUsername.getText();
        if (controller.isLoginValid(username, pass)) {
            controller.login(username, pass);
            MainSceneController.get().MainMenu((Stage) ((Node) e.getSource()).getScene().getWindow());
        } else {
            label.setText("username and password is not match");
        }

    }


    public void enterGuest(ActionEvent e) throws IOException {
        MainSceneController.get().MainMenu((Stage) ((Node) e.getSource()).getScene().getWindow());
    }
}