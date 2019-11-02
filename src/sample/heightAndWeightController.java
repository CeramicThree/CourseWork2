package sample;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class heightAndWeightController {

    public float Weight;
    public float Height;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fieldHeight;

    @FXML
    private TextField fieldWeight;

    @FXML
    private Button nextButton;

    @FXML
    void initialize() {
        nextButton.setOnAction(actionEvent -> {
            Weight = Float.parseFloat(fieldWeight.getText());
            Height = Float.parseFloat(fieldHeight.getText());

            nextButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/sample.fxml"));
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
            stage.showAndWait();
        });

    }
}
