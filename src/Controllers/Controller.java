package Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Controllers.*;
import Database.DBHandler;
import com.mysql.cj.x.protobuf.MysqlxConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sample.Food;
import sample.Human;

public class Controller {
    public Object select = null;
    public Human human = new Human();
    private float curentCcal = 0;
    public List<CheckBox> mealList = new ArrayList<>();
    public List<Food> foodList = new ArrayList<>();

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
    private ProgressBar progressBar;

    @FXML
    private DatePicker dateValue;

    @FXML
    private ScrollPane scrollBreakfast;

    @FXML
    private VBox vboxBreakfast;

    @FXML
    private ScrollPane scroolDinner;

    @FXML
    private VBox vboxDinner;

    @FXML
    private ScrollPane scroolLateDinner;

    @FXML
    private VBox vboxLateDinner;

    @FXML
    private Label fieldCcal;

    @FXML
    private Label currentHaWLable;

    @FXML
    private Button buttonEat;

    @FXML
    private VBox vboxMeals;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonMeals;

    @FXML
    private TextField fieldNewProduct;

    @FXML
    private TextField feildEnergyValue;

    @FXML
    private MenuButton menuButton;

    @FXML
    private MenuItem menuBreakfast;

    @FXML
    private MenuItem menuDinner;

    @FXML
    private MenuItem menuLateDinner;

    @FXML
    private AnchorPane secondPane;


    @FXML
    void initialize() {
        secondPane.setVisible(false);
        dateValue.setValue(LocalDate.now());
        dateValue.setEditable(false);


        buttonMeals.setOnAction(actionEvent -> {
            if(secondPane.isVisible()){
                secondPane.setVisible(false);
            }else{
                secondPane.setVisible(true);
            }
        });

        menuBreakfast.setOnAction(actionEvent -> {
            menuButton.setText("Завтрак");
        });
        menuDinner.setOnAction(actionEvent -> {
            menuButton.setText("Обед");
        });
        menuLateDinner.setOnAction(actionEvent -> {
            menuButton.setText("Ужин");
        });

        buttonAdd.setOnAction(actionEvent -> {
            String name = fieldNewProduct.getText();
            float energyValue = Float.parseFloat(feildEnergyValue.getText());
            vboxMeals.getChildren().add(createCheckBox(name, energyValue));
        });

        buttonDelete.setOnAction(actionEvent -> {
            for(int i = 0; i < mealList.size(); i++){
                if(mealList.get(i).isSelected()){
                    vboxMeals.getChildren().remove(mealList.get(i));
                }
            }
        });

        buttonEat.setOnAction(actionEvent -> {
            Label label = new Label();
            if(menuButton.getText().equals("Завтрак")){
                for(int i = 0; i < mealList.size(); i++){
                    if(mealList.get(i).isSelected()){
                        label.setText(mealList.get(i).getText());
                        vboxBreakfast.getChildren().add(label);
                        curentCcal += foodList.get(i).getEnergyValue();
                    }
                }
            }else if(menuButton.getText().equals("Обед")){
                for(int i = 0; i < mealList.size(); i++) {
                    if (mealList.get(i).isSelected()) {
                        label.setText(mealList.get(i).getText());
                        vboxDinner.getChildren().add(label);
                        curentCcal += foodList.get(i).getEnergyValue();
                    }
                }
            }else if(menuButton.getText().equals("Ужин")){
                for(int i = 0; i < mealList.size(); i++) {
                    if (mealList.get(i).isSelected()) {
                        label.setText(mealList.get(i).getText());
                        vboxLateDinner.getChildren().add(label);
                        curentCcal += foodList.get(i).getEnergyValue();
                    }
                }
            }
            fieldCcal.setText(curentCcal + "/" + human.getCcal());
        });




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

    public CheckBox createCheckBox(String name, float EnergyValue){
        CheckBox checkBox = new CheckBox();
        Food food = new Food(name, EnergyValue);
        foodList.add(food);
        mealList.add(checkBox);
        checkBox.setText(name + "\n" + EnergyValue + " Ccal");
        return checkBox;
    }

}
