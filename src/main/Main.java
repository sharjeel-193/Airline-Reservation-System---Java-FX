package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("./FXML/login.fxml"));
//        primaryStage.setTitle("Login - Your AIR");
//        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setScene(new Scene(root, 1000, 600));
        Parent root = FXMLLoader.load(getClass().getResource("./FXML/admin.fxml"));
        primaryStage.setTitle("Admin Panel - Your AIR");
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
