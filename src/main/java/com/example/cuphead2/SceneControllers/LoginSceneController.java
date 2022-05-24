package com.example.cuphead2.SceneControllers;

import com.example.cuphead2.Controller.LoginController;
import com.example.cuphead2.Main;
import com.example.cuphead2.Models.Music;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static java.time.Duration.ofSeconds;


public class LoginSceneController implements Initializable {
    private final LoginController controller = new LoginController();
    @FXML
    public Button guestLabel;
    public Button nextAvatarButton;
    public ImageView avatarImage;
    public Button nextAvatarButton1;
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
    private int avatar = 1;


    public void register(ActionEvent e) throws InterruptedException {
        System.out.println(guestLabel);
        String pass = registerPassword.getText();
        String username = registerUsername.getText();
        if (controller.isRegisterValid(username, pass)) {
            controller.register(username, pass, avatar);
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


    public void enterGuest(ActionEvent e) throws Exception {
        controller.register("guest", "guest", avatar);
        controller.login("guest", "guest");
        MainSceneController.get().MainMenu((Stage) ((Node) e.getSource()).getScene().getWindow());

    }

    public void nextAvatar(ActionEvent actionEvent) {
        avatar++;
        if (avatar >= 6)
            avatar = 1;
        avatarImage.setImage(new Image(Main.class.getResource("Avatars/" + avatar + ".png").toExternalForm()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        avatarImage.setImage(new Image(Main.class.getResource("Avatars/" + avatar + ".png").toExternalForm()));
        Music.getInstance().playMainMusic();
    }

    public void randomAvatar(ActionEvent actionEvent) {
        Random random = new Random();
        avatar = random.nextInt(4) + 1;
        avatarImage.setImage(new Image(Main.class.getResource("Avatars/" + avatar + ".png").toExternalForm()));
    }
}