package Controllers;

import java.io.FileNotFoundException;
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
import javazoom.jl.decoder.JavaLayerException;
import sample.Food;
import sample.Human;
import Media.Audio;

public class Controller {
    public Human human = new Human();
    private float curentCcal = 0;
    private List<CheckBox> mealList = new ArrayList<>();
    private List<Food> foodList = new ArrayList<>();

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
    private Button imageButton;

    @FXML
    private Button audioButton;

    @FXML
    private Button errorfinderButton;

    @FXML
    void initialize() {

        foodList = DBHandler.selectFromFood();
        for(int i = 0; i < foodList.size(); i++){
            mealList.add(createCheckBox(foodList.get(i)));
            vboxMeals.getChildren().add(createCheckBox(foodList.get(i)));
        }

        errorfinderButton.setOnAction(actionEvent -> {
            for(int i = 0; i < mealList.size(); i++) {
                System.out.println(mealList.get(i).getText());
            }
        });


        secondPane.setVisible(false);
        dateValue.setValue(LocalDate.now());
        dateValue.setEditable(false);

        audioButton.setOnAction(actionEvent -> {
            String audioUrl = "E:\\JavaProjects\\CourseWork2\\src\\Assets\\naruto.mp3";
            Audio audio = new Audio(audioUrl);
            boolean check = false;
            try {
                audio.playAudio(audioUrl);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            audioButton.setText("Stop");
            check = true;

        });

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
            Food food = new Food(name, energyValue);
            DBHandler.insertIntoFood(food);
            mealList.add(createCheckBox(food));
            vboxMeals.getChildren().add(createCheckBox(food));
        });

        buttonDelete.setOnAction(actionEvent -> {
            for(int i = 0; i < mealList.size(); i++){
                if(mealList.get(i).isSelected()){
                    DBHandler.deleteFromFood(foodList.get(i).getName());
                    vboxMeals.getChildren().remove(mealList.get(i));
                    foodList.remove(i);
                    mealList.remove(i);
                }
            }
        });

        buttonEat.setOnAction(actionEvent -> {
            if(progressBar.getProgress() >= 1){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Норма превышена");
                alert.setHeaderText(null);
                alert.setContentText("Зря Вы это съели...");
                alert.showAndWait();
            }


            if(menuButton.getText().equals("Завтрак")){
                for(int i = 0; i < mealList.size(); i++){
                    if(mealList.get(i).isSelected()){
                        Label label = new Label();
                        label.setText(mealList.get(i).getText());
                        label.setStyle("-fx-font-family: Calibri;");
                        vboxBreakfast.getChildren().add(label);
                        curentCcal += foodList.get(i).getEnergyValue();
                    }
                }
            }else if(menuButton.getText().equals("Обед")){
                for(int i = 0; i < mealList.size(); i++) {
                    if (mealList.get(i).isSelected()) {
                        Label label = new Label();
                        label.setText(mealList.get(i).getText());
                        label.setStyle("-fx-font-family: Calibri;");
                        vboxDinner.getChildren().add(label);
                        curentCcal += foodList.get(i).getEnergyValue();
                    }
                }
            }else if(menuButton.getText().equals("Ужин")){
                for(int i = 0; i < mealList.size(); i++) {
                    if (mealList.get(i).isSelected()) {
                        Label label = new Label();
                        label.setText(mealList.get(i).getText());
                        label.setStyle("-fx-font-family: Calibri;");
                        vboxLateDinner.getChildren().add(label);
                        curentCcal += foodList.get(i).getEnergyValue();
                    }
                }
            }
            fieldCcal.setText(curentCcal + "/" + human.getCcal());
            progressBar.setProgress(curentCcal/human.getCcal());
        });

        imageButton.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Секрет разблокирован");
            alert.setHeaderText(null);
            alert.setContentText("Поздравляю с днём рождения, Деда, сегодня тебе 54 года!");
            alert.showAndWait();
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

    public CheckBox createCheckBox(Food food){
        CheckBox checkBox = new CheckBox();
        checkBox.setText(food.getName() + "\n" + food.getEnergyValue() + " Ccal");
        return checkBox;
    }

}
