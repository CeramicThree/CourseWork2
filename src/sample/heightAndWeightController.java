package sample;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Database.*;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.stage.Stage;


public class heightAndWeightController {
    /*
    private Human human;

    public heightAndWeightController(Human human){
        this.human = human;
    }
    */
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
        /*
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
*/
        DBHandler database;
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
                    Integer.parseInt(fieldAge.getText()), fieldName.getText(), "Мужчина");

            if(checkMale.isSelected()){
                human.setGender("Мужчина");
            }else if(checkFem.isSelected()){
                human.setGender("Женщина");
            }
            human.calcCcal();
            String query = "USE heroku_a6b4ec6076ccf62; " + "INSERT INTO users (Name, Age, Weight, Height, Gender, Ccal) " +
                    "VALUES (" + human.getName() + ", " + human.getAge() + ", " + human.getWeight() + ", " + human.getHeight()
                    + ", " + human.getGender() + ", " + human.getCcal() + ");";
            DBHandler.executeQuery(query);

            System.out.println(human.getCcal());
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
            stage.setTitle("Weight Contoller");
            stage.show();
            stage.setResizable(false);
        });
    }
}
