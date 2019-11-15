package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*if(){
            Parent root = FXMLLoader.load(getClass().getResource("heightAndWeight.fxml"));
            primaryStage.setTitle("Weight Helper");
            primaryStage.setScene(new Scene(root, 720, 480));
            primaryStage.setResizable(false);
            primaryStage.show();
        }else{}*/

        Parent root = FXMLLoader.load(getClass().getResource("heightAndWeight.fxml"));
        primaryStage.setTitle("Weight Controller");
        primaryStage.setScene(new Scene(root, 720, 480));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
