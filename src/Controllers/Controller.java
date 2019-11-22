package Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import Controllers.*;
import Database.DBHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import sample.Human;

public class Controller {
    public Object select = null;
    public Human human = new Human();
    private float curentCcal = 0;

    public float getCurentCcal() {
        return curentCcal;
    }

    public void setCurentCcal(float curentCcal) {
        this.curentCcal = curentCcal;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label currentHaWLable;

    @FXML
    private Label fieldCcal;

    @FXML
    private DatePicker dateValue;

    @FXML
    void initialize() {
        dateValue.setValue(LocalDate.now());


    }

    public void transferUser(Human _human){
        human.setName(_human.getName());
        human.setAge(_human.getAge());
        human.setWeight(_human.getWeight());
        human.setHeight(_human.getHeight());
        human.setCcal(_human.getCcal());
        human.setLogin(_human.getLogin());
        human.setPassword(_human.getPassword());
        human.setGender(_human.getGender());

        fieldCcal.setText(curentCcal + "/" + human.getCcal());
    }
}
