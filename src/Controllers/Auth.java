package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Database.DBHandler;
import com.sun.javafx.css.StyleCache;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sample.Human;

public class Auth {
    private Human human = new Human();
    private String login;
    private int password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button nextButton;

    @FXML
    private Button authButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        nextButton.setDefaultButton(true);

        authButton.setOnAction(actionEvent -> {
            authButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(heightAndWeightController.class.getResource("/FXML/heightAndWeight.fxml"));
            try {
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Weight Contoller");
            stage.show();
            stage.setResizable(false);
        });

        nextButton.setOnAction(actionEvent -> {
            login = loginField.getText();
            password = Integer.parseInt(passwordField.getText());
            human = DBHandler.authorization(login, password, human);
            if(human.getCcal() == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Неверный логин или пароль");
                alert.showAndWait();
            }else{
                nextButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Auth.class.getResource("/FXML/sample.fxml"));
                try {
                    loader.load();
                }catch (IOException e){
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();

                Controller controller = loader.getController();
                controller.transferUser(human);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Weight Contoller");
                stage.show();
                stage.setResizable(false);
            }

        });

    }
}

