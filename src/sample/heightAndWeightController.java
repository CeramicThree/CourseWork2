package sample;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class heightAndWeightController {
    private Human human;

    heightAndWeightController(Human human){
        this.human = human;
    }

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
    private CheckBox checkMale;

    @FXML
    private CheckBox checkFem;

    @FXML
    void initialize() {

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
                    Integer.parseInt(fieldAge.getText()), fieldName.getText(), 1);

            if(checkMale.isSelected()){
                human.setGender(1);
            }else if(checkFem.isSelected()){
                human.setGender(0);
            }

            human.calcCcal();

            nextButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(heightAndWeightController.class.getResource("/sample/sample.fxml"));
            try {
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setResizable(false);
        });
    }
}
