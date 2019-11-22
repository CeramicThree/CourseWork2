package Controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Database.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.Human;


public class heightAndWeightController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fieldHeight;

    @FXML
    private TextField fieldWeight;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldAge;

    @FXML
    private Button nextButton;

    @FXML
    private Button authButton;

    @FXML
    private CheckBox checkMale;

    @FXML
    private CheckBox checkFem;

    @FXML
    private PasswordField fieldPass;

    @FXML
    private TextField fieldLogin;

    @FXML
    void initialize() {


        fieldWeight.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    fieldWeight.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        fieldHeight.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    fieldHeight.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        fieldAge.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    fieldAge.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        checkMale.setOnAction(actionEvent -> {
            if(checkMale.isSelected()){
                checkFem.setSelected(false);
            }
        });

        checkFem.setOnAction(actionEvent -> {
            if(checkFem.isSelected()){
                checkMale.setSelected(false);
            }
        });

        nextButton.setOnAction(actionEvent -> {
            Human human = new Human(Float.parseFloat(fieldHeight.getText()), Float.parseFloat(fieldWeight.getText()),
                    Integer.parseInt(fieldAge.getText()), fieldName.getText(), "Male", Integer.parseInt(fieldPass.getText()), fieldLogin.getText());

            if(checkMale.isSelected()){
                human.setGender("Male");
            }else if(checkFem.isSelected()){
                human.setGender("Female");
            }
            human.calcCcal();

            DBHandler.insertIntoUsers(human);

            nextButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(heightAndWeightController.class.getResource("/FXML/sample.fxml"));
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
        });

        authButton.setOnAction(actionEvent -> {
            authButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(heightAndWeightController.class.getResource("/FXML/auth.fxml"));
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
    }
}
