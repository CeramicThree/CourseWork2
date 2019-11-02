package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller extends heightAndWeightController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label currentHaWLable;

    @FXML
    void initialize() {
       currentHaWLable.setText("Ващ текущий рост и вес: " + Height + " " + Weight);
    }
}
